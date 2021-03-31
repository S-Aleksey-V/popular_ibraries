package me.tolkstudio.popularlibraries.mvp.view.list

interface UserItemView : ItemView {
    fun setLogin(text: String)
    fun loadAvatar(url: String)
}