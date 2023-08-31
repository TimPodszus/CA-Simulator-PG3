package de.podszus;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class PopulationsPanel extends Region {

    private Automaton automaton;
    private Canvas canvas;
    private double WIDTH = 30;
    private double linewidth = 2;

    GraphicsContext context;

    public GraphicsContext getContext() {
        return context;
    }
    ArrayList<ColorPickerHBox> colorPickerPanels;
    PopulationsPanel(Automaton automaton, ArrayList<ColorPickerHBox> colorPickerPanels) {
        this.automaton = automaton;
        this.colorPickerPanels = colorPickerPanels;
        this.canvas = new Canvas(
                this.automaton.getNumberOfColumns() * WIDTH + this.automaton.getNumberOfColumns() * (linewidth * 3),
                this.automaton.getNumberOfRows() * WIDTH + this.automaton.getNumberOfRows() * (linewidth * 3));
        this.context = this.canvas.getGraphicsContext2D();
        paintCanvas(context, this.colorPickerPanels);
        getChildren().add(canvas);
    }

    private void paintCanvas(GraphicsContext context, ArrayList<ColorPickerHBox> colorPickerPanels) {

        context.setLineWidth(linewidth);
        context.setFill(Color.BLACK);
        context.strokeRect(linewidth, linewidth, this.automaton.getNumberOfColumns() * WIDTH + this.automaton.getNumberOfColumns() * linewidth, this.automaton.getNumberOfRows() * WIDTH + this.automaton.getNumberOfRows() * linewidth);
        for (int r = 0; r < this.automaton.getNumberOfRows(); r++) {
            for (int c = 0; c < this.automaton.getNumberOfColumns(); c++) {
                Cell cell = automaton.getCell(r, c);
                int index = cell.getState();
                ColorPickerHBox colorPickerHBox = colorPickerPanels.get(index);
                ColorPicker cp = colorPickerHBox.getColorPicker();
                context.setFill(cp.getValue());
                context.fillRect(((c * WIDTH) + (c * linewidth)) +  1.5 * linewidth , ((r * WIDTH) + (r * linewidth)) + 1.5 * linewidth, WIDTH, WIDTH);
                context.setFill(Color.BLACK);
                context.strokeRect((c * WIDTH + c * context.getLineWidth()) , (r * WIDTH + r * context.getLineWidth()) , WIDTH + 2 * context.getLineWidth(), WIDTH + 2 * context.getLineWidth());

            }
        }
    }

    void zoomIn(){
        this.WIDTH = WIDTH * 2;
        this.linewidth = linewidth * 2;
        clear();
        paintCanvas(getContext(),getColorPickerPanels());
        getChildren().add(canvas);
    }
      void zoomOut(){
        this.WIDTH = WIDTH / 2;
        this.linewidth = linewidth / 2;
        clear();
          paintCanvas(getContext(),getColorPickerPanels());
          getChildren().add(canvas);
    }
    void clear(){
    context = getContext();
    context.setStroke(Color.WHITE);
    context.fillRect(0,0,this.automaton.getNumberOfColumns() * WIDTH + this.automaton.getNumberOfColumns() * (linewidth * 3),
            this.automaton.getNumberOfRows() * WIDTH + this.automaton.getNumberOfRows() * (linewidth * 3));
    }

    public ArrayList<ColorPickerHBox> getColorPickerPanels() {
        return colorPickerPanels;
    }
}





