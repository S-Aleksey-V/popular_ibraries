package me.tolkstudio.popularlibraries.mvp.model.repo

import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Single
import me.tolkstudio.popularlibraries.mvp.model.entity.GitHubRepo
import me.tolkstudio.popularlibraries.mvp.model.entity.GithubUser

interface IRepoInfo {
    fun getRepoInfo(githubRepos: GitHubRepo): @NonNull Single<List<GithubUser>>?
}