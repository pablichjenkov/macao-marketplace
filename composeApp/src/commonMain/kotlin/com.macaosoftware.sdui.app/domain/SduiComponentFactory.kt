package com.macaosoftware.sdui.app.domain

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AirplanemodeActive
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Login
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.sharp.DateRange
import androidx.compose.material.icons.sharp.List
import com.macaosoftware.component.bottomnavigation.BottomNavigationComponent
import com.macaosoftware.component.bottomnavigation.BottomNavigationComponentDefaults
import com.macaosoftware.component.core.Component
import com.macaosoftware.component.core.NavItem
import com.macaosoftware.component.drawer.DrawerComponent
import com.macaosoftware.component.drawer.DrawerComponentDefaults
import com.macaosoftware.component.panel.PanelComponent
import com.macaosoftware.component.panel.PanelComponentDefaults
import com.macaosoftware.component.stack.StackComponent
import com.macaosoftware.component.stack.StackComponentDefaults
import com.macaosoftware.component.topbar.TopBarComponent
import com.macaosoftware.component.topbar.TopBarComponentDefaults
import com.macaosoftware.component.viewmodel.StateComponent
import com.macaosoftware.plugin.account.AccountPlugin
import com.macaosoftware.sdui.app.domain.error.ComponentMissingImplementation
import com.macaosoftware.sdui.app.marketplace.amadeus.airport.AirportDemoComponentView
import com.macaosoftware.sdui.app.marketplace.amadeus.airport.AirportDemoViewModel
import com.macaosoftware.sdui.app.marketplace.amadeus.airport.AirportDemoViewModelFactory
import com.macaosoftware.sdui.app.marketplace.amadeus.home.AmadeusHomeCoordinatorViewModel
import com.macaosoftware.sdui.app.marketplace.amadeus.home.AmadeusHomeCoordinatorViewModelFactory
import com.macaosoftware.sdui.app.marketplace.amadeus.hotel.HotelDemoComponentView
import com.macaosoftware.sdui.app.marketplace.amadeus.hotel.HotelDemoViewModel
import com.macaosoftware.sdui.app.marketplace.amadeus.hotel.HotelDemoViewModelFactory
import com.macaosoftware.sdui.app.marketplace.amadeus.profile.ProfileComponentView
import com.macaosoftware.sdui.app.marketplace.amadeus.profile.ProfileViewModel
import com.macaosoftware.sdui.app.marketplace.amadeus.profile.ProfileViewModelFactory
import com.macaosoftware.sdui.app.marketplace.amadeus.schedule.ScheduleComponentView
import com.macaosoftware.sdui.app.marketplace.amadeus.schedule.ScheduleViewModel
import com.macaosoftware.sdui.app.marketplace.amadeus.schedule.ScheduleViewModelFactory
import com.macaosoftware.sdui.app.marketplace.amadeus.search.SearchComponentView
import com.macaosoftware.sdui.app.marketplace.amadeus.search.SearchViewModel
import com.macaosoftware.sdui.app.marketplace.amadeus.search.SearchViewModelFactory
import com.macaosoftware.sdui.app.marketplace.auth.AuthViewModel
import com.macaosoftware.sdui.app.marketplace.auth.AuthViewModelFactory
import com.macaosoftware.sdui.app.marketplace.navigator.bottomnavigation.BottomNavigationSduiHandler
import com.macaosoftware.sdui.app.marketplace.navigator.bottomnavigation.BottomNavigationViewModelFactory
import com.macaosoftware.sdui.app.marketplace.navigator.drawer.DrawerSduiHandler
import com.macaosoftware.sdui.app.marketplace.navigator.drawer.DrawerViewModelFactory
import com.macaosoftware.sdui.app.marketplace.navigator.panel.PanelSduiHandler
import com.macaosoftware.sdui.app.marketplace.navigator.panel.panelViewModel.PanelSettingViewModel
import com.macaosoftware.sdui.app.marketplace.navigator.panel.panelfactory.PanelSettingViewModelFactory
import com.macaosoftware.sdui.app.marketplace.navigator.panel.panelfactory.PanelViewModelFactory
import com.macaosoftware.sdui.app.marketplace.navigator.topbar.CustomTopAppBar
import com.macaosoftware.sdui.app.marketplace.navigator.topbar.CustomTopAppBarFactory
import com.macaosoftware.sdui.app.marketplace.navigator.topbar.CustomTopAppBarViewModel
import com.macaosoftware.sdui.app.marketplace.settings.PanelSettingComponentView
import com.macaosoftware.sdui.app.marketplace.settings.SettingsComponentView
import com.macaosoftware.sdui.app.marketplace.settings.SettingsViewModel
import com.macaosoftware.sdui.app.marketplace.settings.SettingsViewModelFactory
import com.macaosoftware.sdui.app.marketplace.settings.home.HomeComponentView
import com.macaosoftware.sdui.app.marketplace.settings.home.HomeViewModel
import com.macaosoftware.sdui.app.marketplace.settings.home.HomeViewModelFactory
import com.macaosoftware.sdui.data.SduiConstants
import com.pablichj.incubator.amadeus.Database
import com.pablichj.incubator.amadeus.common.ITimeProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonPrimitive
import org.koin.core.Koin
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class SduiComponentFactory(
    private val koin: Koin
) : MacaoComponentFactory, KoinComponent {

    override fun getKoin(): Koin {
        return koin
    }

    override fun getNavItemOf(
        componentJson: JsonObject
    ): NavItem {
        val childComponentType: String =
            componentJson
                .getValue(SduiConstants.JsonKeyName.componentType)
                .jsonPrimitive
                .content

        return when (childComponentType) {

            SduiConstants.ComponentType.BottomNavigation -> {
                NavItem(
                    label = SduiConstants.ComponentType.BottomNavigation,
                    component = getComponentInstanceOf(componentJson),
                    icon = Icons.Default.AccountBox
                )
            }

            SduiConstants.ComponentType.Panel -> {
                NavItem(
                    label = SduiConstants.ComponentType.Panel,
                    component = getComponentInstanceOf(componentJson),
                    icon = Icons.Default.Home
                )
            }

            SduiConstants.ComponentType.AirportDemoComponent -> {
                NavItem(
                    label = "Air",
                    component = getComponentInstanceOf(componentJson),
                    icon = Icons.Sharp.DateRange
                )
            }

            SduiConstants.ComponentType.HotelDemoComponent -> {
                NavItem(
                    label = "Hotel",
                    component = getComponentInstanceOf(componentJson),
                    icon = Icons.Sharp.List
                )
            }

            SduiConstants.ComponentType.Setting -> {
                NavItem(
                    label = "Setting",
                    component = getComponentInstanceOf(componentJson),
                    icon = Icons.Default.Settings
                )
            }

            SduiConstants.ComponentType.SimpleTopAppBar -> {
                NavItem(
                    label = "TopAppBar",
                    component = getComponentInstanceOf(componentJson),
                    icon = Icons.Default.ExitToApp
                )
            }

            SduiConstants.ComponentType.HomeView -> {
                NavItem(
                    label = "Home",
                    component = getComponentInstanceOf(componentJson),
                    icon = Icons.Default.Home
                )
            }

            SduiConstants.ComponentType.SearchView -> {
                NavItem(
                    label = "Search",
                    component = getComponentInstanceOf(componentJson),
                    icon = Icons.Default.Search
                )
            }

            SduiConstants.ComponentType.PanelSetting -> {
                NavItem(
                    label = "Setting",
                    component = getComponentInstanceOf(componentJson),
                    icon = Icons.Default.Settings
                )
            }

            // Amadeus Api Screens
            SduiConstants.ComponentType.Amadeus.HomeScreen -> {
                NavItem(
                    label = "Home",
                    component = getComponentInstanceOf(componentJson),
                    icon = Icons.Default.Home
                )
            }

            SduiConstants.ComponentType.Amadeus.ScheduleScreen -> {
                NavItem(
                    label = "Schedule",
                    component = getComponentInstanceOf(componentJson),
                    icon = Icons.Default.DateRange
                )
            }

            SduiConstants.ComponentType.Amadeus.Travel -> {
                NavItem(
                    label = "Travel",
                    component = getComponentInstanceOf(componentJson),
                    icon = Icons.Filled.AirplanemodeActive
                )
            }

            SduiConstants.ComponentType.Amadeus.Profile -> {
                NavItem(
                    label = "Profile",
                    component = getComponentInstanceOf(componentJson),
                    icon = Icons.Filled.Person
                )
            }

            SduiConstants.ComponentType.Amadeus.Auth.Login -> {
                NavItem(
                    label = "Login",
                    component = getComponentInstanceOf(componentJson),
                    icon = Icons.Filled.Login
                )
            }

            else -> {
                println("Missing NavItem factory for $childComponentType")
                NavItem(
                    label = "Missing_Factory",
                    component = getComponentInstanceOf(componentJson),
                    icon = Icons.Default.Close
                )
            }
        }
    }

    override fun getComponentInstanceOf(
        componentJson: JsonObject
    ): Component {

        val componentType: String =
            componentJson
                .getValue(SduiConstants.JsonKeyName.componentType)
                .jsonPrimitive
                .content

        return when (componentType) {

            SduiConstants.ComponentType.Panel -> {
                PanelComponent(
                    viewModelFactory = PanelViewModelFactory(
                        koinComponent = this@SduiComponentFactory,
                        sduiHandler = PanelSduiHandler(componentJson, this),
                        panelStatePresenter = PanelComponentDefaults.createPanelStatePresenter(
                            dispatcher = Dispatchers.Main
                        )
                    ),
                    content = PanelComponentDefaults.PanelComponentView
                )
            }

            SduiConstants.ComponentType.Drawer -> {

                DrawerComponent(
                    viewModelFactory = DrawerViewModelFactory(
                        koinComponent = this@SduiComponentFactory,
                        sduiHandler = DrawerSduiHandler(
                            componentJson,
                            this@SduiComponentFactory
                        ),
                        drawerStatePresenter = DrawerComponentDefaults.createDrawerStatePresenter(
                            dispatcher = Dispatchers.Main
                        )
                    ),
                    content = DrawerComponentDefaults.DrawerComponentView
                )
            }

            SduiConstants.ComponentType.BottomNavigation -> {

                BottomNavigationComponent(
                    viewModelFactory = BottomNavigationViewModelFactory(
                        koinComponent = this@SduiComponentFactory,
                        sduiHandler = BottomNavigationSduiHandler(
                            jsonObject = componentJson,
                            sduiComponentFactory = this@SduiComponentFactory
                        ),
                        bottomNavigationStatePresenter = BottomNavigationComponentDefaults.createBottomNavigationStatePresenter(
                            dispatcher = Dispatchers.Main
                        )
                    ),
                    content = BottomNavigationComponentDefaults.BottomNavigationComponentView
                )
            }

            SduiConstants.ComponentType.HotelDemoComponent -> {
                StateComponent<HotelDemoViewModel>(
                    viewModelFactory = HotelDemoViewModelFactory(this),
                    content = HotelDemoComponentView
                )
            }

            SduiConstants.ComponentType.AirportDemoComponent -> {
                StateComponent<AirportDemoViewModel>(
                    viewModelFactory = AirportDemoViewModelFactory(this),
                    content = AirportDemoComponentView
                )
            }

            SduiConstants.ComponentType.Setting -> {
                StateComponent<SettingsViewModel>(
                    viewModelFactory = SettingsViewModelFactory(),
                    content = SettingsComponentView
                )
            }

            SduiConstants.ComponentType.SimpleTopAppBar -> {
                StateComponent<CustomTopAppBarViewModel>(
                    viewModelFactory = CustomTopAppBarFactory(
                        koinComponent = this@SduiComponentFactory
                    ),
                    content = CustomTopAppBar
                )
            }

            SduiConstants.ComponentType.HomeView -> {
                StateComponent<HomeViewModel>(
                    viewModelFactory = HomeViewModelFactory(this),
                    content = HomeComponentView
                )
            }

            SduiConstants.ComponentType.SearchView -> {
                StateComponent<SearchViewModel>(
                    viewModelFactory = SearchViewModelFactory(),
                    content = SearchComponentView
                )
            }

            SduiConstants.ComponentType.PanelSetting -> {
                StateComponent<PanelSettingViewModel>(
                    viewModelFactory = PanelSettingViewModelFactory(
                        koinComponent = this@SduiComponentFactory
                    ),
                    content = PanelSettingComponentView
                )
            }

            SduiConstants.ComponentType.Amadeus.HomeScreen -> {

                val database: Database = get()
                val timeProvider: ITimeProvider = get()

                TopBarComponent<AmadeusHomeCoordinatorViewModel>(
                    viewModelFactory = AmadeusHomeCoordinatorViewModelFactory(
                        TopBarComponentDefaults.createTopBarStatePresenter(),
                        database,
                        timeProvider
                    ),
                    content = TopBarComponentDefaults.TopBarComponentView
                )
            }

            SduiConstants.ComponentType.Amadeus.ScheduleScreen -> {
                StateComponent<ScheduleViewModel>(
                    viewModelFactory = ScheduleViewModelFactory(),
                    content = ScheduleComponentView
                )
            }

            SduiConstants.ComponentType.Amadeus.Profile -> {

                val accountPlugin: AccountPlugin = get()

                StateComponent<ProfileViewModel>(
                    viewModelFactory = ProfileViewModelFactory(
                        accountPlugin = accountPlugin
                    ),
                    content = ProfileComponentView
                )
            }

            SduiConstants.ComponentType.Amadeus.Auth.Login -> {

                val accountPlugin: AccountPlugin = get()

                StackComponent<AuthViewModel>(
                    viewModelFactory = AuthViewModelFactory(
                        accountPlugin = accountPlugin,
                        stackStatePresenter = StackComponentDefaults.createStackStatePresenter()
                    ),
                    content = StackComponentDefaults.DefaultStackComponentView
                )
            }

            else -> {
                println("Missing Component factory for $componentType")
                ComponentMissingImplementation(componentType)
            }
        }

    }

}
