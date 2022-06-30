package com.example.chatchit.data.api.message

import com.example.chatchit.common.NodeConstant
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
import javax.inject.Inject

class ChatMessageNodeImpl @Inject constructor(private val database: FirebaseDatabase) :
    ChatMessageNode {
    override suspend fun messageNode(roomId : String): Query {
        return database.getReference(NodeConstant.MESSAGE_NOTE).child(roomId)
    }
}