package com.example.estimoteproximity102

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.estimoteproximity102.dto.Clients

@Composable
fun ClientCard(
    navController: NavController,
    clients: Clients,
) {
    Row(modifier = Modifier.padding(all = 5.dp)) {
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.Transparent),

            onClick = { navController.navigate("ClientPage") }) {

            Column {
                Text(
                    text = clients.name ?: ""
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = clients.address ?: "",
                    modifier = Modifier.padding(all = 4.dp),
                    style = MaterialTheme.typography.body2

                )

            }

        }
        Spacer(modifier = Modifier.width(8.dp))

    }
}