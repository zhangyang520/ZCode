package com.jianhua.zcode.assets.data.response

import java.io.Serializable

/**
 *  扫码 执行操作的封装实体
 *  @Title ${name}
 *  @ProjectName ZCodeAssets
 *  @Description: TODO
 *  @author Administrator
 *  @date 2018/12/1519:51
 *
 */
class ZCodeActionResponse:Serializable {
    var title="" //物品名称
    var num="" //物品编号
    var model="" //物品型号
    var username="" //使用人
    var useName="" //使用人
    var pantime="" //盘点时间
    var panname="" //盘点人
    var shuname="" //所属部门
    var ispandian="" //物品是否盘点
    var panstatus="" //盘点状态
    var projectnum="" //项目编码

    constructor()
    constructor(title: String, num: String, model: String, username: String, useName: String, pantime: String, panname: String, shuname: String, ispandian: String, panstatus: String) {
        this.title = title
        this.num = num
        this.model = model
        this.username = username
        this.useName = useName
        this.pantime = pantime
        this.panname = panname
        this.shuname = shuname
        this.ispandian = ispandian
        this.panstatus = panstatus
    }

    constructor(title: String, num: String, model: String, username: String, useName: String, pantime: String, panname: String, shuname: String, ispandian: String, panstatus: String, projectnum: String) {
        this.title = title
        this.num = num
        this.model = model
        this.username = username
        this.useName = useName
        this.pantime = pantime
        this.panname = panname
        this.shuname = shuname
        this.ispandian = ispandian
        this.panstatus = panstatus
        this.projectnum = projectnum
    }


    override fun toString(): String {
        return "ZCodeActionResponse(title='$title', num='$num', model='$model', username='$username', useName='$useName', pantime='$pantime', panname='$panname', shuname='$shuname', ispandian='$ispandian', panstatus='$panstatus')"
    }
}