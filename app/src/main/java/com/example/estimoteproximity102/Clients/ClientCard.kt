package com.example.estimoteproximity102.Clients

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ClientCard(
    clients: Clients,
    modifier: Modifier = Modifier
) {
    Row(modifier = Modifier.padding(all = 8.dp)) {

        Spacer(modifier = Modifier.width(8.dp))
        Column() {
            Text(
                text = clients?.name ?: ""
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = clients?.address ?: "",
                modifier = Modifier.padding(all = 4.dp),
                style = MaterialTheme.typography.body2
            )
        }
    }
}