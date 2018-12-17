package com.jianhua.zcode.assets.service.impl

import com.jianhua.zcode.assets.baselibrary.Rx.convertData
import com.jianhua.zcode.assets.data.bean.AssetUser
import com.jianhua.zcode.assets.data.request.UserLoginRequest
import com.jianhua.zcode.assets.data.response.UserLoginResponse
import com.jianhua.zcode.assets.data.respository.UserRespository
import com.jianhua.zcode.assets.service.UserService
import rx.Observable
import javax.inject.Inject

/**
 *  @Title ${name}
 *  @ProjectName ZCodeAssets
 *  @Description: TODO
 *  @author Administrator
 *  @date 2018/12/1516:19
 *
 */
class UserServiceImpl @Inject constructor():UserService {

    @Inject
    lateinit var userRespository: UserRespository

    override fun userLogin(userLoginRequest: UserLoginRequest): Observable<AssetUser> {
        return userRespository.userLogin(userLoginRequest).convertData()
    }
}