package com.notgenuis.summari3s.model.remote.dto

data class ChatGPTRequestDto(
    val model: String = "gpt-3.5-turbo",
    val messages: MutableList<ChatGPTRequestMessage>
)