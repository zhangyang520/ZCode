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

    constructor()
    constructor(assetsName: String, assetsNumber: Int, assetsByDepartmentName: String, assetsByUserName: String, assetsModeNumber: String, checkDate: String) {
        this.assetsName = assetsName
        this.assetsNumber = assetsNumber
        this.assetsByDepartmentName = assetsByDepartmentName
        this.assetsByUserName = assetsByUserName
        this.assetsModeNumber = assetsModeNumber
        this.checkDate = checkDate
    }

    override fun toString(): String {
        return "AssetsBean(assetsName='$assetsName', assetsNumber=$assetsNumber, assetsByDepartmentName='$assetsByDepartmentName', assetsByUserName='$assetsByUserName', assetsModeNumber='$assetsModeNumber', checkDate='$checkDate')"
    }

    override fun hasMore(): Boolean {
        return true
    }
}