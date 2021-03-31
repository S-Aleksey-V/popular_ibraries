package me.tolkstudio.popularlibraries.mvp.presenter

import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import me.tolkstudio.popularlibraries.mvp.model.entity.GitHubRepo

import me.tolkstudio.popularlibraries.mvp.model.entity.GithubUser
import me.tolkstudio.popularlibraries.mvp.model.repo.IGitHubRepositoriesRepo
import me.tolkstudio.popularlibraries.mvp.navigation.IScreens
import me.tolkstudio.popularlibraries.mvp.presenter.list.IUserRepoListPresenter
import me.tolkstudio.popularlibraries.mvp.view.UsersView
import me.tolkstudio.popularlibraries.mvp.view.list.RepositoryItemView
import moxy.MvpPresenter

class UserInfoPresenter(
    val uiScheduler: Scheduler,
    val repositoriesRepo: IGitHubRepositoriesRepo,
    val router: Router,
    val user: GithubUser,
    val screens: IScreens
) :
    MvpPresenter<UsersView>() {

    class UserReposListPresenter : IUserRepoListPresenter {

        val repos = mutableListOf<GitHubRepo>()
        override var itemClickListener: ((RepositoryItemView) -> Unit)? = null

        override fun getCount() = repos.size

        override fun bindView(view: RepositoryItemView) {
            val repos = repos[view.pos]
            repos.name.let { view.setName(it) }
        }
    }

    val userReposListPresenter = UserReposListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadRepos()


        userReposListPresenter.itemClickListener = { view ->
            val repository = userReposListPresenter.repos[view.pos]
            router.navigateTo(screens.repository(repository))
        }

    }

    fun loadRepos() {
        repositoriesRepo.getRepositories(user)
            .observeOn(uiScheduler)
            .subscribe({ repositories ->
                userReposListPresenter.repos.clear()
                userReposListPresenter.repos.addAll(repositories)
                viewState.updateList()
            }, {
                println("Error: ${it.message}")
            })
    }

    fun backClick(): Boolean {
        router.exit()
        return true
    }
}