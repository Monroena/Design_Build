package com.example.estimoteproximity102

import androidx.compose.runtime.toMutableStateList
import com.example.estimoteproximity102.Staff.Staff

class StaffRepositoryMock: StaffRepository {
    var staff = mutableListOf<Staff>().toMutableStateList()

    override fun getStaff() {
        staff = StaffData.staffSample.toMutableStateList()
    }
}

