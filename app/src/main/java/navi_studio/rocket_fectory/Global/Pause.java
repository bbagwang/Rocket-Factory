package navi_studio.rocket_fectory.Global;

public class Pause {
    private boolean pause;

    public Pause(){
        pause = false;
    }
    public void set(boolean setting){
        pause = setting;
    }

    public boolean isPause(){
        return pause;
    }
}
