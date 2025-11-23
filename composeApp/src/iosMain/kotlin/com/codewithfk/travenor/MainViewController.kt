package com.codewithfk.travenor

import androidx.compose.ui.window.ComposeUIViewController
import com.codewithfk.travenor.di.appModule
import org.koin.core.context.startKoin

fun MainViewController() = ComposeUIViewController(configure = {
    startKoin { modules(appModule) }
}) { App() }