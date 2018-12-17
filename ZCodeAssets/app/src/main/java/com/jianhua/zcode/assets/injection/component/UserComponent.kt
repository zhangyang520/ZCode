package com.jianhua.zcode.assets.injection.component

import com.jianhua.zcode.assets.baselibrary.injection.component.ActivityComponent
import com.jianhua.zcode.assets.baselibrary.injection.module.ActivityScope
import com.jianhua.zcode.assets.baselibrary.injection.module.PerComponentScope
import com.jianhua.zcode.assets.injection.module.UserModule
import com.jianhua.zcode.assets.ui.activity.LoginActivity
import dagger.Component

/**
 *  用户相关的component
 *  @Title ${name}
 *  @ProjectName ZCodeAssets
 *  @Description: TODO
 *  @author Administrator
 *  @date 2018/12/1516:26
 *
 */
@PerComponentScope
@Component(modules = arrayOf(UserModule::class),dependencies = arrayOf(ActivityComponent::class))
interface UserComponent {

    /**
     * 绑定用户的登录界面
     */
    fun  bind(loginActivity: LoginActivity)
}