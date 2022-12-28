package com.llc.realtimechat

interface Observer<T> {

    fun observe(newValue: T)
}