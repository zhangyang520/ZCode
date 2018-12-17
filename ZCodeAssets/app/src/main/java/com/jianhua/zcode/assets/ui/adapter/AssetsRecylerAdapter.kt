package com.jianhua.zcode.assets.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import com.jianhua.zcode.assets.R
import com.jianhua.zcode.assets.data.bean.AssetsBean
import com.zhy.autolayout.utils.AutoUtils

/**
 *  项目资产的适配器
 *  @Title ${name}
 *  @ProjectName ZCodeAssets
 *  @Description: TODO
 *  @author Administrator
 *  @date 2018/12/1517:46
 *
 */
class AssetsRecylerAdapter: RecyclerView.Adapter<AssetsRecylerAdapter.AssetsRecylerViewHolder>{


    var context:Context?=null
    var assetsList:ArrayList<AssetsBean>?=null

    constructor(context: Context?,assetsList:ArrayList<AssetsBean>) : super() {
        this.context = context
        this.assetsList=assetsList
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): AssetsRecylerViewHolder {
        var contentView=View.inflate(context, R.layout.item_project_assets,null)
        var layoutParams=ViewGroup.LayoutParams(AutoUtils.getPercentWidthSize(750),ViewGroup.LayoutParams.WRAP_CONTENT)
        contentView.layoutParams=layoutParams
        contentView.requestLayout()
        return AssetsRecylerViewHolder(contentView)
    }

    override fun getItemCount(): Int {
        if(assetsList!=null){
            return assetsList!!.size
        }
        return 0
    }

    /**
     * 绑定数据
     */
    override fun onBindViewHolder(holder: AssetsRecylerViewHolder?, position: Int) {
        holder!!.btn_xuhao!!.setText(position.toString())
        holder!!.tv_id_value!!.setText(assetsList!!.get(position).assetsNumber.toString())
        holder!!.tv_name!!.setText(assetsList!!.get(position).assetsName)
        holder.tv_position!!.setText(assetsList!!.get(position).assetsByDepartmentName)
    }

    class AssetsRecylerViewHolder:RecyclerView.ViewHolder {

        var btn_xuhao:TextView?=null
        var tv_name:TextView?=null
        var tv_id_value:TextView?=null
        var tv_position:TextView?=null

        constructor(itemView: View?) : super(itemView){
            btn_xuhao=itemView!!.findViewById(R.id.btn_xuhao)
            tv_name=itemView!!.findViewById(R.id.tv_name)
            tv_id_value=itemView!!.findViewById(R.id.tv_id_value)
            tv_position=itemView!!.findViewById(R.id.tv_position)
        }
    }
}