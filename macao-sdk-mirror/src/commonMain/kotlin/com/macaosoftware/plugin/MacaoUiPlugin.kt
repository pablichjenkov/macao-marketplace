package com.macaosoftware.plugin

import com.macaosoftware.sdui.MacaoComponentFactory


interface MacaoUiPlugin {

    fun provideMacaoComponentFactory(): MacaoComponentFactory

}