package com.example.multibindingkotlinexample.viewmodels

import android.view.View
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.multibindingkotlinexample.repository.Repositry1
import javax.inject.Inject

//In a viewmodel we have to walk and check
// it which kind of data that we want
class ViewModel1 : ViewModel
{
    @Inject
    constructor()
    var repositry1 = Repositry1()
    var mutableLiveData = MutableLiveData<String>()

    fun callData()
    {
        repositry1.getMutableData()
    }
    fun getMutableDataString(): MutableLiveData<String>
    {
        return mutableLiveData
    }
}