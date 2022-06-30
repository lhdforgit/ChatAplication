package com.example.chatchit.data.api

import com.example.chatchit.data.api.message.ChatMessageNode
import com.example.chatchit.data.api.message.ChatMessageNodeImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class ApiModule {
    @ViewModelScoped
    @Binds
    abstract fun bindChatMessageApi(
        chatMessageNodeImpl: ChatMessageNodeImpl
    ): ChatMessageNode
}