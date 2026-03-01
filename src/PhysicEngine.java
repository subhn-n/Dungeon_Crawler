import java.util.ArrayList;


public class PhysicEngine implements Engine{
    ArrayList<DynamicSprite> movingSpriteList = new ArrayList<>();
    ArrayList<Sprite> environment = new ArrayList<>();

    public void addMovingSpriteList(DynamicSprite s){
        movingSpriteList.add(s);
    }

    public void setEnvironment(ArrayList<Sprite> environment) {
        this.environment = environment;
    }

    public void clearSprites(){
        movingSpriteList.clear();
        //environment.clear();
    }


    @Override
    public void update() {
       // movingSpriteList.forEach(s -> s.moveIfPossible(environment));
    }


}
