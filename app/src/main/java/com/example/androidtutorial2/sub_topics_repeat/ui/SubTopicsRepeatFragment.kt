package com.example.androidtutorial2.sub_topics_repeat.ui

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
import com.example.androidtutorial2.material_study_repeat.MaterialStudyRepeatFragment
import com.example.androidtutorial2.notifications.NotificationsManager
import com.example.androidtutorial2.sub_topics_repeat.ui.adapter.SubTopicRepeatAdapter
import javax.inject.Inject

class SubTopicsRepeatFragment : BaseFragment<FragmentSubTopicsBinding, SubTopicsRepeatViewModel>(
    FragmentSubTopicsBinding::inflate
) {

    @Inject
    lateinit var notificationManager: NotificationsManager

    private val subTopicAdapter = SubTopicRepeatAdapter(
        onClick = { subTopic ->
            findNavController().navigate(
                R.id.action_subTopicsRepeatFragment_to_materialStudyRepeatFragment,
                MaterialStudyRepeatFragment.createArguments(subTopic.id)
            )
        },
        onMenuItemClick = { subTopic, itemId ->
            when (itemId) {
                R.id.option_repeat -> {
                    viewModel.updateSelectionState(subTopic.id, true, subTopic.topicId)
                    notificationManager.scheduleTopicRepeatNotifications(
                        topicId = subTopic.id,
                        topicName = subTopic.name,
                        message = "Не забудьте повторить ${subTopic.name}!"
                    )
                }

                R.id.option_repeat_later -> {
                    viewModel.updateSelectionState(subTopic.id, false, subTopic.topicId)
                    notificationManager.cancelNotifications(
                        topicId = subTopic.id,
                        topicName = subTopic.name,
                        message = "Не забудьте повторить ${subTopic.name}!"
                    )
                }

                R.id.option_reset_progress -> {
                    viewModel.resetProgress(subTopic.id, subTopic.topicId)
                    notificationManager.cancelNotifications(
                        topicId = subTopic.id,
                        topicName = subTopic.name,
                        message = "Не забудьте повторить ${subTopic.name}!"
                    )
                }
            }
        }
    )

    override fun onAttach(context: Context) {
        (requireActivity().application as TutorialApplication).appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (requireActivity().application as TutorialApplication).appComponent.inject(this)
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
                is SubTopicsRepeatScreenState.Content -> showContent(screenState)
                SubTopicsRepeatScreenState.Error -> showError()
                SubTopicsRepeatScreenState.Loading -> showLoading()
            }
        }
    }

    private fun initializeAdapter() {
        binding.rvSubTopicsList.adapter = subTopicAdapter
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun showContent(screenState: SubTopicsRepeatScreenState.Content) {
        binding.pbProgressBar.isVisible = false
        binding.rvSubTopicsList.isVisible = true
        binding.tvErrorMessage.isVisible = false
        binding.toolbar.title = requireArguments().getString(TOPIC_NAME) ?: ""
        subTopicAdapter.subTopics.clear()
        subTopicAdapter.subTopics.addAll(screenState.subTopics)
        subTopicAdapter.notifyDataSetChanged()
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
