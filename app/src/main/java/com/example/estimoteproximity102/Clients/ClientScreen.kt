package com.example.estimoteproximity102.Clients

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun ClientScreen(
    modifier: Modifier = Modifier,
    clientViewModel: ClientViewModel = viewModel(),
) {
    Column{
        Row{
            Text("${clientViewModel.clientRepository.clients.size}")
            Spacer(modifier = Modifier.width(40.dp))
            Button(onClick = { clientViewModel.addClients() }) {
                Text(text = "Clients")
            }
        }
        ClientCardList(
            list = clientViewModel.clientRepository.clients
        )
    }
}