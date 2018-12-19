package com.jianhua.zcode.assets.ui.fragment

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.jianhua.zcode.assets.R
import com.jianhua.zcode.assets.baselibrary.data.bean.RefreshAction
import com.jianhua.zcode.assets.baselibrary.exception.ContentException
import com.jianhua.zcode.assets.baselibrary.ui.fragment.BaseMvpRecylerviewFragment
import com.jianhua.zcode.assets.baselibrary.ui.recylerviewRefrsehLayout.adapter.RecyclerListAdapter
import com.jianhua.zcode.assets.baselibrary.utils.DateUtil
import com.jianhua.zcode.assets.baselibrary.utils.ToastUtil
import com.jianhua.zcode.assets.data.bean.AssetListType
import com.jianhua.zcode.assets.data.bean.AssetsBean
import com.jianhua.zcode.assets.data.common.AppConstants
import com.jianhua.zcode.assets.data.dao.AssetBeanDao
import com.jianhua.zcode.assets.data.dao.AssetSearchConditionDao
import com.jianhua.zcode.assets.data.request.AssetsPorjectRequest
import com.jianhua.zcode.assets.injection.component.DaggerAssetComponent
import com.jianhua.zcode.assets.presenter.AssetPresenter
import com.jianhua.zcode.assets.presenter.view.AssetView
import com.suntray.chinapost.baselibrary.ut.base.utils.AppPrefsUtils
import java.util.*
import kotlin.collections.ArrayList

/**
 *  项目资产的 fragment
 *  @Title ${name}
 *  @ProjectName ZCodeAssets
 *  @Description: TODO
 *  @author Administrator
 *  @date 2018/12/1515:46
 */
class ProjectAssetsFragment: BaseMvpRecylerviewFragment<AssetPresenter,AssetsBean>(),AssetView{

    override fun injectCompontent() {
        DaggerAssetComponent.builder().activityComponent(activityComponent).build().bind(this)
        basePresenter.baseView=this
    }

    var recylerVew:RecyclerView?=null
    /**
     * 初始化 对应的view
     */
    override fun initView() {
        recylerVew=root!!.findViewById(R.id.recycler_view)
    }

    /**
     * 处理对应的业务
     */
    override fun processBussiness() {
        super.processBussiness()
        //处理对应的业务
        //展示数据 首先 从数据库中获取 数据
        try {
            var dataList=AssetBeanDao.getAll()
            //有对应的数据
            getOriginAdapter().setItemList(dataList)
            getOriginAdapter().notifyDataSetChanged()
            //进行刷新
            mInteractionListener!!.requestNormal()
        } catch (e: ContentException) {
            //进行刷新
            mInteractionListener!!.requestNormal()
        }
    }

    /**
     * 创建适配器
     */
    override fun createAdapter(): RecyclerListAdapter<AssetsBean, *> {
        return object : RecyclerListAdapter<AssetsBean,RecyclerListAdapter.ViewHolder<AssetsBean>>() {
            init {
                addViewType(AssetsBean::class.java, object : RecyclerListAdapter.ViewHolderFactory<RecyclerListAdapter.ViewHolder<AssetsBean>> {
                   override fun onCreateViewHolder(parent: ViewGroup): RecyclerListAdapter.ViewHolder<AssetsBean> {
                        return ItemViewHolder(parent)
                    }
                })
            }
        }
    }


    /**
     * itemHolder 每个条目的封装
     */
    private inner class ItemViewHolder(parent: ViewGroup) : RecyclerListAdapter.ViewHolder<AssetsBean>
                                (LayoutInflater.from(activity).inflate(R.layout.item_fragment_project_assets, parent, false)) {
        var btn_xuhao:TextView?=null
        var tv_name:TextView?=null
        var tv_pan_date_value:TextView?=null
        var tv_pan_state:TextView?=null
        var tv_num:TextView?=null
        var tv_xinghao:TextView?=null
        var  tv_belong_project:TextView?=null
        var tv_by_people:TextView?=null
        var tv_department:TextView?=null
        var tv_project_num:TextView?=null

        init {
            btn_xuhao=itemView!!.findViewById(R.id.btn_xuhao)
            tv_name=itemView!!.findViewById(R.id.tv_name)
            tv_pan_date_value=itemView!!.findViewById(R.id.tv_pan_date_value)
            tv_pan_state=itemView!!.findViewById(R.id.tv_pan_state_value)

            tv_num=itemView!!.findViewById(R.id.tv_num)
            tv_xinghao=itemView!!.findViewById(R.id.tv_xinghao)
            tv_belong_project=itemView!!.findViewById(R.id.tv_belong_project)
            tv_by_people=itemView!!.findViewById(R.id.tv_by_people)

            tv_department=itemView!!.findViewById(R.id.tv_department)
            tv_project_num=itemView!!.findViewById(R.id.tv_project_num)
        }

        override fun bind(item: AssetsBean, position: Int) {
            btn_xuhao!!.setText((position+1).toString())
            tv_pan_date_value!!.setText(item.pantime)
            tv_name!!.setText(item.title)
            if(item.ispandian.equals("物品已盘点")){
                tv_pan_state!!.setText(item.ispandian)
                tv_pan_state!!.setTextColor(Color.BLUE)
            }else{
                tv_pan_state!!.setText(item.ispandian)
                tv_pan_state!!.setTextColor(Color.RED)
            }


            tv_num!!.setText(item.num)
            tv_xinghao!!.setText(item.model)
            tv_belong_project!!.setText(item.projectgroup)
            tv_by_people!!.setText(item.useName)

            tv_department!!.setText(item.shuname)
            tv_project_num!!.setText(item.projectnum)
        }
    }

