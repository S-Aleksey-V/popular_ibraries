package me.tolkstudio.popularlibraries.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import me.tolkstudio.popularlibraries.databinding.FragmentRepoInfoBinding
import me.tolkstudio.popularlibraries.mvp.model.entity.GitHubRepo
import me.tolkstudio.popularlibraries.mvp.presenter.RepositoryPresenter
import me.tolkstudio.popularlibraries.mvp.view.RepoInfoView
import me.tolkstudio.popularlibraries.ui.BackClickListener
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class RepoInfoFragment() : MvpAppCompatFragment(),
    RepoInfoView, BackClickListener {

    companion object {
        private const val REPOSITORY_ARG = "repository"
        fun newInstance(repository: GitHubRepo) = RepoInfoFragment().apply {
            arguments = Bundle().apply {
                putParcelable(REPOSITORY_ARG, repository)
            }
        }
    }

    private var vb: FragmentRepoInfoBinding? = null

    private val presenter by moxyPresenter {
        val repo = arguments?.getParcelable<GitHubRepo>(REPOSITORY_ARG) as GitHubRepo
        RepositoryPresenter(repo)
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



    override fun init() {}

    override fun setId(text: String) {
        vb?.userFullName?.text = text
    }

    override fun setTitle(text: String) {
        vb?.repoName?.text = text
    }

    override fun setForksCount(text: String) {
        vb?.forksCount?.text = text
    }

    override fun backPressed() = presenter.backPressed()

}