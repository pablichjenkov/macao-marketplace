package com.macaosoftware.sdui.app.marketplace.settings.legal

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun LegalScreen(
    modifier: Modifier = Modifier,
    legalViewModel: LegalViewModel,
    onSkip: () -> Unit,
    onNext: () -> Unit
) {
    // Create a list of legal content
    val legalContent = listOf(
        "Terms and Conditions\n\nBy using this application, you agree to abide by the terms and conditions outlined in this agreement. Failure to comply may result in the termination of your account.",
        "Privacy Policy\n\nYour privacy is important to us. This policy outlines how we collect, use, and share your personal information. By using the app, you consent to our privacy practices.",
        "Thank you for using our app!"
    )

    // Create a ViewPager-like state
    var currentPage by remember { mutableStateOf(0) }

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            // Legal content
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
            ) {
                items(legalContent.size) { index ->
                    LegalPage(
                        title = "Page ${index + 1}",
                        content = legalContent[index],
                        isCurrentPage = index == currentPage
                    )
                }
            }

            // Dot indicator
            TabRow(
                selectedTabIndex = currentPage,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                containerColor = Color.Transparent
            ) {
                legalContent.forEachIndexed { index, _ ->
                    Tab(
                        selected = index == currentPage,
                        onClick = { currentPage = index }
                    )
                }
            }

            // Navigation buttons
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = {
                        if (currentPage > 0) {
                            currentPage--
                        }
                    },
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp)
                ) {
                    Text(text = "Previous")
                }

                Button(
                    onClick = { onSkip() },
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp)
                ) {
                    Text(text = "Skip")
                }

                Button(
                    onClick = {
                        if (currentPage < legalContent.size - 1) {
                            currentPage++
                        } else {
                            onNext()
                        }
                    },
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp)
                ) {
                    Text(text = if (currentPage < legalContent.size - 1) "Next" else "Finish")
                }
            }
        }
    }
}

@Composable
fun LegalPage(
    title: String,
    content: String,
    isCurrentPage: Boolean
) {
    if (isCurrentPage) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Legal content
            Text(
                text = content,
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp),
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            )

            // Title
            Text(
                text = title,
                modifier = Modifier
                    .padding(8.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }
    }
}






