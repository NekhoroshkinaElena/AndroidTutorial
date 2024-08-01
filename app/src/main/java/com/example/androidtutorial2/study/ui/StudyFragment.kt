package com.example.androidtutorial2.study.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.androidtutorial2.databinding.FragmentStudyBinding
import com.example.androidtutorial2.study.ui.adapter.StudyViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class StudyFragment : Fragment() {

    private var _binding: FragmentStudyBinding? = null
    private val binding get() = _binding!!

    private lateinit var tabMediator: TabLayoutMediator

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStudyBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewPager.adapter = StudyViewPagerAdapter(childFragmentManager, lifecycle)

        tabMediator = TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Темы"
                1 -> tab.text = "Повторение"
            }
        }
        tabMediator.attach()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
