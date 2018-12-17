package com.jianhua.zcode.assets.presenter

import com.jianhua.zcode.assets.baselibrary.Rx.BaseSucriber
import com.jianhua.zcode.assets.baselibrary.Rx.assertMethod
import com.jianhua.zcode.assets.baselibrary.Rx.execute
import com.jianhua.zcode.assets.baselibrary.data.bean.RefreshAction
import com.jianhua.zcode.assets.baselibrary.exception.ContentException
import com.jianhua.zcode.assets.baselibrary.presenter.BasePresenter
import com.jianhua.zcode.assets.data.bean.AssetsBean
import com.jianhua.zcode.assets.data.bean.ZCoderRecorder
import com.jianhua.zcode.assets.data.request.AssetsPorjectRequest
import com.jianhua.zcode.assets.data.request.ZCodeRecorderListRequest
import com.jianhua.zcode.assets.data.request.ZCoderActionRequest
import com.jianhua.zcode.assets.data.response.AssetProjectResponse
import com.jianhua.zcode.assets.data.response.ZCodeActionResponse
import com.jianhua.zcode.assets.data.response.ZCodeRecorderListResponse
import com.jianhua.zcode.assets.presenter.view.AssetView
import com.jianhua.zcode.assets.service.impl.AssetServiceImpl
import com.jianhua.zcode.assets.ui.fragment.ProjectAssetsFragment
import com.jianhua.zcode.assets.ui.fragment.ZCodeRecordListFragment
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
                                   (baseView as AssetView).onErrorAction(action,"接口请求失败!",listener)
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
                                    (baseView as AssetView).onErrorAction(action,"接口请求失败!",listener)
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
                                    (baseView as AssetView).onError("接口请求失败!")
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