package com.example.estimoteproximity102.Clients

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun ClientScreen(
    //modifier: ClientViewModel = Modifier,
    clientViewModel: ClientViewModel = viewModel(),
) {
    Column{
        Row{

            Text("${clientViewModel.clientRepository.clients.size}")
            Spacer(modifier = Modifier.width(45.dp)
                .padding(vertical = 16.dp, horizontal = 45.dp)
            )
            /*
            Button(onClick = { clientViewModel.addClients() }) {
                Text(text = "Clients")
            }
             */
        }
        Spacer(modifier = Modifier.width(200.dp))
        ClientCardList(
            list = clientViewModel.clientRepository.clients
        )
    }
}