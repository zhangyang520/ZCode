package com.jianhua.zcode.assets.injection.module

import com.jianhua.zcode.assets.baselibrary.injection.module.PerComponentScope
import com.jianhua.zcode.assets.service.AssetService
import com.jianhua.zcode.assets.service.impl.AssetServiceImpl
import dagger.Module
import dagger.Provides

/**
 *  资产相关模型类
 *  @Title ${name}
 *  @ProjectName ZCodeAssets
 *  @Description: TODO
 *  @author Administrator
 *  @date 2018/12/1520:44
 *
 */
@Module
@PerComponentScope
class AssetModule {

    @Provides
    fun provideAssetServiceImpl(assetServiceImpl: AssetServiceImpl): AssetService {
        return assetServiceImpl
    }
}