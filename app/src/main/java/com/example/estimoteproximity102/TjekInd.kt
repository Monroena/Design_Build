package com.example.estimoteproximity102

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

private const val TAG = "TjekInd"

@Composable
fun TjekInd(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp)
            .background(color =  MaterialTheme.colors.background),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        val context = LocalContext.current
        val visitorNavn = remember { mutableStateOf("") }
        val onVisitorNavnChanged = { newVisitorNavn: String ->
            visitorNavn.value = newVisitorNavn
        }
        Text(
            text = "Borgerens navn"
        )
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = visitorNavn.value,
            onValueChange = { onVisitorNavnChanged(it) },
            label = { Text(text = "Brugernavn") },
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Gray,
                unfocusedIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(12.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )
        Button(
            onClick = {
                navController.navigate("ClientInfo")
                Toast.makeText(context, "Du er tjekket ind", Toast.LENGTH_LONG).show()
                val db = Firebase.firestore
                val newVisit = hashMapOf(
                    "Name" to visitorNavn.value,
                    "Timestamp" to
                            DateTimeFormatter
                                .ofPattern("dd-MM-yyyy HH:mm:ss")
                                .withZone(ZoneId.of("Europe/Paris"))
                                .format(Instant.now())
                )
                db.collection("VisitorLog")
                    .add(newVisit)
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
