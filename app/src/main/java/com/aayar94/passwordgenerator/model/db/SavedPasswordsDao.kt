package com.aayar94.passwordgenerator.model.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aayar94.passwordgenerator.common.Constant.Companion.PASSWORDS_TABLE_NAME

@Dao
interface SavedPasswordsDao {

    @Query("SELECT * FROM $PASSWORDS_TABLE_NAME")
    fun getAllSavedPasswords(): List<SavedPasswordModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun savePasswordToDatabase(savedPasswordModel: SavedPasswordModel)

    @Query("DELETE FROM $PASSWORDS_TABLE_NAME WHERE passwordId = :id")
    fun deleteSavedPassword(id: Int)
}