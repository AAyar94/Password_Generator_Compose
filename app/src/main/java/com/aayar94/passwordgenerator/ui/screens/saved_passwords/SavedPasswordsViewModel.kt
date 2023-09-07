package com.aayar94.passwordgenerator.ui.screens.saved_passwords

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aayar94.passwordgenerator.data.Repository
import com.aayar94.passwordgenerator.model.db.SavedPasswordModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SavedPasswordsViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    val passwordList = MutableStateFlow(emptyList<SavedPasswordModel>())
    //val passwordList = MutableLiveData<List<SavedPasswordModel>>()

    init {
        getAllPasswords()
    }

    private fun getAllPasswords() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getAllSavedPasswords()
            passwordList.value = response
        }
    }

    fun deletePassword(savedPasswordModel: SavedPasswordModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deletePassword(savedPasswordModel)
            getAllPasswords()
        }
    }
}