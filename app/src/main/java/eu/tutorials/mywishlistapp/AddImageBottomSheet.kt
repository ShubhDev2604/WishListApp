package eu.tutorials.mywishlistapp

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
import androidx.compose.material.icons.Icons
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import eu.tutorials.mywishlistapp.ui.theme.AppTypography

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
        containerColor = colorResource(id = R.color.primary_shade1),
        contentColor = colorResource(id = R.color.primary_shade5),
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
                                colorResource(id = R.color.primary_shade2),
                                colorResource(id = R.color.primary),
                                colorResource(id = R.color.primary_variant),
                                colorResource(id = R.color.primary_shade5)
                            )
                        ),
                        shape = RoundedCornerShape(
                            12.dp
                        )
                    )
                    .padding(2.dp)
                    .fillMaxWidth(),
                onClick = {
                    //pickImageFromGallery()
                }
            ) {
                Text(
                    text = "Pick from Gallery",
                    style = AppTypography.button,
                    modifier = Modifier
                        .padding(end = 4.dp)
                )
                Icon(imageVector = ImageVector.vectorResource(id = R.drawable.baseline_image_24), contentDescription = "Pick from Gallery")
            }

            Spacer(modifier = Modifier.height(10.dp))

            TextButton(
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
                        shape = RoundedCornerShape(
                            12.dp
                        )
                    )
                    .padding(2.dp)
                    .fillMaxWidth(),
                onClick = {
                    //takePhotoWithCamera()
                }
            ) {
                Text(
                    text = "Take a Photo",
                    style = AppTypography.button,
                    modifier = Modifier
                        .padding(end = 4.dp)
                )
                Icon(imageVector = ImageVector.vectorResource(id = R.drawable.baseline_camera_alt_24), contentDescription = "Take a Photo")
            }
        }

    }
}