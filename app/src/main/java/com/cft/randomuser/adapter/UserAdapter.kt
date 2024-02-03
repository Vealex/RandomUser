package com.cft.randomuser.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.cft.randomuser.databinding.UserElementBinding
import com.cft.randomuser.models.UserUiModel
import com.cft.randomuser.viewmodel.UserListViewModel

class UserAdapter(private val onClickListener: (Int, Int) -> Unit) :
    ListAdapter<UserUiModel, UserViewHolder>(UserItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = UserElementBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        val viewHolder = UserViewHolder(binding)

        binding.root.setOnClickListener {
            var id = viewHolder.adapterPosition
            var page = 1
            if (id >= UserListViewModel.INIT_LOAD) {
                page += (id - UserListViewModel.INIT_LOAD) / UserListViewModel.NEXT_PAGE_LOAD + 1
                id = (id - UserListViewModel.INIT_LOAD) % UserListViewModel.NEXT_PAGE_LOAD
            }
            onClickListener(id, page)
        }

        return viewHolder
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bindUser(getItem(position))
    }

}