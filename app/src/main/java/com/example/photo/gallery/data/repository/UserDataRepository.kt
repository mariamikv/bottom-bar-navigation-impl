package com.example.photo.gallery.data.repository

import com.example.photo.gallery.data.model.DarkThemeConfig
import com.example.photo.gallery.data.model.ThemeBrand
import com.example.photo.gallery.data.model.UserData
import kotlinx.coroutines.flow.Flow

interface UserDataRepository {

  val userData: Flow<UserData>

  suspend fun setFollowedTopicIds(followedTopicIds: Set<String>)

  suspend fun setTopicIdFollowed(followedTopicId: String, followed: Boolean)

  suspend fun setNewsResourceBookmarked(newsResourceId: String, bookmarked: Boolean)

  suspend fun setNewsResourceViewed(newsResourceId: String, viewed: Boolean)

  suspend fun setThemeBrand(themeBrand: ThemeBrand)

  suspend fun setDarkThemeConfig(darkThemeConfig: DarkThemeConfig)

  suspend fun setDynamicColorPreference(useDynamicColor: Boolean)

  suspend fun setShouldHideOnboarding(shouldHideOnboarding: Boolean)
}