    /**
     * 获取资产列表 的网络请求 回调
     */
    override fun onGetProjectAssetList(projectAssets: ArrayList<AssetsBean>, refreshAction: RefreshAction, listener: RequestListener) {
        //监听器的回调
        println("onGetProjectAssetList projectAssets:"+projectAssets.toString())
        listener.onSuccess(projectAssets,refreshAction)
    }


    /**
     *  失败的相关刷新 回调
     */
    override fun onErrorAction(action: RefreshAction, errorContent: String, listener: RequestListener) {
        listener.onFailed(action,errorContent)
    }


    /**
     * 返回交互器
     */
    override fun createInteraction(): InteractionListener {
        return ItemInteractionListener()
    }
    /**
     * 条目的交互的监听器
     */
    private inner class ItemInteractionListener : InteractionListener(){
        override fun requestRefresh() {
            //进行网络请求
            pagePreNumber=pageNumber
            pageNumber=1
            simulateNetworkRequest(object : RequestListener {
                override fun onSuccess(openProjectModels: List<AssetsBean>,refreshAction: RefreshAction) {
                    getOriginAdapter().getItemList().clear()
                    getOriginAdapter().getItemList().addAll(openProjectModels)
                    getHeaderAdapter().notifyDataSetChanged()
                    super@ItemInteractionListener.requestRefresh()
                }

                override fun onFailed(refreshAction: RefreshAction,errorContent:String) {
                    super@ItemInteractionListener.requestFailure(errorContent)
                    //失败的时候 页码数 进行 回调
                    pageNumber=pagePreNumber

                }
            },RefreshAction.PullDownRefresh)
        }

        override  fun requestMore(openProjectModels1: List<Any>?) {
            pagePreNumber=pageNumber
            pageNumber+=1
            //加载更多 的网络请求
            simulateNetworkRequest(object : RequestListener {
                override fun onSuccess(openProjectModels: List<AssetsBean>,refreshAction: RefreshAction) {
                    if(openProjectModels.size==0){
                        pageNumber-=1
                        ToastUtil.makeText(activity,"暂无更多的数据")
                    }else{
                        getOriginAdapter().getItemList().addAll(openProjectModels)
                        getHeaderAdapter().notifyDataSetChanged()
                    }
                    super@ItemInteractionListener.requestMore(openProjectModels)
                }

                override fun onFailed(refreshAction: RefreshAction,errorContent:String) {
                    super@ItemInteractionListener.requestFailure(errorContent)
                    //失败的时候 页码数 进行 回调
                    pageNumber=pagePreNumber
                }
            },RefreshAction.UpMore)
        }

        override fun requestNormal() {
           pageNumber=1
            simulateNetworkRequest(object : RequestListener {
                override fun onSuccess(openProjectModels: List<AssetsBean>,refreshAction: RefreshAction) {
                    getOriginAdapter().getItemList().clear()
                    getOriginAdapter().getItemList().addAll(openProjectModels)
                    getHeaderAdapter().notifyDataSetChanged()
                    super@ItemInteractionListener.requestNormal()
                }

                override fun onFailed(refreshAction: RefreshAction,errorContent:String) {
                    super@ItemInteractionListener.requestFailure(errorContent)
                    pageNumber=pagePreNumber
                }
            },RefreshAction.NormalAction)
        }

        override fun requestSearch() {
            pageNumber=1
            simulateNetworkRequest(object : RequestListener {
                override fun onSuccess(openProjectModels: List<AssetsBean>,refreshAction: RefreshAction) {
                    getOriginAdapter().getItemList().clear()
                    getOriginAdapter().getItemList().addAll(openProjectModels)
                    getHeaderAdapter().notifyDataSetChanged()
                    super@ItemInteractionListener.requestSearch()
                }

                override fun onFailed(refreshAction: RefreshAction,errorContent:String) {
                    super@ItemInteractionListener.requestFailure(errorContent)
                    pageNumber=pagePreNumber
                }
            },RefreshAction.SearchAction)
        }
    }


    /**
     * 网络请求
     */
    private fun simulateNetworkRequest(listener: RequestListener,refreshAction: RefreshAction) {

        try {
            var assetSearchCondition=
                    AssetSearchConditionDao.getByType(AssetListType.AssetListAll.listType.toInt())
            //有对应的条件
            basePresenter.getAssetList(AssetsPorjectRequest(-1,pageNumber,pageCount,
                    AssetListType.AssetListAll.listType.toInt(),assetSearchCondition.pandate,
                    assetSearchCondition.startTime,assetSearchCondition.endTime,
                    assetSearchCondition.shunname,assetSearchCondition.keyword),refreshAction,listener)
        } catch (e: ContentException) {
           //没有对应的搜索条件
            var panDate=AppPrefsUtils.getString(AppConstants.Inventory_Date_Name, DateUtil.dateFormatMonth(Calendar.getInstance().time))
            basePresenter.getAssetList(AssetsPorjectRequest(-1,pageNumber,pageCount,
                    AssetListType.AssetListAll.listType.toInt(),panDate,"","","",""),refreshAction,listener)
        }
    }


    /**
     * 请求的监听器
     */
     interface RequestListener {
        /**
         * 成功的回调
         */
        fun onSuccess(openProjectModels: List<AssetsBean>,refreshAction: RefreshAction)

        fun onFailed(refreshAction: RefreshAction,errorContent:String)
    }
}