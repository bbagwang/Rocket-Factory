package navi_studio.rocket_fectory.Global;

import android.graphics.Bitmap;

public class Character {

    private Global global;

    private Bitmap[][] Image;

    public Character(Global _global){
        global = _global;
    }

    public Bitmap[][] getImage(){
        return Image;
    }
}
