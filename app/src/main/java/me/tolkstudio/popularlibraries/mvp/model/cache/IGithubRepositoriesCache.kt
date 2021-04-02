package me.tolkstudio.popularlibraries.mvp.model.cache

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import me.tolkstudio.popularlibraries.mvp.model.entity.GitHubRepo
import me.tolkstudio.popularlibraries.mvp.model.entity.GithubUser

interface IGithubRepositoriesCache {
    fun getUserRepos(user: GithubUser): Single<List<GitHubRepo>>
    fun putUserRepos(user: GithubUser, repositories: List<GitHubRepo>): Completable
}