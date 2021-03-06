package com.example.QuickJournal

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.QuickJournal.ViewModel.ClientViewModel

@Composable
fun ClientListView(navController: NavController) {
    ClientList(navController = navController)
}

@Composable
fun ClientList(
    navController: NavController
) {
    var clientViewModel: ClientViewModel = viewModel()
    Column {
        Row {
            Spacer(
                modifier = Modifier
                    .width(200.dp)

                    .padding(vertical = 16.dp, horizontal = 45.dp)
                    .background(Color.Black)
            )
        }
        Spacer(modifier = Modifier.width(200.dp))
        ClientCardList(
            navController,
            list = clientViewModel.clientRepository.clients
        )

    }
}


