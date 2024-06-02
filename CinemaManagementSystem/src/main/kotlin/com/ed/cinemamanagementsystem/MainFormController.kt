package com.ed.cinemamanagementsystem

import javafx.application.Platform
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
import javafx.scene.layout.AnchorPane
import javafx.scene.layout.GridPane
import javafx.stage.Stage
import java.awt.event.ActionEvent
import java.awt.event.MouseEvent
import java.net.URL
import java.sql.Connection
import java.sql.ResultSet
import java.sql.SQLException
import java.sql.Statement
import java.util.*

class MainFormController : Initializable {

    @FXML
    private lateinit var DashboardForm: AnchorPane

    @FXML
    private lateinit var movies_btn: Button

    @FXML
    private lateinit var movies_imageView: ImageView

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
    private lateinit var movies_clearBtn: Button

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
    private lateinit var movies_deleteBtn: Button

    @FXML
    private lateinit var movies_importBtn: Button

    @FXML
    private lateinit var movies_updateBtn: Button

    @FXML
    private lateinit var orders_btn: Button

    @FXML
    private lateinit var sessions_btn: Button

    @FXML
    private lateinit var sessions_col_capacity: TableColumn<*, *>

    @FXML
    private lateinit var sessions_col_currentMovie: TableColumn<*, *>

    @FXML
    private lateinit var sessions_col_nextMovie: TableColumn<*, *>

    @FXML
    private lateinit var sessions_col_number: TableColumn<*, *>

    @FXML
    private lateinit var sessions_col_ocupation: TableColumn<*, *>

    @FXML
    private lateinit var sessions_col_sessionId: TableColumn<*, *>

    @FXML
    private lateinit var sessions_tableView: TableView<*>

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
    private lateinit var sessions_clearBtn: Button

    @FXML
    private lateinit var sessions_closeSales: Button

    @FXML
    private lateinit var sessions_deleteBtn: Button

    @FXML
    private lateinit var sessions_id: TextField

    @FXML
    private lateinit var sessions_movie: ComboBox<String>

    @FXML
    private lateinit var sessions_roomNumber: TextField

    @FXML
    private lateinit var sessions_startSales: Button

    @FXML
    private lateinit var sessions_startTime: TextField

    @FXML
    private lateinit var sessions_updateBtn: Button

    @FXML
    private lateinit var sessions_statusLabel: Label

    private val movieList: ObservableList<Movie> = FXCollections.observableArrayList()

    private val comboList = arrayOf("Sim", "Não")

    private val productionTypeList = arrayOf("Nacional", "Estrangeira")

    private val audioTypeList = arrayOf("Original", "Original com Legenda", "Dublado")

    private val movieDAO: MovieDAO = DynamicMoviesList()

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

