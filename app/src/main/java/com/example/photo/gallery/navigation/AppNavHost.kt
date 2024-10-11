package com.example.photo.gallery.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.example.photo.gallery.features.gallery.GalleryRoute
import com.example.photo.gallery.ui.AppState

@Composable
fun AppNavHost(
  appState: AppState,
  modifier: Modifier = Modifier,
) {
  val navController = appState.navController
  NavHost(
    navController = navController,
    startDestination = GalleryRoute,
    modifier = modifier,
  ) {
//    GalleryScreen(
//      onTopicClick = navController::navigateToInterests
//    )
//    CameraScreen(
//      onTopicClick = navController::navigateToInterests,
//      onShowSnackbar = onShowSnackbar,
//    )
  }
}
