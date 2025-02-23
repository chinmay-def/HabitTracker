package com.example.habittracker.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.habittracker.data.Habit
import com.example.habittracker.data.HabitRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HabitViewModel(private val repository: HabitRepository) : ViewModel() {

    val allHabits: StateFlow<List<Habit>> = repository.allHabits.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = emptyList()
    )

    fun addHabit(name: String, frequency: String) {
        viewModelScope.launch {
            val newHabit = Habit(
                name = name, frequency = frequency, isCompleted = false
            )
            repository.insertHabit(newHabit)
        }
    }

    fun deleteHabit(habit: Habit) {
        viewModelScope.launch {
            repository.deleteHabit(habit)
        }
    }

    fun updateHabit(habit: Habit) {
        viewModelScope.launch {
            repository.updateHabit(habit)
        }
    }
    fun toggleHabitCompletion(habit: Habit) { // ✅ Toggle completion
        viewModelScope.launch {
            val updatedHabit = habit.copy(isCompleted = !habit.isCompleted)
            repository.updateHabit(updatedHabit)
        }
    }
}