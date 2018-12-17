package com.jianhua.zcode.assets.data.dao

import com.jianhua.zcode.assets.baselibrary.common.BaseApplication
import com.jianhua.zcode.assets.baselibrary.exception.ContentException
import com.jianhua.zcode.assets.data.bean.AssetUser
import com.lidroid.xutils.db.sqlite.Selector
import com.lidroid.xutils.db.sqlite.WhereBuilder
import com.lidroid.xutils.exception.DbException

/**
 *
 *  @Title ${name}
 *  @ProjectName ZCodeAssets
 *  @Description: TODO
 *  @author Administrator
 *  @date 2018/12/1720:10
 *
 */
object AssetUserDao {

    /**
     * 获取本地用户
     */
    @Throws(ContentException::class)
    fun  getLocalUser():AssetUser{
        //用户对应集合
        var userList = BaseApplication.dbUtils.findAll<AssetUser>(Selector.from(AssetUser::class.java)
                .where(WhereBuilder.b("isLocalUser", "=", true)))
        if(userList!=null && userList.size>0){
            return userList.get(0)
        }else{
            throw ContentException("暂无用户!")
        }
    }

    /**
     * 进行保存用户
     */
    fun saveUser(user: AssetUser) {
          // 进行保存用户
          BaseApplication.dbUtils.saveOrUpdate(user)
    }

    /**
     * 通过 工号进行查询
     */
    @Throws(ContentException::class)
    fun getUserBy(num:String):AssetUser{
        var userList = BaseApplication.dbUtils.findAll<AssetUser>(Selector.from(AssetUser::class.java)
                .where(WhereBuilder.b("num", "=", num)))
        if(userList!=null && userList.size>0){
            return userList.get(0)
        }else{
            throw ContentException("暂无用户")
        }
    }

    @Throws(ContentException::class)
    fun getUserBy(id:Int):AssetUser{
        var userList = BaseApplication.dbUtils.findAll<AssetUser>(Selector.from(AssetUser::class.java)
                .where(WhereBuilder.b("id", "=", id)))
        if(userList!=null && userList.size>0){
            return userList.get(0)
        }else{
            throw ContentException("暂无用户")
        }
    }

    /**
     * 进行更新所有用户的本地身份状态
     * @param flag
     */
    fun updateAllUserLocalState(flag: Boolean) {
        try {
            val userList = getall()
            for (user in userList) {
                user.isLocalUser = flag
            }
            if(userList!=null && userList.size>0){
                BaseApplication.dbUtils.updateAll(userList, "isLocalUser")
            }
        } catch (e: ContentException) {
            //没有用户
            e.printStackTrace()
        } catch (e: DbException) {
            e.printStackTrace()
        }
    }

    /**
     * 查找数据
     * @param
     * @return
     */
    @Throws(ContentException::class)
    fun getall(): MutableList<AssetUser>
    {
        try {
            val datas = BaseApplication.dbUtils.findAll(AssetUser::class.java)
            if (datas == null || datas.size == 0) {
                throw ContentException("没有用户列表!")
            }
            return datas
        } catch (e: DbException) {
            e.printStackTrace()
            throw RuntimeException()
        }

    }

}