package com.example.trabalhoedgui

import javafx.fxml.FXML
import javafx.scene.control.Label

class Controller {
    @FXML
    private lateinit var welcomeText: Label

    @FXML
    private fun onHelloButtonClick() {
        welcomeText.text = "Welcome to JavaFX Application!"
    }
}