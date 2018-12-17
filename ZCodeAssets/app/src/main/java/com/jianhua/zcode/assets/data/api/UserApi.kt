package com.jianhua.zcode.assets.data.api

import com.jianhua.zcode.assets.baselibrary.data.bean.BaseResp
import com.jianhua.zcode.assets.data.bean.AssetUser
import com.jianhua.zcode.assets.data.common.AppConstants
import com.jianhua.zcode.assets.data.request.UserLoginRequest
import com.jianhua.zcode.assets.data.response.UserLoginResponse
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable

/**
 *  用户相关业务的api
 *  @Title ${name}
 *  @ProjectName ZCodeAssets
 *  @Description: TODO
 *  @author Administrator
 *  @date 2018/12/1515:58
 *
 */
interface UserApi {

    /**
     * 用户登录
     */
    @POST(value = AppConstants.loginDo)
    fun userLogin(@Body userLoginRequest: UserLoginRequest):Observable<BaseResp<AssetUser>>
}