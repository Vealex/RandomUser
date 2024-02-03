package com.cft.randomuser.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cft.randomuser.R
import com.cft.randomuser.databinding.UserElementBinding
import com.cft.randomuser.models.UserUiModel

class UserViewHolder(private val binding: UserElementBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bindUser(user: UserUiModel) {
        val fullName = "${user.first} ${user.last}"
        binding.name.text = fullName

        val addressAndPhone =
            "${user.streetNumber} ${user.streetName}\n${user.city}, ${user.state} ${user.postcode}\n${user.country}\n" +
                    "${user.cell}"
        binding.addressAndPhone.text = addressAndPhone

        Glide.with(binding.avatar)
            .load(user.medium)
            .placeholder(R.drawable.baseline_portrait_24)
            .error(R.drawable.baseline_portrait_24)
            .into(binding.avatar)
    }

}