package com.mua.filesbymaifee.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mua.filesbymaifee.R
import com.mua.filesbymaifee.databinding.FragmentSplashBinding
import com.mua.filesbymaifee.viewmodel.SplashViewModel

class SplashFragment : BaseFragment<FragmentSplashBinding, SplashViewModel>() {

    override val viewModel: SplashViewModel by viewModels()

    override fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentSplashBinding.inflate(inflater, container, false)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        findNavController().navigate(R.id.action_splashFragment_to_filesFragment)
        return view
    }
}