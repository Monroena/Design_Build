package com.example.estimoteproximity102

import android.util.Log
import androidx.compose.foundation.background
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

private const val TAG = "PROXIMITY"

@Composable
fun CreateNoteView(navController: NavController) {
    CreateNote(navController)
}

@Composable
fun CreateNote(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.background),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        val notat = remember { mutableStateOf("") }
        val onNotatChanged = { newNotat: String ->
            //Test af opdatering af parentkomponentens state i log
            Log.i("Staff", "Parent state opdatering $newNotat")
            notat.value = newNotat
        }

        Title2()
        Notat(notat, onNotatChanged)
        CreateNoteButton(notat, navController)
    }
}

//stateless
@Composable
fun Title2() {
    Text(
        text = stringResource(R.string.info),
       // fontStyle =
    )
}

@Composable
fun Notat(notat: MutableState<String>, onNotatChanged: (String) -> Unit) {
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        value = notat.value,
        onValueChange = { onNotatChanged(it) },
        label = { Text(text = stringResource(R.string.notat)) },
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Gray,
            unfocusedIndicatorColor = Color.Transparent
        ),
        shape = RoundedCornerShape(12.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
    )
}

@Composable
fun CreateNoteButton(notat: MutableState<String>, navController: NavController) {
    Button(
        onClick = {
            navController.navigate("ClientPage")
            Log.i("Notes", "Notat tekst: " + notat.value)
            val db = Firebase.firestore
            val noteWrited = notat.value
            val note = hashMapOf(
                "note" to noteWrited,
                "beaconTag" to "500"
            )
            db.collection("Notes")
                .add(note)
                .addOnSuccessListener { documentReference ->
                    Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    Log.w(TAG, "Error adding document", e)
                }
        },

        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        contentPadding = PaddingValues(24.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Gray,
            contentColor = Color.White
        )
    ) {
        Text(
            text = stringResource(id = (R.string.create_note))
        )
    }
}
