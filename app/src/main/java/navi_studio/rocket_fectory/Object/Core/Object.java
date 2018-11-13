package navi_studio.rocket_fectory.Object.Core;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import navi_studio.rocket_fectory.Function.Animation;
import navi_studio.rocket_fectory.Function.Vector2;
import navi_studio.rocket_fectory.Global.Camera;

import static java.lang.Math.sqrt;

/**
 * Created by Administrator on 2018-03-28.
 */

public class Object {
    // 기본 오브젝트 입니다.

    public final static int SpeedLevel1 = 60;       // 분당 생산량 1
    public final static int SpeedLevel2 = 48;       // 분당 생산량 1.25
    public final static int SpeedLevel3 = 36;       // 분당 생산량 1.67
    public final static int SpeedLevel4 = 24;       // 분당 생산량 2.5
    public final static int SpeedLevel5 = 12;       // 분당 생산량 5
    public final static int SpeedLevel6 = 6;        // 분당 생산량 10
    public final static int SpeedLevel7 = 3;        // 분당 생산량 20
    public final static int SpeedLevel8 = 1;        // 분당 생산량 60

    public final static int BUILDING = 100;    // 건물
    public final static int MATERIAL = 101;    // 자원
    public final static int CONVEYOR = 102;    // 컨베이어

    protected Vector2 position;   //오브젝트 위치 (컨베이어 일 경우 시작위치)
    protected boolean select = false;     //화면 범위 안에 있는가 체크 (기본 거짓)
    protected int speed;          // 속도
    protected int ID;              // 오브젝트 고유 ID
    protected int code;            // 오브젝트 공용 ID
    protected int size;             //오브젝트 크기
    protected int type;             // 터치 타입
    protected int speedCount;      // 속도 계산용 변수

    protected boolean destroy = false; // 파괴할때 사용중합니다.

    protected void Create(Vector2 _pos, int _size, int _type) {
        position = new Vector2();
        position = _pos;
        size = _size;
        type = _type;
    }

    public void setID(int _ID) {
        if (ID == 0)
            ID = _ID;
    }

    public int getCode() {
        return code;
    }

    public int getID() {
        return ID;
    }

    protected void setCode(int _code) {
        code = _code;
    }

    // 터치체크     * 컨테이어는 터치체크를 하지않습니다 *
    public boolean touchCheck(int _x, int _y) {

        switch (type) {
            case BUILDING:
                if ((size / 2) >= sqrt(((_x - position.x) * (_x - position.x)) + ((_y - position.y) * (_y - position.y)))) {
                    return true;
                } else {
                    return false;
                }
            case MATERIAL:
                if ((position.x - (size / 2)) <= _x && (position.x + (size / 2)) >= _x &&
                        (position.y - (size / 2)) <= _y && (position.y + (size / 2)) >= _y) {
                    return true;
                } else {
                    return false;
                }
        }
        return false;
    }

    public boolean Update() {
        return destroy;
    }

    public void Render(Canvas canvas, Camera camera) {

    }

    public Vector2 getPos() {
        return position;
    }

    public final int getPosX() {
        return position.x;
    }

    public final int getPosY() {
        return position.y;
    }

    public void setPos(int _x, int _y) {
        position.x = _x;
        position.y = _y;
    }

    public void setPos(Vector2 _other) {
        position = _other;
    }

    public void setPosX(int _x) {
        position.x = _x;
    }

    public void setPosY(int _y) {
        position.y = _y;
    }

    public void setSelect(boolean _select) {
        select = _select;
    }

    public boolean getSelect() {
        return select;
    }
}
