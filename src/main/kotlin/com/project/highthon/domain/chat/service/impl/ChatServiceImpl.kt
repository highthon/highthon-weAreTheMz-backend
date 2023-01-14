package com.project.highthon.domain.chat.service.impl

import com.project.highthon.domain.chat.domain.Chat
import com.project.highthon.domain.chat.domain.Room
import com.project.highthon.domain.chat.domain.RoomUser
import com.project.highthon.domain.chat.domain.repository.ChatRepository
import com.project.highthon.domain.chat.domain.repository.RoomRepository
import com.project.highthon.domain.chat.domain.repository.RoomUserRepository
import com.project.highthon.domain.chat.presentation.data.request.SendChatRequest
import com.project.highthon.domain.chat.presentation.data.request.toEntity
import com.project.highthon.domain.chat.presentation.data.response.ChatResponse
import com.project.highthon.domain.chat.presentation.data.response.RoomResponse
import com.project.highthon.domain.chat.service.ChatService
import com.project.highthon.domain.user.utils.UserUtil
import com.project.highthon.global.annotation.TransactionWithReadOnly
import com.project.highthon.infrastructure.s3.service.S3Service
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile
import java.util.*

@Service
class ChatServiceImpl(
    private val roomRepository: RoomRepository,
    private val roomUserRepository: RoomUserRepository,
    private val chatRepository: ChatRepository,
    private val s3Service: S3Service,
    private val userUtil: UserUtil
): ChatService {

    @Transactional(rollbackFor = [Exception::class])
    override fun createChattingRoom(userId: Long) {
        val room = roomRepository.save(Room(-1, Collections.emptyList(), ""))
        val roomUsers = arrayListOf<RoomUser>()
        roomUsers.add(RoomUser(-1, room, userUtil.currentUser()))
        roomUsers.add(RoomUser(-1, room, userUtil.findUserById(userId)))
        roomUserRepository.saveAll(roomUsers)
    }

    @TransactionWithReadOnly
    override fun findAllMessage(roomId: Long): List<ChatResponse> {
        val chatList = chatRepository.findChatsByRoomIdOrderByIdAsc(roomId)
        return chatList.map { ChatResponse(it.id, it.user == userUtil.currentUser(), it.message) }
    }

    @TransactionWithReadOnly
    override fun findAllMyRoom(): List<RoomResponse> {
        val roomUser = roomUserRepository.findRoomUserByUser(userUtil.currentUser())
        return roomUser.map { RoomResponse(it.room.id, it.user.name, it.room.lastMessage) }
    }

    @Transactional(rollbackFor = [Exception::class])
    override fun sendChat(request: SendChatRequest, file: MultipartFile) {
        val room = roomRepository.findRoomById(request.roomId) ?: throw RuntimeException()
        if(!file.isEmpty) {
            val url = "https://mzip-s3-bucket.s3.ap-northeast-2.amazonaws.com/REELS" + s3Service.uploadFile(file, "CHAT/")
            chatRepository.save(Chat(-1, url, userUtil.currentUser(), room))
            room.updateLastMessage(url)
        }
        chatRepository.save(request.toEntity(userUtil.currentUser(), room))
        room.updateLastMessage(request.message)
    }

}