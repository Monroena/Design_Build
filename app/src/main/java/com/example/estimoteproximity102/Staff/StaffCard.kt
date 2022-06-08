package com.example.estimoteproximity102

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.estimoteproximity102.Staff.Staff

@Composable
fun StaffCard(
    staff: Staff,
    modifier: Modifier = Modifier
) {
    Row(modifier = Modifier.padding(all = 8.dp)) {
       /* Image(
            painter = painterResource((R.drawable.dtu_logo)),
            contentDescription = "Staff profile picture",
            modifier = Modifier
                .size(50.dp)
        )

        */
        Spacer(modifier = Modifier.width(8.dp))
        Column() {
            Text(
                text = staff?.name ?: ""
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = staff?.profession ?: "",
                modifier = Modifier.padding(all = 4.dp),
                style = MaterialTheme.typography.body2
            )
        }
    }
}