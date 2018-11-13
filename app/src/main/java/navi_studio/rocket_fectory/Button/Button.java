package navi_studio.rocket_fectory.Button;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import navi_studio.rocket_fectory.Global.InputManager;

import static java.lang.Math.sqrt;

/**
 * Created by beanb on 2018-03-11.
 */

public class Button {

    public final static int BASIC       =   100;        // 애니메이션 없음, 왼쪽 상단기준
    public final static int CENTER      =   101;        // 중앙기준
    public final static int LTOUCH      =   102;        // 눌린 상태에서 고정
    public final static int STOUCH      =   103;        // 터치되는 순간만 판단
    public final static int CIRCLE      =   104;        // 터치범위 동그라미
    public final static int RECTANGLE   =   105;        // 터치범위 직사각형

    protected boolean Click;

    protected int  PosX;
    protected int  PosY;
    protected int  SizeX;
    protected int  SizeY;
    protected int  TouchType;                             // 터치 타입을 저장합니다. (동그라미, 직사각형)

    protected Bitmap image;                                // 기본 이미지

    public void Create(int _PosMode, int _PosX, int _PosY, int _SizeX, int _SizeY, Bitmap _image, int _TouchType){
        switch (_PosMode){
            // 버튼의 위치는 왼쪽 상단을 기준으로 저장됩니다.
            case BASIC:
                SizeX = _SizeX;
                SizeY = _SizeY;
                PosX = _PosX + (SizeX / 2);
                PosY = _PosY + (SizeY / 2);
                break;
            // 버튼의 위치는 중앙을 기준으로 저장됩니다.
            case CENTER:
                SizeX = _SizeX;
                SizeY = _SizeY;
                PosX = _PosX;
                PosY = _PosY;
                break;
        }

        image = Bitmap.createScaledBitmap(_image, _SizeX, _SizeY, true);
        TouchType = _TouchType;
        Click = false;
    }

    public void Create(int _PosMode, int _PosX, int _PosY, Bitmap _image, int _TouchType){
        switch (_PosMode){
            // 버튼의 위치는 왼쪽 상단을 기준으로 저장됩니다.
            case BASIC:
                SizeX = _image.getWidth();
                SizeY = _image.getHeight();
                PosX = _PosX + (SizeX / 2);
                PosY = _PosY + (SizeY / 2);
                break;
            // 버튼의 위치는 중앙을 기준으로 저장됩니다.
            case CENTER:
                SizeX = _image.getWidth();
                SizeY = _image.getHeight();
                PosX = _PosX;
                PosY = _PosY;
                break;
        }

        image = _image;
        TouchType = _TouchType;
        Click = false;
    }

    public boolean TouchCheck(int touchPosX,int touchPosY,int touchType){
        if(touchType == InputManager.UP){
            Click = false;
            return Click;
        }
        switch (TouchType) {
            case CIRCLE:
                // SizeX 랑 SizeY 를 비교해서 더 작은 쪽을 원의 범위로 사용합니다.
                if(SizeX <= SizeY) {
                    if ((SizeX / 2) >= sqrt(((touchPosX - PosX) * (touchPosX - PosX)) + ((touchPosY - PosY) * (touchPosY - PosY)))) {
                        Click = true;
                    }
                    else {
                        Click = false;
                    }
                }
                else {
                    if ((SizeY / 2) >= sqrt(((touchPosX - PosX) * (touchPosX - PosX)) + ((touchPosY - PosY) * (touchPosY - PosY)))) {
                        Click = true;
                    }
                    else {
                        Click = false;
                    }
                }
                return Click;
            case RECTANGLE:
                if((PosX - (SizeX / 2)) <= touchPosX && (PosX + (SizeX / 2)) >= touchPosX &&
                        (PosY - (SizeY / 2)) <= touchPosY && (PosY + (SizeY / 2)) >= touchPosY){
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

    public boolean isClick(){
        return Click;
    }

    public void Render(Canvas canvas){
        canvas.drawBitmap(image, PosX - (SizeX / 2), PosY - (SizeY / 2), null);
    }
}
