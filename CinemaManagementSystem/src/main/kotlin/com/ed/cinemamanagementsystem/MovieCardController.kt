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
    private lateinit var card_h2: Label

    @FXML
    private lateinit var card_h3: Label

    @FXML
    private lateinit var card_h4: Label

    @FXML
    private lateinit var card_h5: Label

    @FXML
    private lateinit var card_h6: Label

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
        card_name.text = session.movie?.title ?: "No Movie"
        card_sessionName.text = "Sessão ${session.id}"
        card_3d.text = if (session.movie?.has3d == "Sim") "3D" else "2D"
        card_language.text = session.movie?.audio ?: "N/A"
        card_half.text = session.movie?.hasHalf ?: "N/A"

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

        // Preencha os slots dos horários
        val slots = listOf(card_h1, card_h2, card_h3, card_h4, card_h5, card_h6)
        slots.forEachIndexed { index, label ->
            label.text = if (index < session.cols) session.startTime?.toString() ?: "N/A" else "N/A"
        }

        // Configura o spinner para a capacidade da sessão
        card_spinner.valueFactory = SpinnerValueFactory.IntegerSpinnerValueFactory(1, session.sessionCapacity, 1)
    }}