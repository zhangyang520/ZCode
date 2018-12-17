package com.jianhua.zcode.assets.injection.component

import com.jianhua.zcode.assets.baselibrary.injection.component.ActivityComponent
import com.jianhua.zcode.assets.baselibrary.injection.module.PerComponentScope
import com.jianhua.zcode.assets.injection.module.AssetModule
import com.jianhua.zcode.assets.ui.fragment.ProjectAssetsFragment
import com.jianhua.zcode.assets.ui.fragment.ZCodeRecordListFragment
import dagger.Component

/**
 *  资产相关的component
 *  @Title ${name}
 *  @ProjectName ZCodeAssets
 *  @Description: TODO
 *  @author Administrator
 *  @date 2018/12/1520:46
 *
 */
@PerComponentScope
@Component(modules = arrayOf(AssetModule::class),dependencies = arrayOf(ActivityComponent::class))
interface AssetComponent {
    /**
     * 绑定项目资产的 fragment
     */
    fun bind(projectAssetsFragment: ProjectAssetsFragment)

    /**
     * 绑定 扫码记录的fragment
     */
    fun bind(zCodeRecordListFragment: ZCodeRecordListFragment)
}