package com.example.androidtutorial2.topic.ui

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.example.androidtutorial2.R
import com.example.androidtutorial2.TutorialApplication
import com.example.androidtutorial2.base.BaseFragment
import com.example.androidtutorial2.databinding.FragmentTopicsBinding
import com.example.androidtutorial2.sub_topics.ui.SubTopicsFragment
import com.example.androidtutorial2.topic.ui.adapter.TopicAdapter

class TopicsFragment : BaseFragment<FragmentTopicsBinding, TopicsViewModel>(
    FragmentTopicsBinding::inflate
) {

    private val topicAdapter = TopicAdapter { topic ->
        if (!topic.blocked) {
            findNavController().navigate(
                R.id.action_studyFragment_to_subTopicsFragment,
                SubTopicsFragment.createArgs(topic.id, topic.name)
            )
        } else {
            Toast.makeText(
                requireActivity(),
                getString(R.string.topic_lock),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onAttach(context: Context) {
        (requireActivity().application as TutorialApplication).appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeObservers()
        initializeAdapter()
    }

    private fun initializeObservers() {
        viewModel.screenState.observe(viewLifecycleOwner) { screenState ->
            when (screenState) {
                is TopicsScreenState.Content -> showContent(screenState)
                TopicsScreenState.Error -> showError()
                TopicsScreenState.Loading -> showLoading()
            }
        }
    }

    private fun initializeAdapter() {
        binding.rvTopicsList.adapter = topicAdapter
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun showContent(screenState: TopicsScreenState.Content) {
        binding.pbProgressBar.isVisible = false
        binding.rvTopicsList.isVisible = true
        binding.tvErrorMessage.isVisible = false
        topicAdapter.topics.clear()
        topicAdapter.topics.addAll(screenState.listTopics)
        topicAdapter.notifyDataSetChanged()
    }

    private fun showLoading() {
        binding.pbProgressBar.isVisible = true
        binding.rvTopicsList.isVisible = false
        binding.tvErrorMessage.isVisible = false
    }

    private fun showError() {
        binding.pbProgressBar.isVisible = false
        binding.rvTopicsList.isVisible = false
        binding.tvErrorMessage.isVisible = true
    }

    companion object {
        fun newInstance() = TopicsFragment()
    }
}
