package com.example.QuickJournal

import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.QuickJournal.dto.FirebaseConstants
import com.example.QuickJournal.dto.Clients

@Composable
fun ClientCardList(
    navController: NavController,
    list: List<Clients>,
    modifier: Modifier = Modifier
) {
    Log.d(FirebaseConstants.FIREBASETAG, "ClientList size: ${list.size}")
    LazyColumn(
        modifier = modifier
    ) {
        items(
            items = list
        ) { client ->
            ClientCard(
                navController,
                clients = client

            )
        }
    }
}
