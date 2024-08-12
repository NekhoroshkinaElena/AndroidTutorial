package com.example.androidtutorial2.home.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.androidtutorial2.R
import com.example.androidtutorial2.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeListeners()

        Glide.with(requireActivity()).load(R.drawable.learn).into(binding.ivLearn)
        Glide.with(requireActivity()).load(R.drawable.stats).into(binding.ivStats)
        Glide.with(requireActivity()).load(R.drawable.recommendations)
            .into(binding.ivRecommendation)
        Glide.with(requireActivity()).load(R.drawable.example).into(binding.ivExample)
    }

    private fun initializeListeners() {
        binding.cvLearn.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_studyFragment)
        }

        binding.cvExample.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_evampleFragment)
        }

        binding.cvStats.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_statsFragment)
        }

        binding.cvRecommendations.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_recommendationsFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
