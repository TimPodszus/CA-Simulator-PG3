package de.podszus.view;

import de.podszus.model.Automaton;
import de.podszus.model.Cell;
import de.podszus.util.Observer;
import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;

public class PopulationsPanel extends Region implements Observer {

    private final Automaton automaton;
    public final Canvas canvas;
    private double width = 15;


    private double lineWidth = 1;

    private final HBox[] colorPickerPanels;


    CAStage stage;

    PopulationsPanel(Automaton automaton, HBox[] colorPickerPanels, CAStage stage) {
        this.automaton = automaton;
        this.colorPickerPanels = colorPickerPanels;
        this.stage = stage;
        this.canvas = new Canvas(
                this.automaton.getNumberOfColumns() * width + this.automaton.getNumberOfColumns() * (lineWidth * 3),
                this.automaton.getNumberOfRows() * width + this.automaton.getNumberOfRows() * (lineWidth * 3));
        this.automaton.add(this);
        paintCanvas();
        getChildren().add(canvas);
    }


    public void paintCanvas() {
        GraphicsContext context = this.canvas.getGraphicsContext2D();
        updateCanvas();
        context.setLineWidth(lineWidth);
        context.setFill(Color.BLACK);
        context.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        context.strokeRect(lineWidth, lineWidth, this.automaton.getNumberOfColumns() * width + this.automaton.getNumberOfColumns() * lineWidth, this.automaton.getNumberOfRows() * width + this.automaton.getNumberOfRows() * lineWidth);
        for (int r = 0; r < this.automaton.getNumberOfRows(); r++) {
            for (int c = 0; c < this.automaton.getNumberOfColumns(); c++) {
                Cell cell = automaton.getCell(r, c);
                int index = cell.getState();
                HBox colorPickerHBox = colorPickerPanels[index];
                ColorPicker cp = (ColorPicker) colorPickerHBox.getChildren().get(1);
                context.setFill(cp.getValue());
                context.fillRect(((c * width) + (c * lineWidth)) + 1.5 * lineWidth, ((r * width) + (r * lineWidth)) + 1.5 * lineWidth, width, width);
                context.setFill(Color.BLACK);
                context.strokeRect((c * width + c * context.getLineWidth()), (r * width + r * context.getLineWidth()), width + 2 * context.getLineWidth(), width + 2 * context.getLineWidth());

            }
        }
    }


    private void updateCanvas() {
        canvas.setWidth(this.automaton.getNumberOfColumns() * width + this.automaton.getNumberOfColumns() * (lineWidth * 3));
        canvas.setHeight(this.automaton.getNumberOfRows() * width + this.automaton.getNumberOfRows() * (lineWidth * 3));
    }


    @Override
    public void update() {
        if (Platform.isFxApplicationThread()) {
            paintCanvas();
        } else {
            Platform.runLater(this::paintCanvas);
        }

    }

    public double getLineWidth() {
        return lineWidth;
    }

    public HBox[] getColorPickerPanels() {
        return colorPickerPanels;
    }


    public void setCellWidth(double width) {
        this.width = width;
    }

    public double getCellWidth() {
        return width;
    }


    public void setLineWidth(double lineWidth) {
        this.lineWidth = lineWidth;
    }
}






