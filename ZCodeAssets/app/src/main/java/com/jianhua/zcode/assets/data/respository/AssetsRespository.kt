package com.jianhua.zcode.assets.data.respository

import com.jianhua.zcode.assets.baselibrary.data.bean.BaseResp
import com.jianhua.zcode.assets.baselibrary.data.net.RetrofitFactory
import com.jianhua.zcode.assets.data.api.AssetsApi
import com.jianhua.zcode.assets.data.bean.AssetsBean
import com.jianhua.zcode.assets.data.bean.DepartmentBean
import com.jianhua.zcode.assets.data.bean.ZCoderRecorder
import com.jianhua.zcode.assets.data.request.*
import com.jianhua.zcode.assets.data.response.AssetProjectResponse
import com.jianhua.zcode.assets.data.response.PanTotalResponse
import com.jianhua.zcode.assets.data.response.ZCodeActionResponse
import com.jianhua.zcode.assets.data.response.ZCodeRecorderListResponse
import retrofit2.http.Body
import rx.Observable
import javax.inject.Inject

/**
 *  扫码的网络仓库
 *  @Title ${name}
 *  @ProjectName ZCodeAssets
 *  @Description: TODO
 *  @author Administrator
 *  @date 2018/12/1519:54
 *
 */
class AssetsRespository @Inject constructor(){

    /**
     * 获取资产 列表接口
     */
    fun getAssetList(assetsPorjectRequest: AssetsPorjectRequest): Observable<BaseResp<AssetProjectResponse>>{
        return RetrofitFactory.instance.create(AssetsApi::class.java).getAssetList(assetsPorjectRequest)
    }

    /**
     * 获取扫码记录接口
     */
    fun getZcodeRecoderList(zCodeRecorderListRequest: ZCodeRecorderListRequest): Observable<BaseResp<ZCodeRecorderListResponse>>{
        return RetrofitFactory.instance.create(AssetsApi::class.java).getZcodeRecoderList(zCodeRecorderListRequest)
    }


    /**
     * 扫码操作的接口
     */
    fun zcodeAction(zCoderActionRequest: ZCoderActionRequest): Observable<BaseResp<ZCodeActionResponse>>{
        return RetrofitFactory.instance.create(AssetsApi::class.java).zcodeAction(zCoderActionRequest)
    }


    /**
     * 获取 所有的部门列表信息
     */
    fun getDepartmentList(allDepartmentRequest: AllDepartmentRequest):Observable<BaseResp<ArrayList<DepartmentBean>>>{
        return RetrofitFactory.instance.create(AssetsApi::class.java).getDepartmentList(allDepartmentRequest)
    }

    /**
     * 获取盘点的统计 信息
     */
    fun getPanTongji( totalRequest: PanTotalRequest):Observable<BaseResp<PanTotalResponse>>{
        return RetrofitFactory.instance.create(AssetsApi::class.java).getPanTongji(totalRequest)
    }
}