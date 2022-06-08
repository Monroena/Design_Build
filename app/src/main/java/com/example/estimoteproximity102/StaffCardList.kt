package com.example.estimoteproximity102

import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dtu.engtech.iabr.stateincompose.core.Constants

@Composable
fun StaffCardList(
    list: List<Staff>,
    modifier: Modifier = Modifier
) {
    Log.d(Constants.FIREBASETAG, "StaffCardList size: ${list.size}")
    LazyColumn(
        modifier = modifier
    ) {
        items(
            items = list
        ) { staff ->
            StaffCard(
                staff = staff
            )
        }
    }
}
