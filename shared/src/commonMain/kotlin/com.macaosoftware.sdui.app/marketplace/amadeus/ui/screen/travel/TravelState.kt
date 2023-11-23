package com.macaosoftware.sdui.app.marketplace.amadeus.ui.screen.travel

import com.macaosoftware.sdui.app.marketplace.amadeus.data.model.travel.Travel

sealed class TravelState {
    object Loading : TravelState()
    data class Success(val travel: Travel) : TravelState()
    data class Error(val error: String) : TravelState()
}
