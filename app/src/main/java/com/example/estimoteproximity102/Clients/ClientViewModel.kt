package com.example.estimoteproximity102.Clients

import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Color
import androidx.compose.ui.input.pointer.PointerIconDefaults
import androidx.compose.ui.input.pointer.PointerIconDefaults.Text
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.SemanticsProperties.Text
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.KeyboardType.Companion.Text
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.example.estimoteproximity102.R
///import com.example.estimoteproximity102.TAG
import com.example.estimoteproximity102.model.ClientRepositoryFirestore
import com.google.ar.schemas.sceneform.SamplerUsageType.Color
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class ClientViewModel : ViewModel(){
    var clientRepository = ClientRepositoryFirestore()

    //var staffRepository: ClientRepository =ClientRepositoryFirestore()

    private var _client = clientRepository.clients.toMutableStateList()
    val clients: List<Clients>
        get() = _client



    fun getClient(clientID: String) {
        clientRepository.getClients(clientID)
    }


/*
    fun addClients(){
        clientRepository.getClients()

    }

 */

}
