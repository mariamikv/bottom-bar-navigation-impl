package com.example.photo.gallery.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.photo.gallery.R
import com.example.photo.gallery.features.camera.CameraRoute
import com.example.photo.gallery.features.gallery.GalleryRoute
import com.example.photo.gallery.ui.icon.AppIcons
import kotlin.reflect.KClass

enum class TopLevelDestination(
  @DrawableRes val icon: Int,
  @StringRes val iconTextId: Int,
  @StringRes val titleTextId: Int,
  val route: KClass<*>,
) {
  CAMERA(
    icon = AppIcons.camera,
    iconTextId = R.string.camera_screen,
    titleTextId = R.string.camera_screen,
    route = CameraRoute::class,
  ),
  GALLERY(
    icon = AppIcons.gallery,
    iconTextId = R.string.gallery_screen,
    titleTextId = R.string.gallery_screen,
    route = GalleryRoute::class,
  ),
}
