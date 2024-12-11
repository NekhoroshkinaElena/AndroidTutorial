package com.example.androidtutorial2.study.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.androidtutorial2.repeat.ui.RepeatFragment
import com.example.androidtutorial2.topic.ui.TopicsFragment

class StudyViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> TopicsFragment.newInstance()
            else -> RepeatFragment.newInstance()
        }
    }
}
