package me.tolkstudio.popularlibraries.mvp.presenter

import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import me.tolkstudio.popularlibraries.mvp.model.entity.GitHubRepo

import me.tolkstudio.popularlibraries.mvp.model.entity.GithubUser
import me.tolkstudio.popularlibraries.mvp.model.repo.IGitHubUserRepos
import me.tolkstudio.popularlibraries.mvp.navigation.IScreens
import me.tolkstudio.popularlibraries.mvp.presenter.list.IUserRepoListPresenter
import me.tolkstudio.popularlibraries.mvp.view.USersInfoView
import me.tolkstudio.popularlibraries.mvp.view.list.IUserReposItemView
import moxy.MvpPresenter

class UserInfoPresenter(
    val uiScheduler: Scheduler,
    val router: Router,
    val user: GithubUser,
    val repos: IGitHubUserRepos,
    val screen: IScreens
) :
    MvpPresenter<USersInfoView>() {

    class UserReposListPresenter : IUserRepoListPresenter {

        val repos = mutableListOf<GitHubRepo>()
        override var itemClickListener: ((IUserReposItemView) -> Unit)? = null

        override fun bindView(view: IUserReposItemView) {
            val repos = repos[view.pos]
            repos.name.let { view.setNameRepos(it) }
        }

        override fun getCount() = repos.size
    }

    val userReposListPresenter = UserReposListPresenter()
    val compositeDisposable = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        viewState.setLogin(user.login)
        viewState.setImage(user.avatarUrl)
        loadRepos()

        userReposListPresenter.itemClickListener = { view ->
            val user = userReposListPresenter.repos[view.pos]
            router.navigateTo(screen.fork(user))
        }

    }

    private fun loadRepos() {
        val disposable = repos.getRepos(user)
            .observeOn(uiScheduler)
            .subscribe({ reposList ->
                userReposListPresenter.repos.addAll(reposList)
                viewState.updateReposList()
            }, { error ->
                error.printStackTrace()
            })
        compositeDisposable.add(disposable)
    }

    fun backClick(): Boolean {
        router.exit()
        return true
    }
}