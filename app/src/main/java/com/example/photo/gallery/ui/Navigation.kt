package com.example.photo.gallery.ui

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.NavigationRailItemDefaults
import androidx.compose.material3.adaptive.WindowAdaptiveInfo
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteItemColors
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffoldDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun RowScope.NavigationBarItem(
  selected: Boolean,
  onClick: () -> Unit,
  modifier: Modifier = Modifier,
  enabled: Boolean = true,
  alwaysShowLabel: Boolean = true,
  icon: @Composable () -> Unit,
  selectedIcon: @Composable () -> Unit = icon,
  label: @Composable (() -> Unit)? = null,
) {
  NavigationBarItem(
    selected = selected,
    onClick = onClick,
    icon = if (selected) selectedIcon else icon,
    modifier = modifier,
    enabled = enabled,
    label = label,
    alwaysShowLabel = alwaysShowLabel,
    colors = NavigationBarItemDefaults.colors(
      selectedIconColor = NavigationDefaults.navigationSelectedItemColor(),
      unselectedIconColor = NavigationDefaults.navigationContentColor(),
      selectedTextColor = NavigationDefaults.navigationSelectedItemColor(),
      unselectedTextColor = NavigationDefaults.navigationContentColor(),
      indicatorColor = NavigationDefaults.navigationIndicatorColor(),
    ),
  )
}

@Composable
fun NavigationBar(
  modifier: Modifier = Modifier,
  content: @Composable RowScope.() -> Unit,
) {
  NavigationBar(
    modifier = modifier,
    contentColor = NavigationDefaults.navigationContentColor(),
    tonalElevation = 0.dp,
    content = content,
  )
}

@Composable
fun NavigationRailItem(
  selected: Boolean,
  onClick: () -> Unit,
  modifier: Modifier = Modifier,
  enabled: Boolean = true,
  alwaysShowLabel: Boolean = true,
  icon: @Composable () -> Unit,
  selectedIcon: @Composable () -> Unit = icon,
  label: @Composable (() -> Unit)? = null,
) {
  NavigationRailItem(
    selected = selected,
    onClick = onClick,
    icon = if (selected) selectedIcon else icon,
    modifier = modifier,
    enabled = enabled,
    label = label,
    alwaysShowLabel = alwaysShowLabel,
    colors = NavigationRailItemDefaults.colors(
      selectedIconColor = NavigationDefaults.navigationSelectedItemColor(),
      unselectedIconColor = NavigationDefaults.navigationContentColor(),
      selectedTextColor = NavigationDefaults.navigationSelectedItemColor(),
      unselectedTextColor = NavigationDefaults.navigationContentColor(),
      indicatorColor = NavigationDefaults.navigationIndicatorColor(),
    ),
  )
}

@Composable
fun NavigationRail(
  modifier: Modifier = Modifier,
  header: @Composable (ColumnScope.() -> Unit)? = null,
  content: @Composable ColumnScope.() -> Unit,
) {
  NavigationRail(
    modifier = modifier,
    containerColor = Color.Transparent,
    contentColor = NavigationDefaults.navigationContentColor(),
    header = header,
    content = content,
  )
}

@Composable
fun NavigationSuiteScaffold(
  navigationSuiteItems: com.example.photo.gallery.ui.NavigationSuiteScope.() -> Unit,
  modifier: Modifier = Modifier,
  windowAdaptiveInfo: WindowAdaptiveInfo = currentWindowAdaptiveInfo(),
  content: @Composable () -> Unit,
) {
  val layoutType = NavigationSuiteScaffoldDefaults
    .calculateFromAdaptiveInfo(windowAdaptiveInfo)
  val navigationSuiteItemColors = NavigationSuiteItemColors(
    navigationBarItemColors = NavigationBarItemDefaults.colors(
      selectedIconColor = NavigationDefaults.navigationSelectedItemColor(),
      unselectedIconColor = NavigationDefaults.navigationContentColor(),
      selectedTextColor = NavigationDefaults.navigationSelectedItemColor(),
      unselectedTextColor = NavigationDefaults.navigationContentColor(),
      indicatorColor = NavigationDefaults.navigationIndicatorColor(),
    ),
    navigationRailItemColors = NavigationRailItemDefaults.colors(
      selectedIconColor = NavigationDefaults.navigationSelectedItemColor(),
      unselectedIconColor = NavigationDefaults.navigationContentColor(),
      selectedTextColor = NavigationDefaults.navigationSelectedItemColor(),
      unselectedTextColor = NavigationDefaults.navigationContentColor(),
      indicatorColor = NavigationDefaults.navigationIndicatorColor(),
    ),
    navigationDrawerItemColors = NavigationDrawerItemDefaults.colors(
      selectedIconColor = NavigationDefaults.navigationSelectedItemColor(),
      unselectedIconColor = NavigationDefaults.navigationContentColor(),
      selectedTextColor = NavigationDefaults.navigationSelectedItemColor(),
      unselectedTextColor = NavigationDefaults.navigationContentColor(),
    ),
  )

  NavigationSuiteScaffold(
    navigationSuiteItems = {
      NavigationSuiteScope(
        navigationSuiteScope = this,
        navigationSuiteItemColors = navigationSuiteItemColors,
      ).run(navigationSuiteItems)
    },
    layoutType = layoutType,
    containerColor = Color.Transparent,
    navigationSuiteColors = NavigationSuiteDefaults.colors(
      navigationBarContentColor = NavigationDefaults.navigationContentColor(),
      navigationRailContainerColor = Color.Transparent,
    ),
    modifier = modifier,
  ) {
    content()
  }
}

class NavigationSuiteScope internal constructor(
  private val navigationSuiteScope: NavigationSuiteScope,
  private val navigationSuiteItemColors: NavigationSuiteItemColors,
) {
  fun item(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: @Composable () -> Unit,
    selectedIcon: @Composable () -> Unit = icon,
    label: @Composable (() -> Unit)? = null,
  ) = navigationSuiteScope.item(
    selected = selected,
    onClick = onClick,
    icon = {
      if (selected) {
        selectedIcon()
      } else {
        icon()
      }
    },
    label = label,
    colors = navigationSuiteItemColors,
    modifier = modifier,
  )
}

object NavigationDefaults {
  @Composable
  fun navigationContentColor() = MaterialTheme.colorScheme.onSurfaceVariant

  @Composable
  fun navigationSelectedItemColor() = MaterialTheme.colorScheme.onPrimaryContainer

  @Composable
  fun navigationIndicatorColor() = MaterialTheme.colorScheme.primaryContainer
}
