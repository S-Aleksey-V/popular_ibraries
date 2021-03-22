package me.tolkstudio.popularlibraries.mvp.model.repo

import io.reactivex.rxjava3.schedulers.Schedulers
import me.tolkstudio.popularlibraries.mvp.model.api.IDataSource
import me.tolkstudio.popularlibraries.mvp.model.entity.GitHubRepo

class RetrofitRepoInfo(val api: IDataSource): IRepoInfo {
    override fun getRepoInfo(githubRepos: GitHubRepo) = api.getRepoInfo(githubRepos.reposUrl).subscribeOn(Schedulers.io())
}