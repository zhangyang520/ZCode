package com.jianhua.zcode.assets.data.bean

import com.lidroid.xutils.db.annotation.Id
import com.lidroid.xutils.db.annotation.NoAutoIncrement
import com.lidroid.xutils.db.annotation.Table

/**
 *
 *  资产实体的 搜索 条件的封装
 *  @Title ${name}
 *  @ProjectName ZCodeAssets
 *  @Description: TODO
 *  @author Administrator
 *  @date 2018/12/1812:38
 *
 */
@Table
class AssetSearchCondition {
    //资产类型
    @Id
    @NoAutoIncrement
    var assetType: Int = 0  // 0 资产列表  1:扫码记录
    var pandate = "" //盘点月份
    var startTime = "" //盘点开始时间
    var endTime = "" //盘点结束时间
    var shunname = "" //部门
    var keyword = "" //关键字

    constructor()
    constructor(assetType: Int, pandate: String, startTime: String, endTime: String, shunname: String, keyword: String) {
        this.assetType = assetType
        this.pandate = pandate
        this.startTime = startTime
        this.endTime = endTime
        this.shunname = shunname
        this.keyword = keyword
    }

    override fun toString(): String {
        return "AssetSearchCondition(assetType=$assetType, pandate='$pandate', startTime='$startTime', endTime='$endTime', shunname='$shunname', keyword='$keyword')"
    }
}