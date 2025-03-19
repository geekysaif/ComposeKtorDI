package geeky.saif.composektordi.utility.navigationDrawer

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import geeky.saif.composektordi.R


@Composable
fun NavigationDrawer(
    onHomeClicked: () -> Unit,
    onSearchClicked: () -> Unit,
    onProfileClicked: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        // Drawer Header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.slider1), // Replace with your image
                contentDescription = "Drawer Header",
                modifier = Modifier.fillMaxSize()
            )
            Text(
                text = "App Name",
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(16.dp),
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White
            )
        }

        // Navigation Items
        NavigationItem(
            icon = R.drawable.img1, // Replace with your icon resource
            label = "Home",
            onClick = onHomeClicked
        )

        NavigationItem(
            icon = R.drawable.img2, // Replace with your icon resource
            label = "Search",
            onClick = onSearchClicked
        )

        NavigationItem(
            icon = R.drawable.img3, // Replace with your icon resource
            label = "Profile",
            onClick = onProfileClicked
        )
    }
}

@Composable
fun NavigationItem(icon: Any, label: String, onClick: () -> Unit) {

}
