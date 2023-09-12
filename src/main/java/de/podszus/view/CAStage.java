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
    private final MenuItem itemLaden;
    private final MenuItem itemEditor;
    private final MenuItem itemBeenden;
    private final MenuItem itemGroesseAendern;
    private final MenuItem itemLoeschen;
    private final MenuItem itemErzeugen;
    private final CheckMenuItem itemTorus;
    private final MenuItem itemZoomIn;
    private final MenuItem itemZoomOut;
    private final MenuItem itemspeichernXML;
    private final MenuItem itemspeichernSerialisieren;
    private final MenuItem itemladenXML;
    private final MenuItem itemladenDeserialisieren;
    private final MenuItem itemSchritt;
    private final MenuItem itemStart;
    private final MenuItem itemStopp;
    private final Button buttonNeuerAutomat;
    private final Button buttonAutomatLaden;
    private final Button buttonGroesserePopulation;
    private final Button buttonZustandNull;
    private final Button buttonZufaelligePopulation;
    private final ToggleButton buttonTorus;
    private final Button buttonDrucken;
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

        /* Erzeugen des Ausklappbaren Menues Automat*/
        Menu menuAutomat = new Menu("_Automat");
        itemNeu = new MenuItem("_Neu...");
        itemNeu.setAccelerator(KeyCombination.keyCombination("SHORTCUT + N"));
        itemNeu.setGraphic(new ImageView(new Image("New16.gif")));
        itemLaden = new MenuItem("_Laden...");
        itemLaden.setAccelerator(KeyCombination.keyCombination("SHORTCUT + L"));
        itemLaden.setGraphic(new ImageView(new Image("Load16.gif")));
        itemEditor = new MenuItem("_Editor");
        itemEditor.setAccelerator(KeyCombination.keyCombination("SHORTCUT + E"));
        itemBeenden = new MenuItem("_Beenden");
        itemBeenden.setAccelerator(KeyCombination.keyCombination("SHORTCUT + Q"));

        menuAutomat.getItems().addAll(itemNeu, itemLaden, new SeparatorMenuItem(), itemEditor, new SeparatorMenuItem(), itemBeenden);


        /* Erzeugen des Ausklappbaren Menues Population*/
        Menu menuPopulation = new Menu("_Population");
        itemGroesseAendern = new MenuItem("_Größe ändern");
        itemGroesseAendern.setAccelerator(KeyCombination.keyCombination("SHORTCUT + Shift + G"));
        itemLoeschen = new MenuItem("_Löschen");
        itemLoeschen.setAccelerator(KeyCombination.keyCombination("SHORTCUT + Shift + L"));
        itemErzeugen = new MenuItem("_Erzeugen");
        itemErzeugen.setAccelerator(KeyCombination.keyCombination("SHORTCUT + Shift + Z"));
        buttonTorus = new ToggleButton();
        itemTorus = new CheckMenuItem("_Torus");
        itemTorus.setAccelerator(KeyCombination.keyCombination("SHORTCUT + Shift + T"));
        itemZoomIn = new MenuItem("Zoom _In");
        itemZoomIn.setAccelerator(KeyCombination.keyCombination("SHORTCUT + Shift + I"));
        itemZoomOut = new MenuItem("Zoom _Out");
        itemZoomOut.setAccelerator(KeyCombination.keyCombination("SHORTCUT + Shift + O"));


        //Submenü Speichern
        Menu submenueSpeichern = new Menu("_Speichern");
        itemspeichernXML = new MenuItem("_XML");
        itemspeichernXML.setAccelerator(KeyCombination.keyCombination("SHORTCUT + Shift + X"));
        itemspeichernSerialisieren = new MenuItem("_Serialisieren");
        itemspeichernSerialisieren.setAccelerator(KeyCombination.keyCombination("SHORTCUT + Shift + S"));
        submenueSpeichern.getItems().addAll(itemspeichernXML, itemspeichernSerialisieren);

        //Submenue Laden
        Menu submenueLaden = new Menu("L_aden");
        itemladenXML = new MenuItem("_XML");
        itemladenXML.setAccelerator(KeyCombination.keyCombination("SHORTCUT + Shift + X"));
        itemladenDeserialisieren = new MenuItem("_Serialisieren");
        itemladenDeserialisieren.setAccelerator(KeyCombination.keyCombination("SHORTCUT + Shift + S"));
        submenueLaden.getItems().addAll(itemladenXML, itemladenDeserialisieren);
        menuPopulation.getItems().addAll(itemGroesseAendern, itemLoeschen, itemErzeugen, itemTorus, new SeparatorMenuItem(), itemZoomIn, itemZoomOut, new SeparatorMenuItem(), submenueSpeichern, submenueLaden);

        /* Erzeugen des Ausklappbaren Menues Simulation*/
        Menu menuSimulation = new Menu("_Simulation");
        itemSchritt = new MenuItem("Schri_tt");
        itemSchritt.setAccelerator(KeyCombination.keyCombination("SHORTCUT + Alt + S"));
        itemStart = new MenuItem("_Start");
        itemStart.setAccelerator(KeyCombination.keyCombination("SHORTCUT + Alt + A"));
        itemStart.setGraphic(new ImageView(new Image("Start16.gif")));
        itemStopp = new MenuItem("Sto_pp");
        itemStopp.setAccelerator(KeyCombination.keyCombination("SHORTCUT + Shift + O"));
        itemStopp.setGraphic(new ImageView(new Image("Stop16.gif")));
        menuSimulation.getItems().addAll(itemSchritt, itemStart, itemStopp);
        menuBar.getMenus().addAll(menuAutomat, menuPopulation, menuSimulation);


        //Toolbar
        ToolBar toolbar = new ToolBar();
        buttonNeuerAutomat = new Button();
        buttonNeuerAutomat.setTooltip(new Tooltip("Neuer Automat"));
        buttonNeuerAutomat.setGraphic(new ImageView(new Image("New24.gif")));
        buttonAutomatLaden = new Button();
        buttonAutomatLaden.setTooltip(new Tooltip("Automat Laden"));
        buttonAutomatLaden.setGraphic(new ImageView(new Image("Load24.gif")));
        buttonGroesserePopulation = new Button();
        buttonGroesserePopulation.setTooltip(new Tooltip("Population vergroessern"));
        buttonGroesserePopulation.setGraphic(new ImageView(new Image("Size24.gif")));
        buttonZustandNull = new Button();
        buttonZustandNull.setTooltip(new Tooltip("Alle Zellen auf Zustand 0"));
        buttonZustandNull.setGraphic(new ImageView(new Image("Delete24.gif")));
        buttonZufaelligePopulation = new Button();
        buttonZufaelligePopulation.setTooltip(new Tooltip("Zufällige Population"));
        buttonZufaelligePopulation.setGraphic(new ImageView(new Image("Random24.gif")));
        buttonTorus.setTooltip(new Tooltip("Torus"));
        buttonTorus.setGraphic(new ImageView(new Image("Torus24.gif")));
        buttonDrucken = new Button();
        buttonDrucken.setTooltip(new Tooltip("Drucken"));
        buttonDrucken.setGraphic(new ImageView(new Image("Print24.gif")));
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
        sliderSchneller = new Slider(0, 100, 0);
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

        this.populationsPanel = new PopulationsPanel(automaton, colorPickers, this);

        ScrollPane scrollPaneRechts = new ScrollPane(populationsPanel);
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

    public MenuItem getItemNeu() {
        return itemNeu;
    }



    public MenuItem getItemBeenden() {
        return itemBeenden;
    }

    public MenuItem getItemGroesseAendern() {
        return itemGroesseAendern;
    }

    public MenuItem getItemLoeschen() {
        return itemLoeschen;
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



    public Button getButtonGroesserePopulation() {
        return buttonGroesserePopulation;
    }

    public Button getButtonZustandNull() {
        return buttonZustandNull;
    }

    public Button getButtonZufaelligePopulation() {
        return buttonZufaelligePopulation;
    }

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


