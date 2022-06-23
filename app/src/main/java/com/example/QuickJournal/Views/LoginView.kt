package com.example.QuickJournal.ui.theme

import android.util.Log
import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.QuickJournal.R
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

@Composable
fun LoginView(onUserLoggedIn: () -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        val username = remember { mutableStateOf("") }
        val password = remember { mutableStateOf("") }

        val onUsernameChanged = { newEmail: String ->
            Log.i("staff", "Parent state update $newEmail")
            username.value = newEmail
        }

        val onPasswordChanged = { newPassword: String ->
            Log.i("staff", "Parent state update $newPassword")
            password.value = newPassword
        }

        WelcomeText()
        Username(username, onUsernameChanged)
        Password(password, onPasswordChanged)
        SignInButton(username, password, onUserLoggedIn)
    }
}

@Composable
fun WelcomeText() {
    Text("Velkommen til QuickJournal!",color = Color(0xFF3CB8AC), fontSize = 26.sp, modifier = Modifier.padding(8.dp))

}

@Composable
fun SignInButton(
    username: MutableState<String>,
    password: MutableState<String>,
    onUserLoggedIn: () -> Unit
) {
//    var buttonColor = Color.Gray
//    if (email.value == "hej"){
//        buttonColor = Color.Green
//    }
    val context = LocalContext.current
    Button(
        onClick = {

            Log.i("Staff", "Username value: " + username.value)
            Log.i("Staff", "Password value " + password.value)
            val db = Firebase.firestore
            db.collection("staff").get().addOnSuccessListener { result ->
                for (document in result) {
                    if (username.value == document.data["username"] && password.value == document.data["password"]) {
                        Log.i("Staff", "Input matchede noget i databasen!")
                        onUserLoggedIn()
                        Toast.makeText(
                            context,
                            "Du logges ind",
                            Toast.LENGTH_LONG
                            ).show()
                    }
                            else if(username.value != document.data["username"] && password.value != document.data["password"]){
                        //Toast.makeText(
                        //   context,
                         //   "Bruger ikke fundet i systemet",
                        //    Toast.LENGTH_LONG
                        //).show()

                    } else {
                        Log.i("Staff", "Input matchede ikke det i databasen")
                    }
                    Log.i("Staff", document.data["username"].toString())
                    Log.i("Staff", document.data["password"].toString())
                    // Log.d("Staff", "${document.id} => ${document.data}")
                }
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
            text = stringResource(id = (R.string.sign_in))
        )

    }
}

@Composable
fun Password(password: MutableState<String>, onPasswordChanged: (String) -> Unit) {
    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = password.value, onValueChange = { onPasswordChanged(it) },
        label = { Text(text = stringResource(R.string.password)) },
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        shape = RoundedCornerShape(9.dp),
        visualTransformation = PasswordVisualTransformation()
    )
}

@Composable
fun Username(username: MutableState<String>, onUsernameChanged: (String) -> Unit) {
    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = username.value,
        onValueChange = { onUsernameChanged(it) },
        label = { Text(text = stringResource(R.string.username)) },
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Yellow,
            unfocusedIndicatorColor = Color.Transparent
        ),
        shape = RoundedCornerShape(12.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
    )

}
