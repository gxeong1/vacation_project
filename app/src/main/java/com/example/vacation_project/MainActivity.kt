package com.example.vacation_project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.vacation_project.ui.theme.VacationprojectTheme
import com.example.vacation_project.Screen.CommunityScreen
import com.example.vacation_project.Screen.ProfileScreen
import com.example.vacation_project.Screen.RankScreen
import com.example.vacation_project.Screen.SearchScreen
import androidx.compose.material3.*
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.currentBackStackEntryAsState
import org.checkerframework.common.subtyping.qual.Bottom

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VacationprojectTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(

        bottomBar = { BottomNav(navController = navController) }
    ) {
        Box(Modifier.padding(it)){
            NavigationGraph(navController = navController)
        }
    }
}

@Composable
fun BottomNav(navController: NavHostController) {
    val items = listOf(
        BottomNavItem.Community,
        BottomNavItem.Search,
        BottomNavItem.Rank,
        BottomNavItem.Profile
    )

    val selectedColor = colorResource(id = R.color.color_selected)
    val unselectedColor = colorResource(id = R.color.color_unselected)

    NavigationBar(
        containerColor = Color.White,
        contentColor = Color(0xFF3F414E)
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(id = if (currentRoute == item.screenRoute) item.selectedIcon else item.icon),
                        modifier = Modifier
                            .width(24.dp)
                            .height(24.dp),
                        contentDescription = null,
                        tint = if (currentRoute == item.screenRoute) selectedColor else unselectedColor
                    )
                },
                label = { Text(stringResource(id = item.title), fontSize = 12.sp) },
                selected = currentRoute == item.screenRoute,
                onClick = {
                    navController.navigate(item.screenRoute) {
                        navController.graph.startDestinationRoute?.let {
                            popUpTo(it) { saveState = true }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}

@Composable
fun NavigationGraph(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(navController = navController, startDestination = BottomNavItem.Community.screenRoute, Modifier.fillMaxSize()) {
        composable(BottomNavItem.Community.screenRoute) {
            CommunityScreen()
        }
        composable(BottomNavItem.Search.screenRoute) {
            SearchScreen()
        }
        composable(BottomNavItem.Rank.screenRoute) {
            RankScreen()
        }
        composable(BottomNavItem.Profile.screenRoute) {
            ProfileScreen()
        }
    }
}

sealed class BottomNavItem(
    @StringRes val title: Int,
    @DrawableRes val icon: Int,
    @DrawableRes val selectedIcon: Int,
    val screenRoute: String
) {
    object Community : BottomNavItem(R.string.community, R.drawable.community,R.drawable.community_selected, Routes.CommunityScreen)
    object Search : BottomNavItem(R.string.search, R.drawable.search, R.drawable.search_selected,Routes.SearchScreen)
    object Rank : BottomNavItem(R.string.rank, R.drawable.rank, R.drawable.rank_selected,Routes.RankScreen)
    object Profile : BottomNavItem(R.string.profile, R.drawable.profile, R.drawable.profile_selected,Routes.ProfileScreen)
}

object Routes {
    const val CommunityScreen = "CommunityScreen"
    const val SearchScreen = "SearchScreen"
    const val RankScreen = "RankScreen"
    const val ProfileScreen = "ProfileScreen"
}