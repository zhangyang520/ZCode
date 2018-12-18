package com.jianhua.zcode.assets.data.dao

import com.jianhua.zcode.assets.baselibrary.common.BaseApplication
import com.jianhua.zcode.assets.baselibrary.exception.ContentException
import com.jianhua.zcode.assets.data.bean.AssetsBean
import com.lidroid.xutils.db.sqlite.Selector
import com.lidroid.xutils.db.sqlite.WhereBuilder
import com.lidroid.xutils.exception.DbException

/**
 *  项目资产列表的实体 dao 封装
 *  @Title ${name}
 *  @ProjectName ZCodeAssets
 *  @Description: TODO
 *  @author Administrator
 *  @date 2018/12/1812:25
 *
 */
object AssetBeanDao {

    /**
     * 进行获取所有的数据
     */
    @Throws(ContentException::class)
    fun getAll(): List<AssetsBean> {
        var listData=BaseApplication.dbUtils.findAll<AssetsBean>(Selector.from(AssetsBean::class.java))
        if(listData!=null && listData.size>0){
            return listData
        }else{
            throw ContentException("暂无数据")
        }
    }

    /**
     * \进行清空所有的数据
     */
    fun clearAll() {
        try {
           BaseApplication.dbUtils.deleteAll(AssetsBean::class.java)
        } catch (e: DbException) {
            e.printStackTrace()
        }
    }

    /**
     * 增加对应的数据
     */
    fun addData(list: List<AssetsBean>) {
        try {
             BaseApplication.dbUtils.saveOrUpdateAll(list)
        } catch (e: DbException) {
            e.printStackTrace()
        }
    }
}