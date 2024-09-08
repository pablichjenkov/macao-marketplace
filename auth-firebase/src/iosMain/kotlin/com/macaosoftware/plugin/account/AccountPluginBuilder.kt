package com.macaosoftware.plugin.account

fun createAccountPlugin(
    accountPluginWrapperBase: AccountPluginWrapperBase
): AccountPlugin {
    val firebaseAuthKmpWrapper  = accountPluginWrapperBase as FirebaseAuthKmpWrapper
    return FirebaseAccountPlugin(firebaseAuthKmpWrapper)
}