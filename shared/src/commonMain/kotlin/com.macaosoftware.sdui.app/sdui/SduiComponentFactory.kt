package com.macaosoftware.sdui.app.sdui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
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
import com.macaosoftware.sdui.app.view.AirportDemoComponentView
import com.macaosoftware.sdui.app.view.HotelDemoComponentView
import com.macaosoftware.sdui.app.view.SettingsComponentView
import com.macaosoftware.sdui.app.viewmodel.AirportDemoViewModel
import com.macaosoftware.sdui.app.viewmodel.HotelDemoViewModel
import com.macaosoftware.sdui.app.viewmodel.SettingsViewModel
import com.macaosoftware.sdui.app.viewmodel.factory.AirportDemoViewModelFactory
import com.macaosoftware.sdui.app.viewmodel.factory.BottomNavigationViewModelFactory
import com.macaosoftware.sdui.app.viewmodel.factory.DrawerViewModelFactory
import com.macaosoftware.sdui.app.viewmodel.factory.HotelDemoViewModelFactory
import com.macaosoftware.sdui.app.viewmodel.factory.PanelViewModelFactory
import com.macaosoftware.sdui.app.viewmodel.factory.SettingsViewModelFactory
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
                    icon = Icons.Default.Home
                )
            }

            SduiConstants.ComponentType.HotelDemoComponent -> {
                NavItem(
                    label = "Hotel",
                    component = getComponentInstanceOf(componentJson),
                    icon = Icons.Default.Search
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

            else -> {
                throw Exception("Missing Component factory for $componentType")
            }
        }

    }

}
