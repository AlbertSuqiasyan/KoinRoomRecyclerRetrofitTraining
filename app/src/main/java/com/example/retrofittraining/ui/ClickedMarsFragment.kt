package com.example.retrofittraining.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.retrofittraining.MarsViewModel

import com.example.retrofittraining.R
import com.example.retrofittraining.databinding.FragmentClickedMarsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class ClickedMarsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentClickedMarsBinding>(inflater,R.layout.fragment_clicked_mars,container,false)
        val marsViewModel by viewModel<MarsViewModel>()
        val args = ClickedMarsFragmentArgs.fromBundle(arguments!!)
        val marsId = args.marsId
        val marsData = marsViewModel.response.value?.find { it.id == marsId }

        binding.marsType.text = marsData?.type

        return binding.root
    }


}
