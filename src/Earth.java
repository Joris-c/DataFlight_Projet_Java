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

    Sphere sph = new Sphere(300);
    Rotate ry = new Rotate();
    PhongMaterial map = new PhongMaterial();


    public Earth() {
        try {
            map.setDiffuseMap(new Image(new File("src/ressource/earth_lights_4800.png").toURI().toURL().toExternalForm()));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        this.sph.setMaterial(map);
        this.getChildren().add(sph);

        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long time) {
                System.out.println("Valeur de time : " + time);
                ry.setAngle(23.45); // A compl´eter
            }
        };
        animationTimer.start();
    }



}
