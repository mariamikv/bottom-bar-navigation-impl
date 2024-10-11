package com.example.photo.gallery.features.me

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object MeRoute

fun NavController.navigateToMe(
    navOptions: NavOptions,
) = navigate(
    route = MeRoute,
    navOptions = navOptions,
)

fun NavGraphBuilder.meScreen(
    onTopicClick: (String) -> Unit,
) {
    composable<MeRoute> {
        // GalleryScreen(onTopicClick) on click callback
    }
}
