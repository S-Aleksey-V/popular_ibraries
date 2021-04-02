package me.tolkstudio.popularlibraries.ui.navigation

import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import me.tolkstudio.popularlibraries.mvp.model.entity.GitHubRepo
import me.tolkstudio.popularlibraries.mvp.model.entity.GithubUser
import me.tolkstudio.popularlibraries.mvp.navigation.IScreens
import me.tolkstudio.popularlibraries.ui.fragment.RepoInfoFragment
import me.tolkstudio.popularlibraries.ui.fragment.UserInfoFragment
import me.tolkstudio.popularlibraries.ui.fragment.UsersFragment

class AndroidScreens : IScreens {

    override fun users() = FragmentScreen { UsersFragment.newInstance() }
    override fun user(user: GithubUser) = FragmentScreen { UserInfoFragment.newInstance(user) }
    override fun repository(gitHubRepo: GitHubRepo) = FragmentScreen {
        RepoInfoFragment.newInstance(gitHubRepo)
    }
}
