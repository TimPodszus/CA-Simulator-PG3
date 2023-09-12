package de.podszus.controller;

import de.podszus.model.Automaton;
import de.podszus.view.CAStage;
import de.podszus.view.PopulationsPanel;
import javafx.scene.control.RadioButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class PopulationPanelController {
    PopulationsPanel populationsPanel;
    CAStage stage;
    Automaton automaton;
    double startX;
    double startY;
    public PopulationPanelController ( Automaton automaton, CAStage stage) {
            this.populationsPanel = stage.getPopulationsPanel();
            this.automaton = automaton;
            this.stage = stage;
            populationsPanel.canvas.addEventHandler(MouseEvent.MOUSE_PRESSED,this::changeCell);
            populationsPanel.canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED,this::changeCellArea);
        stage.getButtonZoomIn().setOnAction(actionEvent -> {
            zoomIn();
            checkZoomButtonStage();

        });
        stage.getItemZoomIn().setOnAction(actionEvent -> {
            zoomIn();
            checkZoomButtonStage();

        });
        stage.getItemZoomOut().setOnAction(actionEvent -> {
            zoomOut();
            checkZoomButtonStage();

        });
        stage.getButtonZoomOut().setOnAction(actionEvent -> {
            zoomOut();
            checkZoomButtonStage();
        });
    }

    void checkZoomButtonStage(){
        if (populationsPanel.getCellWidth() > 50){
            stage.getItemZoomIn().setDisable(true);
            stage.getButtonZoomIn().setDisable(true);
        }
        if (populationsPanel.getCellWidth() < 8){
            stage.getItemZoomOut().setDisable(true);
            stage.getButtonZoomOut().setDisable(true);
        }
        if (populationsPanel.getCellWidth() > 8 ){
            stage.getItemZoomOut().setDisable(false);
            stage.getButtonZoomOut().setDisable(false);
        }
        if (populationsPanel.getCellWidth() < 50){
            stage.getItemZoomIn().setDisable(false);
            stage.getButtonZoomIn().setDisable(false);
        }
    }


    void changeCell(MouseEvent event) {
        int checkedRadioButton = getCurrentRadioButton();

        double x = event.getX();
        double y = event.getY();
        startX = x;
        startY = y;
        if (x < populationsPanel.getLineWidth() || y < populationsPanel.getLineWidth() || x > populationsPanel.getLineWidth() + automaton.getNumberOfRows() * populationsPanel.getCellWidth() || y > populationsPanel.getLineWidth() + automaton.getNumberOfColumns() * populationsPanel.getCellWidth()) {
            return;
        }
        int column = (int) ((x - populationsPanel.getLineWidth()) / (populationsPanel.getCellWidth() + populationsPanel.getLineWidth()));
        int row = (int) ((y - populationsPanel.getLineWidth()) / (populationsPanel.getCellWidth() + populationsPanel.getLineWidth()));

        automaton.getCell(row, column).setState(checkedRadioButton);
        populationsPanel.update();



    }

    void changeCellArea(MouseEvent event) {
        // Umsetzung dieser Methode mit Hilfestellung von Niels-Thorben Tax

        int checkedRadioButton = getCurrentRadioButton();


        double endX = event.getX();
        double endY = event.getY();
        if (endX < populationsPanel.getLineWidth() || endY < populationsPanel.getLineWidth() || endX > populationsPanel.getLineWidth() + automaton.getNumberOfRows() * populationsPanel.getCellWidth() || endY > populationsPanel.getLineWidth() + automaton.getNumberOfColumns() * populationsPanel.getCellWidth()) {
            return;
        }
        if (endX > populationsPanel.getLineWidth() + automaton.getNumberOfColumns() * populationsPanel.getCellWidth()) {
            endX = populationsPanel.getLineWidth() + automaton.getNumberOfColumns() * populationsPanel.getCellWidth() - 1;

        }
        if (endY > populationsPanel.getLineWidth() + automaton.getNumberOfRows() * populationsPanel.getCellWidth()) {
            endY = populationsPanel.getLineWidth() + automaton.getNumberOfRows() * populationsPanel.getCellWidth() - 1;
        }

        int startcolumn = (int) ((startX - populationsPanel.getLineWidth()) / (populationsPanel.getCellWidth() + populationsPanel.getLineWidth()));
        int startrow = (int) ((startY - populationsPanel.getLineWidth()) / (populationsPanel.getCellWidth() + populationsPanel.getLineWidth()));
        int endcolumn = (int) ((endX - populationsPanel.getLineWidth()) / (populationsPanel.getCellWidth() + populationsPanel.getLineWidth()));
        int endrow = (int) ((endY - populationsPanel.getLineWidth()) / (populationsPanel.getCellWidth() + populationsPanel.getLineWidth()));
        if (endcolumn < startcolumn) {
            int x = startcolumn;
            startcolumn = endcolumn;
            endcolumn = x;
        }
        if (endrow < startrow) {
            int x = startrow;
            startrow = endrow;
            endrow = x;
        }
        automaton.setState(startrow, startcolumn, endrow, endcolumn, checkedRadioButton);

        populationsPanel.update();

    }
    int getCurrentRadioButton(){
        int checkedRadioButton = 0;
        for (int i = 0; i < populationsPanel.getColorPickerPanels().length; i++) {
            HBox currentRadioButtonHBox = populationsPanel.getColorPickerPanels()[i];
            RadioButton currentRadioButton = (RadioButton) currentRadioButtonHBox.getChildren().get(0);
            if (currentRadioButton.isSelected()) {
               checkedRadioButton = i;

            }
        }
        return checkedRadioButton;
    }

    void zoomIn() {

        this.populationsPanel.setCellWidth(populationsPanel.getCellWidth() * 1.5)  ;
        this.populationsPanel.setLineWidth(populationsPanel.getLineWidth() * 1.5) ;
        populationsPanel.update();

    }
    void zoomOut() {
        this.populationsPanel.setCellWidth(populationsPanel.getCellWidth() * 0.75);
        this.populationsPanel.setLineWidth(populationsPanel.getLineWidth() *0.75);

        populationsPanel.update();
    }


}

