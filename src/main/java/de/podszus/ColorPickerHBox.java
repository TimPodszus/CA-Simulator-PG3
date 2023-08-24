package de.podszus;

import javafx.scene.control.ColorPicker;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;


/**Klasse, die in die Übergebene VBOX eine HBox einfügt, in der ein RadioButton und ein ColorPicker ist.
 * @param vBox  Übergebene VBox, in die die HBox eingefügt wird.
 * @param instanziierungen Übergeben wird eine Anzahl, wie viele HBoxen eingefügt werden sollen. Dieses ist u.a. notwendig für die Beschriftung der Radiobuttons
 */
public class ColorPickerHBox {
        ColorPickerHBox(VBox vBox, int instanziierungen) {
            String nummer = Integer.toString(instanziierungen);
            HBox hBox = new HBox(10);
            RadioButton radioButton = new RadioButton(nummer);
            ColorPicker colorPicker = new ColorPicker(Color.color(Math.random(), Math.random(), Math.random()));
            hBox.getChildren().addAll(radioButton, colorPicker);
            vBox.getChildren().add(hBox);
        }
}
