package navi_studio.rocket_fectory.Action.Building;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import navi_studio.rocket_fectory.Button.AnimationButton;
import navi_studio.rocket_fectory.Function.Vector2;
import navi_studio.rocket_fectory.Global.BlackSide;
import navi_studio.rocket_fectory.Global.Global;
import navi_studio.rocket_fectory.Object.Available.Conveyor.Manager.ConveyorCode;
import navi_studio.rocket_fectory.Object.Available.Conveyor.Wagon;
import navi_studio.rocket_fectory.Object.Basic.Conveyor;
import navi_studio.rocket_fectory.Object.Core.ObjLow;
import navi_studio.rocket_fectory.Object.Manager.ObjManager;
import navi_studio.rocket_fectory.R;

public class BuildingConnection extends ObjLow {

    private Global global;
    private ObjManager objManager;

    private int storageCode;                        // 받아온 코드, 오브젝트 아이디

    private Vector2 originPos;     // 터치 시작 위치
    private Vector2 targetPos;     // 터치 현 위치
    private Vector2 MovePos;        // 이동용 위치 저장

    private AnimationButton backSpace;              // 뒤로가기 버튼
    private AnimationButton CheckButton;            // 확인 버튼

    private ConveyorCode conveyorCode;

    private boolean first;                          // 최초터치 확인
    private boolean Check;                          // 체크

    private Paint LainPaint;
    private Paint CirclePaint;
    private Paint EdgePaint;

    Bitmap aiming;

    // destroy = true 일 경우 파괴
    public void Create(Global _global, ObjManager _objManager) {
        global = _global;
        objManager = _objManager;

        conveyorCode = new ConveyorCode();

        originPos = new Vector2();
        targetPos = new Vector2();
        MovePos = new Vector2();

        LainPaint = new Paint();
        LainPaint.setStrokeWidth(global.blackSide.UnitConversion(30));
        LainPaint.setARGB(160, 255, 0, 0);
        LainPaint.setStrokeCap(Paint.Cap.ROUND);

        EdgePaint = new Paint();
        EdgePaint.setStrokeWidth(global.blackSide.UnitConversion(10));
        EdgePaint.setARGB(255, 255, 0, 0);
        EdgePaint.setStyle(Paint.Style.STROKE);

        CirclePaint = new Paint();
        CirclePaint.setStyle(Paint.Style.FILL);
        CirclePaint.setARGB(100, 255, 0, 0);

        aiming = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.aiming);
        aiming = Bitmap.createScaledBitmap(aiming,
                global.blackSide.UnitConversion(310),
                global.blackSide.UnitConversion(310), true);

        backSpace = new AnimationButton();
        backSpace.Create(AnimationButton.BASIC,
                new Vector2(global.blackSide.UnitConversion(10, BlackSide.width),
                        global.blackSide.UnitConversion(10, BlackSide.height)),
                AnimationButton.RECTANGLE, global.bitmapResource.ActionBackSpaceImage,
                3, 3, 5, 10, global
        );

