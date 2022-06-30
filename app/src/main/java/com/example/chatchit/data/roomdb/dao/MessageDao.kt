package com.example.chatchit.data.roomdb.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao
import com.example.chatchit.data.roomdb.entity.MessageEntity

@Dao
interface MessageDao {
    @Query("SELECT * FROM message_tb")
    fun getMessageList(): LiveData<List<MessageEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMessage(movies: MessageEntity)

    @Delete
    fun delete(movie: MessageEntity)

    @Delete
    fun deleteAll(movie: List<MessageEntity>)
}