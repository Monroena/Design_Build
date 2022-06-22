package com.example.QuickJournal

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.example.QuickJournal.dao.ClientRepositoryFirestore
import com.example.QuickJournal.dto.Clients


class ClientViewModel : ViewModel() {
    var clientRepository = ClientRepositoryFirestore()
    private var _client = clientRepository.clients.toMutableStateList()
    val clients: List<Clients>
    get() = _client

    fun getClient(clientID: String) {
        clientRepository.getClients(clientID)
    } }
