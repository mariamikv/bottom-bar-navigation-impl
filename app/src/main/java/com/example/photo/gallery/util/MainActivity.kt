package com.example.photo.gallery.util

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import com.example.photo.gallery.ui.App
import com.example.photo.gallery.ui.rememberAppState
import com.example.photo.gallery.ui.theme.PhotoGalleryTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val darkMode = false
            DisposableEffect(darkMode) {
                enableEdgeToEdge(
                    statusBarStyle = SystemBarStyle.auto(
                        android.graphics.Color.TRANSPARENT,
                        android.graphics.Color.TRANSPARENT,
                    ),
                )
                onDispose {}
            }
            val appState = rememberAppState()

            CompositionLocalProvider {
                PhotoGalleryTheme(
                    darkTheme = isSystemInDarkTheme(),
                ) {
                    App(appState = appState)
                }
            }
        }
    }
}
