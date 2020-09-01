package com.example.demovm.mainFunctionName

import android.app.Activity
import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.demovm.R
import com.example.demovm.mainFunctionName.subFunctionNameOne.MainFragment
import com.example.demovm.mainFunctionName.subFunctionNameTwo.SecondFragment
import com.example.demovm.mainFunctionName.tabFragment.TabFragment

class FragmentViewPagerAdapter(val context: Context, fm: FragmentManager, behavior: Int) :
    FragmentPagerAdapter(fm, behavior) {

    var title = ArrayList<String>()

    fun setTitle(title: List<String>) {
        this.title = title as ArrayList<String>
        notifyDataSetChanged()
    }

    //回傳一個 Fragment 的實體
    override fun getItem(position: Int): Fragment {
        var frg = Fragment()
        fun ddd() {
            destroyItem(
                (context as Activity).findViewById(R.id.vpFrg),
                title.indexOf("2 Frag"),
                SecondFragment()
            )
        }
        when (title[position]) {
            "Tab1" -> {
//                ddd()
                frg = MainFragment()
            }
            "2 Frag" -> {
//                destroyItem(
//                    (context as Activity).findViewById(R.id.vpFrg),
//                    title.indexOf("2 Frag"),
//                    SecondFragment()
//                )
                frg = SecondFragment()
            }
            //直接實作 new Fragment Instance
            "全部" -> {
                frg = TabFragment()
//                ddd()
            }
            context.getString(R.string.odd_number) -> {
                frg = TabFragment.newInstance(1)
//                ddd()
            }
            context.getString(R.string.even_number) -> {
                frg = TabFragment.newInstance(2)
                ddd()
            }
//           "奇數" -> { frg = TabFragment.newInstance(1) }
//           "偶數" -> { frg = TabFragment.newInstance(2) }
        }

        return frg
    }

    override fun getCount(): Int {
        return title.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return title[position]
    }

}