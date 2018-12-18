package com.jianhua.zcode.assets.ui.popup

import android.app.DatePickerDialog
import android.content.Context
import android.graphics.Color
import android.support.v7.widget.AppCompatImageView
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.widget.DatePicker
import android.widget.EditText
import android.widget.PopupWindow
import com.jianhua.zcode.assets.R
import com.jianhua.zcode.assets.baselibrary.Rx.getTxt
import com.jianhua.zcode.assets.baselibrary.exception.ContentException
import com.jianhua.zcode.assets.baselibrary.ui.fragment.BaseMvpFragment
import com.jianhua.zcode.assets.baselibrary.ui.fragment.BaseMvpRecylerviewFragment
import com.jianhua.zcode.assets.baselibrary.utils.DateUtil
import com.jianhua.zcode.assets.data.bean.AssetListType
import com.jianhua.zcode.assets.data.bean.AssetSearchCondition
import com.jianhua.zcode.assets.data.bean.AssetsBean
import com.jianhua.zcode.assets.data.bean.DepartmentBean
import com.jianhua.zcode.assets.data.common.AppConstants
import com.jianhua.zcode.assets.data.dao.AssetSearchConditionDao
import com.jianhua.zcode.assets.presenter.AssetPresenter
import com.jianhua.zcode.assets.ui.activity.MainActivity
import com.jianhua.zcode.assets.ui.adapter.DepartmentListAdapter
import com.suntray.chinapost.baselibrary.ut.base.utils.AppPrefsUtils
import com.zhy.autolayout.AutoRelativeLayout
import com.zhy.autolayout.config.AutoLayoutConifg
import com.zhy.autolayout.utils.AutoUtils
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList

/**
 *  搜索条件 展示popupWindow
 *
 *  @Title ${name}
 *  @ProjectName ZCodeAssets
 *  @Description: TODO
 *  @author Administrator
 *  @date 2018/12/1620:24
 *
 */
object SeachConditionPopup {


    var searchConditionWindow:PopupWindow?=null
    var recyclerView:RecyclerView? =null
    var isDepartmentListAction=false// 是否 正在请求 部门列表的数据
    var currentShowDepartmentName=""// 当前 展示的ui 的部门选择的信息
    var et_department_input_search:EditText ?=null

    /**
     * 展现出搜索条件的框
     */
    fun showSearchConditionPopup(rootView: View, context: MainActivity, maskView: View,currentPosition:Int){
        //如果已经展示 返回该逻辑
        if(searchConditionWindow!=null && searchConditionWindow!!.isShowing){
            return
        }
        searchConditionWindow= PopupWindow(context,null, R.style.Transparent_Dialog);
        searchConditionWindow!!.width= AutoLayoutConifg.getInstance().screenWidth;
        searchConditionWindow!!.height= AutoUtils.getPercentHeightSize(1034);
        var contentView=View.inflate(context,R.layout.popup_search_condition,null)
        AutoUtils.autoSize(contentView)
        searchConditionWindow!!.contentView=contentView;
        searchConditionWindow!!.isOutsideTouchable=false
        searchConditionWindow!!.isFocusable=true
        searchConditionWindow!!.setOnDismissListener {
            searchConditionWindow==null
            isDepartmentListAction=false
            maskView.visibility=View.GONE
        }
        searchConditionWindow!!.showAsDropDown(rootView)
        var iv_condition_cancel = contentView.findViewById<AppCompatImageView>(R.id.iv_cancel)
        //取消的逻辑
        iv_condition_cancel.setOnClickListener({
            //取消的逻辑
            searchConditionWindow!!.dismiss()
        })

        var et_input_start_time = contentView.findViewById<EditText>(R.id.et_input_start_time)
        //盘点开始时间 点击事件
        et_input_start_time.setOnClickListener({
            var picker= DatePickerDialog(context,object: DatePickerDialog.OnDateSetListener {
                override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                    var calendar= Calendar.getInstance()
                    calendar.set(year,month,dayOfMonth)
                    var content= DateUtil.dateFormatMonth(calendar.time)
                    et_input_start_time!!.setText(content)
                }
            }, Calendar.getInstance().get(Calendar.YEAR),
                    Calendar.getInstance().get(Calendar.MONTH),
                    Calendar.getInstance().get(Calendar.DAY_OF_MONTH)).show()
        })

