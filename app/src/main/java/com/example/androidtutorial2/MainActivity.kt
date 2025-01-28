package com.example.androidtutorial2

import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
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

        val showBottomNavigation =
            featureToggleManager.isEnabled(FeatureToggle.BOTTOM_NAVIGATION_BAR)
        binding.bottomNavigationView.isVisible = showBottomNavigation

        val fragmentsWithBottomNav = listOf(
            R.id.homeFragment,
            R.id.studyFragment,
            R.id.settingsFragment
        )

        if (showBottomNavigation) {
            navController.navigate(R.id.homeFragment)
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

        checkPermissionsAndScheduleNotifications()
    }

    private fun checkPermissionsAndScheduleNotifications() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (checkSelfPermission(android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(android.Manifest.permission.POST_NOTIFICATIONS),
                    NOTIFICATION_PERMISSION_REQUEST_CODE
                )
                return
            }
        }
    }

    companion object {
        private const val NOTIFICATION_PERMISSION_REQUEST_CODE = 1
    }
}
