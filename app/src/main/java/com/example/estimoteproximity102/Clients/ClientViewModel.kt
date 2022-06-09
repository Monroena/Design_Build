package com.example.estimoteproximity102.Clients

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel


class ClientViewModel : ViewModel() {
    var clientRepository = ClientRepositoryFirestore()

    private var _client = clientRepository.clients.toMutableStateList()
    val clients: List<Clients>
        get() = _client

    init {
        clientRepository.getClients()
    }

    fun addClients(){
        clientRepository.getClients();

    }
}

