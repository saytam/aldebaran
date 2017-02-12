package aldebaran.ui;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.util.StringConverter;

import java.util.function.Supplier;

public class RollWidget {
    private int x;
    private int y;
    private Supplier<Integer> onRollCallback;

    private IntegerProperty rollResultProperty = new SimpleIntegerProperty();

    public RollWidget(int x, int y, Supplier<Integer> onRollCallback) {
        this.x = x;
        this.y = y;
        this.onRollCallback = onRollCallback;
    }

    public GridPane show() {
        TextField rollResultField = new TextField();
        rollResultField.textProperty().bindBidirectional(rollResultProperty, new StringConverter<Number>() {
            @Override
            public String toString(Number object) {
                return object.toString();
            }

            @Override
            public Number fromString(String string) {
                return null;
            }
        });
        rollResultField.setText("");
        rollResultField.setDisable(true);

        Button rollButton = new Button("Roll");
        rollButton.setOnMouseClicked(event -> roll());

        GridPane rollingDisplay = new GridPane();
        rollingDisplay.addRow(0, rollButton, rollResultField);
        rollingDisplay.setLayoutX(x);
        rollingDisplay.setLayoutY(y);
        return rollingDisplay;
    }

    private void roll() {
        Integer result = onRollCallback.get();
        if (result != null){
            rollResultProperty.setValue(result);
        }
    }
}
