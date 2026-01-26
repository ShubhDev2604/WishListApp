package com.lifehive.app

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.lifehive.app.ui.theme.LifeHiveTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val context = LocalContext.current
            val isSystemDark = isSystemInDarkTheme()
            val sharedPref = context.getSharedPreferences("settings", Context.MODE_PRIVATE)
            var isDarkTheme by remember {
                mutableStateOf(
                    if (sharedPref.contains("dark_theme")) {
                        sharedPref.getBoolean("dark_theme", false)
                    } else {
                        isSystemDark
                    }
                )
            }
            LifeHiveTheme(darkTheme = isDarkTheme) {

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Navigation(
                        onToggleTheme = {
                            isDarkTheme = !isDarkTheme
                            context.getSharedPreferences("settings", MODE_PRIVATE)
                                .edit()
                                .putBoolean("dark_theme", isDarkTheme)
                                .apply()
                        },
                        isDarkTheme = isDarkTheme
                    )
                }
            }
        }
    }
}
