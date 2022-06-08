package com.example.estimoteproximity102.StaffService

import androidx.compose.runtime.toMutableStateList
import com.example.estimoteproximity102.Staff.Staff
import com.example.estimoteproximity102.Staff.StaffData

class StaffRepositoryMock: StaffRepository {
    var staff = mutableListOf<Staff>().toMutableStateList()

    override fun getStaff() {
        staff = StaffData.staffSample.toMutableStateList()
    }
}

