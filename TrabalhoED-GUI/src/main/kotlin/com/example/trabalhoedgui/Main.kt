package com.example.trabalhoedgui

import Controller.LoginController
import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage

class Main : Application() {

    override fun start(primaryStage: Stage) {
        val loader = FXMLLoader(javaClass.getResource("SeuArquivoFXML.fxml"))
        val root: Parent = loader.load()
        val controller = loader.getController<LoginController>()

        primaryStage.title = "Sua Aplicação"
        primaryStage.scene = Scene(root, 240.0, 426.0)
        primaryStage.show()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            launch(Main::class.java)
        }
    }
}
