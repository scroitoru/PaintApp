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

    public void initialize(){
        GraphicsContext gc = canvas.getGraphicsContext2D();

        canvas.setOnMouseDragged(e -> {
            double size = Double.parseDouble(brushSize.getText());
            double x = e.getX() - size / 2;
            double y = e.getY() - size / 2;

            if (eraser.isSelected()){
                gc.clearRect(x,y,size,size);
            }else{
                gc.setFill(colorPicker.getValue());
                gc.fillRect(x,y,size,size);
            }
        });
    }
    public void onExit(){
        Platform.exit();
    }
}
