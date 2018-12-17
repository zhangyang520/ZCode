package com.jianhua.zcode.assets.data.common

/**
 *  @Title ${name}
 *  @ProjectName ZCodeAssets
 *  @Description: TODO
 *  @author Administrator
 *  @date 2018/12/1516:06
 *
 */
object AppConstants {

    //登录操作
    const  val loginDo="api/user/login"
    //资产列表接口
    const val assetList = "api/goods/selectPandianList"
    //扫码记录接口
    const  val recorderList="api/goods/selectPandianList"
    //扫描二维码 接口
    const  val zcodeAction="api/goods/updatePandianStatus"

    //所有部门接口
    const  val departmentList="api/goods/allDept"
    // 盘点日期的键
    const val Inventory_Date_Name="Inventory_Date"

    //用户的编号
    const val USER_NUM="USER_NUM"
}