package com.example.im.model

import com.google.gson.Gson

class AuthorModel(private val token: String) {
    private val act = "access"
    private val op = "login"
    private val msgtype = "message"

    override fun toString(): String {
        return Gson().toJson(this)
    }
}
