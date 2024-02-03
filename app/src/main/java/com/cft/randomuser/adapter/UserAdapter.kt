package com.cft.randomuser.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.cft.randomuser.databinding.UserElementBinding
import com.cft.randomuser.models.UserUiModel

class UserAdapter(private val onClickListener: (View) -> Unit) :
    ListAdapter<UserUiModel, UserViewHolder>(UserItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = UserElementBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        binding.root.setOnClickListener(onClickListener)

        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bindUser(getItem(position))
    }

}