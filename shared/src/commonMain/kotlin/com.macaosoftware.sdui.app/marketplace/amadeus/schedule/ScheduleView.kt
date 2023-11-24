package com.macaosoftware.sdui.app.marketplace.amadeus.schedule

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.Navigator
import com.macaosoftware.component.viewmodel.StateComponent
<<<<<<< HEAD
import com.macaosoftware.sdui.app.marketplace.amadeus.home.HomeViewModel
import com.macaosoftware.sdui.app.marketplace.amadeus.ui.screen.schedule.ScheduleScreen
=======
>>>>>>> origin

val ScheduleComponentView: @Composable StateComponent<ScheduleViewModel>.(
    modifier: Modifier,
    scheduleViewModel: ScheduleViewModel
) -> Unit = { modifier: Modifier, scheduleViewModel: ScheduleViewModel ->
    Navigator(ScheduleScreen())
}