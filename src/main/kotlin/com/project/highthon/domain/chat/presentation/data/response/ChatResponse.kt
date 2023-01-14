package com.project.highthon.domain.chat.presentation.data.response

import com.fasterxml.jackson.annotation.JsonProperty

data class ChatResponse(
    val chatId: Long,
    @JsonProperty("isMine")
    val isMine: Boolean,
    val message: String
)