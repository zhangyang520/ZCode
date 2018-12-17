package com.jianhua.zcode.assets.data.request

 /**
     * @Title:
     * @ProjectName
  *    @parm panstatus:盘点状态
  *          pandate: 盘点日期
  *          startTime：开始时期
  *          endTime：结束日期
  *          shuname：部门
  *          keyword：关键字搜索
     * @Description: TODO
     * @author zhangyang
     * @date
     */
data class AssetsPorjectRequest(var id:Int=0,var pageNum:Int,var pageSize:Int,
                                  var panstatus:String="1",var pandate:String="",
                                  var startTime:String="",var endTime:String="",
                                  var shuname:String="",var keyword:String="")