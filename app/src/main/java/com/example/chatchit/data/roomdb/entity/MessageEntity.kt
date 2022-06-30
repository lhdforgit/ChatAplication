package com.example.chatchit.data.roomdb.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "message_tb")
data class MessageEntity(
    @PrimaryKey
    val message : String
)
