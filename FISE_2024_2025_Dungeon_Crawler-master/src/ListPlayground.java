import java.util.ArrayList;

public class ListPlayground {
    public ArrayList<Playground> playgroundList = new ArrayList<>();
    public int elementDisplayed = 0;

    public ListPlayground(){
        Playground level1 = new Playground("./data/level1.txt");
        playgroundList.add(level1);

        Playground level2 = new Playground("./data/level2.txt");
        playgroundList.add(level2);
    }

    public void setElementDisplayed(int elementDisplayed) {
        this.elementDisplayed = elementDisplayed;
    }
}
