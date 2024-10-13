package com.example.photo.gallery.features.gallery

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object GalleryRoute

fun NavController.navigateToGallery(
    navOptions: NavOptions,
) = navigate(
    route = GalleryRoute,
    navOptions = navOptions,
)

fun NavGraphBuilder.galleryScreen() {
    composable<GalleryRoute> {
        GalleryScreen()
    }
}
