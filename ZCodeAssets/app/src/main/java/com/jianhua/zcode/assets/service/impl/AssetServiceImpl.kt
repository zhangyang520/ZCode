package com.jianhua.zcode.assets.service.impl

import com.jianhua.zcode.assets.baselibrary.Rx.convertData
import com.jianhua.zcode.assets.data.bean.AssetsBean
import com.jianhua.zcode.assets.data.bean.ZCoderRecorder
import com.jianhua.zcode.assets.data.request.AssetsPorjectRequest
import com.jianhua.zcode.assets.data.request.ZCodeRecorderListRequest
import com.jianhua.zcode.assets.data.request.ZCoderActionRequest
import com.jianhua.zcode.assets.data.response.ZCodeActionResponse
import com.jianhua.zcode.assets.data.respository.AssetsRespository
import com.jianhua.zcode.assets.data.respository.UserRespository
import com.jianhua.zcode.assets.service.AssetService
import rx.Observable
import javax.inject.Inject

/**
 *  资产服务实现类
 *  @Title ${name}
 *  @ProjectName ZCodeAssets
 *  @Description: TODO
 *  @author Administrator
 *  @date 2018/12/1519:57
 *
 */
class AssetServiceImpl @Inject constructor() : AssetService {

    @Inject
    lateinit var assetRespository: AssetsRespository

    override fun getAssetList(assetsPorjectRequest: AssetsPorjectRequest): Observable<ArrayList<AssetsBean>> {
        return assetRespository.getAssetList(assetsPorjectRequest).convertData()
    }

    override fun getZcodeRecoderList(zCodeRecorderListRequest: ZCodeRecorderListRequest): Observable<ArrayList<ZCoderRecorder>> {
        return assetRespository.getZcodeRecoderList(zCodeRecorderListRequest).convertData()
    }

    override fun zcodeAction(zCoderActionRequest: ZCoderActionRequest): Observable<ZCodeActionResponse> {
        return assetRespository.zcodeAction(zCoderActionRequest).convertData()
    }
}