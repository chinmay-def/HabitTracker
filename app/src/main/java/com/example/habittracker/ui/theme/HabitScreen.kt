package com.example.habittracker.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Checkbox
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.habittracker.ViewModel.HabitViewModel
import com.example.habittracker.data.Habit


@Composable
fun HabitScreen(viewModel: HabitViewModel,onAddHabit: () -> Unit) {
    val habits by viewModel.allHabits.collectAsState()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(text = "My Habits", fontSize = 24.sp, fontWeight = FontWeight.Bold)

        LazyColumn {
            items(habits) { habit ->
                HabitItem(habit, onDelete = { viewModel.deleteHabit(habit)},   onToggleComplete = { viewModel.toggleHabitCompletion(it) },)
            }
        }

        FloatingActionButton(onClick = onAddHabit // Temporary test data
        ) {
            Icon(Icons.Default.Add, contentDescription = "Add Habit")
        }
    }
}

@Composable
fun HabitItem(habit: Habit, onDelete: () -> Unit,onToggleComplete: (Habit) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color.LightGray, RoundedCornerShape(8.dp))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = habit.isCompleted,
            onCheckedChange = { onToggleComplete(habit) } // ✅ Toggle when clicked
        )
        Text(text = habit.name, modifier = Modifier.weight(1f))
        IconButton(onClick = onDelete) {
            Icon(Icons.Default.Delete, contentDescription = "Delete Habit")
        }
    }
}
