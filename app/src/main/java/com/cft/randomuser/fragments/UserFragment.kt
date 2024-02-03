package com.cft.randomuser.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.bumptech.glide.Glide
import com.cft.randomuser.R
import com.cft.randomuser.api.RandomUserGeneratorApi
import com.cft.randomuser.databinding.FragmentUserBinding
import com.cft.randomuser.mapper.UserUiModelMapper
import com.cft.randomuser.repositories.NetworkUserRepository
import com.cft.randomuser.viewmodel.UserViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class UserFragment : Fragment() {

    companion object {
        const val ID_KEY = "com.cft.randomuser.ID_KEY"
        const val PAGE_KEY = "com.cft.randomuser.PAGE_KEY"
        const val SEED_KEY = "com.cft.randomuser.SEED_KEY"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentUserBinding.inflate(layoutInflater)

        val id = arguments?.getInt(ID_KEY) ?: 0
        val page = arguments?.getInt(PAGE_KEY) ?: 0
        val seed = arguments?.getString(SEED_KEY).orEmpty()

        val viewModel: UserViewModel by viewModels {
            viewModelFactory {
                initializer {
                    UserViewModel(
                        NetworkUserRepository(
                            RandomUserGeneratorApi.INSTANCE
                        ),
                        UserUiModelMapper(),
                    )
                }
            }
        }

        viewModel.getUser(id, page, seed)

        viewModel.uiState
            .flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach {
                val user = it.user

                Glide.with(binding.avatar)
                    .load(user?.large)
                    .placeholder(R.drawable.baseline_portrait_24)
                    .error(R.drawable.baseline_portrait_24)
                    .into(binding.avatar)
                binding.title.text = user?.title
                binding.firstName.text = user?.first
                binding.lastName.text = user?.last
//                binding.streetNumber.text = "House number: ${user?.streetNumber}"
//                binding.streetName.text = "Street: ${user?.streetName}"
//                binding.city.text = "City: ${user?.city}"
//                binding.state.text = "State: ${user?.state}"
//                binding.country.text = "Country: ${user?.country}"
//                binding.postcode.text = "Postcode: ${user?.postcode}"
//                binding.latitude.text = "Latitude: ${user?.latitude}"
//                binding.longitude.text = "Longitude: ${user?.longitude}"
                binding.address.text =
                    "${user?.streetNumber} ${user?.streetName}, ${user?.city}, ${user?.state} ${user?.postcode}, ${user?.country}"
                binding.coordinates.text = "${user?.latitude}, ${user?.longitude}"
                binding.timezoneOffset.text = "Timezone offset: ${user?.timezoneOffset}"
                binding.timezoneDescription.text = "Timezone: ${user?.timezoneDescription}"
                binding.uuid.text = "UUID: ${user?.uuid}"
                binding.username.text = "Username: ${user?.username}"
                binding.password.text = "Password: ${user?.password}"
                binding.salt.text = "Salt: ${user?.salt}"
                binding.md5.text = "MD5: ${user?.md5}"
                binding.sha1.text = "SHA1: ${user?.sha1}"
                binding.sha256.text = "SHA256: ${user?.sha256}"
                binding.idName.text = "Name: ${user?.idName}"
                binding.idValue.text = "Value: ${user?.idValue}"
                binding.gender.text = user?.gender
                binding.email.text = user?.email
                binding.phone.text = user?.phone
                binding.cell.text = user?.cell
                binding.registeredDate.text = "Date: ${user?.registerDate}"
                binding.registeredAge.text = "Age: ${user?.registerAge}"
                binding.dobDate.text = "Date: ${user?.dobDate}"
                binding.dobAge.text = "Age: ${user?.dobAge}"
                binding.nat.text = user?.nat
            }
            .launchIn(lifecycleScope)

        return binding.root
    }

}