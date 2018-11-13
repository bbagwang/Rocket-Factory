package navi_studio.rocket_fectory.Progress;

import android.graphics.Canvas;

import navi_studio.rocket_fectory.Interface.sceneInterface;
import navi_studio.rocket_fectory.scene.Manager.sceneManager;

/**
 * Created by beanb on 2018-03-11.
 */

public class AssistantProgress extends sceneInterface {

    private sceneManager Manager;

    @Override
    public int Awake(int progress){
        Manager = new sceneManager();
        Manager.Initialize(global);
        Manager.Start();
        return  success;
    }

    @Override
    public void Start(){

    }

    @Override
    public void FixedUpdate(){
        Manager.FixedUpdate();
    }

    @Override
    public void InputEvent	(){
        Manager.InputEvent();
    }

    @Override
    public void UIUpdate(){
        Manager.UIUpdate();
    }

    @Override
    public String Update(){
        Next = Manager.Update();
        return  Next;
    }

    @Override
    public void LateUpdate	(){
        Manager.LateUpdate();
    }

    @Override
    public void Render(Canvas canvas){
        Manager.Render(canvas);
    }

    @Override
    public void LastRender	(Canvas canvas){
        Manager.LastRender(canvas);
    }

    @Override
    public void UIRender(Canvas canvas){
        Manager.UIRender(canvas);
    }

    @Override
    public void Disable(){
        Manager.Disable();
    }

    @Override
    public void Destroy(){
        Manager.Destroy();
    }

    @Override
    public void Exception(){
        Manager.Exception();
    }
}
