package com.jianhua.zcode.assets.data.bean

import com.jianhua.zcode.assets.baselibrary.ui.recylerviewRefrsehLayout.model.CursorModel

/**
 *  扫码记录的实体
 *  @Title ${name}
 *  @ProjectName ZCodeAssets
 *  @Description: TODO
 *  @author Administrator
 *  @date 2018/12/1519:30
 *
 */
class ZCoderRecorder: CursorModel {
    var assetsName=""//资产名称
    var assetsNumber=1//资产 序号
    var assetsByDepartmentName=""//资产所属部门
    var assetsByUserName=""//资产使用人
    var assetsModeNumber=""//资产型号
    var checkDate="" //盘点时间


    var title="" //物品名称
    var num="" //物品编号
    var model="" //物品型号
    var projectgroup=""//项目组
    var useName="" //使用人
    var pantime="" //盘点时间
    var panname="" //盘点人
    var shuname="" //所属部门
    var ispandian="" //是否盘点
    var panstatus="" //盘点状态

    constructor()
    constructor(assetsName: String, assetsNumber: Int, assetsByDepartmentName: String, assetsByUserName: String, assetsModeNumber: String, checkDate: String) {
        this.assetsName = assetsName
        this.assetsNumber = assetsNumber
        this.assetsByDepartmentName = assetsByDepartmentName
        this.assetsByUserName = assetsByUserName
        this.assetsModeNumber = assetsModeNumber
        this.checkDate = checkDate
    }

    //构造函数
    constructor(title: String, num: String, model: String, projectgroup: String, useName: String, pantime: String, panname: String, shuname: String, ispandian: String) {
        this.title = title
        this.num = num
        this.model = model
        this.projectgroup = projectgroup
        this.useName = useName
        this.pantime = pantime
        this.panname = panname
        this.shuname = shuname
        this.ispandian = ispandian
    }


    override fun toString(): String {
        return "AssetsBean(assetsName='$assetsName', assetsNumber=$assetsNumber, assetsByDepartmentName='$assetsByDepartmentName', assetsByUserName='$assetsByUserName', assetsModeNumber='$assetsModeNumber', checkDate='$checkDate')"
    }

    override fun hasMore(): Boolean {
        return true
    }
}