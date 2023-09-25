package de.podszus.controller;

import de.podszus.CAMain;
import de.podszus.model.Automaton;
import de.podszus.view.CAStage;
import de.podszus.view.PopulationChangeBox;
import de.podszus.view.PopulationsPanel;
import javafx.scene.control.ChoiceDialog;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.net.URI;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Stream;

public class CAStageController {
    Automaton automaton;
    PopulationsPanel populationsPanel;
    CAStage stage;

    public CAStageController(Automaton automaton, CAStage stage) {
        this.stage = stage;
        this.populationsPanel = stage.getPopulationsPanel();
        this.automaton = automaton;
        stage.getButtonNeuerAutomat().setOnAction(e-> openAutomaton());
        stage.getItemNeu().setOnAction(e -> openAutomaton());
        stage.getItemBeenden().setOnAction(e -> this.stage.close());
        stage.getItemChangeSize().setOnAction(e -> {
            PopulationChangeBox changeBox = new PopulationChangeBox(automaton);
            automaton.changeSize(changeBox.getRowsOutput(), changeBox.getColumnsOutput());
            populationsPanel.update();
        });
        stage.getItemDelete().setOnAction(e -> {
            automaton.clearPopulation();
            populationsPanel.update();
        });
        stage.getItemErzeugen().setOnAction(e -> {
            automaton.randomPopulation();
            populationsPanel.update();
        });
        stage.getItemTorus().setOnAction(e -> {
            automaton.setTorus(!automaton.isTorus());
            stage.getButtonTorus().setSelected(!stage.getButtonTorus().isSelected());
        });

        stage.getButtonBiggerPopulation().setOnAction(e -> {
            PopulationChangeBox changeBox = new PopulationChangeBox(automaton);
            automaton.changeSize(changeBox.getRowsOutput(), changeBox.getColumnsOutput());
            populationsPanel.update();
        });
        stage.getButtonZustandNull().setOnAction(e -> {
            automaton.clearPopulation();
            populationsPanel.update();
        });
        stage.getButtonRandomPopulation().setOnAction(e -> {
            automaton.randomPopulation();
            populationsPanel.update();
        });
        stage.getButtonTorus().setOnAction(e -> {
            automaton.setTorus(!automaton.isTorus());
            stage.getItemTorus().setSelected(!stage.getItemTorus().isSelected());
        });


        stage.getButtonStep().setOnAction(e -> {
            automaton.nextGeneration();
            populationsPanel.update();
        });

        stage.getItemSchritt().setOnAction(e -> {
            automaton.nextGeneration();
            populationsPanel.update();
        });



    }

    public static String getResourcesDir() {
        try {
            final String pkgName = CAStage.class.getPackageName();
            final String pkgPath = pkgName.replace('.', '/');
            final URI pkg = Objects.requireNonNull(ClassLoader.getSystemClassLoader().getResource(pkgPath)).toURI();
            if (pkg.toString().startsWith("jar:")) {
                return "/resources/";
            }
        } catch (Exception exc) {
            // ignore
        }
        return "";
    }


    private static void openAutomaton(){
        List<Class<?>> classes = getClassesForPackage("de.podszus");

        List<String> automatonClasses = new ArrayList<>();
        for (Class<?> cls : classes){
            if (isAutomatonClass(cls)){
                automatonClasses.add(cls.getName());
            }
        }

        ChoiceDialog<String> choiceDialog = new ChoiceDialog<>(automatonClasses.get(0),automatonClasses);
        choiceDialog.setTitle("Neuer Automat");
        choiceDialog.setHeaderText("Automatenauswahl");
        choiceDialog.setContentText("Automaten auswählen:");

        Optional<String> result = choiceDialog.showAndWait();
        if(result.isEmpty()){
            return;
        }
        try{
            Class<?> cls = Class.forName(result.get());
            Automaton automaton1 = (Automaton) cls.getConstructor().newInstance();
            CAMain.newGame(automaton1);
        }
        catch (Exception ignored){
            //ignore
        }
    }


    private static boolean isAutomatonClass(Class<?> cls) {
        try{
            boolean yes = Automaton.class.isAssignableFrom(cls) && Modifier.isPublic(cls.getModifiers()) &&
                    !Modifier.isAbstract(cls.getModifiers());
            Constructor<?> constructor = cls.getConstructor();
            return yes &&Modifier.isPublic(constructor.getModifiers());
        }
        catch (Exception e ){
            return false;
        }
    }

    public static List<Class<?>> getClassesForPackage(final String pkgName) {
        FileSystem rootFS = null;
        try {
            final String pkgPath = pkgName.replace('.', '/');
            final URI pkg = Objects.requireNonNull(ClassLoader.getSystemClassLoader().getResource(pkgPath)).toURI();
            final ArrayList<Class<?>> allClasses = new ArrayList<>();
            Path root;
            if (pkg.toString().startsWith("jar:")) {
                rootFS = FileSystems.newFileSystem(pkg, Collections.emptyMap());
                root = rootFS.getPath(pkgPath);
            } else {
                root = Paths.get(pkg);
            }
            final String extension = ".class";
            try (final Stream<Path> allPaths = Files.walk(root)) {
                allPaths.filter(Files::isRegularFile).forEach(file -> {
                    try {
                        final String fileName = file.toString();
                        final String path = fileName.replace('/', '.').replace('\\', '.');
                        final String name = path.substring(path.indexOf(pkgName), path.length() - extension.length());
                        allClasses.add(Class.forName(name));
                    } catch (final ClassNotFoundException | StringIndexOutOfBoundsException ignored) {
                        //ignore
                    }
                });
            }
            return allClasses;
        } catch (Exception exc) {
            return new ArrayList<>();
        } finally {
            if (rootFS != null) {
                try {
                    rootFS.close();
                } catch (IOException e) {
                    // ignore
                }
            }
        }
    }

}
