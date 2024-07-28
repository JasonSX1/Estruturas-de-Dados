package com.ed.cinemamanagementsystem

data class Order(val orderId: Int, val tickets: List<Ticket>)

val orderList: MutableList<Order> = mutableListOf()
var nextOrderId = 1