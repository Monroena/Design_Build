package com.example.estimoteproximity102

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.example.estimoteproximity102.dto.Clients
import com.example.estimoteproximity102.dao.ClientRepositoryFirestore


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
