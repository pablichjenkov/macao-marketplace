package com.macaosoftware.sdui.app.marketplace.settings.legal

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.macaosoftware.component.viewmodel.StateComponent

val LegalComponentView: @Composable StateComponent<LegalViewModel>.(
    modifier: Modifier,
    legalViewModel: LegalViewModel
) -> Unit = { modifier: Modifier, legalViewModel: LegalViewModel ->

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        LegalScreen(
            modifier = modifier,
            legalViewModel = legalViewModel,
            onSkip = { /* Handle skip action */ },
            onNext = { /* Handle next action */ }
        )
    }

}
