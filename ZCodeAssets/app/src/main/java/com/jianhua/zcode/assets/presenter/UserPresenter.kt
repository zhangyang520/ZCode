package com.jianhua.zcode.assets.presenter

import com.jianhua.zcode.assets.baselibrary.presenter.BasePresenter
import com.jianhua.zcode.assets.data.request.UserLoginRequest
import com.jianhua.zcode.assets.data.response.UserLoginResponse
import com.jianhua.zcode.assets.presenter.view.UserView
import com.jianhua.zcode.assets.service.UserService
import com.jianhua.zcode.assets.service.impl.UserServiceImpl
import javax.inject.Inject

/**
 *  用户相关 业务的 逻辑类
 *  @Title ${name}
 *  @ProjectName ZCodeAssets
 *  @Description: TODO
 *  @author Administrator
 *  @date 2018/12/1516:21
 *
 */
class UserPresenter @Inject constructor():BasePresenter<UserView>(){

    @Inject
    lateinit var userService: UserServiceImpl

    /**
     * 用户登录 的业务操作
     */
    fun userLogin(userLoginRequest: UserLoginRequest) {

    }
}