package com.example.demovm.mainFunctionName.subFunctionNameOne

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demovm.base.BaseDaggerFragment
import com.example.demovm.data.source.local.imagebanner.ImageBanner
import com.example.demovm.databinding.MainFragmentBinding
import com.example.demovm.mainFunctionName.FunctionNameMainActivityViewModel
import com.example.demovm.mainFunctionName.subFunctionNameOne.banner.CircleIndicator
import com.example.demovm.mainFunctionName.subFunctionNameOne.banner.ImageBannerAdapter
import com.google.android.material.appbar.AppBarLayout.OnOffsetChangedListener
import com.youth.banner.Banner

private const val TAG = "MainFragment"

class MainFragment : BaseDaggerFragment() {

    // override 在 BaseDaggerFragment 的參數
    override val viewModel by viewModels<MainFragmentViewModel> { viewModelFactory }

    val activityViewModel by activityViewModels<FunctionNameMainActivityViewModel> { viewModelFactory }

    override lateinit var binding: MainFragmentBinding

//    private lateinit var activityViewModel: SecondActivityViewModel
//    private lateinit var viewModel: MainFragmentViewModel

//    val viewModel by viewModels<MainFragmentViewModel> {ViewModelProviders.Fac}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

//        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(Application())).get(MainFragmentViewModel::class.java)
//        viewModel = ViewModelProviders.of(this).get(MainFragmentViewModel::class.java)
//        activityViewModel = ViewModelProviders.of(this).get(SecondActivityViewModel::class.java)

        binding = MainFragmentBinding.inflate(inflater, null, false).apply {
            this.viewModel = this@MainFragment.viewModel
        }

        initObserver()
        initBanner()
        initScroll()
        gotoTop()

        return binding.root
    }

    //畫面滑動監聽 1.AppBarLaout 2.Nestesd Scroll View
    private fun initScroll() {

        // OnOffsetChangedListener(View: AppBarLayout, scrollY: Int)
        binding.appBar.addOnOffsetChangedListener(OnOffsetChangedListener { appBarLayout, verticalY ->
            /** 自訂義三種狀態，操作AppBar， verticalY 為 View 的 Y 軸位移量，向上為負數
             *  1.完全展開 verticalY = 0
             *  2.完全折疊 verticalY = -總高
             *  3.中間狀態 -總高 < verticalY < 0
             *  */
            val totalScrollRange = appBarLayout.totalScrollRange
            if (verticalY == 0) {
                Log.i(TAG, "appBarLayout 完全展開: $verticalY")
                viewModel.gotoTopEvent.value = false
            } else if (verticalY == (-1 * totalScrollRange)) {
                Log.i(TAG, "appBarLayout 完全折疊: $verticalY")
            } else {
                Log.i(TAG, "appBarLayout 中間狀態: $verticalY")
                viewModel.gotoTopEvent.value = true
            }
        })
        /**
         * Called when the scroll position of a view changes.
         *
         * @param v The view whose scroll position has changed.
         * @param scrollX Current horizontal scroll origin.
         * @param scrollY Current vertical scroll origin.
         * @param oldScrollX Previous horizontal scroll origin.
         * @param oldScrollY Previous vertical scroll origin.
         */
        binding.nestedSv.setOnScrollChangeListener { view: NestedScrollView?, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int ->
            Log.i(TAG, "initScroll: y: " + scrollY + "old y: " + oldScrollY)
            if (scrollY > 0)
                viewModel.gotoTopEvent.value = true
            else
                viewModel.gotoTopEvent.value = false
        }
    }

    private fun gotoTop() {
        binding.goToTop.setOnClickListener(View.OnClickListener {
            Log.i(TAG, "gotoTop: ")
            // 指定位置
            binding.nestedSv.scrollTo(0, 0)
            // 最上面或最下面
//            binding.nestedSv.fullScroll(View.FOCUS_UP)  //View.FOCUS_DOWN
            binding.appBar.setExpanded(true)
        })
    }

    private fun initObserver() {

        viewModel.roomChange.observe(this, Observer { webdata ->
            WebDataItemAdapter().apply {
                val linearLayout =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                binding.rvWebData.layoutManager = linearLayout
                binding.rvWebData.adapter = this

                this.setViewModel(viewModel)
                this.sumbit(webdata)
            }
            Log.i("DBDATA: ", "${viewModel.roomChange.value}")
        })

        viewModel.txtDag.observe(this, Observer {
            binding.txtDag.text = it
        })

    }

    private fun initBanner() {
        var ivList = ArrayList<ImageBanner>()
        ivList.add(ImageBanner(imgName = "one", imgURL = "one"))
        ivList.add(ImageBanner(imgName = "two", imgURL = "two"))
        ivList.add(ImageBanner(imgName = "three", imgURL = "three"))
        ivList.add(ImageBanner(imgName = "four", imgURL = "four"))
        var YBadapter = ImageBannerAdapter(ivList, viewModel)
        binding.ivYouBanner.adapter = YBadapter
        //停留時間，預設 3000毫秒
        binding.ivYouBanner.setLoopTime(1000)
        //切換方向，預設水平 Banner.HORIZONTAL Banner.VERTICAL
        binding.ivYouBanner.setOrientation(Banner.HORIZONTAL)
        //
        var indicator = CircleIndicator(context)
        binding.ivYouBanner.setIndicator(indicator, true)
        //設定畫廊模式 setBannerGalleryEffect( int 左邊顯示寬度, int 右邊顯示寬度, int 自身顯示寬度)
//        binding.ivYouBanner.setBannerGalleryEffect(30, 10, 50)
        //設定凸顯模式(魅族?) setBannerGalleryMZ (左右顯示寬度, 縮小比例)
        binding.ivYouBanner.setBannerGalleryMZ(30, .7f)
    }

}