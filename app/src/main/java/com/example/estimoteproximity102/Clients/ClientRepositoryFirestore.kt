package com.example.estimoteproximity102.Clients

import android.util.Log
import androidx.compose.runtime.toMutableStateList
import com.google.firebase.firestore.FirebaseFirestore
import com.google.flatbuffers.Constants

class ClientRepositoryFirestore: ClientRepository {
    var clients = mutableListOf<Clients>().toMutableStateList()

    override fun getClients() {
        FirebaseFirestore.getInstance().collection(dtu.engtech.iabr.stateincompose.core.Constants.CLIENTS)
            .addSnapshotListener { snapshot, e ->
                if (e != null) {
                    Log.w(dtu.engtech.iabr.stateincompose.core.Constants.FIREBASETAG, "Listen failed.", e)
                    //return@addSnapshotListener
                }

                if (snapshot != null) {
                    clients = snapshot.toObjects(Clients::class.java).toMutableStateList()
                    Log.d(dtu.engtech.iabr.stateincompose.core.Constants.FIREBASETAG, "Current data size: ${clients.size}")
                    logClients()

                } else {
                    Log.d(dtu.engtech.iabr.stateincompose.core.Constants.FIREBASETAG, "Current data: null")
                }
            }
    }

    fun logClients(){
        for(clientMember in clients) {
            Log.d(dtu.engtech.iabr.stateincompose.core.Constants.FIREBASETAG, "Member: $clientMember")
        }
    }

}