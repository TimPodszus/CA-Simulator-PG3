package de.podszus;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;


public class Main extends Application {

    // VM Args: --module-path "\path\to\javafx-sdk-19\lib" --add-modules javafx.controls,javafx.fxml
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        VBox hauptbox = new VBox();
        final MenuBar menuBar = new MenuBar();

        /* Erzeugen des Ausklappbaren Menues Automat*/
        Menu menuAutomat = new Menu("_Automat");
        MenuItem itemNeu = new MenuItem("_Neu...");
        itemNeu.setAccelerator(KeyCombination.keyCombination("SHORTCUT + N"));
        itemNeu.setGraphic(new ImageView(new Image("New16.gif")));
        MenuItem itemLaden = new MenuItem("_Laden...");
        itemLaden.setAccelerator(KeyCombination.keyCombination("SHORTCUT + L"));
        itemLaden.setGraphic(new ImageView(new Image("Load16.gif")));
        MenuItem itemEditor = new MenuItem("_Editor");
        itemEditor.setAccelerator(KeyCombination.keyCombination("SHORTCUT + E"));
        MenuItem itemBeenden = new MenuItem("_Beenden");
        itemBeenden.setAccelerator(KeyCombination.keyCombination("SHORTCUT + Q"));
        menuAutomat.getItems().addAll(itemNeu, itemLaden, new SeparatorMenuItem(), itemEditor, new SeparatorMenuItem(), itemBeenden);


        /* Erzeugen des Ausklappbaren Menues Population*/
        Menu menuPopulation = new Menu("_Population");
        MenuItem itemGroesseAendern = new MenuItem("_Größe ändern");
        itemGroesseAendern.setAccelerator(KeyCombination.keyCombination("SHORTCUT + Shift + G"));
        MenuItem itemLoeschen = new MenuItem("_Löschen");
        itemLoeschen.setAccelerator(KeyCombination.keyCombination("SHORTCUT + Shift + L"));
        MenuItem itemErzeugen = new MenuItem("_Erzeugen");
        itemErzeugen.setAccelerator(KeyCombination.keyCombination("SHORTCUT + Shift + Z"));
        CheckMenuItem itemTorus = new CheckMenuItem("_Torus");
        itemTorus.setAccelerator(KeyCombination.keyCombination("SHORTCUT + Shift + T"));
        MenuItem itemZoomIn = new MenuItem("Zoom _In");
        itemZoomIn.setAccelerator(KeyCombination.keyCombination("SHORTCUT + Shift + I"));
        MenuItem itemZoomOut = new MenuItem("Zoom _Out");
        itemZoomOut.setAccelerator(KeyCombination.keyCombination("SHORTCUT + Shift + O"));

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
        Button buttonneuerAutomat = new Button();
        buttonneuerAutomat.setTooltip(new Tooltip("Neuer Automat"));
        buttonneuerAutomat.setGraphic(new ImageView(new Image("New24.gif")));
        Button buttonAutomatLaden = new Button();
        buttonAutomatLaden.setTooltip(new Tooltip("Automat Laden"));
        buttonAutomatLaden.setGraphic(new ImageView(new Image("Load24.gif")));
        Button buttonGroesserePopulation = new Button();
        buttonGroesserePopulation.setTooltip(new Tooltip("Population vergroessern"));
        buttonGroesserePopulation.setGraphic(new ImageView(new Image("Size24.gif")));
        Button buttonZustandNull = new Button();
        buttonZustandNull.setTooltip(new Tooltip("Alle Zellen auf Zustand 0"));

        buttonZustandNull.setGraphic(new ImageView(new Image("Delete24.gif")));
        Button buttonZufaelligePopulation = new Button();
        buttonZufaelligePopulation.setTooltip(new Tooltip("Zufällige Population"));
        buttonZufaelligePopulation.setGraphic(new ImageView(new Image("Random24.gif")));
        ToggleButton buttonTorus = new ToggleButton();
        buttonTorus.setTooltip(new Tooltip("Torus"));
        buttonTorus.setGraphic(new ImageView(new Image("Torus24.gif")));
        Button buttonDrucken = new Button();
        buttonDrucken.setTooltip(new Tooltip("Drucken"));
        buttonDrucken.setGraphic(new ImageView(new Image("Print24.gif")));
        Button buttonZoomIn = new Button();
        buttonZoomIn.setTooltip(new Tooltip("Zoom In"));
        buttonZoomIn.setGraphic(new ImageView(new Image("ZoomIn24.gif")));
        Button buttonZoomOut = new Button();
        buttonZoomOut.setTooltip(new Tooltip("Zoom Out"));
        buttonZoomOut.setGraphic(new ImageView(new Image("ZoomOut24.gif")));
        Button buttonStart = new Button();
        buttonStart.setTooltip(new Tooltip("Start"));
        buttonStart.setGraphic(new ImageView(new Image("Start24.gif")));
        Button buttonStop = new Button();
        buttonStop.setTooltip(new Tooltip("Stopp"));
        buttonStop.setGraphic(new ImageView(new Image("Stop24.gif")));
        Slider sliderSchneller = new Slider(0, 100, 100);
        sliderSchneller.setShowTickLabels(true);
        sliderSchneller.setShowTickMarks(true);
        sliderSchneller.setMajorTickUnit(50);
        sliderSchneller.setMinorTickCount(1);
        toolbar.getItems().addAll(buttonneuerAutomat, buttonAutomatLaden, new Separator(), buttonGroesserePopulation, buttonZustandNull, buttonZufaelligePopulation, buttonTorus, buttonDrucken, new Separator(), buttonZoomIn, buttonZoomOut, new Separator(), buttonStart, buttonStop, new Separator(), sliderSchneller);


        //innereBox die die Beiden Scrollpanes für die ColorPicker VBOX und die Region beinhaltet
        HBox innereBox = new HBox(5);


        VBox zustandspanel = new VBox();
        ArrayList<ColorPickerHBox> ColorPickerPanels = new ArrayList<>();

        //Anzahl der ColorPicker
        int anzahl = 2;
        for (int i = 1; i <= anzahl; i++) {
            ColorPickerHBox farbwaehler = new ColorPickerHBox(zustandspanel, i);
            ColorPickerPanels.add(farbwaehler);
        }

        ScrollPane scrollPanelinks = new ScrollPane(zustandspanel);

        //Region, die die konkrete Simulation haust
        Region populationspanel = new Region();
        ScrollPane scrollPanerechts = new ScrollPane(populationspanel);
        scrollPanerechts.setPrefSize(1100, 600);
        innereBox.getChildren().addAll(scrollPanelinks, scrollPanerechts);


        VBox.setVgrow(scrollPanerechts, Priority.ALWAYS);
        Label meldungsLabel = new Label("Herzlich Willkommen");
        meldungsLabel.setMaxHeight(30);

        hauptbox.getChildren().addAll(menuBar, toolbar, innereBox, meldungsLabel);
        primaryStage.setTitle("CAS-Simulator");
        primaryStage.setScene(new Scene(hauptbox, 1280, 720));
        primaryStage.show();
    }




    }


