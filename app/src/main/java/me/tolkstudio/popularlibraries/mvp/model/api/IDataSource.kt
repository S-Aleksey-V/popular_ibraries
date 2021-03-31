package me.tolkstudio.popularlibraries.mvp.model.api

import io.reactivex.rxjava3.core.Single
import me.tolkstudio.popularlibraries.mvp.model.entity.GitHubRepo
import me.tolkstudio.popularlibraries.mvp.model.entity.GithubUser
import me.tolkstudio.popularlibraries.mvp.model.entity.RepoItemResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface IDataSource {
    @GET("/users")
    fun getUsers(): Single<List<GithubUser>>

    @GET
    fun getUsersRepos(@Url url: String): Single<List<GitHubRepo>>

    @GET("users/{username}/repos")
    fun getRepoInfo(@Path("username") username: String): Single<List<GithubUser>>
}