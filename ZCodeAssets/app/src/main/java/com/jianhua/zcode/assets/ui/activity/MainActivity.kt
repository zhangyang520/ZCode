package com.jianhua.zcode.assets.ui.activity

import android.content.Intent
import android.graphics.Color
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.Gravity
import android.view.View
import com.jianhua.zcode.assets.R
import com.jianhua.zcode.assets.baselibrary.Rx.getTxt
import com.jianhua.zcode.assets.baselibrary.exception.ContentException
import com.jianhua.zcode.assets.baselibrary.ui.activity.BaseMvpActivity
import com.jianhua.zcode.assets.baselibrary.utils.DateUtil
import com.jianhua.zcode.assets.baselibrary.utils.ToastUtil
import com.jianhua.zcode.assets.data.bean.AssetListType
import com.jianhua.zcode.assets.data.bean.AssetSearchCondition
import com.jianhua.zcode.assets.data.bean.DepartmentBean
import com.jianhua.zcode.assets.data.common.AppConstants
import com.jianhua.zcode.assets.data.dao.AssetSearchConditionDao
import com.jianhua.zcode.assets.data.request.AllDepartmentRequest
import com.jianhua.zcode.assets.data.request.PanTotalRequest
import com.jianhua.zcode.assets.data.response.PanTotalResponse
import com.jianhua.zcode.assets.injection.component.DaggerAssetComponent
import com.jianhua.zcode.assets.presenter.AssetPresenter
import com.jianhua.zcode.assets.presenter.view.AssetView
import com.jianhua.zcode.assets.ui.adapter.PagerAdapter
import com.jianhua.zcode.assets.ui.dialog.SettingDialog
import com.jianhua.zcode.assets.ui.fragment.ProjectAssetsFragment
import com.jianhua.zcode.assets.ui.fragment.ZCodeRecordListFragment
import com.jianhua.zcode.assets.ui.popup.SeachConditionPopup
import com.suntray.chinapost.baselibrary.ut.base.utils.AppPrefsUtils
import com.zhy.autolayout.utils.AutoUtils
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

/**
 *    主界面
 * @Title:
 * @ProjectName
 * @Description: TODO
 * @author zhangyang
 * @date
 */
class MainActivity : BaseMvpActivity<AssetPresenter>(),AssetView {

    //集合数据
    var tabArray:ArrayList<String> = arrayListOf<String>()
    var fragmentList = ArrayList<Fragment>();
    //对应的位置 currentPosition
    var currentPosition=0;


    /**
     * 初始化 绑定
     */
    override fun injectCompontent() {
        DaggerAssetComponent.builder().activityComponent(activityComponent).build().bind(this)
        basePresenter.baseView=this
    }

