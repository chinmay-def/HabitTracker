package com.example.habittracker.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.habittracker.ViewModel.HabitViewModel

@Composable
fun AddHabitScreen(viewModel: HabitViewModel, onHabitAdded: () -> Unit) {
    var habitName by remember { mutableStateOf("") }
    var selectedFrequency by remember { mutableStateOf("Daily") }

    val frequencies = listOf("Daily", "Weekly")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Add Habit", fontSize = 24.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = habitName,
            onValueChange = { habitName = it },
            label = { Text("Habit Name") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Frequency Dropdown
        var expanded by remember { mutableStateOf(false) }
        Box {
            Button(onClick = { expanded = true }) {
                Text(selectedFrequency)
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                frequencies.forEach { frequency ->
                    DropdownMenuItem(
                        text = { Text(frequency) },
                        onClick = {
                            selectedFrequency = frequency
                            expanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (habitName.isNotBlank()) {
                    viewModel.addHabit(habitName, selectedFrequency)
                    onHabitAdded() // Navigate back
                }
            }
        ) {
            Text("Save Habit")
        }
    }
}
