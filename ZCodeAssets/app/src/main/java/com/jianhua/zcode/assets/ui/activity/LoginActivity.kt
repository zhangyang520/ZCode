package com.jianhua.zcode.assets.ui.activity

import android.content.Intent
import android.view.View
import com.jianhua.zcode.assets.R
import com.jianhua.zcode.assets.baselibrary.Rx.getTxt
import com.jianhua.zcode.assets.baselibrary.Rx.hasTxt
import com.jianhua.zcode.assets.baselibrary.exception.ContentException
import com.jianhua.zcode.assets.baselibrary.injection.component.DaggerActivityComponent
import com.jianhua.zcode.assets.baselibrary.ui.activity.BaseMvpActivity
import com.jianhua.zcode.assets.baselibrary.ui.progressbar.KProgressHUD
import com.jianhua.zcode.assets.baselibrary.utils.ToastUtil
import com.jianhua.zcode.assets.data.bean.AssetUser
import com.jianhua.zcode.assets.data.common.AppConstants
import com.jianhua.zcode.assets.data.dao.AssetUserDao
import com.jianhua.zcode.assets.data.request.UserLoginRequest
import com.jianhua.zcode.assets.data.response.UserLoginResponse
import com.jianhua.zcode.assets.injection.component.DaggerUserComponent
import com.jianhua.zcode.assets.presenter.UserPresenter
import com.jianhua.zcode.assets.presenter.view.UserView
import com.suntray.chinapost.baselibrary.ut.base.utils.AppPrefsUtils
import kotlinx.android.synthetic.main.activity_login.*

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
        isRightShow = false
        isTitleShow = false
        isBlackShow = false

        if(!isTaskRoot){
            try {
                //用户本地查询
                AssetUserDao.getLocalUser();
                //直接跳转到首页
                var intent = Intent(this@LoginActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            } catch (e: ContentException) {
                //没有对应的用户
                finish()
                var defaultUserNum=AppPrefsUtils.getString(AppConstants.USER_NUM,"")
                if(!defaultUserNum.equals("")){
                    try {
                        var user=AssetUserDao.getUserBy(defaultUserNum)
                        //有对应的用户
                        ed_name.setText(user.num)
                        ed_pwd.setText(user.pass)
                    } catch (e: ContentException) {
                        //省略吧
                    }
                }
            }
        }else{
                hud2= KProgressHUD(this@LoginActivity).setLabel("登录中...").setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                //登录的点击事件
                btn_login.setOnClickListener({
                    //跳转 到 登录界面
                    if (ed_name.hasTxt() && ed_pwd.hasTxt()) {
                        basePresenter.userLogin(UserLoginRequest(-1,ed_name.getTxt(),ed_pwd.getTxt()))
                    } else {
                        ToastUtil.makeText(this@LoginActivity,"请输入工号 或者 密码")
                    }
                })

                try {
                    //用户本地查询
                    AssetUserDao.getLocalUser();
                    //直接跳转到首页
                    var intent = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } catch (e: ContentException) {
                    //没有对应的用户
                    var defaultUserNum=AppPrefsUtils.getString(AppConstants.USER_NUM,"")
                    if(!defaultUserNum.equals("")){
                        try {
                           var user=AssetUserDao.getUserBy(defaultUserNum)
                           //有对应的用户
                            ed_name.setText(user.num)
                            ed_pwd.setText(user.pass)
                        } catch (e: ContentException) {
                            //省略吧
                        }
                    }
                }
        }
    }

    override fun getView(): View {
        return View.inflate(this@LoginActivity, R.layout.activity_login,null)
    }


    /**
     * 登录成功的回馈
     */
    override fun onLoginResponse(loginResponse: AssetUser) {
        ToastUtil.makeText(this@LoginActivity,"登录成功")
        //直接跳转到首页
        var intent = Intent(this@LoginActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}