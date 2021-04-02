package me.tolkstudio.popularlibraries.di.module

import androidx.room.Room
import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import me.tolkstudio.popularlibraries.mvp.model.cache.IGithubRepositoriesCache
import me.tolkstudio.popularlibraries.mvp.model.cache.IGithubUsersCache
import me.tolkstudio.popularlibraries.mvp.model.cache.IImageCache
import me.tolkstudio.popularlibraries.mvp.model.cache.room.RoomGithubRepositoriesCache
import me.tolkstudio.popularlibraries.mvp.model.cache.room.RoomGithubUsersCache
import me.tolkstudio.popularlibraries.mvp.model.cache.room.RoomImageCache
import me.tolkstudio.popularlibraries.mvp.model.entity.room.db.Database
import me.tolkstudio.popularlibraries.ui.App
import java.io.File
import javax.inject.Named
import javax.inject.Singleton

@Module
class CacheModule {

    @Singleton
    @Provides
    fun database(app: App) =
        Room.databaseBuilder(app, Database::class.java, Database.DB_NAME).build()

    @Singleton
    @Provides
    fun usersCache(db: Database): IGithubUsersCache = RoomGithubUsersCache(db)

    @Named("ui")
    @Provides
    fun uiScheduler(): Scheduler = AndroidSchedulers.mainThread()

    @Singleton
    @Provides
    fun imageCache(database: Database,@Named("cacheDir") cacheDir: File): IImageCache =
        RoomImageCache(database, cacheDir)

    @Singleton
    @Provides
    fun repositoriesCache(database: Database): IGithubRepositoriesCache = RoomGithubRepositoriesCache(database)
}