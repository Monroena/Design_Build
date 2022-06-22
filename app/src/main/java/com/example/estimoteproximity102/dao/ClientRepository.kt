package com.example.estimoteproximity102.dao

import com.example.estimoteproximity102.dto.Clients

interface ClientRepository {
    val clients: List<Clients>

    fun getClients(clientID: String)

    fun addListener()
}

