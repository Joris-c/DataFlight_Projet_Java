import javafx.animation.AnimationTimer;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.CullFace;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

public class Earth extends Group {
    private Sphere sph = new Sphere(300);
    private Sphere s = new Sphere(2);

    private ArrayList<Sphere> list_airport = null;
    boolean behindEarth = false;

    private Rotate ry = new Rotate();
    private Rotate rx = new Rotate();
    private PhongMaterial map = new PhongMaterial();

    private  final double R = 300;

    private int var = 0;

    public Earth() {
        try {
            map.setDiffuseMap(new Image(new File("src/ressource/earth_lights_4800.png").toURI().toURL().toExternalForm()));
            //map.setSelfIlluminationMap(new Image(new File("src/ressource/earth_lights_4800.png").toURI().toURL().toExternalForm()));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        ry.setAxis(Rotate.Y_AXIS);
        rx.setAxis(Rotate.X_AXIS);
        this.sph.toFront();
        this.sph.setMaterial(map);
        this.getChildren().add(sph);
        this.getTransforms().add(ry);
        this.getTransforms().add(rx);
        //sph.getTransforms().add(ry);
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long time) {
                ry.setAngle(360*(time*1e-9)/15);
                double z_airport = s.localToScene(s.getTranslateX(), s.getTranslateY(), s.getTranslateZ()).getZ();
                double z_earth = localToScene(sph.getTranslateX(), sph.getTranslateY(), sph.getTranslateZ()).getZ();
                if(z_airport>(z_earth-110)){
                    behindEarth = false;
                }else {
                    behindEarth = true;
                }
                s.setVisible(behindEarth);
                for (Node child: getManagedChildren()
                     ) {
                    double z = child.localToScene(child.getTranslateX(), child.getTranslateY(), child.getTranslateZ()).getZ();
                    if(z>(z_earth-110)){
                        behindEarth = false;
                    }else {
                        behindEarth = true;
                    }

                }


                /*if (list_airport != null) {
                    for (Sphere sph_airport : list_airport
                    ) {
                        double z = sph_airport.localToScene(sph_airport.getTranslateX(), sph_airport.getTranslateY(), sph_airport.getTranslateZ()).getZ();
                        if (z > (z_earth - 110)) {
                            behindEarth = false;
                        } else {
                            behindEarth = true;
                        }
                        sph_airport.setVisible(behindEarth);

                    }

                }*/

            }
        };
        animationTimer.start();
    }

    public Sphere createSphere(Aeroport a, Color color){
        if(a != null) {
            Sphere new_sphere = new Sphere(2);
            new_sphere.setTranslateX(300 * Math.cos(Math.toRadians(a.getLatitude() * 0.65)) * Math.sin(Math.toRadians(a.getLongitude())));
            new_sphere.setTranslateY(-300 * Math.sin(Math.toRadians(a.getLatitude() * 0.65)));
            new_sphere.setTranslateZ(-300 * Math.cos(Math.toRadians(a.getLatitude() * 0.65)) * Math.cos(Math.toRadians(a.getLongitude())));
        /*s.setTranslateX(150 * Math.cos(Math.toRadians(a.getLatitude()-13))*Math.sin(Math.toRadians(a.getLongitude()-13)));
        s.setTranslateY(-150 * Math.sin(Math.toRadians(a.getLatitude()-13)));
        s.setTranslateZ(-150 * Math.cos(Math.toRadians(a.getLatitude()-13))*Math.sin(Math.toRadians(a.getLongitude()-13)));*/
            new_sphere.setMaterial(new PhongMaterial(color));
            System.out.println("a = " + a);
            return new_sphere;
        }
        return null;
    }

    public void displayRedSphere(Aeroport a){
        if(a != null) {
            if(this.getManagedChildren().contains(this.s)){
                this.s = createSphere(a, Color.RED);
            }
            else {
                Sphere new_sph = createSphere(a, Color.RED);
                if(new_sph != null){
                    this.getChildren().add(new_sph);
                }

            }

        }
        else {
            throw new IllegalArgumentException("Aeroport null");
        }
    }

    public void displayYellowSphere(ArrayList<Flight> listOfFlight){
        //this.list_airport = null;
        if(listOfFlight != null) {
            for (Flight f: listOfFlight
                 ) {
                if (f != null){
                    Sphere new_sph = createSphere(f.getDeparture(), Color.YELLOW);
                    if(new_sph != null){
                        //this.getChildren().add(new_sph);
                    }
                }
            }
        }
        else {
            throw new IllegalArgumentException("liste vide");
        }
    }


    public void Earth_rotate_X(boolean x){
        int i = 0;
        while (i<20){
            if(x){
                rx.setAngle(rx.getAngle()+(1));
                i++;
            }
            else {
                rx.setAngle(rx.getAngle()-(1));
                i++;
            }

        }

    }

}
