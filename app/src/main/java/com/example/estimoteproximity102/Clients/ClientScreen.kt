package com.example.estimoteproximity102.Clients

import android.service.autofill.OnClickAction
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
/*
@Composable
fun ClientScreenView(navController: NavController){
    ClientScreen(navController=navController)
}*/

@Composable
fun ClientScreen(
    clientViewModel: ClientViewModel = viewModel(),
    navController: NavController
) {
    Column{
        Row{

            Text("${clientViewModel.clientRepository.clients.size}",)
            Spacer(modifier = Modifier
                .width(45.dp)
                .padding(vertical = 16.dp, horizontal = 45.dp)
            )
        }

        Spacer(modifier = Modifier.width(200.dp))
        ClientCardList(
            navController,
            list = clientViewModel.clientRepository.clients
        )

        Button(onClick = { navController.navigate("ClientInfo")}) {
            Text(text = "Til ClientInfo - Borger side")


        }




}}









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