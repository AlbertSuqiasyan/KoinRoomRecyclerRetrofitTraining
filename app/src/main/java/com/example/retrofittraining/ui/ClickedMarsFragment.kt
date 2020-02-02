package com.example.retrofittraining.ui


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.retrofittraining.ClickedItemViewModel
import com.example.retrofittraining.MarsViewModel

import com.example.retrofittraining.R
import com.example.retrofittraining.data.MarsData
import com.example.retrofittraining.databinding.FragmentClickedMarsBinding
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.viewModel


class ClickedMarsFragment : Fragment() {

    private val clickedItemViewModel by viewModel<ClickedItemViewModel>()

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

        val args = ClickedMarsFragmentArgs.fromBundle(arguments!!)
        val marsId = args.marsId
        binding.viewModel = clickedItemViewModel


        clickedItemViewModel.response.observe(viewLifecycleOwner, Observer {
            val marsData = clickedItemViewModel.getMarsData(it,marsId)
            binding.marsPrice.text = marsData?.price.toString()
            binding.marsType.text = marsData?.type
            Picasso.with(context)
                .load(marsData?.img_src)
                .into(binding.marsImage)
        })


        return binding.root
    }

}



