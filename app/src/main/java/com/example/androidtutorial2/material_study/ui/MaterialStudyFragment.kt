package com.example.androidtutorial2.material_study.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.androidtutorial2.databinding.FragmentMaterialStudyBinding
import com.example.androidtutorial2.themes.domain.model.Theme

class MaterialStudyFragment : Fragment() {

    private var _binding: FragmentMaterialStudyBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMaterialStudyBinding.inflate(layoutInflater)
        return binding.root
    }

    companion object {
        private const val MATERIAL_TO_STUDY = "material_to_study"
        fun createArguments(theme: Theme): Bundle = bundleOf(MATERIAL_TO_STUDY to theme)
    }
}
