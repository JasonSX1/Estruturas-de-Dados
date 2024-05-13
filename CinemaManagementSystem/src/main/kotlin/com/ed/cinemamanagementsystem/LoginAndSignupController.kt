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

    fun regBtn(){

        if(su_username.text.isEmpty() || su_password.text.isEmpty() ||  su_question.selectionModel.selectedItem == null || su_answer.text.isEmpty()){
            alert = Alert(Alert.AlertType.ERROR)
            alert.title = "Mensagem de erro!"
            alert.headerText = null
            alert.contentText = "Preencha todos os campos em branco!"
            alert.showAndWait()
        } else {
            val regData : String = "INSERT INTO users (username, password, question, answer) VALUES(?, ?, ?, ?)"
            val connect: Connection? = Database.connectDB()
            if (connect != null) {

<<<<<<< Updated upstream
            try {

                val prepare = connect!!.prepareStatement(regData)
                prepare.setString(1, su_username.text)
                prepare.setString(2, su_password.text)
                prepare.setString(3, su_question.selectionModel.selectedItem as String)
                prepare.setString(4, su_answer.text)

                prepare.executeUpdate()

                alert = Alert(Alert.AlertType.INFORMATION)
                alert.title = "Mensagem de informação!"
                alert.headerText = null
                alert.contentText = "Cadastro bem sucedido!"
                alert.showAndWait()

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


            }catch (e: Exception){e.printStackTrace()}
=======
                try {
                    //Verificação se o username do usuario foi cadastrado
                    val checkUsername = "SELECT username FROM users WHERE username = ?"
                    val prepare = connect.prepareStatement(checkUsername)
                    prepare.setString(1, su_username.text)
                    val result = prepare.executeQuery()

                    if(result.next()){
                        alert = Alert(Alert.AlertType.ERROR)
                        alert.title = "Mensagem de erro!"
                        alert.headerText = null
                        alert.contentText = su_username.text + " já está cadastrado!"
                        alert.showAndWait()
                    } else if(su_password.text.length < 8){
                        alert = Alert(Alert.AlertType.ERROR)
                        alert.title = "Mensagem de erro!"
                        alert.headerText = null
                        alert.contentText = "Sua senha deve conter mais de 8 caracteres!"
                        alert.showAndWait()
                    } else {

                        val prepare = connect!!.prepareStatement(regData)
                        prepare.setString(1, su_username.text)
                        prepare.setString(2, su_password.text)
                        prepare.setString(3, su_question.selectionModel.selectedItem as String)
                        prepare.setString(4, su_answer.text)

                        val date = java.util.Date()
                        val sqlDate = java.sql.Date(date.time)
                        prepare.setString(5, sqlDate.toString())
                        prepare.executeUpdate()

                        alert = Alert(Alert.AlertType.INFORMATION)
                        alert.title = "Mensagem de informação!"
                        alert.headerText = null
                        alert.contentText = "Cadastro bem sucedido!"
                        alert.showAndWait()

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

                    }

                    if(su_password.text.length < 8){
                        alert = Alert(Alert.AlertType.ERROR)
                        alert.title = "Mensagem de erro!"
                        alert.headerText = null
                        alert.contentText = "Sua senha deve conter mais de 8 caracteres!"
                        alert.showAndWait()
                    }
                }catch (e: Exception){e.printStackTrace()}
            } else {
                //TODO CASE CONNECTION IS NULL
            }
>>>>>>> Stashed changes
        }
    }

    fun regQuestionList(){
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

                regQuestionList();
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