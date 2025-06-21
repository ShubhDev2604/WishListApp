package com.example.mywishlistapp

import android.content.Context
import android.content.Intent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.IconButton
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.platform.LocalContext
import eu.tutorials.mywishlistapp.AppBarView
import eu.tutorials.mywishlistapp.R
import eu.tutorials.mywishlistapp.Screen
import eu.tutorials.mywishlistapp.WishViewModel
import eu.tutorials.mywishlistapp.data.Wish
import eu.tutorials.mywishlistapp.ui.theme.AppTypography

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeView(
    viewModel: WishViewModel,
    navController: NavController
) {
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            AppBarView(title = "WishList")
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Screen.AddScreen.route + "/0L")
                },
                modifier = Modifier.padding(all = 20.dp),
                contentColor = colorResource(id = R.color.on_primary),
                backgroundColor = colorResource(id = R.color.primary)
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        }
    ) {
        val wishList = viewModel.getAllWishes.collectAsState(initial = listOf())
        Box(modifier = Modifier.fillMaxSize()) {
            if (wishList.value.isEmpty()) {
                androidx.compose.foundation.Image(
                    painter = androidx.compose.ui.res.painterResource(id = R.drawable.no_wishes_background),
                    contentDescription = "No wishes background",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .fillMaxWidth(0.7f)
                )
            }
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                items(wishList.value, key = { wish -> wish.id }) { wish ->
                    val dismissState = rememberDismissState(
                        confirmStateChange = {
                            if (it == DismissValue.DismissedToEnd || it == DismissValue.DismissedToStart) {
                                viewModel.deleteWish(wish)
                            }
                            true
                        }
                    )

                    SwipeToDismiss(
                        modifier = Modifier.align(Alignment.CenterEnd),
                        state = dismissState,
                        background = {
                            if (dismissState.targetValue != DismissValue.Default || dismissState.progress.fraction > 0f) {
                                val progress = dismissState.progress.fraction.coerceIn(0f, 1f)
                                val alpha by animateFloatAsState(
                                    targetValue = 0.3f + 0.7f * progress,
                                    label = "iconAlpha"
                                )
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize(),
                                    contentAlignment = Alignment.CenterEnd
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Delete,
                                        contentDescription = "Delete Icon",
                                        tint = colorResource(id = R.color.primary).copy(alpha = alpha),
                                        modifier = Modifier
                                            .padding(end = 24.dp)
                                            .background(
                                                color = colorResource(R.color.divider),
                                                shape = CircleShape
                                            )
                                            .padding(12.dp)
                                    )
                                }
                            }
                        },
                        directions = setOf(DismissDirection.EndToStart),
                        dismissThresholds = { FractionalThreshold(0.1f) },
                        dismissContent = {
                            WishItem(wish = wish) {
                                val id = wish.id
                                navController.navigate(Screen.AddScreen.route + "/$id")
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun WishItem(wish: Wish, onClick: () -> Unit) {
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, top = 8.dp, end = 8.dp)
            .clickable {
                onClick()
            },
        elevation = 10.dp,
        backgroundColor = colorResource(id = R.color.background)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp)
            ) {
                Text(
                    modifier = Modifier
                        .padding(bottom = 4.dp),
                    text = wish.title,
                    style = AppTypography.semiTitle,
                    color = colorResource(id = R.color.on_secondary)
                )
                Text(
                    text = wish.description,
                    style = AppTypography.description,
                    color = colorResource(id = R.color.on_background)
                )
            }
            IconButton(
                onClick = {
                    shareButtonClicked(wish, context = context)
                },
            ) {
                Icon(
                    imageVector = Icons.Default.Share,
                    contentDescription = "Share Wish",
                    tint = colorResource(id = R.color.primary)
                )
            }
        }
    }
}

fun shareButtonClicked(wish: Wish, context: Context) {
    val sendIntent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_SUBJECT, wish.title)
        putExtra(Intent.EXTRA_TEXT, wish.description)
        type = "text/plain"
    }

    val shareIntent = Intent.createChooser(sendIntent, "Share via")
    context.startActivity(shareIntent)
}