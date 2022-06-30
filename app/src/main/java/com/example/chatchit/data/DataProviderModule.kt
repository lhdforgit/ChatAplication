package com.example.chatchit.data

import com.example.chatchit.data.util.FirebaseService
import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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
}