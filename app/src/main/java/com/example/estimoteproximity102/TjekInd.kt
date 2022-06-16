package com.example.estimoteproximity102

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.estimoteproximity102.ui.theme.EstimoteProximity102Theme


@Preview(showBackground = true)
@Composable
fun TjekIndPreview() {
    EstimoteProximity102Theme {
        TjekInd()
    }
}
@Composable
fun TjekIndView(navController: NavController){
    TjekInd()
}

@Composable
fun TjekInd() {
    var Brugernavn by remember { mutableStateOf("")}
    val context = LocalContext.current
    Column(
        modifier = Modifier.padding(horizontal = 10.dp)
    ) {
        Text(text = "Borgerens navn")
        OutlinedTextField(
            value = Brugernavn,
            onValueChange = { Brugernavn = it },
            //Brugernavnet er navnet på visitor der skal logges
           // label = { Text(stringResource(R.string.rugerNavn))},
            modifier = Modifier.fillMaxWidth()
        )
        //Text(text = )
        Button(
            onClick = {}
        ) {
            Text(text = "Tjek Ind")
        }
    }
}


