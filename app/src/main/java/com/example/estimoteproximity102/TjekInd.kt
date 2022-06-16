package com.example.estimoteproximity102

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.estimoteproximity102.ui.theme.EstimoteProximity102Theme
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

private const val TAG = "TjekInd"

@Preview(showBackground = true)
@Composable
fun TjekIndPreview() {
    //TjekInd()
}
@Composable
fun TjekIndView(navController: NavController){
    //TjekInd()
}

@Composable
fun TjekInd(navController: NavController){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ){
        val visitorNavn = remember { mutableStateOf("") }
        val onVisitorNavnChanged = { newVisitorNavn: String ->
            //Test af opdatering af parentkomponentens state i log
            Log.i("Visitor", "Parent state opdatering $newVisitorNavn")
            visitorNavn.value = newVisitorNavn
        }
    //Brug de øvrige composable funktioner her
    //BorgerNavn()
        Text(
            text = "Borgerens navn"
        )
    //VisitorNavn(visitorNavn, onVisitorNavnChanged)
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = visitorNavn.value,
            onValueChange = { onVisitorNavnChanged(it) },
            label = { Text(text = "VisitorNavnFelt") },
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Gray,
                unfocusedIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(12.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )
    //TjekIndButton(visitorNavn)
        Button(
            onClick = { navController.navigate("ClientInfo")
                Log.i("VisitorNavne", "VisitorNavn" + visitorNavn.value)
                val db = Firebase.firestore
                val visitorNavnn = visitorNavn.value
                val visitorNavn = hashMapOf(
                    "VisitorNavn" to visitorNavnn
                )
                db.collection("VisitorLog")
                    .add(visitorNavn)
                    .addOnSuccessListener { documentReference ->
                        Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                    }
                    .addOnFailureListener { e ->
                        Log.w(TAG, "Error adding document", e)
                    }
            },
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(24.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Gray,
                contentColor = Color.White
            )
        ) {
            Text(
                text = stringResource(id = (R.string.tjek_ind))
            )
        }
    }
}

@Composable
fun BorgerNavn(){
    Text(
        text = "Borgerens navn"
    )
}

@Composable
fun VisitorNavn(visitorNavn: MutableState<String>, onVisitorNavnChanged: (String) -> Unit) {
    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = visitorNavn.value,
        onValueChange = { onVisitorNavnChanged(it) },
        label = { Text(text = "VisitorNavnFelt") },
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Gray,
            unfocusedIndicatorColor = Color.Transparent
        ),
        shape = RoundedCornerShape(12.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
    )
}

@Composable
fun TjekIndButton(visitorNavn: MutableState<String>) {
    Button(
        onClick = {
            Log.i("VisitorNavne", "VisitorNavn" + visitorNavn.value)
            val db = Firebase.firestore
            val visitorNavnn = visitorNavn.value
            val visitorNavn = hashMapOf(
                "VisitorNavn" to visitorNavnn
            )
            db.collection("VisitorLog")
                .add(visitorNavn)
                .addOnSuccessListener { documentReference ->
                    Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    Log.w(TAG, "Error adding document", e)
                }
        },
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(24.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Gray,
            contentColor = Color.White
        )
    ) {
        Text(
            text = stringResource(id = (R.string.tjek_ind))
        )
    }
}