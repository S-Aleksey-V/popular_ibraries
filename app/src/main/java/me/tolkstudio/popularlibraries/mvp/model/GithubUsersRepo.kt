package me.tolkstudio.popularlibraries.mvp.model

import com.github.terrakok.cicerone.Screen
import io.reactivex.rxjava3.core.Observable

import me.tolkstudio.popularlibraries.mvp.model.entity.GithubUser

class GithubUsersRepo : Screen {

    private val users = listOf(
        GithubUser("Login1"),
        GithubUser("Login2"),
        GithubUser("Login3"),
        GithubUser("Login4"),
        GithubUser("Login5")
    )

    fun getUsers() : Observable<List<GithubUser>> {
        return Observable.just(users)
    }
}