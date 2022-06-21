package com.example.estimoteproximity102

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

private const val TAG = "PROXIMITY"

@Composable
fun ViewNotes() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        val notes = remember { mutableListOf<String>().toMutableStateList() }
        val onNotesChanged = { newNotes: ArrayList<String> ->
            notes.clear()
            for (note in newNotes) {
                notes.add(note)
            }
        }
        val beaconTagInput = remember { mutableStateOf("") }
        val onBeaconTagInputChanged = { newBeaconTagInput: String ->
            Log.i("notes", "Parent state update $newBeaconTagInput")
            beaconTagInput.value = newBeaconTagInput
        }
        NoteIntroduction()
        BeaconTagInput(beaconTagInput, onBeaconTagInputChanged)
        ViewNotesButton(beaconTagInput, onNotesChanged)
        Notes(notes.toMutableStateList())
    }
}

//stateless
@Composable
fun NoteIntroduction() {
    Text(
        text = stringResource(R.string.show_notes),
        )
}

@Composable
fun BeaconTagInput(beaconTagInput: MutableState<String>, onBeaconTagInputChanged: (String) -> Unit) {
    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = beaconTagInput.value,
        onValueChange = { onBeaconTagInputChanged(it) },
        label = { Text(text = stringResource(R.string.input_beaconTag)) },
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Gray,
            unfocusedIndicatorColor = Color.Transparent
        ),
        shape = RoundedCornerShape(12.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
    )
}

@Composable
fun Notes(notes: MutableList<String>) {
    Log.d("Document nr:", notes.toString())
    for (note in notes) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = note,
        )
    }
}

@Composable
fun ViewNotesButton(beaconTagInput: MutableState<String>, onNotesChanged: (ArrayList<String>) -> Unit) {
    Button(
        onClick = {
            Log.i("Clients", "BeaconTag: " + beaconTagInput.value)
            val db = Firebase.firestore
            val beaconTag = beaconTagInput.value

            db.collection("notes").whereEqualTo("beaconTag", beaconTag)
                .get()
                .addOnSuccessListener { result ->
                    val notes = ArrayList<String>()

                    Log.d(TAG, result.toString())
                    for (document in result) {
                        notes.add(document.data["note"].toString())
                    }

                    onNotesChanged(notes)
                    Log.d(TAG, notes.toString())
                }
                .addOnFailureListener { exception ->
                    Log.d(TAG, "Error getting documents: ", exception)
                }

            //*/
            //Følgende kode virker således at ud fra et bestemt indtastede document-ID hentes info fra en bestemt refDoc.
            /*
                val docRef = db.collection("Notes").document("6PR953um57HDlv5tEK82")
                docRef.get()
                    .addOnSuccessListener { document ->
                        if (document != null && ID == document.data?.get("beaconTag") ?: ID) {
                            Log.d(TAG, "DocumentSnapshot data: ${document.data}")
                        } else {
                            Log.d(TAG, "No such document")
                        }
                    }
                    .addOnFailureListener { exception ->
                        Log.d(TAG, "get failed with ", exception)
                    }

             */
        },
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(24.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Gray,
            contentColor = Color.White
        )
    ) {
        Text(
            text = stringResource(id = (R.string.view_notes))
        )
    }
}






