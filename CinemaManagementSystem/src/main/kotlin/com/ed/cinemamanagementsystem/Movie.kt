package com.ed.cinemamanagementsystem

data class Movie(
    val id: Int,
    val title: String,
    val duration: Int,
    val productionType: String,
    val halfPrice: Boolean,
    val price: Double,
    val typeAud: String,
    val has3d: Boolean,
    val image: String
)