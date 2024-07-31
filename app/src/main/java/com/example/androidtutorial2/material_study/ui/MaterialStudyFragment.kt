package com.example.androidtutorial2.material_study.ui

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.androidtutorial2.databinding.FragmentMaterialStudyBinding
import com.example.androidtutorial2.sub_themes.domain.SubTheme
import com.example.androidtutorial2.utils.TagHandler


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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeListeners()

        val subTheme: SubTheme? = requireArguments().getParcelable(MATERIAL_TO_STUDY)
        binding.tvThemeDescription.text =
            Html.fromHtml(
                subTheme?.materialStudy,
                null,
                TagHandler()
            )
        binding.toolbar.title = subTheme?.name
    }

    private fun initializeListeners() {
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    companion object {
        private const val MATERIAL_TO_STUDY = "material_to_study"
        fun createArguments(subTheme: SubTheme): Bundle =
            bundleOf(MATERIAL_TO_STUDY to subTheme)
    }
}
