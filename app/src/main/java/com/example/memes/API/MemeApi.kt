package com.example.memes.API

import com.example.memes.Model.GetRes
import com.example.memes.Model.PostRes
import com.squareup.moshi.Moshi
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.http.*

private const val BASE_URL = "https://api.imgflip.com/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()


interface MemesApi {
    @GET("get_memes")
    fun getProperties():
            Call<GetRes>

    @POST("caption_image")
    fun makeMeme(
        @Query("template_id") template_id: String?,
        @Query("username") username: String,
        @Query("password") password: String,
        @Query("text0") text0: String?,
        @Query("text1") text1: String?
    ):
            Call<PostRes>


}

object MemeApi {
    val retrofitService: MemesApi by lazy {
        retrofit.create(MemesApi::class.java)
    }
}