        CheckButton = new AnimationButton();
        CheckButton.Create(AnimationButton.BASIC,
                new Vector2(global.blackSide.UnitConversion(1610, BlackSide.width),
                        global.blackSide.UnitConversion(780, BlackSide.height)),
                AnimationButton.RECTANGLE, global.bitmapResource.CheckButton,
                3, 3, 5, 10, global
        );
    }

    public void set(int _storageCode) {
        storageCode = _storageCode;
    }

    public boolean Destroy() {

        return true;
    }

    // 건물 검색
    private void BuildingSearch() {
        Check = false;
        if (BuildPossible()) {
            Check = true;
        }
    }

    // 건설 가능 여부
    private boolean BuildPossible() {

        for (int i = 0; i < objManager.getSize(ObjManager.BuildingType); i++) {
            if (objManager.getBuilding().get(i).getSelect() && objManager.getBuilding().get(i).isCompleted()) {
                if(objManager.getBuilding().get(i).getPos().ForDistance(global.camera.getPos()) < global.blackSide.UnitConversion(150)){
                    if(objManager.getBuilding().get(i) == objManager.BuildingSearch(storageCode))
                        return false;
                    Check = true;
                }
            }
        }

        return false;
    }

    // 주 실행
    public boolean Update() {
        // 건물 검색
        backSpace.Update();
        if (backSpace.isClick())
            return Destroy();

        CheckButton.Update();
        if (CheckButton.isClick()) {
            // 컨베이어 확인 버튼
            // 프로토타입 모드
            for (int i = 0; i < objManager.getSize(ObjManager.BuildingType); i++) {
                if (objManager.getBuilding().get(i).getSelect()) {
                    if(objManager.getBuilding().get(i).getPos().ForDistance(global.camera.getPos()) < global.blackSide.UnitConversion(150)){
                        Conveyor conveyor = conveyorCode.getConveyor(global.levelLimit.getThisLevel());
                        conveyor.Create(
                                objManager.BuildingSearch(storageCode),
                                objManager.getBuilding().get(i),
                                global
                                );

                        objManager.BuildingSearch(storageCode).outConnect(conveyor);
                        objManager.getBuilding().get(i).inConnect(conveyor);

                        objManager.add(conveyor);

                        return Destroy();
                    }
                }
            }
            return Destroy();
        }

        if (first) {
            if (global.inputManager.touch(0).type() == global.inputManager.DOWN)    // 터치가 시작되는 순간
            {
                touchStart();
                first = false;
                return false;
            }
        } else {
            if (global.inputManager.touch(0).type() == global.inputManager.UP) { // 터치끝
                touchEnd();
                first = true;
                return false;
            } else if (global.inputManager.touch(0).type() == global.inputManager.DOWN)
                touchState();
        }

        BuildingSearch();

        return false;
    }

    private void SearchDisplay(Canvas canvas) {
        if (Check) {
            LainPaint.setARGB(160, 0, 0, 255);
            canvas.drawLine(
                    objManager.BuildingSearch(storageCode).getPos().x - global.camera.getPos().x + (global.camera.getSizeWidth() / 2),
                    objManager.BuildingSearch(storageCode).getPos().y - global.camera.getPos().y + (global.camera.getSizeHeight() / 2),
                    global.camera.getSizeWidth() / 2,
                    global.camera.getSizeHeight() / 2,
                    LainPaint
            );
            CirclePaint.setARGB(100, 0, 0, 255);
            canvas.drawCircle(
                    global.camera.getSizeWidth() / 2,
                    global.camera.getSizeHeight() / 2,
                    global.blackSide.UnitConversion(150),
                    CirclePaint
            );
            EdgePaint.setARGB(255, 0, 0, 255);
            canvas.drawCircle(
                    global.camera.getSizeWidth() / 2,
                    global.camera.getSizeHeight() / 2,
                    global.blackSide.UnitConversion(150),
                    EdgePaint
            );
        } else {
            LainPaint.setARGB(160, 255, 0, 0);
            canvas.drawLine(
                    objManager.BuildingSearch(storageCode).getPos().x - global.camera.getPos().x + (global.camera.getSizeWidth() / 2),
                    objManager.BuildingSearch(storageCode).getPos().y - global.camera.getPos().y + (global.camera.getSizeHeight() / 2),
                    global.camera.getSizeWidth() / 2,
                    global.camera.getSizeHeight() / 2,
                    LainPaint
            );
            CirclePaint.setARGB(100, 255, 0, 0);
            canvas.drawCircle(
                    global.camera.getSizeWidth() / 2,
                    global.camera.getSizeHeight() / 2,
                    global.blackSide.UnitConversion(150),
                    CirclePaint
            );
            EdgePaint.setARGB(255, 255, 0, 0);
            canvas.drawCircle(
                    global.camera.getSizeWidth() / 2,
                    global.camera.getSizeHeight() / 2,
                    global.blackSide.UnitConversion(150),
                    EdgePaint
            );
        }
    }

    // 화면 이동
    public void SlideMove() {
        //  MovePos 는 마우스 방향을 따라갑니다.
        originPos.x = (MovePos.x - targetPos.x);
        originPos.y = (MovePos.y - targetPos.y);
        MovePos.x -= originPos.x;
        MovePos.y -= originPos.y;

        // 이동 부분입니다.
        //  MovePos 의 이동만큼 카메라를 이동합니다.
        global.camera.setPos(
                global.camera.getPos().x + originPos.x,
                global.camera.getPos().y + originPos.y
        );
    }

    // 터치 시작
    private void touchStart() {
        targetPos.setVector(
                global.inputManager.touch(0).x(),
                global.inputManager.touch(0).y()
        );
        originPos.setVector(
                global.inputManager.touch(0).x(),
                global.inputManager.touch(0).y());
        MovePos.setVector(originPos);
    }

    // 터치 중
    private void touchState() {
        targetPos.setVector(
                global.inputManager.touch(0).x(),
                global.inputManager.touch(0).y()
        );
        SlideMove();
    }

    // 터치 끝
    private void touchEnd() {

    }

    // 주 랜더
    public void Render(Canvas canvas) {

    }

    // UI 랜더
    public void UIRender(Canvas canvas) {

        SearchDisplay(canvas);
        canvas.drawBitmap(
                aiming,
                (global.camera.getSizeWidth() / 2) - (aiming.getWidth() / 2),
                (global.camera.getSizeHeight() / 2) - (aiming.getHeight() / 2),
                null
        );

        backSpace.Render(canvas);
        CheckButton.Render(canvas);
    }
}
