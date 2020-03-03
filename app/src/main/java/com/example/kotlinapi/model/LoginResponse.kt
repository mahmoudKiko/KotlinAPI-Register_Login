package com.example.kotlinapi.model

data class LoginResponse(val error: Boolean, val message:String, val user: User)