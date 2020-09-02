package com.example.demovm.mainFunctionName.subFunctionNameOne

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.demovm.base.BaseDaggerFragment
import com.example.demovm.data.source.local.imagebanner.ImageBanner
import com.example.demovm.databinding.MainFragmentBinding
import com.example.demovm.mainFunctionName.FunctionNameMainActivityViewModel
import com.example.demovm.mainFunctionName.subFunctionNameOne.banner.BaseBannerIndicator
import com.example.demovm.mainFunctionName.subFunctionNameOne.banner.CircleIndicator
import com.example.demovm.mainFunctionName.subFunctionNameOne.banner.ImageBannerAdapter
import com.youth.banner.Banner
import com.youth.banner.config.BannerConfig
import com.youth.banner.config.IndicatorConfig
import com.youth.banner.indicator.Indicator


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
        initial()

        return binding.root
    }

    private fun initial() {
        //元件初始化
//        binding.btnRoom.setOnClickListener (View.OnClickListener {
//            viewModel.RoomDemo()
//        })
//
//        binding.btnDag.setOnClickListener (View.OnClickListener {
//            viewModel.btnDag_click()
//        })
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
        binding.ivYouBanner.setIndicator(indicator ,true)
        //設定畫廊模式 setBannerGalleryEffect( int 左邊顯示寬度, int 右邊顯示寬度, int 自身顯示寬度)
//        binding.ivYouBanner.setBannerGalleryEffect(30, 10, 50)
        //設定凸顯模式(魅族?) setBannerGalleryMZ (左右顯示寬度, 縮小比例)
        binding.ivYouBanner.setBannerGalleryMZ(30,.7f)
    }

}