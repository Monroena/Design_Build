package com.example.estimoteproximity102.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.estimoteproximity102.R

@Preview(showSystemUi = true)
@Composable
fun LoginSide(){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ){
        Title()
        Email()
        Password()
        SignInButton()
    }
}
@Composable fun Title(){
    Text(
       text = stringResource(R.string.sign_in_welcome_text)

    )
}
@Composable fun SignInButton(){
    Button(
        onClick = {},
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text= stringResource(id = (R.string.sign_in))
        )
        
    }
}
@Composable fun Password(){
    val passwordState = remember { mutableStateOf(TextFieldValue())}
    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = passwordState.value, onValueChange ={passwordState.value=it}
    )
}
@Composable fun Email(){
    val emailState = remember { mutableStateOf(TextFieldValue())}
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = emailState.value,
            onValueChange ={emailState.value=it}
        )
    }
