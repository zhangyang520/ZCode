package com.jianhua.zcode.assets.ui.activity

import android.view.View
import com.jianhua.zcode.assets.R
import com.jianhua.zcode.assets.baselibrary.injection.component.DaggerActivityComponent
import com.jianhua.zcode.assets.baselibrary.ui.activity.BaseMvpActivity
import com.jianhua.zcode.assets.data.response.UserLoginResponse
import com.jianhua.zcode.assets.injection.component.DaggerUserComponent
import com.jianhua.zcode.assets.presenter.UserPresenter
import com.jianhua.zcode.assets.presenter.view.UserView

/**
 *  登录的界面
 *  @Title ${name}
 *  @ProjectName ZCodeAssets
 *  @Description: TODO
 *  @author Administrator
 *  @date 2018/12/1418:29
 *
 */
class LoginActivity : BaseMvpActivity<UserPresenter>(),UserView{
    override fun injectCompontent() {
        DaggerUserComponent.builder().activityComponent(activityComponent).build().bind(this)
        this.basePresenter.baseView=this
    }

    override fun initView() {
        isRightShow=false
        isTitleShow=false
        isBlackShow=false
    }

    override fun getView(): View {
        return View.inflate(this@LoginActivity, R.layout.activity_login,null)
    }

    override fun onLoginResponse(loginResponse: UserLoginResponse) {
        super.onLoginResponse(loginResponse)
    }
}