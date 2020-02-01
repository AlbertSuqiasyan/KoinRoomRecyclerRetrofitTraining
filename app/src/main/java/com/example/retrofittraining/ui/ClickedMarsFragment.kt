package com.example.retrofittraining.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.retrofittraining.MarsViewModel

import com.example.retrofittraining.R
import com.example.retrofittraining.data.MarsDao
import com.example.retrofittraining.databinding.FragmentClickedMarsBinding
import com.squareup.picasso.Picasso
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class ClickedMarsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentClickedMarsBinding>(
            inflater,
            R.layout.fragment_clicked_mars,
            container,
            false
        )
        val marsViewModel by viewModel<MarsViewModel>()
        val args = ClickedMarsFragmentArgs.fromBundle(arguments!!)
        val marsId = args.marsId
        val marsObject = marsViewModel.getMarsById(marsId)
        binding.marsPrice.text = marsViewModel.marsObject.value?.price.toString()
        binding.marsType.text = marsViewModel.marsObject.value?.type
        Picasso.with(this.context)
            .load(marsViewModel.marsObject.value?.imgSrc)
            .into(binding.marsImage)

        return binding.root
    }
}
