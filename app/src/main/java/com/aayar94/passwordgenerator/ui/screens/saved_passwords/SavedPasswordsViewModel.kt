package com.aayar94.passwordgenerator.ui.screens.saved_passwords

import android.app.assist.AssistStructure.ViewNode
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aayar94.passwordgenerator.data.Repository
import com.aayar94.passwordgenerator.model.db.SavedPasswordModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SavedPasswordsViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    val passwordList = MutableLiveData<List<SavedPasswordModel>>()

    fun getAllPasswords() {
        val response = repository.getAllSavedPasswords()
        passwordList.postValue(response)
    }

    fun deletePassword(savedPasswordModel: SavedPasswordModel) {
        repository.deletePassword(savedPasswordModel)
    }

}