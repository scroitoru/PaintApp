package croitoru.paint;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;

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
    public void onExit(){
        Platform.exit();
    }
}
