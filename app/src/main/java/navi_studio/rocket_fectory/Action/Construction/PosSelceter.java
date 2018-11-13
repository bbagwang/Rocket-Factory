package navi_studio.rocket_fectory.Action.Construction;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import navi_studio.rocket_fectory.Button.AnimationButton;
import navi_studio.rocket_fectory.Button.NullButton;
import navi_studio.rocket_fectory.Function.SoundUnit;
import navi_studio.rocket_fectory.Function.Vector2;
import navi_studio.rocket_fectory.Global.BitmapResource;
import navi_studio.rocket_fectory.Global.BlackSide;
import navi_studio.rocket_fectory.Global.Global;
import navi_studio.rocket_fectory.Global.Inventory;
import navi_studio.rocket_fectory.Global.ItemCode;
import navi_studio.rocket_fectory.Object.Basic.Building;
import navi_studio.rocket_fectory.Object.Basic.BuildingType.Production;
import navi_studio.rocket_fectory.Object.Core.BuildingList;
import navi_studio.rocket_fectory.Object.Core.Ephemera;
import navi_studio.rocket_fectory.Object.Core.ObjLow;
import navi_studio.rocket_fectory.Object.Core.Size.Main.BuildingSize;
import navi_studio.rocket_fectory.Object.Manager.ObjManager;
import navi_studio.rocket_fectory.R;

public class PosSelceter extends ObjLow {

    private Global global;
    private ObjManager objManager;              // 오브젝트 메니저
    private BuildingSize buildingSize;          // 건물 크기 목록
    private BuildingList buildingList;          // 건물 목록 ( 생성 기능 탑제 )

    private AnimationButton backSpace;              // 뒤로가기 버튼
    private AnimationButton CheckButton;            // 확인 버튼

    private Vector2 originPos;     // 터치 시작 위치
    private Vector2 targetPos;     // 터치 현 위치
    private Vector2 MovePos;        // 이동용 위치 저장

    Paint paint;

    private int type;
    private int number;

    private boolean possible;
    private boolean first;

    Bitmap aiming;

    SoundUnit button;
    SoundUnit Sound_Back_Button;

    // destroy = true 일 경우 파괴
    public void Create(Global _global, ObjManager _objManager) {
        objManager = _objManager;
        global = _global;
        possible = true;

        backSpace = new AnimationButton();
        CheckButton = new AnimationButton();
        paint = new Paint();
        originPos = new Vector2();
        targetPos = new Vector2();
        MovePos = new Vector2();
        buildingList = new BuildingList(global);

        buildingSize = new BuildingSize();

        buildingSize.Create(global);

        button=new SoundUnit();
        Sound_Back_Button=new SoundUnit();
        button.Create(SoundUnit.music, R.raw.check_button,1,global.context);
        Sound_Back_Button.Create(SoundUnit.music,R.raw.back_button,1,global.context);

        aiming = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.aiming);
        aiming = Bitmap.createScaledBitmap(aiming,
                global.blackSide.UnitConversion(310),
                global.blackSide.UnitConversion(310), true);

        backSpace.Create(AnimationButton.BASIC,
                new Vector2(global.blackSide.UnitConversion(10, BlackSide.width),
                        global.blackSide.UnitConversion(10, BlackSide.height)),
                AnimationButton.RECTANGLE, global.bitmapResource.ActionBackSpaceImage,
                3, 3, 5, 10, global
        );

        CheckButton.Create(AnimationButton.BASIC,
                new Vector2(global.blackSide.UnitConversion(1610, BlackSide.width),
                        global.blackSide.UnitConversion(780, BlackSide.height)),
                AnimationButton.RECTANGLE, global.bitmapResource.CheckButton,
                3, 3, 5, 10, global
        );

