package sample;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

import com.opencsv.CSVWriter;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class Controller {
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
    private LinkedList<checkItem> items = new LinkedList<>();
    private int count = 0;

    public Controller() {
    }

    @FXML
    void addItem() {
        if (!this.nameBox.getText().equals("")) {
            this.list.getItems().add(this.nameBox.getText());
            this.items.add(new checkItem(this.nameBox.getText()));
            ++this.count;
            this.nameBox.clear();
        }

    }

    @FXML
    void deleteItem() {
        int ind = this.list.getSelectionModel().getSelectedIndex();
        if (ind > -1) {
            this.items.remove(ind);
        }

        this.updateItems();
    }

    @FXML
    void saveItem() {
        int ind = this.list.getSelectionModel().getSelectedIndex();
        if (ind > -1) {
            this.items.get(ind).saveConfigToFile();
        }

    }

    @FXML
    private void loadFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");
        fileChooser.getExtensionFilters().add(new ExtensionFilter("config file (*.cbcfg)", "*.cbcfg"));
        File file = fileChooser.showOpenDialog(this.loadButton.getScene().getWindow());
        ArrayList<String> lines = new ArrayList<>();

        try {
            Scanner fileReader = new Scanner(file);
            String title = fileReader.nextLine();
            System.out.println(title);

            while(fileReader.hasNextLine()) {
                String debug = fileReader.nextLine();
                lines.add(debug);
                System.out.println(debug);
            }

            checkItem item = new checkItem(title);
            Iterator<String> var7 = lines.iterator();

            while(var7.hasNext()) {
                String s = var7.next();
                String indicator = item.types[0];
                String substr = s.substring(1);
                System.out.println(s.substring(0, 1));
                String var11 = s.substring(0, 1);
                byte var12 = -1;
                switch(var11.hashCode()) {
                    case 35:
                        if (var11.equals("#")) {
                            var12 = 0;
                        }
                        break;
                    case 126:
                        if (var11.equals("~")) {
                            var12 = 1;
                        }
                }

                switch(var12) {
                    case 0:
                        indicator = item.types[0];
                        break;
                    case 1:
                        indicator = item.types[1];
                }

                System.out.println(indicator);
                System.out.println(substr);
                item.addItem(substr, indicator);
            }

            this.items.add(item);
            this.updateItems();
        } catch (FileNotFoundException var13) {
            var13.printStackTrace();
        }

    }

    @FXML
    void writeToFile() {
        int ind = this.list.getSelectionModel().getSelectedIndex();
        String filename = this.list.getSelectionModel().getSelectedItem() + ".csv";
        try {
            ArrayList<HBoxWidgetAbstract> temp = this.items.get(ind).boxes;
            int n = temp.size();
            int i = 0;
            String[] headers = new String[n];
            for(HBoxWidgetAbstract hB: temp){
                headers[i] = hB.getText();
            }
            CSVWriter writer = new CSVWriter(new FileWriter(filename));
            writer.writeNext(headers);
            if (ind > -1) {
                this.items.get(ind).outputValues(filename);
            }
        } catch (IOException | ArrayIndexOutOfBoundsException var4) {
            var4.printStackTrace();
        }

    }

    void showItemListener() {
        this.displayPane.getChildren().clear();
        int ind = this.list.getSelectionModel().getSelectedIndex();

        try {
            if (ind > -1) {
                this.displayPane.getChildren().add(this.items.get(ind));
            }
        } catch (ArrayIndexOutOfBoundsException var3) {
            System.out.println("ERROR!");
        }

    }

    private void updateItems() {
        this.list.getItems().clear();
        Iterator<checkItem> var1 = this.items.iterator();

        while(var1.hasNext()) {
            checkItem x = var1.next();
            this.list.getItems().add(x.getName());
        }

    }

    @FXML
    void open_Help() throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("help.fxml"));
        this.Help_Window.setTitle("About");
        this.Help_Window.setScene(new Scene(root));
        this.Help_Window.getIcons().add(new Image("file:C:\\Users\\ambro\\Downloads\\SOFDESG_Proj-master\\SOFDESG_Proj-master\\src\\sample\\info.png"));
        this.Help_Window.show();
    }

    @FXML
    void add_Help() {
        this.Add_Text.setVisible(true);
        this.Delete_Text.setVisible(false);
        this.Save_Text.setVisible(false);
        this.Load_Text.setVisible(false);
        this.Write_Text.setVisible(false);
    }

    @FXML
    void delete_Help() {
        this.Add_Text.setVisible(false);
        this.Delete_Text.setVisible(true);
        this.Save_Text.setVisible(false);
        this.Load_Text.setVisible(false);
        this.Write_Text.setVisible(false);
    }

    @FXML
    void save_Help() {
        this.Add_Text.setVisible(false);
        this.Delete_Text.setVisible(false);
        this.Save_Text.setVisible(true);
        this.Load_Text.setVisible(false);
        this.Write_Text.setVisible(false);
    }

    @FXML
    void load_Help() {
        this.Add_Text.setVisible(false);
        this.Delete_Text.setVisible(false);
        this.Save_Text.setVisible(false);
        this.Load_Text.setVisible(true);
        this.Write_Text.setVisible(false);
    }

    @FXML
    void write_Help() {
        this.Add_Text.setVisible(false);
        this.Delete_Text.setVisible(false);
        this.Save_Text.setVisible(false);
        this.Load_Text.setVisible(false);
        this.Write_Text.setVisible(true);
    }

    @FXML
    void close_Help() {
        this.Write_Text.getScene().getWindow().hide();
    }
}
