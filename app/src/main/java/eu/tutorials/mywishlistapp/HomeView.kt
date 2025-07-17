package eu.tutorials.mywishlistapp

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.requiredWidthIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import eu.tutorials.mywishlistapp.data.Wish
import eu.tutorials.mywishlistapp.ui.theme.AppTypography
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeView(
    viewModel: WishViewModel,
    navController: NavController,
    snackBarMessage: String? = null,
    isDarkTheme: Boolean,
    onToggleTheme: () -> Unit
) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    var showBlocker by remember { mutableStateOf(false) }
    val context = LocalContext.current

    LaunchedEffect(snackBarMessage) {
        snackBarMessage?.let { message ->
            if (message.isNotEmpty()) {
                showBlocker = true
                val snackBarJob = launch {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message,
                        duration = SnackbarDuration.Indefinite
                    )
                }
                delay(1000)
                scaffoldState.snackbarHostState.currentSnackbarData?.dismiss()
                snackBarJob.cancel()
                navController.navigate(Screen.HomeScreen.route) {
                    popUpTo(Screen.HomeScreen.route) { inclusive = true }
                }
                showBlocker = false
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Scaffold(
            scaffoldState = scaffoldState,
            snackbarHost = { SnackbarHost(scaffoldState.snackbarHostState) },
            topBar = {
                AppBarView(
                    title = "WishList",
                    actions = {
                        IconButton(onClick = {
                            shareButtonClicked("https://www.linkedin.com/in/shubh-tandon-8133b8201/", context)
                        }) {
                            Image(
                                painter = painterResource(id = R.drawable.linkedin),
                                contentDescription = "Linked-In page",
                                modifier = Modifier.size(24.dp),
                                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimaryContainer)
                            )
                        }
                        IconButton(onClick = {
                            shareButtonClicked("https://github.com/ShubhDev2604/WishListApp", context)
                        }) {
                            Image(
                                painter = painterResource(id = R.drawable.github_mark_white),
                                contentDescription = "Github Repo Link",
                                modifier = Modifier.size(24.dp),
                                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimaryContainer)
                            )
                        }
                        IconButton(onClick = {
                            onToggleTheme()
                        }) {
                            Image(
                                painter = painterResource(id = if(isDarkTheme) R.drawable.moon else R.drawable.sunscreen),
                                contentDescription = "Toggle Theme",
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }
                )
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        navController.navigate(Screen.AddScreen.route + "/0L")
                    },
                    modifier = Modifier.padding(all = 20.dp),
                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    backgroundColor = MaterialTheme.colorScheme.primaryContainer
                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = null)
                }
            },
            backgroundColor = MaterialTheme.colorScheme.background
        ) {
            val wishList = viewModel.getAllWishes.collectAsState(initial = listOf())
            Box(modifier = Modifier.fillMaxSize()) {
                if (wishList.value.isEmpty()) {
                    Image(
                        painter = painterResource(id = if(isDarkTheme) R.drawable.no_wishes_background_dark_theme else R.drawable.no_wishes_background),
                        contentDescription = "No wishes background",
                        modifier = Modifier
                            .align(Alignment.Center)
                            .fillMaxWidth(0.7f)
                            .background(Color.Transparent)
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
                                    scope.launch {
                                        scaffoldState.snackbarHostState.showSnackbar("Wish deleted: ${wish.title}")
                                    }
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
                                            tint = MaterialTheme.colorScheme.inverseOnSurface,
                                            modifier = Modifier
                                                .padding(end = 24.dp)
                                                .background(
                                                    color = MaterialTheme.colorScheme.inverseSurface.copy(
                                                        alpha = alpha
                                                    ),
                                                    shape = CircleShape
                                                )
                                                .padding(12.dp)
                                                .size(36.dp * alpha)
                                        )
                                    }
                                }
                            },
                            directions = setOf(DismissDirection.EndToStart),
                            dismissThresholds = { FractionalThreshold(0.8f) },
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
        if (showBlocker) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        color = Color.Transparent
                    )
                    .clickable(enabled = false, onClick = {})
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
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
        backgroundColor = MaterialTheme.colorScheme.surface
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
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = wish.description,
                    style = AppTypography.description,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (!wish.imageUri.isNullOrEmpty()) {
                    Box(
                        modifier = Modifier
                            .height(150.dp)
                            .padding(vertical = 6.dp)
                            .requiredWidthIn(150.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .border(
                                1.dp,
                                MaterialTheme.colorScheme.outlineVariant,
                                RoundedCornerShape(12.dp)
                            )
                            .combinedClickable(
                                onClick = {},
                                onLongClick = {
                                    val intent = Intent(Intent.ACTION_VIEW).apply {
                                        setDataAndType(
                                            Uri.parse(wish.imageUri), "image/*"
                                        )
                                        flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
                                    }
                                    context.startActivity(intent)
                                }
                            ),
                    ) {
                        AsyncImage(
                            modifier = Modifier
                                .matchParentSize(),
                            contentScale = ContentScale.Fit,
                            model = wish.imageUri,
                            contentDescription = "Selected Image"
                        )
                    }
                }
                IconButton(
                    onClick = {
                        shareButtonClicked(wish, context = context)
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Share,
                        contentDescription = "Share Wish",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}

fun shareButtonClicked(wish: Wish, context: Context) {
    val sendIntent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, "*${wish.title}*\n\n${wish.description}")
        putExtra(Intent.EXTRA_STREAM, Uri.parse(wish.imageUri))
        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        type = "image/*"
    }

    val shareIntent = Intent.createChooser(sendIntent, "Share Wish")
    context.startActivity(shareIntent)
}

fun shareButtonClicked(link: String, context: Context) {
    val uri = Uri.parse(link)
    val browserIntent = Intent(Intent.ACTION_VIEW, uri)
    context.startActivity(browserIntent)
}