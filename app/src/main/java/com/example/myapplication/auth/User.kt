package com.example.myapplication.auth

data class User(
    val uid: String,
    val name: String,
    val nickName: String,
    var mileage: Int = 0,
    val currentMileage: Int = 0,
    val petType: Int = 0,

    val sofa: List<String> = listOf(),
    val roof: List<String> = listOf(),
    val wall: List<String> = listOf(),
    val plant: List<String> = listOf(),
    val window: List<String> = listOf(),
    val prop: List<String> = listOf(),
    val wallpaper: List<String> = listOf(),
    val curtain: List<String> = listOf(),

    val like: Int = 0,

    val nsofa: Int = 0,
    val nroof: Int = 0,
    val nwall: Int = 0,
    val nplant: Int = 0,
    val nwindow: Int = 0,
    val nprop: Int = 0,
    val ncurtain: Int = 0,
    val nwallpaper: Int = 0,

    val issueNumRecord : List<String> = listOf(),
    val whereRecord : List<String> = listOf(),
    val timeRecord : List<String> = listOf()

)
