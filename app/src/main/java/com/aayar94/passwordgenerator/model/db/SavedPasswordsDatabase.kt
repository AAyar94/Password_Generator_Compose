package com.aayar94.passwordgenerator.model.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [SavedPasswordModel::class], version = 1, exportSchema = false)
abstract class SavedPasswordsDatabase : RoomDatabase() {
    abstract fun savedPasswordsDao(): SavedPasswordsDao

}