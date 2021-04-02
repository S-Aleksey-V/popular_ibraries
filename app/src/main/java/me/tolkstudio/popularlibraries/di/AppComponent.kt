package me.tolkstudio.popularlibraries.di

import dagger.Component
import me.tolkstudio.popularlibraries.di.module.*
import me.tolkstudio.popularlibraries.mvp.presenter.MainPresenter
import me.tolkstudio.popularlibraries.mvp.presenter.RepositoryPresenter
import me.tolkstudio.popularlibraries.mvp.presenter.UserInfoPresenter
import me.tolkstudio.popularlibraries.mvp.presenter.UsersPresenter
import me.tolkstudio.popularlibraries.ui.activity.MainActivity
import me.tolkstudio.popularlibraries.ui.adapter.UsersRVAdapter
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        CiceroneModule::class,
        ApiModule::class,
        RepoModule::class,
        CacheModule::class,
        ImageModule::class
    ]
)
interface AppComponent {

    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
    fun inject(usersPresenter: UsersPresenter)
    fun inject(usersRVAdapter: UsersRVAdapter)
    fun inject(repositoryPresenter: RepositoryPresenter)
    fun inject(userInfoPresenter: UserInfoPresenter)


}