package com.example.estimoteproximity102

import android.util.Log
import androidx.compose.runtime.toMutableStateList
import com.google.firebase.firestore.FirebaseFirestore
//import com.google.flatbuffers.Constants
import dtu.engtech.iabr.stateincompose.core.Constants

class StaffRepositoryFirestore: StaffRepository {
    var staff = mutableListOf<Staff>().toMutableStateList()

    override fun getStaff() {
        FirebaseFirestore.getInstance().collection(dtu.engtech.iabr.stateincompose.core.Constants.STAFF)
            .addSnapshotListener { snapshot, e ->
                if (e != null) {
                    Log.w(dtu.engtech.iabr.stateincompose.core.Constants.FIREBASETAG, "Listen failed.", e)
                    //return@addSnapshotListener
                }

                if (snapshot != null) {
                    staff = snapshot.toObjects(Staff::class.java).toMutableStateList()
                    Log.d(dtu.engtech.iabr.stateincompose.core.Constants.FIREBASETAG, "Current data size: ${staff.size}")
                    logStaff()

                } else {
                    Log.d(dtu.engtech.iabr.stateincompose.core.Constants.FIREBASETAG, "Current data: null")
                }
            }
    }

    fun logStaff(){
        for(staffMember in staff) {
            Log.d(dtu.engtech.iabr.stateincompose.core.Constants.FIREBASETAG, "Member: $staffMember")
        }
    }
}