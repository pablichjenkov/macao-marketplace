package com.macaosoftware.plugin.account

fun createAccountPlugin(
    accountPluginSwiftWrapperBase: AccountPluginSwiftWrapperBase
): AccountPlugin {
    // Supabase doesn't need any Swift library. So we simply ignore accountPluginWrapperBase
    return SupabaseAccountPlugin()
}