package com.mua.filesbymaifee.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mua.filesbymaifee.R
import com.mua.filesbymaifee.databinding.ItemFileFilesFragmentBinding
import com.mua.filesbymaifee.listener.FileClickListener
import java.io.File
import java.util.*


class FilesAdapter() : RecyclerView.Adapter<FilesAdapter.FilesVH>() {

    inner class FilesVH(val binding: ItemFileFilesFragmentBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilesVH {
        val binding =
            ItemFileFilesFragmentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FilesVH(binding)
    }

    var files: Array<String> = arrayOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    private lateinit var onItemClickListener: FileClickListener

    constructor(onItemClickListener: FileClickListener) : this() {
        this.onItemClickListener = onItemClickListener
    }

    override fun getItemCount(): Int {
        return files.size
    }

    override fun onBindViewHolder(holder: FilesVH, position: Int) {
        val filePath = files[position]
        val file = File(filePath)
        holder.binding.apply {
            tvItemFileName.text = file.name
            tvItemFileLastModified.text = Date(file.lastModified()).toString()
            if (file.isDirectory) {
                ivItemFileIcon.setImageResource(R.drawable.icon_file_folder)
            } else {
                val filePath = file.absolutePath
                val lastDotIndex = filePath.lastIndexOf(".")
                var extension= ""
                if(lastDotIndex>0)
                    extension= filePath.substring(lastDotIndex)
                if (extension == ".mp3" || extension == ".wav") {
                    ivItemFileIcon.setImageResource(R.drawable.icon_file_audio)
                } else if (extension == ".pdf") {
                    ivItemFileIcon.setImageResource(R.drawable.icon_file_pdf)
                } else if (extension == ".mp4" || extension == ".gif") {
                    ivItemFileIcon.setImageResource(R.drawable.icon_file_video)
                } else {
                    ivItemFileIcon.setImageResource(R.drawable.icon_file_file)
                }
            }
            if (::onItemClickListener.isInitialized) {
                holder.itemView.setOnClickListener {
                    onItemClickListener.onClick(filePath)
                }
            }
        }
    }

}