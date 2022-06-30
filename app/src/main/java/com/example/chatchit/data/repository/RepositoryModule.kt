package com.example.chatchit.data.repository

import com.example.chatchit.data.repository.message.ChatMessageRepository
import com.example.chatchit.data.repository.message.ChatMessageRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @ViewModelScoped
    @Binds
    abstract fun bindChatMessageRepository(
        chatRepositoryImpl: ChatMessageRepositoryImpl
    ): ChatMessageRepository

}