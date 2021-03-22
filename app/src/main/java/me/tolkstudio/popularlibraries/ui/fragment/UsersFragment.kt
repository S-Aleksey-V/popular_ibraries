package me.tolkstudio.popularlibraries.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import me.tolkstudio.popularlibraries.databinding.FragmentUsersBinding
import me.tolkstudio.popularlibraries.mvp.model.api.ApiHolder
import me.tolkstudio.popularlibraries.mvp.model.repo.RetrofitGitHubUsersRepo
import me.tolkstudio.popularlibraries.mvp.presenter.UsersPresenter
import me.tolkstudio.popularlibraries.mvp.view.UsersView
import me.tolkstudio.popularlibraries.ui.App
import me.tolkstudio.popularlibraries.ui.BackClickListener
import me.tolkstudio.popularlibraries.ui.adapter.UsersRVAdapter
import me.tolkstudio.popularlibraries.ui.image.GlideImageLoader
import me.tolkstudio.popularlibraries.ui.navigation.AndroidScreens
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment : MvpAppCompatFragment(), UsersView, BackClickListener {

    companion object {
        fun newInstance() = UsersFragment()
    }

    private val presenter by moxyPresenter {
        UsersPresenter(
            AndroidSchedulers.mainThread(),
            RetrofitGitHubUsersRepo(ApiHolder.api),
            App.instance.router, AndroidScreens()
        )
    }

    private var vb: FragmentUsersBinding? = null

    private var adapter: UsersRVAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentUsersBinding.inflate(inflater, container, false).also {
        vb = it
    }.root

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

    override fun init() {
        vb?.rvUsers?.layoutManager = LinearLayoutManager(requireContext())
        adapter = UsersRVAdapter(presenter.usersListPresenter, GlideImageLoader())
        vb?.rvUsers?.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backClick()
}