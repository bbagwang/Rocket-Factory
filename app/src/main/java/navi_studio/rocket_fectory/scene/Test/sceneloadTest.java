package navi_studio.rocket_fectory.scene.Test;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import navi_studio.rocket_fectory.Global.Target;
import navi_studio.rocket_fectory.Interface.loadsceneInterface;

/**
 * Created by beanb on 2018-03-11.
 */

public class sceneloadTest extends loadsceneInterface {

    Paint paint;
    String buff;

    @Override
    public int Awake(int progress) {
        return success;
    }

    @Override
    public  void Start(){
        if(Target.test) {
            paint = new Paint();
            paint.setColor(Color.WHITE);
            paint.setTextSize(global.blackSide.FontUnitConversion(50));
            paint.setTextAlign(Paint.Align.CENTER);
        }
    }

    @Override
    public void LoadUpdate(){

    }
    @Override
    public void LoadRender(Canvas canvas){
        if(Target.test) {
            buff = "Load : " + (loading / 100);
            canvas.drawText(buff,
                    global.blackSide.UnitConversion(960, global.blackSide.width),
                    global.blackSide.UnitConversion(800, global.blackSide.height),
                    paint);
        }
    }
    @Override
    public void Destroy(){
        paint = null;
        buff = null;
    }
}
