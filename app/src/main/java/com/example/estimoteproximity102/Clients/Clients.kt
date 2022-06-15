package com.example.estimoteproximity102.Clients

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.google.firebase.Timestamp
import com.google.firebase.firestore.ServerTimestamp

import java.util.*

data class Clients (
    var name: String? = null,
    var address: String? =null,
    var beaconID: String?=null,
    var zipCode: String?=null,
    var beaconTag: String?=null,
    //@ServerTimestamp
    var birthdate: Date?=null,

)
