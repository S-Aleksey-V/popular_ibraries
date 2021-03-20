package me.tolkstudio.popularlibraries.mvp.presenter

import com.github.terrakok.cicerone.Router
import me.tolkstudio.popularlibraries.mvp.navigation.IScreens
import me.tolkstudio.popularlibraries.mvp.view.MainView
import moxy.MvpPresenter

class MainPresenter(val router: Router, val screens: IScreens) : MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(screens.users())
    }

    fun bacClicked() {
        router.exit()
    }
}
