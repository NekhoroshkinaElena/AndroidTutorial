package com.example.androidtutorial2.sub_themes.ui

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.androidtutorial2.R
import com.example.androidtutorial2.TutorialApplication
import com.example.androidtutorial2.databinding.FragmentSubThemesBinding
import com.example.androidtutorial2.sub_themes.domain.SubTheme
import com.example.androidtutorial2.sub_themes.ui.adapter.SubThemesAdapter
import javax.inject.Inject

class SubThemesFragment : Fragment() {

    @Inject
    lateinit var viewModel: SubThemesViewModel

    private var _binding: FragmentSubThemesBinding? = null
    private val binding get() = _binding!!

    private val subThemeAdapter = SubThemesAdapter { _ ->
        findNavController().navigate(
            R.id.action_subThemesFragment_to_materialStudyFragment
        )
    }

    override fun onAttach(context: Context) {
        (requireActivity().application as TutorialApplication).appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSubThemesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeObservers()
        initializeAdapter()
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
        private const val SUB_THEMES = "sub_themes"

        fun createArgs(subThemes: List<SubTheme>?): Bundle {
            return bundleOf(SUB_THEMES to subThemes)
        }
    }
}
