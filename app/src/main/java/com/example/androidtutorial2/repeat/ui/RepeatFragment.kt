package com.example.androidtutorial2.repeat.ui

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.example.androidtutorial2.R
import com.example.androidtutorial2.TutorialApplication
import com.example.androidtutorial2.base.BaseFragment
import com.example.androidtutorial2.databinding.FragmentRepeatBinding
import com.example.androidtutorial2.repeat.ui.adapter.RepeatThemesAdapter
import com.example.androidtutorial2.sub_themes.ui.SubThemesFragment
import javax.inject.Inject

class RepeatFragment : BaseFragment<FragmentRepeatBinding>(
    FragmentRepeatBinding::inflate
) {

    @Inject
    lateinit var viewModel: RepeatViewModel

    private val themeAdapter = RepeatThemesAdapter { theme ->
        if (!theme.blocked) {
            findNavController().navigate(
                R.id.action_studyFragment_to_subThemesFragment,
                SubThemesFragment.createArgs(theme.id, theme.name)
            )
        } else {
            Toast.makeText(
                requireActivity(),
                getString(R.string.theme_lock),
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
        binding.rvRepeatThemeList.adapter = themeAdapter
    }


    @SuppressLint("NotifyDataSetChanged")
    private fun showContent(screenState: RepeatScreenState.Content) {
        binding.pbProgressBar.isVisible = false
        binding.rvRepeatThemeList.isVisible = true
        binding.tvErrorMessage.isVisible = false
        Log.i("TAG2", "showContent: ${screenState.listThemes}")
        themeAdapter.themes.clear()
        themeAdapter.themes.addAll(screenState.listThemes)
        themeAdapter.notifyDataSetChanged()
    }

    private fun showLoading() {
        binding.pbProgressBar.isVisible = true
        binding.rvRepeatThemeList.isVisible = false
        binding.tvErrorMessage.isVisible = false
    }

    private fun showError() {
        binding.pbProgressBar.isVisible = false
        binding.rvRepeatThemeList.isVisible = false
        binding.tvErrorMessage.isVisible = true
    }

    companion object {
        fun newInstance() = RepeatFragment()
    }
}
