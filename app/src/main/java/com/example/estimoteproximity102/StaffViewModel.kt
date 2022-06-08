package com.example.estimoteproximity102

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel


class StaffViewModel : ViewModel() {
    //var staffRepository = StaffRepositoryMock()
    var staffRepository = StaffRepositoryFirestore()

    private var _staff = staffRepository.staff.toMutableStateList()
    val staff: List<Staff>
        get() = _staff

    init {
        staffRepository.getStaff()
    }

    fun addStaff(){
        staffRepository.getStaff();
        staffRepository.staff.add(Staff("Joe Tester", "V2.02"))
    }
}

