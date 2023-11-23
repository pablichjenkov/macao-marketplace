package com.macaosoftware.sdui.app.plugin

import com.macaosoftware.sdui.app.sdui.MacaoComponentFactory

interface MacaoUiPlugin {

    fun provideMacaoComponentFactory(): MacaoComponentFactory

}