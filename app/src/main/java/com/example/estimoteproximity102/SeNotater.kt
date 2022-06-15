package com.example.estimoteproximity102

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.textInputServiceFactory
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlin.math.log

private const val TAG = "PROXIMITY"

@Composable fun SeNyNotat(){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ){
        val notatFelt = remember { mutableStateOf("")}
        val seNotat = remember { mutableStateOf("") }
        val onSeNotatChanged = { newSeNotat: String ->
            //Test af opdatering af parentkomponentens state i log
            Log.i("Notes", "Parent state opdatering $newSeNotat")
            seNotat.value = newSeNotat
        }

        Title3()
        IndtastID(seNotat, onSeNotatChanged)
        SeNotatButton(seNotat,notatFelt)
    }
}
//stateless
@Composable
fun Title3(){
    Text(
        text = stringResource(R.string.vis_notat),
    )
}
@Composable
fun IndtastID(seNotat: MutableState<String>, onSeNotatChanged: (String) -> Unit) {
    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = seNotat.value,
        onValueChange = { onSeNotatChanged(it) },
        label = { Text(text = stringResource(R.string.input_tekst)) },
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Gray,
            unfocusedIndicatorColor = Color.Transparent
        ),
        shape = RoundedCornerShape(12.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
    )
    // "6PR953um57HDlv5tEK82"
}
@Composable
fun SeNotatButton(seNotat: MutableState<String>, notatFelt: MutableState<String>) {
    Button(
        onClick = {
            Log.i("Notes", "Notat tekst: " + seNotat.value)
            val db = Firebase.firestore
            val documentPath = seNotat.value

            val docRef = db.collection("clients").document("PRQEVOfysywaOI0P5DdJ").collection("notes").document("fY3H9ufpRuTR1h1taT1n")
            docRef.get()
                .addOnSuccessListener { document ->
                    if (document != null) {
                        Log.d(TAG, "DocumentSnapshot data: ${document.data}")


                    } else {
                        Log.d(TAG, "No such document")
                    }
                }
                .addOnFailureListener { exception ->
                    Log.d(TAG, "get failed with ", exception)
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
            text = stringResource(id = (R.string.se_notat))
        )
    }
}



