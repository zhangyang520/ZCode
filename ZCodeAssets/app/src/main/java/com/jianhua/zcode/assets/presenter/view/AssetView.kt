package com.jianhua.zcode.assets.presenter.view

import com.jianhua.zcode.assets.baselibrary.data.bean.RefreshAction
import com.jianhua.zcode.assets.baselibrary.presenter.view.BaseView
import com.jianhua.zcode.assets.data.bean.AssetsBean
import com.jianhua.zcode.assets.data.bean.ZCoderRecorder
import com.jianhua.zcode.assets.data.response.ZCodeActionResponse
import com.jianhua.zcode.assets.ui.fragment.ProjectAssetsFragment
import com.jianhua.zcode.assets.ui.fragment.ZCodeRecordListFragment

/**
 *  资产相关业务的view
 *  @Title ${name}
 *  @ProjectName ZCodeAssets
 *  @Description: TODO
 *  @author Administrator
 *  @date 2018/12/1520:02
 *
 */
interface AssetView:BaseView{

    /**
     *  获取资产列表的响应
     */
    fun  onGetProjectAssetList(projectAssets: ArrayList<AssetsBean>, refreshAction: RefreshAction, listener: ProjectAssetsFragment.RequestListener){

    }

    /**
     * 获取 扫码记录列表的响应
     */
    fun  onGetZCodeRecoderList(zCoderRecorder: ArrayList<ZCoderRecorder>,refreshAction: RefreshAction, listener: ZCodeRecordListFragment.RequestListener){

    }

    /**
     * 扫码操作的响应
     */
    fun  onZCodeAction(zCodeActionResponse: ZCodeActionResponse){

    }

    /**
     * 刷新相关 失败的请求
     */
    fun onErrorAction(action: RefreshAction, errorContent: String, listener: ProjectAssetsFragment.RequestListener){

    }

    /**
     * 刷新相关 失败的请求
     */
    fun onErrorAction(action: RefreshAction, errorContent: String, listener: ZCodeRecordListFragment.RequestListener){

    }
}