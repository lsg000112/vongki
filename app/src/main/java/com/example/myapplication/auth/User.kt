package com.example.myapplication.auth

data class User(
    val uid: String,
    val name: String,
    val nickName: String,
    var mileage: Int = 0,
    val petType: Int = 0
)
