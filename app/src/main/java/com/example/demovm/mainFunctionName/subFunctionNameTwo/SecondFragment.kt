package com.example.demovm.mainFunctionName.subFunctionNameTwo

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demovm.AlertDialogFragment
import com.example.demovm.EventObserver
import com.example.demovm.InterfaceDialog
import com.example.demovm.base.BaseDaggerFragment
import com.example.demovm.databinding.SecondFragmentBinding
import kotlinx.android.synthetic.main.layout_bottom_drawe.view.*

private const val TAG = "SecondFragment"

class SecondFragment : BaseDaggerFragment() {

    override val viewModel by viewModels<SecondFragmentViewModel> { viewModelFactory }
    override lateinit var binding: SecondFragmentBinding
    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.i(TAG, "onAttach: ")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "onCreate: ")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i(TAG, "onCreateView: ")

        binding = SecondFragmentBinding.inflate(inflater, null, false).apply {
            this.viewModel = this@SecondFragment.viewModel
        }

        binding.botDraLinearLayout.visibility = View.VISIBLE
        binding.botDraLinearLayout.setViewModel(viewModel)
        binding.botDraLinearLayout.setViewPagerAdapter(this.childFragmentManager)

        initObserver()
        initBottomDrawLayout()

        return binding.root
    }

    private fun initBottomDrawLayout() {
        /** 觀察狀態選擇要顯示還是關閉的動畫
         *  false : 不顯示 Layout
         *  true : 顯示 Layout
         * */
        viewModel.bottomOpenState.observe(this, Observer {
            if (it) {
                Log.i(TAG, "initBottomDrawLayout 開起狀態 / 關閉狀態 -> 開起狀態 : $it")
                // 執行開啟的動畫
                binding.botDraLinearLayout.postOnAnimation {
                    //(target: View, propertyName: String, values: float... ))
                    /** ProprtyName 基本參數
                     * 1. 透明度
                     *  alpha
                     * 2. 直線平移
                     *  translationX :
                     *  translationY :
                     * 3. 旋轉度
                     *  rotationX ：繞 X 軸旋轉，水平線
                     *  rotationY : 繞 Y 軸旋轉，垂直線
                     *  rotation : 繞 Z 軸旋轉，XY面的垂直線
                     * 4. 縮放倍數
                     *  scaleX
                     *  scaleY
                     * */
                    ObjectAnimator.ofFloat(
                        binding.botDraLinearLayout,
                        "translationY",
                        - binding.botDraLinearLayout.height.toFloat() + binding.space.height
                    )
                        .apply {
                            //動畫時間長度
                            duration = 2000
                            //開始執行動畫
                            start()
                        }

                }

                binding.botDraLinearLayout.postOnAnimation {
                    ObjectAnimator.ofFloat(
                        binding.botDraLinearLayout,
                        "translationX",
                        0f, -50f, 0f, 50f, 0f, -50f, 0f, -50f, 0f, 50f, 0f, -50f, 0f
                    ).apply {
                            duration = 1000
                            start()
                        }
                }
            } else {
                Log.i(TAG, "initBottomDrawLayout 關閉狀態 / 開起狀態 -> 關閉狀態 : $it")
                // 執行關閉的動畫
                binding.botDraLinearLayout.postOnAnimation {
                    ObjectAnimator.ofFloat(binding.botDraLinearLayout, "translationY", 0f)
                        .apply {
                            duration = 1000
                            start()
                        }
                }
            }
        })
    }

    fun initObserver() {
        /**
         * AlertDialog 視窗
         * setContent : 設定 body
         * 透過 FragmentManager 顯示(show) Fragment
         */
        viewModel.alertDialog.observe(this, Observer {

            Log.i(TAG, "onCreateView: alertDialog ")
            AlertDialogFragment(activity as Context)
                .setTitle("Title")
                .setContent("iiiii")
                .setOnConfirmClickListener(object : InterfaceDialog.OnClickListener {
                    override fun onClick(it: InterfaceDialog) {
                        Log.i(TAG, "OnConfirmClick: ")
                        it.dismiss()
                    }
                })
                .show(fragmentManager!!, "")

//            var fragment = AlertDialogFragment(activity as Context,"ttt")
//            fragment
//                .setContent("iiiii")
//                // Interface … does not have constructors
//                .onClick(fragment)
//            fragment
//                .show(fragmentManager!!,"")
        })

        viewModel.alertDialogEvent.observe(this, EventObserver {
            Log.i(TAG, "onCreateView: alertDialogEvent ")
            AlertDialogFragment(activity as Context)
                .setTitle("EventObserver")
                .setContent(
                    "透過 Event 包裝 LiveData\n"
                            + "而Observer Event 本身，讓觀察者僅看到 Event 本身，而不會直接知道內部資料是否更新\n"
                            + "避免在載入資料/初始化時執行或重複執行 Observer 的資料"
                )
                .setOnConfirmClickListener(object : InterfaceDialog.OnClickListener {
                    override fun onClick(it: InterfaceDialog) {
                        Log.i(TAG, "OnConfirmClick: ")
                        it.dismiss()
                    }
                })
                .show(fragmentManager!!, "")
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "onDestroy: ")
    }

    override fun onStop() {
        super.onStop()
        Log.i(TAG, "onStop: ")
    }
}