package com.ed.cinemamanagementsystem

import javafx.animation.TranslateTransition
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.fxml.FXML
import javafx.scene.layout.AnchorPane
import javafx.event.ActionEvent
import javafx.scene.control.*
import javafx.util.Duration
import java.sql.Connection
import java.sql.SQLException

class LoginAndSignupController {
    @FXML
    private lateinit var si_user: TextField

    @FXML
    private lateinit var su_answer: TextField

    @FXML
    private lateinit var su_username: TextField

    @FXML
    private lateinit var si_login: Button

    @FXML
    private lateinit var side_CreateBtn: Button

    @FXML
    private lateinit var side_alreadyHave: Button

    @FXML
    private lateinit var si_password: PasswordField

    @FXML
    private lateinit var su_password: PasswordField

    @FXML
    private lateinit var su_question: ComboBox<*>

    @FXML
    private lateinit var si_loginForm: AnchorPane

    @FXML
    private lateinit var side_form: AnchorPane

    @FXML
    private lateinit var su_signupForm: AnchorPane

    @FXML
    private lateinit var si_forgotPass: Hyperlink

    private val questionList = arrayOf("Onde você cursou o ensino médio?", "Qual o nome da sua mãe?", "Qual sua data de nascimento?")

    private lateinit var alert: Alert

    // Função de conexão com o BD
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

    private fun showAlert(title: String, message: String, alertType: Alert.AlertType) {
        val alert = Alert(alertType)
        alert.title = title
        alert.headerText = null
        alert.contentText = message
        alert.showAndWait()
    }

    fun loginBtn() {
        if (si_user.text.isNullOrEmpty() || si_password.text.isNullOrEmpty()) {
            showAlert("Mensagem de erro!", "Preencha corretamente seus dados de login!", Alert.AlertType.ERROR)
            return
        }

        val connection = connectToDatabase() ?: return

        val selectData = "SELECT username, password FROM users WHERE username = ? AND password = ?"

        try {
            // Imprimir valores dos campos de entrada para depuração
            val usernameInput = si_user.text.trim()
            val passwordInput = si_password.text.trim()
            println("Valores inseridos - Username: $usernameInput, Password: $passwordInput")

            val prepare = connection.prepareStatement(selectData)
            prepare.setString(1, usernameInput)
            prepare.setString(2, passwordInput)

            val result = prepare.executeQuery()

            if (result.next()) {
                val username = result.getString("username")
                println("Login bem-sucedido para o usuário: $username")
                showAlert("Aviso!", "Login bem sucedido!", Alert.AlertType.INFORMATION)

                // Linkar main form aqui
            } else {
                println("Nome de usuário ou senha incorretos.")
                showAlert("Mensagem de erro!", "Nome de usuário/Senha incorretos", Alert.AlertType.ERROR)
            }

            result.close()
            prepare.close()
            connection.close()
        } catch (e: SQLException) {
            println("Erro de banco de dados: ${e.message}")
            showAlert("Mensagem de erro!", "Erro de banco de dados: ${e.message}", Alert.AlertType.ERROR)
        }
    }

    fun regBtn() {
        if (su_username.text.isEmpty() || su_password.text.isEmpty() || su_question.selectionModel.selectedItem == null || su_answer.text.isEmpty()) {
            showAlert("Mensagem de erro!", "Preencha todos os campos em branco!", Alert.AlertType.ERROR)
            return
        }

        if (su_password.text.length < 8) {
            showAlert("Mensagem de erro!", "Sua senha deve conter ao menos 8 caracteres!", Alert.AlertType.ERROR)
            return
        }

        val connection = connectToDatabase() ?: return

        try {
            // Verificar se o nome de usuário já existe
            val checkUsernameQuery = "SELECT username FROM users WHERE username = ?"
            val checkUsernameStatement = connection.prepareStatement(checkUsernameQuery)
            checkUsernameStatement.setString(1, su_username.text)
            val existingUserResult = checkUsernameStatement.executeQuery()

            if (existingUserResult.next()) {
                showAlert("Mensagem de erro!", "O usuário: ${su_username.text} já está cadastrado!", Alert.AlertType.ERROR)
                return
            }

            // Nome de usuário não duplicado, então podemos prosseguir com o cadastro
            val regData = "INSERT INTO users (username, password, question, answer, date) VALUES(?, ?, ?, ?, ?)"
            val prepare = connection.prepareStatement(regData)
            prepare.setString(1, su_username.text)
            prepare.setString(2, su_password.text)
            prepare.setString(3, su_question.selectionModel.selectedItem as String)
            prepare.setString(4, su_answer.text)
            val currentDate = java.sql.Date(System.currentTimeMillis())
            prepare.setDate(5, currentDate)

            prepare.executeUpdate()

            showAlert("Aviso!", "Cadastro bem sucedido!", Alert.AlertType.INFORMATION)

            su_username.text = null
            su_password.text = null
            su_question.selectionModel.clearSelection()
            su_answer.text = null

            val slider = TranslateTransition()
            slider.node = side_form
            slider.toX = 0.0
            slider.duration = Duration.seconds(0.5)
            slider.setOnFinished {
                side_alreadyHave?.isVisible = false
                side_CreateBtn?.isVisible = true
                AnchorPane.setRightAnchor(side_form, null)
            }
            slider.play()
        } catch (e: SQLException) {
            showAlert("Mensagem de erro!", "Erro ao registrar: ${e.message}", Alert.AlertType.ERROR)
        } finally {
            try {
                connection.close()
            } catch (e: SQLException) {
                e.printStackTrace()
            }
        }
    }

    private fun regQuestionList() {
        val listQuestion = ArrayList<String>()

        for (data in questionList) {
            listQuestion.add(data)
        }

        val listData: ObservableList<String> = FXCollections.observableArrayList(listQuestion)
        su_question.items = listData
    }

    fun switchForm(event: ActionEvent) {
        val slider = TranslateTransition()

        if (event.source == side_CreateBtn) {
            slider.node = side_form
            slider.toX = 300.0
            slider.duration = Duration.seconds(0.5)

            slider.setOnFinished {
                side_alreadyHave?.isVisible = true
                side_CreateBtn?.isVisible = false
                AnchorPane.setRightAnchor(side_form, 300.0)

                regQuestionList()
            }

            slider.play()
        } else if (event.source == side_alreadyHave) {
            slider.node = side_form
            slider.toX = 0.0
            slider.duration = Duration.seconds(0.5)

            slider.setOnFinished {
                side_alreadyHave?.isVisible = false
                side_CreateBtn?.isVisible = true
                AnchorPane.setRightAnchor(side_form, null)
            }

            slider.play()
        }
    }
}
