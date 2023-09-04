package com.aayar94.passwordgenerator.ui.screens.saved_passwords

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aayar94.passwordgenerator.data.Repository
import com.aayar94.passwordgenerator.model.db.SavedPasswordModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SavedPasswordsViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    val passwordList = MutableLiveData<List<SavedPasswordModel>>()

    fun getAllPasswords() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getAllSavedPasswords()
            passwordList.postValue(response.toMutableList())
        }
    }

    fun deletePassword(savedPasswordModel: SavedPasswordModel) {
        repository.deletePassword(savedPasswordModel)
    }

}