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
import androidx.compose.ui.unit.dp
import com.example.estimoteproximity102.R
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

@Composable
fun LoginSide(onUserLoggedIn: () -> Unit){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ){
        val email = remember { mutableStateOf("") }
        val password = remember { mutableStateOf("") }
        val onEmailChanged = { newEmail: String ->
            //Test af opdatering af parentkomponentens state i log
            Log.i("Staff", "Parent state opdatering $newEmail")
            email.value = newEmail
        }
        val onPasswordChanged = { newPassword: String ->
            //Test af opdatering af parentkomponentens state i log
            Log.i("Staff", "Parent state update $newPassword")
            password.value = newPassword
        }

        Title()
        Email(email, onEmailChanged)
        Password(password, onPasswordChanged)
        SignInButton(email, password, onUserLoggedIn)
    }
}
//stateless
@Composable fun Title(){
    Text(
       text = stringResource(R.string.sign_in_welcome_text),
       //Gerne ændre tekststørrelsen af titlen til lidt større her.
    )
}
//Følgende er statefull men kunne mere optimalt være statesless i stedet
@Composable fun SignInButton(email: MutableState<String>, password: MutableState<String>, onUserLoggedIn: () -> Unit){
//Følgende kode er test af den løbende opdatering af SignInButton-komponentens state
//    var buttonColor = Color.Gray
//    if (email.value == "hej"){
//        buttonColor = Color.Green
//    }

    Button(
        onClick = {
            Log.i("Staff", "Email value: " + email.value)
            Log.i("Staff", "Password value " + password.value)
            val db = Firebase.firestore
            db.collection("staff").get().addOnSuccessListener{ result ->
                for (document in result) {
                    if (email.value == document.data["Email"] && password.value == document.data["Kodeord"]){
                        Log.i("Staff", "Virkede!")
                        onUserLoggedIn()
                    } else {
                        Log.i("Staff", "Virkede ikke")
                    }
                    Log.i("Staff", document.data["Email"].toString())
                    Log.i("Staff", document.data["Kodeord"].toString())
                    // Log.d("Staff", "${document.id} => ${document.data}")
                }
            }
            },

        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(24.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Gray,
            contentColor=Color.White
        )
    ) {
        Text(
            text= stringResource(id = (R.string.sign_in))
        )
    }
}
//Følgende er stateless
@Composable fun Password(password: MutableState<String>, onPasswordChanged: (String) -> Unit){
    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = password.value, onValueChange = { onPasswordChanged(it) },
        label = {Text(text= stringResource(R.string.kodeord))},
                colors=TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Gray,
                    unfocusedIndicatorColor = Color.Transparent
                ),
        shape = RoundedCornerShape(9.dp)
    )
}
//Følgende er stateless
@Composable fun Email(email: MutableState<String>, onEmailChanged: (String) -> Unit){
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = email.value,
            onValueChange = { onEmailChanged(it)},
            //i følgende er "email" defineret til at være en tekst: "Brugernavn" som kan ændres i strings.xml
            label = {Text(text= stringResource(R.string.email))},
            colors=TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Gray,
                unfocusedIndicatorColor = Color.Transparent
            ),
            shape=RoundedCornerShape(12.dp),
            keyboardOptions= KeyboardOptions(keyboardType = KeyboardType.Email)
            )

    }
