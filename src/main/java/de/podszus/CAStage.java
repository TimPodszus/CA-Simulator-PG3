package de.podszus;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class CAStage extends Stage {

    private final Automaton automaton;
    PopulationsPanel populationspanel;
    ToggleGroup toggleGroupRadioButtons;

    public CAStage(Automaton automaton) {
        this.automaton = automaton;


        VBox hauptbox = new VBox();
        final MenuBar menuBar = new MenuBar();

        /* Erzeugen des Ausklappbaren Menues Automat*/
        Menu menuAutomat = new Menu("_Automat");
        MenuItem itemNeu = new MenuItem("_Neu...");
        itemNeu.setAccelerator(KeyCombination.keyCombination("SHORTCUT + N"));
        itemNeu.setGraphic(new ImageView(new Image("New16.gif")));
        itemNeu.setOnAction(e -> {
            Automaton newAutomaton = new KruemelmonsterAutomaton(automaton.rows, automaton.columns, automaton.isTorus);
            CAStage caStage = new CAStage(newAutomaton);
            caStage.show();
        });
        MenuItem itemLaden = new MenuItem("_Laden...");
        itemLaden.setAccelerator(KeyCombination.keyCombination("SHORTCUT + L"));
        itemLaden.setGraphic(new ImageView(new Image("Load16.gif")));
        MenuItem itemEditor = new MenuItem("_Editor");
        itemEditor.setAccelerator(KeyCombination.keyCombination("SHORTCUT + E"));
        MenuItem itemBeenden = new MenuItem("_Beenden");
        itemBeenden.setAccelerator(KeyCombination.keyCombination("SHORTCUT + Q"));
        itemBeenden.setOnAction(e -> Platform.exit());
        menuAutomat.getItems().addAll(itemNeu, itemLaden, new SeparatorMenuItem(), itemEditor, new SeparatorMenuItem(), itemBeenden);


        /* Erzeugen des Ausklappbaren Menues Population*/
        Menu menuPopulation = new Menu("_Population");
        MenuItem itemGroesseAendern = new MenuItem("_Größe ändern");
        itemGroesseAendern.setAccelerator(KeyCombination.keyCombination("SHORTCUT + Shift + G"));
        itemGroesseAendern.setOnAction(e -> {
            PopulationChangeBox changeBox = new PopulationChangeBox(automaton);
            automaton.changeSize(changeBox.getRowsOutput(), changeBox.getColumnsOutput());
            populationspanel.paintCanvas();
        });
        MenuItem itemLoeschen = new MenuItem("_Löschen");
        itemLoeschen.setAccelerator(KeyCombination.keyCombination("SHORTCUT + Shift + L"));
        itemLoeschen.setOnAction(e -> {
            automaton.setState(0, 0, automaton.rows, automaton.columns, 0);
            populationspanel.paintCanvas();
        });
        MenuItem itemErzeugen = new MenuItem("_Erzeugen");
        itemErzeugen.setAccelerator(KeyCombination.keyCombination("SHORTCUT + Shift + Z"));
        itemErzeugen.setOnAction(e -> {
            automaton.randomPopulation();
            populationspanel.paintCanvas();
        });

        CheckMenuItem itemTorus = new CheckMenuItem("_Torus");
        itemTorus.setAccelerator(KeyCombination.keyCombination("SHORTCUT + Shift + T"));
        itemTorus.setOnAction(e -> automaton.setTorus(!automaton.isTorus));

        MenuItem itemZoomIn = new MenuItem("Zoom _In");
        itemZoomIn.setAccelerator(KeyCombination.keyCombination("SHORTCUT + Shift + I"));
        itemZoomIn.setOnAction(actionEvent -> populationspanel.zoomIn());

        MenuItem itemZoomOut = new MenuItem("Zoom _Out");
        itemZoomOut.setAccelerator(KeyCombination.keyCombination("SHORTCUT + Shift + O"));
        itemZoomOut.setOnAction(actionEvent -> populationspanel.zoomOut());


        //Submenü Speichern
        Menu submenueSpeichern = new Menu("_Speichern");
        MenuItem itemspeichernXML = new MenuItem("_XML");
        itemspeichernXML.setAccelerator(KeyCombination.keyCombination("SHORTCUT + Shift + X"));
        MenuItem itemspeichernSerialisieren = new MenuItem("_Serialisieren");
        itemspeichernSerialisieren.setAccelerator(KeyCombination.keyCombination("SHORTCUT + Shift + S"));
        submenueSpeichern.getItems().addAll(itemspeichernXML, itemspeichernSerialisieren);

        //Submenue Laden
        Menu submenueLaden = new Menu("L_aden");
        MenuItem itemladenXML = new MenuItem("_XML");
        itemladenXML.setAccelerator(KeyCombination.keyCombination("SHORTCUT + Shift + X"));
        MenuItem itemladenDeserialisieren = new MenuItem("_Serialisieren");
        itemladenDeserialisieren.setAccelerator(KeyCombination.keyCombination("SHORTCUT + Shift + S"));
        submenueLaden.getItems().addAll(itemladenXML, itemladenDeserialisieren);

        menuPopulation.getItems().addAll(itemGroesseAendern, itemLoeschen, itemErzeugen, itemTorus, new SeparatorMenuItem(), itemZoomIn, itemZoomOut, new SeparatorMenuItem(), submenueSpeichern, submenueLaden);

        /* Erzeugen des Ausklappbaren Menues Simulation*/
        Menu menuSimulation = new Menu("_Simulation");
        MenuItem itemSchritt = new MenuItem("Schri_tt");
        itemSchritt.setAccelerator(KeyCombination.keyCombination("SHORTCUT + Alt + S"));

        MenuItem itemStart = new MenuItem("_Start");
        itemStart.setAccelerator(KeyCombination.keyCombination("SHORTCUT + Alt + A"));
        itemStart.setGraphic(new ImageView(new Image("Start16.gif")));
        MenuItem itemStopp = new MenuItem("Sto_pp");
        itemStopp.setAccelerator(KeyCombination.keyCombination("SHORTCUT + Shift + O"));
        itemStopp.setGraphic(new ImageView(new Image("Stop16.gif")));
        menuSimulation.getItems().addAll(itemSchritt, itemStart, itemStopp);
        menuBar.getMenus().addAll(menuAutomat, menuPopulation, menuSimulation);


        //Toolbar
        ToolBar toolbar = new ToolBar();
        Button buttonNeuerAutomat = new Button();
        buttonNeuerAutomat.setTooltip(new Tooltip("Neuer Automat"));
        buttonNeuerAutomat.setGraphic(new ImageView(new Image("New24.gif")));
        buttonNeuerAutomat.setOnAction(e -> {
            Automaton newAutomaton = new KruemelmonsterAutomaton(automaton.rows, automaton.columns, automaton.isTorus);
            CAStage caStage = new CAStage(newAutomaton);
            caStage.show();
        });
        Button buttonAutomatLaden = new Button();
        buttonAutomatLaden.setTooltip(new Tooltip("Automat Laden"));
        buttonAutomatLaden.setGraphic(new ImageView(new Image("Load24.gif")));
        Button buttonGroesserePopulation = new Button();
        buttonGroesserePopulation.setTooltip(new Tooltip("Population vergroessern"));
        buttonGroesserePopulation.setGraphic(new ImageView(new Image("Size24.gif")));
        buttonGroesserePopulation.setOnAction(e -> {
            PopulationChangeBox changeBox = new PopulationChangeBox(automaton);
            this.automaton.changeSize(changeBox.getRowsOutput(), changeBox.getColumnsOutput());
            populationspanel.paintCanvas();
        });
        Button buttonZustandNull = new Button();
        buttonZustandNull.setTooltip(new Tooltip("Alle Zellen auf Zustand 0"));
        buttonZustandNull.setGraphic(new ImageView(new Image("Delete24.gif")));
        buttonZustandNull.setOnAction(e -> {
            this.automaton.setState(0, 0, automaton.rows, automaton.columns, 0);
            populationspanel.paintCanvas();
        });

        Button buttonZufaelligePopulation = new Button();
        buttonZufaelligePopulation.setTooltip(new Tooltip("Zufällige Population"));
        buttonZufaelligePopulation.setGraphic(new ImageView(new Image("Random24.gif")));
        buttonZufaelligePopulation.setOnAction(e -> {
            this.automaton.randomPopulation();
            populationspanel.paintCanvas();
        });
        ToggleButton buttonTorus = new ToggleButton();
        buttonTorus.setTooltip(new Tooltip("Torus"));
        buttonTorus.setGraphic(new ImageView(new Image("Torus24.gif")));
        buttonTorus.setOnAction(e -> automaton.setTorus(!automaton.isTorus));
        Button buttonDrucken = new Button();
        buttonDrucken.setTooltip(new Tooltip("Drucken"));
        buttonDrucken.setGraphic(new ImageView(new Image("Print24.gif")));
        Button buttonZoomIn = new Button();
        buttonZoomIn.setTooltip(new Tooltip("Zoom In"));
        buttonZoomIn.setGraphic(new ImageView(new Image("ZoomIn24.gif")));
        buttonZoomIn.setOnAction(actionEvent -> {
            populationspanel.zoomIn();
            buttonZoomIn.setDisable(populationspanel.getCellWidth() > 240);
        });
        Button buttonZoomOut = new Button();
        buttonZoomOut.setTooltip(new Tooltip("Zoom Out"));
        buttonZoomOut.setGraphic(new ImageView(new Image("ZoomOut24.gif")));
        buttonZoomOut.setOnAction(actionEvent -> {
            populationspanel.zoomOut();
            buttonZoomOut.setDisable(populationspanel.getCellWidth() < 4);

        });

        Button buttonStep = new Button();
        buttonStep.setTooltip(new Tooltip("Schritt"));
        buttonStep.setGraphic(new ImageView(new Image("Step24.gif")));
        Button buttonStart = new Button();
        buttonStart.setTooltip(new Tooltip("Start"));
        buttonStart.setGraphic(new ImageView(new Image("Start24.gif")));
        Button buttonStop = new Button();
        buttonStop.setTooltip(new Tooltip("Stopp"));
        buttonStop.setGraphic(new ImageView(new Image("Stop24.gif")));
        buttonStop.setOnAction(e -> Platform.exit());

        Slider sliderSchneller = new Slider(0, 100, 100);
        sliderSchneller.setShowTickLabels(true);
        sliderSchneller.setShowTickMarks(true);
        sliderSchneller.setMajorTickUnit(50);
        sliderSchneller.setMinorTickCount(1);
        toolbar.getItems().addAll(buttonNeuerAutomat, buttonAutomatLaden, new Separator(), buttonGroesserePopulation, buttonZustandNull, buttonZufaelligePopulation, buttonTorus, buttonDrucken, new Separator(), buttonZoomIn, buttonZoomOut, new Separator(), buttonStep, buttonStart, buttonStop, new Separator(), sliderSchneller);


        //innereBox die die beiden Scrollpanes für die ColorPicker VBOX und die Region beinhaltet
        HBox innereBox = new HBox(5);


        VBox zustandspanel = new VBox();

        //Anzahl der ColorPicker
        int anzahl = automaton.getNumberOfStates();
        this.toggleGroupRadioButtons = new ToggleGroup();
        HBox[] colorPickers = new HBox[anzahl];
        for (int i = 0; i < anzahl; i++) {

            RadioButton radioButton = new RadioButton(Integer.toString(i));
            radioButton.setToggleGroup(toggleGroupRadioButtons);
            if (i == 0) {
                radioButton.setSelected(true);
            }
            ColorPicker colorPicker = new ColorPicker(Color.color(Math.random(), Math.random(), Math.random()));

            HBox colorPickerBox = new HBox(radioButton, colorPicker);
            colorPickers[i] = colorPickerBox;
            zustandspanel.getChildren().add(colorPickerBox);
        }


        ScrollPane scrollPaneLinks = new ScrollPane(zustandspanel);
        scrollPaneLinks.setMinWidth(200);

        //Region, die die konkrete Simulation haust

        this.populationspanel = new PopulationsPanel(automaton, colorPickers, this);

        ScrollPane scrollPaneRechts = new ScrollPane(populationspanel);
        scrollPaneRechts.setPrefSize(1100, 600);
        scrollPaneRechts.fitToWidthProperty();
        scrollPaneRechts.fitToHeightProperty();
        scrollPaneRechts.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPaneRechts.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        innereBox.getChildren().addAll(scrollPaneLinks, scrollPaneRechts);


        VBox.setVgrow(scrollPaneRechts, Priority.ALWAYS);
        Label meldungsLabel = new Label("Herzlich Willkommen");
        meldungsLabel.setMaxHeight(30);

        hauptbox.getChildren().addAll(menuBar, toolbar, innereBox, meldungsLabel);
        this.setTitle("CAS-Simulator");
        this.setScene(new Scene(hauptbox, 1280, 720));


    }


}


