package com.example.estimoteproximity102.Clients

import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import dtu.engtech.iabr.stateincompose.core.Constants

@Composable
fun ClientCardList(
    list: List<Clients>,
    modifier: Modifier = Modifier
) {
    Log.d(Constants.FIREBASETAG, "ClientList size: ${list.size}")
    LazyColumn(
        modifier = modifier
    ) {
        items(
            items = list
        ) { client ->
            ClientCard(
                clients = client
            )
        }
    }
}
