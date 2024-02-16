package com.example.l4_nav.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.l4_nav.AppViewModel
import com.example.l4_nav.screens.PantallaJuego
import com.example.l4_nav.screens.PantallaPrincipal

@Composable
fun MyAppNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    appViewModel: AppViewModel
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = AppScreens.PantallaPrincipal.route
    ) {
        composable(route = AppScreens.PantallaPrincipal.route){
            PantallaPrincipal(navController, appViewModel)
        }
        composable(route = AppScreens.PantallaJuego.route){
            PantallaJuego(navController, appViewModel)
        }
    }
}

