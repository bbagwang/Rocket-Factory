package navi_studio.rocket_fectory.Object.Basic;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

import navi_studio.rocket_fectory.Function.Vector2;
import navi_studio.rocket_fectory.Global.Camera;
import navi_studio.rocket_fectory.Global.FPS;
import navi_studio.rocket_fectory.Global.Global;
import navi_studio.rocket_fectory.Global.Target;
import navi_studio.rocket_fectory.Object.Core.Object;
import navi_studio.rocket_fectory.Object.Core.ObjectCode;
import navi_studio.rocket_fectory.Part.ItemCell;

/**
 * Created by beanb on 2018-04-02.
 */

public class Conveyor extends Object {

    public final static int WagonSize                = 30;         // 수레길 길이당 크기

    protected Building start;      // 시작
    protected Building target;      // 타겟
    protected Paint paint;          // 페인트

    protected boolean completed;  // 건설 완료
    protected boolean power;  // 스위치

    protected ItemCell[] requiredItem;      // 건설에 필요한 아이템
    protected boolean[] checkItem;            // 투입된 아이템

    protected int storageCode;                  // 담겨있는 아이템 코드
    protected int storageMaxSize;                  // 최대용량          ( 길이에 따른 용량 )
    protected int storageSize;                  // 현재 용량

    public void Create(Building _start, Building _target, int _size, int _speed, int Red, int Green, int Blue, int _setCode, int _storageLevel,Global global) {
        start = _start;
        target = _target;
        size = _size;
        speed = _speed;
        completed = false;
        power = false;
        setPaint(30, Red, Green, Blue);
        setCode(_setCode);
        float temp = start.getPos().ForDistance(target.getPos()) / global.blackSide.UnitConversion(80);

        if(temp < 1)
            temp = 1;

        storageMaxSize = (int) temp * _storageLevel;
        storageCode = ObjectCode.Empty;
        storageSize = 0;
    }

    public void Create(Building _start, Building _target, Global global) { }

    public void RequiredItemSetting(ItemCell[] itemCells){
        requiredItem = itemCells;

        checkItem = new boolean[requiredItem.length];

    }

    public void insertItem(int Number){
        checkItem[Number] = true;
    }

    public final ItemCell[] RequiredItem(){
        return requiredItem;
    }

    public final boolean[] CheckItem(){
        return checkItem;
    }

    public final boolean isCompleted(){
        return completed;
    }

    public final boolean isPower(){
        return power;
    }

    public void setPower(boolean _power){
        power = _power;
    }

    public void Completed(){
        paint.setAlpha(200);
        completed = true;
    }

    // 페인트 설정
    private void setPaint(int Alpha, int Red, int Green, int Blue) {
        paint = new Paint();
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(size);
        paint.setARGB(Alpha, Red, Green, Blue);
    }

    @Override
    public boolean Update() {
        return destroy;
    }

    @Override
    public void Render(Canvas canvas, Camera camera) {
        canvas.drawLine(
                start.getPos().x - (camera.getPos().x - (camera.getSizeWidth() / 2)), start.getPos().y  - (camera.getPos().y - (camera.getSizeHeight() / 2)),
                target.getPos().x - (camera.getPos().x - (camera.getSizeWidth() / 2)), target.getPos().y  - (camera.getPos().y - (camera.getSizeHeight() / 2)), paint);
    }

    public final Building getTarget() {
        return target;
    }


    public final Building getStart() {
        return start;
    }

    public void setID(int _ID) {
        if (ID == 0)
            ID = _ID;
    }

    public final int getSpeed(){return speed;};

    public final int getCode() {
        return code;
    }

    public final int getID() {
        return ID;
    }

    public void setSelect(boolean _select) {
        select = _select;
    }

    public final boolean getSelect() {
        return select;
    }
}
