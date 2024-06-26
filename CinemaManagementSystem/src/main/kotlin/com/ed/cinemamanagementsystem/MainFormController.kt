package com.ed.cinemamanagementsystem

import javafx.application.Application.launch
import javafx.application.Platform
import javafx.beans.property.SimpleStringProperty
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.fxml.Initializable
import javafx.geometry.Insets
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.control.*
import javafx.scene.control.cell.PropertyValueFactory
import javafx.scene.image.ImageView
import javafx.scene.layout.*
import javafx.stage.FileChooser
import javafx.stage.Modality
import javafx.stage.Stage
import javafx.util.Callback
import javafx.util.StringConverter
import java.awt.Image
import java.io.File
import java.net.URL
import java.sql.Connection
import java.sql.SQLException
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
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
    private lateinit var sessions_col_currentMovie: TableColumn<Session, Movie>

    @FXML
    private lateinit var sessions_col_nextMovie: TableColumn<Session, Movie>

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
    private lateinit var sessions_capacity: TextField

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

    private fun connectToDatabase(): Connection? {
        return try {
            val connection = Database.connectDB()
            if (connection == null) {
                showAlert("Mensagem de erro!", "Não foi possível estabelecer conexão com o banco de dados!", Alert.AlertType.ERROR)
            }
            connection
        } catch (e: SQLException) {
            showAlert("Mensagem de erro!", "Erro ao conectar ao banco de dados: ${e.message}", Alert.AlertType.ERROR)
            null
        }
    }

    private lateinit var alert: Alert

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
            val sessionId = sessions_id.text.toIntOrNull()
            val roomNumber = sessions_roomNumber.text
            val capacity = sessions_capacity.text.toIntOrNull()
            val movie = sessions_movie.value

            if (sessionId != null && roomNumber.isNotBlank() && capacity != null) {
                val existingSession = sessionDAO.searchSessionByID(sessionId)
                if (existingSession != null) {
                    showAlert("Erro", "ID da sessão já está cadastrado!", Alert.AlertType.ERROR)
                    return
                }

                val startDateTime = if (sessions_startTime.text.isNotBlank() && sessions_datePicker.value != null) {
                    val dateString = sessions_datePicker.value.toString() // Pega a data do DatePicker
                    val timeString = sessions_startTime.text // Pega o tempo do campo de texto

                    val dateTimeString = "$dateString $timeString" // Concatena data e hora
                    try {
                        LocalDateTime.parse(dateTimeString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
                    } catch (e: DateTimeParseException) {
                        showAlert("Erro", "Data e hora de início inválida!", Alert.AlertType.ERROR)
                        return
                    }
                } else {
                    null // startDateTime será null se a data ou a hora não forem fornecidas
                }

                // Cria uma nova sessão com status padrão WAITING
                val session = Session(sessionId, roomNumber, capacity, movie, startDateTime, SessionStatus.WAITING)
                val successful = sessionDAO.addSession(session)

                if (successful) {
                    showAlert("Sucesso", "Sessão adicionada com sucesso!", Alert.AlertType.INFORMATION)
                    loadSessionsToTableView()
                    clearSessionsForm()
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
        sessions_capacity.clear()
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

        if(event.source == dashboard_btn) {
            DashboardForm.isVisible = true
        } else if(event.source == sessions_btn){
            sessions_form.isVisible = true
        } else if(event.source == movies_btn){
            MoviesForm.isVisible = true
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
            val capacity = sessions_capacity.text.toIntOrNull()
            val movie = sessions_movie.value

            if (sessionId != null && roomNumber.isNotBlank() && capacity != null) {
                val dateString = sessions_datePicker.value.toString() // Pega a data do DatePicker
                val timeString = sessions_startTime.text // Pega o tempo do campo de texto

                val dateTimeString = "$dateString $timeString" // Concatena data e hora
                val startDateTime = try {
                    LocalDateTime.parse(dateTimeString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
                } catch (e: DateTimeParseException) {
                    showAlert("Erro", "Data e hora de início inválida!", Alert.AlertType.ERROR)
                    return
                }

                val updatedSession = Session(sessionId, roomNumber, capacity, movie, startDateTime, getSessionStatusFromString(sessions_statusLabel.text))

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
        sessions_capacity.text = session.sessionCapacity.toString()
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

    private fun setupSessionParameters(){
        sessions_col_sessionId.cellValueFactory = PropertyValueFactory<Session, Int>("id")
        sessions_col_number.cellValueFactory = PropertyValueFactory<Session, String>("numberRoom")
        sessions_col_currentMovie.cellValueFactory = PropertyValueFactory<Session, Movie>("movie")
        sessions_col_capacity.cellValueFactory = PropertyValueFactory<Session, Int>("sessionCapacity")

        // Usando uma célula personalizada para a coluna de status
        sessions_col_sessionStatus.setCellValueFactory(PropertyValueFactory<Session, SessionStatus>("status"))
        sessions_col_sessionStatus.setCellFactory(Callback<TableColumn<Session, SessionStatus>, TableCell<Session, SessionStatus>> {
            object : TableCell<Session, SessionStatus>() {
                override fun updateItem(item: SessionStatus?, empty: Boolean) {
                    super.updateItem(item, empty)
                    text = item?.status ?: ""
                }
            }
        })

        sessions_tableView.items = sessionList

        //Função que carrega os nomes dos filmes para a comboBox do menu de sesões
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
        val timeFormatter = TextFormatter<String>({ change ->
            val newText = change.controlNewText

            // Permitir apenas números e o caractere ":"
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
        })

        sessions_startTime.textFormatter = timeFormatter
    }

    fun clearMovieBox(){
        sessions_movie.value = null
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
    }
}