        //盘点结束时间
        var et_input_end_time = contentView.findViewById<EditText>(R.id.et_input_end_time)
        //盘点结束时间 点击事件
        et_input_end_time.setOnClickListener({
            var picker= DatePickerDialog(context,object: DatePickerDialog.OnDateSetListener {
                    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                        var calendar= Calendar.getInstance()
                        calendar.set(year,month,dayOfMonth)
                        var content= DateUtil.dateFormatMonth(calendar.time)
                        et_input_end_time!!.setText(content)
                    }
                }, Calendar.getInstance().get(Calendar.YEAR),
                        Calendar.getInstance().get(Calendar.MONTH),
                        Calendar.getInstance().get(Calendar.DAY_OF_MONTH)).show()
        })

        var et_input_search=contentView.findViewById<EditText>(R.id.et_input_search)
        et_department_input_search = contentView.findViewById<EditText>(R.id.et_department_input_search)

        var rl_department=contentView.findViewById<AutoRelativeLayout>(R.id.rl_department)
        //部门 展示 点击事件
        et_department_input_search!!.setOnClickListener({
            isDepartmentListAction=true
            //获取 部门的列表的数据
            context.getDepartmentListAction()
        })
        //recylerview的实例
        recyclerView=contentView.findViewById<RecyclerView>(R.id.recylerview_list)

        //当前选择的部门信息
        currentShowDepartmentName = ""

        //初始化 设置 信息:
        try {
            var assetSearchCondition:AssetSearchCondition ?=null
            if(currentPosition==0){
                assetSearchCondition= AssetSearchConditionDao.getByType(AssetListType.AssetListAll.listType.toInt())
            }else{
                assetSearchCondition= AssetSearchConditionDao.getByType(AssetListType.ZCodeRecoderList.listType.toInt())
            }
            et_input_start_time.setText(assetSearchCondition.endTime)
            et_input_end_time.setText(assetSearchCondition.startTime)
            et_input_search.setText(assetSearchCondition.keyword)
            et_department_input_search!!.setText(assetSearchCondition.shunname)
            currentShowDepartmentName=assetSearchCondition.shunname
        } catch (e: ContentException) {
            //没有对应的信息 不进行设置
        }


        var iv_ok=contentView.findViewById<AppCompatImageView>(R.id.iv_ok)
        //确定的点击事件
        iv_ok.setOnClickListener({
            //当前 的位置 是项目资产 的列表
            try {
                var assetSearchCondition:AssetSearchCondition ?=null
                if(currentPosition==0){
                    assetSearchCondition= AssetSearchConditionDao.getByType(AssetListType.AssetListAll.listType.toInt())
                }else{
                    assetSearchCondition= AssetSearchConditionDao.getByType(AssetListType.ZCodeRecoderList.listType.toInt())
                }
                assetSearchCondition!!.endTime=et_input_start_time.getTxt()
                assetSearchCondition!!.startTime=et_input_end_time.getTxt()
                assetSearchCondition!!.keyword=et_input_search.getTxt()
                assetSearchCondition!!.shunname=et_department_input_search!!.getTxt()
                //盘点 日期
                var date=AppPrefsUtils.getString(AppConstants.Inventory_Date_Name,DateUtil.dateFormatMonth(Calendar.getInstance().time))
                assetSearchCondition!!.pandate=date
                AssetSearchConditionDao.saveData(assetSearchCondition!!)
            } catch (e: ContentException) {
                //没有对应的信息
                var assetSearchCondition= AssetSearchCondition()
                if(currentPosition==0){
                    assetSearchCondition.assetType=AssetListType.AssetListAll.listType.toInt()
                }else{
                    assetSearchCondition.assetType=AssetListType.ZCodeRecoderList.listType.toInt()
                }

                assetSearchCondition.endTime=et_input_start_time.getTxt()
                assetSearchCondition.startTime=et_input_end_time.getTxt()
                assetSearchCondition.keyword=et_input_search.getTxt()
                assetSearchCondition.shunname=et_department_input_search!!.getTxt()
                //盘点 日期
                var date=AppPrefsUtils.getString(AppConstants.Inventory_Date_Name,DateUtil.dateFormatMonth(Calendar.getInstance().time))
                assetSearchCondition.pandate=date
                AssetSearchConditionDao.saveData(assetSearchCondition)
            }
            recyclerView!!.setBackgroundColor(Color.WHITE)
            searchConditionWindow!!.dismiss()

            //进行处理 对应业务
            context.processBussiness()
            for(data in context.fragmentList){
                (data as BaseMvpRecylerviewFragment<AssetPresenter, AssetsBean>).processBussiness()
            }
        })


    }


    /**
     *  展示部门的数据列表
     */
    fun showDepartmentList(departmentList: ArrayList<DepartmentBean>,context: Context) {
          if(isDepartmentListAction && searchConditionWindow!=null && searchConditionWindow!!.isShowing){
               //满足 条件的情况 下 展示列表数据
              recyclerView!!.setBackgroundColor(Color.WHITE)
              println("departmentList size:"+departmentList.size)
              var linearLayoutManager=LinearLayoutManager(context)
              linearLayoutManager.orientation=LinearLayoutManager.VERTICAL
              recyclerView!!.layoutManager=linearLayoutManager
              departmentList.add(0,DepartmentBean("请选择"))
              recyclerView!!.adapter=DepartmentListAdapter(departmentList,context, recyclerView)
          }
    }
}