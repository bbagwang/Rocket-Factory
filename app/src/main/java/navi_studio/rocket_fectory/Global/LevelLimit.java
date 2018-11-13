package navi_studio.rocket_fectory.Global;

public class LevelLimit {

    private Global global;

    private int thisLevel;

    public LevelLimit(Global _global){
        global = _global;
        thisLevel = 1;
    }

    public void Create(){

    }

    public final int getThisLevel(){
        return thisLevel;
    }

}
