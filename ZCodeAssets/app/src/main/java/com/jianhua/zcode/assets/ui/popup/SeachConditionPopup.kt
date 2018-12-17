package com.jianhua.zcode.assets.ui.popup

import android.app.DatePickerDialog
import android.content.Context
import android.support.v7.widget.AppCompatImageView
import android.view.Gravity
import android.view.View
import android.widget.DatePicker
import android.widget.EditText
import android.widget.PopupWindow
import com.jianhua.zcode.assets.R
import com.jianhua.zcode.assets.baselibrary.utils.DateUtil
import com.zhy.autolayout.config.AutoLayoutConifg
import com.zhy.autolayout.utils.AutoUtils
import java.util.*

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
    /**
     * 展现出搜索条件的框
     */
    fun showSearchConditionPopup(rootView: View, context: Context, maskView: View){
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
                    var content= DateUtil.dateFormat(calendar.time)
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
                        var content= DateUtil.dateFormat(calendar.time)
                        et_input_end_time!!.setText(content)
                    }
                }, Calendar.getInstance().get(Calendar.YEAR),
                        Calendar.getInstance().get(Calendar.MONTH),
                        Calendar.getInstance().get(Calendar.DAY_OF_MONTH)).show()
        })


        var iv_ok=contentView.findViewById<AppCompatImageView>(R.id.iv_ok)
        //确定的点击事件
        iv_ok.setOnClickListener({
            searchConditionWindow!!.dismiss()
        })


    }

}