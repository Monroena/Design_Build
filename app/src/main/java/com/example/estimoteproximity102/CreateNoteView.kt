package com.example.estimoteproximity102


import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
        horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        val note = remember { mutableStateOf("") }
        val onNoteChanged = { newNote: String ->
            //Test of update of parent-components state in log
            Log.i("Staff", "Parent state opdatering $newNote")
            note.value = newNote
        }

        CreateNoteTitle()
        Note(note, onNoteChanged)
        CreateNoteButton(note, navController)
    }
}

//stateless
@Composable
fun CreateNoteTitle() {
    //Text("Indtast et notat i følgende felt",color = Color(0, 400, 400), fontSize = 30.sp, modifier = Modifier.padding(8.dp) )
    Text("Indtast et notat i følgende felt",color = Color(0xFF3CB8AC), fontSize = 26.sp, modifier = Modifier.padding(8.dp))
}

@Composable
fun Note(note: MutableState<String>, onNoteChanged: (String) -> Unit) {
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        value = note.value,
        onValueChange = { onNoteChanged(it) },
        label = { Text(text = stringResource(R.string.notat)) },
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Gray,
            unfocusedIndicatorColor = Color.Transparent
        ),
        shape = RoundedCornerShape(2.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
    )
}

@Composable
fun CreateNoteButton(note: MutableState<String>, navController: NavController) {
    val context = LocalContext.current
    Button(
        onClick = {
            navController.navigate("ClientPage")
            Log.i("notes", "Note text: " + note.value)
            val db = Firebase.firestore
            val inputNote = note.value
            val noteInfo = hashMapOf(
                "note" to inputNote,
                "beaconTag" to "510",
                "name" to "Grethe Hansen"
            )
            db.collection("notes")
                .add(noteInfo)
                .addOnSuccessListener { documentReference ->
                    Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                    Toast.makeText(
                        context,
                        "Notat oprettet",
                        Toast.LENGTH_LONG
                    ).show()
                }
                .addOnFailureListener { e ->
                    Log.w(TAG, "Error adding document", e)
                }
        },

        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        shape = RoundedCornerShape(12.dp),
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
