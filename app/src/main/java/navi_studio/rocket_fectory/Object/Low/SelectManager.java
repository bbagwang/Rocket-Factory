package navi_studio.rocket_fectory.Object.Low;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import navi_studio.rocket_fectory.Action.Manager.ActionManager;
import navi_studio.rocket_fectory.Function.Gesture;
import navi_studio.rocket_fectory.Function.LocaleManager;
import navi_studio.rocket_fectory.Function.Vector2;
import navi_studio.rocket_fectory.Global.Camera;
import navi_studio.rocket_fectory.Global.Global;
import navi_studio.rocket_fectory.Global.InputManager;
import navi_studio.rocket_fectory.Object.Basic.Building;
import navi_studio.rocket_fectory.Object.Core.Ephemera;
import navi_studio.rocket_fectory.Object.Core.ObjectCode;
import navi_studio.rocket_fectory.Object.Manager.ObjManager;

public class SelectManager extends Ephemera {

    public final static int Empty = 99;

    public final static int BubbleRange = 100;          // BlackSide 로 변환하여 사용합니다.
    public final static int InputRange = 170;           // 보조 단추를 입력 범위
    public final static int Speed = 3;                  // 속도

    private Gesture gesture;                // 전역 변수
    private ObjManager objManager;          // ObjManager 주소
    private Global global;                  // 전역 변수
    private ActionManager actionManager;    // 액션 메니저
    private Paint paint;                    // 텍스트 랜더용
    private LocaleManager localeManager;   // Text 로드용

    protected Bitmap[] DestroyAnimation;    // 파괴 애니메이션
    protected Bitmap bubble;                // 보조 단추 이미지

    private int MaxLoopCount;           // 애니메이션 재생 횟수 제한
    private int loopCount;              // 애니메이션 재생 횟수
    private int selectingCode;          // 선택된 코드      Empty = 빈 땅
    private int selectingID;            // 선택된 ID      Empty = 빈 땅

    private boolean building;    // 선택한 오브젝트가 건물일 경우
    private boolean first;         // 터치 시작부분을 찾기위한 계산용 변수
    private boolean isMaterial;     // 자원 선택시

    private Building storage;             // 건물 선택시 사용되는 변수

    private int Action;                 // 명령 개수
    private int selectBubbleNumber;    // 선택된 버블

    private String[] Command;           // 명령

    private Vector2[] BubbleSettingPosition; // 버블 생성 위치 ( "0,0" 좌표 기준 )
    private Vector2[] BubblePosition;         // 버블 위치

    public void Create(int _PosMode, int _PosX, int _PosY, int _SizeX, int _SizeY,
                       Bitmap _image[], Bitmap[] _destroyAnimation, Bitmap _bubble,
                       int _FrameSpeed, int _MaxLoopCount, Gesture _gesture,
                       ObjManager _objManager, Global _global, Paint _paint, ActionManager _actionManager) {

        Create(_PosMode, _PosX, _PosY, _SizeX, _SizeY, _image, _FrameSpeed, CHECK);
        DestroyAnimation = _destroyAnimation;
        bubble = _bubble;
        MaxLoopCount = _MaxLoopCount;
        gesture = _gesture;
        objManager = _objManager;
        global = _global;
        paint = _paint;
        actionManager = _actionManager;

        building = false;
        first = false;
        isMaterial = false;

        localeManager = new LocaleManager();
        localeManager.init("Game");

        // Selecting
        selectingCode = objManager.touchObject(
                global.inputManager.touch(0).x(),
                global.inputManager.touch(0).y(),
                true);

        if (selectingCode > ObjectCode.End) {
            // 건물 선택
            building = true;
            storage = objManager.BuildingSearch(selectingCode);
            selectingID = storage.getCode();
            Position.setVector(storage.getPos());
            Command = storage.open();
            Action = Command.length;
        } else if (selectingCode == ObjectCode.Empty) {
            // 빈 땅 선택
            Action = 4;
            Command = new String[Action];
            Command[0] = localeManager.getScriptByNum("001001C001");
            Command[1] = localeManager.getScriptByNum("001001C002");
            Command[2] = localeManager.getScriptByNum("001001C003");
            Command[3] = localeManager.getScriptByNum("001001C004");
        } else {
            // 자원 선택
            Action = 2;
            Command = new String[Action];
            isMaterial = true;
            Command[0] = localeManager.getScriptByNum("001001M002");
            Command[1] = localeManager.getScriptByNum("001001M003");
        }

        // 버블 생성 위치 샛팅
        BubbleSettingPosition = new Vector2[6];
        // 오른쪽 상단
        BubbleSettingPosition[0] = new Vector2(
                Position.x + global.blackSide.UnitConversion(190),
                Position.y - global.blackSide.UnitConversion(170)
        );
        // 오른쪽 중앙
        BubbleSettingPosition[1] = new Vector2(
                Position.x + global.blackSide.UnitConversion(250),
                Position.y
        );
        // 오른쪽 하단
        BubbleSettingPosition[2] = new Vector2(
                Position.x + global.blackSide.UnitConversion(190),
                Position.y + global.blackSide.UnitConversion(170)
        );
        // 왼쪽 상단
        BubbleSettingPosition[3] = new Vector2(
                Position.x - global.blackSide.UnitConversion(190),
                Position.y - global.blackSide.UnitConversion(170)
        );
        // 왼쪽 중앙
        BubbleSettingPosition[4] = new Vector2(
                Position.x - global.blackSide.UnitConversion(250),
                Position.y
        );
        // 왼쪽 하단
        BubbleSettingPosition[5] = new Vector2(
                Position.x - global.blackSide.UnitConversion(190),
                Position.y + global.blackSide.UnitConversion(170)
        );

        // 버블 위치 설정
        BubblePosition = new Vector2[Action];
        for (int i = 0; i < Action; i++) {
            BubblePosition[i] = new Vector2(
                    BubbleSettingPosition[i].x,
                    BubbleSettingPosition[i].y
            );
        }
    }

