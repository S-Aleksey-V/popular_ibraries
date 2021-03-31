package me.tolkstudio.popularlibraries.mvp.view.list

interface IUserItemView : ItemView {
    fun setLogin(text: String)
    fun loadAvatar(url: String)
}