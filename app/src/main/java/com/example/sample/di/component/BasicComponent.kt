package com.example.sample.di.component

import com.example.sample.activity.BaseActivity
import com.example.sample.di.module.ActivityModule
import com.example.sample.di.module.ContextModule
import com.example.sample.fragment.BaseFragment
import dagger.Component
import javax.inject.Singleton


/**
 * Created by neeraj on 30/04/2018.
 */
@Singleton
@Component(modules = [ContextModule::class, ActivityModule::class])
interface BasicComponent {
    fun inject(activity: BaseActivity)
    fun inj(fragment: BaseFragment)
}
