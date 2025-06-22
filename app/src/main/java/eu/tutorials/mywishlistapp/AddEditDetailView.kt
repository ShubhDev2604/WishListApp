package eu.tutorials.mywishlistapp

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Environment
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.AlertDialog
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.FileProvider
import androidx.navigation.NavController
import coil.compose.AsyncImage
import eu.tutorials.mywishlistapp.data.Wish
import eu.tutorials.mywishlistapp.ui.theme.AppTypography
import kotlinx.coroutines.launch
import java.io.File

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditDetailView(
    id: Long,
    viewModel: WishViewModel,
    navController: NavController
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()
    val keyboardController = LocalSoftwareKeyboardController.current
    val (showSheet, setShowSheet) = remember { mutableStateOf(false) }
    var showRemoveDialog by remember { mutableStateOf(false) }
    var cameraUri by remember { mutableStateOf<Uri?>(null) }

    // Use rememberSaveable for local state
    var title by rememberSaveable { mutableStateOf("") }
    var description by rememberSaveable { mutableStateOf("") }
    var image by rememberSaveable { mutableStateOf("") }

    // Only set initial state when id changes, and only if user hasn't started editing
    LaunchedEffect(key1 = id) {
        if (id != 0L) {
            viewModel.getAWishById(id).collect { wish ->
                if (title.isEmpty() && description.isEmpty()) {
                    title = wish.title
                    description = wish.description
                    image = wish.imageUri ?: ""
                }
            }
        } else {
            if (title.isEmpty() && description.isEmpty()) {
                title = ""
                description = ""
                image = ""
            }
        }
    }

    Scaffold(
        topBar = {
            AppBarView(
                title = if (id != 0L) stringResource(id = R.string.update_wish) else stringResource(
                    id = R.string.add_wish
                ),
                onBackNavClicked = {
                    navController.navigate(Screen.HomeScreen.route) {
                        popUpTo(Screen.HomeScreen.route) { inclusive = true }
                    }
                }
            )
        },
        scaffoldState = scaffoldState
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .wrapContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            WishTextField(
                label = "Title",
                value = title,
                onValueChanged = { title = it }
            )
            Spacer(modifier = Modifier.height(10.dp))
            WishTextField(
                label = "Description",
                value = description,
                onValueChanged = { description = it }
            )
            Spacer(modifier = Modifier.height(10.dp))
            Box(
                modifier = Modifier
                    .border(
                        width = 2.dp,
                        brush = Brush.linearGradient(
                            listOf(
                                colorResource(id = R.color.primary_shade2),
                                colorResource(id = R.color.primary),
                                colorResource(id = R.color.primary_variant),
                                colorResource(id = R.color.primary_shade5)
                            )
                        ),
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(2.dp)
                    .clickable { setShowSheet(true) }
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    Icon(
                        modifier = Modifier
                            .padding(end = 6.dp)
                            .size(36.dp),
                        imageVector = ImageVector.vectorResource(id = R.drawable.baseline_camera_24),
                        contentDescription = "Take Photo"
                    )
                    Text(
                        style = AppTypography.button,
                        text = "Upload Image"
                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = {
                    keyboardController?.hide()
                    if (title.isNotEmpty() && description.isNotEmpty()) {
                        if (id != 0L) {
                            viewModel.updateWish(
                                Wish(
                                    id = id,
                                    title = title.trim(),
                                    description = description.trim(),
                                    imageUri = image
                                )
                            )
                            navController.navigate(
                                Screen.HomeScreen.route + "?snackbarMessage=" + context.getString(
                                    R.string.wish_update
                                )
                            ) {
                                popUpTo(Screen.HomeScreen.route) { inclusive = true }
                            }
                        } else {
                            viewModel.addWish(
                                Wish(
                                    title = title.trim(),
                                    description = description.trim(),
                                    imageUri = image
                                )
                            )
                            navController.navigate(
                                Screen.HomeScreen.route + "?snackbarMessage=" + context.getString(
                                    R.string.wish_created
                                )
                            ) {
                                popUpTo(Screen.HomeScreen.route) { inclusive = true }
                            }
                        }
                    } else {
                        val errorMessage = if (title.isEmpty()) {
                            context.getString(R.string.empty_title_message)
                        } else {
                            context.getString(R.string.empty_description_message)
                        }
                        scope.launch {
                            scaffoldState.snackbarHostState.showSnackbar(errorMessage)
                        }
                    }
                }
            ) {
                Text(
                    text = if (id != 0L) stringResource(id = R.string.update_wish) else stringResource(
                        id = R.string.add_wish
                    ),
                    style = AppTypography.button
                )
            }
            if (image.isNotEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(12.dp))
                        .border(1.dp, Color.Gray, RoundedCornerShape(12.dp)),
                ) {
                    AsyncImage(
                        modifier = Modifier
                            .matchParentSize(),
                        contentScale = ContentScale.Crop,
                        model = image,
                        contentDescription = "Selected Image"
                    )

                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.baseline_close_24),
                        contentDescription = "Remove Image",
                        tint = colorResource(id = R.color.on_primary),
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(8.dp)
                            .background(
                                color = colorResource(id = R.color.primary),
                                shape = RoundedCornerShape(50)
                            )
                            .clickable {
                                showRemoveDialog = true
                            }
                            .size(24.dp)
                    )
                }

            }
            val galleryLauncher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.GetContent()
            ) { uri: Uri? ->
                uri?.let {
                    image = uri.toString()
                    try {
                        context.contentResolver.takePersistableUriPermission(
                            it,
                            Intent.FLAG_GRANT_READ_URI_PERMISSION
                        )
                    } catch (e: SecurityException) {
                        // Handle error if needed
                    }
                }
            }
            val cameraLauncher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.TakePicture()
            ) { success ->
                if (success) {
                    val uri = cameraUri
                    if (uri != null) {
                        image = uri.toString()
                    }
                }
            }

            fun takePhotoWithCamera(context: Context) {
                val photoFile = createImageFile(context)
                val uri: Uri = FileProvider.getUriForFile(
                    context,
                    "${context.packageName}.provider",
                    photoFile
                )
                cameraUri = uri
                cameraLauncher.launch(uri)
            }

            fun pickImageFromGallery() {
                galleryLauncher.launch("image/*")
            }
            if (showSheet) {
                AddImageBottomSheet(
                    onPickFromGallery = {
                        pickImageFromGallery()
                        setShowSheet(false)
                    },
                    onTakePhoto = {
                        takePhotoWithCamera(context)
                        setShowSheet(false)
                    },
                    onDismiss = { setShowSheet(false) },
                    sheetState = rememberModalBottomSheetState()
                )
            }

            if (showRemoveDialog) {
                AlertDialog(
                    title = {
                        Text(
                            text = "Remove Image",
                            style = AppTypography.semiTitle,
                            fontSize = 16.sp
                        )
                    },
                    text = {
                        Text(
                            text = "Are you sure you want to remove this image?",
                            style = AppTypography.description,
                            fontSize = 14.sp
                        )
                    },
                    backgroundColor = colorResource(id = R.color.primary_shade1),
                    onDismissRequest = {
                        showRemoveDialog = false
                    },
                    confirmButton = {
                        TextButton(
                            onClick = {
                                image = ""
                                showRemoveDialog = false
                            }
                        ) {
                            Text(
                                text = "Yes",
                                style = AppTypography.button
                            )
                        }
                    },
                    dismissButton = {
                        TextButton(
                            onClick = {
                                showRemoveDialog = false
                            }
                        ) {
                            Text(
                                text = "Cancel",
                                style = AppTypography.button
                            )
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun WishTextField(
    label: String,
    value: String,
    onValueChanged: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChanged,
        label = {
            Text(
                text = label,
                color = colorResource(id = R.color.primary)
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = colorResource(id = R.color.primary),
            focusedLabelColor = colorResource(id = R.color.primary_variant),
            focusedBorderColor = colorResource(id = R.color.primary_variant),
            unfocusedLabelColor = colorResource(id = R.color.primary),
            unfocusedBorderColor = colorResource(id = R.color.primary),
            cursorColor = colorResource(id = R.color.primary)
        )
    )
}

fun createImageFile(context: Context): File {
    val timestamp = System.currentTimeMillis()
    val storageDir = File(
        context.getExternalFilesDir(Environment.DIRECTORY_PICTURES),
        "MyWishlistApp"
    )
    if (!storageDir.exists()) {
        storageDir.mkdirs()
    }
    return File(storageDir, "IMG_${timestamp}.jpg")
}


@Preview
@Composable
fun WishTestFieldPrev() {
    Box(
        modifier = Modifier
            .background(color = Color.White)
    ) {
        WishTextField(label = "text", value = "text", onValueChanged = {})
    }
}