package com.cft.randomuser.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.cft.randomuser.R
import com.cft.randomuser.adapter.UserAdapter
import com.cft.randomuser.api.RandomUserGeneratorApi
import com.cft.randomuser.databinding.FragmentListBinding
import com.cft.randomuser.errors.UserListStatement
import com.cft.randomuser.mapper.UserUiModelMapper
import com.cft.randomuser.repositories.NetworkUserRepository
import com.cft.randomuser.repositories.PreferencesRepositoryImpl
import com.cft.randomuser.viewmodel.UserListViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class ListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentListBinding.inflate(layoutInflater)

        val viewModel by viewModels<UserListViewModel> {
            viewModelFactory {
                initializer {
                    UserListViewModel(
                        NetworkUserRepository(
                            RandomUserGeneratorApi.INSTANCE
                        ),
                        PreferencesRepositoryImpl(requireContext().applicationContext),
                        UserUiModelMapper(),
                    )
                }
            }
        }

        val adapter = UserAdapter { id, page ->
            activity?.findNavController(R.id.container)?.navigate(
                R.id.action_listFragment_to_userFragment,
                bundleOf(
                    UserFragment.ID_KEY to id,
                    UserFragment.PAGE_KEY to page,
                    UserFragment.SEED_KEY to viewModel.uiState.value.seed,
                ),
            )
        }
        binding.list.adapter = adapter

        binding.list.addOnChildAttachStateChangeListener(object :
            RecyclerView.OnChildAttachStateChangeListener {
            override fun onChildViewAttachedToWindow(view: View) {
                val position = binding.list.getChildAdapterPosition(view)
                val adapterListCount = adapter.itemCount
                if (position == adapterListCount - 1) {
                    viewModel.getNextUsers(UserListViewModel.NEXT_PAGE_LOAD)
                }
            }

            override fun onChildViewDetachedFromWindow(view: View) = Unit

        })

        binding.swipeRefresh.setOnRefreshListener {
            viewModel.clearSeed()
            viewModel.getUsers(UserListViewModel.INIT_LOAD)
        }

        viewModel.uiState
            .flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach { state ->
                binding.progressBar.isVisible = false
                binding.swipeRefresh.isRefreshing = false
                when (state.statement) {
                    UserListStatement.LOADING -> {
                        binding.progressBar.isVisible = true
                    }

                    UserListStatement.LIST_ERROR -> {
                        Toast.makeText(requireContext(), R.string.list_error, Toast.LENGTH_SHORT)
                            .show()
                    }

                    UserListStatement.PAGE_ERROR -> {
                        Toast.makeText(requireContext(), R.string.page_error, Toast.LENGTH_SHORT)
                            .show()
                    }

                    null -> {
                        state.seed?.let {
                            adapter.submitList(state.users)
                        }
                    }
                }
                viewModel.errorHandled()
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)

        /*val usersTestList = List<UserUiModel>(10) {
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
        adapter.submitList(usersTestList)*/

        return binding.root
    }

}