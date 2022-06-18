package com.example.estimoteproximity102

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@Composable
fun ClientListView(navController: NavController) {

    ClientList(navController = navController)
}

@Composable
fun ClientList(
    clientViewModel: ClientViewModel = viewModel(),
    navController: NavController
) {

    Column {
        Row {

            Text("${clientViewModel.clientRepository.clients.size}")
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


/*
@Composable
fun ClientScreen(   clientViewModel: ClientViewModel = viewModel()){

    LazyColumn(){
        items(20){
            Surface(modifier = Modifier.clickable {clientViewModel.clients.size}) {

                Text("${clientViewModel.clientRepository.clients.size}",/* modifier = Modifier.clickable()*/)
                Spacer(modifier = Modifier.width(45.dp)
                    .padding(vertical = 16.dp, horizontal = 45.dp)
                )
                Divider(color = androidx.compose.ui.graphics.Color.Yellow, thickness = 1.dp)
                //Spacer(modifier = Modifier.width(200.dp))

            }
            /*
            Spacer(modifier = Modifier.width(200.dp))
            ClientCardList(
                list = clientViewModel.clientRepository.clients
            )

             */

        }
    }
}


 */
