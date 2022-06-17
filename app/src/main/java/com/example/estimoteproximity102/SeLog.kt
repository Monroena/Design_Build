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
import androidx.navigation.NavController
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

private const val TAG = "SeLog"

@Composable fun SeLog(){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ){
        val beaconID = 515
        val logs = remember {mutableListOf<String>().toMutableStateList()}
        val onLogsChanged  = { newLogs: ArrayList<String> ->
            logs.clear()
            for (log in newLogs){
                logs.add(log)
            }
        }
        Text(
            text = "Log for: " + beaconID
        )

        val db = Firebase.firestore
        db.collection("VisitorLog").whereEqualTo("beaconTag",beaconID)
            .get()
            .addOnSuccessListener { result ->
                val logs = ArrayList<String>()
                Log.d(TAG, result.toString())
                for (document in result) {
                    logs.add(document.data.get("Name").toString())
                }
                onLogsChanged(logs)
                Log.d(TAG, logs.toString())
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "Error getting documents: ", exception)
            }

        Text(
            text = "Info: " + logs
        )

    }
}
