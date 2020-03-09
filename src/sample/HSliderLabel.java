package sample;

import javafx.scene.control.Label;
import javafx.scene.control.Slider;

class HSliderLabel extends HBoxWidgetAbstract {
    private Slider slider;
    private Label dLabel;
    private Label label;
    private Label number;
    private value value;
    private double sliderVal;

    HSliderLabel(String text, int count){
        label = new Label(text);
        dLabel = new Label("0.0");
        number = new Label(count + ".");
        slider = new Slider(0.0d, 10.0d, 0.0d);
        value = new value(0, "~");
        sliderVal = 0.0d;
        slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            sliderVal = Math.round(newValue.doubleValue()*100.0) / 100.0;
            dLabel.setText("\t" + sliderVal);
            value.setValue(Math.round((float) sliderVal));
        });
        this.getChildren().addAll(number, label, dLabel, slider);
    }


    @Override
    String getText() {
        return label.getText();
    }

    @Override
    String getNumText() {
        return "~"+label.getText();
    }

    @Override
    value getValue() {
        return value;
    }
}