        paint.setStrokeWidth(global.blackSide.UnitConversion(10));
    }

    // 파괴
    public boolean Destroy() {
        /*Sound_Back_Button.stop();
        button.stop();*/
        return true;
    }

    // 값 세팅
    public void setting(int _type, int _number) {
        type = _type;
        number = _number;
    }

    // UI 업데이트
    private boolean UIUpdate() {
        CheckButton.Update();
        backSpace.Update();
        if(CheckButton.isStart())
            button.play(100.0f);

        if(backSpace.isStart())
            Sound_Back_Button.play(100.0f);

        if (CheckButton.isClick()) {
            // 건물 생성
            if (possible) {
                // 건설 가능
                Building temp = buildingList.Creator(type, number,
                        new Vector2(
                                global.camera.getPos().x,
                                global.camera.getPos().y)
                );
                objManager.add(temp);
            } else {
                // 건설 불가능
            }
            return Destroy();
        }
        if (backSpace.isClick()) {
            return Destroy();
        }
        if(backSpace.isStart())
            Sound_Back_Button.play(100.0f);
            return false;
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

    // 금지구역 확인
    private void possibleCheck() {

        possible = true;

        // 거리 체크
        for (int i = 0; i < objManager.getSize(ObjManager.BuildingType); i++) {
            if (objManager.getBuilding().get(i).getSelect()) {
                // 건설할 기지 기준으로 거리 측정
                if ((float) buildingSize.getSize(type, number) > global.camera.getPos().ForDistance(objManager.getBuilding().get(i).getPos())) {
                    possible = false;
                    return;
                }
                // 건설되어 있는 기지 기준으로 거리 측정
                if ((float) objManager.getBuilding().get(i).getSize() > objManager.getBuilding().get(i).getPos().ForDistance(global.camera.getPos())) {
                    possible = false;
                    return;
                }
            }
        }
        // Production 타입은 추가적으로 검사합니다.
        if (type == BitmapResource.Production) {
            for (int i = 0; i < objManager.getSize(ObjManager.MaterialType); i++) {
                if (objManager.getMaterial().get(i).getSelect()) {
                    if (objManager.getMaterial().get(i).touchCheck(
                            global.camera.getPos().x,
                            global.camera.getPos().y)) {

                        Production temp = buildingList.getProduction(number);

                        if (temp.Availability(objManager.getMaterial().get(i).getCode())) {
                            possible = true;
                            temp = null;
                            return;
                        }
                        temp = null;
                    }
                }
            }
            possible = false;

        }
    }

    // 주 실행
    public boolean Update() {
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
        possibleCheck();
        return UIUpdate();
    }

    public void FirstRender(Canvas canvas) {
    }

    // 건설 금지구역 렌더링
    public void ProhibitedAreaRender(Canvas canvas) {
        // 건설 금지구역 렌더링
        for (int i = 0; i < objManager.getSize(ObjManager.BuildingType); i++) {
            if (objManager.getBuilding().get(i).getSelect()) {
                paint.setARGB(60, 255, 60, 0);
                paint.setStyle(Paint.Style.FILL);

                canvas.drawCircle(
                        objManager.getBuilding().get(i).getPos().x - global.camera.getPos().x + (global.camera.getSizeWidth() / 2),
                        objManager.getBuilding().get(i).getPos().y - global.camera.getPos().y + (global.camera.getSizeHeight() / 2),
                        objManager.getBuilding().get(i).getSize(),
                        paint
                );

                paint.setARGB(255, 255, 0, 0);
                paint.setStyle(Paint.Style.STROKE);

                canvas.drawCircle(
                        objManager.getBuilding().get(i).getPos().x - global.camera.getPos().x + (global.camera.getSizeWidth() / 2),
                        objManager.getBuilding().get(i).getPos().y - global.camera.getPos().y + (global.camera.getSizeHeight() / 2),
                        objManager.getBuilding().get(i).getSize(),
                        paint
                );
            }
        }

        // 건설할 건물 금지구역 랜더
        if (possible) {
            paint.setARGB(60, 0, 0, 255);
            paint.setStyle(Paint.Style.FILL);

            canvas.drawCircle(
                    (global.camera.getSizeWidth() / 2),
                    (global.camera.getSizeHeight() / 2),
                    buildingSize.getSize(type, number),
                    paint
            );

            paint.setARGB(255, 0, 0, 255);
            paint.setStyle(Paint.Style.STROKE);

            canvas.drawCircle(
                    (global.camera.getSizeWidth() / 2),
                    (global.camera.getSizeHeight() / 2),
                    buildingSize.getSize(type, number),
                    paint
            );
        } else {
            paint.setARGB(60, 255, 60, 0);
            paint.setStyle(Paint.Style.FILL);

            canvas.drawCircle(
                    (global.camera.getSizeWidth() / 2),
                    (global.camera.getSizeHeight() / 2),
                    buildingSize.getSize(type, number),
                    paint
            );

            paint.setARGB(255, 255, 0, 0);
            paint.setStyle(Paint.Style.STROKE);

            canvas.drawCircle(
                    (global.camera.getSizeWidth() / 2),
                    (global.camera.getSizeHeight() / 2),
                    buildingSize.getSize(type, number),
                    paint
            );
        }
    }

    // 주 렌더
    public void Render(Canvas canvas) {

        ProhibitedAreaRender(canvas);

    }

    // UI 렌더
    public void UIRender(Canvas canvas) {

        canvas.drawBitmap(
                aiming,
                (global.camera.getSizeWidth() / 2) - (aiming.getWidth() / 2),
                (global.camera.getSizeHeight() / 2) - (aiming.getHeight() / 2),
                null
        );

        CheckButton.Render(canvas);
        backSpace.Render(canvas);
    }
}
