package com.ed.cinemamanagementsystem

import javafx.application.Platform
import javafx.beans.property.SimpleDoubleProperty
import javafx.beans.property.SimpleStringProperty
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.fxml.Initializable
import javafx.geometry.HPos
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.geometry.VPos
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.control.*
import javafx.scene.control.cell.PropertyValueFactory
import javafx.scene.image.ImageView
import javafx.scene.layout.*
import javafx.stage.FileChooser
import javafx.stage.Stage
import javafx.util.StringConverter
import java.awt.Image
import java.io.File
import java.net.URL
import java.sql.SQLException
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.util.*

class MainFormController : Initializable {

    @FXML
    private lateinit var DashboardForm: AnchorPane

    @FXML
    private lateinit var movies_btn: Button

    @FXML
    private lateinit var movies_alertIcon: Label

    @FXML
    private lateinit var MoviesForm: AnchorPane

    @FXML
    private lateinit var sessions_form: AnchorPane

    @FXML
    private lateinit var SessionsForm: AnchorPane

    @FXML
    private lateinit var dashboard_btn: Button

    @FXML
    private lateinit var home_btn: Button

    @FXML
    private lateinit var logout_btn: Button

    @FXML
    private lateinit var main_form: AnchorPane

    @FXML
    private lateinit var movies_addBtn: Button

    @FXML
    private lateinit var movies_tableView: TableView<Movie>

    @FXML
    private lateinit var movies_col_movieId: TableColumn<Movie, Int>

    @FXML
    private lateinit var movies_col_movieTitle: TableColumn<Movie, String>

    @FXML
    private lateinit var movies_col_movieDuration: TableColumn<Movie, Int>

    @FXML
    private lateinit var movies_col_productionType: TableColumn<Movie, String>

    @FXML
    private lateinit var movies_col_moviePrice: TableColumn<Movie, Double>

    @FXML
    private lateinit var movies_col_audType: TableColumn<Movie, String>

    @FXML
    private lateinit var movies_col_has3d: TableColumn<Movie, Boolean>

    @FXML
    private lateinit var movies_col_hasHalf: TableColumn<Movie, Boolean>

    @FXML
    private lateinit var movies_col_hasCover: TableColumn<Movie, String>

    @FXML
    private lateinit var movies_deleteBtn: Button

    @FXML
    private lateinit var movies_importBtn: Button

    @FXML
    private lateinit var movies_updateBtn: Button

    @FXML
    private lateinit var movies_randomizerBtn: Button

    @FXML
    private lateinit var orders_btn: Button

    @FXML
    private lateinit var sessions_btn: Button

    @FXML
    private lateinit var sessions_col_capacity: TableColumn<Session, Int>

    @FXML
    private lateinit var sessions_col_ocupation: TableColumn<Session, Int>

    @FXML
    private lateinit var sessions_col_currentMovie: TableColumn<Session, Movie>

    @FXML
    private lateinit var sessions_col_number: TableColumn<Session, String>

    @FXML
    private lateinit var sessions_col_occupation: TableColumn<Session, Double>

    @FXML
    private lateinit var sessions_col_sessionId: TableColumn<Session, Int>

    @FXML
    private lateinit var sessions_col_sessionStatus: TableColumn<Session, SessionStatus>

    @FXML
    private lateinit var sessions_tableView: TableView<Session>

    @FXML
    private lateinit var wishlist_btn: Button

    @FXML
    private lateinit var movies_hasHalf: ComboBox<String>

    @FXML
    private lateinit var movies_has3d: ComboBox<String>

    @FXML
    private lateinit var movies_audio: ComboBox<String>

    @FXML
    private lateinit var movies_typeProd: ComboBox<String>

    @FXML
    private lateinit var movies_movieId: TextField

    @FXML
    private lateinit var movies_title: TextField

    @FXML
    private lateinit var movies_duration: TextField

    @FXML
    private lateinit var movies_price: TextField

    @FXML
    private lateinit var usernameLabel: Label

    @FXML
    private lateinit var sessions_addBtn: Button

    @FXML
    private lateinit var sessions_closeSales: Button

    @FXML
    private lateinit var sessions_deleteBtn: Button

    @FXML
    private lateinit var sessions_id: TextField

    @FXML
    private lateinit var sessions_movie: ComboBox<Movie>

    @FXML
    private lateinit var sessions_roomNumber: TextField

    @FXML
    private lateinit var sessions_startSales: Button

    @FXML
    private lateinit var sessions_startTime: TextField

    @FXML
    private lateinit var sessions_updateBtn: Button

    @FXML
    private lateinit var movies_refreshBtn: Button

    @FXML
    private lateinit var sessions_statusLabel: Label

    @FXML
    private lateinit var movies_imageView: ImageView

    @FXML
    private lateinit var movies_imageLabel: Label

    @FXML
    private lateinit var movies_imageLabel2: Label

    @FXML
    private lateinit var sessions_programation: Button

    @FXML
    private lateinit var sessions_clearMoviesBox: Button

    @FXML
    private lateinit var sessions_datePicker: DatePicker

    @FXML
    private lateinit var sessions_cols: TextField

    @FXML
    private lateinit var sessions_rows: TextField

    @FXML
    private lateinit var home_scrollPane: ScrollPane

    @FXML
    private lateinit var SalesForm: AnchorPane
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @FXML
    private lateinit var home_cartTableView: TableView<Ticket>

    @FXML
    private lateinit var home_cartTableView_col_movie: TableColumn<Ticket, String>
    @FXML
    private lateinit var home_cartTableView_col_seat: TableColumn<Ticket, String>

    @FXML
    private lateinit var home_cartTableView_col_price: TableColumn<Ticket, Double>
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @FXML
    private lateinit var home_total: Label

    @FXML
    private lateinit var home_fullPrice: Label

    @FXML
    private lateinit var home_halfPrice: Label

    @FXML
    private lateinit var home_gridPane: GridPane

    @FXML
    private lateinit var home_payBtn: Button

    @FXML
    private lateinit var home_removeBtn: Button

    @FXML
    private lateinit var home_recibeBtn: Button

    private var ticketCount: Int = 0

    private var imagePath: String? = null

    private lateinit var image: Image

    private var originalMovie: Movie? = null

    private var originalSession: Session? = null

    private val movieList: ObservableList<Movie> = FXCollections.observableArrayList()

    private val sessionList: ObservableList<Session> = FXCollections.observableArrayList()

    private val comboList = arrayOf("Sim", "Não")

    private val productionTypeList = arrayOf("Nacional", "Estrangeira")

    private val audioTypeList = arrayOf("Original", "Original com Legenda", "Dublado")

