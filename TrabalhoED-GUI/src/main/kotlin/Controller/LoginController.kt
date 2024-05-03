package Controller

import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.control.PasswordField
import javafx.scene.control.TextField

class LoginController {

    @FXML
    private lateinit var usernameTextField: TextField

    @FXML
    private lateinit var passwordTextField: PasswordField

    @FXML
    private lateinit var loginButton: Button

    @FXML
    private lateinit var guestButton: Button

    @FXML
    fun initialize() {
        // Método chamado quando o controller é inicializado
        // Você pode adicionar inicializações adicionais aqui
        loginButton.setOnAction { handleLogin() }
        guestButton.setOnAction { handleGuestLogin() }
    }

    private fun handleLogin() {
        val username = usernameTextField.text
        val password = passwordTextField.text
    }

    private fun handleGuestLogin() {
    }
}
