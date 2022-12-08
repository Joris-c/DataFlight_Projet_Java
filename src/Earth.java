import javafx.scene.Group;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;

import java.util.ArrayList;

public class Earth extends Group {

    Sphere sph = new Sphere(300);


    public Earth() {
        this.getChildren().add(sph);
    }
}
