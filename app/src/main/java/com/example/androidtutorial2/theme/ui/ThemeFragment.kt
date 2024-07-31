package com.example.androidtutorial2.theme.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.androidtutorial2.R
import com.example.androidtutorial2.databinding.FragmentThemeBinding
import com.example.androidtutorial2.sub_themes.ui.SubThemesFragment
import com.example.androidtutorial2.themes.domain.model.Theme

class ThemeFragment : Fragment() {

    private var _binding: FragmentThemeBinding? = null
    private val binding get() = _binding!!

    private var theme: Theme? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentThemeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        theme = requireArguments().getParcelable(THEME)
        initializeListeners()
    }

    private fun initializeListeners() {
        binding.tvLearn.setOnClickListener {
            findNavController().navigate(
                R.id.action_themeFragment_to_subThemesFragment,
                SubThemesFragment.createArgs(themeId = theme!!.id)
            )
        }

        binding.tvRepeat.setOnClickListener {
            findNavController().navigate(
                R.id.action_themeFragment_to_repeatFragment
            )
        }

        binding.tvTests.setOnClickListener {
            findNavController().navigate(
                R.id.action_themeFragment_to_testsFragment
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val THEME = "theme"
        fun createArguments(theme: Theme): Bundle = bundleOf(THEME to theme)
    }
}
