package com.example.demovm.mainFunctionName.accountinfo

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.demovm.base.BaseDaggerFragment
import com.example.demovm.databinding.AccountInfoFragmentBinding

private const val TAG = "AccountInfoFragment"

class AccountInfoFragment() : BaseDaggerFragment() {

    val activityViewModel by activityViewModels<AccountInfoViewModel> { viewModelFactory }
    override val viewModel by viewModels<AccountInfoFragmentViewModel> { viewModelFactory }
    override lateinit var binding: AccountInfoFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = AccountInfoFragmentBinding.inflate(inflater, container, false).apply {
            this.viewModel = this@AccountInfoFragment.viewModel
            this.activityViewModel = this@AccountInfoFragment.activityViewModel
        }

        initObserver()
        return binding.root

    }

    private fun initObserver() {
        viewModel.editAccount.observe(this, Observer {
            if (!binding.etAccount.text.isNullOrEmpty())
                activityViewModel.account.value = binding.etAccount.text.toString()
        })
        viewModel.editName.observe(this, Observer {
            if (!binding.etName.text.isNullOrEmpty())
                activityViewModel.name.value = binding.etName.text.toString()
        })
        viewModel.editEmail.observe(this, Observer {
            if (!binding.etEmail.text.isNullOrEmpty())
                activityViewModel.email.value = binding.etEmail.text.toString()
        })
    }
}