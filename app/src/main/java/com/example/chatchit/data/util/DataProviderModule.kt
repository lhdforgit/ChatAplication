package com.example.chatchit.data.util

import android.content.Context
import androidx.room.Room
import com.example.chatchit.data.roomdb.ChatDatabase
import com.example.chatchit.data.roomdb.dao.MessageDao
import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataProviderModule {
    @Singleton
    @Provides
    fun provideFirebaseDataBase(): FirebaseDatabase {
        return FirebaseService.getInstance()
    }

    @Singleton
    @Provides
    fun provideChatDatabase(@ApplicationContext appContext: Context): ChatDatabase {
        return Room.databaseBuilder(
            appContext,
            ChatDatabase::class.java,
            "app_chat.db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideMovieDao(appDatabase: ChatDatabase): MessageDao {
        return appDatabase.messageDao()
    }
}