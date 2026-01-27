package com.lifehive.app

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

class TokenManager(context: Context) {
    private val masterKey = MasterKey.Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()
    private val sharedPrefs = EncryptedSharedPreferences.create(
        context,
        "auth_prefs",
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    companion object {
        private const val KEY_ACCESS_TOKEN = "access_token"
        private const val KEY_TOKEN_TYPE = "token_type"
    }

    fun getAccessToken(): String? {
        return sharedPrefs.getString(KEY_ACCESS_TOKEN, null)
    }

    fun getTokenType(): String {
        return sharedPrefs.getString(KEY_TOKEN_TYPE,"Bearer") ?: "Bearer"
    }

    fun hasToken(): Boolean {
        return getAccessToken() != null
    }

    fun clearToken() {
        sharedPrefs.edit().clear().apply()
    }

    fun saveToken(accessToken: String, tokenType: String) {
    sharedPrefs
        .edit()
        .putString(KEY_TOKEN_TYPE, tokenType.ifEmpty { "Bearer" })
        .putString(KEY_ACCESS_TOKEN, accessToken)
        .apply()
    }
}