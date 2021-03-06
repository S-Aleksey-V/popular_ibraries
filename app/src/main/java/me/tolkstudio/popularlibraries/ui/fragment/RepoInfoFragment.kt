package me.tolkstudio.popularlibraries.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import me.tolkstudio.popularlibraries.databinding.FragmentRepoInfoBinding
import me.tolkstudio.popularlibraries.mvp.model.api.ApiHolder
import me.tolkstudio.popularlibraries.mvp.model.entity.GitHubRepo
import me.tolkstudio.popularlibraries.mvp.model.image.IImageLoader
import me.tolkstudio.popularlibraries.mvp.model.repo.RetrofitRepoInfo
import me.tolkstudio.popularlibraries.mvp.presenter.RepoInfoPresenter
import me.tolkstudio.popularlibraries.mvp.view.RepoInfoView
import me.tolkstudio.popularlibraries.ui.App
import me.tolkstudio.popularlibraries.ui.BackClickListener
import me.tolkstudio.popularlibraries.ui.adapter.UserReposAdapter
import me.tolkstudio.popularlibraries.ui.image.GlideImageLoader
import me.tolkstudio.popularlibraries.ui.navigation.AndroidScreens
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class RepoInfoFragment(val imageLoader: IImageLoader<ImageView>) : MvpAppCompatFragment(),
    RepoInfoView, BackClickListener {

    companion object {
        private const val USER_ARG = "repos"
        fun newInstance(user: GitHubRepo) = RepoInfoFragment(GlideImageLoader()).apply {
            arguments = Bundle().apply {
                putParcelable(USER_ARG, user)
            }
        }
    }

    private var vb: FragmentRepoInfoBinding? = null
    private var adapter: UserReposAdapter? = null

    private val presenter by moxyPresenter {
        val repo = arguments?.getParcelable<GitHubRepo>(USER_ARG) as GitHubRepo
        RepoInfoPresenter(
            AndroidSchedulers.mainThread(),
            RetrofitRepoInfo(ApiHolder.api),
            App.instance.router, repo, AndroidScreens()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) =
        FragmentRepoInfoBinding.inflate(inflater, container, false).also {
            vb = it
        }.root

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

    override fun backPressed(): Boolean = presenter.backClick()


    override fun updateReposList() {
        adapter?.notifyDataSetChanged()
    }

    override fun setLogin(text: String) {
        vb?.repoName?.text = text
    }


    override fun setWatchers(text: String) {
        vb?.userFullName?.text = text
    }

    override fun setForks(text: String) {
        vb?.forksCount?.text = text
    }

    override fun setDefaultBranch(text: String) {
        vb?.defaultBranch?.text = text
    }


}