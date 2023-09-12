package de.podszus.controller;

import de.podszus.view.CAStage;
import de.podszus.view.PopulationsPanel;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.HBox;

public class StatePanelController {
    PopulationsPanel populationsPanel;
    HBox[] colorPickerPanels;
    public StatePanelController(CAStage caStage){
        populationsPanel = caStage.getPopulationsPanel();
        colorPickerPanels = caStage.getPopulationsPanel().getColorPickerPanels();
        for (HBox colorPickerHBox : colorPickerPanels) {
            ColorPicker cp = (ColorPicker) colorPickerHBox.getChildren().get(1);
            cp.setOnAction(e -> populationsPanel.update());
        }



    }
}
