package navi_studio.rocket_fectory.Object.Available.SpecialBuilding;

import android.graphics.Bitmap;

import navi_studio.rocket_fectory.Function.LocaleManager;
import navi_studio.rocket_fectory.Function.Vector2;
import navi_studio.rocket_fectory.Global.Global;
import navi_studio.rocket_fectory.Object.Basic.Building;
import navi_studio.rocket_fectory.Object.Core.ObjectCode;

public class TetoraCenter extends Building {

    LocaleManager localeManager;

    public int Level;

    private Global global;

    public void Initialize(Global _global) {
        global = _global;
    }

    public void Create(Vector2 _pos, Bitmap[] _image) {
        Level = global.levelLimit.getThisLevel();
        switch (Level) {
            case 1:
                Level = ObjectCode.TetoraCenterLevel1;
                break;
            case 2:
                Level = ObjectCode.TetoraCenterLevel2;
                break;
            case 3:
                Level = ObjectCode.TetoraCenterLevel3;
                break;
            case 4:
                Level = ObjectCode.TetoraCenterLevel4;
                break;
            case 5:
                Level = ObjectCode.TetoraCenterLevel5;
                break;
            case 6:
                Level = ObjectCode.TetoraCenterLevel6;
                break;
            case 7:
                Level = ObjectCode.TetoraCenterLevel7;
                break;
            case 8:
                Level = ObjectCode.TetoraCenterLevel8;
                break;
            case 9:
                Level = ObjectCode.TetoraCenterLevel9;
                break;
            case 10:
                Level = ObjectCode.TetoraCenterLevel10;
                break;
        }

        isCompleted = true;
        Switch = true;

        Create(
                _pos,
                global.blackSide.UnitConversion(500),
                _image, _image,
                0, 0,
                0
                , Level, true);
        Action = 1;

        isCompleted = true;
        Switch = true;

        localeManager = new LocaleManager();
        localeManager.init("BuildingTetoraCenter");

        Command = new String[Action];

        Command[0] = localeManager.getScriptByNum("001001C001");
    }

    @Override
    public int close(int command) {
        return 0;
    }

    public int getLevel() {
        return Level;
    }

}
