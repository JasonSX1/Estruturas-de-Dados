package com.ed.cinemamanagementsystem

data class Movie(
    val id: Int,
    val title: String,
    val duration: Int,
    val productionType: String,
    val hasHalf: Boolean,
    val price: Double,
    val audio: String,
    val has3d: Boolean,
    val imagePath: String
)