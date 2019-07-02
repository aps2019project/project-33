package View.AccountMenu;

import Controller.Client;
import Controller.Menus.ServerMassage;
import View.View;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Popup;

import java.net.URL;
import java.util.ResourceBundle;

//akse duelyst chera nis
public class AccountMenuController implements Initializable {
    public Label logInModeButton;
    public TextField usernameTextField;
    public TextField passwordTextFields;
    public Label signUpModeButton;
    public Label enterButton;
    public Label errorLabel;
    private Mode mode = Mode.logIn;

    enum Mode {
        logIn, signUp
    }

    //
    private void selectMode(Label label) {
        label.getStyleClass().clear();
        label.getStyleClass().add("SelectedButton");
    }

    private void notSelectMode(Label label) {
        label.getStyleClass().clear();
        label.getStyleClass().add("NotSelectedButton");
    }

    private void changeMode(Mode mode) {
        this.mode = mode;
        if (mode == Mode.logIn) {
            selectMode(logInModeButton);
            notSelectMode(signUpModeButton);
            enterButton.setText("LOG IN");
        } else {
            selectMode(signUpModeButton);
            notSelectMode(logInModeButton);
            enterButton.setText("SIGN UP");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        enterButton.setOnMouseClicked(event -> {
            String username = usernameTextField.getText();
            String password = passwordTextFields.getText();
            ServerMassage serverMassage;
            if (mode == Mode.logIn)
                serverMassage = Client.getClient().getAccountMenu().inputCommandLine("login " + username + " " + password);
            else
                serverMassage = Client.getClient().getAccountMenu().inputCommandLine("create account " + username + " " + password);
            interpret(serverMassage);
            usernameTextField.clear();
            passwordTextFields.clear();
        });

        signUpModeButton.setOnMouseClicked(event -> {
            changeMode(Mode.signUp);
        });

        logInModeButton.setOnMouseClicked(event -> {
            changeMode(Mode.logIn);
        });

        changeMode(Mode.signUp);
    }

    private void interpret(ServerMassage serverMassage) {
        if (serverMassage.getType() == ServerMassage.Type.Accept) return;
        usernameTextField.setStyle("-fx-border-color: red");
        passwordTextFields.setStyle("-fx-border-color: red");
        if (serverMassage.getErrorType() == ServerMassage.ErrorType.LogInFailed) {
            errorLabel.setText("Invalid username or password");
        }
        if (serverMassage.getErrorType() == ServerMassage.ErrorType.InvalidUsernameForSignUp) {
            errorLabel.setText("This account exists");
        }
        if (serverMassage.getErrorType() == ServerMassage.ErrorType.InvalidPasswordForSignUp) {
            errorLabel.setText("space in password !! ");
        }
    }
}
