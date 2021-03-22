package me.tolkstudio.popularlibraries.mvp.presenter

import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import me.tolkstudio.popularlibraries.mvp.model.entity.GithubUser
import me.tolkstudio.popularlibraries.mvp.model.repo.IGithubUsersRepo
import me.tolkstudio.popularlibraries.mvp.navigation.IScreens
import me.tolkstudio.popularlibraries.mvp.presenter.list.IUsersListPresenter
import me.tolkstudio.popularlibraries.mvp.view.UsersView
import me.tolkstudio.popularlibraries.mvp.view.list.IUserItemView
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
        override var itemClickListener: ((IUserItemView) -> Unit)? = null

        override fun bindView(view: IUserItemView) {
            val user = users[view.pos]
            user.login.let { view.setLogin(it) }
            view.loadAvatar(user.avatarUrl)
        }

        override fun getCount() = users.size
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
        val disposable = usersRepo.getRepo()
            .observeOn(uiScheduler)
            .subscribe({ usersList ->
                usersListPresenter.users.addAll(usersList)
                viewState.updateList()
            }, { error ->
                error.printStackTrace()
            })
        compositeDisposable.add(disposable)
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


