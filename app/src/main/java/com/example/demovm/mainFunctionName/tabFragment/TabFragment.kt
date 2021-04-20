package com.example.demovm.mainFunctionName.tabFragment

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.demovm.R
import com.example.demovm.base.BaseDaggerFragment
import com.example.demovm.databinding.TabFragmentBinding
import com.example.demovm.mainFunctionName.FunctionNameMainActivityViewModel
import com.example.demovm.mainFunctionName.number.three.ThreeFragment
import com.example.demovm.mainFunctionName.tabFragment.item.ItemAdapter
import com.example.demovm.mainFunctionName.tabFragment.item.ItemDetail
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import timber.log.Timber


private const val TAG = "TabFragment"

class TabFragment : BaseDaggerFragment() {

    // ??????
    companion object {
        const val TAB_KIND = "TAB_KIND"
        fun newInstance(kind: Int): TabFragment {
            var frg = TabFragment()
            frg.apply {
                arguments = Bundle().apply {
                    putInt(TAB_KIND, kind)
                }
            }
            return frg
        }
    }

    override val viewModel by viewModels<TabFragmentViewModel> { viewModelFactory }
    val activityViewModel by activityViewModels<FunctionNameMainActivityViewModel> { viewModelFactory }
    override lateinit var binding: TabFragmentBinding

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = TabFragmentBinding.inflate(inflater, null, false).apply {
            this.viewModel = this@TabFragment.viewModel
            this.activityViewModel = this@TabFragment.activityViewModel
        }

        initHeader()
        initobserver()
        binding.rvItemList.layoutManager = GridLayoutManager(context, 4)
        binding.lifecycleOwner = this.viewLifecycleOwner

        return binding.root
    }

    private fun initHeader() {
        activityViewModel.headerTitle.value = "Activity"
        activityViewModel.headerBack.value = false
        Log.i(TAG, "ViewPager headerBack: " + activityViewModel.headerBack.value)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun initobserver() {
        viewModel.itemListEvent.observe(this, Observer {
            Log.i(TAG, "initobserver: " + viewModel.itemList.size)
            binding.rvItemList.adapter = ItemAdapter(viewModel).apply {
                var type = arguments?.getInt(TAB_KIND)
                var newlist: MutableList<ItemDetail> = arrayListOf()
                when (type) {
                    1 -> {
                        for (i in viewModel.itemList) {
                            if (i.kind == type) newlist.add(i)
                        }
                    }
                    2 -> {
                        for (i in viewModel.itemList) {
                            if (i.kind == type) newlist.add(i)
                        }
                    }
                    else -> {
                        newlist.addAll(viewModel.itemList)
                        viewModel.tabKind.value = true
                    }
                }
                submit(newlist)
            }
        })

        viewModel.itemDetail.observe(viewLifecycleOwner, Observer {
            // ? 的用意
            it?.apply {
                Log.i(TAG, "initobserver: itemDetail " + it.name)
                activityViewModel.headerTitle.value = "from ViewPager " + it.name

                val navController = findNavController()
                when (it.name) {
                    "one" -> {
//                        共享元件 畫面
//                        sharedElementEnterTransition
//                        TransitionInflater.from(context).inflateTransition(android.R.transition.move)
                        navController.navigate(R.id.action_host_fragment_to_one_fragment)
                        activityViewModel.headerBack.value = true
                        Log.i(TAG, "initobserver headerBack: " + activityViewModel.headerBack.value)
                    }
                    "two" -> {
                        navController.navigate(R.id.two_fragment)
                        activityViewModel.headerBack.value = true
                        Log.i(TAG, "initobserver headerBack: " + activityViewModel.headerBack.value)
                    }
                    "three" -> {
                        // 多層 Fragment，要看要取代的位置去找他的 FragmentManager
                        activityViewModel.headerBack.value = true
                        parentFragment?.parentFragment?.childFragmentManager?.beginTransaction()
                            ?.apply {
                                /** 切換畫面的動畫
                                 * setCustomAnimations(enter, exit, popEnter, popExit)
                                 * 正常執行 下一個 View enter, 當前 View exit
                                 * 返回鍵執行 popEnter, popExit
                                 * */
                                setCustomAnimations(
                                    R.anim.slide_enter_from_right,
                                    R.anim.slide_exit_to_left,
                                    R.anim.slide_enter_from_left,
                                    R.anim.slide_exit_to_right
                                )
                                add(R.id.layout_out_fragment, ThreeFragment())
                                addToBackStack(null)
                            }?.commit()
                    }
                    "four" -> {
                        Intent(Intent.ACTION_SENDTO).apply {
                            var uriText = "mailto:" + Uri.encode("somebody@gmail.com") +
                                    "?subject" + Uri.encode("標題") +
                                    "&body" + Uri.encode("內容")
                            this.setData(Uri.parse(uriText))
                            startActivity(this)
                        }
                    }
                    "five" -> {
                        Intent(Intent.ACTION_VIEW).apply {
                            this.setData(Uri.parse("fb-messenger://user/107125337319534"))
                            try {
                                startActivity(this)
                            } catch (e: ActivityNotFoundException) {
                                Timber.d("ActivityNotFoundException: $e")
                            }
                        }
                    }

                    "six" ->{
                        // Entry(Float, Float)
                        val dataOne : ArrayList<Entry> = arrayListOf(Entry(1f,5f),Entry(2f,7f),Entry(3f,3f))
                        val dataTwo : ArrayList<Entry> = arrayListOf(Entry(1f,2f),Entry(2f,4f),Entry(3f,6f))

                        binding.lineChart.apply {
                            // axisRight, axisLeft 左右邊的Y軸設定
                            this.axisRight.isEnabled = false // 設定顯示右邊Y軸

                            this.xAxis.let { //設定 X軸
                                it.position = XAxis.XAxisPosition.BOTTOM //將x軸表示字移到底下
                                it.setLabelCount(10, false) //設定X軸上要有幾個標籤
                            }
                            val set1 = LineDataSet(dataOne, "one").apply {
                                this.mode = LineDataSet.Mode.LINEAR //類型為折線
                                this.color = context.getColor(R.color.aqua) //線的顏色
                                this.lineWidth = 1.5f //線寬
                                this.setDrawCircles(true) //不顯示相應座標點的小圓圈(預設顯示)
                                this.setDrawValues(false) //不顯示座標點對應Y軸的數字(預設顯示)
                            }
                            val set2 = LineDataSet(dataTwo, "two").apply {
                                this.mode = LineDataSet.Mode.LINEAR //類型為折線
                                this.color = context.getColor(R.color.brown) //線的顏色
                                this.lineWidth = 1.5f //線寬
                                this.setDrawCircles(true) //不顯示相應座標點的小圓圈(預設顯示)
                                this.setDrawValues(false) //不顯示座標點對應Y軸的數字(預設顯示)
                            }

                            this.description.apply { // 設定圖表內標籤
                                this.text = "表內標籤"
                                this.textSize = 14f
                                this.textColor = context.getColor(R.color.red)
                                this.setPosition(800f,100f) //顯示位置座標 (預設右下方)
                            }

                            this.setDrawBorders(true)//顯示邊框 (預設不顯示)
                            this.setBorderColor(context.getColor(R.color.dark_salmon))
                            this.setBorderWidth(5f)

                            this.data = LineData(set1,set2)
                            this.invalidate() //繪製圖表
                        }

                    }
                    "seven" ->{

                    }
                }
            }
        })

    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "onResume: ")
    }

    override fun onStop() {
        super.onStop()
        Log.i(TAG, "onStop: ")
    }

}
