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
        MenuItem itemNeu = new MenuItem("Neu...");
        itemNeu.setAccelerator(KeyCombination.keyCombination("SHORTCUT + N"));
        itemNeu.setGraphic(new ImageView(new Image("New16.gif")));
        MenuItem itemLaden = new MenuItem("Laden...");
        itemLaden.setAccelerator(KeyCombination.keyCombination("SHORTCUT + L"));
        itemLaden.setGraphic(new ImageView(new Image("Load16.gif")));
        MenuItem itemEditor = new MenuItem("Editor");
        itemEditor.setAccelerator(KeyCombination.keyCombination("SHORTCUT + E"));
        MenuItem itemBeenden = new MenuItem("Beenden");
        itemBeenden.setAccelerator(KeyCombination.keyCombination("SHORTCUT + Q"));
        menuAutomat.getItems().addAll(itemNeu, itemLaden,new SeparatorMenuItem(), itemEditor,new SeparatorMenuItem(), itemBeenden);


        /* Erzeugen des Ausklappbaren Menues Population*/
        Menu menuPopulation = new Menu("_Population");
        MenuItem itemGroesseAendern = new MenuItem("Größe ändern");
        itemGroesseAendern.setAccelerator(KeyCombination.keyCombination("SHORTCUT + Shift + G"));
        MenuItem itemLoeschen = new MenuItem("Löschen");
        itemLoeschen.setAccelerator(KeyCombination.keyCombination("SHORTCUT + Shift + L"));
        MenuItem itemErzeugen = new MenuItem("Erzeugen");
        itemErzeugen.setAccelerator(KeyCombination.keyCombination("SHORTCUT + Shift + Z"));
        CheckMenuItem itemTorus = new CheckMenuItem("Torus");
        itemTorus.setAccelerator(KeyCombination.keyCombination("SHORTCUT + Shift + T"));
        MenuItem itemZoomIn = new MenuItem("Vergrößern");
        itemZoomIn.setAccelerator(KeyCombination.keyCombination("SHORTCUT + Shift + I"));
        MenuItem itemZoomOut = new MenuItem("Verkleinern");
        itemZoomOut.setAccelerator(KeyCombination.keyCombination("SHORTCUT + Shift + O"));

        //Submenü Speichern
        Menu submenueSpeichern = new Menu("Speichern");
        MenuItem itemspeichernXML = new MenuItem("XML");
        itemspeichernXML.setAccelerator(KeyCombination.keyCombination("SHORTCUT + Shift + X"));
        MenuItem itemspeichernSerialisieren = new MenuItem("Serialisieren");
        itemspeichernSerialisieren.setAccelerator(KeyCombination.keyCombination("SHORTCUT + Shift + S"));
        submenueSpeichern.getItems().addAll(itemspeichernXML,itemspeichernSerialisieren);

        //Submenue Laden
        Menu submenueLaden = new Menu("Laden");
        MenuItem itemladenXML = new MenuItem("XML");
        itemladenXML.setAccelerator(KeyCombination.keyCombination("SHORTCUT + Shift + X"));
        MenuItem itemladenDeserialisieren = new MenuItem("Serialisieren");
        itemladenDeserialisieren.setAccelerator(KeyCombination.keyCombination("SHORTCUT + Shift + S"));
        submenueLaden.getItems().addAll(itemladenXML,itemladenDeserialisieren);

        menuPopulation.getItems().addAll(itemGroesseAendern, itemLoeschen,itemErzeugen, itemTorus,new SeparatorMenuItem(), itemZoomIn, itemZoomOut,new SeparatorMenuItem(),submenueSpeichern, submenueLaden);

        /* Erzeugen des Ausklappbaren Menues Simulation*/
        Menu menuSimulation = new Menu("_Simulation");
        MenuItem itemSchritt = new MenuItem("Schritt");
        itemSchritt.setAccelerator(KeyCombination.keyCombination("SHORTCUT + Alt + S"));
        MenuItem itemStart = new MenuItem("Start");
        itemStart.setAccelerator(KeyCombination.keyCombination("SHORTCUT + Alt + A"));
        itemStart.setGraphic(new ImageView(new Image("Start16.gif")));
        MenuItem itemStopp = new MenuItem("Stopp");
        itemStopp.setAccelerator(KeyCombination.keyCombination("SHORTCUT + Shift + O"));
        itemStopp.setGraphic(new ImageView(new Image("Stop16.gif")));
        menuSimulation.getItems().addAll(itemSchritt, itemStart, itemStopp);
        menuBar.getMenus().addAll(menuAutomat,menuPopulation,menuSimulation);



        //Toolbar
        ToolBar toolbar = new ToolBar();
        Button buttonneuerAutomat = new Button();
        buttonneuerAutomat.setGraphic(new ImageView(new Image("New24.gif")));
        Button  buttonAutomatLaden= new Button();
        buttonAutomatLaden.setGraphic(new ImageView(new Image("Load24.gif")));
        Button buttonGroesserePopulation = new Button();
        buttonGroesserePopulation.setGraphic(new ImageView(new Image("Size24.gif")));
        Button buttonZustandNull = new Button();
        buttonZustandNull.setGraphic(new ImageView(new Image("Delete24.gif")));
        Button buttonZufaelligePopulation = new Button();
        buttonZufaelligePopulation.setGraphic(new ImageView(new Image("Random24.gif")));
        ToggleButton buttonTorus = new ToggleButton();
        buttonTorus.setGraphic(new ImageView(new Image("Torus24.gif")));
        Button buttonDrucken = new Button();
        buttonDrucken.setGraphic(new ImageView(new Image("Print24.gif")));
        Button buttonZoomIn = new Button();
        buttonZoomIn.setGraphic(new ImageView(new Image("ZoomIn24.gif")));
        Button buttonZoomOut = new Button();
        buttonZoomOut.setGraphic(new ImageView(new Image("ZoomOut24.gif")));
        Button buttonStart = new Button();
        buttonStart.setGraphic(new ImageView(new Image("Start24.gif")));
        Button buttonStop = new Button();
        buttonStop.setGraphic(new ImageView(new Image("Stop24.gif")));
        Slider sliderSchneller  = new Slider(0,100,100);
        sliderSchneller.setShowTickLabels(true);
        sliderSchneller.setShowTickMarks(true);
        sliderSchneller.setMajorTickUnit(50);
        sliderSchneller.setMinorTickCount(1);
        toolbar.getItems().addAll(buttonneuerAutomat,buttonAutomatLaden,buttonGroesserePopulation,buttonZustandNull,buttonZufaelligePopulation,buttonTorus,buttonDrucken,buttonZoomIn,buttonZoomOut,buttonStart,buttonStop,sliderSchneller);



        //innereBox die die Beiden Scrollpanes für die ColorPicker VBOX und die Region beinhaltet
        HBox innereBox = new HBox(5);


        VBox zustandspanel = new VBox();
        ArrayList<HBox> ColorPickerPanels = new ArrayList<>();

        //Anzahl der ColorPicker
        int anzahl = 2;
        for (int i = 0; i < anzahl; i++){
            HBox temp = createZustandspanel();
            ColorPickerPanels.add(temp);
            zustandspanel.getChildren().add(temp);


        }



        ScrollPane scrollPanelinks = new ScrollPane(zustandspanel);

        //Region, die die konkrete Simulation haust
        Region populationspanel = new Region();
        ScrollPane scrollPanerechts = new ScrollPane(populationspanel);
        scrollPanerechts.setPrefSize(1100,600);
        innereBox.getChildren().addAll(scrollPanelinks,scrollPanerechts);


        VBox.setVgrow(scrollPanerechts, Priority.ALWAYS);
        Label meldungsLabel = new Label("Herzlich Willkommen");
        meldungsLabel.setMaxHeight(30);

        hauptbox.getChildren().addAll(menuBar,toolbar,innereBox,meldungsLabel);
        primaryStage.setTitle("CAS-Simulator");
        primaryStage.setScene(new Scene(hauptbox , 1280, 720));
        primaryStage.show();
    }

    public HBox createZustandspanel(){
        HBox hBox = new HBox(10);
           RadioButton radioButton = new RadioButton();
           ColorPicker colorPicker = new ColorPicker();
           hBox.getChildren().addAll(radioButton, colorPicker);
        return hBox;


        }

        /*
        class ColorPickerHBox extends Node {
        ArrayList<RadioButton> radioButtons;
        ArrayList<ColorPicker> colorPickers;


        ColorPickerHBox(){
            HBox hBox = new HBox(10);
            RadioButton radioButton = new RadioButton();
            ColorPicker colorPicker = new ColorPicker(Color.color(Math.random(),Math.random(),Math.random()));
            hBox.getChildren().addAll(radioButton,colorPicker);

        }


        } */
    }

