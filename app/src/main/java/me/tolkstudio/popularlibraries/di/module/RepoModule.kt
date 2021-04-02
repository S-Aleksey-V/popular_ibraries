package me.tolkstudio.popularlibraries.di.module


import dagger.Module
import dagger.Provides
import me.tolkstudio.popularlibraries.mvp.model.api.IDataSource
import me.tolkstudio.popularlibraries.mvp.model.cache.IGithubRepositoriesCache
import me.tolkstudio.popularlibraries.mvp.model.cache.IGithubUsersCache
import me.tolkstudio.popularlibraries.mvp.model.network.INetworkStatus
import me.tolkstudio.popularlibraries.mvp.model.repo.IGitHubRepositoriesRepo
import me.tolkstudio.popularlibraries.mvp.model.repo.IGithubUsersRepo
import me.tolkstudio.popularlibraries.mvp.model.repo.RetrofitGithubRepositoriesRepo
import me.tolkstudio.popularlibraries.mvp.model.repo.RetrofitGithubUsersRepo
import javax.inject.Singleton


@Module
class RepoModule {

    @Singleton
    @Provides
    fun usersRepo(
        api: IDataSource,
        networkStatus: INetworkStatus,
        cache: IGithubUsersCache
    ): IGithubUsersRepo = RetrofitGithubUsersRepo(api, networkStatus, cache)

    @Singleton
    @Provides
    fun repositoriesRepo(
        api: IDataSource,
        networkStatus: INetworkStatus,
        cache: IGithubRepositoriesCache
    ): IGitHubRepositoriesRepo = RetrofitGithubRepositoriesRepo(api, networkStatus, cache)

}