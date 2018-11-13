package navi_studio.rocket_fectory.Function;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import navi_studio.rocket_fectory.Action.Manager.ActionManager;
import navi_studio.rocket_fectory.Global.Camera;
import navi_studio.rocket_fectory.Global.FPS;
import navi_studio.rocket_fectory.Global.Global;
import navi_studio.rocket_fectory.Object.Low.SelectManager;
import navi_studio.rocket_fectory.Object.Low.TouchDestroy;
import navi_studio.rocket_fectory.Object.Manager.ObjManager;
import navi_studio.rocket_fectory.R;

public class Gesture {

    private final static boolean Stop = false; // 부동
    private final static boolean Run = true; // 이동중

    private final static int arrival = 10; // 근사값
    private final static int leastDistance = 20; // 이동이 시작될 최소 거리
    private final static int Speed = 5; // 맵 이동속도
    private final static int DestroyAnimationFrameSize = 8;        // 파괴 애니메이션 프레임 크기

    private final static int stateCounterMIN = 8;   // 터치 중 프레임 시작 위치
    private final static int stateCounterMAX = 15;  // 터치 중 프레임 리턴 위치

    public final static int Immobility = 999;      // 다른 UI
    public final static int Selecting = 1000;     // 건물 선택
    public final static int Running = 1001;     // 화면 이동

    private Global global;         // 전역 변수
    private ObjManager objManager;      // ObjManager 주소
    private ActionManager actionManager;

    private Paint paint;

    private Vector2 originPos;     // 터치 시작 위치
    private Vector2 targetPos;     // 터치 현 위치
    private Vector2 size;     // 크기
    private Vector2 direction;      // 정규화 되어 방향만 나타내는 벡터
    private Vector2 MovePos;        // 이동용 시작 위치

    private boolean first;         // 터치 시작부분을 찾기위한 계산용 변수

    protected int Gear;           // 기어
    protected boolean RenderSwich;  // 렌더 상황 저장
    public boolean Moving;               // 이동 상태 저장 변수

    private Bitmap[] touchAnimation;    // 애니메이션
    private Bitmap[] DestroyAnimation;  // 파괴 애니메이션
    private Bitmap[] selectAnimation;   // 선택 애니메이션

    protected int count;                                   // 애니메이션 프레임 위치
    protected int FrameSpeed = 10;                         // 초당 넘어갈 프레임 속도
    protected float Max_Frame_count;                       // fpsSetting / fps (디바이스 30프레임에 애니메이션 10프레임이면 3)
    protected float Frame_count;                           // 프레임 스택

    private Bitmap bubble;                                  // 버블 이미지

    //TODO : Pick Gesture Sound
    SoundUnit Sound_Touch;

    public void Create(Global _global, ObjManager _objManager, Bitmap[] _touchAnimation) {
        Moving = Stop;  // 이동 중인가를 판가름 하는 변수입니다. 기본은 부동으로 시작합니다.
        RenderSwich = Stop;
        first = true;   // 터치 시작을 판가름 하기 위한 변수입니다.
        Gear = Running;
        originPos = new Vector2();  // 터치 시작시의 위치를 저장하는 변수입니다.
        targetPos = new Vector2();  // 현 터치 위치를 저장하는 변수입니다.
        direction = new Vector2();  // 계산용 변수
        MovePos = new Vector2();    // 이동용 시작 위치 변수
        global = _global;
        objManager = _objManager;

        actionManager = new ActionManager();
        actionManager.Create(global, objManager);

        Sound_Touch=new SoundUnit();
        Sound_Touch.Create(SoundUnit.music,R.raw.touch,1,global.context);

        Max_Frame_count = FPS.fpsSetting / FrameSpeed;  // 프레임 속도를 위해 값을 설정합니다.
        // 제스처 크기
        size = new Vector2(
                global.blackSide.UnitConversion(490),
                global.blackSide.UnitConversion(332));
        // 임시로 사용될 버튼 제작
        bubble = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.dot0);
        bubble = Bitmap.createScaledBitmap(bubble,
                global.blackSide.UnitConversion(148),
                global.blackSide.UnitConversion(148),
                false);

        // Text 셋팅
        paint = new Paint();

        paint.setColor(Color.WHITE);
        paint.setTextSize(global.blackSide.FontUnitConversion(70));

        // 애니메이션 할당
        touchAnimation = new Bitmap[_touchAnimation.length - DestroyAnimationFrameSize];
        for (int i = 0; i < touchAnimation.length; i++)
            touchAnimation[i] = Bitmap.createScaledBitmap(_touchAnimation[i], size.x, size.y, false);

        // 파괴 애니메이션 할당
        DestroyAnimation = new Bitmap[DestroyAnimationFrameSize];
        for (int i = _touchAnimation.length - DestroyAnimationFrameSize; i < _touchAnimation.length; i++)
            DestroyAnimation[i - (_touchAnimation.length - DestroyAnimationFrameSize)] = Bitmap.createScaledBitmap(_touchAnimation[i], size.x, size.y, false);

