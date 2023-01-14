package com.project.highthon.domain.chat.domain.repository

import com.project.highthon.domain.chat.domain.Chat
import org.springframework.data.repository.CrudRepository

interface ChatRepository: CrudRepository<Chat, Long> {

    fun findChatsByRoomIdOrderByIdAsc(roomId: Long): List<Chat>

}