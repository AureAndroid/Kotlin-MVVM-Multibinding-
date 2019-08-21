package com.example.multibindingkotlinexample.viewmodels

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.multibindingkotlinexample.repository.Repositry2
import javax.inject.Inject

class ViewModel2: ViewModel
{
    @Inject
    constructor()
    var repositry2 = Repositry2()
    var mutableLiveData = MutableLiveData<String>()

    fun callData()
    {
        repositry2.getMutableData()
    }
    fun getMutableDataString(): MutableLiveData<String>
    {
        mutableLiveData = repositry2.getMutableDataString()
        return mutableLiveData
    }
}