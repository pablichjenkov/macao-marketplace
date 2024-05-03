package com.macaosoftware.sdui.app.marketplace.amadeus.schedule

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.macaosoftware.component.viewmodel.StateComponent

val ScheduleComponentView: @Composable StateComponent<ScheduleViewModel>.(
    modifier: Modifier,
    scheduleViewModel: ScheduleViewModel
) -> Unit = { modifier: Modifier, scheduleViewModel: ScheduleViewModel ->

    ScheduleScreen()
}