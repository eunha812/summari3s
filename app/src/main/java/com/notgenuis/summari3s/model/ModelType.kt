package com.notgenuis.summari3s.model

enum class ModelType(val typeName: String) {
    CHAT_GPT("gpt"), GOOGLE("google");

    companion object {
        fun String.toModelType() = when (this) {
            CHAT_GPT.typeName -> CHAT_GPT
            else -> GOOGLE
        }
    }
}