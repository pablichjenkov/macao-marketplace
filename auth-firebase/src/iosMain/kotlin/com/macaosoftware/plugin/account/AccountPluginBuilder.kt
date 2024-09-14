package com.macaosoftware.plugin.account

fun createAccountPlugin(
    accountPluginSwiftWrapperBase: AccountPluginSwiftWrapperBase
): AccountPlugin {
    val firebaseAuthKmpWrapper  = accountPluginSwiftWrapperBase as FirebaseAuthKmpSwiftWrapper
    return FirebaseAccountPlugin(firebaseAuthKmpWrapper)
}