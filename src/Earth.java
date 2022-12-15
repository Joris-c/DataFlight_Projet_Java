import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;

import java.io.File;
import java.net.MalformedURLException;

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
        ry.setAxis(Rotate.Y_AXIS);
        this.sph.setMaterial(map);
        this.getChildren().add(sph);

        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long time) {
                //System.out.println("Valeur de time : " + time);
                ry.setAngle(0.15); // A compl´eter
                sph.getTransforms().add(ry);
            }
        };
        animationTimer.start();
    }




}
