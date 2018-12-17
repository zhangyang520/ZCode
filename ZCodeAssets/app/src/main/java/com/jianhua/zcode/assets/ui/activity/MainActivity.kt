package com.jianhua.zcode.assets.ui.activity

import android.graphics.Color
import android.support.v4.app.Fragment
import android.view.Gravity
import android.view.View
import android.widget.AdapterView
import android.widget.SimpleAdapter
import com.jianhua.zcode.assets.R
import com.jianhua.zcode.assets.baselibrary.ui.activity.BaseAcvitiy
import com.jianhua.zcode.assets.ui.adapter.PagerAdapter
import com.jianhua.zcode.assets.ui.dialog.SettingDialog
import com.jianhua.zcode.assets.ui.fragment.ProjectAssetsFragment
import com.jianhua.zcode.assets.ui.fragment.ZCodeRecordListFragment
import com.jianhua.zcode.assets.ui.popup.SeachConditionPopup
import com.suntray.chinapost.baselibrary.ut.base.utils.AppPrefsUtils
import com.zhy.autolayout.utils.AutoUtils
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

/**
 *    主界面
 * @Title:
 * @ProjectName
 * @Description: TODO
 * @author zhangyang
 * @date
 */
class MainActivity : BaseAcvitiy() {

    //集合数据
    var tabArray:ArrayList<String> = arrayListOf<String>()
    var fragmentList=ArrayList<Fragment>();
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

        //设置对应的适配器
        container.adapter= PagerAdapter(tabArray, supportFragmentManager,fragmentList)
        //指示器绑定 对应的 viewPagaer
        ll_middle.setTabTextColors(Color.parseColor("#000000"),Color.parseColor("#d4237a"))
        ll_middle.setupWithViewPager(container)

        container.setCurrentItem(0)
        //对应的imageView 搜索对应的事件
        iv_search.setOnClickListener({
            SeachConditionPopup.showSearchConditionPopup(findViewById(R.id.ll_top),this@MainActivity,maskView)
            maskView.visibility=View.VISIBLE
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

        })
    }

    /**
     * 获取view对象
     */
    override fun getView(): View {
        return View.inflate(this@MainActivity,R.layout.activity_main,null)
    }
}
