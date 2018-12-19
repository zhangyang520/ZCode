package com.jianhua.zcode.assets.presenter

import com.jianhua.zcode.assets.baselibrary.Rx.BaseSucriber
import com.jianhua.zcode.assets.baselibrary.Rx.assertMethod
import com.jianhua.zcode.assets.baselibrary.Rx.execute
import com.jianhua.zcode.assets.baselibrary.data.bean.RefreshAction
import com.jianhua.zcode.assets.baselibrary.exception.ContentException
import com.jianhua.zcode.assets.baselibrary.presenter.BasePresenter
import com.jianhua.zcode.assets.baselibrary.utils.NetWorkUtils
import com.jianhua.zcode.assets.data.bean.AssetsBean
import com.jianhua.zcode.assets.data.bean.DepartmentBean
import com.jianhua.zcode.assets.data.bean.ZCoderRecorder
import com.jianhua.zcode.assets.data.common.AppConstants
import com.jianhua.zcode.assets.data.request.*
import com.jianhua.zcode.assets.data.response.AssetProjectResponse
import com.jianhua.zcode.assets.data.response.PanTotalResponse
import com.jianhua.zcode.assets.data.response.ZCodeActionResponse
import com.jianhua.zcode.assets.data.response.ZCodeRecorderListResponse
import com.jianhua.zcode.assets.presenter.view.AssetView
import com.jianhua.zcode.assets.service.impl.AssetServiceImpl
import com.jianhua.zcode.assets.ui.fragment.ProjectAssetsFragment
import com.jianhua.zcode.assets.ui.fragment.ZCodeRecordListFragment
import rx.Observable
import javax.inject.Inject

/**
 *  资产业务封装
 *  @Title ${name}
 *  @ProjectName ZCodeAssets
 *  @Description: TODO
 *  @author Administrator
 *  @date 2018/12/1520:07
 */
class AssetPresenter @Inject constructor():BasePresenter<AssetView>(){

    @Inject
    lateinit var assetServiceImpl: AssetServiceImpl


    /**
     * 获取 盘点 的统计信息
     */
    fun getPanTongji(totalRequest: PanTotalRequest){
        assetServiceImpl.getPanTongji(totalRequest)
                .execute(object:BaseSucriber<PanTotalResponse>(baseView,AssetPresenter::class.java.simpleName){

                    /**
                     * 开始
                     */
                    override fun onStart() {

                    }

                    /**
                     * 错误的回调
                     */
                    override fun onError(e: Throwable?) {
                        assertMethod(baseView,{
                            if(e is ContentException){
                                assertMethod(baseView,{
                                    baseView.hideLoading()
                                    (baseView as AssetView).onError(e.errorContent)
                                })
                            }else{
                                assertMethod(baseView,{
                                    baseView.hideLoading()
                                    if(NetWorkUtils.isNetworkConnected(context)){
                                        (baseView as AssetView).onError(AppConstants.SERVER_ERROR)
                                    }else{
                                        (baseView as AssetView).onError(AppConstants.NO_NET_WORK)
                                    }
                                })
                            }
                        })
                    }

                    /**
                     * 成功的结果返回
                     */
                    override fun onNext(t:PanTotalResponse) {
                        super.onNext(t)
                        assertMethod(baseView,{
                            (baseView as AssetView).onGetPanTongji(t)
                        })
                    }
                },lifecylerProvider)
    }
    /**
     * 获取 所有的部门列表信息
     */
    fun getDepartmentList(allDepartmentRequest: AllDepartmentRequest){
        assetServiceImpl.getDepartmentList(allDepartmentRequest)
                .execute(object:BaseSucriber<ArrayList<DepartmentBean>>(baseView,AssetPresenter::class.java.simpleName){

                    /**
                     * 开始
                     */
                    override fun onStart() {

                    }

                    /**
                     * 错误的回调
                     */
                    override fun onError(e: Throwable?) {
                        assertMethod(baseView,{
                            if(e is ContentException){
                                assertMethod(baseView,{
                                    baseView.hideLoading()
                                    (baseView as AssetView).onError(e.errorContent)
                                })
                            }else{
                                assertMethod(baseView,{
                                    baseView.hideLoading()
                                    if(NetWorkUtils.isNetworkConnected(context)){
                                        (baseView as AssetView).onError(AppConstants.SERVER_ERROR)
                                    }else{
                                        (baseView as AssetView).onError(AppConstants.NO_NET_WORK)
                                    }
                                })
                            }
                        })
                    }

                    /**
                     * 成功的结果返回
                     */
                    override fun onNext(t: ArrayList<DepartmentBean>) {
                        super.onNext(t)
                        assertMethod(baseView,{
                            (baseView as AssetView).onGetDepartmentList(t)
                        })
                    }
                },lifecylerProvider)
    }
    /**
     * 获取资产 列表接口
     */
    fun getAssetList( assetsPorjectRequest: AssetsPorjectRequest,action: RefreshAction,listener: ProjectAssetsFragment.RequestListener){
        assetServiceImpl.getAssetList(assetsPorjectRequest)
                .execute(object:BaseSucriber<AssetProjectResponse>(baseView,AssetPresenter::class.java.simpleName){

                    /**
                     * 开始
                     */
                    override fun onStart() {

                    }

                    /**
                     * 错误的回调
                     */
                    override fun onError(e: Throwable?) {
                       assertMethod(baseView,{
                           if(e is ContentException){
                               assertMethod(baseView,{
                                   baseView.hideLoading()
                                   (baseView as AssetView).onErrorAction(action,e.errorContent,listener)
                               })
                           }else{
                               assertMethod(baseView,{
                                   baseView.hideLoading()
                                   if(NetWorkUtils.isNetworkConnected(context)){
                                       (baseView as AssetView).onErrorAction(action,AppConstants.SERVER_ERROR,listener)
                                   }else{
                                       (baseView as AssetView).onErrorAction(action,AppConstants.NO_NET_WORK,listener)
                                   }
                               })
                           }
                       })
                    }

                    /**
                     * 成功的结果返回
                     */
                    override fun onNext(t: AssetProjectResponse) {
                        super.onNext(t)
                        assertMethod(baseView,{
                            (baseView as AssetView).onGetProjectAssetList(t.list!!,action,listener)
                        })
                    }
        },lifecylerProvider)
    }

