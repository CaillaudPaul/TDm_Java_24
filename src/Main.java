import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Main {

    JFrame displayZoneFrame;

    RenderEngine renderEngine;
    GameEngine gameEngine;
    PhysicEngine physicEngine;

    public Main() throws Exception{
        displayZoneFrame = new JFrame("Java Labs");
        displayZoneFrame.setSize(1550,900);
        displayZoneFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        ListPlayground listLevel = new ListPlayground();
        Player hero = new Player(200,300,
                ImageIO.read(new File("./img/heroTileSheetLowRes.png")),48,50, 5, listLevel);

        renderEngine = new RenderEngine(listLevel);
        physicEngine = new PhysicEngine(listLevel);
        gameEngine = new GameEngine(hero);

        Timer renderTimer = new Timer(20,(time)-> renderEngine.update());
        Timer gameTimer = new Timer(50,(time)-> gameEngine.update());
        Timer physicTimer = new Timer(20,(time)-> physicEngine.update());

        renderTimer.start();
        gameTimer.start();
        physicTimer.start();

        displayZoneFrame.getContentPane().add(renderEngine);
        displayZoneFrame.setVisible(true);


        //SolidSprite testSprite = new DynamicSprite(100,100,test,0,0);
        //renderEngine.addToRenderList(listLevel.playgroundList.get(listLevel.elementDisplayed).getSpriteList());
        for(int i = 0; i < listLevel.playgroundList.size(); i++){
            renderEngine.addToRenderList(hero, i);
        }
        physicEngine.addToMovingSpriteList(hero);
        ArrayList<Sprite> allenvironment = (listLevel.playgroundList.get(listLevel.elementDisplayed).getSolidSpriteList());
        allenvironment.addAll(listLevel.playgroundList.get(listLevel.elementDisplayed).getInteractiveSpriteList());
        //physicEngine.setEnvironment(allenvironment);

        displayZoneFrame.addKeyListener(gameEngine);
    }

    public static void main (String[] args) throws Exception {
	// write your code here
        Main main = new Main();
    }
}
