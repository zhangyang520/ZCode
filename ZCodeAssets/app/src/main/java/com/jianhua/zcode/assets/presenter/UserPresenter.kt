package com.jianhua.zcode.assets.presenter

import com.jianhua.zcode.assets.baselibrary.Rx.BaseSucriber
import com.jianhua.zcode.assets.baselibrary.Rx.assertMethod
import com.jianhua.zcode.assets.baselibrary.Rx.execute
import com.jianhua.zcode.assets.baselibrary.exception.ContentException
import com.jianhua.zcode.assets.baselibrary.presenter.BasePresenter
import com.jianhua.zcode.assets.baselibrary.utils.NetWorkUtils
import com.jianhua.zcode.assets.data.bean.AssetUser
import com.jianhua.zcode.assets.data.bean.AssetsBean
import com.jianhua.zcode.assets.data.common.AppConstants
import com.jianhua.zcode.assets.data.dao.AssetUserDao
import com.jianhua.zcode.assets.data.request.UserLoginRequest
import com.jianhua.zcode.assets.data.response.UserLoginResponse
import com.jianhua.zcode.assets.presenter.view.AssetView
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
        //用户登录
        userService.userLogin(userLoginRequest)
                .execute(object: BaseSucriber<AssetUser>(baseView,AssetPresenter::class.java.simpleName){

                    /**
                     * 开始
                     */
                    override fun onStart() {

                    }

                    /**
                     * 错误的回调
                     */
                    override fun onError(e: Throwable?) {
                        assertMethod(baseView,{
                            if(e is ContentException){
                                assertMethod(baseView,{
                                    baseView.hideLoading()
                                    (baseView as UserView).onError(e.errorContent)
                                })
                            }else{
                                assertMethod(baseView,{
                                    baseView.hideLoading()
                                    if(NetWorkUtils.isNetworkConnected(context)){
                                        (baseView as UserView).onError(AppConstants.SERVER_ERROR)
                                    }else{
                                        (baseView as UserView).onError(AppConstants.NO_NET_WORK)
                                    }
                                })
                            }
                        })
                    }

                    /**
                     * 成功的结果返回
                     */
                    override fun onNext(t: AssetUser) {
                        super.onNext(t)
                        assertMethod(baseView,{
                            baseView.hideLoading()
                            //进行保存用户 信息
                            try {
                                AssetUserDao.updateAllUserLocalState(false)
                                AssetUserDao.getUserBy(t.id)
                                t.isLocalUser=true
                                t.pass=userLoginRequest.pass
                                t.account=userLoginRequest.account
                                AssetUserDao.saveUser(t)
                            } catch (e: ContentException) {
                                t.pass=userLoginRequest.pass
                                t.account=userLoginRequest.account
                                t.isLocalUser=true
                                AssetUserDao.saveUser(t)
                            }

                            (baseView as UserView).onLoginResponse(t)
                        })
                    }
                },lifecylerProvider)
    }
}