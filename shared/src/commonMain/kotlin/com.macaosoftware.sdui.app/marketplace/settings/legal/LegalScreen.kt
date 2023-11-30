package com.macaosoftware.sdui.app.marketplace.settings.legal

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.macaosoftware.sdui.app.marketplace.amadeus.util.Util.PRIVACY
import com.macaosoftware.sdui.app.marketplace.amadeus.util.Util.TERMS
import com.macaosoftware.sdui.app.marketplace.amadeus.util.Util.WELCOME
import com.macaosoftware.sdui.app.marketplace.settings.SettingScreen


class LegalScreen : Screen {
    @Composable
    override fun Content() {
        // Create a list of legal content
        val legalContent = listOf(
            TERMS,
            PRIVACY,
            WELCOME
        )

        // Create a ViewPager-like state
        var currentPage by remember { mutableStateOf(0) }
        val navigator = LocalNavigator.current

        Box(
            modifier = Modifier
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
                        .fillMaxWidth()
                ) {
                    items(legalContent.size) { index ->
                        LegalPage(
                            title = if (index == 0) "Terms & Conditions" else if (index == 1) "Privacy Policy" else "Welcome",
                            content = legalContent[index],
                            isCurrentPage = index == currentPage
                        )
                    }
                }

                // Dot indicator
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    for (index in legalContent.indices) {
                        DotIndicator(selected = index == currentPage)
                    }
                }

                // Navigation buttons
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    NavigationButton(
                        text = "Previous",
                        onClick = { if (currentPage > 0) currentPage-- }
                    )

                    NavigationButton(
                        text = "Skip",
                        onClick = { currentPage = legalContent.size - 1 }
                    )

                    NavigationButton(
                        text = if (currentPage < legalContent.size - 1) "Next" else "Finish",
                        onClick = {
                            if (currentPage < legalContent.size - 1) currentPage++ else navigator!!.push(SettingScreen())

                        }
                    )
                }
            }
        }
    }
}

@Composable
fun DotIndicator(selected: Boolean) {
    Box(
        modifier = Modifier
            .size(10.dp)
            .background(
                color = if (selected) Color.Gray else Color.LightGray,
                shape = CircleShape
            )
            .padding(4.dp)
    )
}

@Composable
fun NavigationButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .wrapContentWidth()
            .padding(end = 8.dp)
    ) {
        Text(text = text)
    }
}

@Composable
fun LegalPage(
    title: String,
    content: String,
    isCurrentPage: Boolean
) {
    if (isCurrentPage) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(600.dp)
                .padding(16.dp)
                .verticalScroll(state = rememberScrollState())
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Title
                Text(
                    text = title,
                    modifier = Modifier
                        .padding(8.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                // Legal content
                Text(
                    text = content,
                    modifier = Modifier
                        .fillMaxSize(), // Remove weight here
                    // .padding(16.dp),
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center
                )


            }
        }
    }
}







