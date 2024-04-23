package com.macaosoftware.sdui.app.marketplace.amadeus.schedule

import com.macaosoftware.component.viewmodel.ComponentViewModelFactory
import com.macaosoftware.component.viewmodel.StateComponent
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class ScheduleViewModelFactory(

) : ComponentViewModelFactory<ScheduleViewModel> {
    override fun create(component: StateComponent<ScheduleViewModel>): ScheduleViewModel {
        return ScheduleViewModel(component)
    }
}
