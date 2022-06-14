package com.example.estimoteproximity102

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.estimoteproximity102.StaffService.StaffViewModel

@Composable
fun StaffScreen(
    modifier: Modifier = Modifier,
    staffViewModel: StaffViewModel = viewModel(),
) {
    Column{
        Row{
            Text("${staffViewModel.staffRepository.staff.size}")
            Spacer(modifier = Modifier.width(45.dp))
            Button(onClick = { staffViewModel.addStaff() }) {
                Text(text = "Staff")
            }
        }
        StaffCardList(
            list = staffViewModel.staffRepository.staff
        )
    }
}