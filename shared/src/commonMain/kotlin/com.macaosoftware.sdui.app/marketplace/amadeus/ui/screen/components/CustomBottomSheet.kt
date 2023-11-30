package com.macaosoftware.sdui.app.marketplace.amadeus.ui.screen.components

import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Shapes
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomBottomSheet(
    onRequestDismiss: () -> Unit,
    sheetState: SheetState,
    scope: CoroutineScope,
    modifier: Modifier,
    containerColor: Color,
    contentColor: Color,
) {
    var showBottomSheet by remember { mutableStateOf(true) }

    ModalBottomSheet(
        onDismissRequest = {
            showBottomSheet = !showBottomSheet
        },
        sheetState = sheetState,
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        containerColor = containerColor,
        contentColor = contentColor,
    ) {
        // Sheet content
        Button(onClick = {
            scope.launch { sheetState.hide() }.invokeOnCompletion {
                if (!sheetState.isVisible) {
                    showBottomSheet = false
                }
            }
        }) {
            Text("Hide bottom sheet")
        }
    }
}
