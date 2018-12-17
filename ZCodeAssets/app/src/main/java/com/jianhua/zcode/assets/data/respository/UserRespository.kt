package com.jianhua.zcode.assets.data.respository

import com.jianhua.zcode.assets.baselibrary.data.bean.BaseResp
import com.jianhua.zcode.assets.baselibrary.data.net.RetrofitFactory
import com.jianhua.zcode.assets.data.api.UserApi
import com.jianhua.zcode.assets.data.bean.AssetUser
import com.jianhua.zcode.assets.data.request.UserLoginRequest
import com.jianhua.zcode.assets.data.response.UserLoginResponse
import retrofit2.Retrofit
import retrofit2.http.Body
import rx.Observable
import javax.inject.Inject

/**
 *  用户网络请求仓库
 *  @Title ${name}
 *  @ProjectName ZCodeAssets
 *  @Description: TODO
 *  @author Administrator
 *  @date 2018/12/1516:10
 *
 */
class UserRespository @Inject constructor(){

    /**
     * 用户登录
     */
    fun userLogin(userLoginRequest: UserLoginRequest): Observable<BaseResp<AssetUser>>{
        return RetrofitFactory.instance.create(UserApi::class.java).userLogin(userLoginRequest)
    }
}