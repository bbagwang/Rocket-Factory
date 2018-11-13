package navi_studio.rocket_fectory.Button;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import navi_studio.rocket_fectory.Function.Vector2;
import navi_studio.rocket_fectory.Global.Global;

import static java.lang.Math.sqrt;

public class ScrollBar {

    public final static int Horizontal = 10;  // 가로
    public final static int Vertical = 11;  // 세로

    private Global global;

    private Vector2 position;               // 시작 위치 값

    private int value;                      // 0 ~ 99 값 반환
    private int startPoint;                 // 시작 위치
    private int type;                       // 가로 혹은 세로 모드
    private float slice;                    // 0 ~ 99 중 1 의 크기
    private float handlePosition;           // 핸들 위치

    private Bitmap bar;                     // 바
    private Bitmap[] handle;                // 핸들

    private boolean first;         // 터치 시작부분을 찾기위한 계산용 변수
    private boolean On;            // 핸들 터치

    // 생성
    public void Create(Global _global, Vector2 _position, int _type,
                       Bitmap _bar, Bitmap[] _handle, int startPos) {
        value = startPos;
        global = _global;
        position = _position;
        type = _type;
        bar = _bar;
        handle = _handle;

        switch (type) {
            case Horizontal:            // 가로
                startPoint = position.x + (handle[0].getWidth() / 2);
                slice = (bar.getWidth() - handle[0].getWidth()) / 100f;
                break;
            case Vertical:              // 세로
                startPoint = position.y + (handle[0].getHeight() / 2);
                slice = (bar.getHeight() - handle[0].getHeight()) / 100f;
                break;
        }
        handlePosition = startPoint;
    }

    private void Run() {
        switch (type) {
            case Horizontal:            // 가로
                if (handlePosition + slice < global.inputManager.touch(0).x() &&
                        value < 99) {
                    value += (int) ((global.inputManager.touch(0).x() - handlePosition) / slice);
                    if (value > 99)
                        value = 99;
                    handlePosition = startPoint + (value * slice);
                } else if (handlePosition - slice > global.inputManager.touch(0).x() &&
                        value > 0) {
                    value -= (int) ((handlePosition - global.inputManager.touch(0).x()) / slice);
                    if (value < 0)
                        value = 0;
                    handlePosition = startPoint + (value * slice);
                }
                break;
            case Vertical:              // 세로
                if (handlePosition + slice < global.inputManager.touch(0).y() &&
                        value < 99) {
                    value += (int) ((global.inputManager.touch(0).y() - handlePosition) / slice);
                    if (value > 99)
                        value = 99;
                    handlePosition = startPoint + (value * slice);
                } else if (handlePosition - slice > global.inputManager.touch(0).y() &&
                        value > 0) {
                    value -= (int) ((handlePosition - global.inputManager.touch(0).y()) / slice);
                    if (value < 0)
                        value = 0;
                    handlePosition = startPoint + (value * slice);
                }
                break;
        }
    }

    private void touchStart() {
        // 처음 터치될때 한번 들어옵니다.
        if (TouchCheck())
            On = true;
    }

    private void touchState() {
        // 터치중
        if (On)
            Run();
    }

    private void touchEnd() {
        // 터치가 끝날때 한번 들어옵니다.
        On = false;
    }

    public boolean TouchCheck() {
        if ((position.x <= global.inputManager.touch(0).x()) &&
                ((position.x + handle[0].getWidth()) >= global.inputManager.touch(0).x()) &&
                ((handlePosition - (handle[0].getHeight() / 2)) <= global.inputManager.touch(0).y()) &&
                ((handlePosition + (handle[0].getHeight() / 2)) >= global.inputManager.touch(0).y())) {
            return true;
        } else {
            return false;
        }
    }

    // Update
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

    // 랜더
    public void Render(Canvas canvas) {
        // bar 출력
        canvas.drawBitmap(bar, position.x, position.y, null);
        // handle 출력
        if (!On) {
            switch (type) {
                case Horizontal:            // 가로
                    canvas.drawBitmap(handle[0], (int) (handlePosition - (handle[0].getWidth() / 2)), position.y, null);
                    break;
                case Vertical:              // 세로
                    canvas.drawBitmap(handle[0], position.x, (int) (handlePosition - (handle[0].getHeight() / 2)), null);
                    break;
            }
        } else {
            switch (type) {
                case Horizontal:            // 가로
                    canvas.drawBitmap(handle[1], (int) (handlePosition - (handle[0].getWidth() / 2)), position.y, null);
                    break;
                case Vertical:              // 세로
                    canvas.drawBitmap(handle[1], position.x, (int) (handlePosition - (handle[0].getHeight() / 2)), null);
                    break;
            }
        }
    }

    // 반환
    public int Value() {
        return value;
    }

}
