package com.example.maymay

import retrofit2.Call
import retrofit2.http.GET

interface MemeAPI {

    @GET("get_memes")
    fun getMemes(): Call<MemeModel>
}