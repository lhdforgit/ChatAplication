package com.example.chatchit.data.roomdb.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.chatchit.common.NodeConstant
import kotlin.random.Random

@Entity(tableName = "message_tb")
data class MessageEntity(
    @PrimaryKey
    var id: String = "",
    val roomId: String = "",
    var userId: String = "",
    var message: String = "",
    var time: Long = 0
)


object MessageUtil {
    fun createMessage(message: String): MessageEntity {
        val userId = NodeConstant.userId
        val roomId = NodeConstant.roomId
        val time = System.currentTimeMillis()
        val id = "id_${System.currentTimeMillis()}"
        return MessageEntity(
            id = id,
            roomId = roomId,
            userId = userId,
            message = message,
            time = time
        )
    }
}
