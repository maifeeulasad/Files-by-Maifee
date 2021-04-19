package com.mua.filesbymaifee.viewmodel

import android.app.Application
import androidx.core.content.ContextCompat.getExternalFilesDirs
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.io.File

class FilesViewModel(application: Application) : AndroidViewModel(application) {

    private val _files: MutableLiveData<Array<String>> = MutableLiveData(arrayOf())
    val files: LiveData<Array<String>> = _files

    private var mounts: List<String> = listOf()

    init {
        mounts = mounts()
        _files.postValue(mounts.toTypedArray())
    }

    private fun mounts(): List<String> {
        val res: MutableList<String> = ArrayList()
        val externalStorageFiles =
            getExternalFilesDirs(getApplication(), null)
        val base = "/Android/data/" + getApplication<Application>().packageName + "/files"
        for (file in externalStorageFiles) {
            if (file != null) {
                val path = file.absolutePath
                if (path.contains(base)) {
                    val finalPath = path.replace(base, "")
                    res.add(finalPath)
                }
            }
        }
        return res
    }

    fun listChildren(filePath: String) {
        val file = File(filePath)
        if (!file.isDirectory) {
            return
        }
        val res: MutableList<String> = ArrayList()
        file.listFiles()?.forEach {
            res.add(it.absolutePath)
        }
        _files.postValue(res.toTypedArray())
    }

    fun navigateUp() {
        if (files.value?.size!! > 0) {
            files.value?.forEach {
                if (mounts.contains(it))
                    return
            }
            val fileParent = File(files.value?.get(0)).parentFile
            val fileParentParent = fileParent?.parentFile
            if (!mounts.contains(fileParent.absolutePath)) {
                fileParentParent?.absolutePath?.let { listChildren(it) }
            } else {
                _files.postValue(mounts.toTypedArray())
            }
        }
    }

}