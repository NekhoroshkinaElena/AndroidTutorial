package com.example.androidtutorial2.sub_themes.ui

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
import com.example.androidtutorial2.databinding.FragmentSubThemesBinding
import com.example.androidtutorial2.material_study.ui.MaterialStudyFragment
import com.example.androidtutorial2.sub_themes.ui.adapter.SubThemesAdapter
import javax.inject.Inject

class SubThemesFragment : BaseFragment<FragmentSubThemesBinding>(
    FragmentSubThemesBinding::inflate
) {

    @Inject
    lateinit var viewModel: SubThemesViewModel

    private val subThemeAdapter = SubThemesAdapter { subThemes ->
        findNavController().navigate(
            R.id.action_subThemesFragment_to_materialStudyFragment,
            MaterialStudyFragment.createArguments(subThemes)
        )
    }

    override fun onAttach(context: Context) {
        (requireActivity().application as TutorialApplication).appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val themeId: Int = requireArguments().getInt(THEME_ID)
        viewModel.getSubThemes(themeId)

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
                is SubThemesScreenState.Content -> showContent(screenState)
                SubThemesScreenState.Error -> showError()
                SubThemesScreenState.Loading -> showLoading()
            }
        }
    }

    private fun initializeAdapter() {
        binding.rvSubThemesList.adapter = subThemeAdapter
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun showContent(screenState: SubThemesScreenState.Content) {
        binding.pbProgressBar.isVisible = false
        binding.rvSubThemesList.isVisible = true
        binding.tvErrorMessage.isVisible = false
        binding.toolbar.title = requireArguments().getString(THEME_NAME) ?: ""
        subThemeAdapter.subthemes.clear()
        subThemeAdapter.subthemes.addAll(screenState.listThemes)
        subThemeAdapter.notifyDataSetChanged()
    }

    private fun showLoading() {
        binding.pbProgressBar.isVisible = true
        binding.rvSubThemesList.isVisible = false
        binding.tvErrorMessage.isVisible = false
    }

    private fun showError() {
        binding.pbProgressBar.isVisible = false
        binding.rvSubThemesList.isVisible = false
        binding.tvErrorMessage.isVisible = true
    }

    companion object {
        private const val THEME_ID = "theme_id"
        private const val THEME_NAME = "theme_name"

        fun createArgs(themeId: Int, themeName: String): Bundle {
            return bundleOf(THEME_ID to themeId, THEME_NAME to themeName)
        }
    }
}
