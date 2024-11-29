import java.lang.reflect.Array;
import java.util.ArrayList;

public class PhysicEngine implements Engine{
    private ArrayList<DynamicSprite> movingSpriteList;
    private ArrayList<ArrayList<Sprite>> environment;
    ListPlayground listPlayground;

    public PhysicEngine(ListPlayground listPlayground) {
        {
            this.listPlayground = listPlayground;
            movingSpriteList = new ArrayList<>();
            this.environment = new ArrayList<ArrayList<Sprite>>();
            for (Playground playground : listPlayground.playgroundList) {
                ArrayList<Sprite> temp = playground.getSolidSpriteList();
                temp.addAll(playground.getInteractiveSpriteList());
                environment.add(temp);
            }
        }

//    public void addToEnvironmentList(Sprite sprite){
//        if (!environment.contains(sprite)){
//            environment.add(sprite);
//        }
//    }
//
//    public void setEnvironment(ArrayList<Sprite> environment){
//        this.environment=environment;
//    }
    }

    public void addToMovingSpriteList(DynamicSprite sprite){
        if (!movingSpriteList.contains(sprite)) {
            movingSpriteList.add(sprite);
        }
    }


    @Override
    public void update() {
        for(DynamicSprite dynamicSprite : movingSpriteList){
            dynamicSprite.moveIfPossible(environment.get(listPlayground.elementDisplayed));
        }
    }
}
