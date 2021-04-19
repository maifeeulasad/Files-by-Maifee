package com.mua.filesbymaifee.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.mua.filesbymaifee.databinding.FragmentSplashBinding
import com.mua.filesbymaifee.viewmodel.SplashViewModel

class SplashFragment : BaseFragment<FragmentSplashBinding, SplashViewModel>() {

    override val viewModel: SplashViewModel by viewModels()

    override fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentSplashBinding.inflate(inflater, container, false)

    init {

    }
}