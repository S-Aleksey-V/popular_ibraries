package me.tolkstudio.popularlibraries.mvp.presenter

import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import me.tolkstudio.popularlibraries.mvp.model.entity.GithubUser
import me.tolkstudio.popularlibraries.mvp.model.repo.IGithubUsersRepo
import me.tolkstudio.popularlibraries.mvp.navigation.IScreens
import me.tolkstudio.popularlibraries.mvp.presenter.list.IUsersListPresenter
import me.tolkstudio.popularlibraries.mvp.view.UsersView
import me.tolkstudio.popularlibraries.mvp.view.list.UserItemView
import moxy.MvpPresenter


class UsersPresenter(
    val uiScheduler: Scheduler,
    val usersRepo: IGithubUsersRepo,
    val router: Router,
    val screen: IScreens
) :
    MvpPresenter<UsersView>() {

    class UsersListPresenter : IUsersListPresenter {

        val users = mutableListOf<GithubUser>()
        override var itemClickListener: ((UserItemView) -> Unit)? = null

        override fun getCount() = users.size

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            user.login.let { view.setLogin(it) }
            user.avatarUrl?.let { view.loadAvatar(it) }
        }
    }

    val usersListPresenter = UsersListPresenter()
    val compositeDisposable = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        usersListPresenter.itemClickListener = { view ->
            val user = usersListPresenter.users[view.pos]
            router.navigateTo(screen.user(user))
        }
    }

    fun loadData() {
        usersRepo.getUsers()
            .observeOn(uiScheduler)
            .subscribe({ repos ->
                usersListPresenter.users.clear()
                usersListPresenter.users.addAll(repos)
                viewState.updateList()
            }, {
                println("Error: ${it.message}")
            })
    }

    fun backClick(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
        super.onDestroy()
    }
}


