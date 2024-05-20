package com.ed.cinemamanagementsystem

import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.fxml.Initializable
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.control.*
import javafx.scene.image.ImageView
import javafx.scene.layout.AnchorPane
import javafx.stage.Stage
import java.net.URL
import java.sql.SQLException
import java.util.*

class MainFormController : Initializable {

    @FXML
    private lateinit var DashboardForm: AnchorPane

    @FXML
    private lateinit var MoviesBtn: Button

    @FXML
    private lateinit var MoviesForm: AnchorPane

    @FXML
    private lateinit var MoviesForm1: AnchorPane

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
    private lateinit var movies_cleatBtn: Button

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
    private lateinit var movies_imageView: ImageView

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

    private lateinit var alert: Alert

    private fun showAlert(title: String, message: String, alertType: Alert.AlertType): Optional<ButtonType> {
        val alert = Alert(alertType)
        alert.title = title
        alert.headerText = null
        alert.contentText = message
        return alert.showAndWait()
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
            } else {
                // Não faz nada se o usuário cancelar ou se o valor não estiver presente
            }
        } catch (e: SQLException) {
            e.printStackTrace()
        }
    }



    override fun initialize(location: URL?, resources: ResourceBundle?) {
        // Inicialização do controlador
    }
}
