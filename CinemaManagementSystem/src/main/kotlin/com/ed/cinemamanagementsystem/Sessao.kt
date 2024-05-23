package com.ed.cinemamanagementsystem
import java.time.LocalDateTime

data class Sessao(
    val id: Int,
    val numeroSala: Int,
    val capacidade: Int,
    val filme: Filme,
    val horarioInicio: LocalDateTime
)