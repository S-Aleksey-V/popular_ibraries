package me.tolkstudio.popularlibraries.mvp.presenter

import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import me.tolkstudio.popularlibraries.mvp.model.entity.GitHubRepo
import me.tolkstudio.popularlibraries.mvp.model.entity.GithubUser
import me.tolkstudio.popularlibraries.mvp.model.repo.RetrofitRepoInfo
import me.tolkstudio.popularlibraries.mvp.navigation.IScreens
import me.tolkstudio.popularlibraries.mvp.presenter.list.IUserRepoListPresenter
import me.tolkstudio.popularlibraries.mvp.view.RepoInfoView
import me.tolkstudio.popularlibraries.mvp.view.USersInfoView
import me.tolkstudio.popularlibraries.mvp.view.list.IUserReposItemView
import moxy.MvpPresenter

class RepoInfoPresenter(
    val uiScheduler: Scheduler,
    val usersRepo: RetrofitRepoInfo,
    val router: Router,
    val repo: GitHubRepo,
    val screen: IScreens,
) :
    MvpPresenter<RepoInfoView>() {
    class ReposListPresenter : IUserRepoListPresenter {

        val repos = mutableListOf<GithubUser>()
        override var itemClickListener: ((IUserReposItemView) -> Unit)? = null

        override fun bindView(view: IUserReposItemView) {
            val repos = repos[view.pos]
            repos.login.let { view.setNameRepos(it) }
        }

        override fun getCount() = repos.size
    }

    val reposListPresenter = ReposListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setLogin(repo.name)
        viewState.setWatchers(repo.watchers)
        viewState.setForks(repo.forks)
        viewState.setDefaultBranch(repo.defaultBranch)

    }

    fun backClick(): Boolean {
        router.exit()
        return true
    }
}