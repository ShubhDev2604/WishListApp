package com.lifehive.app.ui.screen

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.lifehive.app.AppBarView
import com.lifehive.app.R
import com.lifehive.app.data.Wish
import com.lifehive.app.ui.theme.AppTypography
import com.lifehive.app.ui.theme.Typography
import com.lifehive.app.viewmodel.WishViewModel
import kotlinx.coroutines.delay

@Composable
fun WishDetailView(
    id: Long,
    viewModel: WishViewModel,
    navController: NavController
) {
    val wishItem = remember { mutableStateOf<Wish?>(null) }
    val context = LocalContext.current
    val loading = remember { mutableStateOf(true) }
    LaunchedEffect(key1 = id) {
        delay(1000)
        if (id != 0L) {
            viewModel.getAWishById(id).collect { wish ->
                wishItem.value = wish
            }
        } else {
            navController.navigateUp()
        }
    }
    if (wishItem.value != null) {
        loading.value = false
        WishDetailScreen(context = context, wish = wishItem.value!!, navController = navController)
    } else {
        if (loading.value) {
            LoadingScreenUI()
        } else {
            navController.navigate(
                Screen.HomeScreen.route + "?snackbarMessage=" + context.getString(
                    R.string.something_went_wrong
                )
            ) {
                popUpTo(Screen.HomeScreen.route) { inclusive = true }
            }
        }
    }
}

@Composable
fun WishDetailScreen(
    context: Context,
    wish: Wish,
    navController: NavController
) {
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        topBar = {
            AppBarView(
                title = stringResource(id = R.string.wish_details),
                onBackNavClicked = {
                    navController.navigate(Screen.HomeScreen.route) {
                        popUpTo(Screen.HomeScreen.route) { inclusive = true }
                    }
                }
            )
        },
        scaffoldState = scaffoldState,
        backgroundColor = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
        ) {
            Box(
                modifier = Modifier
                    .padding(all = 16.dp)
            ) {
                AsyncImage(
                    modifier = Modifier
                        .border(1.dp, MaterialTheme.colorScheme.outline, RoundedCornerShape(12.dp))
                        .clip(RoundedCornerShape(12.dp))
                        .height(300.dp)
                        .fillMaxWidth(),
                    model = wish.imageUri ?: Image(
                        painter = painterResource(id = R.drawable.no_image_available),
                        contentDescription = "No image attached"
                    ),
                    contentDescription = "Wish Image",
                    contentScale = ContentScale.Crop
                )
            }
            Divider(
                modifier = Modifier.padding(vertical = 8.dp)
            )
            Text(
                modifier = Modifier.padding(16.dp),
                text = wish.title,
                style = AppTypography.detailPageTitle,
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                modifier = Modifier.padding(16.dp),
                text = wish.description,
                style = AppTypography.detailPageDescription,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}