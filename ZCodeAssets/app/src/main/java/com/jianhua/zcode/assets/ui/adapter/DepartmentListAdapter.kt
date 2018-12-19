package com.jianhua.zcode.assets.ui.adapter

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.jianhua.zcode.assets.R
import com.jianhua.zcode.assets.data.bean.DepartmentBean
import com.jianhua.zcode.assets.ui.popup.SeachConditionPopup
import com.zhy.autolayout.AutoRelativeLayout
import org.w3c.dom.Text

/**
 *  部门 的列表 数据
 *  @Title ${name}
 *  @ProjectName ZCodeAssets
 *  @Description: TODO
 *  @author Administrator
 *  @date 2018/12/1815:25
 *
 */
class DepartmentListAdapter : RecyclerView.Adapter<DepartmentListAdapter.DepartmentListViewHolder>{

    var departmentList:ArrayList<DepartmentBean> ?=null
    var context:Context ?=null
    var recyclerView:RecyclerView?=null

    constructor(departmentList: ArrayList<DepartmentBean>?) : super() {
        this.departmentList = departmentList
    }

    constructor(departmentList: ArrayList<DepartmentBean>?, context: Context?) : super() {
        this.departmentList = departmentList
        this.context = context
    }

    constructor(departmentList: ArrayList<DepartmentBean>?, context: Context?, recyclerView: RecyclerView?) : super() {
        this.departmentList = departmentList
        this.context = context
        this.recyclerView = recyclerView
    }


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): DepartmentListViewHolder {
        return DepartmentListViewHolder(View.inflate(context,R.layout.item_department,null))
    }

    override fun getItemCount(): Int {
        if(departmentList!=null){
            return departmentList!!.size
        }
        return 0
    }

    override fun onBindViewHolder(holder: DepartmentListViewHolder?, position: Int) {

        if(departmentList!!.get(position).name.equals("请选择")){
            //如果 是第一个
            holder!!.rl_choose_item!!.visibility = View.GONE
            holder!!.tv_choose!!.visibility=View.VISIBLE
            holder.line!!.visibility=View.GONE
        }else{
            if(departmentList!!.get(position).name.equals(SeachConditionPopup.currentShowDepartmentName)){
                //如果 相等
                holder!!.rl_choose_item!!.visibility = View.VISIBLE
                holder!!.tv_choose!!.visibility=View.GONE
                //对应的条目 设置信息
                holder.tv_department_name!!.setText(departmentList!!.get(position).name)
                holder.tv_department_name!!.setTextColor(context!!.resources.getColor(R.color.fen_se))
                holder.view_ok!!.visibility=View.VISIBLE
                holder!!.tv_choose!!.visibility=View.GONE
                holder.line!!.visibility=View.VISIBLE
            }else{
                holder!!.rl_choose_item!!.visibility = View.VISIBLE
                //对应的条目 设置信息
                holder.tv_department_name!!.setText(departmentList!!.get(position).name)
                holder.tv_department_name!!.setTextColor(Color.BLACK)
                holder!!.tv_choose!!.visibility=View.GONE
                holder.view_ok!!.visibility=View.GONE
                holder.line!!.visibility=View.VISIBLE
                holder!!.tv_choose!!.visibility=View.GONE
            }
        }

        //对整个条目的 点击事件
        holder.itemView.setOnClickListener({
            if(SeachConditionPopup.et_department_input_search!=null){
                SeachConditionPopup.recyclerView!!.setBackgroundColor(context!!.resources.getColor(R.color.mask_color))
                SeachConditionPopup.currentShowDepartmentName=departmentList!!.get(position).name
                if(departmentList!!.get(position).name.equals("请选择")){
                    SeachConditionPopup.et_department_input_search!!.setText("")
                }else{
                    SeachConditionPopup.et_department_input_search!!.setText(departmentList!!.get(position).name)
                }
                departmentList!!.clear()
                notifyDataSetChanged()
            }
        })
    }

    inner class DepartmentListViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

        //相对布局
        var rl_choose_item:AutoRelativeLayout?=null
        var tv_department_name:TextView?=null
        var view_ok:View ?=null
        var tv_choose:TextView ?=null
        var line:View?=null

        init {
             rl_choose_item=itemView!!.findViewById(R.id.rl_choose_item)
             tv_department_name=itemView!!.findViewById(R.id.tv_department_name)
             view_ok=itemView!!.findViewById(R.id.view_ok)
            tv_choose = itemView!!.findViewById(R.id.tv_choose)
            line=itemView.findViewById(R.id.line)
        }
    }
}