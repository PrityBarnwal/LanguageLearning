package com.example.languageLearning

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.languageLearning.screen.LoginScreen
import com.example.languageLearning.screen.SignUpScreen
import com.example.languageLearning.screen.home.HomeScreen
import com.example.languageLearning.screen.home.ProfileScreen
import com.example.languageLearning.screen.home.SearchScreen
import com.example.languageLearning.ui.theme.NavigationAppTheme
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavigationAppTheme {
                val navController = rememberNavController()

                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .navigationBarsPadding(),
                    bottomBar = {
                        val navBackStackEntry by navController.currentBackStackEntryAsState()
                        val currentRoute = navBackStackEntry?.destination?.route
                        if (currentRoute !in listOf(LoginRoute::class.java.name, SignInRoute::class.java.name)) {
                            BottomNavigationBar(navController = navController)
                        }
                    },
                    content = { padding ->
                        Box(modifier = Modifier.padding(padding)) {
                            NavHost(
                                navController = navController,
                                startDestination = LoginRoute::class.java.name
                            ) {
                                composable(LoginRoute::class.java.name) {
                                    LoginScreen(navController = navController)
                                }
                                composable(SignInRoute::class.java.name) {
                                    SignUpScreen(navController = navController)
                                }
                                composable<HomeRoute> {
                                    val args = it.toRoute<HomeRoute>()
                                    HomeScreen(args = args)
                                }
                                composable(ProfileRoute::class.java.name) {
                                    ProfileScreen()
                                }
                                composable(SearchRoute::class.java.name) {
                                    SearchScreen()
                                }
                            }
                        }
                    }
                )
            }
        }
    }
}

@Serializable
object LoginRoute

@Serializable
object SignInRoute

@Serializable
data class HomeRoute(
    val email: String?=null,
    val password: String?=null
)

@Serializable
object SearchRoute

@Serializable
object ProfileRoute

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val items = listOf(HomeRoute, ProfileRoute, SearchRoute)
    var selectedPosition by remember { mutableIntStateOf(0) }
    BottomNavigation {
        items.forEachIndexed { index, route ->
            BottomNavigationItem(
                icon = {
                    when (route) {
                        HomeRoute -> Icon(Icons.Default.Home, contentDescription = "Home")
                        ProfileRoute -> Icon(Icons.Default.Person, contentDescription = "Profile")
                        SearchRoute -> Icon(Icons.Default.Search, contentDescription = "Search")
                    }
                },
                selected = selectedPosition == index,
                onClick = {
                    selectedPosition = index
                    navController.navigate(route::class.java.name) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}
