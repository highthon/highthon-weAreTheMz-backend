package com.project.highthon.domain.chat.presentation.data.request

import com.project.highthon.domain.chat.domain.Chat
import com.project.highthon.domain.chat.domain.Room
import com.project.highthon.domain.user.domain.User

data class SendChatRequest(
    val roomId: Long,
    val message: String
)

fun SendChatRequest.toEntity(user: User, room: Room): Chat =
    Chat(
        id = -1,
        message = message,
        user = user,
        room = room
    )