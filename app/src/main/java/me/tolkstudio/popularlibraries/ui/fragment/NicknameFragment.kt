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

class NicknameFragment() : MvpAppCompatFragment(), UserNickNameView, BackClickListener {

    companion object {

        private const val USER_ARG = "user"

        fun newInstance(user: GithubUser) = NicknameFragment().apply {
            arguments = Bundle().apply {
                putParcelable(USER_ARG, user)
            }
        }
    }

    private val presenter by moxyPresenter {
        val user = arguments?.getParcelable<GithubUser>(USER_ARG) as GithubUser
        UserNickNamePresenter(App.instance.router, user)

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


    override fun setLogin(text: String) {
        vb?.nickName?.text = text
    }


}