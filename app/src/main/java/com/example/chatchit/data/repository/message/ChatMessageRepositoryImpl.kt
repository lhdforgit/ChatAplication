package com.example.chatchit.data.repository.message

import androidx.lifecycle.LiveData
import com.example.chatchit.common.FirebaseQueryLiveData
import com.example.chatchit.data.api.message.ChatMessageNode
import javax.inject.Inject


class ChatMessageRepositoryImpl @Inject constructor(private val node: ChatMessageNode) : ChatMessageRepository {

    override suspend fun getMessage(roomId: String): LiveData<String> {
        node.messageNode(roomId).let { query ->
           return FirebaseQueryLiveData(query)
        }
    }

    override suspend fun sendMessage(message: String, roomId: String){
        node.messageNode(roomId).ref.setValue(message)
            .addOnSuccessListener {

            }
            .addOnFailureListener {

            }
    }
}