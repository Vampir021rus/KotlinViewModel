package com.example.kotlinviewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.kotlinviewmodel.retrofit.Message
import com.example.kotlinviewmodel.retrofit.MessageAPI
import kotlinx.android.synthetic.main.activity_retrofit.*
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

    fun getMessage() {
        var retrofit = Retrofit.Builder()
            //добавляем базывй адрес
            .baseUrl(baseURL)
            // создаем json конвертер
            .addConverterFactory(GsonConverterFactory.create())
            //билдим
            .build()

        //передаем в метод create класс интерфейса с методами и получаем реализацию API от Retrofit
        var api: MessageAPI = retrofit.create(MessageAPI::class.java)
        var messages = api.getMessage()

        //с помощью асиннхронного метода делаем запрос
        messages.enqueue(object : Callback<List<Message>> {
            //метод в котором пришел ответ, ответ может быть удачным или создержать коды ошибок типа 404
            override fun onResponse(call: Call<List<Message>>, response: Response<List<Message>>) {
                //проверка на точ то ответ пришел удачный без всяких косяков типа 404 и тд
                if (response.isSuccessful) {
                   //если я правильно понял в response хранится весь ответ в формате json
                    // но чет не очень понял что хранить call
                    var str: String = ""
                    var listMsg = response.body()
                    var length = listMsg!!.size

                    //if(true)
                    //listMsg =null

                    for (i in 0 until length) {
                        //делал тесты если вдруг эдемент может оказаться null
                        // в этом случае лучше проверку на null делать  занком ? вместо !!
                        // с использование ? код не крашится
                        //либо создать условие проверки на пустоту
                        if (listMsg != null) {
                            str = str + "\n" + listMsg.get(i).id.toString()
                            str = str + " " + listMsg?.get(i)?.time.toString()
                            str = str + " " + listMsg!![i].text
                        }
                    }



                    textView.setText(str)
                    Log.d("Good", "response " + response.body());
                } else {
                    Log.d("Error", "Responce code" + response.code())
                }
            }

            //метод если есть какой то косяк с запросом
            override fun onFailure(call: Call<List<Message>>, t: Throwable) {
                Log.v("Error2", t.toString())
            }
        })
    }


}
