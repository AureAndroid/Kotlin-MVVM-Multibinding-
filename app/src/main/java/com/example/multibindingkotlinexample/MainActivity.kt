package com.example.multibindingkotlinexample

import android.os.Bundle
import android.arch.lifecycle.Observer
import android.support.v7.app.AppCompatActivity
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import com.example.multibindingkotlinexample.factory.DaggerViewModelFactory
import com.example.multibindingkotlinexample.factory.DaggerViewModelFactory_Factory
import com.example.multibindingkotlinexample.viewmodels.ViewModel1
import com.example.multibindingkotlinexample.viewmodels.ViewModel2
import dagger.Binds
import dagger.Component
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.reflect.KClass

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    lateinit var viewModel1: ViewModel1
    lateinit var viewModel2: ViewModel2


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Dagger

        viewModel1 = ViewModelProviders.of(this, factory).get(ViewModel1::class.java)

        viewModel2 = ViewModelProviders.of(this, factory).get(ViewModel2::class.java)

        viewModel1.callData()
        viewModel1.getMutableDataString().observe(this, Observer {
            firsttext.setText(it)
        })

        viewModel2.callData()
        viewModel2.getMutableDataString().observe(this, Observer {
            secondtext.setText(it)
        })
    }
}


    @Component(modules = [MultibindModule::class])
    interface MyComponent {
        fun inject(activity: MainActivity)
    }

    @Module
    internal abstract class MultibindModule {
        //The most important is to bind with ur factory
        //We pass the value and
        // check that which mapkey could be the one key
        @Binds
        abstract fun bindsViewModelfactory(factory: DaggerViewModelFactory): ViewModelProvider

        //ViewModelKey is create below to make the link
        @Binds
        @IntoMap
        @ViewModelKey(ViewModel1::class)
        abstract fun bindmainViewmodel1(viewmodel: ViewModel1): ViewModel

        @Binds
        @IntoMap
        @ViewModelKey(ViewModel2::class)
        abstract fun bindmainViewmode2(viewmodel: ViewModel2): ViewModel

    }

    @MustBeDocumented
    @Target(AnnotationTarget.FUNCTION)
    @Retention(AnnotationRetention.RUNTIME)
    @MapKey
    internal annotation class ViewModelKey(val value: KClass<out ViewModel>)

