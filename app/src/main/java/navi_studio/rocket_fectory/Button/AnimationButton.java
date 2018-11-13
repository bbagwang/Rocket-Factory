package navi_studio.rocket_fectory.Button;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import navi_studio.rocket_fectory.Function.Vector2;
import navi_studio.rocket_fectory.Global.FPS;
import navi_studio.rocket_fectory.Global.Global;
import navi_studio.rocket_fectory.Global.InputManager;

import static java.lang.Math.sqrt;

public class AnimationButton {

    public final static int BASIC = 100;        // 애니메이션 없음, 왼쪽 상단기준
    public final static int CENTER = 101;        // 중앙기준
    public final static int LTOUCH = 102;        // 눌린 상태에서 고정
    public final static int STOUCH = 103;        // 터치되는 순간만 판단
    public final static int CIRCLE = 104;        // 터치범위 동그라미
    public final static int RECTANGLE = 105;        // 터치범위 직사각형

    //protected boolean Click;        // 터치 확인

    private boolean first;         // 터치 시작부분을 찾기위한 계산용 변수

    protected Vector2 position;
    protected Vector2 size;
    protected int TouchType;                             // 터치 타입을 저장합니다. (동그라미, 직사각형)

    protected int count;                                   // 애니메이션 프레임 위치
    protected int FrameSpeed;                         // 초당 넘어갈 프레임 속도
    protected float Max_Frame_count;                       // fpsSetting / fps (디바이스 30프레임에 애니메이션 10프레임이면 3)
    protected float Frame_count;                           // 프레임 스택

    protected int Start_Frame;                              // 시작 프레임 위치
    protected int End_Frame;                                // 종료 프레임 위치

    protected Bitmap[] animation;                                // 시작 애니메이션

    protected boolean Run;                                  // 터치 중
    protected boolean Click;                                // 터치 확인 변수
    protected boolean ending;                               // 애니메이션 종료

    protected boolean touchStart;

    protected int start;                        // 애니메이션 시작 위치
    protected int state;                        // 애니메이션 작동 중 위치
    protected int end;                          // 애니메이션 종료 위치

    private Global global;

    public void Create(int _PosMode, Vector2 _position, Vector2 _size,
                       int _TouchType, Bitmap[] _animation, int _start, int _state, int _end,
                       int _FrameSpeed, Global _global) {
        switch (_PosMode) {
            // 버튼의 위치는 왼쪽 상단을 기준으로 저장됩니다.
            case BASIC:
                size = _size;
                position = _position;
                position.x = position.x + (size.x / 2);
                position.y = position.y + (size.y / 2);
                break;
            // 버튼의 위치는 중앙을 기준으로 저장됩니다.
            case CENTER:
                size = _size;
                position = _position;
                break;
        }

        FrameSpeed = _FrameSpeed;
        Max_Frame_count = FPS.fpsSetting / FrameSpeed;
        Frame_count = 0;
        count = 0;
        TouchType = _TouchType;

        start = _start - 1;
        state = _state - 1;
        end = _end - 1;

        first = true;

        global = _global;

        Start_Frame = start;
        End_Frame = state;

        animation = new Bitmap[end];

        for (int i = 0; i < end; i++)
            animation[i] = Bitmap.createScaledBitmap(_animation[i], size.x, size.y, true);
    }

    public void Create(int _PosMode, Vector2 _position,
                       int _TouchType, Bitmap[] _animation, int _start, int _state, int _end,
                       int _FrameSpeed, Global _global) {
        size = new Vector2();
        switch (_PosMode) {
            // 버튼의 위치는 왼쪽 상단을 기준으로 저장됩니다.
            case BASIC:
                size.x = _animation[0].getWidth();
                size.y = _animation[0].getHeight();
                position = _position;
                position.x = position.x + (size.x / 2);
                position.y = position.y + (size.y / 2);
                break;
            // 버튼의 위치는 중앙을 기준으로 저장됩니다.
            case CENTER:
                size.x = _animation[0].getWidth();
                size.y = _animation[0].getHeight();
                position = _position;
                break;
        }

        FrameSpeed = _FrameSpeed;
        Max_Frame_count = FPS.fpsSetting / FrameSpeed;
        Frame_count = 0;
        count = 0;
        TouchType = _TouchType;

        start = _start - 1;
        state = _state - 1;
        end = _end - 1;

        first = true;

        global = _global;

        Start_Frame = start;
        End_Frame = state;

        animation = _animation;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(int x, int y) {
        position.x = x;
        position.y = y;
    }

    public boolean isStart(){
        if(touchStart) {
            touchStart = false;
            return true;
        }
        return false;
    }

    private void touchStart() {

        // 처음 터치될때 한번 들어옵니다.
        if (TouchCheck()) {
            Run = true;
            touchStart = true;
        }
    }

    private void touchState() {
        // 터치중
        if (!TouchCheck() && Run) {
            End_Frame = end;
        }
    }

    public void touchEnd() {
        // 터치가 끝날때 한번 들어옵니다.
        //End_Frame = end;
        if (TouchCheck() && Run) {
            End_Frame = end;
            Click = true;
        }
    }

    public void Update() {
        if (first) {
            if (global.inputManager.touch(0).type() == global.inputManager.DOWN)    // 터치가 시작되는 순간
            {
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
            touchState();
        }
    }

    // AnimationButton 은 Update 를 이용합니다.
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

    public boolean isClick() {
        if (ending) {
            ending = false;
            return true;
        } else
            return false;
    }

    public void Render(Canvas canvas) {

        if (Run) {
            Frame_count++;                                  // 프레임당 1씩 증가합니다.
            if (Frame_count > Max_Frame_count) {           // 프레임 카운터가 Max_Frame_count 보다 크면 통과
                Frame_count -= Max_Frame_count;           // 프레임 카운터를 Max_Frame_count 만큼 뺀다.

                //  카운터
                count++;
                if (count >= End_Frame) {
                    count = Start_Frame;
                    if (End_Frame == end) {
                        Run = false;
                        count = 0;
                        Start_Frame = start;
                        End_Frame = state;
                        if (Click) {
                            Click = false;
                            ending = true;
                        }
                    }
                }
            }
        }
        canvas.drawBitmap(animation[count], position.x - (size.x / 2), position.y - (size.y / 2), null);
    }

    public void Destroy() {
        for (int i = 0; i < animation.length; i++)
            animation[i].recycle();
        animation = null;
    }
}
