package com.ed.cinemamanagementsystem

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.image.Image
import javafx.stage.Stage

class HelloApplication : Application() {
    override fun start(stage: Stage) {
        val fxmlLoader = FXMLLoader(HelloApplication::class.java.getResource("LoginAndSignup.fxml"))
        val root: Parent = fxmlLoader.load()

        val scene = Scene(root, 600.0, 400.0) 
        stage.title = "Software de gerenciamento de sessões de cinema"
        stage.scene = scene
        stage.minWidth = 600.0
        stage.minHeight = 450.0
        val icon = Image(HelloApplication::class.java.getResourceAsStream("/Values/Pngs/Icons/cineicon4.png"))
        stage.icons.add(icon)

        stage.show()
    }
}

fun main() {
    Application.launch(HelloApplication::class.java)
}