    private val movieDAO: MovieDAO = DynamicMoviesList() // Seria uma boa pratica utilizar movie e sessionDAO como nomes das variaveis???

    private val sessionDAO: SessionDAO = DynamicSessionList()

    @FXML
    private lateinit var movieCardContainer: AnchorPane

    @FXML
    private lateinit var home_halfPriceCheckBox: CheckBox

    @FXML
    private lateinit var home_fullPriceCheckBox: CheckBox

    @FXML
    private lateinit var seatGridPane: GridPane

    @FXML
    private lateinit var seatSelecionGrid: GridPane

    @FXML
    private lateinit var ticketSpinner: Spinner<Int>

    private var currentSelectedSession: Session? = null

    private var numberOfTickets: Int = 0

    private fun showAlert(title: String, message: String, alertType: Alert.AlertType): Optional<ButtonType> {
        val alert = Alert(alertType)
        alert.title = title
        alert.headerText = null
        alert.contentText = message
        return alert.showAndWait()
    }

    private fun initializeComboBoxes() {
        val listData: ObservableList<String> = FXCollections.observableArrayList(*comboList)

        movies_hasHalf.items = listData
        movies_has3d.items = listData
    }

    private fun loadMoviesToSessions() {
        try {
            // Obter a lista de filmes a partir do DAO
            val moviesList = movieDAO.listMovies()

            // Atualizar o ComboBox na thread da UI
            Platform.runLater {
                sessions_movie.items.clear()
                sessions_movie.items.addAll(moviesList)
                setupSessionsMovieComboBox()
            }

        } catch (e: Exception) {
            showAlert("Mensagem de erro!", "Erro ao carregar os filmes: ${e.message}", Alert.AlertType.ERROR)
        }
    }

    private fun intializeProductionTypeList(){
        val listData: ObservableList<String> = FXCollections.observableArrayList(*productionTypeList)

        movies_typeProd.items = listData
    }

    private fun initializeAudioTypeList(){
        val listData: ObservableList<String> = FXCollections.observableArrayList(*audioTypeList)

        movies_audio.items = listData
    }

    private fun displayUsername() {
        val user = Data.username
        val formattedUser = user.substring(0, 1).uppercase() + user.substring(1)
        usernameLabel.text = formattedUser
    }

    fun logout() {
        try {
            val option = showAlert("Mensagem de confirmação", "Você realmente deseja sair?", Alert.AlertType.CONFIRMATION)
            if (option.isPresent && option.get() == ButtonType.OK) {
                // Fechar o estágio atual
                val currentStage = logout_btn.scene.window as Stage
                currentStage.close()

                // Carregar a tela de login e signup
                val loginLoader = FXMLLoader(javaClass.getResource("LoginAndSignup.fxml"))
                val root: Parent = loginLoader.load()
                val loginController = loginLoader.getController<LoginAndSignupController>()

                val stage = Stage()
                val scene = Scene(root)
                stage.title = "Software de gerenciamento de sessões de cinema"
                stage.scene = scene
                stage.show()
                stage.centerOnScreen()
            } else {
                // Não faz nada se o usuário cancelar ou se o valor não estiver presente
            }
        } catch (e: SQLException) {
            e.printStackTrace()
        }
    }

    fun addMovie() {
        try {
            val movieId = movies_movieId.text.toIntOrNull()
            val title = movies_title.text
            val duration = movies_duration.text.toIntOrNull()
            val price = movies_price.text.toDoubleOrNull()
            val productionType = movies_typeProd.value
            val hasHalf = movies_hasHalf.value
            val audio = movies_audio.value
            val has3d = movies_has3d.value

            if (movieId != null && title.isNotBlank() && duration != null && price != null && productionType != null && audio != null && has3d != null) {
                // Verificar se o ID já está cadastrado
                val existingMovie = movieDAO.searchMovieByID(movieId)
                if (existingMovie != null) {
                    showAlert("Erro", "ID do filme já está cadastrado!", Alert.AlertType.ERROR)
                    return
                }

                // Atribuir o caminho da imagem ou uma string vazia se nulo
                val movie = Movie(movieId, title, duration, productionType, hasHalf, price, audio, has3d, imagePath ?: "")
                val options = listOf("Início", "Fim", "Posição Personalizada")
                val dialog = ChoiceDialog(options[0], options)
                dialog.title = "Escolher posição"
                dialog.headerText = "Escolha onde adicionar o filme"
                dialog.contentText = "Posição:"

                val result = dialog.showAndWait()

                if (result.isPresent) {
                    val position = result.get()
                    var sucesso = false

                    when (position) {
                        "Início" -> {
                            movieDAO.addMovieStart(movie)
                            sucesso = true
                        }
                        "Fim" -> {
                            movieDAO.addMovieEnd(movie)
                            sucesso = true
                        }
                        "Posição Personalizada" -> {
                            val inputDialog = TextInputDialog()
                            inputDialog.title = "Posição Personalizada"
                            inputDialog.headerText = "Digite a posição onde deseja adicionar o filme"
                            inputDialog.contentText = "Posição:"

                            val posResult = inputDialog.showAndWait()

                            if (posResult.isPresent) {
                                val pos = posResult.get()
                                val posInt = pos.toIntOrNull()
                                if (posInt != null && posInt >= 0 && posInt <= movieDAO.qtdMovies()) {
                                    movieDAO.addMovieCustomP(movie, posInt)
                                    sucesso = true
                                } else {
                                    showAlert("Erro", "Posição inválida!", Alert.AlertType.ERROR)
                                }
                            }
                        }
                    }

                    if (sucesso) {
                        showAlert("Sucesso", "Filme adicionado com sucesso!", Alert.AlertType.INFORMATION)
                        loadMoviesToTableView()
                        clearMoviesForm()
                    }
                }
            } else {
                showAlert("Erro", "Por favor, preencha todos os campos corretamente!", Alert.AlertType.ERROR)
            }
        } catch (e: Exception) {
            showAlert("Erro", "Ocorreu um erro ao adicionar o filme: ${e.message}", Alert.AlertType.ERROR)
            e.printStackTrace()
        }
    }

