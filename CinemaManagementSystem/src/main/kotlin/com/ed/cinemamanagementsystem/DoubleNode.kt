package com.ed.cinemamanagementsystem

data class DoubleNode<T>(var data: T) {
    var previous: DoubleNode<T>? = null
    var next: DoubleNode<T>? = null
}