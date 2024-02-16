package com.example.l4_nav.navigation

sealed class AppScreens (val route: String) {
    object PantallaPrincipal: AppScreens("pantalla_principal")
    object PantallaJuego: AppScreens("pantalla_juego")
}