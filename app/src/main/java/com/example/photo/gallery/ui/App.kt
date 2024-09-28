package com.example.photo.gallery.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.photo.gallery.R
import com.example.photo.gallery.navigation.TopLevelDestination

@Composable
fun App(
  appState: AppState,
  modifier: Modifier = Modifier,
) {
  val shouldShowGradientBackground =
    appState.currentTopLevelDestination == TopLevelDestination.GALLERY
  var showSettingsDialog by rememberSaveable { mutableStateOf(false) }

  val snackbarHostState = remember { SnackbarHostState() }

  App(
    appState = appState,
    snackbarHostState = snackbarHostState,
    showSettingsDialog = showSettingsDialog,
    onSettingsDismissed = { showSettingsDialog = false },
    onTopAppBarActionClick = { showSettingsDialog = true },
  )
}

@Composable
@OptIn(
  ExperimentalMaterial3Api::class,
  ExperimentalComposeUiApi::class,
)
internal fun App(
  appState: AppState,
  snackbarHostState: SnackbarHostState,
  showSettingsDialog: Boolean,
  onSettingsDismissed: () -> Unit,
  onTopAppBarActionClick: () -> Unit,
  modifier: Modifier = Modifier,
) {
  val currentDestination = appState.currentDestination

  NiaNavigationSuiteScaffold(
    navigationSuiteItems = {
      appState.topLevelDestinations.forEach { destination ->
        val selected = currentDestination
          .isRouteInHierarchy(destination.route)
        item(
          selected = selected,
          onClick = { appState.navigateToTopLevelDestination(destination) },
          icon = {
            Icon(
              imageVector = destination.unselectedIcon,
              contentDescription = null,
            )
          },
          selectedIcon = {
            Icon(
              imageVector = destination.selectedIcon,
              contentDescription = null,
            )
          },
          label = { Text(stringResource(destination.iconTextId)) },
          modifier =
          Modifier
            .testTag("NiaNavItem")
            .then(if (hasUnread) Modifier.notificationDot() else Modifier),
        )
      }
    },
    windowAdaptiveInfo = windowAdaptiveInfo,
  ) {
    Scaffold(
      modifier = modifier.semantics {
        testTagsAsResourceId = true
      },
      containerColor = Color.Transparent,
      contentColor = MaterialTheme.colorScheme.onBackground,
      contentWindowInsets = WindowInsets(0, 0, 0, 0),
      snackbarHost = { SnackbarHost(snackbarHostState) },
    ) { padding ->
      Column(
        Modifier
          .fillMaxSize()
          .padding(padding)
          .consumeWindowInsets(padding)
          .windowInsetsPadding(
            WindowInsets.safeDrawing.only(
              WindowInsetsSides.Horizontal,
            ),
          ),
      ) {
        // Show the top app bar on top level destinations.
        val destination = appState.currentTopLevelDestination
        var shouldShowTopAppBar = false

        if (destination != null) {
          shouldShowTopAppBar = true
          NiaTopAppBar(
            titleRes = destination.titleTextId,
            navigationIcon = NiaIcons.Search,
            navigationIconContentDescription = stringResource(
              id = settingsR.string.feature_settings_top_app_bar_navigation_icon_description,
            ),
            actionIcon = NiaIcons.Settings,
            actionIconContentDescription = stringResource(
              id = settingsR.string.feature_settings_top_app_bar_action_icon_description,
            ),
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
              containerColor = Color.Transparent,
            ),
            onActionClick = { onTopAppBarActionClick() },
            onNavigationClick = { appState.navigateToSearch() },
          )
        }

        Box(
          // Workaround for https://issuetracker.google.com/338478720
          modifier = Modifier.consumeWindowInsets(
            if (shouldShowTopAppBar) {
              WindowInsets.safeDrawing.only(WindowInsetsSides.Top)
            } else {
              WindowInsets(0, 0, 0, 0)
            },
          ),
        ) {
          NiaNavHost(
            appState = appState,
            onShowSnackbar = { message, action ->
              snackbarHostState.showSnackbar(
                message = message,
                actionLabel = action,
                duration = Short,
              ) == ActionPerformed
            },
          )
        }

        // TODO: We may want to add padding or spacer when the snackbar is shown so that
        //  content doesn't display behind it.
      }
    }
  }
}

private fun Modifier.notificationDot(): Modifier =
  composed {
    val tertiaryColor = MaterialTheme.colorScheme.tertiary
    drawWithContent {
      drawContent()
      drawCircle(
        tertiaryColor,
        radius = 5.dp.toPx(),
        // This is based on the dimensions of the NavigationBar's "indicator pill";
        // however, its parameters are private, so we must depend on them implicitly
        // (NavigationBarTokens.ActiveIndicatorWidth = 64.dp)
        center = center + Offset(
          64.dp.toPx() * .45f,
          32.dp.toPx() * -.45f - 6.dp.toPx(),
        ),
      )
    }
  }

private fun NavDestination?.isRouteInHierarchy(route: KClass<*>) =
  this?.hierarchy?.any {
    it.hasRoute(route)
  } ?: false
