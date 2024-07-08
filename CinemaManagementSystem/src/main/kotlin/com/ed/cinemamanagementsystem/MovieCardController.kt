package com.ed.cinemamanagementsystem

import javafx.fxml.FXML
import javafx.scene.control.Label
import javafx.scene.control.Spinner
import javafx.scene.control.SpinnerValueFactory

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

    private lateinit var sessionsData: List<Session>

    fun initialize(sessions: List<Session>) {
        sessionsData = sessions
        // Carregar dados na interface gráfica
        loadSessionsData()
    }

    private fun loadSessionsData() {
        // Exemplo: preenche os campos com os dados da primeira sessão
        if (sessionsData.isNotEmpty()) {
            val session = sessionsData[0] // Ajuste conforme necessário para múltiplas sessões
            card_name.text = session.movie?.title ?: "No Movie"
            card_sessionName.text = "Sessão ${session.id}"
            card_3d.text = if (session.movie?.has3d == "Sim") "3D" else "2D"
            card_language.text = session.movie?.audio ?: "N/A"
            card_half.text = session.movie?.hasHalf ?: "N/A"

            // Preencha os slots dos horários
            val slots = listOf(card_h1, card_h2, card_h3, card_h4, card_h5, card_h6)
            slots.forEachIndexed { index, label ->
                label.text = if (index < sessionsData.size) sessionsData[index].startTime?.toString() ?: "N/A" else "N/A"
            }

            // Configura o spinner para a capacidade da sessão
            card_spinner.valueFactory = SpinnerValueFactory.IntegerSpinnerValueFactory(1, session.sessionCapacity, 1)
        }
    }
}
