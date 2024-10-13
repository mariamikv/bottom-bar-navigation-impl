# Simple Bottom Bar Implementation with Jetpack Compose
This project demonstrates a simple bottom navigation bar in Jetpack Compose using the `androidx.navigation:navigation-compose` library. Implementation are inspired by the [Now in Android](https://github.com/android/nowinandroid) project.

### Features
- **Bottom Navigation Bar**: Navigate between different screens using a bottom bar.
- **Jetpack Compose**: Built entirely with Jetpack Compose for a modern, declarative UI.
- **Navigation Components**: Utilizes `androidx.navigation:navigation-compose` for seamless navigation between screens.

### Adding Your Own Screens
To add a new screen:

- Add your feature screen to the feature folder.
- Then, add the feature screen like this:
```kotlin
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
```
- Don't forget to implement the screen component.
- After that, you can add your `cameraScreen()` in your `NavHost`.
  
```
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
        profileScreen()
        galleryScreen()
        cameraScreen()
    }
}
```

### Example Video
<video src="https://github.com/user-attachments/assets/1148c78b-9b67-4052-83d1-f6491720d87a" size ="200" />
