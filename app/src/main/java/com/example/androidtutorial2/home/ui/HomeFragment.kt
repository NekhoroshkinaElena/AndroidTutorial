package com.example.androidtutorial2.home.ui

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.androidtutorial2.R
import com.example.androidtutorial2.base.BaseFragment
import com.example.androidtutorial2.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeListeners()
    }

    private fun initializeListeners() {
        binding.cvLearn.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_studyFragment)
        }
    }
}
