import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.geometry.Point3D;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

import java.util.concurrent.atomic.AtomicReference;

//API KEY : 367de72a690d5732c70ac8f4dfeb6737
public class Interface extends Application {

    World w = new World("src/ressource/airport-codes_no_comma.csv");
    double z_max = -500;
    double z_min = -1500;
    double current_z = -1000;
    @Override
    public void start(Stage primaryStage) throws Exception{
        AtomicReference<Double> base = new AtomicReference<>((double) 0);
        primaryStage.setTitle("Hello world");
        Earth earth = new Earth();
        BackgroundImage bg = new BackgroundImage(new Image("/ressource/bg_voie_lactee.jpg")
                , BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);

        Pane pane = new Pane();
        pane.getChildren().add(prepareBG());
        pane.getChildren().add(earth);
        //pane.setBackground(new Background(bg));

        ImageView bg1 = new ImageView();
        bg1.setImage(new Image("https://www.science-et-vie.com/wp-content/uploads/scienceetvie/2020/05/d-voie-lactee-est-elle-visible.jpg"));
        Scene ihm = new Scene(pane, 600, 400,false);

        //ihm.setFill(Color.GRAY);


        //ihm.setFill(Color.GREEN);

        PerspectiveCamera camera = new PerspectiveCamera(true);
        camera.setTranslateZ(-1000);
        camera.setNearClip(0.1);
        camera.setFarClip(3000.0);
        camera.setFieldOfView(35);
        ihm.setCamera(camera);
        ihm.addEventHandler(MouseEvent.ANY, event -> {
            if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
                //System.out.println("Clicked on : (" + event.getSceneX()+ ", "+ event.getSceneY()+ ")");
                base.set(event.getSceneY());
            }
            if (event.getEventType() == MouseEvent.MOUSE_DRAGGED) {
                double diff = (event.getSceneY() - base.get());
                if(current_z + diff < z_max && current_z + diff > z_min){
                    camera.getTransforms().add(new Translate(0,0,diff));
                    base.set(event.getSceneY());
                    current_z += diff;
                }

                //System.out.println("dragged on : (" + event.getSceneX()+ ", "+ event.getSceneY()+")");
            }
            if (event.getButton()== MouseButton.SECONDARY && event.getEventType()==MouseEvent.MOUSE_CLICKED) {
                PickResult pickResult = event.getPickResult();
                if (pickResult.getIntersectedNode() != null) {
                    if(pickResult.getIntersectedTexCoord() != null) {
                        double X = pickResult.getIntersectedTexCoord().getX();
                        double Y = pickResult.getIntersectedTexCoord().getY();
                        double latitude = -180.0 * (Y - 0.5);
                        double longitude = 360.0 * (X - 0.5);
                        Aeroport a;
                        a = w.findNearestAirport(latitude, longitude);
                        System.out.println("a = " + a);
                        earth.displayRedSphere(a);
                        // Code `a compl´eter : on r´ecup`ere le point d'intersection
                        // Conversion en longitude et lattitude
                        // Recherche dans l'objet w de la classe World de l'a´eroport le plus proche.
                        // Affichage dans la console
                    }
                }
            }

        });
        ihm.addEventHandler(KeyEvent.KEY_PRESSED, keyEvent -> {
            if(keyEvent.getText().equals("+")){
                earth.Earth_rotate_X(true);
                System.out.println("up");
            }
            if(keyEvent.getText().equals("-")){
                earth.Earth_rotate_X(false);
                System.out.println("down");
            }
        });

        primaryStage.setScene(ihm);
        primaryStage.show();


    }
    public static void main(String[] args) {
        launch(args);
    }

    private ImageView prepareBG(){
        Image image = new Image("/ressource/bg_voie_lactee.jpg",2304,1680,false,false);
        System.out.println("image.getWidth() = " + image.getWidth());
        System.out.println("image.getHeight() = " + image.getHeight());
        ImageView imageView = new ImageView(image);
        imageView.setPreserveRatio(true);
        imageView.getTransforms().add(new Translate(-image.getWidth()/2,-image.getHeight()/2,800));
        return imageView;
    }
}


