package com.example.memes.Model



data class GetRes (
    val success: Boolean,
    val data: MemesArray
)

data class MemesArray (
    val memes: List<MemeData>
)