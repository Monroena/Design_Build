package com.example.estimoteproximity102.StaffService

import android.util.Log
import androidx.compose.runtime.toMutableStateList
import com.example.estimoteproximity102.Staff.Staff
import com.example.estimoteproximity102.core.Constants
import com.google.firebase.firestore.FirebaseFirestore
//import com.google.flatbuffers.Constants

class StaffRepositoryFirestore: StaffRepository {
    var staff = mutableListOf<Staff>().toMutableStateList()

    override fun getStaff() {
        FirebaseFirestore.getInstance().collection(Constants.STAFF)
            .addSnapshotListener { snapshot, e ->
                if (e != null) {
                    Log.w(Constants.FIREBASETAG, "Listen failed.", e)
                    //return@addSnapshotListener
                }

                if (snapshot != null) {
                    staff = snapshot.toObjects(Staff::class.java).toMutableStateList()
                    Log.d(Constants.FIREBASETAG, "Current data size: ${staff.size}")
                    logStaff()

                } else {
                    Log.d(Constants.FIREBASETAG, "Current data: null")
                }
            }
    }

    fun logStaff(){
        for(staffMember in staff) {
            Log.d(Constants.FIREBASETAG, "Member: $staffMember")
        }
    }
}