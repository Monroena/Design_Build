package com.example.estimoteproximity102.Clients

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

import java.util.*

data class Clients (
    var name: String? = null,
    var address: String? =null,
    var beaconID: String?=null,
    var birthday: Date?=null,
    var zipCode: String?=null,

)
