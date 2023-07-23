package com.aayar94.passwordgenerator.model.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.aayar94.passwordgenerator.common.Constant.Companion.PASSWORDS_TABLE_NAME


@Entity(tableName = PASSWORDS_TABLE_NAME)
data class SavedPasswordModel(
    @PrimaryKey(autoGenerate = true)
    val passwordId: Int? = 0,
    val password: String?,
    val tag: String?,
)