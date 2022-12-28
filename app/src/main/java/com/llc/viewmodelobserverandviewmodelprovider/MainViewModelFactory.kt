package com.llc.viewmodelobserverandviewmodelprovider

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.llc.realtimechat.MainViewModel

class MainViewModelFactory: ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

       // if(modelClass.name=="...")
        return MainViewModel() as T
    }
}