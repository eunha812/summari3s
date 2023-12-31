package com.notgenius.summari3s.model.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("message")
data class MessageEntity(
    var senderNumber: String,
    var date: String,
    var origin: String,
    var result: String?
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    constructor(id: Long, senderNumber: String, date: String, origin: String, result: String) : this(senderNumber, date, origin, result) {
        this.id = id
    }
}