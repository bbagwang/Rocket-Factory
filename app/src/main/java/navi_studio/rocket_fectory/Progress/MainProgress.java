package navi_studio.rocket_fectory.Progress;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;

import navi_studio.rocket_fectory.Button.AnimationButton;
import navi_studio.rocket_fectory.Function.Vector2;
import navi_studio.rocket_fectory.Global.BitmapResource;
import navi_studio.rocket_fectory.Global.BlackSide;
import navi_studio.rocket_fectory.Global.Camera;
import navi_studio.rocket_fectory.Global.Character;
import navi_studio.rocket_fectory.Global.CharacterScript;
import navi_studio.rocket_fectory.Global.FPS;
import navi_studio.rocket_fectory.Global.Global;
import navi_studio.rocket_fectory.Global.InputManager;
import navi_studio.rocket_fectory.Global.Inventory;
import navi_studio.rocket_fectory.Global.ItemCode;
import navi_studio.rocket_fectory.Global.LevelLimit;
import navi_studio.rocket_fectory.Global.Option;
import navi_studio.rocket_fectory.Global.Pause;
import navi_studio.rocket_fectory.Global.ScreenSize;
import navi_studio.rocket_fectory.Global.Target;
import navi_studio.rocket_fectory.Global.TextUnit;
import navi_studio.rocket_fectory.Global.dTime;
import navi_studio.rocket_fectory.R;

/**
 * Created by beanb on 2018-03-11.
 */

public class MainProgress extends View {

    public Global global;                                  // 모든 곳에 사용되는 전역 변수 입니다.
    public final int MaxTouch = 1;                        // 최대 터치 개수

    Paint paint;                                            // 디버그용 텍스트 출력 변수

    public double startTime;                              // d_time 의 driving_time 를 구하는데 사용합니다.

    AssistantProgress assistant;                            // 구동은 이 클래스에 구현합니다.

    public int pointer_count;                             // 현재 관리되고 있는 터치 개수

    double fpsStartTime;
    double timeElapsed;
    int oldfps;

    String buff;                                             // 화면 출력 문자열 변수
    String Next;                                             //

    Bitmap pauseScreen;                                // 기본 이미지

    // 클래스 시작 함수
    public MainProgress(Context context, Pause _pause) {
        super(context);
        global.pause = _pause;
        global = new Global();

        global.context = context;       // 메인 context를 입력합니다.

        assistant = new AssistantProgress();

        Next = "Running";

        global.dtime = new dTime();
        global.fps = new FPS();
        global.screenSize = new ScreenSize();
        global.blackSide = new BlackSide();
        global.inputManager = new InputManager();
        global.option = new Option();
        global.camera = new Camera();
        global.levelLimit = new LevelLimit(global);
        global.bitmapResource = new BitmapResource(global);
        global.character = new Character(global);
        global.characterScript = new CharacterScript();
        global.textUnit = new TextUnit();

        // 화면의 가로, 세로의 크기를 가져와 변수에 저장합니다.
        global.screenSize.width = context.getResources().getDisplayMetrics().widthPixels;
        global.screenSize.height = context.getResources().getDisplayMetrics().heightPixels;

        startTime = timeElapsed = fpsStartTime = System.currentTimeMillis();

        Awake();
        Start();

        global.camera.setPos(
                (global.screenSize.width / 2),
                (global.screenSize.height / 2)
        );

        pauseScreen = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.pausescreen);
        pauseScreen = Bitmap.createScaledBitmap(pauseScreen,
                global.blackSide.UnitConversion(1920),
                global.blackSide.UnitConversion(1080), false);

