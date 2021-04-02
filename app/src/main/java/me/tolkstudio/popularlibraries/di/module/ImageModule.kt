package me.tolkstudio.popularlibraries.di.module

import android.widget.ImageView
import dagger.Module
import dagger.Provides
import me.tolkstudio.popularlibraries.mvp.model.cache.IImageCache
import me.tolkstudio.popularlibraries.mvp.model.image.IImageLoader
import me.tolkstudio.popularlibraries.mvp.model.network.INetworkStatus
import me.tolkstudio.popularlibraries.ui.App
import me.tolkstudio.popularlibraries.ui.image.GlideImageLoader
import java.io.File
import javax.inject.Named
import javax.inject.Singleton

@Module
class ImageModule {

    @Named("cacheDir")
    @Singleton
    @Provides
    fun cacheDir(app: App): File = app.cacheDir

    @Singleton
    @Provides
    fun imageLoader(cache: IImageCache, networkStatus: INetworkStatus): IImageLoader<ImageView> =
        GlideImageLoader(cache,networkStatus)
}