package com.mua.filesbymaifee.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.mua.filesbymaifee.adapter.FilesAdapter
import com.mua.filesbymaifee.databinding.FragmentFilesBinding
import com.mua.filesbymaifee.listener.FileClickListener
import com.mua.filesbymaifee.viewmodel.FilesViewModel
import java.io.File

class FilesFragment : BaseFragment<FragmentFilesBinding, FilesViewModel>(), FileClickListener {

    override val viewModel: FilesViewModel by viewModels()
    private lateinit var filesAdapter: FilesAdapter

    override fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentFilesBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRV()
        observeRV()

        initUp()
    }

    private fun initRV() = with(binding) {
        filesAdapter = FilesAdapter(this@FilesFragment)
        rvFiles.apply {
            adapter = filesAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    private fun observeRV(){
        viewModel.files.observe(viewLifecycleOwner, Observer {
            filesAdapter.files = it
        })
    }

    private fun initUp(){
        binding.btnUp.setOnClickListener {
            viewModel.navigateUp()
        }
    }

    override fun onClick(filePath: String) {
        viewModel.listChildren(filePath)
    }

}