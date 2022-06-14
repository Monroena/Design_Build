package com.example.estimoteproximity102

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.MailOutline

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

typealias ComposableFun = @Composable ()->Unit

sealed class TabItem(val title:String,val icons:ImageVector, val screens:ComposableFun) {

    object Info : TabItem(
        title = "Info",
        icons= Icons.Outlined.AccountCircle,
        screens = { HomeScreen()})
    object Log: TabItem(
        title = "Log",
        icons = Icons.Outlined.CheckCircle,
        screens={ LogScreen()}
    )
    object Notes : TabItem(
        title = "Noter",
        icons= Icons.Outlined.MailOutline,
        screens = { NotesScreen() }
    )


}