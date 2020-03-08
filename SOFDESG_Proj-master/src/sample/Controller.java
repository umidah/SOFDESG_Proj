package sample;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


public class Controller {


    //FXML Objects
    @FXML
    TextField nameBox = new TextField();
    @FXML
    Button add = new Button();
    @FXML
    Button delButton = new Button();
    @FXML
    Button saveButton = new Button();
    @FXML
    AnchorPane displayPane = new AnchorPane();
    @FXML
    ListView<String> list = new ListView<>();
    @FXML
    Button loadButton = new Button();
    @FXML
    Button writeButton = new Button();
    @FXML
    Text Add_Text = new Text();
    @FXML
    Text Delete_Text = new Text();
    @FXML
    Text Save_Text = new Text();
    @FXML
    Text Load_Text = new Text();
    @FXML
    Text Write_Text = new Text();
    @FXML
    Menu adding_help = new Menu();
    @FXML
    Menu deleting_help = new Menu();
    @FXML
    Menu saving_help = new Menu();
    @FXML
    Menu loading_help = new Menu();
    @FXML
    Menu writing_help = new Menu();


    Stage Help_Window = new Stage();

    //not-FXML Objects and Properties
    private LinkedList<checkItem> items = new LinkedList<>();
    private int count = 0;
    //FXML methods
    @FXML
    void addItem(){
        if(!nameBox.getText().equals("")){
            list.getItems().add(nameBox.getText());
            items.add(new checkItem(nameBox.getText()));
            count++;
            nameBox.clear();
        }
    }

    @FXML
    void deleteItem(){
        int ind = list.getSelectionModel().getSelectedIndex();
        if (ind > -1) items.remove(ind);
        updateItems();
    }

    @FXML
    void saveItem(){
        int ind = list.getSelectionModel().getSelectedIndex();
        if (ind > -1) items.get(ind).saveConfigToFile();
    }

    @FXML
    private void loadFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("config file (*.cbcfg)", "*.cbcfg"));
        File file = fileChooser.showOpenDialog(loadButton.getScene().getWindow());
        List<String> lines = new ArrayList<>();
        String title;
        try{
            Scanner fileReader = new Scanner(file);
            title = fileReader.nextLine();
            System.out.println(title);
            while(fileReader.hasNextLine()){
                String debug = fileReader.nextLine();
                lines.add(debug);
                System.out.println(debug);
            }
            checkItem item = new checkItem(title);
            for(String s : lines) {
                String substr;
                String indicator = item.types[0];
                substr = s.substring(1);
                System.out.println(s.substring(0, 1));
                switch (s.substring(0, 1)) {
                    case "#":
                        indicator = item.types[0];
                        break;
                    case "~":
                        indicator = item.types[1];
                        break;
                }
                System.out.println(indicator);
                System.out.println(substr);
                item.addItem(substr, indicator);
            }
            items.add(item);
            updateItems();
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }

    @FXML
    void writeToFile(){
        int ind = list.getSelectionModel().getSelectedIndex();
        String filename = list.getSelectionModel().getSelectedItem() + ".csv";
        try{
            if (ind > -1)
                items.get(ind).outputValues(filename);
        }
        catch (ArrayIndexOutOfBoundsException | IOException e){
            e.printStackTrace();
        }


    }

    void showItemListener(){
        displayPane.getChildren().clear();
        int ind = list.getSelectionModel().getSelectedIndex();
        try {
            if (ind > -1)
            displayPane.getChildren().add(items.get(ind));
        }
        catch (ArrayIndexOutOfBoundsException e){
            System.out.println("ERROR!");
        }
    }

    private void updateItems(){
        list.getItems().clear();
        for(checkItem x : items){
            list.getItems().add(x.getName());
        }
    }

    @FXML
    void open_Help() throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("help.fxml"));
        Help_Window.setTitle("About");
        Help_Window.setScene(new Scene(root));
        //Icons made by <a href="https://www.flaticon.com/authors/prettycons" title="prettycons">prettycons</a> from <a href="https://www.flaticon.com/" title="Flaticon"> www.flaticon.com</a>
        Help_Window.getIcons().add(new Image("file:C:\\Users\\ambro\\Downloads\\SOFDESG_Proj-master\\SOFDESG_Proj-master\\src\\sample\\info.png"));
        Help_Window.show();
    }

    @FXML
    void add_Help(){
        Add_Text.setVisible(true);
        Delete_Text.setVisible(false);
        Save_Text.setVisible(false);
        Load_Text.setVisible(false);
        Write_Text.setVisible(false);
    }

    @FXML
    void delete_Help(){
        Add_Text.setVisible(false);
        Delete_Text.setVisible(true);
        Save_Text.setVisible(false);
        Load_Text.setVisible(false);
        Write_Text.setVisible(false);
    }

    @FXML
    void save_Help(){
        Add_Text.setVisible(false);
        Delete_Text.setVisible(false);
        Save_Text.setVisible(true);
        Load_Text.setVisible(false);
        Write_Text.setVisible(false);
    }

    @FXML
    void load_Help(){
        Add_Text.setVisible(false);
        Delete_Text.setVisible(false);
        Save_Text.setVisible(false);
        Load_Text.setVisible(true);
        Write_Text.setVisible(false);
    }

    @FXML
    void write_Help(){
        Add_Text.setVisible(false);
        Delete_Text.setVisible(false);
        Save_Text.setVisible(false);
        Load_Text.setVisible(false);
        Write_Text.setVisible(true);
    }


    @FXML
    void close_Help(){
        Write_Text.getScene().getWindow().hide();
    }
}
