package com.example.androidtutorial2.study.ui

import android.os.Bundle
import android.view.View
import com.example.androidtutorial2.base.BaseFragment
import com.example.androidtutorial2.databinding.FragmentStudyBinding
import com.example.androidtutorial2.study.ui.adapter.StudyViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class StudyFragment : BaseFragment<FragmentStudyBinding>(FragmentStudyBinding::inflate) {

    private lateinit var tabMediator: TabLayoutMediator


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
}
