package com.jianhua.zcode.assets.data.bean

import com.jianhua.zcode.assets.baselibrary.ui.recylerviewRefrsehLayout.model.CursorModel
import java.io.Serializable

/**
 *  扫码记录的实体
 *  @Title ${name}
 *  @ProjectName ZCodeAssets
 *  @Description: TODO
 *  @author Administrator
 *  @date 2018/12/1519:30
 *
 */
class ZCoderRecorder: CursorModel,Serializable {
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
    var projectnum="" //项目编码


    constructor()

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

    constructor(title: String, num: String, model: String, projectgroup: String, useName: String, pantime: String, panname: String, shuname: String, ispandian: String, panstatus: String, projectnum: String) {
        this.title = title
        this.num = num
        this.model = model
        this.projectgroup = projectgroup
        this.useName = useName
        this.pantime = pantime
        this.panname = panname
        this.shuname = shuname
        this.ispandian = ispandian
        this.panstatus = panstatus
        this.projectnum = projectnum
    }


    override fun hasMore(): Boolean {
        return true
    }

    override fun toString(): String {
        return "ZCoderRecorder(title='$title', num='$num', model='$model', projectgroup='$projectgroup', useName='$useName', pantime='$pantime', panname='$panname', shuname='$shuname', ispandian='$ispandian', panstatus='$panstatus')"
    }
}