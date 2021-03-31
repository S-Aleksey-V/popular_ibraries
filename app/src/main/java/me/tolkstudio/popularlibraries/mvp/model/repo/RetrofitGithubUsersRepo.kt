package me.tolkstudio.popularlibraries.mvp.model.repo


import io.reactivex.rxjava3.schedulers.Schedulers
import me.tolkstudio.popularlibraries.mvp.model.api.IDataSource

class RetrofitGitHubUsersRepo(val api: IDataSource) : IGithubUsersRepo {
    override fun getRepo() = api.getUsers().subscribeOn(Schedulers.io())
}
