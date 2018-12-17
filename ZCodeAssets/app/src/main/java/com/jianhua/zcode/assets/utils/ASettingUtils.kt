package com.jianhua.zcode.assets.utils

import com.jianhua.zcode.assets.data.common.AppConstants
import com.suntray.chinapost.baselibrary.ut.base.utils.AppPrefsUtils
import java.util.*

/**
 *  设置 弹出框相关设置
 *  @Author 张扬 @version 1.0
 *  @Date 2018/10/16 11:13
 */
object ASettingUtils {

    /**
     * 清空设置条件
     */
    fun clearSetting(){
        AppPrefsUtils.putString(AppConstants.Inventory_Date_Name,"")
        AppPrefsUtils.putString(AppConstants.USER_NUM,"")
    }

    /**
     *  初始化数据
     */
    fun initSetting(){
//        AppPrefsUtils.putString(AppConstants.Inventory_Date_Name,"")
    }
}