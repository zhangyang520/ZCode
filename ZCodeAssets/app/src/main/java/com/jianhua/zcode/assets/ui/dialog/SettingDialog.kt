package com.jianhua.zcode.assets.ui.dialog

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import com.jianhua.zcode.assets.R
import com.jianhua.zcode.assets.baselibrary.Rx.getTxt
import com.jianhua.zcode.assets.baselibrary.common.AppManager
import com.jianhua.zcode.assets.baselibrary.exception.ContentException
import com.jianhua.zcode.assets.baselibrary.utils.DateUtil
import com.jianhua.zcode.assets.data.bean.AssetListType
import com.jianhua.zcode.assets.data.bean.AssetSearchCondition
import com.jianhua.zcode.assets.data.common.AppConstants
import com.jianhua.zcode.assets.data.dao.AssetSearchConditionDao
import com.jianhua.zcode.assets.data.dao.AssetUserDao
import com.jianhua.zcode.assets.ui.activity.LoginActivity
import com.jianhua.zcode.assets.ui.activity.MainActivity
import com.suntray.chinapost.baselibrary.ut.base.utils.AppPrefsUtils
import com.zhy.autolayout.AutoLinearLayout
import com.zhy.autolayout.AutoRelativeLayout
import com.zhy.autolayout.utils.AutoUtils
import kotlinx.android.synthetic.main.popup_setting.*
import java.util.*

/**
 *   设置的对话弹框
 *  @Author 张扬 @version 1.0
 *  @Date 2018/9/16 10:05
 */
class SettingDialog:Dialog{

    var context: MainActivity?=null
    constructor(context: MainActivity?) : super(context){
        this.context=context
    }

    constructor(context: MainActivity?, themeResId: Int) : super(context, themeResId){
        this.context=context
    }

    constructor(context: Context?, cancelable: Boolean,
                    cancelListener: DialogInterface.OnCancelListener?) : super(context, cancelable, cancelListener)

    var btn_ok:Button?=null
    var btn_cance: Button? = null
    var btn_exit:Button?=null
    //当前的位置
    var currentPosition=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var view=View.inflate(context, R.layout.popup_setting,null);
        AutoUtils.autoSize(view);
        setContentView(view)

        btn_ok=findViewById<Button>(R.id.btn_ok)
        btn_cance=findViewById<Button>(R.id.btn_cance)
        btn_exit=findViewById<Button>(R.id.btn_exit)
        //设置 对应的编辑器中的日周期 ed_daily
        var inventoryDate=AppPrefsUtils.getString(AppConstants.Inventory_Date_Name,"")
        if(inventoryDate.equals("")){
             //首次添加 设置 为当前月份
            inventoryDate=DateUtil.dateFormatMonth(Calendar.getInstance().time)
            AppPrefsUtils.putString(AppConstants.Inventory_Date_Name,inventoryDate)
            ed_daily.setText(inventoryDate)
        }else{
            //如果有 进行展示
            ed_daily.setText(inventoryDate)
        }


        //上个月份
        iv_last_month.setOnClickListener({

            //缓存月份值
            var calendar=Calendar.getInstance()
            var currentDate=calendar.time
            var cacheMonthDate = DateUtil.parse2DateMonth(AppPrefsUtils.getString(
                    AppConstants.Inventory_Date_Name, DateUtil.dateFormatMonth(Calendar.getInstance().time)))
            calendar.time=cacheMonthDate
            //缓存月份 值
            var cacheMonthNum=calendar.get(Calendar.MONTH)
            calendar.time=currentDate
            var currentMonthNum=calendar.get(Calendar.MONTH)
            //todo  其中的calendar 日期实例 month 从0 开始。-1 返回到上一年的当前月份 值
            //只有
            if(currentMonthNum==cacheMonthNum){
                //此时可以上一个 月份
                calendar.set(Calendar.MONTH,currentMonthNum-1)
                ed_daily.setText( DateUtil.dateFormatMonth(calendar.time))
            }else{
                //下个月份: 只能下个月份

            }
        })
        btn_ok!!.setOnClickListener({
            //进行保存 信息 根据对话框中的 内容 进行保存
            if(currentPosition==0){
                //当前 的位置 是项目资产 的列表
                try {
                    var assetSearchCondition=AssetSearchConditionDao.getByType(AssetListType.AssetListAll.listType.toInt())
                    assetSearchCondition.assetType=AssetListType.AssetListAll.listType.toInt()
                    assetSearchCondition.endTime
                    assetSearchCondition.startTime
                    assetSearchCondition.keyword
                    assetSearchCondition.shunname
                    assetSearchCondition.pandate
                } catch (e: ContentException) {
                    //没有对应的信息
                    var assetSearchCondition=AssetSearchCondition()
                    assetSearchCondition.assetType=AssetListType.AssetListAll.listType.toInt()
                    assetSearchCondition.endTime
                    assetSearchCondition.startTime
                    assetSearchCondition.keyword
                    assetSearchCondition.shunname
                    assetSearchCondition.pandate
                }
            }else{
                //扫码记录的列表
            }

            AppPrefsUtils.putString(AppConstants.Inventory_Date_Name,ed_daily.getTxt().toString())
            dismiss()
        })

        btn_cance!!.setOnClickListener({
            dismiss()
        })
        setCancelable(true)

        btn_exit!!.setOnClickListener({
            dismiss()
            AssetUserDao.updateAllUserLocalState(false)
            AppManager.instance.finishAll()
            var intent = Intent(context, LoginActivity::class.java)
            context!!.startActivity(intent)
        })
    }
}