    //Testar essa função de inicializar a combobox dos filmes no menu de sessões
    //Na verdade tem que atualizar ela pra funcionar com a ED ao invés do BD
    private fun loadMovieNames() {
        val connection = connectToDatabase()
        if (connection != null) {
            val query = "SELECT movie_name FROM movies"
            try {
                val statement: Statement = connection.createStatement()
                val resultSet: ResultSet = statement.executeQuery(query)
                val movieNames = mutableListOf<String>()
                while (resultSet.next()) {
                    movieNames.add(resultSet.getString("nome"))
                }
                resultSet.close()
                statement.close()
                connection.close()

                // Atualizar o ComboBox na thread da UI
                Platform.runLater {
                    sessions_movie.items.clear()
                    sessions_movie.items.addAll(movieNames)
                }

            } catch (e: SQLException) {
                showAlert("Mensagem de erro!", "Erro ao carregar os nomes dos filmes: ${e.message}", Alert.AlertType.ERROR)
            }
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
            val hasHalf = movies_hasHalf.value == "Sim"
            val audio = movies_audio.value
            val has3d = movies_has3d.value

            if (movieId != null && title.isNotBlank() && duration != null && price != null && productionType != null && audio != null && has3d != null) {
                val movie = Movie(movieId, title, duration, productionType, hasHalf, price, audio, has3d == "Sim", "ImagePath")
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
                        clearForm()
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

    fun showCustomDialog() {
        val dialog = Dialog<String>().apply {
            title = "Escolher posição"
            headerText = "Escolha onde adicionar o filme"
            dialogPane.stylesheets.add(javaClass.getResource("dialog-styles.css").toExternalForm())
        }

        val addButtonType = ButtonType("Adicionar", ButtonBar.ButtonData.OK_DONE)
        dialog.dialogPane.buttonTypes.addAll(addButtonType, ButtonType.CANCEL)

        val grid = GridPane().apply {
            styleClass.add("dialog-grid")
            hgap = 10.0
            vgap = 10.0
            padding = Insets(20.0, 150.0, 10.0, 10.0)
        }

        val comboBox = ComboBox<String>(FXCollections.observableArrayList("Início", "Fim", "Posição Personalizada")).apply {
            styleClass.add("dialog-combo-box")
            selectionModel.selectFirst()
        }
        val positionField = TextField().apply {
            styleClass.add("dialog-text-field")
            isDisable = true
        }

        comboBox.selectionModel.selectedItemProperty().addListener { _, _, newValue ->
            positionField.isDisable = newValue != "Posição Personalizada"
        }

        grid.add(Label("Posição:").apply { styleClass.add("dialog-label") }, 0, 0)
        grid.add(comboBox, 1, 0)
        grid.add(Label("Posição Personalizada:").apply { styleClass.add("dialog-label") }, 0, 1)
        grid.add(positionField, 1, 1)

        dialog.dialogPane.content = grid

        dialog.setResultConverter { dialogButton ->
            if (dialogButton == addButtonType) {
                val selectedPosition = comboBox.selectionModel.selectedItem
                if (selectedPosition == "Posição Personalizada") {
                    positionField.text
                } else {
                    selectedPosition
                }
            } else {
                null
            }
        }

        val result = dialog.showAndWait()

        result.ifPresent { selectedPosition ->
            if (selectedPosition != null) { // Verifica se o usuário clicou em "Adicionar"
                val movieId = movies_movieId.text.toIntOrNull()
                val title = movies_title.text
                val duration = movies_duration.text.toIntOrNull()
                val price = movies_price.text.toDoubleOrNull()

                if (movieId != null && title.isNotBlank() && duration != null && price != null) {
                    val movie = Movie(movieId, title, duration, "Type", false, price, "AudioType", false, "ImagePath")

                    when (selectedPosition) {
                        "Início" -> movieDAO.addMovieStart(movie)
                        "Fim" -> movieDAO.addMovieEnd(movie)
                        else -> {
                            val posInt = selectedPosition.toIntOrNull()
                            if (posInt != null && posInt >= 0 && posInt <= movieDAO.qtdMovies()) {
                                movieDAO.addMovieCustomP(movie, posInt)
                            } else {
                                showAlert("Erro", "Posição inválida!", Alert.AlertType.ERROR)
                            }
                        }
                    }
                    showAlert("Sucesso", "Filme adicionado com sucesso!", Alert.AlertType.INFORMATION)
                    updateTableView()
                    loadMoviesToTableView()
                } else {
                    showAlert("Erro", "Por favor, preencha todos os campos corretamente!", Alert.AlertType.ERROR)
                }
            }
        }
    } // falta corrigir o css

    fun removeMovie() {
        val movieId = movies_movieId.text.toIntOrNull()

        if (movieId != null) {
            val removedMovie = movieDAO.removeMovie(movieId)
            if (removedMovie != null) {
                showAlert("Sucesso", "Filme removido com sucesso!", Alert.AlertType.INFORMATION)
            } else {
                showAlert("Erro", "Filme com ID $movieId não encontrado!", Alert.AlertType.ERROR)
            }
        } else {
            showAlert("Erro", "Por favor, insira um ID de filme válido!", Alert.AlertType.ERROR)
        }
    }

    fun configureTableViewColumns(tableView: TableView<Movie>) {
        // Vincula as propriedades do objeto Movie às células das colunas
        val idColumn = tableView.columns[0] as TableColumn<Movie, Int>
        idColumn.setCellValueFactory(PropertyValueFactory<Movie, Int>("id"))

        val titleColumn = tableView.columns[1] as TableColumn<Movie, String>
        titleColumn.setCellValueFactory(PropertyValueFactory<Movie, String>("title"))

        val durationColumn = tableView.columns[2] as TableColumn<Movie, Int>
        durationColumn.setCellValueFactory(PropertyValueFactory<Movie, Int>("duration"))

        val productionTypeColumn = tableView.columns[3] as TableColumn<Movie, String>
        productionTypeColumn.setCellValueFactory(PropertyValueFactory<Movie, String>("productionType"))

        val audTypeColumn = tableView.columns[4] as TableColumn<Movie, String>
        audTypeColumn.setCellValueFactory(PropertyValueFactory<Movie, String>("audType"))

        val has3dColumn = tableView.columns[5] as TableColumn<Movie, Boolean>
        has3dColumn.setCellValueFactory(PropertyValueFactory<Movie, Boolean>("has3d"))

        val hasHalfColumn = tableView.columns[6] as TableColumn<Movie, Boolean>
        hasHalfColumn.setCellValueFactory(PropertyValueFactory<Movie, Boolean>("hasHalf"))

        val priceColumn = tableView.columns[7] as TableColumn<Movie, Double>
        priceColumn.setCellValueFactory(PropertyValueFactory<Movie, Double>("price"))
    }

    private fun updateTableView() {
        // Obtém a lista atualizada de filmes da sua lista dinâmica
        val movies = movieDAO.listMovies()

        // Define os itens da TableView como a lista atualizada de filmes
        movies_tableView.items = FXCollections.observableArrayList(movies)
    }

    private fun loadMoviesToTableView() {
        movies_tableView.items.clear()
        val moviesList = movieDAO.listMovies()
        val movies = movieDAO.listMovies()
        println("Carregando filmes: $movies")

        val observableList = FXCollections.observableList(moviesList)
        movies_tableView.items = observableList
    }

    private fun clearForm() {
        movies_movieId.clear()
        movies_title.clear()
        movies_duration.clear()
        movies_price.clear()
        movies_typeProd.value = null
        movies_hasHalf.value = null
        movies_audio.value = null
        movies_has3d.value = null
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
    }

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        initializeComboBoxes()
        initializeAudioTypeList()
        intializeProductionTypeList()
        displayUsername()

        movies_addBtn.setOnAction { addMovie() }

        movies_col_movieId.cellValueFactory = PropertyValueFactory("id")
        movies_col_movieTitle.cellValueFactory = PropertyValueFactory("title")
        movies_col_movieDuration.cellValueFactory = PropertyValueFactory("duration")
        movies_col_productionType.cellValueFactory = PropertyValueFactory("productionType")
        movies_col_moviePrice.cellValueFactory = PropertyValueFactory("price")
        movies_col_audType.cellValueFactory = PropertyValueFactory("audio")
        movies_col_has3d.cellValueFactory = PropertyValueFactory("has3d")
        movies_col_hasHalf.cellValueFactory = PropertyValueFactory("hasHalf")

        movies_tableView.items = movieList

        // Carregar filmes no início
        loadMoviesToTableView()
    }
}