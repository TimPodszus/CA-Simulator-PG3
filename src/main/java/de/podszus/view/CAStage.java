package de.podszus.view;

import de.podszus.model.Automaton;
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

    ToggleGroup toggleGroupRadioButtons;

    private final MenuItem itemNeu;
    private final MenuItem itemBeenden;
    private final MenuItem itemChangeSize;
    private final MenuItem itemDelete;
    private final MenuItem itemErzeugen;
    private final CheckMenuItem itemTorus;
    private final MenuItem itemZoomIn;
    private final MenuItem itemZoomOut;
    private final MenuItem itemSchritt;
    private final MenuItem itemStart;
    private final MenuItem itemStopp;
    private final Button buttonNeuerAutomat;
    private final Button buttonBiggerPopulation;
    private final Button buttonZustandNull;
    private final Button buttonRandomPopulation;
    private final ToggleButton buttonTorus;
    private final Button buttonZoomIn;
    private final Button buttonZoomOut;
    private final Button buttonStep;
    private final Button buttonStart;
    private final Button buttonStop;
    private final Slider sliderSchneller;
    private final PopulationsPanel populationsPanel;


    public CAStage(Automaton automaton) {


        VBox hauptbox = new VBox();
        final MenuBar menuBar = new MenuBar();

        /* Erzeugen des Ausklappbaren Menus Automat*/
        Menu menuAutomat = new Menu("_Automat");
        itemNeu = new MenuItem("_Neu...");
        itemNeu.setAccelerator(KeyCombination.keyCombination("SHORTCUT + N"));
        itemNeu.setGraphic(new ImageView(new Image("New16.gif")));
        MenuItem itemLaden = new MenuItem("_Laden...");
        itemLaden.setAccelerator(KeyCombination.keyCombination("SHORTCUT + L"));
        itemLaden.setGraphic(new ImageView(new Image("Load16.gif")));
        itemLaden.setDisable(true);
        MenuItem itemEditor = new MenuItem("_Editor");
        itemEditor.setAccelerator(KeyCombination.keyCombination("SHORTCUT + E"));
        itemEditor.setDisable(true);
        itemBeenden = new MenuItem("_Beenden");
        itemBeenden.setAccelerator(KeyCombination.keyCombination("SHORTCUT + Q"));

        menuAutomat.getItems().addAll(itemNeu, itemLaden, new SeparatorMenuItem(), itemEditor, new SeparatorMenuItem(), itemBeenden);


        /* Erzeugen des Ausklappbaren Menus Population*/
        Menu menuPopulation = new Menu("_Population");
        itemChangeSize = new MenuItem("_Größe ändern");
        itemChangeSize.setAccelerator(KeyCombination.keyCombination("SHORTCUT + Shift + G"));
        itemDelete = new MenuItem("_Löschen");
        itemDelete.setAccelerator(KeyCombination.keyCombination("SHORTCUT + Shift + L"));
        itemErzeugen = new MenuItem("_Erzeugen");
        itemErzeugen.setAccelerator(KeyCombination.keyCombination("SHORTCUT + Shift + Z"));
        buttonTorus = new ToggleButton();
        itemTorus = new CheckMenuItem("_Torus");
        itemTorus.setAccelerator(KeyCombination.keyCombination("SHORTCUT + Shift + T"));
        if(automaton.isTorus()){
            itemTorus.isSelected();
        }
        itemZoomIn = new MenuItem("Zoom _In");
        itemZoomIn.setAccelerator(KeyCombination.keyCombination("SHORTCUT + Shift + I"));
        itemZoomOut = new MenuItem("Zoom _Out");
        itemZoomOut.setAccelerator(KeyCombination.keyCombination("SHORTCUT + Shift + O"));


        //Submenü Speichern
        Menu submenuSpeichern = new Menu("_Speichern");
        MenuItem itemSpeichernXML = new MenuItem("_XML");
        itemSpeichernXML.setAccelerator(KeyCombination.keyCombination("SHORTCUT + Shift + X"));
        MenuItem itemSpeichernSerialisieren = new MenuItem("_Serialisieren");
        itemSpeichernSerialisieren.setAccelerator(KeyCombination.keyCombination("SHORTCUT + Shift + S"));
        submenuSpeichern.getItems().addAll(itemSpeichernXML, itemSpeichernSerialisieren);
        submenuSpeichern.setDisable(true);
        //Submenu Laden
        Menu submenuLaden = new Menu("L_aden");
        MenuItem itemLadenXML = new MenuItem("_XML");
        itemLadenXML.setAccelerator(KeyCombination.keyCombination("SHORTCUT + Shift + X"));
        MenuItem itemLadenDeserialize = new MenuItem("_Serialisieren");
        itemLadenDeserialize.setAccelerator(KeyCombination.keyCombination("SHORTCUT + Shift + S"));
        submenuLaden.getItems().addAll(itemLadenXML, itemLadenDeserialize);
        menuPopulation.getItems().addAll(itemChangeSize, itemDelete, itemErzeugen, itemTorus, new SeparatorMenuItem(), itemZoomIn, itemZoomOut, new SeparatorMenuItem(), submenuSpeichern, submenuLaden);
        submenuLaden.setDisable(true);
        /* Erzeugen des Ausklappbaren Menus Simulation*/
        Menu menuSimulation = new Menu("_Simulation");
        itemSchritt = new MenuItem("Schri_tt");
        itemSchritt.setAccelerator(KeyCombination.keyCombination("SHORTCUT + Alt + S"));
        itemStart = new MenuItem("_Start");
        itemStart.setAccelerator(KeyCombination.keyCombination("SHORTCUT + Alt + A"));
        itemStart.setGraphic(new ImageView(new Image("Start16.gif")));
        itemStopp = new MenuItem("Sto_pp");
        itemStopp.setAccelerator(KeyCombination.keyCombination("SHORTCUT + Shift + O"));
        itemStopp.setGraphic(new ImageView(new Image("Stop16.gif")));
        itemStopp.setDisable(true);
        menuSimulation.getItems().addAll(itemSchritt, itemStart, itemStopp);
        menuBar.getMenus().addAll(menuAutomat, menuPopulation, menuSimulation);


        //Toolbar
        ToolBar toolbar = new ToolBar();
        buttonNeuerAutomat = new Button();
        buttonNeuerAutomat.setTooltip(new Tooltip("Neuer Automat"));
        buttonNeuerAutomat.setGraphic(new ImageView(new Image("New24.gif")));
        Button buttonAutomatLaden = new Button();
        buttonAutomatLaden.setTooltip(new Tooltip("Automat Laden"));
        buttonAutomatLaden.setGraphic(new ImageView(new Image("Load24.gif")));
        buttonAutomatLaden.setDisable(true);
        buttonBiggerPopulation = new Button();
        buttonBiggerPopulation.setTooltip(new Tooltip("Population vergroessern"));
        buttonBiggerPopulation.setGraphic(new ImageView(new Image("Size24.gif")));
        buttonZustandNull = new Button();
        buttonZustandNull.setTooltip(new Tooltip("Alle Zellen auf Zustand 0"));
        buttonZustandNull.setGraphic(new ImageView(new Image("Delete24.gif")));
        buttonRandomPopulation = new Button();
        buttonRandomPopulation.setTooltip(new Tooltip("Zufällige Population"));
        buttonRandomPopulation.setGraphic(new ImageView(new Image("Random24.gif")));
        buttonTorus.setTooltip(new Tooltip("Torus"));
        buttonTorus.setGraphic(new ImageView(new Image("Torus24.gif")));
        if(automaton.isTorus()){
            buttonTorus.isSelected();
        }
        Button buttonPrint = new Button();
        buttonPrint.setTooltip(new Tooltip("Drucken"));
        buttonPrint.setGraphic(new ImageView(new Image("Print24.gif")));
        buttonPrint.setDisable(true);
        buttonZoomIn = new Button();
        buttonZoomIn.setTooltip(new Tooltip("Zoom In"));
        buttonZoomIn.setGraphic(new ImageView(new Image("ZoomIn24.gif")));
        buttonZoomOut = new Button();
        buttonZoomOut.setTooltip(new Tooltip("Zoom Out"));
        buttonZoomOut.setGraphic(new ImageView(new Image("ZoomOut24.gif")));
        buttonStep = new Button();
        buttonStep.setTooltip(new Tooltip("Schritt"));
        buttonStep.setGraphic(new ImageView(new Image("Step24.gif")));
        buttonStart = new Button();
        buttonStart.setTooltip(new Tooltip("Start"));
        buttonStart.setGraphic(new ImageView(new Image("Start24.gif")));
        buttonStop = new Button();
        buttonStop.setTooltip(new Tooltip("Stopp"));
        buttonStop.setGraphic(new ImageView(new Image("Stop24.gif")));
        buttonStop.setDisable(true);
        sliderSchneller = new Slider(50, 1000, 300);
        sliderSchneller.setShowTickLabels(true);
        sliderSchneller.setShowTickMarks(true);
        sliderSchneller.setMajorTickUnit(100);
        sliderSchneller.setMinorTickCount(50);
        toolbar.getItems().addAll(buttonNeuerAutomat, buttonAutomatLaden, new Separator(), buttonBiggerPopulation, buttonZustandNull, buttonRandomPopulation, buttonTorus, buttonPrint, new Separator(), buttonZoomIn, buttonZoomOut, new Separator(), buttonStep, buttonStart, buttonStop, new Separator(), sliderSchneller);


        //innereBox die beiden ScrollPanes für die ColorPicker VBOX und die Region beinhaltet
        HBox innereBox = new HBox(5);


        VBox statePanel = new VBox();

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
            statePanel.getChildren().add(colorPickerBox);
        }


        ScrollPane scrollPaneLinks = new ScrollPane(statePanel);
        scrollPaneLinks.setMinWidth(200);

        //Region, die die konkrete Simulation haust

        this.populationsPanel = new PopulationsPanel(automaton, colorPickers, this);

        ScrollPane scrollPaneRechts = new ScrollPane(populationsPanel);
        scrollPaneRechts.setPrefSize(1300, 750);
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



    public MenuItem getItemNeu() {
        return itemNeu;
    }

    public MenuItem getItemBeenden() {
        return itemBeenden;
    }

    public MenuItem getItemChangeSize() {
        return itemChangeSize;
    }

    public MenuItem getItemDelete() {
        return itemDelete;
    }

    public MenuItem getItemErzeugen() {
        return itemErzeugen;
    }

    public CheckMenuItem getItemTorus() {
        return itemTorus;
    }

    public MenuItem getItemZoomIn() {
        return itemZoomIn;
    }

    public MenuItem getItemZoomOut() {
        return itemZoomOut;
    }

    public MenuItem getItemSchritt() {
        return itemSchritt;
    }

    public MenuItem getItemStart() {
        return itemStart;
    }

    public MenuItem getItemStopp() {
        return itemStopp;
    }

    public Button getButtonNeuerAutomat() {
        return buttonNeuerAutomat;
    }

    public Button getButtonBiggerPopulation() {
        return buttonBiggerPopulation;
    }

    public Button getButtonZustandNull() {
        return buttonZustandNull;
    }

    public Button getButtonRandomPopulation() {return buttonRandomPopulation;}

    public ToggleButton getButtonTorus() {
        return buttonTorus;
    }

    public Button getButtonZoomIn() {
        return buttonZoomIn;
    }

    public Button getButtonZoomOut() {
        return buttonZoomOut;
    }

    public Button getButtonStep() {
        return buttonStep;
    }

    public Button getButtonStart() {
        return buttonStart;
    }

    public Button getButtonStop() {
        return buttonStop;
    }

    public Slider getSliderSchneller() {
        return sliderSchneller;
    }

    public PopulationsPanel getPopulationsPanel(){return populationsPanel;}

}


