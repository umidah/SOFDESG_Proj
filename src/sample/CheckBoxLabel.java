package sample;

import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

class CheckBoxLabel extends HBoxWidgetAbstract {
    private CheckBox check;
    private Label label;
    private Label number;
    public value value;

    CheckBoxLabel(String text, int count){
        check = new CheckBox();
        label = new Label(text);
        number = new Label(count + ". ");
        this.getChildren().addAll(number, label, check);
        value = new value(0, "#");
        check.selectedProperty().addListener((observable, oldValue, newValue) -> {
            value.setValue(newValue ? 1 : 0);
        });
    }

    @Override
    String getText(){
        return label.getText();
    }

    @Override
    String getNumText(){
        return "#"+label.getText();
    }

    @Override
    value getValue() {
        return value;
    }
}
