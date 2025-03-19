package geeky.saif.composektordi.viewScreens.homeUI

import GlobalBottomBar
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.ui.platform.LocalContext
import geeky.saif.composektordi.utility.appbar.GlobalAppBar

import geeky.saif.composektordi.utility.constants.AppConstants

import geeky.saif.composektordi.utility.toast.ToastUtils
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.TextUnit
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.UnstableApi
import geeky.saif.composektordi.R
import geeky.saif.composektordi.utility.sharedPrefrence.PreferencesHelper
import geeky.saif.composektordi.viewScreens.PostScreenData
import geeky.saif.composektordi.viewScreens.ProductScreenData
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeActivity : ComponentActivity() {
    private lateinit var preferencesHelper: PreferencesHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        preferencesHelper = PreferencesHelper(this) // Initialize here
        enableEdgeToEdge()
        setContent {
            SharedPreferencesExample(preferencesHelper)
            PreviewAppBar()
        }
    }
}


@Preview
@Composable
fun PreviewAppBar() {
    MainScreen()
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val strContext = LocalContext.current

    var selectedTab by remember { mutableStateOf(AppConstants.HOME) }

    // Place the AppBar OUTSIDE the ModalNavigationDrawer
    Column(modifier = Modifier.fillMaxSize()) {
        GlobalAppBar(
            title = AppConstants.APP_NAME,
            onActionClicked = {
                ToastUtils.showCustomToast(strContext, "Logo Clicked")
            },
            onNavigationIconClick = {
                ToastUtils.showCustomToast(strContext, "Navigation Drawer Clicked")
                scope.launch { drawerState.open() }
            },
            navigationIcon = R.drawable.slider5, // Custom icon
            drawerState = drawerState,
            scope = scope
        )

        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                DrawerContent(
                    navController = navController,
                    closeDrawer = { scope.launch { drawerState.close() } }
                )
            }
        ) {
            Scaffold(
                bottomBar = {
                    Box(modifier = Modifier.height(120.dp)) {
                        GlobalBottomBar(
                            selectedTab = selectedTab,
                            onHomeClicked = {
                               if(selectedTab ==AppConstants.HOME){
                                   selectedTab =AppConstants.HOME
                               } else{
                                   selectedTab =AppConstants.HOME
                                   navController.navigate(AppConstants.HOME)
                               }

                                            },
                            onProductClicked = {
                                selectedTab = AppConstants.PRODUCTS
                                navController.navigate(AppConstants.PRODUCTS) },
                            onSettingClicked = { ToastUtils.showCustomToast(strContext, "Setting") },
                            onCategoryClicked = { ToastUtils.showCustomToast(strContext, "Category") }
                        )
                    }
                },
                content = { innerPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        NavHost(
                            navController = navController,
                            startDestination = AppConstants.HOME,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            composable(AppConstants.HOME) { PostScreenData() }
                            composable(AppConstants.PRODUCTS) { ProductScreenData() }
                            composable("Partners") { ScreenContent("Partners") }
                            composable("privacy_policy") { ScreenContent("Privacy Policy") }
                            composable("change_password") { ScreenContent("Change Password") }
                            composable("profile") { ScreenContent("Profile") }
                            composable("logout") { ScreenContent("Logout") }
                        }
                    }
                }
            )
        }
    }
}



@androidx.annotation.OptIn(UnstableApi::class)
@Composable
fun DrawerContent(navController: NavController, closeDrawer: () -> Unit) {
    Column {
        SetMenuItem("Profile", navController, closeDrawer)
        SetMenuItem("Change Password", navController, closeDrawer)
        SetMenuItem("Privacy Policy", navController, closeDrawer)
        SetMenuItem("Logout", navController, closeDrawer)
    }
}

@Composable
fun ScreenContent(title: String) {
    when (title) {
        "Home" -> {PostScreenData()}
        "PRODUCTS" ->  {ProductScreenData()}
        "Partners" -> Text("This is the $title screen", modifier = Modifier.padding(16.dp))
        "Change Password" -> Text("This is the $title screen", modifier = Modifier.padding(16.dp))
        "Profile" -> Text("This is the $title screen", modifier = Modifier.padding(16.dp))
        "Logout" -> Text("This is the $title screen", modifier = Modifier.padding(16.dp))
        "Privacy Policy" -> Text("This is the $title screen", modifier = Modifier.padding(16.dp))
        else -> {} // Handle default or unknown item
    }

}

@Composable
fun SetMenuItem(title: String, navController: NavController, closeDrawer: () -> Unit) {
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .fillMaxWidth(0.7f) // Set width to half of the screen
            .padding(5.dp)
            .clickable {
                // Handle the click event and show a toast message

                closeDrawer()

                // Handle navigation based on the title
                when (title) {
                    "Change Password" -> navController.navigate("change_password")
                    "Profile" -> navController.navigate("profile")
                    "Logout" -> navController.navigate("logout")
                    "Privacy Policy" -> navController.navigate("privacy_policy")
                    else -> {} // Handle default or unknown item
                }
            }
    ) {
        Row(
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,  // Centering content horizontally
            verticalAlignment = Alignment.CenterVertically // Centering content vertically
        ) {
            val icon = when (title) {
                "Privacy Policy" -> Icons.Filled.Person
                "Change Password" -> Icons.Filled.AccountBox
                "Profile" -> Icons.Filled.Person
                "Logout" -> Icons.Filled.ArrowDropDown
                else -> Icons.Filled.Menu // Default icon
            }

            Icon(icon, contentDescription = null, modifier = Modifier
                .height(30.dp)
                .width(30.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                modifier = Modifier,
                text = title,
                fontSize = TextUnit.Unspecified,
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun SharedPreferencesExample(preferencesHelper: PreferencesHelper) {
    val context = LocalContext.current
    preferencesHelper.putString("name","GeekySaif")
    val defaultString = preferencesHelper.getString("name", "GeekySaif")
    val defaultInt = preferencesHelper.getInt("age", 0)
    val defaultBool = preferencesHelper.getBoolean("isUserLoggedIn", false)
    Log.d("Name:-- ", defaultString)
}

