package com.example.androidtutorial2.themes.ui

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.androidtutorial2.R
import com.example.androidtutorial2.TutorialApplication
import com.example.androidtutorial2.databinding.FragmentThemesBinding
import com.example.androidtutorial2.theme.ui.ThemeFragment
import com.example.androidtutorial2.themes.ui.adapter.ThemesAdapter
import javax.inject.Inject

class ThemesFragment : Fragment() {

    @Inject
    lateinit var viewModel: ThemesViewModel

    private var _binding: FragmentThemesBinding? = null
    private val binding get() = _binding!!

    private val themeAdapter = ThemesAdapter { theme ->
        if (!theme.blocked) {
            findNavController().navigate(
                R.id.action_studyFragment_to_themeFragment,
                ThemeFragment.createArguments(theme)
            )
        } else {
            Toast.makeText(
                requireActivity(),
                "Данные темы будут доступны позже",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onAttach(context: Context) {
        (requireActivity().application as TutorialApplication).appComponent.inject(this)
        super.onAttach(context)
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

    @SuppressLint("NotifyDataSetChanged")
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = ThemesFragment()
    }
}
