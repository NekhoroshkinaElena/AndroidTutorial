package com.example.androidtutorial2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.fragment.NavHostFragment
import com.example.androidtutorial2.databinding.ActivityMainBinding
import com.example.androidtutorial2.feature_toggle.FeatureToggle
import com.example.androidtutorial2.feature_toggle.FeatureToggleManager
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var featureToggleManager: FeatureToggleManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (application as TutorialApplication).appComponent.inject(this)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragment_container) as NavHostFragment
        val navController = navHostFragment.navController

        binding.bottomNavigationView.selectedItemId = R.id.studyFragment

        val showBottomNavigation =
            featureToggleManager.isEnabled(FeatureToggle.BOTTOM_NAVIGATION_BAR)
        binding.bottomNavigationView.isVisible = showBottomNavigation

        val fragmentsWithBottomNav = listOf(
            R.id.homeFragment,
            R.id.studyFragment,
            R.id.settingsFragment
        )

        if (showBottomNavigation) {
            navController.addOnDestinationChangedListener { _, navDestination, _ ->
                binding.bottomNavigationView.isVisible =
                    fragmentsWithBottomNav.contains(navDestination.id)
            }
        }

        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.homeFragment -> {
                    navController.navigate(R.id.homeFragment)
                    true
                }

                R.id.studyFragment -> {
                    navController.navigate(R.id.studyFragment)
                    true
                }

                R.id.settingsFragment -> {
                    navController.navigate(R.id.settingsFragment)
                    true
                }

                else -> false
            }
        }
    }
}
