package me.tolkstudio.popularlibraries.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface USersInfoView : MvpView {
    fun init()
    fun updateReposList()
    fun setLogin(text: String)
    fun setImage(url: String)
}