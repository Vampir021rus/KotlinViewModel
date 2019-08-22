package com.example.kotlinviewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlinviewmodel.retrofit.Message
import com.example.kotlinviewmodel.retrofit.MessageAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitActivity : AppCompatActivity() {
    val baseURL = "https://rawgit.com/startandroid/data/master/messages/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit)
        getMessage()

    }

    fun getMessage(){
        var retrofit = Retrofit.Builder()
            //добавляем базывй адрес
            .baseUrl(baseURL)
            // создаем json конвертер
            .addConverterFactory(GsonConverterFactory.create())
            //билдим
            .build()

        //передаем в метод create класс интерфейса с методами и получаем реализацию API от Retrofit
        var api:MessageAPI = retrofit.create(MessageAPI::class.java)
        var messages  = api.getMessage()

        //с помощью асиннхронного метода делаем запрос
        messages.enqueue(Callback<List<Message>>(){
            

        })
    }

    }
}
