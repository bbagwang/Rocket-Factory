package navi_studio.rocket_fectory.Action;

import android.graphics.Canvas;

import navi_studio.rocket_fectory.Global.Global;
import navi_studio.rocket_fectory.Object.Core.ObjLow;

public class SaveAction extends ObjLow {

    private Global global;

    // destroy = true 일 경우 파괴
    public void Create(Global _global){

        global = _global;

    }

    public void set(int Empty){

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
