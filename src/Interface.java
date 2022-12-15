import javafx.application.Application;
import javafx.geometry.Point3D;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.PickResult;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

import java.util.concurrent.atomic.AtomicReference;

public class Interface extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        AtomicReference<Double> base = new AtomicReference<>((double) 0);
        primaryStage.setTitle("Hello world");
        Earth earth = new Earth();
        Pane pane = new Pane(earth);
        Scene ihm = new Scene(pane, 600, 400,true);


        PerspectiveCamera camera = new PerspectiveCamera(true);
        camera.setTranslateZ(-1000);
        camera.setNearClip(0.1);
        camera.setFarClip(2000.0);
        camera.setFieldOfView(35);
        ihm.setCamera(camera);
        ihm.addEventHandler(MouseEvent.ANY, event -> {
            if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
                System.out.println("Clicked on : (" + event.getSceneX()+ ", "+ event.getSceneY()+ ")");
                base.set(event.getSceneY());
            }
            if (event.getEventType() == MouseEvent.MOUSE_DRAGGED) {
                double diff = (event.getSceneY() - base.get());
                camera.getTransforms().add(new Translate(0,0,diff));
                base.set(event.getSceneY());
                //System.out.println("dragged on : (" + event.getSceneX()+ ", "+ event.getSceneY()+")");
            }
            if (event.getButton()== MouseButton.SECONDARY && event.getEventType()==MouseEvent.MOUSE_CLICKED) {
                PickResult pickResult = event.getPickResult();
                if (pickResult.getIntersectedNode() != null) {
                    double X = pickResult.getIntersectedPoint().getX();
                    System.out.println("X = " + X);
                    //double X = pickResult.getIntersectedTexCoord().getX();
                    //double Y = pickResult.getIntersectedPoint().getY();
                    //double latitude = (2*Math.toDegrees(Math.atan(Math.exp((0.5-Y))/0.2678)))-90 ;
                    double longitude = 360 * (X - 0.5);
                    System.out.println("longitude = " + longitude);
                    //System.out.println("latitude = " + latitude);
                    // Code `a compl´eter : on r´ecup`ere le point d'intersection
                    // Conversion en longitude et lattitude
                    // Recherche dans l'objet w de la classe World de l'a´eroport le plus proche.
                    // Affichage dans la console
                }
            }

        });

        primaryStage.setScene(ihm);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
