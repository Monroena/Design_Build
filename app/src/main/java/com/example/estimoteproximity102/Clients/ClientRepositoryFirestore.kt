package com.example.estimoteproximity102.model

import android.util.Log
import androidx.compose.runtime.toMutableStateList
import com.example.estimoteproximity102.Clients.Clients
import com.example.estimoteproximity102.Clients.ClientRepository
import com.example.estimoteproximity102.core.Constants
import com.google.firebase.firestore.FirebaseFirestore

class ClientRepositoryFirestore : ClientRepository {

    override var clients = mutableListOf<Clients>().toMutableStateList()

    override fun getClients(clientID: String) {
        val docRef = FirebaseFirestore.getInstance()
            .collection(Constants.CLIENTS)

        docRef.whereEqualTo(Constants.ZONETAG, clientID)
            .get().addOnSuccessListener { documents ->
                clients = documents.toObjects(Clients::class.java).toMutableStateList()

                logClients("getClients")
                for (document in documents) {
                    Log.d(
                        Constants.FIREBASETAG,
                        "Number of documents => ${documents.size()}"
                    )
                    Log.d(
                        Constants.FIREBASETAG,
                        "${document.id} => ${document.data}"
                    )

                }
            }.addOnFailureListener { exception ->
                Log.w(Constants.FIREBASETAG, "Error getting documents: ", exception)
            }


    }

    override fun getClients() {
        val docRef = FirebaseFirestore.getInstance()
        docRef.collection(Constants.CLIENTS)
            .get().addOnSuccessListener { documents ->
                clients = documents.toObjects(Clients::class.java).toMutableStateList()

                logClients("getClientMember")
                for (document in documents) {
                    Log.d(
                        Constants.FIREBASETAG,
                        "Number of documents => ${documents.size()}"
                    )
                    Log.d(
                        Constants.FIREBASETAG,
                        "${document.id} => ${document.data}"
                    )


                }
            }
            .addOnFailureListener { exception ->
                Log.w(
                    Constants.FIREBASETAG,
                    "Error getting documents: ",
                    exception
                )
            }
    }










    override fun addListener() {
        FirebaseFirestore.getInstance().collection(Constants.CLIENTS)
            .addSnapshotListener { snapshot, e ->
                if (e != null) {
                    Log.w(Constants.FIREBASETAG, "Listen failed.", e)
                    //return@addSnapshotListener
                }

                if (snapshot != null) {
                    clients = snapshot.toObjects(Clients::class.java).toMutableStateList()
                    logClients("Initial read")

                } else {
                    Log.d(Constants.FIREBASETAG, "Current data: null")
                }
            }
    }

    private fun logClients(comment: String) {
        for (clientMember in clients) {

            Log.d(Constants.FIREBASETAG, "$comment: $clientMember")
        }
    }
}
