package com.ed.cinemamanagementsystem

import java.sql.Connection
import java.sql.DriverManager

object Database {

    fun connectDB(): Connection? {
        return try {
            Class.forName("com.mysql.jdbc.Driver")
            val connect: Connection = DriverManager.getConnection("jdbc:mysql://localhost/cinema", "root", "") // LINK YOUR DATABASE
            connect
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
