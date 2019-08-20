package com.example.kotlinviewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel(val interactor:Interactor) : ViewModel() {
    val data : MutableLiveData<Int> = MutableLiveData()


    fun incValue( value: Int){
        data.value = interactor.incValue(value)
    }
}