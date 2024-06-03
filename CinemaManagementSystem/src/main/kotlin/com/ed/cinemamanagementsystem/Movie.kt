package com.ed.cinemamanagementsystem

data class Movie(
    val id: Int,
    val title: String,
    val duration: Int,
    val productionType: String,
    val hasHalf: String,
    val price: Double,
    val audio: String,
    val has3d: String,
    val imagePath: String
)