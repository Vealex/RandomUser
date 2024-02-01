package com.eltex.randomuser.adapter

import androidx.recyclerview.widget.DiffUtil
import com.eltex.randomuser.models.UserUiModel

class UserItemCallback : DiffUtil.ItemCallback<UserUiModel>() {

    override fun areItemsTheSame(oldItem: UserUiModel, newItem: UserUiModel): Boolean =
        oldItem.idName == newItem.idName && oldItem.idValue == newItem.idValue

    override fun areContentsTheSame(oldItem: UserUiModel, newItem: UserUiModel): Boolean =
        oldItem == newItem
}