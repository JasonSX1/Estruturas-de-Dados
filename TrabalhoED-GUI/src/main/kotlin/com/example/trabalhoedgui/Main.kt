package com.example.trabalhoedgui

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.stage.Stage

class Main : Application() {
    override fun start(stage: Stage) {
        val fxmlLoader = FXMLLoader(Main::class.java.getResource("Cinema app.fxml"))
        val scene = Scene(fxmlLoader.load())
        stage.title = "Cinema App"
        stage.scene = scene
        stage.show()
    }
}

fun main() {
    Application.launch(Main::class.java)
}