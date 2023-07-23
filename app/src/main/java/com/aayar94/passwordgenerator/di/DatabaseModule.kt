package com.aayar94.passwordgenerator.di

import android.content.Context
import androidx.room.Room
import com.aayar94.passwordgenerator.common.Constant.Companion.DATABASE_NAME
import com.aayar94.passwordgenerator.model.db.SavedPasswordsDao
import com.aayar94.passwordgenerator.model.db.SavedPasswordsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ): SavedPasswordsDatabase{
        return Room.databaseBuilder(
            context,
            SavedPasswordsDatabase::class.java,
            DATABASE_NAME
        ).build()
    }

    @Singleton
    @Provides
    fun provideSavedPasswordsDao(database: SavedPasswordsDatabase) : SavedPasswordsDao{
        return database.savedPasswordsDao()
    }

}