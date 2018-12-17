package com.jianhua.zcode.assets.service

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
 *  @Title ${name}
 *  @ProjectName ZCodeAssets
 *  @Description: TODO
 *  @author Administrator
 *  @date 2018/12/1519:56
 *
 */
interface AssetService {
    /**
     * 获取资产 列表接口
     */
    fun getAssetList( assetsPorjectRequest: AssetsPorjectRequest): Observable<AssetProjectResponse>

    /**
     * 获取扫码记录接口
     */
    fun getZcodeRecoderList(zCodeRecorderListRequest: ZCodeRecorderListRequest): Observable<ZCodeRecorderListResponse>


    /**
     * 扫码操作的接口
     */
    fun zcodeAction(zCoderActionRequest: ZCoderActionRequest): Observable<ZCodeActionResponse>
}