    fun addSession() {
        try {
            val dialogResult = showAddSessionDialog()
            if (dialogResult == null) {
                showAlert("Erro", "Por favor, preencha todos os campos corretamente!", Alert.AlertType.ERROR)
                return
            }

            val (capacity, rows, cols) = dialogResult

            val sessionId = sessions_id.text.toIntOrNull()
            val roomNumber = sessions_roomNumber.text
            val disponibility = capacity
            val movie = sessions_movie.value

            if (sessionId != null && roomNumber.isNotBlank() && capacity > 0 && rows > 0 && cols > 0) {
                val existingSession = sessionDAO.searchSessionByID(sessionId)
                if (existingSession != null) {
                    showAlert("Erro", "ID da sessão já está cadastrado!", Alert.AlertType.ERROR)
                    return
                }

                val startDateTime = if (sessions_startTime.text.isNotBlank() && sessions_datePicker.value != null) {
                    val dateString = sessions_datePicker.value.toString()
                    val timeString = sessions_startTime.text
                    val dateTimeString = "$dateString $timeString"
                    try {
                        LocalDateTime.parse(dateTimeString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
                    } catch (e: DateTimeParseException) {
                        showAlert("Erro", "Data e hora de início inválida!", Alert.AlertType.ERROR)
                        return
                    }
                } else {
                    null
                }

                val calculatedCapacity = rows * cols
                if (calculatedCapacity != capacity) {
                    showAlert("Erro", "A capacidade calculada ($calculatedCapacity) não corresponde à capacidade definida ($capacity)!", Alert.AlertType.ERROR)
                    return
                }

                val session = Session(sessionId, roomNumber, capacity, disponibility, movie, startDateTime, SessionStatus.WAITING, rows, cols)
                val successful = sessionDAO.addSession(session)

                if (successful) {
                    showAlert("Sucesso", "Sessão adicionada com sucesso!", Alert.AlertType.INFORMATION)
                    loadSessionsToTableView()
                    clearSessionsForm()
                    println("$session")
                    updateHomeGridPane()
                } else {
                    showAlert("Erro", "Falha ao adicionar a sessão!", Alert.AlertType.ERROR)
                }
            } else {
                showAlert("Erro", "Por favor, preencha todos os campos corretamente!", Alert.AlertType.ERROR)
            }
        } catch (e: Exception) {
            showAlert("Erro", "Ocorreu um erro ao adicionar a sessão: ${e.message}", Alert.AlertType.ERROR)
            e.printStackTrace()
        }
    }
    fun showAddSessionDialog(): Triple<Int, Int, Int>? {
        val dialog = Dialog<Triple<Int, Int, Int>>()
        dialog.title = "Adicionar Sessão"
        dialog.headerText = "Preencha os detalhes da sessão"

        val capacityField = TextField()
        capacityField.promptText = "Capacidade"

        val rowsField = TextField()
        rowsField.promptText = "Linhas"

        val colsField = TextField()
        colsField.promptText = "Colunas"

        val previewGrid = GridPane()
        previewGrid.gridLinesVisibleProperty().set(true)

        // Atualizar colunas automaticamente ao inserir capacidade e linhas
        rowsField.textProperty().addListener { _, _, newValue ->
            val capacity = capacityField.text.toIntOrNull()
            val rows = newValue.toIntOrNull()
            if (capacity != null && rows != null && rows > 0) {
                val cols = capacity / rows
                colsField.text = cols.toString()
                updatePreviewGridForAdd(previewGrid, rows, cols)
            }
        }

        colsField.textProperty().addListener { _, _, newValue ->
            val capacity = capacityField.text.toIntOrNull()
            val cols = newValue.toIntOrNull()
            if (capacity != null && cols != null && cols > 0) {
                val rows = capacity / cols
                rowsField.text = rows.toString()
                updatePreviewGridForAdd(previewGrid, rows, cols)
            }
        }

        val grid = GridPane()
        grid.padding = Insets(10.0)
        grid.hgap = 10.0
        grid.vgap = 10.0

        grid.addRow(0, Label("Capacidade:"), capacityField)
        grid.addRow(1, Label("Linhas:"), rowsField)
        grid.addRow(2, Label("Colunas:"), colsField)
        grid.addRow(3, Label("Preview:"), previewGrid)

        val scrollPane = ScrollPane(previewGrid)
        scrollPane.isFitToWidth = true
        scrollPane.isFitToHeight = true
        scrollPane.prefViewportWidth = 300.0
        scrollPane.prefViewportHeight = 200.0

        grid.add(scrollPane, 0, 3, 2, 1)

        dialog.dialogPane.content = grid

        dialog.dialogPane.buttonTypes.addAll(ButtonType.OK, ButtonType.CANCEL)

        dialog.setResultConverter { dialogButton ->
            if (dialogButton == ButtonType.OK) {
                val capacity = capacityField.text.toIntOrNull()
                val rows = rowsField.text.toIntOrNull()
                val cols = colsField.text.toIntOrNull()
                if (capacity != null && rows != null && cols != null) {
                    return@setResultConverter Triple(capacity, rows, cols)
                }
            }
            null
        }

        val result = dialog.showAndWait()
        return result.orElse(null)
    }

    private fun updatePreviewGridForAdd(gridPane: GridPane, rows: Int, cols: Int) {
        gridPane.children.clear()
        for (i in 0 until rows) {
            for (j in 0 until cols) {
                val button = Button()
                button.text = "${('A' + i)}${j + 1}"
                button.style = "-fx-background-color: lightgray"
                button.minWidth = 40.0
                button.minHeight = 40.0

                GridPane.setConstraints(button, j, i)
                GridPane.setMargin(button, Insets(5.0))  // Adiciona uma margem de 5px ao redor dos botões
                gridPane.children.add(button)
            }
        }
    }

    private fun updatePreviewGrid(gridPane: GridPane, session: Session, rows: Int, cols: Int, numberOfTickets: Int) {
        gridPane.children.clear()
        val soldSeats = soldSeatsMap[session.id] ?: mutableListOf()
        for (i in 0 until rows) {
            for (j in 0 until cols) {
                val button = Button()
                button.text = "${('A' + i)}${j + 1}"
                button.style = "-fx-background-color: lightgray"
                button.minWidth = 40.0
                button.minHeight = 40.0

                val seatPosition = Pair(i, j)
                val isSeatTaken = soldSeats.contains(seatPosition) || ticketList.any { it.sessionId == session.id && it.seatRow == seatPosition.first && it.seatCol == seatPosition.second }
                if (isSeatTaken) {
                    button.isDisable = true
                    button.style = "-fx-background-color: red"
                }

                button.setOnAction {
                    if (selectedSeats.contains(seatPosition)) {
                        selectedSeats.remove(seatPosition)
                        button.style = "-fx-background-color: lightgray"
                    } else if (selectedSeats.size < numberOfTickets) {
                        selectedSeats.add(seatPosition)
                        button.style = "-fx-background-color: lightgreen"
                    }
                }

                GridPane.setConstraints(button, j, i)
                GridPane.setMargin(button, Insets(5.0))  // Adiciona uma margem de 5px ao redor dos botões
                gridPane.children.add(button)
            }
        }
    }

    fun removeMovie() {
        val movieId = movies_movieId.text.toIntOrNull()

        if (movieId != null) {
            val removedMovie = movieDAO.removeMovie(movieId)
            if (removedMovie != null) {
                showAlert("Sucesso", "Filme removido com sucesso!", Alert.AlertType.INFORMATION)
                clearMoviesForm()
            } else {
                showAlert("Erro", "Filme com ID $movieId não encontrado!", Alert.AlertType.ERROR)
            }
        } else {
            showAlert("Erro", "Por favor, insira um ID de filme válido!", Alert.AlertType.ERROR)
        }
        updateMoviesTableView()
    }

    fun removeSession() {
        val sessionId = sessions_id.text.toIntOrNull()

        if (sessionId != null){
            val removedSession = sessionDAO.removeSession(sessionId)
            if (removedSession != null) {
                showAlert("Sucesso", "Sessão removida com sucesso!", Alert.AlertType.INFORMATION)
                clearSessionsForm()
            } else {
                showAlert("Erro", "Sessão com ID $sessionId não encontrada!", Alert.AlertType.ERROR)
            }
        } else {
            showAlert("Erro", "Por favor, insira um ID de filme válido!", Alert.AlertType.ERROR)
        }
        updateSessionsTableView()
    }

    fun generateRandomData() {
        val quantidadeTemp = movieDAO.qtdMovies()
        val randomId = quantidadeTemp + 1
        val randomTitle = "Filme $randomId"
        val randomDuration = (90..180).random()
        val randomProductionType = if ((0..1).random() == 0) "Nacional" else "Estrangeira"
        val randomHasHalf = if ((0..1).random() == 0) "Não" else "Sim"
        val randomPrice = (10..50).random().toDouble()
        val randomAudio = when ((0..2).random()) {
            0 -> "Original"
            1 -> "Original com Legenda"
            else -> "Dublado"
        }
        val randomHas3d = if ((0..1).random() == 0) "Não" else "Sim"

        movies_movieId.text = randomId.toString()
        movies_title.text = randomTitle
        movies_duration.text = randomDuration.toString()
        movies_typeProd.value = randomProductionType
        movies_hasHalf.value = randomHasHalf
        movies_price.text = randomPrice.toString()
        movies_audio.value = randomAudio
        movies_has3d.value = randomHas3d
    }//Metodo de geração aleatorio durante a fase de testes

    fun updateMoviesTableView() {
        val movies = movieDAO.listMovies()
        movies_tableView.items = FXCollections.observableArrayList(movies)
    }

    private fun updateSessionsTableView(){
        val sessions = sessionDAO.listSessions()
        sessions_tableView.items = FXCollections.observableArrayList(sessions)
    }

    private fun updateHomeGridPane(){
        val sessions = sessionDAO.getAllSessions()
        homeDisplayCards()
    }

    private fun loadMoviesToTableView() {
        movies_tableView.items.clear()
        val moviesList = movieDAO.listMovies()
        val observableMovieList = FXCollections.observableList(moviesList)
        movies_tableView.items = observableMovieList
    }

    private fun loadSessionsToTableView(){
        sessions_tableView.items.clear()
        val sessionsList = sessionDAO.listSessions()
        val observableSessionList = FXCollections.observableArrayList(sessionsList)
        sessions_tableView.items = observableSessionList
    }

    private fun clearMoviesForm() {
        movies_movieId.clear()
        movies_title.clear()
        movies_duration.clear()
        movies_price.clear()
        movies_typeProd.value = null
        movies_hasHalf.value = null
        movies_audio.value = null
        movies_has3d.value = null
        movies_imageView.image = null
        movies_imageLabel.isVisible = true
        movies_imageLabel2.isVisible = false
        imagePath = null

        // Limpar o item selecionado na tabela de filmes
        movies_tableView.selectionModel.clearSelection()
    }

    private fun clearSessionsForm() {
        sessions_id.clear()
        sessions_roomNumber.clear()
        sessions_movie.value = null
        sessions_startTime.clear()
        sessions_datePicker.value = null

        // Limpar o item selecionado na tabela de sessões
        sessions_tableView.selectionModel.clearSelection()
    }

    fun switchMenu(event: javafx.event.ActionEvent) {
        DashboardForm.isVisible = false
        sessions_form.isVisible = false
        MoviesForm.isVisible = false
        SalesForm.isVisible = false

        when (event.source) {
            dashboard_btn -> {
                DashboardForm.isVisible = true
            }
            sessions_btn -> {
                sessions_form.isVisible = true
                loadSessionsToTableView()
                clearSessionsForm()
                updateSessionsTableView()
            }
            movies_btn -> {
                MoviesForm.isVisible = true
            }
            home_btn -> {
                SalesForm.isVisible = true
                homeDisplayCards()
            }
        }

        clearMoviesForm()
        clearSessionsForm()
    }

    fun updateMovie() {
        val movieId = movies_movieId.text.toIntOrNull()

        if (movieId == null) {
            showAlert("Erro", "ID do filme inválido!", Alert.AlertType.ERROR)
            return
        }

        val updatedMovie = Movie(
            movieId,
            movies_title.text,
            movies_duration.text.toIntOrNull() ?: 0,
            movies_typeProd.value ?: "",
            movies_hasHalf.value ?: "",
            movies_price.text.toDoubleOrNull() ?: 0.0,
            movies_audio.value ?: "",
            movies_has3d.value ?: "",
            imagePath ?: originalMovie?.imagePath ?: "" // Atualiza o imagePath
        )

        val changes = getMovieChanges(updatedMovie)
        if (changes.isEmpty()) {
            showAlert("Sem alterações", "Nenhuma alteração foi feita.", Alert.AlertType.INFORMATION)
            return
        }

        val confirmation = showConfirmationDialog(changes)
        if (confirmation) {
            movieDAO.updateMovie(movieId, updatedMovie)
            showAlert("Sucesso", "Filme atualizado com sucesso!", Alert.AlertType.INFORMATION)
            loadMoviesToTableView()
            clearMoviesForm()
        }
    }

    fun updateSession() {
        try {
            val sessionId = sessions_id.text.toIntOrNull()
            val roomNumber = sessions_roomNumber.text
            val movie = sessions_movie.value

            if (sessionId != null && roomNumber.isNotBlank()) {
                val dateString = sessions_datePicker.value.toString() // Pega a data do DatePicker
                val timeString = sessions_startTime.text // Pega o tempo do campo de texto

                val dateTimeString = "$dateString $timeString" // Concatena data e hora
                val startDateTime = try {
                    LocalDateTime.parse(dateTimeString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
                } catch (e: DateTimeParseException) {
                    showAlert("Erro", "Data e hora de início inválida!", Alert.AlertType.ERROR)
                    return
                }

                val currentSession = sessionDAO.searchSessionByID(sessionId)
                if (currentSession == null) {
                    showAlert("Erro", "Sessão não encontrada!", Alert.AlertType.ERROR)
                    return
                }

                val updatedSession = Session(sessionId, roomNumber, currentSession.sessionCapacity, currentSession.sessionDisponibility, movie, startDateTime, getSessionStatusFromString(sessions_statusLabel.text), currentSession.rows, currentSession.cols)

                val changes = getSessionChanges(updatedSession)
                if (changes.isEmpty()) {
                    showAlert("Sem alterações", "Nenhuma alteração foi feita.", Alert.AlertType.INFORMATION)
                    return
                }

                val confirmation = showConfirmationDialog(changes)
                if (confirmation) {
                    sessionDAO.updateSession(sessionId, updatedSession)
                    showAlert("Sucesso", "Sessão atualizada com sucesso!", Alert.AlertType.INFORMATION)
                    loadSessionsToTableView()
                    clearSessionsForm()
                }
            } else {
                showAlert("Erro", "Por favor, preencha todos os campos corretamente!", Alert.AlertType.ERROR)
            }
        } catch (e: Exception) {
            showAlert("Erro", "Ocorreu um erro ao atualizar a sessão: ${e.message}", Alert.AlertType.ERROR)
            e.printStackTrace()
        }
    }
    private fun getMovieChanges(updatedMovie: Movie): String {
        val changes = mutableListOf<String>()
        originalMovie?.let {
            if (it.title != updatedMovie.title) changes.add("Título: ${it.title} -> ${updatedMovie.title}")
            if (it.duration != updatedMovie.duration) changes.add("Duração: ${it.duration} -> ${updatedMovie.duration}")
            if (it.price != updatedMovie.price) changes.add("Preço: ${it.price} -> ${updatedMovie.price}")
            if (it.productionType != updatedMovie.productionType) changes.add("Tipo de Produção: ${it.productionType} -> ${updatedMovie.productionType}")
            if (it.hasHalf != updatedMovie.hasHalf) changes.add("Meia Entrada: ${it.hasHalf} -> ${updatedMovie.hasHalf}")
            if (it.audio != updatedMovie.audio) changes.add("Áudio: ${it.audio} -> ${updatedMovie.audio}")
            if (it.has3d != updatedMovie.has3d) changes.add("3D: ${it.has3d} -> ${updatedMovie.has3d}")
            if (it.imagePath != updatedMovie.imagePath) changes.add("Imagem: ${it.imagePath} -> ${updatedMovie.imagePath}")
        }
        return changes.joinToString("\n")
    }

    private fun getSessionChanges(updatedSession: Session): String {
        val changes = mutableListOf<String>()
        originalSession?.let {
            if (it.id != updatedSession.id) changes.add("ID: ${it.id} -> ${updatedSession.id}")
            if (it.numberRoom != updatedSession.numberRoom) changes.add("Número da Sala: ${it.numberRoom} -> ${updatedSession.numberRoom}")
            if (it.sessionCapacity != updatedSession.sessionCapacity) changes.add("Capacidade: ${it.sessionCapacity} -> ${updatedSession.sessionCapacity}")
            if (it.movie != updatedSession.movie) changes.add("Filme: ${it.movie?.title} -> ${updatedSession.movie?.title}")
            if (it.startTime != updatedSession.startTime) changes.add("Hora de Início: ${it.startTime} -> ${updatedSession.startTime}")
            if (it.status != updatedSession.status) changes.add("Status: ${it.status} -> ${updatedSession.status}")
        }
        return changes.joinToString("\n")
    }

    private fun showConfirmationDialog(changes: String): Boolean {
        val dialog = Dialog<ButtonType>()
        dialog.title = "Confirmação de Atualização"
        dialog.headerText = "Você deseja atualizar as seguintes informações?"

        val grid = GridPane().apply {
            styleClass.add("dialog-grid")
            hgap = 10.0
            vgap = 10.0
            padding = Insets(20.0, 150.0, 10.0, 10.0)
        }

        val label = Label("Alterações:")
        val textArea = TextArea(changes).apply {
            isEditable = false
            prefWidth = 400.0
            prefHeight = 200.0
        }

        grid.add(label, 0, 0)
        grid.add(textArea, 0, 1)

        dialog.dialogPane.content = grid
        dialog.dialogPane.buttonTypes.addAll(ButtonType.OK, ButtonType.CANCEL)

        val result = dialog.showAndWait()
        return result.isPresent && result.get() == ButtonType.OK
    } // Tá sem CSS

    private fun loadMovieData(movie: Movie) {
        originalMovie = movie

        movies_movieId.text = movie.id.toString()
        movies_title.text = movie.title
        movies_duration.text = movie.duration.toString()
        movies_typeProd.value = movie.productionType
        movies_hasHalf.value = movie.hasHalf
        movies_price.text = movie.price.toString()
        movies_audio.value = movie.audio
        movies_has3d.value = movie.has3d

        val imagePath = movie.imagePath
        if (imagePath != null && imagePath.isNotEmpty() && File(imagePath).exists()) {
            val image = javafx.scene.image.Image(File(imagePath).toURI().toString(), 260.0, 385.0, false, true)
            movies_imageView.image = image
            movies_imageLabel.isVisible = false
            movies_imageLabel2.isVisible = false
        } else {
            movies_imageView.image = null
            movies_imageLabel.isVisible = false
            movies_imageLabel2.isVisible = true
        }
    }

    private fun loadSessionData(session: Session) {
        originalSession = session

        sessions_id.text = session.id.toString()
        sessions_roomNumber.text = session.numberRoom
        sessions_movie.value = session.movie
        sessions_statusLabel.text = session.status.status

        // Carregar data e hora somente se houver um filme
        if (session.movie != null) {
            // Separar a data e a hora
            val date = session.startTime?.toLocalDate() // Usar a data da sessão ou null
            val time = session.startTime?.toLocalTime()?.format(DateTimeFormatter.ofPattern("HH:mm")) ?: "N/A" // Usar a hora da sessão ou "N/A"

            // Carregar a data no DatePicker
            sessions_datePicker.value = date

            // Carregar a hora no campo de texto
            sessions_startTime.text = if (time != "N/A") time else ""
        } else {
            sessions_datePicker.value = null
            sessions_startTime.text = ""
        }
    }

    fun importImage() {
        val fileChooser = FileChooser().apply {
            extensionFilters.addAll(
                FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.webp")
            )
        }

        val stage: Stage = movies_importBtn.scene.window as Stage

        val file = fileChooser.showOpenDialog(stage)

        file?.let {
            val image = javafx.scene.image.Image(it.toURI().toString(), 260.0, 385.0, false, true)
            movies_imageView.image = image
            movies_imageLabel.isVisible = false
            movies_imageLabel2.isVisible = false
            imagePath = it.path.replace("\\", "\\\\")
        }
    }

    fun openSales() {
        val selectedSession = sessions_tableView.selectionModel.selectedItem
        if (selectedSession == null) {
            showAlert("Erro", "Nenhuma sessão selecionada!", Alert.AlertType.ERROR)
            return
        }

        if (selectedSession.status == SessionStatus.SALE) {
            showAlert("Erro", "As vendas já estão abertas para esta sessão!", Alert.AlertType.ERROR)
            return
        }

        val updatedSession = selectedSession.copy(status = SessionStatus.SALE)
        sessionDAO.updateSession(updatedSession.id, updatedSession)
        showAlert("Sucesso", "Vendas abertas com sucesso!", Alert.AlertType.INFORMATION)
        loadSessionsToTableView()
        clearSessionsForm()
    }

    fun closeSales() {
        val selectedSession = sessions_tableView.selectionModel.selectedItem
        if (selectedSession == null) {
            showAlert("Erro", "Nenhuma sessão selecionada!", Alert.AlertType.ERROR)
            return
        }

        if (selectedSession.status == SessionStatus.CLOSED) {
            showAlert("Erro", "As vendas já estão fechadas para esta sessão!", Alert.AlertType.ERROR)
            return
        }

        val updatedSession = selectedSession.copy(status = SessionStatus.CLOSED)
        sessionDAO.updateSession(updatedSession.id, updatedSession)
        showAlert("Sucesso", "Vendas fechadas com sucesso!", Alert.AlertType.INFORMATION)
        loadSessionsToTableView()
        clearSessionsForm()
    }
    
    private fun getSessionStatusFromString(status: String): SessionStatus {
        return when (status) {
            "Espera" -> SessionStatus.WAITING
            "Venda" -> SessionStatus.SALE
            "Cancelada" -> SessionStatus.CANCELLED
            "Fechada" -> SessionStatus.CLOSED
            else -> SessionStatus.WAITING // Valor padrão
        }
    }

    private fun setupSessionsMovieComboBox() {
        // Define a StringConverter for the ComboBox
        sessions_movie.converter = object : StringConverter<Movie>() {
            override fun toString(movie: Movie?): String? {
                return if (movie == null) {
                    null
                } else {
                    "Filme: ${movie.title} - Duração: ${movie.duration}min. - Preço:  R$${movie.price} - Tipo de Áudio: ${movie.audio} - 3D: ${movie.has3d} - Meia: ${movie.hasHalf}"
                }
            }

            override fun fromString(string: String?): Movie? {
                throw UnsupportedOperationException("Not supported")
            }
        }

        // Set a custom cell factory to format the options in the ComboBox dropdown
        sessions_movie.setCellFactory { _ ->
            object : ListCell<Movie>() {
                override fun updateItem(movie: Movie?, empty: Boolean) {
                    super.updateItem(movie, empty)
                    text = if (empty || movie == null) {
                        null
                    } else {
                        "Filme: ${movie.title} - Duração: ${movie.duration}min. - Preço:  R$${movie.price} - Tipo de Áudio: ${movie.audio} - 3D: ${movie.has3d} - Meia: ${movie.hasHalf}"
                    }
                }
            }
        }
    } //Não é possível instanciar diretamente um enum com um construtor em kotlin, então preciso usar uma das constantes definidas no enum, para isso verifico o texto do status e correspondendo-o ao valor correto do enum

    private fun setupMovieParameters(){
        movies_addBtn.setOnAction { addMovie() }

        movies_col_movieId.cellValueFactory = PropertyValueFactory("id")
        movies_col_movieTitle.cellValueFactory = PropertyValueFactory("title")
        movies_col_movieDuration.cellValueFactory = PropertyValueFactory("duration")
        movies_col_productionType.cellValueFactory = PropertyValueFactory("productionType")
        movies_col_moviePrice.cellValueFactory = PropertyValueFactory("price")
        movies_col_audType.cellValueFactory = PropertyValueFactory("audio")
        movies_col_has3d.cellValueFactory = PropertyValueFactory("has3d")
        movies_col_hasHalf.cellValueFactory = PropertyValueFactory("hasHalf")

        movies_col_hasCover.setCellValueFactory { cellData ->
            val movie = cellData.value
            val hasCover = if (movie.imagePath.isNullOrEmpty()) "Não" else "Sim"
            SimpleStringProperty(hasCover)
        }

        movies_tableView.selectionModel.selectedItemProperty().addListener { _, _, newValue ->
            if (newValue != null) {
                loadMovieData(newValue)
                movies_imageLabel.isVisible = false
                movies_imageLabel2.isVisible = true
            } else {
                clearMoviesForm()
                movies_imageLabel.isVisible = true
                movies_imageLabel2.isVisible = false
            }
        }

        movies_tableView.items = movieList

        //Função de interação com o mouse do usuario com os filmes cadastrados
        movies_tableView.selectionModel.selectedItemProperty().addListener { _, _, selectedMovie ->
            selectedMovie?.let { movie ->
                loadMovieData(movie)
            }
        }
    }

    private fun setupSessionParameters() {
        sessions_col_sessionId.cellValueFactory = PropertyValueFactory<Session, Int>("id")
        sessions_col_number.cellValueFactory = PropertyValueFactory<Session, String>("numberRoom")
        sessions_col_currentMovie.cellValueFactory = PropertyValueFactory<Session, Movie>("movie")
        sessions_col_capacity.cellValueFactory = PropertyValueFactory<Session, Int>("sessionCapacity")
        sessions_col_ocupation.cellValueFactory = PropertyValueFactory<Session, Int>("sessionDisponibility")
        sessions_rows = TextField()
        sessions_cols = TextField()

        // Usando uma célula personalizada para a coluna de filme atual
        sessions_col_currentMovie.setCellFactory {
            object : TableCell<Session, Movie>() {
                override fun updateItem(movie: Movie?, empty: Boolean) {
                    super.updateItem(movie, empty)
                    text = if (empty || movie == null) {
                        ""
                    } else {
                        formatMovieDetails(movie)
                    }
                }
            }
        }

        // Usando uma célula personalizada para a coluna de status
        sessions_col_sessionStatus.setCellValueFactory(PropertyValueFactory<Session, SessionStatus>("status"))
        sessions_col_sessionStatus.setCellFactory {
            object : TableCell<Session, SessionStatus>() {
                override fun updateItem(item: SessionStatus?, empty: Boolean) {
                    super.updateItem(item, empty)
                    text = item?.status ?: ""
                }
            }
        }

        sessions_tableView.items = sessionList

        // Função que carrega os nomes dos filmes para a comboBox do menu de sessões
        sessions_form.visibleProperty().addListener { _, _, newValue ->
            if (newValue) {
                loadMoviesToSessions()
            }
        }

        // Adiciona o listener para a TableView de sessões
        sessions_tableView.selectionModel.selectedItemProperty().addListener { _, _, selectedSession ->
            selectedSession?.let { session ->
                loadSessionData(session)
            }
        }
    }

    private fun setupTimeFormatter() {
        val timeFormatter = TextFormatter<String> { change ->
            val newText = change.controlNewText

            // Permitir apenas números e     o caractere ":"
            if (!newText.matches(Regex("\\d{0,2}:?\\d{0,2}"))) {
                return@TextFormatter null
            }

            val caretPosition = change.caretPosition
            val anchor = change.anchor

            // Adicionar ':' automaticamente após os dois primeiros dígitos
            if (newText.length == 2 && !newText.contains(":")) {
                change.text += ":"
                change.caretPosition = caretPosition + 1
                change.anchor = anchor + 1
            }

            change
        }

        sessions_startTime.textFormatter = timeFormatter
    }

    fun clearMovieBox(){
        sessions_movie.value = null
    }

    private fun truncateTitle(title: String, maxLength: Int = 15): String {
        return if (title.length > maxLength) {
            "${title.take(maxLength)}..."
        } else {
            title
        }
    }

    private fun formatMovieDetails(movie: Movie): String {
        return """
        Título: ${truncateTitle(movie.title)} - Duração: ${movie.duration} min
        3D: ${movie.has3d} - Audio: ${movie.audio}
    """.trimIndent()
    }

    private fun homeDisplayCards() {
        val sessionsData = sessionDAO.listSessions()

        home_gridPane.children.clear()
        home_gridPane.rowConstraints.clear()
        home_gridPane.columnConstraints.clear()

        var row = 0
        var column = 0
        val maxColumns = 2

        for (session in sessionsData) {
            try {
                val fxmlLoader = FXMLLoader(javaClass.getResource("/com/ed/cinemamanagementsystem/MovieCard.fxml"))
                val cardPane: AnchorPane = fxmlLoader.load()

                val movieCardController = fxmlLoader.getController<MovieCardController>()
                movieCardController.setData(session, this)

                if (column == maxColumns) {
                    column = 0
                    row++
                }

                home_gridPane.add(cardPane, column++, row)
                GridPane.setMargin(cardPane, Insets(10.0))
                GridPane.setHalignment(cardPane, HPos.CENTER)
                GridPane.setValignment(cardPane, VPos.CENTER)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        home_gridPane.alignment = Pos.TOP_CENTER
    }

    private val selectedSeats = mutableListOf<Pair<Int, Int>>()
    private val tickets: ObservableList<Ticket> = FXCollections.observableArrayList()

    fun handleAddTickets(session: Session) {
        if (selectedSeats.isEmpty()) {
            showAlert("Erro", "Por favor, selecione ao menos uma poltrona!", Alert.AlertType.ERROR)
            return
        }

        try {
            val fullPrice = session.movie?.price ?: 0.0
            val halfPrice = fullPrice / 2

            val tickets = selectedSeats.map { seat ->
                Ticket(
                    ticketId = generateTicketId(),
                    sessionId = session.id,
                    movieName = session.movie?.title ?: "",
                    customerId = Data.username,
                    purchaseTime = LocalDateTime.now(),
                    seatRow = seat.first,
                    seatCol = seat.second,
                    price = 0.0,
                    ticketType = ""
                )
            }

            ticketList.addAll(tickets)
            home_cartTableView.items = ticketList
            home_cartTableView.refresh()

            // Atualiza a grade para refletir os assentos adicionados ao carrinho
            val gridPane = sessionGridMap[session.id] ?: GridPane().apply {
                sessionGridMap[session.id] = this
            }
            updatePreviewGrid(gridPane, session, session.rows, session.cols, 0) // Passa 0 para não selecionar novos assentos

            selectedSeats.clear()

            updatePriceLabels(fullPrice, halfPrice)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        initializeComboBoxes()
        initializeAudioTypeList()
        intializeProductionTypeList()
        displayUsername()
        setupMovieParameters()
        setupSessionParameters()
        loadMoviesToTableView()
        setupTimeFormatter()

        home_cartTableView_col_movie.setCellValueFactory { cellData -> SimpleStringProperty(cellData.value.movieName) }
        home_cartTableView_col_seat.setCellValueFactory { cellData ->
            SimpleStringProperty("${('A' + cellData.value.seatRow)}${cellData.value.seatCol + 1}")
        }
        home_cartTableView_col_price.setCellValueFactory { cellData -> SimpleDoubleProperty(cellData.value.price).asObject() }
        home_cartTableView.items = tickets

        home_fullPriceCheckBox.selectedProperty().addListener { _, _, isSelected ->
            onPriceCheckBoxClicked(isSelected, "Inteira")
        }
        home_halfPriceCheckBox.selectedProperty().addListener { _, _, isSelected ->
            onPriceCheckBoxClicked(isSelected, "Meia")
        }
        home_cartTableView.selectionModel.selectedItemProperty().addListener { _, _, newTicket ->
            home_fullPriceCheckBox.isSelected = false
            home_halfPriceCheckBox.isSelected = false
        }

        home_cartTableView.selectionModel.selectedItemProperty().addListener { _, _, newTicket ->
            if (newTicket != null) {
                home_fullPriceCheckBox.isSelected = newTicket.ticketType == "Inteira"
                home_halfPriceCheckBox.isSelected = newTicket.ticketType == "Meia"
            }

            home_fullPriceCheckBox.setOnAction {
                if (newTicket != null) {
                    newTicket.ticketType = "Inteira"
                    home_halfPriceCheckBox.isSelected = false
                    home_cartTableView.refresh()
                    updateTotal()
                }
            }

            home_halfPriceCheckBox.setOnAction {
                if (newTicket != null) {
                    newTicket.ticketType = "Meia"
                    home_fullPriceCheckBox.isSelected = false
                    home_cartTableView.refresh()
                    updateTotal()
                }
            }
        }
    }

    private fun onPriceCheckBoxClicked(isSelected: Boolean, type: String) {
        val selectedTickets = home_cartTableView.selectionModel.selectedItems
        val session = currentSelectedSession ?: return

        selectedTickets.forEach { ticket ->
            ticket.price = if (type == "Inteira") session.movie?.price ?: 0.0 else (session.movie?.price ?: 0.0) / 2
            ticket.ticketType = if (isSelected) type else ""
        }
        home_cartTableView.refresh()
    }

    private val ticketList: ObservableList<Ticket> = FXCollections.observableArrayList()

    private var nextTicketId = 100000

    private fun generateTicketId(): Int {
        return nextTicketId++
    }

    private fun updatePriceLabels(fullPrice: Double, halfPrice: Double) {
        home_fullPrice.text = "Preço: R$ %.2f".format(fullPrice)
        home_halfPrice.text = "Preço: R$ %.2f".format(halfPrice)
    }

    private val sessionGridMap: MutableMap<Int, GridPane> = mutableMapOf()
    private val soldSeatsMap: MutableMap<Int, MutableList<Pair<Int, Int>>> = mutableMapOf()


    fun showSeatSelectionPopup(session: Session, numberOfTickets: Int) {
        currentSelectedSession = session
        this.numberOfTickets = numberOfTickets
        val dialog = Stage()
        dialog.title = "Seleção de Poltronas"

        // Obtém a instância de GridPane da sessão atual
        val gridPane = sessionGridMap[session.id] ?: GridPane().apply {
            sessionGridMap[session.id] = this
        }
        gridPane.gridLinesVisibleProperty().set(true)
        updatePreviewGrid(gridPane, session, session.rows, session.cols, numberOfTickets) // Passa numberOfTickets aqui

        val scrollPane = ScrollPane(gridPane)
        scrollPane.isFitToWidth = true
        scrollPane.isFitToHeight = true
        scrollPane.maxWidth = Double.MAX_VALUE
        scrollPane.maxHeight = Double.MAX_VALUE

        val layout = VBox(10.0, scrollPane)
        layout.alignment = Pos.CENTER
        layout.padding = Insets(10.0)

        VBox.setVgrow(scrollPane, Priority.ALWAYS)

        val scene = Scene(layout)
        dialog.scene = scene

        val confirmButton = Button("Confirmar")
        confirmButton.setOnAction {
            if (selectedSeats.size != numberOfTickets) {
                showAlert("Erro", "Por favor, selecione exatamente $numberOfTickets poltronas!", Alert.AlertType.ERROR)
            } else {
                handleAddTickets(session)
                dialog.close()
            }
        }

        layout.children.add(confirmButton)
        dialog.showAndWait()
    }


    private fun updateTotal() {
        val total = ticketList.sumOf { it.price }
        home_total.text = String.format("%.2f", total)
    }

    fun payForTickets() {
        if (ticketList.isEmpty()) {
            showAlert("Erro", "Nenhum ingresso selecionado!", Alert.AlertType.ERROR)
            return
        }

        val orderId = nextOrderId++
        val ticketSummary = ticketList.joinToString("\n") { ticket ->
            "ID do Ingresso: ${ticket.ticketId}, Sessão ID: ${ticket.sessionId}, Filme: ${ticket.movieName}, Poltrona: ${('A' + ticket.seatRow)}${ticket.seatCol + 1}, Tipo: ${ticket.ticketType}, Preço: ${ticket.price}, Cliente ID: ${ticket.customerId}, Data de Compra: ${ticket.purchaseTime}"
        }

        // Armazena o pedido
        val order = Order(orderId, ticketList.toList())
        orderList.add(order)

        showAlert("Compra Confirmada", "Ingressos vendidos:\n$ticketSummary", Alert.AlertType.INFORMATION)
        println("Venda realizada: \n$ticketSummary")

        // Atualiza a disponibilidade dos assentos na sessão
        currentSelectedSession?.let { session ->
            val soldSeats = soldSeatsMap.getOrPut(session.id) { mutableListOf() }
            ticketList.forEach { ticket ->
                val seatPosition = Pair(ticket.seatRow, ticket.seatCol)
                soldSeats.add(seatPosition) // Adiciona a posição do assento vendido
            }

            // Atualiza a grade para refletir os assentos vendidos
            val gridPane = sessionGridMap[session.id] ?: GridPane().apply {
                sessionGridMap[session.id] = this
            }
            updatePreviewGrid(gridPane, session, session.rows, session.cols, 0) // Atualiza a grade para refletir os assentos vendidos

            // Diminui a disponibilidade da sessão
            session.sessionDisponibility -= ticketList.size
            println("Disponibilidade da sessão após a compra: ${session.sessionDisponibility}")
        }

        // Limpa a lista de ingressos após a confirmação
        ticketList.clear()
        home_cartTableView.refresh()
        updateTotal()
        loadSessionsToTableView()
        clearSessionsForm()
        updateSessionsTableView()
        homeDisplayCards()
    }


//    @FXML
//    private fun onFullPriceCheckBoxClicked() {
//        val selectedTickets = home_cartTableView.selectionModel.selectedItems
//        selectedTickets.forEach { ticket ->
//            ticket.price = session.movie?.price ?: 0.0 // Atualize com o preço da inteira
//            ticket.ticketType = "Inteira"
//        }
//        home_cartTableView.refresh()
//    }
//
//    @FXML
//    private fun onHalfPriceCheckBoxClicked() {
//        val selectedTickets = home_cartTableView.selectionModel.selectedItems
//        selectedTickets.forEach { ticket ->
//            ticket.price = (session.movie?.price ?: 0.0) / 2 // Atualize com o preço da meia
//            ticket.ticketType = "Meia"
//        }
//        home_cartTableView.refresh()
//    }

    //    private fun removeSelectedTicket() {
//        val selectedTicket = home_cartTableView.selectionModel.selectedItem
//        if (selectedTicket != null) {
//            ticketList.remove(selectedTicket)
//            home_cartTableView.refresh()
//            // Libera o botão referente à poltrona
//            val seatPosition = Pair(selectedTicket.seatRow, selectedTicket.seatCol)
//            selectedSeats.remove(seatPosition)
//            currentSelectedSession?.let { updatePreviewGrid(previewGrid, it, it.rows, it.cols, numberOfTickets) }
//            updateTotal() // Atualiza o total após a remoção
//        } else {
//            showAlert("Erro", "Nenhum ticket selecionado!", Alert.AlertType.ERROR)
//        }
//    }
}