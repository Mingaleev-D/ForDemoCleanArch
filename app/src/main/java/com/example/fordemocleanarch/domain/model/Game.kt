package com.example.fordemocleanarch.domain.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    indices = [Index(value = ["id"], unique = true)]
)
data class Game(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val platform: String,
    val releaseDate: String,
    val thumbnail: String,
    val title: String
)
