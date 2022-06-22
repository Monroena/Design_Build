package com.example.QuickJournal.dao

import com.example.QuickJournal.dto.Clients

interface ClientRepository {
    val clients: List<Clients>

    fun getClients(clientID: String)

    fun addListener()
}

