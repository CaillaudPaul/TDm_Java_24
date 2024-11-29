import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameEngine implements Engine, KeyListener {
    Player hero;

    public GameEngine(Player hero) {
        this.hero = hero;
    }

    @Override
    public void update() {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()){
            case KeyEvent.VK_Z :
                hero.setDirection(Direction.NORTH);
                hero.isWalking = true;
                break;
            case KeyEvent.VK_S:
                hero.setDirection(Direction.SOUTH);
                hero.isWalking = true;
                break;
            case KeyEvent.VK_Q:
                hero.setDirection(Direction.WEST);
                hero.isWalking = true;
                break;
            case KeyEvent.VK_D:
                hero.setDirection(Direction.EAST);
                hero.isWalking = true;
                break;
            case KeyEvent.VK_CONTROL:
                hero.speed = 8;
                break;
            case KeyEvent.VK_SHIFT:
                hero.speed = 2;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        hero.isWalking = false;
        if(e.getKeyCode() == KeyEvent.VK_CONTROL){
            hero.speed = 4;
        }
        if(e.getKeyCode() == KeyEvent.VK_SHIFT){
            hero.speed = 4;
        }
    }
}
