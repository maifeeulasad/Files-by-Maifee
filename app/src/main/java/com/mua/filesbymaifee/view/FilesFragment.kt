package com.mua.filesbymaifee.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.mua.filesbymaifee.databinding.FragmentFilesBinding
import com.mua.filesbymaifee.viewmodel.FilesViewModel

class FilesFragment : BaseFragment<FragmentFilesBinding, FilesViewModel>() {

    override val viewModel: FilesViewModel by viewModels()

    override fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentFilesBinding.inflate(inflater, container, false)

    init {

    }
}