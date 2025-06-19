package eu.tutorials.mywishlistapp

import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import eu.tutorials.mywishlistapp.ui.theme.AppTypography

@Composable
fun AppBarView(
    title: String,
    onBackNavClicked: () -> Unit= {}
){
    val context = LocalContext.current
    val navigationIcon : (@Composable () -> Unit)? =
        if (!title.equals(context.getString(R.string.wishlist), ignoreCase = true)) {
            {
                IconButton(
                    onClick = { onBackNavClicked() },
                    modifier = Modifier
                        .padding(start = 4.dp)
                        .heightIn(max = 24.dp)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        tint = Color.White,
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
            color = colorResource(id = R.color.on_primary),
            modifier = Modifier
                .padding(start = 4.dp)
                .heightIn(max = 24.dp),
            style = AppTypography.title
        )
    },
        elevation = 3.dp,
        backgroundColor = colorResource(id = R.color.primary_variant),
        navigationIcon = navigationIcon
    )
}