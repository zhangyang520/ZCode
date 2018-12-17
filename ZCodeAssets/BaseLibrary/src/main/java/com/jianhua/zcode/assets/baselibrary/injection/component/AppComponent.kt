package com.jianhua.zcode.assets.baselibrary.injection.component

import android.content.Context
import com.jianhua.zcode.assets.baselibrary.injection.module.AppModule
import dagger.Component

/**
 * Created by zhangyang on 2018/5/28 09:57.
 * version 1
 * 在
 */
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {
    fun context():Context
}