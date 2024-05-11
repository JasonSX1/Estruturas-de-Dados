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

    fun switchForm(event: ActionEvent) {
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
