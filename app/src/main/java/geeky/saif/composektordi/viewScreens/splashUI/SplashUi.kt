package geeky.saif.composektordi.viewScreens.splashUI

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import geeky.saif.composektordi.utility.constants.AppConstants
import geeky.saif.composektordi.utility.loadImage.LoadImage
import geeky.saif.composektordi.utility.loadImage.LoadImage1
import geeky.saif.composektordi.viewScreens.homeUI.HomeActivity
import kotlinx.coroutines.delay

class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SplashScreen()
        }
    }
}

@Composable
fun SplashScreen() {
    val context = LocalContext.current
    var isVisible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        isVisible = true
        delay(2000) // Show splash for 2 seconds
        context.startActivity(Intent(context, HomeActivity::class.java))
        (context as? ComponentActivity)?.finish() // Close SplashActivity
    }

    Box(
        modifier = Modifier.fillMaxSize().background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        AnimatedVisibility(visible = isVisible, enter = fadeIn()) {
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    // Make the item clickable
            ) {
                LoadImage1(AppConstants.DEMO_IMAGE)
                Text(AppConstants.APP_NAME, fontSize = 32.sp, fontWeight = FontWeight.Bold)
            }

        }
    }
}
