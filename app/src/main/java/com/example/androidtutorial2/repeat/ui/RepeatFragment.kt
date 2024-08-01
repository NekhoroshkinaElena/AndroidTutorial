package com.example.androidtutorial2.repeat.ui

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.animation.doOnEnd
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.androidtutorial2.R
import com.example.androidtutorial2.databinding.FragmentRepeatBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class RepeatFragment : Fragment() {

    private var _binding: FragmentRepeatBinding? = null
    private val binding get() = _binding!!

    private var isClickableQuestion: Boolean = true
    private var isClickableAnswer: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRepeatBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeListeners()
    }

    private fun initializeListeners() {

        binding.viewQuestion.setOnClickListener {
            if (isClickableQuestion) {
                flipCard(requireActivity(), binding.viewAnswer, binding.viewQuestion)
                isClickableQuestion = false
                lifecycleScope.launch {
                    delay(1000)
                    isClickableAnswer = true
                }
            }
        }

        binding.viewAnswer.setOnClickListener {
            if (isClickableAnswer) {
                flipCard(requireActivity(), binding.viewQuestion, binding.viewAnswer)
                isClickableAnswer = false
                lifecycleScope.launch {
                    delay(1000)
                    isClickableQuestion = true
                }
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun flipCard(context: Context, visibleView: View, inVisibleView: View) {
        try {
            visibleView.isVisible = true
            val scale = context.resources.displayMetrics.density
            val cameraDist = 8000 * scale
            visibleView.cameraDistance = cameraDist
            inVisibleView.cameraDistance = cameraDist
            val flipOutAnimatorSet =
                AnimatorInflater.loadAnimator(
                    context,
                    R.animator.flip_out
                ) as AnimatorSet
            flipOutAnimatorSet.setTarget(inVisibleView)
            val flipInAnimationSet =
                AnimatorInflater.loadAnimator(
                    context,
                    R.animator.flip_in
                ) as AnimatorSet
            flipInAnimationSet.setTarget(visibleView)
            flipOutAnimatorSet.start()
            flipInAnimationSet.start()
            flipInAnimationSet.doOnEnd {
                inVisibleView.isVisible = false
            }
        } catch (e: Exception) {
            Log.i("TAG2", "flipCard: ")
        }
    }

    companion object {
        fun newInstance() = RepeatFragment()
    }
}
