package me.tolkstudio.popularlibraries.mvp.model

import com.github.terrakok.cicerone.Screen
import me.tolkstudio.popularlibraries.mvp.model.entity.GithubUser

class GithubUsersRepo : Screen {


    private val users = listOf<GithubUser>(
        GithubUser("Login1"),
        GithubUser("Login2"),
        GithubUser("Login3"),
        GithubUser("Login4"),
        GithubUser("Login5")
    )

    fun getUsers(): List<GithubUser> {
        return users
    }
}