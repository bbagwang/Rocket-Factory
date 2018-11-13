package navi_studio.rocket_fectory.Interface;

import android.graphics.Canvas;

/**
 * Created by beanb on 2018-03-11.
 */

public class loadsceneInterface extends sceneInterface {

    // 아래의 함수를 재정의 하여 사용하십시오.
    @Override
    public int Awake(int progress) {
        Next = "";
        return 0;
    }

    // 아래의 함수를 재정의 하여 사용하십시오.
    @Override
    public void LoadUpdate(){ }

    // 아래의 함수를 재정의 하여 사용하십시오.
    @Override
    public void LoadRender(Canvas canvas){ }

    @Override
    public String Update() {
        return Next;
    }
}
