package com.example.androidtutorial2.themes.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.androidtutorial2.TutorialApplication
import com.example.androidtutorial2.databinding.FragmentThemesBinding
import com.example.androidtutorial2.themes.ui.adapter.ThemesAdapter
import javax.inject.Inject

class ThemesFragment : Fragment() {

    @Inject
    lateinit var viewModel: ThemesViewModel

    private var _binding: FragmentThemesBinding? = null
    private val binding get() = _binding!!

    private val themeAdapter = ThemesAdapter()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as TutorialApplication).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentThemesBinding.inflate(layoutInflater)
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
                is ThemesScreenState.Content -> showContent(screenState)
                ThemesScreenState.Error -> showError()
                ThemesScreenState.Loading -> showLoading()
            }
        }
    }

    private fun initializeAdapter() {
        binding.rvThemesList.adapter = themeAdapter
    }

    private fun showContent(screenState: ThemesScreenState.Content) {
        binding.pbProgressBar.isVisible = false
        binding.rvThemesList.isVisible = true
        binding.tvErrorMessage.isVisible = false
        themeAdapter.themes.clear()
        themeAdapter.themes.addAll(screenState.listThemes)
        themeAdapter.notifyDataSetChanged()
    }

    private fun showLoading() {
        binding.pbProgressBar.isVisible = true
        binding.rvThemesList.isVisible = false
        binding.tvErrorMessage.isVisible = false
    }

    private fun showError() {
        binding.pbProgressBar.isVisible = false
        binding.rvThemesList.isVisible = false
        binding.tvErrorMessage.isVisible = true
    }
}
