package com.sample.demo.data

import android.content.Context
import android.provider.Settings
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

// 为Context类扩展dataStore属性
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_preferences")

@Singleton
class UserPreferencesRepository @Inject constructor(
    @ApplicationContext private val context: Context
) {
    // 定义键
    private object PreferencesKeys {
        val IS_LOGGED_IN = booleanPreferencesKey("is_logged_in")
        val USERNAME = stringPreferencesKey("username")
    }

    // 获取登录状态
    val isLoggedIn: Flow<Boolean> = context.dataStore.data.map(::mapIsLoggedIn)

    // 获取用户名
    val username: Flow<String> = context.dataStore.data.map(::mapUsername)

    // 映射函数，避免使用lambda
    private fun mapIsLoggedIn(preferences: Preferences): Boolean {
        return preferences[PreferencesKeys.IS_LOGGED_IN] ?: false
    }

    // 映射函数，避免使用lambda
    private fun mapUsername(preferences: Preferences): String {
        return preferences[PreferencesKeys.USERNAME] ?: ""
    }

    // 保存登录状态
    suspend fun saveLoginState(isLoggedIn: Boolean, username: String) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.IS_LOGGED_IN] = isLoggedIn
            preferences[PreferencesKeys.USERNAME] = username
        }
    }

    // 清除登录状态
    suspend fun clearLoginState() {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.IS_LOGGED_IN] = false
            preferences[PreferencesKeys.USERNAME] = ""
        }
    }

    // 获取设备唯一码
    fun getDeviceId(): String {
        return Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
    }
} 