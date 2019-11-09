package com.example.memes.Model


data class PostRes (
    val success : Boolean = false,
    val data : UrlObject?= UrlObject("", ""),
    val error_message: String? = ""

)


data class UrlObject (
    val url : String = "",
    val page_url : String = ""
)