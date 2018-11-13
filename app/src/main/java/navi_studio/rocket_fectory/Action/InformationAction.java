package navi_studio.rocket_fectory.Action;

import android.graphics.Canvas;

import navi_studio.rocket_fectory.Global.Global;
import navi_studio.rocket_fectory.Object.Core.ObjLow;
import navi_studio.rocket_fectory.Object.Manager.ObjManager;

public class InformationAction extends ObjLow {

    private Global global;
    private ObjManager objManager;

    private int storageCode;                        // 받아온 코드   오브젝트 코드

    // destroy = true 일 경우 파괴
    public void Create(Global _global, ObjManager _objManager){

        global = _global;
        objManager = _objManager;
    }

    public void set(int _storageCode){
        storageCode = _storageCode;
    }

    public boolean Destroy(){

        return true;
    }

    // 주 실행
    public boolean Update() {

        return true;
    }

    public void Render(Canvas canvas) {

    }

    // UI 랜더
    public void UIRender(Canvas canvas) {

    }
}
