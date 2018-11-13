package navi_studio.rocket_fectory.Interface;

import android.content.Context;
import android.graphics.Canvas;

import navi_studio.rocket_fectory.Global.BlackSide;
import navi_studio.rocket_fectory.Global.FPS;
import navi_studio.rocket_fectory.Global.Global;
import navi_studio.rocket_fectory.Global.InputManager;
import navi_studio.rocket_fectory.Global.Option;
import navi_studio.rocket_fectory.Global.ScreenSize;
import navi_studio.rocket_fectory.Global.Target;
import navi_studio.rocket_fectory.Global.dTime;

/**
 * Created by beanb on 2018-03-11.
 */

public class sceneInterface {
    public final static String Running = "Running";
    public final static String End = "End";
    public final static int success = 10000;
    public int loading = 0;

    public Global global;                                  // 모든 곳에 사용되는 전역 변수 입니다.

    public String Next = "End";    // 다음 씬 이름을 적습니다. End 일 경우 프로그램 종료가 실행됩니다.

    public void Initialize(Global _global){
        global = _global;
    }

    // 다음 씬의 Awake 를 1회 실행하고 반환값을 loading 에 저장합니다.
    public int LoadScene(sceneInterface scene){
        loading = scene.Awake(loading);
        if(loading > success)
            loading = success;
        return  loading;
    }

    public void LoadUpdate(){

    }   // 로딩 씬 에서만 사용됩니다.

    public void LoadRender(Canvas canvas){

    }   // 로딩 씬 에서만 사용됩니다.

    public int Awake(int progress){
        return  success;
    }

    public void Start(){

    }

    public void FixedUpdate(){

    }

    public void InputEvent	(){

    }

    public void UIUpdate(){

    }

    public String Update(){
        return  Running;
    }

    public void LateUpdate	(){

    }

    public void Render(Canvas canvas){

    }

    public void LastRender	(Canvas canvas){

    }

    public void UIRender(Canvas canvas){

    }

    public void Disable(){

    }

    public void Destroy(){

    }
    public void Exception(){

    }
}
