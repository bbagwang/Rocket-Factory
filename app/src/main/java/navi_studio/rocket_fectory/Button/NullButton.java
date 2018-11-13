package navi_studio.rocket_fectory.Button;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import navi_studio.rocket_fectory.Function.Vector2;
import navi_studio.rocket_fectory.Global.Global;
import navi_studio.rocket_fectory.Global.InputManager;
import navi_studio.rocket_fectory.Global.Target;

import static java.lang.Math.sqrt;

/**
 * Created by beanb on 2018-03-26.
 */

// 이미지가 없는 버튼 입니다.
public class NullButton {

    private Global global;
    private Paint paint;
    public final static int BASIC = 100;        // 애니메이션 없음, 왼쪽 상단기준
    public final static int CENTER = 101;        // 중앙기준

    public final static int CIRCLE = 104;        // 터치범위 동그라미
    public final static int RECTANGLE = 105;        // 터치범위 직사각형

    protected boolean Click;
    protected boolean first;
    protected boolean In;

    protected Vector2 position;
    protected Vector2 size;
    protected int TouchType;                             // 터치 타입을 저장합니다. (동그라미, 직사각형)

    public void Create(int _PosMode, int _PosX, int _PosY, int _SizeX, int _SizeY, int _TouchType, Global _global) {

        if(Target.test) {
            paint = new Paint();
            paint.setARGB(120,255,0,0);
        }

        global = _global;
        position = new Vector2();
        size = new Vector2();
        switch (_PosMode) {
            // 버튼의 위치는 왼쪽 상단을 기준으로 저장됩니다.
            case BASIC:
                size.x = _SizeX;
                size.y = _SizeY;
                position.x = _PosX + (size.x / 2);
                position.y = _PosY + (size.y / 2);
                break;
            // 버튼의 위치는 중앙을 기준으로 저장됩니다.
            case CENTER:
                size.x = _SizeX;
                size.y = _SizeY;
                position.x = _PosX;
                position.y = _PosY;
                break;
        }
        TouchType = _TouchType;
        Click = false;
    }

    public void touchStart() {
        if (TouchCheck())
            In = true;
    }

    public void touchEnd() {
        if (TouchCheck() && In)
            Click = true;
    }

    public boolean TouchCheck() {
        switch (TouchType) {
            case CIRCLE:
                // SizeX 랑 SizeY 를 비교해서 더 작은 쪽을 원의 범위로 사용합니다.
                if (size.x <= size.y) {
                    if ((size.x / 2) >= sqrt(
                            ((global.inputManager.touch(0).x() - position.x) * (global.inputManager.touch(0).x() - position.x)) +
                                    ((global.inputManager.touch(0).y() - position.y) * (global.inputManager.touch(0).y() - position.y)))) {
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    if ((size.y / 2) >= sqrt(
                            ((global.inputManager.touch(0).x() - position.x) * (global.inputManager.touch(0).x() - position.x)) +
                                    ((global.inputManager.touch(0).y() - position.y) * (global.inputManager.touch(0).y() - position.y)))) {
                        return true;
                    } else {
                        return false;
                    }
                }
            case RECTANGLE:
                if ((position.x - (size.x / 2)) <= global.inputManager.touch(0).x() && (position.x + (size.x / 2)) >= global.inputManager.touch(0).x() &&
                        (position.y - (size.y / 2)) <= global.inputManager.touch(0).y() && (position.y + (size.y / 2)) >= global.inputManager.touch(0).y()) {
                    return true;
                } else {
                    return false;
                }
            default:
                return false;
        }
    }

    public void Update() {

        if (first) {
            if (global.inputManager.touch(0).type() == global.inputManager.DOWN)    // 터치가 시작되는 순간
            {
                In = false;
                touchStart();
                first = false;
                return;
            }
        } else {
            if (global.inputManager.touch(0).type() == global.inputManager.UP) { // 터치끝
                touchEnd();
                first = true;
                return;
            }
        }
    }

    public boolean TouchCheck(int touchPosX,int touchPosY,int touchType){
        if(touchType == InputManager.UP){
            Click = false;
            return Click;
        }
        switch (TouchType) {
            case CIRCLE:
                // SizeX 랑 SizeY 를 비교해서 더 작은 쪽을 원의 범위로 사용합니다.
                if(size.x <= size.y) {
                    if ((size.x / 2) >= sqrt(((touchPosX - position.x) * (touchPosX - position.x)) +
                            ((touchPosY - position.y) * (touchPosY - position.y)))) {
                        Click = true;
                    }
                    else {
                        Click = false;
                    }
                }
                else {
                    if ((size.y / 2) >= sqrt(((touchPosX - position.x) * (touchPosX - position.x)) +
                            ((touchPosY - position.y) * (touchPosY - position.y)))) {
                        Click = true;
                    }
                    else {
                        Click = false;
                    }
                }
                return Click;
            case RECTANGLE:
                if((position.x - (size.x / 2)) <= touchPosX && (position.x + (size.x / 2)) >= touchPosX &&
                        (position.y - (size.y / 2)) <= touchPosY && (position.y + (size.y / 2)) >= touchPosY){
                    Click = true;
                }
                else{
                    Click = false;
                }
                return Click;
            default:
                return false;
        }
    }

    public void Render(Canvas canvas){

        if(Target.test) {
            canvas.drawRect(
                    position.x - (size.x / 2),
                    position.y - (size.y / 2),
                    position.x + (size.x / 2),
                    position.y + (size.y / 2),
                    paint
            );
        }
    }

    public boolean isClick() {
        if(Click) {
            Click = false;
            return true;
        }
        return false;
    }
}