package com.lifehive.app

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.lifehive.app.ui.theme.AppTypography

@Composable
fun AppBarView(
    title: String,
    onBackNavClicked: () -> Unit= {},
    actions: @Composable (RowScope.() -> Unit) = {},
){
    val context = LocalContext.current
    val navigationIcon : (@Composable () -> Unit)? =
        if (!title.equals(context.getString(R.string.app_name), ignoreCase = true)) {
            {
                IconButton(
                    onClick = { onBackNavClicked() },
                    modifier = Modifier
                        .padding(start = 4.dp)
                        .heightIn(max = 24.dp)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        tint = MaterialTheme.colorScheme.onSurface,
                        contentDescription = null
                    )
                }
            }
        } else {
            null
        }


    TopAppBar(
        title = {
        Text(text = title,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            modifier = Modifier
                .padding(start = 4.dp)
                .heightIn(max = 24.dp),
            style = AppTypography.title
        )
    },
        elevation = 3.dp,
        backgroundColor = MaterialTheme.colorScheme.primaryContainer,
        navigationIcon = navigationIcon,
        actions = actions
    )
}