    /**
     * 获取扫码记录接口
     */
    fun getZcodeRecoderList(zCodeRecorderListRequest: ZCodeRecorderListRequest,action:RefreshAction,listener: ZCodeRecordListFragment.RequestListener){
        assetServiceImpl.getZcodeRecoderList(zCodeRecorderListRequest)
                .execute(object:BaseSucriber<ZCodeRecorderListResponse>(baseView,AssetPresenter::class.java.simpleName){

                    /**
                     * 开始
                     */
                    override fun onStart() {

                    }

                    /**
                     * 错误的回调
                     */
                    override fun onError(e: Throwable?) {
                        assertMethod(baseView,{
                            if(e is ContentException){
                                assertMethod(baseView,{
                                    baseView.hideLoading()
                                    (baseView as AssetView).onErrorAction(action,e.errorContent,listener)
                                })
                            }else{
                                assertMethod(baseView,{
                                    baseView.hideLoading()
                                    if(NetWorkUtils.isNetworkConnected(context)){
                                        (baseView as AssetView).onErrorAction(action,AppConstants.SERVER_ERROR,listener)
                                    }else{
                                        (baseView as AssetView).onErrorAction(action,AppConstants.NO_NET_WORK,listener)
                                    }
                                })
                            }
                        })
                    }

                    /**
                     * 成功的结果返回
                     */
                    override fun onNext(t: ZCodeRecorderListResponse) {
                        super.onNext(t)
                        assertMethod(baseView,{
                            (baseView as AssetView).onGetZCodeRecoderList(t.list!!,action,listener)
                        })
                    }
                },lifecylerProvider)
    }


    /**
     * 扫码操作的接口
     */
    fun zcodeAction(zCoderActionRequest: ZCoderActionRequest){
        assetServiceImpl.zcodeAction(zCoderActionRequest)
                .execute(object:BaseSucriber<ZCodeActionResponse>(baseView,AssetPresenter::class.java.simpleName){

                    /**
                     * 开始
                     */
                    override fun onStart() {

                    }

                    /**
                     * 错误的回调
                     */
                    override fun onError(e: Throwable?) {
                        assertMethod(baseView,{
                            if(e is ContentException){
                                assertMethod(baseView,{
                                    baseView.hideLoading()
                                    (baseView as AssetView).onError(e.errorContent)
                                })
                            }else{
                                assertMethod(baseView,{
                                    baseView.hideLoading()
                                    if(NetWorkUtils.isNetworkConnected(context)){
                                        (baseView as AssetView).onError(AppConstants.SERVER_ERROR)
                                    }else{
                                        (baseView as AssetView).onError(AppConstants.NO_NET_WORK)
                                    }
                                })
                            }
                        })
                    }

                    /**
                     * 成功的结果返回
                     */
                    override fun onNext(t: ZCodeActionResponse) {
                        super.onNext(t)
                        assertMethod(baseView,{
                            (baseView as AssetView).onZCodeAction(t)
                        })
                    }
                },lifecylerProvider)
    }
}