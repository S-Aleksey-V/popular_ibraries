package me.tolkstudio.popularlibraries.ui

import android.app.Application
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import me.tolkstudio.popularlibraries.mvp.model.entity.room.db.Database

class App : Application() {


    private val cicerone: Cicerone<Router> by lazy {
        Cicerone.create()
    }

    val navigatorHolder get() = cicerone.getNavigatorHolder()
    val router get() = cicerone.router

    companion object {
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        Database.create(this)
    }
}