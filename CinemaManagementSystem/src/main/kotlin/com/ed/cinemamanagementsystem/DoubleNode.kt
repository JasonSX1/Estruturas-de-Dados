package com.ed.cinemamanagementsystem

import kotlinx.serialization.Serializable

@Serializable
data class DoubleNode(var movie: Movie) {
    var previous: DoubleNode? = null
    var next: DoubleNode? = null
}
