package com.example.kotlinviewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MyModelFactory(val interactor: Interactor) : ViewModelProvider.NewInstanceFactory() {

    //переопределяем метод create
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        //прверяем есть ли такой класс ViewModel
        if (modelClass.isAssignableFrom(MyViewModel::class.java)) {
            return MyViewModel(interactor) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
