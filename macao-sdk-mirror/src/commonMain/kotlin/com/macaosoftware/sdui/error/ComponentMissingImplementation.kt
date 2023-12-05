package com.macaosoftware.sdui.error

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.macaosoftware.component.core.Component

class ComponentMissingImplementation(
    private val componentType: String
) : Component() {

    @Composable
    override fun Content(modifier: Modifier) {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = "No implementation for componentType: $componentType"
            )
        }
    }
}
