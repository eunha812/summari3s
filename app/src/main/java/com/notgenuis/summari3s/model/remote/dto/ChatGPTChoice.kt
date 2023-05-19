package com.notgenuis.summari3s.model.remote.dto

import com.google.gson.annotations.SerializedName

data class ChatGPTChoice(
    val message: ChatGPTResponseMessage,
    @SerializedName("finish_reason")
    val finishReason: String,
    val index: Int
)
