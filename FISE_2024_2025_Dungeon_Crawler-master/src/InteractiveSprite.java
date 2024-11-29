import java.awt.*;
import java.awt.geom.Rectangle2D;

public class InteractiveSprite extends Sprite{
    private String name;

    public InteractiveSprite(double x, double y, Image image, double width, double height, String name) {
        super(x, y, image, width, height);
        this.name = name;
    }

    public Rectangle2D getHitBox() {
        return new Rectangle2D.Double(x,y,(double) width,(double) height);
    }

    public boolean intersect(Rectangle2D.Double hitBox){
        return this.getHitBox().intersects(hitBox);
    }

    public String getName() {
        return this.name;
    }
}
