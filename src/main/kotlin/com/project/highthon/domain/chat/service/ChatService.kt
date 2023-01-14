package com.project.highthon.domain.chat.service

import com.project.highthon.domain.chat.presentation.data.request.SendChatRequest
import com.project.highthon.domain.chat.presentation.data.response.ChatResponse
import com.project.highthon.domain.chat.presentation.data.response.RoomResponse
import org.springframework.web.multipart.MultipartFile

interface ChatService {

    fun createChattingRoom(userId: Long)
    fun findAllMessage(roomId: Long): List<ChatResponse>
    fun findAllMyRoom(): List<RoomResponse>
    fun sendChat(request: SendChatRequest, file: MultipartFile)

}