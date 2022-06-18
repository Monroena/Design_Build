package com.example.estimoteproximity102

import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


private const val TAG = "PROXIMITY"

@Composable
fun GetClientData(onInfoChanged: (ArrayList<String>) -> Unit) {
    Button(
        onClick = {
            Log.i("clients", "Info tekst: " + 515)
            val db = Firebase.firestore
            val id = "515"

            db.collection("clients").whereEqualTo("beaconTag", id)
                .get()
                .addOnSuccessListener { result ->
                    val informationer = ArrayList<String>()

                    Log.d(TAG, result.toString())
                    for (document in result) {
                        informationer.add(document.data["name"].toString())
                        informationer.add(document.data["address"].toString())
                        informationer.add(document.data["birthdate"].toString())
                        informationer.add(document.data["beaconTag"].toString())


                        Log.d(TAG, "${document.id} => ${document.data}")
                    }

                    onInfoChanged(informationer)
                    Log.d(TAG, informationer.toString())
                }
                .addOnFailureListener { exception ->
                    Log.d(TAG, "Error getting documents: ", exception)
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
            text = stringResource(id = (R.string.view_info))
        )

    }
}


@Composable
fun ShowClientData(seInfo: MutableList<String>) {
    for (inf in seInfo) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = inf,
            //Text(
            //    text = clients?.name ?: ""
        )
    }
}