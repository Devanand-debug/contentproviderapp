package com.iav.contestdataprovider.ui

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.iav.contestdataprovider.databinding.FragmentRandomStringBinding
import com.iav.contestdataprovider.viewmodel.RandomStringViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RandomStringFragment : Fragment() {

    private lateinit var binding: FragmentRandomStringBinding
    private val viewModel: RandomStringViewModel by viewModels()
//    private val adapter = StringListAdapter {
//        viewModel.deleteString(it)
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRandomStringBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = RandomStringAdapter { position ->
            viewModel.deleteString(position)
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        binding.recyclerView.adapter = adapter

        viewModel.randomStrings.observe(viewLifecycleOwner) { list ->
            adapter.submitList(list)
        }

        binding.generateButton.setOnClickListener {
            val length = binding.lengthInput.text.toString().toIntOrNull() ?: 0
            Log.e("btnClick", length.toString())
            viewModel.generateRandomString(length)
        }

        binding.clearButton.setOnClickListener {
            viewModel.clearAllStrings()
        }
    }
}