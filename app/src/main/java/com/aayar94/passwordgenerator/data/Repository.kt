package com.aayar94.passwordgenerator.data

import com.aayar94.passwordgenerator.model.db.SavedPasswordModel
import javax.inject.Inject

class Repository @Inject constructor(
    private val savedPasswordsDao: SavedPasswordsDao
) {

    fun savePassword(pswrd: SavedPasswordModel) {
        return savedPasswordsDao.savePasswordToDatabase(pswrd)
    }

    fun getAllSavedPasswords(): List<SavedPasswordModel> {
        return savedPasswordsDao.getAllSavedPasswords()
    }

    fun updatePassword(pswrd: SavedPasswordModel) {
        return savedPasswordsDao.savePasswordToDatabase(pswrd)
    }

    fun deletePassword(pswrd: SavedPasswordModel) {
        return savedPasswordsDao.deleteSavedPassword(pswrd.passwordId!!)
    }

}