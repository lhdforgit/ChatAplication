package com.example.chatchit.data.repository.message

import androidx.lifecycle.LiveData
import com.example.chatchit.common.FirebaseQueryLiveData
import com.example.chatchit.data.api.message.ChatMessageNode
import com.example.chatchit.data.roomdb.dao.MessageDao
import com.example.chatchit.data.roomdb.entity.MessageEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ChatMessageRepositoryImpl @Inject constructor(
    private val node: ChatMessageNode,
    private val dao: MessageDao
) : ChatMessageRepository {

    override suspend fun getMessage(roomId: String): LiveData<String> {
        node.messageNode(roomId).let { query ->
            return FirebaseQueryLiveData(query)
        }
    }

    override suspend fun sendMessage(message: String, roomId: String) {
        node.messageNode(roomId).ref.setValue(message)
            .addOnSuccessListener {

            }
            .addOnFailureListener {

            }
    }

    override suspend fun getListMessage(): LiveData<List<MessageEntity>> {
        return dao.getMessageList()
    }

    override suspend fun insertMessage(message: MessageEntity) {
        withContext(Dispatchers.IO){
            dao.insertMessage(message)
        }
    }

}