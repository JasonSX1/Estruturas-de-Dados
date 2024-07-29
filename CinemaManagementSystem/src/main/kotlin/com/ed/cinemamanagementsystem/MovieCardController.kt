package com.ed.cinemamanagementsystem

import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.Spinner
import javafx.scene.control.SpinnerValueFactory
import javafx.scene.effect.GaussianBlur
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.AnchorPane
import javafx.scene.layout.VBox
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

    @FXML
    private lateinit var card_percent: Label

    @FXML
    private lateinit var card_pane: VBox

    @FXML
    private lateinit var card_statusLabel: Label

    private lateinit var mainFormController: MainFormController
    private lateinit var currentSession: Session

    fun setData(session: Session, mainFormController: MainFormController) {
        // Verifica se a sessão não é nula
        if (session == null) {
            println("Sessão é nula.")
            return
        }

        card_name.text = session.movie?.title ?: "Sessão sem filme"
        card_sessionName.text = "Sessão ${session.id}"
        card_3d.text = if (session.movie?.has3d == "Sim") "3D" else "2D"

        val soldTickets = session.sessionCapacity - session.sessionDisponibility
        val percentageSold = if (session.sessionCapacity > 0) {
            (soldTickets.toDouble() / session.sessionCapacity * 100)
        } else {
            0.0
        }
        card_percent.text = String.format("%.2f%%", percentageSold)

        // Verifica se a disponibilidade é zero e altera o status da sessão
        if (session.sessionDisponibility <= 0) {
            session.status = SessionStatus.CLOSED
        }

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

        // Configura o áudio
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

        // Formata tempo e data
        val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")
        card_time.text = session.startTime?.format(timeFormatter) ?: "N/A"

        val dateFormatter = DateTimeFormatter.ofPattern("dd/MM")
        card_date.text = session.startTime?.format(dateFormatter) ?: "N/A"

        // Verifica se a sessão tem meia entrada
        val hasHalf = session.movie?.hasHalf == "Sim"
        card_half.text = if (hasHalf) "Meia" else ""
        card_half.isVisible = hasHalf

        // Configura o spinner para a capacidade da sessão
        val maxCapacity = session.sessionDisponibility

        this.currentSession = session
        this.mainFormController = mainFormController

        if (maxCapacity > 0) {
            card_spinner.valueFactory = SpinnerValueFactory.IntegerSpinnerValueFactory(1, maxCapacity, 1)
        } else {
            card_spinner.isDisable = true // Desabilita o spinner se não houver disponibilidade
        }

        // Desabilita o botão e o spinner se a disponibilidade for zero
        val isAvailable = session.sessionDisponibility > 0
        card_addBtn.isDisable = !isAvailable
        card_spinner.isDisable = !isAvailable

        // Configurar ação do botão de adicionar
        card_addBtn.setOnAction {
            if (isAvailable) {
                handleAddTickets()
            }
        }

        // Aplicar o efeito GaussianBlur e exibir o status
        applyStatusEffect(session.status)
    }


    private fun applyStatusEffect(status: SessionStatus) {
        if (status != SessionStatus.SALE) {
            // Aplica o efeito GaussianBlur ao card
            val blur = GaussianBlur(10.0)
            card_pane.effect = blur // Supondo que card_pane é o seu contêiner principal do card

            // Exibe o label de status
            card_statusLabel.text = when (status) {
                SessionStatus.WAITING -> "Sessão em espera"
                SessionStatus.SALE -> "Sessão à venda"
                SessionStatus.CANCELLED -> "Sessão cancelada"
                SessionStatus.CLOSED -> "Sessão fechada"
                SessionStatus.CLEANING -> "Sessão em limpeza"
                SessionStatus.EXHIBITION -> "Sessão em exibição"
            }
            card_statusLabel.isVisible = true
        } else {
            // Remove o efeito e oculta o label se o status for SALE
            card_pane.effect = null
            card_statusLabel.isVisible = false
        }
    }

    @FXML
    private fun handleAddTickets() {
        val numberOfTickets = card_spinner.value
        mainFormController.showSeatSelectionPopup(currentSession, numberOfTickets)
    }
}
