package com.ed.cinemamanagementsystem

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage

class HelloApplication : Application() {
    override fun start(stage: Stage) {
        val fxmlLoader = FXMLLoader(HelloApplication::class.java.getResource("LoginAndSignup.fxml"))
        val root: Parent = fxmlLoader.load()

        val scene = Scene(root, 600.0, 400.0)
        stage.title = "teste!"
        stage.scene = scene
        stage.show()
    }
}

fun main() {
    Application.launch(HelloApplication::class.java)
}
