package com.ed.cinemamanagementsystem

import javafx.animation.TranslateTransition
import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.control.ComboBox
import javafx.scene.control.Hyperlink
import javafx.scene.control.PasswordField
import javafx.scene.control.TextField
import javafx.scene.layout.AnchorPane
import javafx.event.ActionEvent
import javafx.util.Duration

class LoginAndSignupController {
    @FXML
    private val si_forgotPass: Hyperlink? = null

    @FXML
    private val si_login: Button? = null

    @FXML
    private val si_loginForm: AnchorPane? = null

    @FXML
    private val si_password: PasswordField? = null

    @FXML
    private lateinit var si_user: TextField // Correção aqui

    @FXML
    private val side_CreateBtn: Button? = null

    @FXML
    private val side_form: AnchorPane? = null

    @FXML
    private val su_CreateBtn: Button? = null

    @FXML
    private lateinit var su_answer: TextField // Correção aqui

    @FXML
    private val su_password: PasswordField? = null

    @FXML
    private val su_question: ComboBox<*>? = null

    @FXML
    private val su_signupForm: AnchorPane? = null

    @FXML
    private lateinit var su_username: TextField // Correção aqui

    @FXML
    private val side_alreadyHave: Button? = null

    fun SwitchForm(event: ActionEvent) {
        val slider = TranslateTransition()

        if (event.source == side_CreateBtn) {
            slider.node = side_form
            slider.toX = 300.0
            slider.duration = Duration.seconds(0.5)

            slider.setOnFinished {
                side_alreadyHave?.isVisible = true
                side_CreateBtn?.isVisible = false
            }

            slider.play()
        } else if (event.source == side_alreadyHave) {
            slider.node = side_form
            slider.toX = 0.0
            slider.duration = Duration.seconds(0.5)

            slider.setOnFinished {
                side_alreadyHave?.isVisible = false
                side_CreateBtn?.isVisible = true
            }

            slider.play()
        }
    }
}
