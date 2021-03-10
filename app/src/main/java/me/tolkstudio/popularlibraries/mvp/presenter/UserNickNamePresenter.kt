package me.tolkstudio.popularlibraries.mvp.presenter

import com.github.terrakok.cicerone.Router
import me.tolkstudio.popularlibraries.mvp.model.GithubUsersRepo
import me.tolkstudio.popularlibraries.mvp.model.entity.GithubUser
import me.tolkstudio.popularlibraries.mvp.view.UserNickNameView
import me.tolkstudio.popularlibraries.ui.fragment.NicknameFragment
import moxy.MvpPresenter

class UserNickNamePresenter(val user: GithubUser, val router: Router) :
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