        mHandler.sendEmptyMessageDelayed(0, 0);
    }

    // 무한 루프
    android.os.Handler mHandler = new android.os.Handler() {
        public void handleMessage(Message msg) {

            if (!global.pause.isPause()) {
                if (timeElapsed + (1000 / global.fps.fpsSetting) < System.currentTimeMillis()) {
                    global.dtime.deltaTime = (System.currentTimeMillis() - timeElapsed) / 1000f;
                    timeElapsed = System.currentTimeMillis();
                    global.dtime.driving_count++;
                    global.dtime.driving_time = global.dtime.driving_count / global.fps.fpsSetting;
                    FixedUpdate();
                    InputEvent();
                    UIUpdate();
                    Next = Update();
                    LateUpdate();
                    invalidate();
                }
                if (Next == "End") {
                    Disable();
                    Destroy();
                }
            } else if (global.pause.isPause()) {
                Exception();
                invalidate();
            }
            mHandler.sendEmptyMessageDelayed(0, 0);
        }
    };

    // 화면에서 멀어질때
    public void Exception() {
        assistant.Exception();
    }

    // 랜더 구동
    public void onDraw(Canvas canvas) {
        if (!global.pause.isPause()) {
            canvas.drawColor(Color.BLACK);

            Render(canvas);
            LastRender(canvas);
            UIRender(canvas);

        } else if (global.pause.isPause()) {
            canvas.drawBitmap(pauseScreen,
                    global.blackSide.UnitConversion(0, BlackSide.width),
                    global.blackSide.UnitConversion(0, BlackSide.height),
                    null);
        }
        global.blackSide.BlackSideRender(canvas);
    }

    // 구동 준비
    public void Awake() {
        global.blackSide.InitBlackSide(global.screenSize.width, global.screenSize.height);
        global.inputManager.InitInputManager(MaxTouch);

        global.camera.Create(
                new Vector2(
                        global.screenSize.width - global.blackSide.Width(),
                        global.screenSize.height - global.blackSide.Height()),
                global.blackSide.UnitConversion(200)
        );

        global.levelLimit.Create();

        assistant.Initialize(global);

        assistant.Awake(0);
    }

    // 구동 시작
    public void Start() {
        if (Target.test) {
            paint = new Paint();
            paint.setColor(Color.WHITE);
            paint.setTextSize(global.blackSide.FontUnitConversion(50));
        }

        global.itemCode = new ItemCode(global);
        global.inventory = new Inventory(global);

        assistant.Start();
    }

    // 구동 최초 연산
    public void FixedUpdate() {

        assistant.FixedUpdate();
    }

    // 구동 입력 연산
    public void InputEvent() {

        // 실시간으로 변경되는 정보를 복사하여 고정시킵니다.
        // InputEvent 는 이 함수 뒤에 구현합니다.
        global.inputManager.setCopyData();

        assistant.InputEvent();
    }

    // UI 터치 작업 연산
    public void UIUpdate() {

        assistant.UIUpdate();
    }

    // 구동 연산
    public String Update() {
        global.fps.fps++;
        return assistant.Update();
    }

    // 구동 마지막 연산
    public void LateUpdate() {

        assistant.LateUpdate();
    }

    // 구동 랜더
    public void Render(Canvas canvas) {
        if (fpsStartTime + 1000 < timeElapsed) {
            oldfps = global.fps.fps;
            fpsStartTime = System.currentTimeMillis();
            global.fps.fps = 0;
        }

        assistant.Render(canvas);
    }

    // 구동 UI랜더
    public void UIRender(Canvas canvas) {
        assistant.UIRender(canvas);
    }

    // 구동 마지막 랜더
    public void LastRender(Canvas canvas) {
        assistant.LastRender(canvas);

        if (Target.test) {
            paint.setTextAlign(Paint.Align.LEFT);
            buff = "FPS : " + oldfps;
            canvas.drawText(buff,
                    global.blackSide.UnitConversion(50, global.blackSide.width),
                    global.blackSide.UnitConversion(100, global.blackSide.height),
                    paint);
            paint.setTextAlign(Paint.Align.CENTER);
            buff = "width : " + global.screenSize.width + " / height : " + global.screenSize.height;
            canvas.drawText(buff,
                    global.blackSide.UnitConversion(960, global.blackSide.width),
                    global.blackSide.UnitConversion(980, global.blackSide.height),
                    paint);
            buff = "X : " + global.camera.getPos().x + " / Y : " + global.camera.getPos().y;
            canvas.drawText(buff,
                    global.blackSide.UnitConversion(960, global.blackSide.width),
                    global.blackSide.UnitConversion(1030, global.blackSide.height),
                    paint);
            // 터치 관련 Test 함수 입니다.
            for (int i = 0; i < pointer_count; i++) {
                buff = "[" + i + "]" + " type : ";
                switch (global.inputManager.touch(i).type()) {
                    case InputManager.DOWN:
                        buff += "DOWN";
                        break;
                    case InputManager.UP:
                        buff += "UP";
                        break;
                    default:
                        break;
                }
                paint.setTextAlign(Paint.Align.LEFT);
                canvas.drawText(buff,
                        global.blackSide.UnitConversion(50, global.blackSide.width),
                        global.blackSide.UnitConversion(170 + (i * 50), global.blackSide.height),
                        paint);
                paint.setTextAlign(Paint.Align.CENTER);
                buff = global.inputManager.touch(i).x() + " / " + global.inputManager.touch(i).y();
                canvas.drawText(buff,
                        global.blackSide.UnitConversion(550, global.blackSide.width),
                        global.blackSide.UnitConversion(170 + (i * 50), global.blackSide.height),
                        paint);
                buff = "ID : " + global.inputManager.touch(i).id();
                canvas.drawText(buff,
                        global.blackSide.UnitConversion(760, global.blackSide.width),
                        global.blackSide.UnitConversion(170 + (i * 50), global.blackSide.height),
                        paint);
            }
            paint.setTextAlign(Paint.Align.LEFT);
            buff = "time : " + (int) global.dtime.driving_time;
            canvas.drawText(buff,
                    global.blackSide.UnitConversion(1470, global.blackSide.width),
                    global.blackSide.UnitConversion(100, global.blackSide.height),
                    paint);
            assistant.FixedUpdate();
            buff = "deltaTime : " + global.dtime.deltaTime;
            canvas.drawText(buff,
                    global.blackSide.UnitConversion(1470, global.blackSide.width),
                    global.blackSide.UnitConversion(170, global.blackSide.height),
                    paint);
            paint.setTextAlign(Paint.Align.CENTER);
        }
    }

    // 구동 종료
    public void Disable() {
        assistant.Disable();
    }

    // 구동 파괴
    public void Destroy() {
        assistant.Destroy();

        // 정상종료 함수를 구현하시면 됩니다.
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    // 입력
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (global.pause.isPause()) {
            global.pause.set(false);
            return true;
        }

        pointer_count = event.getPointerCount();

        if (pointer_count > global.inputManager.getMaxtouch()) {
            global.inputManager.pushOverTouch(pointer_count - global.inputManager.getMaxtouch());
            pointer_count = global.inputManager.getMaxtouch();
        }

        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_MOVE:
                for (int i = 0; i < pointer_count; i++) {
                    global.inputManager.originalTouch(i).id(event.getPointerId(i));
                    global.inputManager.originalTouch(i).x((int) (event.getX(i)));
                    global.inputManager.originalTouch(i).y((int) (event.getY(i)));
                }
                break;
            case MotionEvent.ACTION_DOWN:
                // 처음 터치가 눌러졌을 때
                global.inputManager.originalTouch(0).id(event.getPointerId(0));
                global.inputManager.originalTouch(0).x((int) (event.getX(0)));
                global.inputManager.originalTouch(0).y((int) (event.getY(0)));
                global.inputManager.originalTouch(0).type(global.inputManager.DOWN);
                break;
            case MotionEvent.ACTION_UP:
                // 터치가 떼어졌을 때
                global.inputManager.originalTouch(0).id(event.getPointerId(0));
                global.inputManager.originalTouch(0).x((int) (event.getX(0)));
                global.inputManager.originalTouch(0).y((int) (event.getY(0)));
                global.inputManager.originalTouch(0).type(global.inputManager.UP);
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                // 터치가 눌러졌을 때   (멀티터치)
                for (int i = 0; i < pointer_count; i++) {
                    global.inputManager.originalTouch(i).id(event.getPointerId(i));
                    global.inputManager.originalTouch(i).x((int) (event.getX(i)));
                    global.inputManager.originalTouch(i).y((int) (event.getY(i)));
                    global.inputManager.originalTouch(i).type(global.inputManager.DOWN);
                }
                break;
            case MotionEvent.ACTION_POINTER_UP:
                // 터치가 떼어졌을 때   (멀티터치)
                if (global.inputManager.getOverTouch() > 0)
                    return true;
                for (int i = 0; i < pointer_count; i++) {
                    global.inputManager.originalTouch(i).id(event.getPointerId(i));
                    global.inputManager.originalTouch(i).x((int) (event.getX(i)));
                    global.inputManager.originalTouch(i).y((int) (event.getY(i)));
                    global.inputManager.originalTouch(i).type(global.inputManager.UP);
                }
                break;
            default:
                break;
        }
        return true;
    }
}
