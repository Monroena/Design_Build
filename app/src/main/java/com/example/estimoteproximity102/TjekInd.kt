package com.example.estimoteproximity102

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.estimoteproximity102.ui.theme.EstimoteProximity102Theme


@Preview(showBackground = true)
@Composable
fun TjekIndPreview() {
    EstimoteProximity102Theme {
        TjekInd()
    }
}

@Composable
fun TjekInd() {
//    var BorgerNavn by remember { mutableStateOf("") }
    var BorgerOpgaveliste by remember { mutableStateOf("")}
    val context = LocalContext.current
    Column(
        modifier = Modifier.padding(horizontal = 10.dp)
    ) {
//        OutlinedTextField(
//            value = BorgerNavn,
//            onValueChange = { BorgerNavn = it },
//            label = { Text(stringResource(R.string.borgerNavn)) },
//            modifier = Modifier.fillMaxWidth()
//        )
        Text(text = "Borgerens navn")
        OutlinedTextField(
            value = BorgerOpgaveliste,
            onValueChange = { BorgerOpgaveliste = it },
            label = { Text(stringResource(R.string.borgerOpgaveliste))},
            modifier = Modifier.fillMaxWidth()
        )
        //Text(text = )
        Button(
            onClick = {
//                Toast.makeText(
//                    context,
//                    "Du er tjekket ind",
//                    Toast.LENGTH_LONG
//                ).show()
            }) {
            Text(text = "Tjek Ind")
        }
    }
}
