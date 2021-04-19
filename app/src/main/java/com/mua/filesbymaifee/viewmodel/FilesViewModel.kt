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
        mounts = mounts().map {
            it.absolutePath
        }
        _files.postValue(mounts.toTypedArray())
    }

    private fun mounts(): Array<File> {
        val res: MutableList<File> = ArrayList()
        val externalStorageFiles =
            getExternalFilesDirs(getApplication(), null)
        val base = "/Android/data/" + getApplication<Application>().packageName + "/files"
        for (file in externalStorageFiles) {
            if (file != null) {
                val path = file.absolutePath
                if (path.contains(base)) {
                    val finalPath = path.replace(base, "")
                    res.add(File(finalPath))
                }
            }
        }
        return res.toTypedArray()
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
        /*
        if (files.value?.get(0)?.parent != null) {
            listChildren(File(File(files.value!![0].parent).parent))
        }
        */
    }

}