package com.example.habittracker.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "habits")
data class Habit(
    @PrimaryKey(autoGenerate = true) val id :Int = 0,
    val name:String,
    val frequency :String,
    val isCompleted:Boolean,
    val createdAt:Long = System.currentTimeMillis()
    )