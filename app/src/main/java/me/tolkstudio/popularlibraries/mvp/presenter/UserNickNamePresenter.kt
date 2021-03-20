package me.tolkstudio.popularlibraries.mvp.presenter

import com.github.terrakok.cicerone.Router
import me.tolkstudio.popularlibraries.mvp.model.GithubUsersRepo
import me.tolkstudio.popularlibraries.mvp.model.entity.GithubUser
import me.tolkstudio.popularlibraries.mvp.view.UserNickNameView
import me.tolkstudio.popularlibraries.ui.fragment.NicknameFragment
import moxy.MvpPresenter

class UserNickNamePresenter(val router: Router, val user: GithubUser) :
    MvpPresenter<UserNickNameView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setLogin(user.login)
    }

    fun backClick(): Boolean {
        router.exit()
        return true
    }
}
