package com.aayar94.passwordgenerator.model.password.content

data class LowerPwdContentImpl(override var content: String = "abcdefghijklmnopqrstuvwxyz") :
    PasswordContent

object LowerPwdContent {
    private var instance: PasswordContent = LowerPwdContentImpl()

    fun getInstance(): PasswordContent {
        return instance
    }
}