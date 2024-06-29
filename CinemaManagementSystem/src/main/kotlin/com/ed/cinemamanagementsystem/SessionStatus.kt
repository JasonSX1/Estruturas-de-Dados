package com.ed.cinemamanagementsystem

enum class SessionStatus(val status: String) {
    WAITING("Espera"),
    SALE("Venda"),
    CANCELLED("Cancelada"),
    CLOSED("Fechada"),
    CLEANING("Limpeza"),
    EXHIBITION("Em exibição");

    override fun toString(): String {
        return status
    }
}