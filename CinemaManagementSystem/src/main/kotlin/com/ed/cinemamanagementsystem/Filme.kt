package com.ed.cinemamanagementsystem

data class Filme(
    val id: Int,
    val titulo: String,
    val duracao: Int,
    val tipo: String,
    val meiaEntrada: Boolean,
    val preco: Double,
    val tipoAudio: String,
    val possui3D: Boolean
)
