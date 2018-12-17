package com.jianhua.zcode.assets.service

import com.jianhua.zcode.assets.data.bean.AssetUser
import com.jianhua.zcode.assets.data.request.UserLoginRequest
import com.jianhua.zcode.assets.data.response.UserLoginResponse
import rx.Observable

/**
 *  用户的服务
 *  @Title ${name}
 *  @ProjectName ZCodeAssets
 *  @Description: TODO
 *  @author Administrator
 *  @date 2018/12/1516:13
 *
 */
interface UserService {

    /**
     * 用户登录
     */
    fun userLogin(userLoginRequest: UserLoginRequest):Observable<AssetUser>
}