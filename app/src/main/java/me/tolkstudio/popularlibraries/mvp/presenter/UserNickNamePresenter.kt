package me.tolkstudio.popularlibraries.mvp.presenter

import com.github.terrakok.cicerone.Router
import me.tolkstudio.popularlibraries.mvp.view.UserNickNameView
import moxy.MvpPresenter

class UserNickNamePresenter(val router: Router) :
    MvpPresenter<UserNickNameView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
    }

    fun backClick(): Boolean {
        router.exit()
        return true
    }
}
