package com.example.demovm.mainFunctionName.accountinfo

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.demovm.R

class AccountInfoViewPagerAdapter(val context: Context,fragmentManager: FragmentManager, behavior: Int): FragmentPagerAdapter(fragmentManager, behavior) {

    var title = ArrayList<String>()
    private val accountInfoFragment: AccountInfoFragment = AccountInfoFragment()

    fun setTitle(list: List<String>){
        this.title = list as ArrayList<String>
        notifyDataSetChanged()
    }

    override fun getItem(position: Int): Fragment {
        var fragment = Fragment()
        when(title[position]){
            context.getString(R.string.accountinfo) -> { fragment = accountInfoFragment }
            "一般Fragment" -> { fragment = NormalFragment()}
        }
        return fragment
    }

    override fun getCount(): Int {
        return this.title.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return title[position]
    }
}
