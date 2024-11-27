package com.example.androidtutorial2.material_study.ui

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.example.androidtutorial2.R
import com.example.androidtutorial2.TutorialApplication
import com.example.androidtutorial2.base.BaseFragment
import com.example.androidtutorial2.databinding.FragmentMaterialStudyBinding
import com.example.androidtutorial2.material_study.ui.adapter.QuestionAdapter
import com.google.android.material.bottomsheet.BottomSheetBehavior

class MaterialStudyFragment : BaseFragment<FragmentMaterialStudyBinding, MaterialStudyViewModel>(
    FragmentMaterialStudyBinding::inflate
) {

    private var bottomSheetContainer: ConstraintLayout? = null
    private var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout?>? = null

    private val questionsAdapter = QuestionAdapter { question, binding ->
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

        val subThemeId: Int = requireArguments().getInt(MATERIAL_TO_STUDY_ID)

        viewModel.showMaterialStudy(subThemeId)
    }

    private fun initializeListeners() {
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        binding.btQuestions.setOnClickListener {
            bottomSheetBehavior?.state = BottomSheetBehavior.STATE_EXPANDED
        }

        binding.ivClose.setOnClickListener {
            bottomSheetBehavior?.state = BottomSheetBehavior.STATE_HIDDEN
        }

        binding.webViewContent.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                // После загрузки контента, отображаем кнопку
                binding.btQuestions.isVisible = true
            }
        }
    }

    private fun initializeObservers() {
        viewModel.screenState.observe(viewLifecycleOwner) { screenState ->
            when (screenState) {
                is QuestionsScreenState.Content -> showContent(screenState = screenState)
                else -> showLoading()
            }
        }
    }

    private fun initializeAdapter() {
        binding.rvAnswer.adapter = questionsAdapter
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun showContent(screenState: QuestionsScreenState.Content) {
        binding.pbProgressBar.isVisible = false
        binding.tvErrorMessage.isVisible = false
        binding.btQuestions.isVisible = false
        bottomSheetContainer = binding.standardBottomSheet
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheetContainer!!)

        bottomSheetBehavior!!.state = BottomSheetBehavior.STATE_HIDDEN


        binding.svContent.isVisible = true
        binding.standardBottomSheet.isVisible = true

        val htmlContent = screenState.subTheme.materialStudy

        val cssStyle = screenState.cssStyle

        val styledHtmlContent = getString(R.string.html_template, cssStyle, htmlContent)

        binding.webViewContent.loadDataWithBaseURL(
            null,
            styledHtmlContent,
            "text/html",
            "UTF-8",
            null
        )

        binding.toolbar.title = screenState.subTheme.name

        questionsAdapter.questions.clear()
        questionsAdapter.questions.addAll(screenState.listQuestions)
        questionsAdapter.notifyDataSetChanged()
    }

    private fun showLoading() {
        binding.pbProgressBar.isVisible = true
        binding.svContent.isVisible = false
        binding.tvErrorMessage.isVisible = false
        binding.standardBottomSheet.isVisible = false
    }

    private fun showError() {
        binding.pbProgressBar.isVisible = false
        binding.svContent.isVisible = false
        binding.tvErrorMessage.isVisible = true
        binding.standardBottomSheet.isVisible = false
    }

    companion object {
        private const val MATERIAL_TO_STUDY_ID = "material_to_study_id"
        fun createArguments(subThemeId: Int): Bundle =
            bundleOf(MATERIAL_TO_STUDY_ID to subThemeId)
    }
}
