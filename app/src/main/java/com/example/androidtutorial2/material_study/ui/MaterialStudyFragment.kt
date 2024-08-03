package com.example.androidtutorial2.material_study.ui

import android.content.Context
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.androidtutorial2.TutorialApplication
import com.example.androidtutorial2.databinding.FragmentMaterialStudyBinding
import com.example.androidtutorial2.sub_themes.domain.SubTheme
import com.example.androidtutorial2.utils.TagHandler
import com.google.android.material.bottomsheet.BottomSheetBehavior
import javax.inject.Inject

class MaterialStudyFragment : Fragment() {

    @Inject
    lateinit var viewModel: MaterialStudyViewModel

    private var _binding: FragmentMaterialStudyBinding? = null
    private val binding get() = _binding!!

    private var bottomSheetContainer: ConstraintLayout? = null
    private var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout?>? = null

    override fun onAttach(context: Context) {
        (requireActivity().application as TutorialApplication).appComponent.inject(this)
        super.onAttach(context)
    }

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

        bottomSheetContainer = binding.standardBottomSheet
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheetContainer!!)

        bottomSheetBehavior!!.state = BottomSheetBehavior.STATE_HIDDEN

        val subTheme: SubTheme? = requireArguments().getParcelable(MATERIAL_TO_STUDY)
        binding.tvThemeDescription.text =
            Html.fromHtml(
                subTheme?.materialStudy,
                null,
                TagHandler()
            )
        binding.toolbar.title = subTheme?.name

        viewModel.get()
    }

    private fun initializeListeners() {
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        binding.questions.setOnClickListener {
            bottomSheetBehavior?.state = BottomSheetBehavior.STATE_EXPANDED
        }

        binding.ivClose.setOnClickListener {
            bottomSheetBehavior?.state = BottomSheetBehavior.STATE_HIDDEN
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val MATERIAL_TO_STUDY = "material_to_study"
        fun createArguments(subTheme: SubTheme): Bundle =
            bundleOf(MATERIAL_TO_STUDY to subTheme)
    }
}
