package me.tolkstudio.popularlibraries.mvp.model.repo

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import me.tolkstudio.popularlibraries.mvp.model.api.IDataSource
import me.tolkstudio.popularlibraries.mvp.model.cache.IGithubRepositoriesCache
import me.tolkstudio.popularlibraries.mvp.model.entity.GitHubRepo
import me.tolkstudio.popularlibraries.mvp.model.entity.GithubUser
import me.tolkstudio.popularlibraries.mvp.model.entity.room.RoomGithubRepository
import me.tolkstudio.popularlibraries.mvp.model.entity.room.db.Database
import me.tolkstudio.popularlibraries.mvp.model.network.INetworkStatus
import java.lang.RuntimeException

class RetrofitGithubRepositoriesRepo(
    val api: IDataSource,
    val networkStatus: INetworkStatus,
    val cache: IGithubRepositoriesCache
) : IGitHubRepositoriesRepo {
    override fun getRepositories(user: GithubUser) =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                user.reposUrl?.let { url ->
                    api.getRepositories(url)
                        .flatMap { repositories ->
                            cache.putUserRepos(user, repositories).toSingleDefault(repositories)
                        }
                } ?: Single.error<List<GitHubRepo>>(RuntimeException("User has no repo uri"))
                    .subscribeOn(Schedulers.io())
            } else {
                cache.getUserRepos(user)
            }
        }.subscribeOn(Schedulers.io())

}