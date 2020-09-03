package com.example.demovm.bottomdraw

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter


class BottomDrawTabAdapter(fm: FragmentManager, behavior: Int) : FragmentPagerAdapter(fm, behavior){
    val tabTitle: List<String> = listOf(
        "彈跳視窗","動畫","測試"
    )
    var datas : List<String> = listOf(
        tabTitle[0],tabTitle[1],tabTitle[2]
    )

    override fun getItem(position: Int): Fragment {
       lateinit var fragment : Fragment
        when(datas[position]){
            tabTitle[0] -> {
                fragment = BottomDrawFragment.newInstance("100")
            }
            tabTitle[1] -> {
                fragment = BottomDrawFragment.newInstance("200")
            }
            tabTitle[2] -> {
                fragment = BottomDrawFragment.newInstance("300")
            }
       }
        return fragment
    }

    override fun getCount(): Int {
        return datas.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return datas[position]
    }

}