package com.example.retrofittraining.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.retrofittraining.MarsViewModel

import com.example.retrofittraining.R
import com.example.retrofittraining.databinding.FragmentMainBinding
import com.example.retrofittraining.recyclerView.ClickListener
import com.example.retrofittraining.recyclerView.MarsAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainFragment : Fragment() {

    private val marsViewModel by viewModel<MarsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentMainBinding>(inflater,R.layout.fragment_main,container,false)

        val adapter = MarsAdapter(ClickListener { marsId ->
            this.findNavController().navigate(MainFragmentDirections.actionMainFragmentToClickedMarsFragment(marsId))
        })

        binding.marsListRecyclerView.adapter = adapter

        marsViewModel.response.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })


        return binding.root
    }


}