        //stateCounterMIN
        selectAnimation = new Bitmap[stateCounterMAX - stateCounterMIN];
        for (int i = stateCounterMIN; i < stateCounterMAX; i++)
            selectAnimation[i - stateCounterMIN] = touchAnimation[i];
    }

    public void FirstRender(Canvas canvas) {
        if (Gear == Immobility) {
            actionManager.FirstRender(canvas);
        }
    }

    public void Render(Canvas canvas) {
        if (Gear == Immobility) {
            actionManager.Render(canvas);
        }
    }

    public void UIRender(Canvas canvas) {
        // 렌더 스위치 true 일경우 실행
        if (RenderSwich) {
            BaseRender(canvas);
        }
        if (Gear == Immobility) {
            actionManager.UIRender(canvas);
        }
    }

    public void Update() {
        switch (Gear) {
            case Running:
                touchPosition();    // 터치가 시작되는 위치를 저장합니다.  최초터치인지를 판가름 하기도 합니다.
                MoveCheck();        // 이동을 감지합니다.
                if (Moving)         // 이동이 감지되었을 경우
                    SlideMove();    // 화면을 이동시킵니다.
                break;
            case Immobility:
                if (actionManager.Update()) {
                    Gear = Running;
                }
                break;
        }
    }

    // 터치 시작
    private void start() {

        Sound_Touch.play(100.0f);

        if (Gear == Running)
            RenderSwich = true;
    }

    // 터치 중
    private void state() {
    }

    // 터치 끝 (이동)
    private void end() {
        RenderSwich = false;
        count = 0;
        // 오브젝트 생성 후 매니저에 입력
        if (Gear == Running) {
            TouchDestroy touchDestroy = new TouchDestroy();
            touchDestroy.Create(TouchDestroy.CENTER,
                    global.inputManager.touch(0).x() + (global.camera.getPos().x - global.camera.getSizeWidth() / 2),
                    global.inputManager.touch(0).y() + (global.camera.getPos().y - global.camera.getSizeHeight() / 2),
                    size.x, size.y,
                    DestroyAnimation, 10
            );
            objManager.add(touchDestroy);
        }
    }

    // 터치 끝 (부동)
    private void stopEnd() {
        RenderSwich = false;
        count = 0;
        if (Gear == Running) {
            Gear = Selecting;
            SelectManager selectManager = new SelectManager();
            selectManager.Create(TouchDestroy.CENTER,
                    global.inputManager.touch(0).x() + (global.camera.getPos().x - global.camera.getSizeWidth() / 2),
                    global.inputManager.touch(0).y() + (global.camera.getPos().y - global.camera.getSizeHeight() / 2),
                    size.x, size.y,
                    selectAnimation, DestroyAnimation, bubble,
                    10, 7, this, objManager, global, paint, actionManager
            );
            objManager.add(selectManager);
        }
    }

    // 기본적인 애니메이션 랜더 입니다.
    public void BaseRender(Canvas canvas) {
        Frame_count++;                                  // 프레임당 1씩 증가합니다.
        if (Frame_count > Max_Frame_count) {           // 프레임 카운터가 Max_Frame_count 보다 크면 통과
            Frame_count -= Max_Frame_count;           // 프레임 카운터를 Max_Frame_count 만큼 뺀다.

            //  카운터
            count++;
            if (count >= stateCounterMAX)
                count = stateCounterMIN;
        }

        //  캔버스에 출력
        canvas.drawBitmap(touchAnimation[count],
                targetPos.x - (size.x / 2),
                targetPos.y - (size.y / 2), null);
    }

    // 상황을 조작합니다.
    public void GearCommand(int command) {
        Gear = command;
    }

    private void touchStart() {
        // 처음 터치될때 한번 들어옵니다.
        targetSearch(
                global.inputManager.touch(0).x(),
                global.inputManager.touch(0).y()
        );
        originPos.setVector(
                global.inputManager.touch(0).x(),
                global.inputManager.touch(0).y());
        MovePos.setVector(originPos);
        start();
    }

    private void touchState() {
        // 터치중
        targetSearch(
                global.inputManager.touch(0).x(),
                global.inputManager.touch(0).y()
        );
        state();
    }

    private void touchEnd() {
        // 터치가 끝날때 한번 들어옵니다.
        targetSearch(
                global.inputManager.touch(0).x(),
                global.inputManager.touch(0).y()
        );
        if (leastDistance > originPos.ForDistance(new Vector2(
                global.inputManager.touch(0).x(),
                global.inputManager.touch(0).y()))) {
            // 시작위치와 종료 위치의 거리가 짧을경우
            // 건물 생성 실험
            stopEnd();
            return;
        }
        end();
    }

    // 화면 이동
    public void SlideMove() {
        //  MovePos 는 마우스 방향을 따라갑니다.
        direction.x = (MovePos.x - targetPos.x) / Speed;
        direction.y = (MovePos.y - targetPos.y) / Speed;
        MovePos.x -= direction.x;
        MovePos.y -= direction.y;

        if (MovePos.ForDistance(targetPos) < arrival) {
            Moving = Stop;
            return;
        }
        // 이동 부분입니다.
        //  MovePos 의 이동만큼 카메라를 이동합니다.
        global.camera.setPos(
                global.camera.getPos().x + direction.x,
                global.camera.getPos().y + direction.y
        );
    }

    // 이동이 감지되어 시작될 때
    public void MoveCheck() {
        // 터치 부동 범위를 넘어갈 경우
        if ((originPos.ForDistance(targetPos) > leastDistance))
            Moving = Run;
    }

    // 타겟을 입력받습니다.
    public void targetSearch(int x, int y) {
        targetPos.x = x;
        targetPos.y = y;
    }

    // 터치 위치 입력
    public void touchPosition() {
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
}