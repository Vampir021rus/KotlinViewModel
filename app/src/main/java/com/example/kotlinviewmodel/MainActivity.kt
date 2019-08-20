package com.example.kotlinviewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*
import java.util.function.IntPredicate

class MainActivity : AppCompatActivity() {
    val interactor = Interactor()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textTest.setText("0")

        //без фабрики
        //var myModel = ViewModelProviders.of(this).get(MyViewModel::class.java)
        //c фабрикой
        val myModel = ViewModelProviders.of(this, MyModelFactory(interactor)).get(MyViewModel::class.java)

        //
        val data: LiveData<Int> = myModel.data
        //
        data.observe(this, Observer {kolay->
            textTest.setText(kolay.toString())
        })


        btnIncriment.setOnClickListener(){
            val i = textTest.text.toString().toInt()
            myModel.incValue(i)
        }
    }
}
