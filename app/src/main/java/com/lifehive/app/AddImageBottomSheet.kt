package com.lifehive.app

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.lifehive.app.ui.theme.AppTypography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddImageBottomSheet(
    onPickFromGallery: () -> Unit,
    onTakePhoto: () -> Unit,
    onDismiss: () -> Unit,
    sheetState: SheetState
) {
    ModalBottomSheet(
        modifier = Modifier,
        sheetState = sheetState,
        shape = RoundedCornerShape(
            12.dp
        ),
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
        windowInsets = WindowInsets.navigationBars,
        onDismissRequest = onDismiss
    ) {
        Column(
            modifier = Modifier
                .padding(
                    horizontal = 16.dp,
                    vertical = 8.dp
                )
                .navigationBarsPadding()
                .imePadding()
        ) {
            TextButton(
                modifier = Modifier
                    .border(
                        width = 2.dp,
                        brush = Brush.linearGradient(
                            listOf(
                                MaterialTheme.colorScheme.primaryContainer,
                                MaterialTheme.colorScheme.primary,
                                MaterialTheme.colorScheme.inversePrimary,
                                MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        ),
                        shape = RoundedCornerShape(
                            12.dp
                        )
                    )
                    .padding(2.dp)
                    .fillMaxWidth(),
                onClick = {
                    onPickFromGallery()
                }
            ) {
                Text(
                    text = "Pick from Gallery",
                    style = AppTypography.button,
                    modifier = Modifier
                        .padding(end = 4.dp),
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.baseline_image_24),
                    contentDescription = "Pick from Gallery",
                    tint = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            TextButton(
                modifier = Modifier
                    .border(
                        width = 2.dp,
                        brush = Brush.linearGradient(
                            listOf(
                                MaterialTheme.colorScheme.primaryContainer,
                                MaterialTheme.colorScheme.primary,
                                MaterialTheme.colorScheme.inversePrimary,
                                MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        ),
                        shape = RoundedCornerShape(
                            12.dp
                        )
                    )
                    .padding(2.dp)
                    .fillMaxWidth(),
                onClick = {
                    onTakePhoto()
                }
            ) {
                Text(
                    text = "Take a Photo",
                    style = AppTypography.button,
                    modifier = Modifier
                        .padding(end = 4.dp),
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.baseline_camera_alt_24),
                    contentDescription = "Take a Photo",
                    tint = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
        }
    }
}