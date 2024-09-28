package com.example.photo.gallery.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.util.trace
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.example.photo.gallery.features.camera.navigateToCamera
import com.example.photo.gallery.features.gallery.navigateToGallery
import com.example.photo.gallery.navigation.TopLevelDestination
import kotlinx.coroutines.CoroutineScope

@Composable
fun rememberAppState(
  coroutineScope: CoroutineScope = rememberCoroutineScope(),
  navController: NavHostController = rememberNavController(),
): AppState {
  return remember(
    navController,
    coroutineScope,
  ) {
    AppState(
      navController = navController,
      coroutineScope = coroutineScope,
    )
  }
}

@Stable
class AppState(
  val navController: NavHostController,
  coroutineScope: CoroutineScope,
) {
  val currentDestination: NavDestination?
    @Composable get() = navController
      .currentBackStackEntryAsState().value?.destination

  val currentTopLevelDestination: TopLevelDestination?
    @Composable get() {
      return TopLevelDestination.entries.firstOrNull { topLevelDestination ->
        currentDestination?.hasRoute(
          route = topLevelDestination.route.toString(), //TODO don't know if it works
          arguments = null,
        ) ?: false
      }
    }

  /**
   * Map of top level destinations to be used in the TopBar, BottomBar and NavRail. The key is the
   * route.
   */
  val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.entries

  /**
   * UI logic for navigating to a top level destination in the app. Top level destinations have
   * only one copy of the destination of the back stack, and save and restore state whenever you
   * navigate to and from it.
   *
   * @param topLevelDestination: The destination the app needs to navigate to.
   */
  fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
    trace("Navigation: ${topLevelDestination.name}") {
      val topLevelNavOptions = navOptions {
        // Pop up to the start destination of the graph to
        // avoid building up a large stack of destinations
        // on the back stack as users select items
        popUpTo(navController.graph.findStartDestination().id) {
          saveState = true
        }
        // Avoid multiple copies of the same destination when
        // reselecting the same item
        launchSingleTop = true
        // Restore state when reselecting a previously selected item
        restoreState = true
      }

      when (topLevelDestination) {
        TopLevelDestination.CAMERA -> navController.navigateToCamera(topLevelNavOptions)
        TopLevelDestination.GALLERY -> navController.navigateToGallery(topLevelNavOptions)
      }
    }
  }
}
