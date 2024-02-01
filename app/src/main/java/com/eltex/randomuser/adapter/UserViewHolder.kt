package com.eltex.randomuser.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.eltex.randomuser.R
import com.eltex.randomuser.databinding.UserElementBinding
import com.eltex.randomuser.models.UserUiModel

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
            .error(R.drawable.baseline_portrait_24)
            .into(binding.avatar)
    }

}