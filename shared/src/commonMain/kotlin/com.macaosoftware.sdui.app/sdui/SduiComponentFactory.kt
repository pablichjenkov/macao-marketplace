package com.macaosoftware.sdui.app.sdui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.sharp.List
import androidx.compose.material.icons.sharp.Search
import com.macaosoftware.component.core.Component
import com.macaosoftware.component.core.NavItem
import com.macaosoftware.component.drawer.DrawerComponent
import com.macaosoftware.component.drawer.DrawerComponentDefaults
import com.macaosoftware.component.navbar.BottomNavigationComponent
import com.macaosoftware.component.navbar.BottomNavigationComponentDefaults
import com.macaosoftware.component.panel.PanelComponent
import com.macaosoftware.component.panel.PanelComponentDefaults
import com.macaosoftware.component.viewmodel.StateComponent
import com.macaosoftware.sdui.app.data.SduiConstants
import com.macaosoftware.sdui.app.marketplace.SettingsVoyager2
import com.macaosoftware.sdui.app.view.panel.PanelSettingComponentView
import com.macaosoftware.sdui.app.view.root.AirportDemoComponentView
import com.macaosoftware.sdui.app.view.root.topappbar.CustomTopAppBar
import com.macaosoftware.sdui.app.view.root.home.HomeComponentView
import com.macaosoftware.sdui.app.view.root.HotelDemoComponentView
import com.macaosoftware.sdui.app.view.root.SettingsComponentView
import com.macaosoftware.sdui.app.view.root.search.SearchComponentView
import com.macaosoftware.sdui.app.viewmodel.AirportDemoViewModel
import com.macaosoftware.sdui.app.viewmodel.CustomTopAppBarViewModel
import com.macaosoftware.sdui.app.viewmodel.HomeViewModel
import com.macaosoftware.sdui.app.viewmodel.HotelDemoViewModel
import com.macaosoftware.sdui.app.viewmodel.SearchViewModel
import com.macaosoftware.sdui.app.viewmodel.SettingsViewModel
import com.macaosoftware.sdui.app.viewmodel.factory.AirportDemoViewModelFactory
import com.macaosoftware.sdui.app.viewmodel.factory.BottomNavigationViewModelFactory
import com.macaosoftware.sdui.app.viewmodel.factory.CustomTopAppBarFactory
import com.macaosoftware.sdui.app.viewmodel.factory.DrawerViewModelFactory
import com.macaosoftware.sdui.app.viewmodel.factory.HomeViewModelFactory
import com.macaosoftware.sdui.app.viewmodel.factory.HotelDemoViewModelFactory
import com.macaosoftware.sdui.app.viewmodel.factory.panelfactory.PanelViewModelFactory
import com.macaosoftware.sdui.app.viewmodel.factory.SearchViewModelFactory
import com.macaosoftware.sdui.app.viewmodel.factory.SettingsViewModelFactory
import com.macaosoftware.sdui.app.viewmodel.factory.panelfactory.PanelSettingViewModelFactory
import com.macaosoftware.sdui.app.viewmodel.panelViewModel.PanelSettingViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonPrimitive
import org.koin.core.Koin
import org.koin.core.KoinApplication
import org.koin.core.component.KoinComponent

class SduiComponentFactory(
    private val koinApplication: KoinApplication
) : KoinComponent {

    override fun getKoin(): Koin = koinApplication.koin

    fun getNavItemOf(
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
                    icon = Icons.Sharp.Search
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

            SduiConstants.ComponentType.Setting2 -> {
                NavItem(
                    label = "Setting 2",
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

            else -> {
                throw Exception("Missing NavItem factory for $childComponentType")
            }
        }
    }

    fun getComponentInstanceOf(
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
                        sduiHandler = DrawerSduiHandler(componentJson, this),
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
                        sduiHandler = BottomNavigationSduiHandler(componentJson, this),
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

            SduiConstants.ComponentType.Setting2 -> {
                SettingsVoyager2()
            }

            SduiConstants.ComponentType.SimpleTopAppBar ->{
                StateComponent<CustomTopAppBarViewModel>(
                    viewModelFactory = CustomTopAppBarFactory(),
                    content = CustomTopAppBar
                )
            }

            SduiConstants.ComponentType.HomeView ->{
                StateComponent<HomeViewModel>(
                    viewModelFactory = HomeViewModelFactory(this),
                    content = HomeComponentView
                )
            }

            SduiConstants.ComponentType.SearchView ->{
                StateComponent<SearchViewModel>(
                    viewModelFactory = SearchViewModelFactory(),
                    content = SearchComponentView
                )
            }

            SduiConstants.ComponentType.PanelSetting ->{
                StateComponent<PanelSettingViewModel>(
                    viewModelFactory = PanelSettingViewModelFactory(),
                    content = PanelSettingComponentView
                )
            }

            else -> {
                throw Exception("Missing Component factory for $componentType")
            }
        }

    }

}
