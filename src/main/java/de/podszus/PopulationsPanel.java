package de.podszus;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.input.MouseEvent;

public class PopulationsPanel extends Region {

    private final Automaton automaton;
    private final Canvas canvas;
    private double width = 30;
    private double lineWidth = 2;


    public double getCellWidth() {
        return width;
    }

    HBox[] colorPickerPanels;


    double startx;
    double starty;
    CAStage stage;

    PopulationsPanel(Automaton automaton, HBox[] colorPickerPanels, CAStage stage) {
        this.automaton = automaton;
        this.colorPickerPanels = colorPickerPanels;
        this.stage = stage;
        this.canvas = new Canvas(
                this.automaton.getNumberOfColumns() * width + this.automaton.getNumberOfColumns() * (lineWidth * 3),
                this.automaton.getNumberOfRows() * width + this.automaton.getNumberOfRows() * (lineWidth * 3));

        this.canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, this::changeCell);
        this.canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, this::changeCellArea);
        paintCanvas();
        getChildren().add(canvas);
    }


    void paintCanvas() {
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
                cp.setOnAction(e -> paintCanvas());
                context.setFill(cp.getValue());
                context.fillRect(((c * width) + (c * lineWidth)) + 1.5 * lineWidth, ((r * width) + (r * lineWidth)) + 1.5 * lineWidth, width, width);
                context.setFill(Color.BLACK);
                context.strokeRect((c * width + c * context.getLineWidth()), (r * width + r * context.getLineWidth()), width + 2 * context.getLineWidth(), width + 2 * context.getLineWidth());

            }
        }
    }

    private void updateCanvas() {
        canvas.setHeight(this.automaton.getNumberOfRows() * width + this.automaton.getNumberOfRows() * (lineWidth * 3));
        canvas.setWidth(this.automaton.getNumberOfColumns() * width + this.automaton.getNumberOfColumns() * (lineWidth * 3));
    }

    void zoomIn() {

        this.width = width * 2;
        this.lineWidth = lineWidth * 2;
        paintCanvas();

    }

    void zoomOut() {
        this.width = width / 2;
        this.lineWidth = lineWidth / 2;

        paintCanvas();
    }

    void changeCell(MouseEvent event) {
        int checkedRadioButton = 0;
        for (int i = 0; i < colorPickerPanels.length; i++) {
            HBox currentRadioButtonHBox = colorPickerPanels[i];
            RadioButton currentRadioButton = (RadioButton) currentRadioButtonHBox.getChildren().get(0);
            if (currentRadioButton.isSelected()) {
                checkedRadioButton = i;

            }
        }
        double x = event.getX();
        double y = event.getY();
        startx = x;
        starty = y;
        if (x < lineWidth || y < lineWidth || x > lineWidth + automaton.rows * width || y > lineWidth + automaton.columns * width) {
            return;
        }
        int column = (int) ((x - lineWidth) / (width+ lineWidth));
        int row = (int) ((y - lineWidth) / (width + lineWidth));

        automaton.getCell(row, column).setState(checkedRadioButton);
        paintCanvas();


    }

    void changeCellArea (MouseEvent event){

        int checkedRadioButton = 0;
        for (int i = 0; i < colorPickerPanels.length; i++) {
            HBox currentRadioButtonHBox = colorPickerPanels[i];
            RadioButton currentRadioButton = (RadioButton) currentRadioButtonHBox.getChildren().get(0);
            if (currentRadioButton.isSelected()) {
                checkedRadioButton = i;

            }
        }

        double endx = event.getX();
        double endy = event.getY();
        if (endx < lineWidth || endy < lineWidth || endx > lineWidth + automaton.rows * width || endy > lineWidth + automaton.columns * width) {
            return;
        }
       if (endx > lineWidth + automaton.getNumberOfColumns() * width) {
            endx = lineWidth + automaton.getNumberOfColumns() * width -1;

        }
        if (endy > lineWidth + automaton.getNumberOfRows() * width) {
            endy = lineWidth + automaton.getNumberOfRows() * width -1;
        }

        int startcolumn = (int) ((startx - lineWidth) / (width+ lineWidth));
        int startrow = (int) ((starty - lineWidth) / (width + lineWidth));
        int endcolumn = (int) ((endx - lineWidth) / (width + lineWidth));
        int endrow = (int) ((endy - lineWidth) / (width+ lineWidth));
        if (endcolumn < startcolumn){
            int x = startcolumn;
            startcolumn = endcolumn;
            endcolumn = x;
        }
        if (endrow < startrow){
            int x = startrow;
            startrow = endrow;
            endrow = x;
        }
        automaton.setState(startrow,startcolumn, endrow, endcolumn, checkedRadioButton);

        paintCanvas();

    }
}






