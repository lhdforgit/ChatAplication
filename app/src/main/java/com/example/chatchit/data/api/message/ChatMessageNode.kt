package com.example.chatchit.data.api.message

import com.google.firebase.database.Query

interface ChatMessageNode {
   suspend fun messageNode(roomId : String) : Query
}