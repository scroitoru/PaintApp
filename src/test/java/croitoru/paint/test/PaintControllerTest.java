package croitoru.paint.test;

import croitoru.paint.PaintController;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

public class PaintControllerTest {
    private PaintController controller;
    private Canvas canvas;
    private ColorPicker colorPicker;
    private TextField brushSize;
    private CheckBox eraser;
    private GraphicsContext gc;
    double x, y, lastX, lastY;

    @BeforeClass
    public static void beforeClass(){
        com.sun.javafx.application.PlatformImpl.startup(()->{});
    }


    @Test
    public void draw(){
        //given
        givenPaintController();
        double size = Double.parseDouble(brushSize.getText());

        //when
        controller.initialize();
        assertFalse(eraser.isSelected());

        //then
        verify(gc).setLineWidth(size);
        verify(gc).setStroke(colorPicker.getValue());
        verify(gc).strokeLine(lastX,lastY,x,y);
        lastX = x;
        lastY = y;
    }

    @Test
    public void erase(){
        //given
        givenPaintController();
        double size = Double.parseDouble(brushSize.getText());

        //when
        controller.initialize();
        eraser.isSelected();

        //then
        verify(gc).clearRect(x,y,size,size);
    }

    private void givenPaintController(){
        canvas = mock(Canvas.class);
        colorPicker = mock(ColorPicker.class);
        brushSize = mock(TextField.class);
        eraser = mock(CheckBox.class);
        controller = mock(PaintController.class);
        gc = mock(GraphicsContext.class);
    }

//    @Test
//    public void setBrushSize(){
//        //given
//        givenPaintController();
//        doReturn(18).when(brushSize.getText());
//
//        //when
//        controller.initialize();
//
//        //then
//        verify(brushSize.getText());
//    }
}
