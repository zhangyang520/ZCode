package com.jianhua.zcode.assets.injection.module

import com.jianhua.zcode.assets.baselibrary.injection.module.ActivityScope
import com.jianhua.zcode.assets.baselibrary.injection.module.PerComponentScope
import com.jianhua.zcode.assets.service.UserService
import com.jianhua.zcode.assets.service.impl.UserServiceImpl
import dagger.Module
import dagger.Provides

/**
 *  @Title ${name}
 *  @ProjectName ZCodeAssets
 *  @Description: TODO
 *  @author Administrator
 *  @date 2018/12/1516:23
 *
 */
@PerComponentScope
@Module
class UserModule {

    @Provides
    fun provideUserServiceImpl(userServiceImpl: UserServiceImpl):UserService{
        return userServiceImpl;
    }
}