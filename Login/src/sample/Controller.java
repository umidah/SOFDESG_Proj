package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Controller {

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Button exit;

    String test1 = "username";
    String test2 = "password";

    @FXML
    void Loginpressed(ActionEvent event) {
        String user = username.getText();
        String pass = password.getText();
       // System.out.println(username.getText());
       // System.out.println(password.getText());

        if (user.equals(test1)&&pass.equals(test2)){
            System.out.println("LOGIN SUCCESS");
        }
        else System.out.println("LOGIN FAILED");
    }

    @FXML
    void Exitpressed(ActionEvent event){
        Stage stage = (Stage) exit.getScene().getWindow();
        stage.close();

    }

}

