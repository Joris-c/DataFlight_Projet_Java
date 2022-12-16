import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
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

    private  final double R = 300;




    public Earth() {
        try {
            map.setDiffuseMap(new Image(new File("src/ressource/earth_lights_4800.png").toURI().toURL().toExternalForm()));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        ry.setAxis(Rotate.Y_AXIS);
        this.sph.setMaterial(map);
        this.getChildren().add(sph);
        this.getTransforms().add(ry);
        //sph.getTransforms().add(ry);

        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long time) {
                ry.setAngle(360*(time*1e-9)/15);
            }
        };
        animationTimer.start();
    }

    public Sphere createSphere(Aeroport a, Color color){
        Sphere s = new Sphere(10);
        s.setTranslateX(150 * Math.cos(Math.toRadians(a.getLatitude()-13))*Math.sin(Math.toRadians(a.getLongitude())));
        s.setTranslateY(-150 * Math.sin(Math.toRadians(a.getLatitude()-13)));
        s.setTranslateZ(-150 * Math.cos(Math.toRadians(a.getLatitude()-13))*Math.sin(Math.toRadians(a.getLongitude())));
        /*s.setTranslateX(150 * Math.cos(Math.toRadians(a.getLatitude()-13))*Math.sin(Math.toRadians(a.getLongitude()-13)));
        s.setTranslateY(-150 * Math.sin(Math.toRadians(a.getLatitude()-13)));
        s.setTranslateZ(-150 * Math.cos(Math.toRadians(a.getLatitude()-13))*Math.sin(Math.toRadians(a.getLongitude()-13)));*/
        s.setMaterial(new PhongMaterial(color));
        return s;
    }

    public void displayRedSphere(Aeroport a){
        this.getChildren().add(createSphere(a,Color.RED));
    }




}
