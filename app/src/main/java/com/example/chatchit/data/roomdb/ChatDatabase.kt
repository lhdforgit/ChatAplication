package com.example.chatchit.data.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.chatchit.data.roomdb.dao.MessageDao
import com.example.chatchit.data.roomdb.entity.MessageEntity

@Database(entities = [MessageEntity::class], version = 1)
abstract class ChatDatabase : RoomDatabase(){
    abstract fun messageDao(): MessageDao
}