package com.jianhua.zcode.assets.service.impl

import com.jianhua.zcode.assets.baselibrary.Rx.convertData
import com.jianhua.zcode.assets.data.bean.AssetsBean
import com.jianhua.zcode.assets.data.bean.DepartmentBean
import com.jianhua.zcode.assets.data.bean.ZCoderRecorder
import com.jianhua.zcode.assets.data.request.*
import com.jianhua.zcode.assets.data.response.AssetProjectResponse
import com.jianhua.zcode.assets.data.response.PanTotalResponse
import com.jianhua.zcode.assets.data.response.ZCodeActionResponse
import com.jianhua.zcode.assets.data.response.ZCodeRecorderListResponse
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

    override fun getAssetList(assetsPorjectRequest: AssetsPorjectRequest): Observable<AssetProjectResponse> {
        return assetRespository.getAssetList(assetsPorjectRequest).convertData()
    }

    override fun getZcodeRecoderList(zCodeRecorderListRequest: ZCodeRecorderListRequest): Observable<ZCodeRecorderListResponse> {
        return assetRespository.getZcodeRecoderList(zCodeRecorderListRequest).convertData()
    }

    override fun zcodeAction(zCoderActionRequest: ZCoderActionRequest): Observable<ZCodeActionResponse> {
        return assetRespository.zcodeAction(zCoderActionRequest).convertData()
    }

    override fun getDepartmentList(allDepartmentRequest: AllDepartmentRequest): Observable<ArrayList<DepartmentBean>> {
        return assetRespository.getDepartmentList(allDepartmentRequest).convertData()
    }

    override fun getPanTongji(totalRequest: PanTotalRequest): Observable<PanTotalResponse> {
        return assetRespository.getPanTongji(totalRequest).convertData()
    }
}