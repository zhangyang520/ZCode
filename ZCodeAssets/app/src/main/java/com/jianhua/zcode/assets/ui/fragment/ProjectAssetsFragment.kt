package com.jianhua.zcode.assets.ui.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.jianhua.zcode.assets.R
import com.jianhua.zcode.assets.baselibrary.data.bean.RefreshAction
import com.jianhua.zcode.assets.baselibrary.ui.fragment.BaseMvpRecylerviewFragment
import com.jianhua.zcode.assets.baselibrary.ui.recylerviewRefrsehLayout.adapter.RecyclerListAdapter
import com.jianhua.zcode.assets.data.bean.AssetsBean
import com.jianhua.zcode.assets.data.request.AssetsPorjectRequest
import com.jianhua.zcode.assets.injection.component.DaggerAssetComponent
import com.jianhua.zcode.assets.presenter.AssetPresenter
import com.jianhua.zcode.assets.presenter.view.AssetView
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


    /**
     * 初始化相关的view
     * 以及 从数据库获取数据 如果有 初始化界面
     */
    override fun initView() {
        //展示数据
        getOriginAdapter().setItemList(getDatas())
        getOriginAdapter().notifyDataSetChanged()
    }

    /**
     * 处理对应的业务
     */
    override fun processBussiness() {
        super.processBussiness()
        //进行刷新
        mInteractionListener!!.requestNormal()
    }

    /**
     * 模拟的数据
     */
    private fun getDatas(): ArrayList<AssetsBean> {
        var i = 0
        var assetList=ArrayList<AssetsBean>()
        while (i < 20) {
            var assetsBean=AssetsBean()
            assetsBean.assetsName="设备1"
            assetsBean.assetsNumber=i
            assetsBean.assetsByDepartmentName="技术部"
            assetsBean.assetsModeNumber=i.toString()
            assetList.add(assetsBean)
            i++
        }
        return assetList
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
                                (LayoutInflater.from(activity).inflate(R.layout.item_project_assets, parent, false)) {
        var btn_xuhao:TextView?=null
        var tv_name:TextView?=null
        var tv_id_value:TextView?=null
        var tv_position:TextView?=null

        init {
            btn_xuhao=itemView!!.findViewById(R.id.btn_xuhao)
            tv_name=itemView!!.findViewById(R.id.tv_name)
            tv_id_value=itemView!!.findViewById(R.id.tv_id_value)
            tv_position=itemView!!.findViewById(R.id.tv_position)
        }

        override fun bind(item: AssetsBean, position: Int) {
            btn_xuhao!!.setText(position.toString())
            tv_id_value!!.setText(item.assetsNumber.toString())
            tv_name!!.setText(item.assetsName)
            tv_position!!.setText(item.assetsByDepartmentName)
        }
    }

    /**
     * 获取资产列表 的网络请求 回调
     */
    override fun onGetProjectAssetList(projectAssets: ArrayList<AssetsBean>, refreshAction: RefreshAction, listener: RequestListener) {
        //监听器的回调
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

        override  fun requestMore() {
            pagePreNumber=pageNumber
            pageNumber+=1
            //加载更多 的网络请求
            simulateNetworkRequest(object : RequestListener {
                override fun onSuccess(openProjectModels: List<AssetsBean>,refreshAction: RefreshAction) {
                    getOriginAdapter().getItemList().addAll(openProjectModels)
                    getHeaderAdapter().notifyDataSetChanged()
                    super@ItemInteractionListener.requestMore()
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
        /**
         * todo 相关的请求参数 需要完善
         */
        basePresenter.getAssetList(AssetsPorjectRequest(-1,pageNumber,pageCount),refreshAction,listener)
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