package com.example.kotlinviewmodel.retrofit

import retrofit2.http.GET
import retrofit2.Call
import retrofit2.http.Path

public interface MessageAPI {
    @GET("message1.json")
    fun getMessage() :Call<List<Message>>


}