    @Override
    public boolean Update() {

        counter();

        touchProcedure();

        return destroy;
    }

    // 닫기
    public int closeCheck() {
        for (int i = 0; i < Action; i++) {
            if (global.blackSide.UnitConversion(InputRange) > BubblePosition[i].ForDistance(Position)) {
                Destroy();
                return i;
            }
        }
        return Empty;
    }

    // 터치된 위치에 맞는 번호 반환
    private int selectBubble() {
        for (int i = 0; i < Action; i++) {
            if (global.blackSide.UnitConversion(BubbleRange) > BubblePosition[i].ForDistance(new Vector2(
                    global.inputManager.touch(0).x() + (global.camera.getPos().x - (global.camera.getSizeWidth() / 2)),
                    global.inputManager.touch(0).y() + (global.camera.getPos().y - (global.camera.getSizeHeight() / 2))
            )))
                return i;
        }
        return Empty;
    }

    // 터치 시작
    private void touchStart() {
        selectBubbleNumber = selectBubble();

        // 빈 공간 터치시 파괴
        if (selectBubbleNumber == Empty)
            Destroy();
    }

    // 터치 종료
    private void touchState() {

        BubblePosition[selectBubbleNumber].setVector(
                BubblePosition[selectBubbleNumber].x - ((BubblePosition[selectBubbleNumber].x - (global.inputManager.touch(0).x()) - (global.camera.getPos().x - (global.camera.getSizeWidth() / 2))) / Speed),
                BubblePosition[selectBubbleNumber].y - ((BubblePosition[selectBubbleNumber].y - (global.inputManager.touch(0).y()) - (global.camera.getPos().y - (global.camera.getSizeHeight() / 2))) / Speed)
        );
    }

    // 터치 중
    private void touchEnd() {
        // 입력 확인
        selectBubbleNumber = closeCheck();

        if (building) {
            // 건물의 경우 건물안에서 명령 처리
            if (0 != storage.close(selectBubbleNumber)) {
                actionManager.storageSet(Position, storage.close(selectBubbleNumber), selectingCode,selectingID);
                actionManager.start();
                gesture.GearCommand(Gesture.Immobility);
            }
        } else if (selectBubbleNumber != Empty) {
            // 명령 처리 / actionManager 시작
            // 자원 선택시 수 추가
            if(isMaterial)
                selectBubbleNumber++;

            actionManager.storageSet(Position, selectBubbleNumber, selectingCode,selectingID);
            actionManager.start();
            gesture.GearCommand(Gesture.Immobility);
        }
    }

    // 터치 순서 지정
    private void touchProcedure() {
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

    @Override
    public void Render(Canvas canvas, Camera camera) {
        if (!building)
            canvas.drawBitmap(image[count],
                    Position.x - (Size.x / 2) - (camera.getPos().x - (camera.getSizeWidth() / 2)),
                    Position.y - (Size.y / 2) - (camera.getPos().y - (camera.getSizeHeight() / 2)), null);

        // 버블 랜더
        for (int i = 0; i < Action; i++) {
            canvas.drawBitmap(bubble,
                    BubblePosition[i].x - (bubble.getWidth() / 2) - (camera.getPos().x - (camera.getSizeWidth() / 2)),
                    BubblePosition[i].y - (bubble.getHeight() / 2) - (camera.getPos().y - (camera.getSizeHeight() / 2)), null);
            paint.setTextAlign(Paint.Align.LEFT);
            if (i >= 3) {
                paint.setTextAlign(Paint.Align.RIGHT);
                canvas.drawText(Command[i],
                        BubblePosition[i].x - (camera.getPos().x - (camera.getSizeWidth() / 2)) - global.blackSide.UnitConversion(93),
                        BubblePosition[i].y - (camera.getPos().y - (camera.getSizeHeight() / 2)) + (paint.getTextSize() / 5),
                        paint);
            } else {
                paint.setTextAlign(Paint.Align.LEFT);
                canvas.drawText(Command[i],
                        BubblePosition[i].x - (camera.getPos().x - (camera.getSizeWidth() / 2)) + global.blackSide.UnitConversion(93),
                        BubblePosition[i].y - (camera.getPos().y - (camera.getSizeHeight() / 2)) + (paint.getTextSize() / 5),
                        paint);
            }
        }
    }

    // 파괴
    public void Destroy() {
        destroy = true;
        gesture.GearCommand(Gesture.Running);
        TouchDestroy touchDestroy = new TouchDestroy();
        touchDestroy.Create(TouchDestroy.CENTER,
                Position.x,
                Position.y,
                Size.x, Size.y,
                DestroyAnimation, 10
        );
        objManager.add(touchDestroy);
    }

    // 카운터
    @Override
    public void counter() {
        Frame_count++;                                  // 프레임당 1씩 증가합니다.
        if (Frame_count > Max_Frame_count) {           // 프레임 카운터가 Max_Frame_count 보다 크면 통과
            Frame_count -= Max_Frame_count;           // 프레임 카운터를 Max_Frame_count 만큼 뺀다.

            //  카운터
            count++;
            if (count >= image.length) {
                count = 0;
                if (selectBubbleNumber == Empty)
                    loopCount++;
                if (loopCount >= MaxLoopCount)
                    Destroy();
            }
        }
    }
}
