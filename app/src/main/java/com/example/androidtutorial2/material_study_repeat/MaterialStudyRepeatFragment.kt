package com.example.androidtutorial2.material_study_repeat

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.example.androidtutorial2.R
import com.example.androidtutorial2.TutorialApplication
import com.example.androidtutorial2.base.BaseFragment
import com.example.androidtutorial2.databinding.FragmentMaterialStudyRepeatBinding
import com.example.androidtutorial2.notifications.NotificationsManager
import com.example.androidtutorial2.sub_topics.domain.model.SubTopic
import javax.inject.Inject

private const val MATERIAL_TO_STUDY_REPEAT = "material_to_study_repeat_id"
private const val IS_FROM_NOTIFICATION = "is_from_notification"

class MaterialStudyRepeatFragment :
    BaseFragment<FragmentMaterialStudyRepeatBinding, MaterialStudyRepeatViewModel>(
        FragmentMaterialStudyRepeatBinding::inflate
    ) {

    private var isFromNotification: Boolean = false

    @Inject
    lateinit var notificationManager: NotificationsManager

    override fun onAttach(context: Context) {
        (requireActivity().application as TutorialApplication).appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val subTopicId: Int = requireArguments().getInt(MATERIAL_TO_STUDY_REPEAT)
        isFromNotification = requireArguments().getBoolean(IS_FROM_NOTIFICATION, false)

        viewModel.showMaterialStudy(subTopicId)

        initializeObservers()

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    val currentState = viewModel.screenState.value
                    if (currentState is MaterialStudyRepeatScreenState.Content) {
                        handleNavigation(currentState.subTopic)
                    }
                }
            }
        )
    }

    private fun initializeListeners(subTopic: SubTopic) {
        binding.toolbar.setNavigationOnClickListener {
            handleNavigation(subTopic)
        }

        binding.ibDone.setOnClickListener {
            viewModel.updateNumberRepetitions(subTopic.id)
            notificationManager.scheduleTopicRepeatNotifications(
                topicId = subTopic.id,
                topicName = subTopic.name,
                message = getString(R.string.reminder_message, subTopic.name),
                currentRepetition = subTopic.numberRepetitions + 1
            )
            findNavController().navigateUp()
        }

        binding.ibClose.setOnClickListener {
            handleNavigation(subTopic)
        }
    }

    private fun initializeObservers() {
        viewModel.screenState.observe(viewLifecycleOwner) { screenState ->
            when (screenState) {
                is MaterialStudyRepeatScreenState.Content -> showContent(screenState)
                is MaterialStudyRepeatScreenState.Loading -> showLoading()
                is MaterialStudyRepeatScreenState.Error -> showError(screenState.message)
            }
        }
    }

    private fun showContent(screenState: MaterialStudyRepeatScreenState.Content) {
        initializeListeners(screenState.subTopic)

        binding.pbProgressBar.isVisible = false
        binding.svContent.isVisible = true
        binding.tvErrorMessage.isVisible = false

        binding.toolbar.title = screenState.subTopic.name


        val htmlContent = screenState.subTopic.materialStudy

        val cssStyle = screenState.cssStyle

        val styledHtmlContent = getString(R.string.html_template, cssStyle, htmlContent)

        binding.webViewContent.loadDataWithBaseURL(
            null,
            styledHtmlContent,
            "text/html",
            "UTF-8",
            null
        )
    }

    private fun showLoading() {
        binding.pbProgressBar.isVisible = true
        binding.svContent.isVisible = false
        binding.tvErrorMessage.isVisible = false
    }

    private fun showError(message: String) {
        binding.pbProgressBar.isVisible = false
        binding.svContent.isVisible = false
        binding.tvErrorMessage.text = message
        binding.tvErrorMessage.isVisible = true
    }

    private fun showExitOptionsDialog(subTopic: SubTopic) {
        val alertDialog = AlertDialog.Builder(requireActivity(), R.style.CustomAlertDialogTheme)
            .setTitle(getString(R.string.title_select_action))
            .setMessage(getString(R.string.message_select_action))
            .setPositiveButton(getString(R.string.postpone_repetition)) { dialog, _ ->
                viewModel.postponeRepetition(subTopic.id)
                findNavController().navigateUp()
            }
            .setNeutralButton(getString(R.string.remind_later)) { dialog, _ ->
                findNavController().navigateUp()
            }
            .setNegativeButton(getString(R.string.reset_progress)) { dialog, _ ->
                viewModel.resetProgress(subTopic.id)
                findNavController().navigateUp()
            }
            .setCancelable(true)
            .create()

        alertDialog.show()

        alertDialog.window?.setBackgroundDrawableResource(R.drawable.rounded_dialog_background)
    }

    private fun handleNavigation(subTopic: SubTopic) {
        if (isFromNotification) {
            showExitOptionsDialog(subTopic)
        } else {
            findNavController().navigateUp()
        }
    }

    companion object {
        fun createArguments(subTopicId: Int, isFromNotification: Boolean = false): Bundle =
            bundleOf(
                MATERIAL_TO_STUDY_REPEAT to subTopicId,
                IS_FROM_NOTIFICATION to isFromNotification
            )
    }
}
