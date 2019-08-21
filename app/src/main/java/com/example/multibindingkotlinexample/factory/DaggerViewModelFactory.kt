package com.example.multibindingkotlinexample.factory

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException
import javax.inject.Inject
import javax.inject.Provider

/*
    u just make a add inject
    constructor
    I'm just making the creator
    that which u can map
    mapping it's ur model u can map
    wich class u need
    it's viewmodel type
 */
class DaggerViewModelFactory
    @Inject constructor(private val creators :Map<Class<out ViewModel>,
            @JvmSuppressWildcards Provider<ViewModel>>):ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        var creator:Provider<ViewModel>?=creators[modelClass]
        if(creator == null)
            //if we can map we can assign it
            for((key,value) in creators)
            {
                if(modelClass.isAssignableFrom(key))
                {
                    creator = value
                    break
                }
            }
        if(creator==null)throw IllegalArgumentException("unknown model class")
         try{
            return creator.get() as T
         }
         catch(e:Exception){
             throw RuntimeException(e)
         }
    }
}
//permit be instruct the compiler to join