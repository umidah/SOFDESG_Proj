package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class mainMenu {
    @FXML
    Button editButton = new Button();

    private Parent newParent;
    private Stage editStage, mainStage;
    private Scene editScene, mainScene;


    private FXMLLoader mainMenu = new FXMLLoader(getClass().getResource("mainMenu.fxml"));
    private FXMLLoader formEditor = new FXMLLoader(getClass().getResource("sample.fxml"));

    @FXML
    void changeScene() throws IOException {
        newParent = formEditor.load();
        editStage = new Stage();
        mainScene = editButton.getScene();
        mainStage = new Stage();
        mainStage.setScene(mainScene);
        mainStage.close();
        editScene = new Scene(newParent);
        editStage.setScene(editScene);
        editStage.show();
    }
}
