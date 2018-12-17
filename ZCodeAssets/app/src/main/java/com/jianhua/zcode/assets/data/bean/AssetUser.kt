package com.jianhua.zcode.assets.data.bean

import com.lidroid.xutils.db.annotation.Id
import com.lidroid.xutils.db.annotation.NoAutoIncrement
import com.lidroid.xutils.db.annotation.Table
import java.io.Serializable

/**
 *  资产用户实体
 *  @Title ${name}
 *  @ProjectName ZCodeAssets
 *  @Description: TODO
 *  @author Administrator
 *  @date 2018/12/1720:02
 *
 */
@Table
class AssetUser:Serializable{
    @Id
    @NoAutoIncrement
    var id:Int=-1
    var account="" //账号
    var pass=""  //密码
    var num="" //工号
    var name="" //小名
    var deptname="" //部门
    var ranking="" //职位
    var isLocalUser=false

    constructor()
    constructor(id: Int, account: String, pass: String, num: String, name: String, deptname: String, ranking: String) {
        this.id = id
        this.account = account
        this.pass = pass
        this.num = num
        this.name = name
        this.deptname = deptname
        this.ranking = ranking
    }

    override fun toString(): String {
        return "AssetUser(id=$id, account='$account', pass='$pass', num='$num', name='$name', deptname='$deptname', ranking='$ranking')"
    }
}