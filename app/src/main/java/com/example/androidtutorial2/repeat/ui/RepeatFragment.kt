package com.example.androidtutorial2.repeat.ui

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
import com.example.androidtutorial2.databinding.FragmentRepeatBinding
import com.example.androidtutorial2.repeat.ui.adapter.RepeatTopicsAdapter
import com.example.androidtutorial2.sub_topics_repeat.ui.SubTopicsRepeatFragment

class RepeatFragment : BaseFragment<FragmentRepeatBinding, RepeatViewModel>(
    FragmentRepeatBinding::inflate
) {

    private val topicAdapter = RepeatTopicsAdapter { topic ->
        if (!topic.blocked) {
            findNavController().navigate(
                R.id.action_studyFragment_to_subTopicsRepeatFragment,
                SubTopicsRepeatFragment.createArgs(topic.id, topic.name)
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
        initializeListeners()
        initializeObservers()
        initializeAdapter()
    }

    private fun initializeListeners() {
    }

    private fun initializeObservers() {
        viewModel.screenState.observe(viewLifecycleOwner) { screenState ->
            when (screenState) {
                is RepeatScreenState.Content -> showContent(screenState)
                RepeatScreenState.Error -> showError()
                RepeatScreenState.Loading -> showLoading()
            }
        }
    }

    private fun initializeAdapter() {
        binding.rvRepeatTopicsList.adapter = topicAdapter
    }


    @SuppressLint("NotifyDataSetChanged")
    private fun showContent(screenState: RepeatScreenState.Content) {
        binding.pbProgressBar.isVisible = false
        binding.rvRepeatTopicsList.isVisible = true
        binding.tvErrorMessage.isVisible = false
        topicAdapter.topics.clear()
        topicAdapter.topics.addAll(screenState.listTopics)
        topicAdapter.notifyDataSetChanged()
    }

    private fun showLoading() {
        binding.pbProgressBar.isVisible = true
        binding.rvRepeatTopicsList.isVisible = false
        binding.tvErrorMessage.isVisible = false
    }

    private fun showError() {
        binding.pbProgressBar.isVisible = false
        binding.rvRepeatTopicsList.isVisible = false
        binding.tvErrorMessage.isVisible = true
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadTopics()
    }

    companion object {
        fun newInstance() = RepeatFragment()
    }
}
