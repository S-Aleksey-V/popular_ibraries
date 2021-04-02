package me.tolkstudio.popularlibraries.ui

import android.app.Application
import me.tolkstudio.popularlibraries.di.AppComponent
import me.tolkstudio.popularlibraries.di.DaggerAppComponent
import me.tolkstudio.popularlibraries.di.module.AppModule
import me.tolkstudio.popularlibraries.mvp.model.entity.room.db.Database

class App : Application() {

    companion object {
        lateinit var instance: App
    }

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        Database.create(this)

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
}