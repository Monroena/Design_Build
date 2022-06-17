package com.example.estimoteproximity102.ui.theme

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.estimoteproximity102.R
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
        val email = remember { mutableStateOf("") }
        val password = remember { mutableStateOf("") }

        val onEmailChanged = { newEmail: String ->
            Log.i("Staff", "Parent state opdatering $newEmail")
            email.value = newEmail
        }

        val onPasswordChanged = { newPassword: String ->
            Log.i("Staff", "Parent state update $newPassword")
            password.value = newPassword
        }

        Title()
        Username(email, onEmailChanged)
        Password(password, onPasswordChanged)
        SignInButton(email, password, onUserLoggedIn)
    }
}

@Composable
fun Title() {
    Text(
        text = stringResource(R.string.sign_in_welcome_text)
    )
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

    Button(
        onClick = {
            Log.i("Staff", "Username value: " + username.value)
            Log.i("Staff", "Password value " + password.value)
            val db = Firebase.firestore
            db.collection("staff").get().addOnSuccessListener { result ->
                for (document in result) {
                    if (username.value == document.data["username"] && password.value == document.data["password"]) {
                        Log.i("Staff", "Virkede!")
                        onUserLoggedIn()
                    } else {
                        Log.i("Staff", "Virkede ikke")
                    }
                    Log.i("Staff", document.data["username"].toString())
                    Log.i("Staff", document.data["passwird"].toString())
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
