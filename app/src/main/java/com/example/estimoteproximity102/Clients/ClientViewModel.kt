package com.example.estimoteproximity102.Clients

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.example.estimoteproximity102.model.ClientRepositoryFirestore


class ClientViewModel : ViewModel(){
    var clientRepository = ClientRepositoryFirestore()

    var staffRepository: ClientRepository =ClientRepositoryFirestore()
    private var _client = clientRepository.clients.toMutableStateList()
    val clients: List<ClientMembers>
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

