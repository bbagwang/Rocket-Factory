package navi_studio.rocket_fectory.Object.Core;

import android.graphics.Canvas;

import navi_studio.rocket_fectory.Global.Camera;

public class ObjLow {
    // Update 와 Render 만 존재하는 오브젝트입니다.

    protected boolean destroy = false; // 파괴할때 사용중합니다.

    // true 일 경우 파괴합니다.
    public boolean Update() {
        return destroy;
    }

    public void Render(Canvas canvas){

    }

    public void Render(Canvas canvas, Camera camera) {

    }
}
