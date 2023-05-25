package com.aayar94.passwordgenerator.model.password.content

data class NumericPwdContentImpl(override var content: String = "0123456789") :
    PasswordContent

object NumericPwdContent {
    private var instance: PasswordContent = NumericPwdContentImpl()

    fun getInstance(): PasswordContent {
        return instance
    }
}