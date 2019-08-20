package com.example.kotlinviewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel() : ViewModel() {
    val data : MutableLiveData<Int> = MutableLiveData()
    val interactor=Interactor()


    fun incValue( value: Int){

        data.value = interactor.incValue(value)
    }
}