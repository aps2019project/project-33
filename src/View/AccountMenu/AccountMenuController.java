package View.AccountMenu;

import Controller.Client;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

//akse duelyst chera nis
public class AccountMenuController implements Initializable {
    public Label logInModeButton;
    public TextField usernameTextField;
    public TextField passwordTextFields;
    public Label signUpModeButton;
    public Label enterButton;
    private Mode mode = Mode.logIn;

    enum Mode{
        logIn, signUp
    }
//
    private void selectMode(Label label){
        label.getStyleClass().clear();
        label.getStyleClass().add("SelectedButton");
    }

    private void notSelectMode(Label label){
        label.getStyleClass().clear();
        label.getStyleClass().add("NotSelectedButton");
    }

    private void changeMode(Mode mode){
        this.mode = mode;
        if(mode == Mode.logIn){
            selectMode(logInModeButton);
            notSelectMode(signUpModeButton);
            enterButton.setText("LOG IN");
        }
        else{
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
            if(mode == Mode.logIn)
                Client.getClient().getAccountMenu().inputCommandLine("login " + username + " " + password);
            else
                Client.getClient().getAccountMenu().inputCommandLine("create account " + username + " " + password);
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
}
