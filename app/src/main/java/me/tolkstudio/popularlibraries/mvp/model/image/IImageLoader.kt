package me.tolkstudio.popularlibraries.mvp.model.image

interface IImageLoader<T> {
    fun load(url: String, container: T)
}