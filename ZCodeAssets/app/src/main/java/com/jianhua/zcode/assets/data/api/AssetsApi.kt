package com.jianhua.zcode.assets.data.api

import com.jianhua.zcode.assets.baselibrary.data.bean.BaseResp
import com.jianhua.zcode.assets.data.bean.AssetsBean
import com.jianhua.zcode.assets.data.bean.ZCoderRecorder
import com.jianhua.zcode.assets.data.common.AppConstants
import com.jianhua.zcode.assets.data.request.AssetsPorjectRequest
import com.jianhua.zcode.assets.data.request.ZCodeRecorderListRequest
import com.jianhua.zcode.assets.data.request.ZCoderActionRequest
import com.jianhua.zcode.assets.data.response.AssetProjectResponse
import com.jianhua.zcode.assets.data.response.ZCodeActionResponse
import com.jianhua.zcode.assets.data.response.ZCodeRecorderListResponse
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable

/**
 *  资产相关业务的接口
 *  @Title ${name}
 *  @ProjectName ZCodeAssets
 *  @Description: TODO
 *  @author Administrator
 *  @date 2018/12/1519:40
 *
 */
interface AssetsApi {

    /**
     * 获取资产 列表接口
     */
    @POST(value = AppConstants.assetList)
    fun getAssetList(@Body assetsPorjectRequest: AssetsPorjectRequest):Observable<BaseResp<AssetProjectResponse>>

    /**
     * 获取扫码记录接口
     */
    @POST(value = AppConstants.recorderList)
    fun getZcodeRecoderList(@Body zCodeRecorderListRequest: ZCodeRecorderListRequest):Observable<BaseResp<ZCodeRecorderListResponse>>


    /**
     * 扫码操作的接口
     */
    @POST(value = AppConstants.zcodeAction)
    fun zcodeAction(@Body zCoderActionRequest: ZCoderActionRequest):Observable<BaseResp<ZCodeActionResponse>>
}