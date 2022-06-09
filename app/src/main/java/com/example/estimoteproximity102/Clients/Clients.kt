package com.example.estimoteproximity102.Clients

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.google.type.DateTime
import java.sql.Time
import java.sql.Timestamp

data class Clients (
    var name: String? = null,
    var adress: String? =null,
    var beaconID: String?=null,
    var birthday: DateTime?=null,
    var zipCode: String?=null,

)
