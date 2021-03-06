package me.tolkstudio.popularlibraries.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import me.tolkstudio.popularlibraries.databinding.FragmentUserInfoBinding
import me.tolkstudio.popularlibraries.mvp.model.api.ApiHolder
import me.tolkstudio.popularlibraries.mvp.model.entity.GithubUser
import me.tolkstudio.popularlibraries.mvp.model.image.IImageLoader
import me.tolkstudio.popularlibraries.mvp.model.repo.RetrofitGithubRepos
import me.tolkstudio.popularlibraries.mvp.presenter.UserInfoPresenter
import me.tolkstudio.popularlibraries.mvp.view.USersInfoView
import me.tolkstudio.popularlibraries.ui.App
import me.tolkstudio.popularlibraries.ui.BackClickListener
import me.tolkstudio.popularlibraries.ui.adapter.UserReposAdapter
import me.tolkstudio.popularlibraries.ui.image.GlideImageLoader
import me.tolkstudio.popularlibraries.ui.navigation.AndroidScreens
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserInfoFragment(val imageLoader: IImageLoader<ImageView>) : MvpAppCompatFragment(),
    USersInfoView, BackClickListener {

    companion object {
        private const val USER_ARG = "user"
        fun newInstance(user: GithubUser) = UserInfoFragment(GlideImageLoader()).apply {
            arguments = Bundle().apply {
                putParcelable(USER_ARG, user)
            }
        }
    }

    private var vb: FragmentUserInfoBinding? = null
    private var adapter: UserReposAdapter? = null

    private val presenter by moxyPresenter {
        val user = arguments?.getParcelable<GithubUser>(USER_ARG) as GithubUser
        UserInfoPresenter(
            AndroidSchedulers.mainThread(),
            App.instance.router,
            user,
            RetrofitGithubRepos(ApiHolder.api),
            AndroidScreens()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) =
        FragmentUserInfoBinding.inflate(inflater, container, false).also {
            vb = it
        }.root

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

    override fun backPressed(): Boolean = presenter.backClick()

    override fun init() {
        vb?.rvUserRepo?.layoutManager = LinearLayoutManager(requireContext())
        adapter = UserReposAdapter(presenter.userReposListPresenter)
        vb?.rvUserRepo?.adapter = adapter
    }

    override fun updateReposList() {
        adapter?.notifyDataSetChanged()
    }

    override fun setLogin(text: String) {
        vb?.userLogin?.text = text
    }

    override fun setImage(url: String) {
        imageLoader.load(url, vb!!.ivAvatar)
    }

}