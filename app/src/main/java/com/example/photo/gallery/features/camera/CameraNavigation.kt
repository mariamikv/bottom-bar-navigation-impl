package com.example.photo.gallery.features.camera

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object CameraRoute

fun NavController.navigateToCamera(
    navOptions: NavOptions,
) = navigate(
    route = CameraRoute,
    navOptions = navOptions,
)

fun NavGraphBuilder.cameraScreen() {
    composable<CameraRoute> {
        CameraScreen()
    }
}
