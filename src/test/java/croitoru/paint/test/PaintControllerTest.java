package croitoru.paint.test;

import croitoru.paint.PaintController;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class PaintControllerTest {
    private PaintController controller;
    private Canvas canvas;
    private ColorPicker colorPicker;
    private CheckBox eraser;
    private GraphicsContext gc;
    double x, y, lastX, lastY;

    @BeforeClass
    public static void beforeClass(){
        com.sun.javafx.application.PlatformImpl.startup(()->{});
    }


    /**
     * verify that initialize() works
     */
    @Test
    public void initialize(){
        //given
        givenPaintController();

        //when
        controller.initialize();

        //then
        verify(controller).initialize();
    }


    /**
     * verify that when mouse is dragged, drawing works
     */
    @Test
    public void draw(){
        //given
        givenPaintController();
        double size = 18;
        eraser.setSelected(false);
        doReturn(false).when(eraser).isSelected();

        //when
        controller.initialize();

        //then
        verify(gc).setLineWidth(size);
        verify(gc).setStroke(colorPicker.getValue());
        verify(gc).strokeLine(lastX,lastY,x,y);
        lastX = x;
        lastY = y;
    }

    /**
     * verify that when eraser is checked and then mouse dragged, it erases
     */
    @Test
    public void erase(){
        //given
        givenPaintController();
        double size = 18;
        eraser.setSelected(true);
        doReturn(true).when(eraser).isSelected();

        //when
        controller.initialize();
        eraser.isSelected();

        //then
        verify(gc).clearRect(x,y,size,size);
    }

    private void givenPaintController(){
        canvas = mock(Canvas.class);
        colorPicker = mock(ColorPicker.class);
        eraser = mock(CheckBox.class);
        controller = mock(PaintController.class);
        gc = mock(GraphicsContext.class);
    }

}
