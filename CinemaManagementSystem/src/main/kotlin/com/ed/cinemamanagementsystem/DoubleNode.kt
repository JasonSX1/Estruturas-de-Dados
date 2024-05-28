package com.ed.cinemamanagementsystem

data class DoubleNode(var movie: Movie) {
    var previous: DoubleNode? = null
    var next: DoubleNode? = null
}
