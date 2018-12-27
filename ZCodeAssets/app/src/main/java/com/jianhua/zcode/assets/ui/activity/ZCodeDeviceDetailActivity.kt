package com.jianhua.zcode.assets.ui.activity

import android.view.View
import com.jianhua.zcode.assets.R
import com.jianhua.zcode.assets.baselibrary.ui.activity.BaseAcvitiy
import com.jianhua.zcode.assets.data.response.ZCodeActionResponse
import kotlinx.android.synthetic.main.activity_zcode_detail.*
import kotlinx.android.synthetic.main.item_client_info_layout_show.*
import kotlinx.android.synthetic.main.item_device_pan_info_layout_show.*

/**
 *
 *  扫码回调 的结果 的详情界面
 *  @Title ${name}
 *  @ProjectName ZCodeAssets
 *  @Description: TODO
 *  @author Administrator
 *  @date 2018/12/1818:19
 *
 */
class ZCodeDeviceDetailActivity : BaseAcvitiy() {

    var zcodeActionResponse:ZCodeActionResponse?=null

    override fun initView() {
         isRightShow=false
         isTitleShow=true
         isBlackShow = true
         viewtitle="设备详情"
         tv_title.setTextColor(resources.getColor(R.color.fen_se))

        //详情的反馈 实体
        zcodeActionResponse = intent.getSerializableExtra("zcodeAction") as ZCodeActionResponse
        if(zcodeActionResponse==null){
            finish()
            return
        }
    }

    /**
     * 显示 数据信息
     */
    override fun processBussiness() {
        //设备名称
        tv_device_name.setText(zcodeActionResponse!!.title)
        tv_device_number.setText(zcodeActionResponse!!.num)
        tv_device_type.setText(zcodeActionResponse!!.model)
        tv_device_by_people.setText(zcodeActionResponse!!.usename)
        tv_device_department.setText(zcodeActionResponse!!.shuname)
        tv_device_pan_by_people.setText(zcodeActionResponse!!.panname)
        tv_device_pan_time.setText(zcodeActionResponse!!.pantime)
        tv_device_pan_state.setText(zcodeActionResponse!!.ispandian)
        tv_project_group_value.setText(zcodeActionResponse!!.projectgroup)
        tv_project_num_value.setText(zcodeActionResponse!!.projectnum)
    }

    override fun getView(): View {
        return View.inflate(this@ZCodeDeviceDetailActivity, R.layout.activity_zcode_detail,null)
    }
}