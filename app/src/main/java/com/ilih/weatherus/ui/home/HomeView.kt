package com.ilih.weatherus.ui.home

import com.ilih.weatherus.ui.common.BaseView

interface HomeView: BaseView {

    fun onFinishInflate(listener: Listener, targetCity: Long)
    fun refresh()

    interface Listener{
        fun onRefreshClicked()
        fun inflated()
    }

}