    /**
     * 请求  部门列表数据
     */
    fun  getDepartmentListAction(){
         basePresenter.getDepartmentList(AllDepartmentRequest())
    }
    /**
     * 获取部门列表 的数据
     */
    override fun onGetDepartmentList(departmentList: ArrayList<DepartmentBean>) {
       SeachConditionPopup.showDepartmentList(departmentList,this@MainActivity)
    }
    /**
     * 初始化view
     */
    override fun initView() {
        isBlackShow=false
        isTitleShow=false
        isRightShow=false

        tabArray.add("项目资产")
        tabArray.add("扫码记录")

        fragmentList.clear()
        fragmentList.add(ProjectAssetsFragment())
        fragmentList.add(ZCodeRecordListFragment())

        //页面变化的监听器
        container.setOnPageChangeListener(object:ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                //打印 页面切换 的对应的 position
                currentPosition=position
                println("onPageSelected position:"+position)
            }
        })

        //设置对应的适配器
        container.adapter= PagerAdapter(tabArray, supportFragmentManager,fragmentList)

        //指示器绑定 对应的 viewPagaer
        ll_middle.setTabTextColors(Color.parseColor("#000000"),Color.parseColor("#d4237a"))
        ll_middle.setupWithViewPager(container)

        container.setCurrentItem(0)
        //对应的imageView 搜索对应的事件
        iv_search.setOnClickListener({
            maskView.visibility=View.VISIBLE
            SeachConditionPopup.showSearchConditionPopup(findViewById(R.id.ll_top),this@MainActivity,maskView,currentPosition)
        })


        //设置的点击事件
        iv_setting.setOnClickListener({
                var dialog= SettingDialog(this@MainActivity,R.style.ActionSheetDialogStyle)
                //获取当前Activity所在的窗体
                var  dialogWindow = dialog.getWindow();
                //设置Dialog从窗体底部弹出
                dialogWindow.getDecorView().setPadding(AutoUtils.getPercentWidthSize(70), 0, 0, 0);
                var lp = dialogWindow.getAttributes();
                //设置宽
                lp.width = AutoUtils.getPercentWidthSize(700)
                //设置高
                lp.height = AutoUtils.getPercentHeightSize(1334)

                dialogWindow.setAttributes(lp);
                //显示对话框
                dialogWindow.setGravity(Gravity.RIGHT);
                //设置dialog的动画效果
                dialogWindow.setWindowAnimations(R.style.dialogWindowAnim);
                dialog.show()
        })

        /**
         * 图片 扫描按钮
         */
        iv_saomiao.setOnClickListener({
               requestPermission(101, android.Manifest.permission.CAMERA,object:Runnable{
                   override fun run() {
                       //通过的情况
                       var intent = Intent(this@MainActivity, ZCodeScanActivity::class.java)
                       startActivity(intent)
                   }
               },object:Runnable{
                   override fun run() {
                       //不允许的情况
                       ToastUtil.makeText(this@MainActivity,"")
                   }
               })
        })
    }

    /**
     * 处理对应的逻辑
     */
    override fun processBussiness() {
        super.processBussiness()
        try {
            var assetSearchCondition: AssetSearchCondition?=null
            assetSearchCondition= AssetSearchConditionDao.getByType(AssetListType.AssetListAll.listType.toInt())
            //盘点 日期
            var date= AppPrefsUtils.getString(AppConstants.Inventory_Date_Name, DateUtil.dateFormatMonth(Calendar.getInstance().time))
            assetSearchCondition!!.pandate=date

            basePresenter.getPanTongji(PanTotalRequest(-1,date,assetSearchCondition.keyword,assetSearchCondition.shunname))
        } catch (e: ContentException) {
            //没有对应的信息

            //盘点 日期
            var date= AppPrefsUtils.getString(AppConstants.Inventory_Date_Name, DateUtil.dateFormatMonth(Calendar.getInstance().time))
            basePresenter.getPanTongji(PanTotalRequest(-1,date,"",""))
        }
    }

    override fun onRestart() {
        super.onRestart()
        try {
            var assetSearchCondition: AssetSearchCondition?=null
            assetSearchCondition= AssetSearchConditionDao.getByType(AssetListType.AssetListAll.listType.toInt())
            //盘点 日期
            var date= AppPrefsUtils.getString(AppConstants.Inventory_Date_Name, DateUtil.dateFormatMonth(Calendar.getInstance().time))
            assetSearchCondition!!.pandate=date

            basePresenter.getPanTongji(PanTotalRequest(-1,date,assetSearchCondition.keyword,assetSearchCondition.shunname))
        } catch (e: ContentException) {
            //没有对应的信息

            //盘点 日期
            var date= AppPrefsUtils.getString(AppConstants.Inventory_Date_Name, DateUtil.dateFormatMonth(Calendar.getInstance().time))
            basePresenter.getPanTongji(PanTotalRequest(-1,date,"",""))
        }
    }
    /**
     *  获取统计信息的回调
     */
    override fun onGetPanTongji(panTotalResponse: PanTotalResponse) {
        //设置对应的数字
        tv_total_num_value.setText(panTotalResponse.isTotalNum.toString())
        //已盘点 数量
        tv_yipandian_num_value.setText(panTotalResponse.isPandianTotal.toString())
    }
    /**
     * 获取view对象
     */
    override fun getView(): View {
        return View.inflate(this@MainActivity,R.layout.activity_main,null)
    }
}
