package me.tolkstudio.popularlibraries.mvp.model.repo

import io.reactivex.rxjava3.schedulers.Schedulers
import me.tolkstudio.popularlibraries.mvp.model.api.IDataSource
import me.tolkstudio.popularlibraries.mvp.model.entity.GithubUser

class RetrofitGithubRepos(val api: IDataSource) : IGitHubUserRepos {
    override fun getRepos(user: GithubUser) =
        api.getUsersRepos(user.reposUrl).subscribeOn(Schedulers.io())
}