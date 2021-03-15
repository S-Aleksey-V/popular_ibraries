package me.tolkstudio.popularlibraries.mvp.presenter

import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Observable.fromIterable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import me.tolkstudio.popularlibraries.mvp.model.GithubUsersRepo
import me.tolkstudio.popularlibraries.mvp.model.entity.GithubUser
import me.tolkstudio.popularlibraries.mvp.navigation.IScreens
import me.tolkstudio.popularlibraries.mvp.presenter.list.IUsersListPresenter
import me.tolkstudio.popularlibraries.mvp.view.UsersView
import me.tolkstudio.popularlibraries.mvp.view.list.IUserItemView
import moxy.MvpPresenter

class UsersPresenter(val usersRepo: GithubUsersRepo, val router: Router, val screens: IScreens) :
    MvpPresenter<UsersView>() {

    class UsersListPresenter : IUsersListPresenter {
        val users = mutableListOf<GithubUser>()
        override var itemClickListener: ((IUserItemView) -> Unit)? = null

        override fun bindView(view: IUserItemView) {
            val user = users[view.pos]
            view.setLogin(user.login)
        }

        override fun getCount() = users.size

    }

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        usersListPresenter.itemClickListener = { view ->
            val user = usersListPresenter.users[view.pos]
            router.navigateTo(screens.user(user))
        }

    }

    fun loadData() {
        execFromIterable()
        viewState.updateList()
    }

    fun backClick(): Boolean {
        router.exit()
        return true
    }

    val stringObserver = object : Observer<GithubUser> {
        var disposable: Disposable? = null

        override fun onComplete() {
            println("onComplete")
        }

        override fun onSubscribe(d: Disposable?) {
            disposable = d
            println("onSubscribe")
        }


        override fun onNext(s: GithubUser?) {
            println("onNext: $s")
            usersListPresenter.users.add( s!!)

        }

        override fun onError(e: Throwable?) {
            println("onError: ${e?.message}")
        }


    }

    fun execFromIterable() {
        usersRepo.fromIterable()
            ?.subscribe(stringObserver)
    }
}


