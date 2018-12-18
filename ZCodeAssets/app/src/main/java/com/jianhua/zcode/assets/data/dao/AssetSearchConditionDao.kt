package com.jianhua.zcode.assets.data.dao

import com.jianhua.zcode.assets.baselibrary.common.BaseApplication
import com.jianhua.zcode.assets.baselibrary.exception.ContentException
import com.jianhua.zcode.assets.data.bean.AssetSearchCondition
import com.lidroid.xutils.db.sqlite.Selector
import com.lidroid.xutils.db.sqlite.WhereBuilder
import com.lidroid.xutils.exception.DbException

/**
 *   扫码条件 的dao 的封装
 *  @Title ${name}
 *  @ProjectName ZCodeAssets
 *  @Description: TODO
 *  @author Administrator
 *  @date 2018/12/1812:45
 *
 */
object AssetSearchConditionDao {

    /**
     * 通过资产类型 获取 对应的条件封装实体
     */
    @Throws(ContentException::class)
    fun getByType(assetType: Int):AssetSearchCondition{
        var dataList = BaseApplication.dbUtils.findAll<AssetSearchCondition>(Selector.from(AssetSearchCondition::class.java)
                .where(WhereBuilder.b("assetType", "=", assetType)))
        if(dataList!=null && dataList.size>0){
            return dataList.get(0)
        }else{
            throw ContentException("暂无数据")
        }
    }

    /**
     * 保存 对应的数据
     */
    fun saveData(assetSearchCondition: AssetSearchCondition) {
        try {
            BaseApplication.dbUtils.saveOrUpdate(assetSearchCondition)
        } catch (e: DbException) {
            e.printStackTrace()
        }
    }
}