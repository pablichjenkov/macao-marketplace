package com.macaosoftware.plugin.account

fun createAccountPlugin(
    accountPluginWrapperBase: AccountPluginWrapperBase
): AccountPlugin {
    // Supabase doesn't need any Swift library. So we simply ignore accountPluginWrapperBase
    return SupabaseAccountPlugin()
}