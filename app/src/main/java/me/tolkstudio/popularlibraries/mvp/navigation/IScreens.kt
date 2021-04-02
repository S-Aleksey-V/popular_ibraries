package me.tolkstudio.popularlibraries.mvp.navigation

import com.github.terrakok.cicerone.Screen
import me.tolkstudio.popularlibraries.mvp.model.entity.GitHubRepo
import me.tolkstudio.popularlibraries.mvp.model.entity.GithubUser

interface IScreens {
    fun users(): Screen
    fun user(githubUser: GithubUser): Screen
    fun repository(gitHubRepo: GitHubRepo): Screen
}