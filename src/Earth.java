import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;

public class Earth extends Group {

    private Sphere sph = new Sphere(300);
    private Rotate ry = new Rotate();
    private PhongMaterial map = new PhongMaterial();

    long diff_time = 0;



    public Earth() {
        try {
            map.setDiffuseMap(new Image(new File("src/ressource/earth_lights_4800.png").toURI().toURL().toExternalForm()));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        this.sph.setRotationAxis(ry.Y_AXIS);
        this.sph.setMaterial(map);
        this.getChildren().add(sph);

        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long time) {
                long rTime = 15000000;
                System.out.println("Valeur de time : " + time);
                diff_time = time;
                sph.rotateProperty().set(sph.getRotate()+0.2);
                //sph.rotateProperty().set(ry);
                //boucle avec i qui s'incremente pour la durée

            }
        };
        animationTimer.start();
    }



}
