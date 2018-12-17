package com.jianhua.zcode.assets.data.request

/**
 *  扫码 操作的请求封装实体
 *
 *  num:编号
 *  pandate:盘点日期
 *  panname:盘点人 姓名
 *  @Title ${name}
 *  @ProjectName ZCodeAssets
 *  @Description: TODO
 *  @author Administrator
 *  @date 2018/12/1519:50
 *
 */
data class ZCoderActionRequest(var id:Int=0,var num:String="",
                                   var pandate:String="",var panname:String="")