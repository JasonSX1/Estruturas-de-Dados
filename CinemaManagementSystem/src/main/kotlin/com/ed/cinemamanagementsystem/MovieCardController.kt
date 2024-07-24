package com.ed.cinemamanagementsystem

import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.Spinner
import javafx.scene.control.SpinnerValueFactory
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import java.io.File
import java.time.format.DateTimeFormatter

class MovieCardController {

    @FXML
    private lateinit var card_3d: Label

    @FXML
    private lateinit var card_time: Label

    @FXML
    private lateinit var card_date: Label

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

    @FXML
    private lateinit var card_addBtn: Button

    private lateinit var mainFormController: MainFormController
    private lateinit var currentSession: Session

    fun setData(session: Session, mainFormController: MainFormController) {
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

        when (session.movie?.audio) {
            "Original" -> {
                card_language.text = "Orig."
                card_language.isVisible = true
            }
            "Original com Legenda" -> {
                card_language.text = "Leg."
                card_language.isVisible = true
            }
            "Dublado" -> {
                card_language.text = "Dub."
                card_language.isVisible = true
            }
            else -> {
                card_language.isVisible = false
            }
        }

        val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")
        val formattedTime = session.startTime?.format(timeFormatter) ?: "N/A"
        card_time.text = formattedTime

        val dateFormatter = DateTimeFormatter.ofPattern("dd/MM")
        val formattedDate = session.startTime?.format(dateFormatter) ?: "N/A"
        card_date.text = formattedDate

        val hasHalf = session.movie?.hasHalf == "Sim"
        card_half.text = if (hasHalf) "Meia" else ""
        card_half.isVisible = hasHalf

        // Configura o spinner para a capacidade da sessão
        card_spinner.valueFactory = SpinnerValueFactory.IntegerSpinnerValueFactory(1, session.sessionCapacity, 1)

        this.currentSession = session
        this.mainFormController = mainFormController

        // Configurar o spinner para a capacidade da sessão
        card_spinner.valueFactory = SpinnerValueFactory.IntegerSpinnerValueFactory(1, session.sessionDisponibility, 1)

        // Configurar ação do botão de adicionar
        card_addBtn.setOnAction {
            handleAddTickets()
        }
    }

    @FXML
    private fun handleAddTickets() {
        val numberOfTickets = card_spinner.value
        mainFormController.showSeatSelectionPopup(currentSession, numberOfTickets)
    }
}
