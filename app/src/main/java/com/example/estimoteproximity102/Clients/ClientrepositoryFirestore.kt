/*package com.example.estimoteproximity102.Clients

import android.util.Log
import androidx.compose.runtime.toMutableStateList
import com.example.estimoteproximity102.Staff.Staff
import com.example.estimoteproximity102.StaffService.StaffRepository
import com.google.firebase.firestore.FirebaseFirestore

class ClientRepositoryFirestore: ClientRepository {
    var Clients = mutableListOf<Staff>().toMutableStateList()

    override fun getClients() {
        FirebaseFirestore.getInstance().collection(dtu.engtech.iabr.stateincompose.core.Constants.STAFF)
            .addSnapshotListener { snapshot, e ->
                if (e != null) {
                    Log.w(dtu.engtech.iabr.stateincompose.core.Constants.FIREBASETAG, "Listen failed.", e)
                    //return@addSnapshotListener
                }

                if (snapshot != null) {
                    Clients = snapshot.toObjects(Staff::class.java).toMutableStateList()
                    Log.d(dtu.engtech.iabr.stateincompose.core.Constants.FIREBASETAG, "Current data size: ${staff.size}")
                    logStaff()

                } else {
                    Log.d(dtu.engtech.iabr.stateincompose.core.Constants.FIREBASETAG, "Current data: null")
                }
            }
    }

    fun logStaff(){
        for(staffMember in Clients) {
            Log.d(dtu.engtech.iabr.stateincompose.core.Constants.FIREBASETAG, "Member: $staffMember")
        }
    }

    override fun getClients() {
        TODO("Not yet implemented")
    }
}


 */