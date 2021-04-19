package com.mua.filesbymaifee.viewmodel

import android.app.Application
import androidx.core.content.ContextCompat.getExternalFilesDirs
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.io.File

class FilesViewModel(application: Application) : AndroidViewModel(application) {

    private val _files: MutableLiveData<Array<File>> = MutableLiveData(arrayOf())
    val files: LiveData<Array<File>> = _files

    init {
        _files.postValue(mounts())
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

}