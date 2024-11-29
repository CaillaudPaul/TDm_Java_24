import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Player extends DynamicSprite{
    protected int health = 5;
    Rectangle2D.Double hitbox = new Rectangle2D.Double();
    private int hitboxXOffset = 0;
    private int hitboxYOffset = 0;

    private long lastTimeHit = 0;
    private final long timeBetweenHit = 2000;
    ListPlayground listPlayground;


    public Player(double x, double y, Image image, double width, double height, int health, ListPlayground listPlayground) {
        super(x, y, image, width, height);
        this.health = health;
        //this.hitbox = hitbox;
        setHitbox(10, 18, 27, 28);
        this.listPlayground = listPlayground;
    }

    public void setHitbox(int xOffset, int yOffSet, int width, int height){
        this.hitboxXOffset = xOffset;
        this.hitboxYOffset = yOffSet;
        this.hitbox.x = this.x + xOffset;
        this.hitbox.y = this.y + yOffSet;
        this.hitbox.width = width;
        this.hitbox.height = height;
        //System.out.println("Hitbox updated: x=" + hitbox.x + ", y=" + hitbox.y + ", width=" + hitbox.width + ", height=" + hitbox.height);
    }

    private boolean canBeHit(){
        long currentTime = System.currentTimeMillis();
        return (currentTime - lastTimeHit) > timeBetweenHit;
    }

    @Override
    protected boolean checkCollision(ArrayList<Sprite> environment) {
        double futurX = this.x;
        double futurY = this.y;
        switch(direction){
            case EAST:
                futurX += speed;
                break;
            case WEST:
                futurX -= speed;
                break;
            case NORTH:
                futurY -= speed;
                break;
            case SOUTH:
                futurY += speed;
                break;
            }
        this.hitbox.x = this.hitboxXOffset + futurX;
        this.hitbox.y = this.hitboxYOffset + futurY;
        for(Sprite s : environment){
            if((s instanceof SolidSprite) && ((SolidSprite)s).intersect(this.hitbox)) {
                return false;
            }
        }
        return true;
    }

    private void checkInteraction(ArrayList<Sprite> environment){
        double futurX = this.x;
        double futurY = this.y;
        switch(direction){
            case EAST:
                futurX += speed;
                break;
            case WEST:
                futurX -= speed;
                break;
            case NORTH:
                futurY -= speed;
                break;
            case SOUTH:
                futurY += speed;
                break;
        }
        this.hitbox.x = this.hitboxXOffset + futurX;
        this.hitbox.y = this.hitboxYOffset + futurY;
        for (Sprite s : environment){
            if((s instanceof InteractiveSprite) && ((InteractiveSprite)s).intersect(this.hitbox)){
                String name = ((InteractiveSprite)s).getName();
                switch (name){
                    case "Trap":
                        if (this.speed > 2 && canBeHit()) {
                            this.health = this.health - 1;
                            System.out.println("Hero's health is " + this.health);
                            if (this.health == 0) {
                                System.out.println("You lose!");
                            }
                            lastTimeHit = System.currentTimeMillis();
                        }
                        break;
                    case "EndLevel":
                        if (canBeHit()) {
                            this.listPlayground.setElementDisplayed((this.listPlayground.elementDisplayed + 1) % 2);
                            if(this.listPlayground.elementDisplayed == 1) {
                                this.y -= 704; //height of11 sprites
                            }else{
                                this.y += 704;
                            }
                        }
                        break;
                }
            }
        }
    }

    @Override
    public void moveIfPossible(ArrayList<Sprite> environment) {
        if (isWalking) {
            if (checkCollision(environment)) {
                move();
                setHitbox(hitboxXOffset, hitboxYOffset, 27, 28);
                checkInteraction(environment);
            }
        }
    }
}
