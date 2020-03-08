package sample;

import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import java.util.ArrayList;
import java.util.List;

public class RadioButtonLabel extends HBoxWidgetAbstract {
    private Label label;
    private Label number;
    private List<RadioButton> radioButtons;
    private ToggleGroup group;
    public value value;
    private static final int max = 5;

    RadioButtonLabel(String label, int number){
        this.label = new Label(label + ".\t");
        this.number = new Label(number + ". ");
        radioButtons = new ArrayList<>();
        value = new value(0, "@");
        group = new ToggleGroup();
        for (int x = 0; x < max; x++){
            RadioButton tempRad = new RadioButton(Integer.toString(x + 1));
            tempRad.setToggleGroup(group);
            radioButtons.add(tempRad);
        }
        this.getChildren().addAll(this.number, this.label);
        this.getChildren().addAll(this.radioButtons);
        group.selectedToggleProperty().addListener((observable, oldValue, newValue) ->{
            RadioButton selected = (RadioButton) newValue;
            value.setValue(Integer.parseInt(selected.getText()));
        });
    }

    @Override
    String getText() {
        return this.label.getText();
    }

    @Override
    String getNumText() {
        return "@"+this.label.getText();
    }

    @Override
    value getValue() {
        return value;
    }
}
