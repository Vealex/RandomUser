package com.eltex.randomuser.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.eltex.randomuser.R
import com.eltex.randomuser.adapter.UserAdapter
import com.eltex.randomuser.databinding.FragmentListBinding
import com.eltex.randomuser.models.UserUiModel

class ListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentListBinding.inflate(layoutInflater)

        val adapter = UserAdapter {
            activity?.findNavController(R.id.container)?.navigate(
                R.id.action_listFragment_to_userFragment
            )
        }
        binding.list.adapter = adapter

        val usersTestList = List<UserUiModel>(10) {
            UserUiModel(
                first = "Karla",
                last = "Christiansen",
                streetNumber = 4647,
                streetName = "Guldagervej",
                city = "Kvistgaard",
                state = "Danmark",
                country = "Denmark",
                postcode = 20911,
                cell = "61809747",
                medium = "https://randomuser.me/api/portraits/med/women/0.jpg",
            )
        }
        adapter.submitList(usersTestList)

        return binding.root
    }

}