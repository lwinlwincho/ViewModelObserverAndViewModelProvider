package com.llc.realtimechat

class Observable<T> {

    private val observeSet = mutableSetOf<Observer<T>>()

    fun attchObserver(observer: Observer<T>){
        this.observeSet.add(observer)
    }

    fun detchObserver(observer: Observer<T>){
        this.observeSet.remove(observer)
    }

    fun emit(value:T){
        observeSet.forEach{
            it.observe(value)
        }
    }

}