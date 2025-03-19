package geeky.saif.composektordi.utility.appbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import geeky.saif.composektordi.R
import geeky.saif.composektordi.utility.constants.AppConstants
import kotlinx.coroutines.CoroutineScope
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GlobalAppBar(
    title: String,
    onActionClicked: () -> Unit,
    onNavigationIconClick: () -> Unit,
    navigationIcon: Int, // Custom icon resource
    drawerState: DrawerState, // Pass DrawerState
    scope: CoroutineScope // Pass CoroutineScope to control drawer
) {
    TopAppBar(
        title = { Text(title) },
        modifier = Modifier.fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.background),
        navigationIcon = {
            IconButton(onClick = {
                // Trigger the drawer open on icon click
                onNavigationIconClick()
                scope.launch {
                    drawerState.open()
                }
            }) {
                // Use a custom icon from your resources
                Image(
                    painter = painterResource(id = navigationIcon), // Custom icon resource passed here
                    contentDescription = "Navigation Icon"
                )
            }
        },
        actions = {
            IconButton(onClick = onActionClicked) {
                Image(
                    painter = painterResource(id = R.drawable.app_icon),
                    contentDescription = "profile",
                )
            }
        }
    )
}

@Preview
@Composable
fun PreviewGlobalAppBar() {
    GlobalAppBar(
        title = AppConstants.APP_NAME,
        onActionClicked = { /* Handle search action */ },
        onNavigationIconClick = { /* Handle navigation icon click */ },
        navigationIcon = R.drawable.app_icon, // Example custom icon resource
        drawerState = DrawerState(DrawerValue.Closed), // Provide DrawerState (used in your screen)
        scope = rememberCoroutineScope() // Provide a coroutine scope (used in your screen)
    )
}
