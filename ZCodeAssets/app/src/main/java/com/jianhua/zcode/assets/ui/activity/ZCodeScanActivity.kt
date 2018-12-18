package com.jianhua.zcode.assets.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Vibrator
import android.view.View
import cn.bingoogolapple.qrcode.core.QRCodeView
import com.jianhua.zcode.assets.R
import com.jianhua.zcode.assets.baselibrary.ui.activity.BaseMvpActivity
import com.jianhua.zcode.assets.baselibrary.utils.DateUtil
import com.jianhua.zcode.assets.baselibrary.utils.ToastUtil
import com.jianhua.zcode.assets.data.common.AppConstants
import com.jianhua.zcode.assets.data.dao.AssetUserDao
import com.jianhua.zcode.assets.data.request.ZCoderActionRequest
import com.jianhua.zcode.assets.data.response.ZCodeActionResponse
import com.jianhua.zcode.assets.injection.component.DaggerAssetComponent
import com.jianhua.zcode.assets.presenter.AssetPresenter
import com.jianhua.zcode.assets.presenter.view.AssetView
import com.suntray.chinapost.baselibrary.ut.base.utils.AppPrefsUtils
import kotlinx.android.synthetic.main.activity_zcode_saoma.*
import java.util.*

/**
 *  扫描二维码的界面
 *  @Title ${name}
 *  @ProjectName ZCodeAssets
 *  @Description: TODO
 *  @author Administrator
 *  @date 2018/12/1817:49
 *
 */
class ZCodeScanActivity : BaseMvpActivity<AssetPresenter>(), AssetView, QRCodeView.Delegate  {
    override fun injectCompontent() {
       DaggerAssetComponent.builder().activityComponent(activityComponent).build().bind(this)
       basePresenter.baseView=this
    }

    override fun initView() {
        isBlackShow=false
        isTitleShow=false
        isRightShow = false

        //设置对应的 桥接模式
        zxingview.setDelegate(this)

        //启动相机
        zxingview.startCamera()
        //矩形 框 扫码
        zxingview.showScanRect()
        //启动 spot
        zxingview.startSpot()
    }


    override fun onStop() {
        super.onStop()
        zxingview.stopCamera()
    }

    override fun onDestroy() {
        super.onDestroy()
        zxingview.destroyDrawingCache()
        zxingview.onDestroy()
    }
    override fun getView(): View {
        return View.inflate(this@ZCodeScanActivity, R.layout.activity_zcode_saoma,null)
    }


    /**
     * 扫描 二维码  成功回馈
     */
    override fun onScanQRCodeSuccess(result: String?) {

        println("onScanQRCodeSuccess result:"+result)
        //获取到字符串  之后 请求接口
        var pandate = AppPrefsUtils.getString(AppConstants.Inventory_Date_Name, DateUtil.dateFormatMonth(Calendar.getInstance().time))
        //执行 扫码 网络的操作
        basePresenter.zcodeAction(ZCoderActionRequest(-1,result!!,pandate,AssetUserDao.getLocalUser().name))
        //震动
        val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        vibrator.vibrate(200)
        //开始扫码
        zxingview.stopSpot();
    }

    override fun onRestart() {
        super.onRestart()
        //启动相机
        zxingview.startCamera()
        //矩形 框 扫码
        zxingview.showScanRect()
        zxingview.startSpot()
    }
    /**
     * 扫码 失败
     */
    override fun onScanQRCodeOpenCameraError() {
            zxingview.startSpot();
            //提示 的错误信息
            ToastUtil.makeText(this@ZCodeScanActivity,"扫码错误!")
    }

    /**
     * 扫码操作的返回
     *
     */
    override fun onZCodeAction(zCodeActionResponse: ZCodeActionResponse) {
        super.onZCodeAction(zCodeActionResponse)
        //成功的获取信息
        var intent = Intent(this@ZCodeScanActivity, ZCodeDeviceDetailActivity::class.java)
        intent.putExtra("zcodeAction",zCodeActionResponse)
        //启动对应的 intent
        startActivity(intent)
    }

}