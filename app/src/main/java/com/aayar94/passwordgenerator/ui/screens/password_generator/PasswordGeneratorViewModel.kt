package com.aayar94.passwordgenerator.ui.screens.password_generator

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aayar94.passwordgenerator.data.Repository
import com.aayar94.passwordgenerator.model.db.SavedPasswordModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PasswordGeneratorViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    fun savePassword(password: String, passwordTag: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val passwordObject = SavedPasswordModel(null, password, passwordTag)
            repository.savePassword(passwordObject)
        }
    }
}