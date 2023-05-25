package com.aayar94.passwordgenerator.model.password.content

interface PasswordContent {

    val content: String

    fun getRandom(): Char {
        val index = (Math.random() * content.length).toInt()
        return content[index]
    }

}