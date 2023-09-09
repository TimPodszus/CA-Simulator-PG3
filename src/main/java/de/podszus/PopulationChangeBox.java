package de.podszus;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Pair;

public class PopulationChangeBox extends Stage {

    int rowsOutput;
    int columnsOutput;
    boolean abbruch;
    //Klasse für das Dialogfenster zum Größe ändern. Nach:https://code.makery.ch/blog/javafx-dialogs-official/
    public PopulationChangeBox(Automaton automaton) {

        Dialog<Pair<Integer,Integer>> dialog = new Dialog<>();
        dialog.setTitle("Größe ändern");
        dialog.setHeaderText("Größe der Population ändern (2-200)");


        ButtonType bestaetigungsButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(bestaetigungsButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        Spinner<Integer> rows = new Spinner<>(2,200,automaton.getNumberOfRows());
        rows.setPromptText("Reihen");
        Spinner<Integer> columns = new Spinner<>(2,200,automaton.getNumberOfColumns());
        columns.setPromptText("Spalten");

        grid.add(new Label("Reihen:"), 0, 0);
        grid.add(rows, 1, 0);
        grid.add(new Label("Spalten:"), 0, 1);
        grid.add(columns, 1, 1);

        Node bestaetigunsbutton = dialog.getDialogPane().lookupButton(bestaetigungsButtonType);


        rows.valueProperty().addListener((observable, oldValue, newValue) -> {
            boolean validInput = (newValue >= 2 && newValue <= 200 && columns.getValue() >= 2 && columns.getValue() <= 200);
            bestaetigunsbutton.setDisable(!validInput);
        });

        columns.valueProperty().addListener((observable, oldValue, newValue) -> {
            boolean validInput = (rows.getValue() >= 2 && rows.getValue() <= 200 && newValue >= 2 && newValue <= 200);
            bestaetigunsbutton.setDisable(!validInput);
        });

        dialog.getDialogPane().setContent(grid);
        Platform.runLater(rows::requestFocus);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == bestaetigungsButtonType) {
                return new Pair<>(rows.getValue(), rows.getValue());
            }
             abbruch = true;
            rowsOutput = automaton.rows;
            columnsOutput = automaton.columns;
            return null;
        });

        dialog.showAndWait().ifPresent(result -> {
            rowsOutput = result.getKey();
            columnsOutput = result.getValue();
        });


        if (!abbruch){
        this.rowsOutput = rows.getValue();
        this.columnsOutput = columns.getValue();}

    }

    public int getRowsOutput() {
        return rowsOutput;
    }

    public int getColumnsOutput() {
        return columnsOutput;
    }
}



