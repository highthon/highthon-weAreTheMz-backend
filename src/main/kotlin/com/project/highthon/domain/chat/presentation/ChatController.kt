package com.project.highthon.domain.chat.presentation

import com.project.highthon.domain.chat.presentation.data.request.SendChatRequest
import com.project.highthon.domain.chat.presentation.data.response.ChatResponse
import com.project.highthon.domain.chat.presentation.data.response.RoomResponse
import com.project.highthon.domain.chat.service.ChatService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("chat")
class ChatController(
    private val chatService: ChatService
) {

    @PostMapping("{userId}")
    fun createChattingRoom(@PathVariable userId: Long): ResponseEntity<Void> =
        chatService.createChattingRoom(userId)
            .let { ResponseEntity.status(HttpStatus.CREATED).build() }

    @PostMapping
    fun sendChat(
        @RequestPart(value = "requestDto", required = false) request: SendChatRequest,
        @RequestPart(required = false) file: MultipartFile
    ): ResponseEntity<Void> =
        chatService.sendChat(request, file)
            .let { ResponseEntity.status(HttpStatus.CREATED).build() }

    @GetMapping
    fun findAllMyRoom(): ResponseEntity<List<RoomResponse>> =
        chatService.findAllMyRoom()
            .let { ResponseEntity.ok(it) }

    @GetMapping("{roomId}")
    fun findAllMessage(@PathVariable roomId: Long): ResponseEntity<List<ChatResponse>> =
        chatService.findAllMessage(roomId)
            .let { ResponseEntity.ok(it) }

}