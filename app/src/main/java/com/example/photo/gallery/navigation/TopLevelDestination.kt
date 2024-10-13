package com.example.photo.gallery.navigation

import androidx.annotation.DrawableRes
import com.example.photo.gallery.features.camera.CameraRoute
import com.example.photo.gallery.features.gallery.GalleryRoute
import com.example.photo.gallery.features.profile.ProfileRoute
import com.example.photo.gallery.ui.icon.AppIcons
import kotlin.reflect.KClass

enum class TopLevelDestination(
    @DrawableRes val icon: Int,
    val route: KClass<*>,
) {
    CAMERA(
        icon = AppIcons.camera,
        route = CameraRoute::class,
    ),
    GALLERY(
        icon = AppIcons.gallery,
        route = GalleryRoute::class,
    ),
    ME(
        icon = AppIcons.profile,
        route = ProfileRoute::class,
    ),
}
