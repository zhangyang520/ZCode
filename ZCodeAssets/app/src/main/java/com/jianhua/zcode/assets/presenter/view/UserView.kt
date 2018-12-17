package com.jianhua.zcode.assets.presenter.view

import com.jianhua.zcode.assets.baselibrary.presenter.view.BaseView
import com.jianhua.zcode.assets.data.response.UserLoginResponse

/**
 *  用户相关业务的接口
 *  @Title ${name}
 *  @ProjectName ZCodeAssets
 *  @Description: TODO
 *  @author Administrator
 *  @date 2018/12/1516:29
 *
 */
interface UserView:BaseView{


    /**
     * 用户登录的相关的response
     */
    fun onLoginResponse(loginResponse: UserLoginResponse){

    }
}