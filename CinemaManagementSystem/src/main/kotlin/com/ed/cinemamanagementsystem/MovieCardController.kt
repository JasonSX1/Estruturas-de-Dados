package com.ed.cinemamanagementsystem

import javafx.fxml.FXML
import javafx.scene.control.Label
import javafx.scene.control.Spinner
import javafx.scene.control.SpinnerValueFactory
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import java.io.File

class MovieCardController {

    @FXML
    private lateinit var card_3d: Label

    @FXML
    private lateinit var card_h1: Label

    @FXML
    private lateinit var card_language: Label

    @FXML
    private lateinit var card_half: Label

    @FXML
    private lateinit var card_name: Label

    @FXML
    private lateinit var card_sessionName: Label

    @FXML
    private lateinit var card_spinner: Spinner<Int>

    @FXML
    private lateinit var home_img: ImageView

    fun setData(session: Session) {
        card_name.text = session.movie?.title ?: "Sessão sem filme"
        card_sessionName.text = "Sessão ${session.id}"
        card_3d.text = if (session.movie?.has3d == "Sim") "3D" else "2D"

        val imagePath = session.movie?.imagePath
        if (imagePath != null && imagePath.isNotEmpty()) {
            try {
                val file = File(imagePath)
                if (file.exists()) {
                    val image = Image(file.toURI().toString())
                    home_img.image = image
                } else {
                    println("Imagem não encontrada: $imagePath")
                    home_img.image = Image(javaClass.getResource("/path/to/default/image.png").toExternalForm())
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        if (session.movie?.audio != null){
            card_language.text = session.movie?.audio ?: "N/A"
        } else {
            card_language.isVisible = false
        }

        if (session.movie?.hasHalf != null){
            card_language.text = session.movie?.audio ?: "N/A"
        } else {
            card_language.isVisible = false
        }

        card_h1.text = session.startTime?.toString() ?: "N/A"

        if (session.startTime != null){
            card_language.text = session.movie?.audio ?: "N/A"
        } else {
            card_language.isVisible = false
        }

        // Configura o spinner para a capacidade da sessão
        card_spinner.valueFactory = SpinnerValueFactory.IntegerSpinnerValueFactory(1, session.sessionCapacity, 1)
    }
}
