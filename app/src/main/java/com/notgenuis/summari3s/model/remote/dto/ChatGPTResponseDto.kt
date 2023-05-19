package com.notgenuis.summari3s.model.remote.dto

data class ChatGPTResponseDto(
    val id: String,
    val `object`: String,
    val created: Long,
    val model: String,
    val usage: MutableMap<String, Int>,
    val choices: MutableList<ChatGPTChoice>
)
