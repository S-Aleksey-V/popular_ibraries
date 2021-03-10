package me.tolkstudio.popularlibraries.mvp.presenter.list

interface ILIstPresenter<V> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}