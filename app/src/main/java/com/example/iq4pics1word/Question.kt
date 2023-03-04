package com.example.iq4pics1word

data class Question(
    val id: Int,
    val pictureList: MutableList<Int>,
    val answer: String
)
