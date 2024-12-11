package com.example.androidtutorial2.sub_topics.ui

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.example.androidtutorial2.R
import com.example.androidtutorial2.TutorialApplication
import com.example.androidtutorial2.base.BaseFragment
import com.example.androidtutorial2.databinding.FragmentSubTopicsBinding
import com.example.androidtutorial2.material_study.ui.MaterialStudyFragment
import com.example.androidtutorial2.sub_topics.ui.adapter.SubTopicsAdapter

class SubTopicsFragment : BaseFragment<FragmentSubTopicsBinding, SubTopicsViewModel>(
    FragmentSubTopicsBinding::inflate
) {

    private val subTopicsAdapter = SubTopicsAdapter { subTopic ->
        findNavController().navigate(
            R.id.action_subTopicsFragment_to_materialStudyFragment,
            MaterialStudyFragment.createArguments(subTopic.id)
        )
    }

    override fun onAttach(context: Context) {
        (requireActivity().application as TutorialApplication).appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val topicId: Int = requireArguments().getInt(TOPIC_ID)
        viewModel.getSubTopics(topicId)

        initializeObservers()
        initializeAdapter()
        initializeListeners()
    }

    private fun initializeListeners() {
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun initializeObservers() {
        viewModel.screenState.observe(viewLifecycleOwner) { screenState ->
            when (screenState) {
                is SubTopicsScreenState.Content -> showContent(screenState)
                SubTopicsScreenState.Error -> showError()
                SubTopicsScreenState.Loading -> showLoading()
            }
        }
    }

    private fun initializeAdapter() {
        binding.rvSubTopicsList.adapter = subTopicsAdapter
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun showContent(screenState: SubTopicsScreenState.Content) {
        binding.pbProgressBar.isVisible = false
        binding.rvSubTopicsList.isVisible = true
        binding.tvErrorMessage.isVisible = false
        binding.toolbar.title = requireArguments().getString(TOPIC_NAME) ?: ""
        subTopicsAdapter.subTopics.clear()
        subTopicsAdapter.subTopics.addAll(screenState.listTopics)
        subTopicsAdapter.notifyDataSetChanged()
    }

    private fun showLoading() {
        binding.pbProgressBar.isVisible = true
        binding.rvSubTopicsList.isVisible = false
        binding.tvErrorMessage.isVisible = false
    }

    private fun showError() {
        binding.pbProgressBar.isVisible = false
        binding.rvSubTopicsList.isVisible = false
        binding.tvErrorMessage.isVisible = true
    }

    companion object {
        private const val TOPIC_ID = "topic_id"
        private const val TOPIC_NAME = "topic_name"

        fun createArgs(topicId: Int, topicName: String): Bundle {
            return bundleOf(TOPIC_ID to topicId, TOPIC_NAME to topicName)
        }
    }
}
