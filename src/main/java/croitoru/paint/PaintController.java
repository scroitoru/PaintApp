package croitoru.paint;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class PaintController {

    @FXML
    private Canvas canvas;
    @FXML
    private ColorPicker colorPicker;
    @FXML
    private TextField brushSize;
    @FXML
    private CheckBox eraser;

    private double lastX;
    private double lastY;

    public void initialize(){
        GraphicsContext gc = canvas.getGraphicsContext2D();

        //so that the drawing will begin where you put mouse
        //instead of continuing from where you last drew
        canvas.setOnMousePressed(e ->{
            lastX = e.getX();
            lastY = e.getY();
        });

        canvas.setOnMouseDragged(e -> {
            double size = Double.parseDouble(brushSize.getText());
            double x = e.getX();
            double y = e.getY();

            if (eraser.isSelected()){
                gc.clearRect(x,y,size,size);
            }else{
                gc.setLineWidth(size);
                gc.setStroke(colorPicker.getValue());
                gc.strokeLine(lastX,lastY,x,y);
                lastX = x;
                lastY = y;
            }
        });
    }
}
