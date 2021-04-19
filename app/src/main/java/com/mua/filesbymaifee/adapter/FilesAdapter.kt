package com.mua.filesbymaifee.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mua.filesbymaifee.databinding.ItemFileFilesFragmentBinding
import com.mua.filesbymaifee.listener.FileClickListener
import java.io.File


class FilesAdapter() : RecyclerView.Adapter<FilesAdapter.TransactionVH>() {

    inner class TransactionVH(val binding: ItemFileFilesFragmentBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionVH {
        val binding =
            ItemFileFilesFragmentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TransactionVH(binding)
    }

    var files: Array<File> = arrayOf()
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

    override fun onBindViewHolder(holder: TransactionVH, position: Int) {
        val file = files[position]
        holder.binding.apply {
            tvItemFileFilesFragment.text = file.name
            if (::onItemClickListener.isInitialized) {
                holder.itemView.setOnClickListener {
                    onItemClickListener.onClick(file)
                }
            }
        }
    }

}