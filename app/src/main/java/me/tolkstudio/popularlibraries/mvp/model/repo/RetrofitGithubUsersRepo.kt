package me.tolkstudio.popularlibraries.mvp.model.repo


import io.reactivex.rxjava3.schedulers.Schedulers
import me.tolkstudio.popularlibraries.mvp.model.api.IDataSource
import me.tolkstudio.popularlibraries.mvp.model.cache.IGithubUsersCache
import me.tolkstudio.popularlibraries.mvp.model.network.INetworkStatus

class RetrofitGithubUsersRepo(
    val api: IDataSource,
    val networkStatus: INetworkStatus,
    val cache: IGithubUsersCache
) : IGithubUsersRepo {

    override fun getUsers() = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            api.getUsers()
                .flatMap { users ->
                    cache.putUsers(users).toSingleDefault(users)
                }
        } else {
            cache.getUsers()
        }
    }.subscribeOn(Schedulers.io())

}