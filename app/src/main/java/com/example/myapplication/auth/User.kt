package com.example.myapplication.auth

data class User(
    val uid: String,
    val name: String,
    val nickName: String,
    var mileage: Int = 0,
    val currentMileage: Int = 0,
    val petType: Int = 0,
    val sofa: Int = 0,
    val roof: Int = 0,
    val wall: Int = 0,
    val plant: Int = 0,
    val window: Int = 0,
    val prop: Int = 0,
    val like: Int = 0,
)
