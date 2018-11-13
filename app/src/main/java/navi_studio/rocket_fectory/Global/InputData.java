package navi_studio.rocket_fectory.Global;

import navi_studio.rocket_fectory.Function.Vector2;

/**
 * Created by beanb on 2018-03-11.
 */

public class InputData {
    private int InputType;                          // 어떠한 입력신호가 들어왔는지 저장합니다.
    private int InputTouchPositionX;               // 입력 신호의 위치 X
    private int InputTouchPositionY;               // 입력 신호의 위치 Y
    private int InputID;

    public void setInputData(int type,int x,int y){
        InputType = type;
        InputTouchPositionX = x;
        InputTouchPositionY = y;
    }
    public int type(){
        return InputType;
    }
    public int id() { return  InputID; }
    public int x(){
        return InputTouchPositionX;
    }
    public int y(){
        return InputTouchPositionY;
    }
    public void type(int type){
        InputType = type;
    }
    public void id(int ID) { InputID = ID; }
    public void x(int x){
        InputTouchPositionX = x;
    }
    public void y(int y){
        InputTouchPositionY = y;
    }
}
