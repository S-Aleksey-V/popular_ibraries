package me.tolkstudio.popularlibraries.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import me.tolkstudio.popularlibraries.databinding.ItemUserRepoBinding
import me.tolkstudio.popularlibraries.mvp.presenter.list.IUserRepoListPresenter
import me.tolkstudio.popularlibraries.mvp.view.list.IUserReposItemView


class UserReposAdapter(val presenter: IUserRepoListPresenter) :
    RecyclerView.Adapter<UserReposAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserReposAdapter.ViewHolder =
        ViewHolder(
            ItemUserRepoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        ).apply {
            itemView.setOnClickListener { presenter.itemClickListener?.invoke(this) }
        }

    override fun getItemCount(): Int = presenter.getCount()

    override fun onBindViewHolder(holder: UserReposAdapter.ViewHolder, position: Int) =
        presenter.bindView(holder.apply { pos = position })

    inner class ViewHolder(val vb: ItemUserRepoBinding) : RecyclerView.ViewHolder(vb.root),
        IUserReposItemView {

        override var pos = -1

        override fun setNameRepos(nameRepo: String) = with(vb) {
            vb.tvRepoName.text = nameRepo
        }
    }
}