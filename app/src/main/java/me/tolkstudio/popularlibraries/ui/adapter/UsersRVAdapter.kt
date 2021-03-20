package me.tolkstudio.popularlibraries.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import me.tolkstudio.popularlibraries.databinding.ItemUserBinding
import me.tolkstudio.popularlibraries.mvp.presenter.list.IUsersListPresenter
import me.tolkstudio.popularlibraries.mvp.view.list.IUserItemView

class UsersRVAdapter(val presenter: IUsersListPresenter) :
    RecyclerView.Adapter<UsersRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ItemUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        ).apply {
            itemView.setOnClickListener { presenter.itemClickListener?.invoke(this) }
        }

    override fun getItemCount() = presenter.getCount()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        presenter.bindView(holder.apply { pos = position })

    inner class ViewHolder(val vb: ItemUserBinding) : RecyclerView.ViewHolder(vb.root),
        IUserItemView {

        override fun setLogin(text: String) = with(vb) {
            tvLogin.text = text
        }

        override var pos = -1
    }
}