package me.tolkstudio.popularlibraries.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import me.tolkstudio.popularlibraries.databinding.NickNameBinding
import me.tolkstudio.popularlibraries.mvp.model.entity.GithubUser
import me.tolkstudio.popularlibraries.mvp.presenter.UserNickNamePresenter
import me.tolkstudio.popularlibraries.mvp.view.UserNickNameView
import me.tolkstudio.popularlibraries.ui.App
import me.tolkstudio.popularlibraries.ui.BackClickListener
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class NicknameFragment : MvpAppCompatFragment(), UserNickNameView, BackClickListener {

    companion object {
        lateinit var currentUser: GithubUser
        fun newInstance(user: GithubUser): NicknameFragment {
            currentUser = user
            return NicknameFragment()
        }
    }

    private val presenter by moxyPresenter {
        UserNickNamePresenter(App.instance.router)
    }

    private var vb: NickNameBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = NickNameBinding.inflate(inflater, container, false).also {
        vb = it
    }.root

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

    override fun backPressed() = presenter.backClick()

    override fun init() {
        vb?.nickName?.text = currentUser.login
    }
}