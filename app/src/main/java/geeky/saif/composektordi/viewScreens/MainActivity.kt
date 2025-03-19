package geeky.saif.composektordi.viewScreens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import geeky.saif.composektordi.utility.constants.AppConstants
import geeky.saif.composektordi.utility.sharedPrefrence.PreferencesHelper
import geeky.saif.composektordi.viewScreens.homeUI.HomeActivity
import geeky.saif.composektordi.viewScreens.splashUI.SplashActivity
import geeky.saif.composektordi.viewScreens.splashUI.SplashScreen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            NavHost(navController, startDestination = AppConstants.SPLASH) {
                composable(AppConstants.SPLASH) { SplashActivity() }
                composable(AppConstants.HOME) { HomeActivity() }
            }
        }
    }
}

