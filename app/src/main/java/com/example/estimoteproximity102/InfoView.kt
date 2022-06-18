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
            val db = Firebase.firestore
            val beaconTag = "515"

            db.collection("clients").whereEqualTo("beaconTag", beaconTag)
                .get()
                .addOnSuccessListener { result ->
                    val information = ArrayList<String>()

                    Log.d(TAG, result.toString())
                    for (document in result) {
                        information.add("Borger navn: ")
                        information.add(document.data["name"].toString())
                        information.add("Køn: ")
                        information.add(document.data["køn"].toString())
                        information.add("Diagnose: ")
                        information.add(document.data["diagnose"].toString())
                        information.add("Fødselsdag: ")
                        information.add(document.data["birthdate"].toString())
                        information.add("Adresse: ")
                        information.add(document.data["address"].toString())
                        information.add("BeaconTag: ")
                        information.add(document.data["beaconTag"].toString())

                        Log.d(TAG, "${document.id} => ${document.data}")
                    }

                    onInfoChanged(information)
                    Log.d(TAG, information.toString())
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