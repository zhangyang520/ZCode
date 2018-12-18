package com.jianhua.zcode.assets.data.bean

import com.jianhua.zcode.assets.baselibrary.ui.recylerviewRefrsehLayout.model.CursorModel
import com.lidroid.xutils.db.annotation.Id
import java.io.Serializable

/**
 *  @Title ${name}
 *  @ProjectName ZCodeAssets
 *  @Description: TODO
 *  @author Administrator
 *  @date 2018/12/1517:39
 *
 */
class AssetsBean:Serializable, CursorModel {

    @Id
    var id:Int=0
    var title="" //物品名称
    var num="" //物品编号
    var model="" //物品型号
    var projectgroup=""//项目组
    var useName="" //使用人
    var pantime="" //盘点时间
    var panname="" //盘点人
    var shuname="" //所属部门
    var ispandian="" //是否 盘点
    var projectnum="" //项目编码

    constructor()

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

    constructor(id: Int, title: String, num: String, model: String, projectgroup: String, useName: String, pantime: String, panname: String, shuname: String, ispandian: String, projectnum: String) {
        this.id = id
        this.title = title
        this.num = num
        this.model = model
        this.projectgroup = projectgroup
        this.useName = useName
        this.pantime = pantime
        this.panname = panname
        this.shuname = shuname
        this.ispandian = ispandian
        this.projectnum = projectnum
    }


    /**
     * 是否有更多
     */
    override fun hasMore(): Boolean {
        return true
    }

    override fun toString(): String {
        return "AssetsBean(title='$title', num='$num', model='$model', projectgroup='$projectgroup', useName='$useName', pantime='$pantime', panname='$panname', shuname='$shuname', ispandian='$ispandian')"
    }
}