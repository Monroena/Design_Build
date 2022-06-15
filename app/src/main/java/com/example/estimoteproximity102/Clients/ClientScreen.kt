package com.example.estimoteproximity102.Clients

import android.service.autofill.OnClickAction
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun ClientScreen(


    clientViewModel: ClientViewModel = viewModel(),
) {
    Column{
        Row{

            Text("${clientViewModel.clientRepository.clients.size}",/* modifier = Modifier.clickable()*/)
            Spacer(modifier = Modifier.width(45.dp)
                .padding(vertical = 16.dp, horizontal = 45.dp)
            )

/*
            Button(onClick = { clientViewModel.hashCode() }) {
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
