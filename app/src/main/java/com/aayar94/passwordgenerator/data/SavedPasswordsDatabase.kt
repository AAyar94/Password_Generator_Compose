package com.aayar94.passwordgenerator.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.aayar94.passwordgenerator.model.db.SavedPasswordModel

@Database(entities = [SavedPasswordModel::class], version = 1, exportSchema = false)
abstract class SavedPasswordsDatabase : RoomDatabase() {
    abstract fun savedPasswordsDao(): SavedPasswordsDao

}