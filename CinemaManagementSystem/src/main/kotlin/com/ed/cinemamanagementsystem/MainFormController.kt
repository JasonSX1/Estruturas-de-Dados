package com.ed.cinemamanagementsystem

import com.ed.cinemamanagementsystem.Data.Companion.username
import javafx.application.Platform
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.fxml.Initializable
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.control.*
import javafx.scene.image.ImageView
import javafx.scene.layout.AnchorPane
import javafx.stage.Stage
import org.w3c.dom.Text
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
    private lateinit var MoviesBtn: Button

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
    private lateinit var movies_btn: ImageView

    @FXML
    private lateinit var movies_clearBtn: Button

    @FXML
    private lateinit var movies_col_audType: TableColumn<*, *>

    @FXML
    private lateinit var movies_col_has3d: TableColumn<*, *>

    @FXML
    private lateinit var movies_col_hasHalf: TableColumn<*, *>

    @FXML
    private lateinit var movies_col_movieDuration: TableColumn<*, *>

    @FXML
    private lateinit var movies_col_movieId: TableColumn<*, *>

    @FXML
    private lateinit var movies_col_moviePrice: TableColumn<*, *>

    @FXML
    private lateinit var movies_col_moviePrice1: TableColumn<*, *>

    @FXML
    private lateinit var movies_col_movieTitle: TableColumn<*, *>

    @FXML
    private lateinit var movies_col_productionType: TableColumn<*, *>

    @FXML
    private lateinit var movies_deleteBtn: Button

    @FXML
    private lateinit var movies_importBtn: Button

    @FXML
    private lateinit var movies_tableView: TableView<*>

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

    private val comboList = arrayOf("Sim", "Não")

    private val productionTypeList = arrayOf("Nacional", "Estrangeira")

    private val audioTypeList = arrayOf("Original", "Original com Legenda", "Dublado")

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

    //Testar essa função de inicializar a combobox dos filmes
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

    //Desenvolver o metodo para cadastrar os filmes e alocar eles na ED


    fun displayUsername() {
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

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        initializeComboBoxes()
        initializeAudioTypeList()
        intializeProductionTypeList()
        displayUsername()
    }
}
