package me.tolkstudio.popularlibraries.mvp.presenter

import com.github.terrakok.cicerone.Router
import me.tolkstudio.popularlibraries.mvp.model.entity.GitHubRepo

import me.tolkstudio.popularlibraries.mvp.view.RepoInfoView
import moxy.MvpPresenter

class RepositoryPresenter(val router: Router, val githubRepository: GitHubRepo) :
    MvpPresenter<RepoInfoView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        viewState.setId(githubRepository.id ?: "")
        viewState.setTitle(githubRepository.name ?: "")
        viewState.setForksCount(githubRepository.forksCount ?: "")
    }

    fun backClick(): Boolean {
        router.exit()
        return true
    }
}