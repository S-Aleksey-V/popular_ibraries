package me.tolkstudio.popularlibraries.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface RepoInfoView : MvpView {
    fun updateReposList()
    fun setLogin(text: String)
    fun setWatchers(text: String)
    fun setForks(text: String)
    fun setDefaultBranch(text: String)
}