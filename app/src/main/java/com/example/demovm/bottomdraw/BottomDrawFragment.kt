package com.example.demovm.bottomdraw

import android.os.BaseBundle
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.viewModels
import com.example.demovm.base.BaseDaggerFragment
import com.example.demovm.databinding.BottomDrawFragmentBinding
import dagger.android.support.DaggerFragment

class BottomDrawFragment : BaseDaggerFragment() {

    companion object {
        const val BOT_DRAW_TAB_ITEM = "BOT_DRAW_TAB_ITEM"

        fun newInstance(id: String): BottomDrawFragment {
            return BottomDrawFragment().apply {
                arguments = Bundle().apply {
                    putString(BOT_DRAW_TAB_ITEM, id)
                }
            }
        }
    }

    override val viewModel by viewModels<BottomDrawTabViewModel> { viewModelFactory }

    override lateinit var binding: BottomDrawFragmentBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = BottomDrawFragmentBinding.inflate(inflater, container, false).apply {
            this.viewModel = this@BottomDrawFragment.viewModel
        }

        binding.txtUuu.text = arguments?.getString(BOT_DRAW_TAB_ITEM,"")

        return binding.root
    }
}