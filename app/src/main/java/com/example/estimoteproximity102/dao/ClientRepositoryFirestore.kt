package com.example.estimoteproximity102.dao

import android.util.Log
import androidx.compose.runtime.toMutableStateList
import com.example.estimoteproximity102.dto.FirebaseConstants
import com.example.estimoteproximity102.dto.Clients
import com.google.firebase.firestore.FirebaseFirestore

class ClientRepositoryFirestore : ClientRepository {

    override var clients = mutableListOf<Clients>().toMutableStateList()

    override fun getClients(clientID: String) {
        val docRef = FirebaseFirestore.getInstance()
            .collection(FirebaseConstants.CLIENTS)

        docRef.whereEqualTo(FirebaseConstants.ZONETAG, clientID)
            .get().addOnSuccessListener { documents ->

                clients.addAll(documents.toObjects(Clients::class.java).toMutableStateList())

                logClients("getClients")
                for (document in documents) {
                    Log.d(
                        FirebaseConstants.FIREBASETAG,
                        "Number of documents => ${documents.size()}"
                    )
                    Log.d(
                        FirebaseConstants.FIREBASETAG,
                        "${document.id} => ${document.data}"
                    )
                }
            }.addOnFailureListener { exception ->
                Log.w(FirebaseConstants.FIREBASETAG, "Error getting documents: ", exception)
            }
    }

    override fun getClients() {
        val docRef = FirebaseFirestore.getInstance()
        docRef.collection(FirebaseConstants.CLIENTS)
            .get().addOnSuccessListener { documents ->
                clients = documents.toObjects(Clients::class.java).toMutableStateList()

                logClients("getClientMember")
                for (document in documents) {
                    Log.d(
                        FirebaseConstants.FIREBASETAG,
                        "Number of documents => ${documents.size()}"
                    )
                    Log.d(
                        FirebaseConstants.FIREBASETAG,
                        "${document.id} => ${document.data}"
                    )
                }
            }
            .addOnFailureListener { exception ->
                Log.w(
                    FirebaseConstants.FIREBASETAG,
                    "Error getting documents: ",
                    exception
                )
            }
    }


    override fun addListener() {
        FirebaseFirestore.getInstance().collection(FirebaseConstants.CLIENTS)
            .addSnapshotListener { snapshot, e ->
                if (e != null) {
                    Log.w(FirebaseConstants.FIREBASETAG, "Listen failed.", e)
                    //return@addSnapshotListener
                }
                if (snapshot != null) {
                    clients = snapshot.toObjects(Clients::class.java).toMutableStateList()
                    logClients("Initial read")

                } else {
                    Log.d(FirebaseConstants.FIREBASETAG, "Current data: null")
                }
            }
    }

    private fun logClients(comment: String) {
        for (clientMember in clients) {

            Log.d(FirebaseConstants.FIREBASETAG, "$comment: $clientMember")
        }
    }
}
