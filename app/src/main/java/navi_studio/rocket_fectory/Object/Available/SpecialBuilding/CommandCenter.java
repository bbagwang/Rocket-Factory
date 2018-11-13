package navi_studio.rocket_fectory.Object.Available.SpecialBuilding;

import android.graphics.Bitmap;

import navi_studio.rocket_fectory.Function.LocaleManager;
import navi_studio.rocket_fectory.Function.Vector2;
import navi_studio.rocket_fectory.Global.Global;
import navi_studio.rocket_fectory.Object.Basic.Building;
import navi_studio.rocket_fectory.Object.Core.ObjectCode;

public class CommandCenter extends Building {

    LocaleManager localeManager;

    private boolean Jump;

    public void Create(Vector2 _pos, Bitmap[] _image, Global _global) {
        Create(
                _pos,
                _global.blackSide.UnitConversion(220),
                _image,_image,
                0,0,
                0
                , ObjectCode.CommandCenter,true);
        Action = 2;
        Jump = false;

        isCompleted = true;
        Switch = true;

        localeManager = new LocaleManager();
        localeManager.init("BuildingCommandCenter");

        Command = new String[Action];

        Command[0] = localeManager.getScriptByNum("001001C001");
        Command[1] = localeManager.getScriptByNum("001001C002");

        localeManager = null;
    }

    public int close(int command){
        if(command == 0) {
            Jump = true;
        }else if(command == 1){

        }
        return 0;
    }

    public boolean IsJump(){
        return Jump;
    }

}
