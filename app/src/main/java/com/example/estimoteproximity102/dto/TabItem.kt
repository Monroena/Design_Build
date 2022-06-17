package com.example.estimoteproximity102.dto

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.MailOutline
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.estimoteproximity102.HomeScreen
import com.example.estimoteproximity102.LogScreen
import com.example.estimoteproximity102.NotesScreen

typealias ComposableFun = @Composable () -> Unit

sealed class TabItem(val title: String, val icons: ImageVector, val screens: ComposableFun) {

    object Info : TabItem(
        title = "Info",
        icons = Icons.Outlined.AccountCircle,
        screens = { HomeScreen() }
    )

    object Log : TabItem(
        title = "Se Log",
        icons = Icons.Outlined.List,
        screens = { LogScreen() }
    )

    object Notes : TabItem(
        title = "Notater",
        icons = Icons.Outlined.MailOutline,
        screens = { NotesScreen() }
    )
}




