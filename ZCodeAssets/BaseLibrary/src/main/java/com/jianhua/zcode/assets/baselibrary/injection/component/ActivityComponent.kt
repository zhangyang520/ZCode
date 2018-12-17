package com.jianhua.zcode.assets.baselibrary.injection.component

import android.app.Activity
import android.content.Context
import com.jianhua.zcode.assets.baselibrary.injection.module.ActivityModule
import com.jianhua.zcode.assets.baselibrary.injection.module.ActivityScope
import com.jianhua.zcode.assets.baselibrary.injection.module.LifecylerProviderModule
import com.trello.rxlifecycle.LifecycleProvider
import dagger.Component


/**
 * Ceated by zhangyang on 2018/5/28 10:29.
 * version 1
 */
@ActivityScope
@Component(modules = arrayOf(ActivityModule::class, LifecylerProviderModule::class),dependencies = arrayOf(AppComponent::class))
interface ActivityComponent {
    fun context():Context
    fun activity():Activity
    fun lifecylerProvider(): LifecycleProvider<*>
}