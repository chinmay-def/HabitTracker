package com.example.habittracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

import com.example.habittracker.ViewModel.HabitViewModel
import com.example.habittracker.data.HabitDatabase
import com.example.habittracker.data.HabitRepository
import com.example.habittracker.ui.theme.AddHabitScreen
import com.example.habittracker.ui.theme.HabitScreen
import com.example.habittracker.ui.theme.HabitTrackerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val db = HabitDatabase.getDatabase(this)
        val repository = HabitRepository(db.habitDao())
        val viewModel = HabitViewModel(repository)



        setContent {
            var showAddScreen by remember { mutableStateOf(false) }
            HabitTrackerTheme {
                if (showAddScreen) {
                    AddHabitScreen(viewModel, onHabitAdded = { showAddScreen = false })
                } else {
                    HabitScreen(viewModel, onAddHabit = { showAddScreen = true })
                }
            }
        }
    }
}