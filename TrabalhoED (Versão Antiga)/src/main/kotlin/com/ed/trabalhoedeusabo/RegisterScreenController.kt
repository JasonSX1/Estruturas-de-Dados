package Controller
import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.control.PasswordField
import javafx.scene.control.TextField

class RegisterScreenController{

    @FXML
    private lateinit var fullNameTextField: TextField

    @FXML
    private lateinit var emailTextField: TextField

    @FXML
    private lateinit var passwordField: PasswordField

    @FXML
    private lateinit var confirmPasswordField: PasswordField

    @FXML
    private lateinit var createAccountButton: Button

    @FXML
    fun initialize() {
        createAccountButton.setOnAction { handleCreateAccount() }
    }

    private fun handleCreateAccount() {
        val fullName = fullNameTextField.text
        val email = emailTextField.text
        val password = passwordField.text
        val confirmPassword = confirmPasswordField.text

    }
}
