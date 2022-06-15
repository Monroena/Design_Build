package com.example.estimoteproximity102.Clients

interface ClientRepository {
    abstract val clients: List<ClientMembers>
    fun getClients(clientID: String )
    //fun getClients()
    fun addListener()

    fun getClients()
}