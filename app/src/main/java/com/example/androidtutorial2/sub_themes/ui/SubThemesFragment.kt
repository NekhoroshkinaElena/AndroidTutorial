package com.example.androidtutorial2.sub_themes.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.androidtutorial2.databinding.FragmentSubThemesBinding
import com.example.androidtutorial2.sub_themes.domain.SubTheme

class SubThemesFragment : Fragment() {
    private var _binding: FragmentSubThemesBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSubThemesBinding.inflate(layoutInflater)
        return binding.root
    }

    companion object {
        private const val SUB_THEMES = "sub_themes"

        fun createArgs(subThemes: List<SubTheme>?): Bundle {
            return bundleOf(SUB_THEMES to subThemes)
        }
    }
}
