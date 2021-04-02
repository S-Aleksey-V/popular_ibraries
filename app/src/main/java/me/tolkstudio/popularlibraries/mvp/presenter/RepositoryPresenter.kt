package me.tolkstudio.popularlibraries.mvp.presenter

import com.github.terrakok.cicerone.Router
import me.tolkstudio.popularlibraries.mvp.model.entity.GitHubRepo
import me.tolkstudio.popularlibraries.mvp.navigation.IScreens

import me.tolkstudio.popularlibraries.mvp.view.RepoInfoView
import moxy.MvpPresenter
import javax.inject.Inject

class RepositoryPresenter( val githubRepository: GitHubRepo) :
    MvpPresenter<RepoInfoView>() {


    @Inject lateinit var router: Router

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        viewState.setId(githubRepository.id ?: "")
        viewState.setTitle(githubRepository.name ?: "")
        viewState.setForksCount(githubRepository.forksCount ?: "")
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}