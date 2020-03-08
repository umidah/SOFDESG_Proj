package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    private FXMLLoader loader = new FXMLLoader(getClass().getResource("ECM_Layout.fxml"));
    private FXMLLoader mainMenu = new FXMLLoader(getClass().getResource("mainMenu.fxml"));

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = loader.load();
        Scene mainScene = new Scene(root);
        Controller controller = loader.getController();
        mainMenu mainMenuController = mainMenu.getController();
        controller.list.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            controller.showItemListener();
        });
        primaryStage.setTitle("ECM");
        primaryStage.setScene(mainScene);
        //primaryStage.setMaximized(true);
        //Images References:
        //Icons made by <a href="https://www.flaticon.com/authors/vectors-market" title="Vectors Market">Vectors Market</a> from <a href="https://www.flaticon.com/" title="Flaticon"> www.flaticon.com</a>
        //Icons made by <a href="https://www.flaticon.com/authors/alfredo-hernandez" title="Alfredo Hernandez">Alfredo Hernandez</a> from <a href="https://www.flaticon.com/" title="Flaticon"> www.flaticon.com</a>
        //Icons made by <a href="https://www.flaticon.com/authors/pixel-perfect" title="Pixel perfect">Pixel perfect</a> from <a href="https://www.flaticon.com/" title="Flaticon"> www.flaticon.com</a>
        //Icons made by <a href="https://www.flaticon.com/authors/freepik" title="Freepik">Freepik</a> from <a href="https://www.flaticon.com/" title="Flaticon"> www.flaticon.com</a>
        //Icons made by <a href="https://www.flaticon.com/authors/pixel-perfect" title="Pixel perfect">Pixel perfect</a> from <a href="https://www.flaticon.com/" title="Flaticon"> www.flaticon.com</a>
        //Icons made by <a href="https://www.flaticon.com/authors/smashicons" title="Smashicons">Smashicons</a> from <a href="https://www.flaticon.com/" title="Flaticon"> www.flaticon.com</a>
        primaryStage.getIcons().add(new Image("file:C:\\Users\\ambro\\Downloads\\SOFDESG_Proj-master\\SOFDESG_Proj-master\\src\\sample\\checklist.png"));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
