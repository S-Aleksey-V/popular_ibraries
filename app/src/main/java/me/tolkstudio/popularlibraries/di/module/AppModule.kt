package me.tolkstudio.popularlibraries.di.module

import dagger.Module
import dagger.Provides
import me.tolkstudio.popularlibraries.ui.App

@Module
class AppModule(val app: App) {

    @Provides
    fun app(): App = app
}