package com.example.multibindingkotlinexample.repository

import android.arch.lifecycle.MutableLiveData
import javax.inject.Inject

class Repositry1
{
    @Inject
    constructor()
    var mutableLiveData = MutableLiveData<String>()

    fun getMutableData()
    {
        mutableLiveData.value = "This is repository1"
    }

    fun getMutableDataString():MutableLiveData<String>
    {
        return mutableLiveData
    }

}