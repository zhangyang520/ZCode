package com.jianhua.zcode.assets.ui.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.jianhua.zcode.assets.ui.activity.MainActivity

/**
 *  适配器
 *  @Title ${name}
 *  @ProjectName ZCodeAssets
 *  @Description: TODO
 *  @author Administrator
 *  @date 2018/12/1514:00
 *
 */
/**
 *  对应的适配器
 */
class PagerAdapter : FragmentStatePagerAdapter {

    constructor(tabArray: ArrayList<String>, fm: FragmentManager?, fragmentList: ArrayList<Fragment>):super(fm){
        this.tabArray=tabArray
        this.fragmentList=fragmentList
    }
    //tab数组
    var tabArray:ArrayList<String> ?=null
    var fragmentList:ArrayList<Fragment>? =null

    override fun getItem(position: Int): Fragment {
        return fragmentList!!.get(position)
    }

    override fun getCount(): Int {
        return tabArray!!.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return tabArray!!.get(position)
    }
}