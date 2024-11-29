import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class RenderEngine extends JPanel implements Engine{
    ListPlayground listPlayground;
    private ArrayList<ArrayList<Displayable>> renderList;

    public RenderEngine(ListPlayground listPlayground) {
        this.listPlayground = listPlayground;
        renderList = new ArrayList<ArrayList<Displayable>>();
        for (Playground playground : listPlayground.playgroundList){
            renderList.add(playground.getSpriteList());
        }
    }

    public void addToRenderList(Displayable displayable, int index){
        if (!renderList.contains(displayable)){
            renderList.get(index).add(displayable);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (Displayable renderObject:renderList.get(listPlayground.elementDisplayed)){
            renderObject.draw(g);
        }
    }

    @Override
    public void update(){
        this.repaint();
    }
}
