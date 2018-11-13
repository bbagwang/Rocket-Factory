package navi_studio.rocket_fectory.Action.Construction;


import android.graphics.Canvas;
import android.graphics.Paint;

import navi_studio.rocket_fectory.Button.AnimationButton;
import navi_studio.rocket_fectory.Button.NullButton;
import navi_studio.rocket_fectory.Button.ScrollBar;
import navi_studio.rocket_fectory.Function.Animation;
import navi_studio.rocket_fectory.Function.SoundUnit;
import navi_studio.rocket_fectory.Function.Vector2;
import navi_studio.rocket_fectory.Global.BitmapResource;
import navi_studio.rocket_fectory.Global.BlackSide;
import navi_studio.rocket_fectory.Global.Global;
import navi_studio.rocket_fectory.Global.InputManager;
import navi_studio.rocket_fectory.Object.Core.ObjLow;
import navi_studio.rocket_fectory.Object.Manager.ObjManager;
import navi_studio.rocket_fectory.R;

public class BuildingSelectAction extends ObjLow {

    private AnimationButton backSpace;              // 뒤로가기 버튼
    private AnimationButton CheckButton;            // 확인 버튼
    private Animation previewAnimation;             // 미리보기 애니메이션
    private ScrollBar scrollBarLeft;                // 왼쪽 스크롤 바
    private ScrollBar scrollBarRight;                // 왼쪽 스크롤 바

    private Global global;

    private int storageCode;                        // 받아온 코드   오브젝트 ID
    private int selectStamp;                        // 선택된 Stamp 코드
    private int selectMark;                         // 선택된 Mark 코드

    private NullButton[] touchPadStamp;
    private NullButton[] touchPadMark;

    private boolean first;

    private ObjManager objManager;
    private PosSelceter posSelceter;

    private boolean selecting;

    SoundUnit Sound_Check_Button;
    SoundUnit Sound_Stamp_Button;
    SoundUnit Sound_Mark_Button;
    SoundUnit Sound_Back_Button;

    // destroy = true 일 경우 파괴
    public void Create(Global _global, ObjManager _objManager) {

        objManager = _objManager;
        global = _global;
        selecting = false;
        posSelceter = new PosSelceter();
        posSelceter.Create(global, objManager);

        backSpace = new AnimationButton();
        CheckButton = new AnimationButton();
        previewAnimation = new Animation();
        scrollBarLeft = new ScrollBar();

        Sound_Check_Button=new SoundUnit();
        Sound_Stamp_Button=new SoundUnit();
        Sound_Mark_Button=new SoundUnit();
        Sound_Back_Button=new SoundUnit();

        Sound_Check_Button.Create(SoundUnit.music, R.raw.check_button,1,global.context);
        Sound_Stamp_Button.Create(SoundUnit.music,R.raw.stamp_mark,1,global.context);
        Sound_Mark_Button.Create(SoundUnit.music,R.raw.stamp_mark,1,global.context);
        Sound_Back_Button.Create(SoundUnit.music,R.raw.back_button,1,global.context);

        touchPadStamp = new NullButton[12];

        for (int i = 0; i < 12; i++)
            touchPadStamp[i] = new NullButton();

        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 3; x++) {
                touchPadStamp[x + (y * 3)].Create(NullButton.BASIC,
                        global.blackSide.UnitConversion(240, BlackSide.width) +
                                (global.bitmapResource.ActionBookStamp[1].getWidth() * x),
                        global.blackSide.UnitConversion(100, BlackSide.height) +
                                (global.bitmapResource.ActionBookStamp[1].getHeight() * y),
                        global.blackSide.UnitConversion(230),
                        global.blackSide.UnitConversion(230),
                        NullButton.RECTANGLE, global
                );
            }
        }

        touchPadMark = new NullButton[7];

        for (int i = 0; i < 7; i++)
            touchPadMark[i] = new NullButton();

        for (int i = 0; i < 7; i++)
            touchPadMark[i].Create(NullButton.BASIC,
                    global.blackSide.UnitConversion(10, BlackSide.width),
                    global.blackSide.UnitConversion(250, BlackSide.height) +
                            ((global.bitmapResource.ActionBookMark[0].getHeight() + global.blackSide.UnitConversion(10)) * i),
                    global.blackSide.UnitConversion(220),
                    global.blackSide.UnitConversion(100),
                    NullButton.RECTANGLE, global
            );

        backSpace.Create(AnimationButton.BASIC,
                new Vector2(global.blackSide.UnitConversion(10, BlackSide.width),
                        global.blackSide.UnitConversion(10, BlackSide.height)),
                AnimationButton.RECTANGLE, global.bitmapResource.ActionBackSpaceImage,
                3, 3, 5, 10, global
        );

        CheckButton.Create(AnimationButton.BASIC,
                new Vector2(global.blackSide.UnitConversion(1090, BlackSide.width),
                        global.blackSide.UnitConversion(860, BlackSide.height)),
                AnimationButton.RECTANGLE, global.bitmapResource.ActionBookButton,
                3, 3, 5, 10, global
        );

        scrollBarLeft.Create(global, new Vector2(
                        global.blackSide.UnitConversion(940, BlackSide.width),
                        global.blackSide.UnitConversion(100, BlackSide.height)),
                ScrollBar.Vertical,
                global.bitmapResource.ActionBookScrollbar,
                global.bitmapResource.ActionBookHandle, 0
        );

        scrollBarRight = new ScrollBar();

        scrollBarRight.Create(global, new Vector2(
                        global.blackSide.UnitConversion(1780, BlackSide.width),
                        global.blackSide.UnitConversion(100, BlackSide.height)),
                ScrollBar.Vertical,
                global.bitmapResource.ActionBookScrollbar,
                global.bitmapResource.ActionBookHandle, 0);

        previewAnimation.Create(Animation.BASIC,
                global.blackSide.UnitConversion(1255, BlackSide.width),
                global.blackSide.UnitConversion(150, BlackSide.height),
                global.bitmapResource.BuildingAnimation[BitmapResource.Production][selectStamp],
                8
        );
    }

    // storageCode 셋팅
    public void set(int _storageCode) {
        storageCode = _storageCode;
    }

    // 파괴
    public boolean Destroy() {
        /*Sound_Check_Button.stop();
        Sound_Stamp_Button.stop();
        Sound_Mark_Button.stop();
        Sound_Back_Button.stop();*/

        return true;
    }

    // 선택
    public void select() {
        selecting = true;
        posSelceter.setting(selectMark, selectStamp);
    }

    // Button Update
    private void ButtonUpdate() {
        backSpace.Update();
        CheckButton.Update();
        scrollBarLeft.Update();
        scrollBarRight.Update();
    }

    private void PreviewAnimationSetting() {
        if (global.bitmapResource.BuildingAnimation.length >= selectStamp)
            previewAnimation.Create(Animation.BASIC,
                    global.blackSide.UnitConversion(1255, BlackSide.width),
                    global.blackSide.UnitConversion(150, BlackSide.height),
                    global.bitmapResource.BuildingAnimation[selectMark][selectStamp],
                    8
            );
    }

    // 주 실행
    private void touchStart() {
        // 처음 터치될때 한번 들어옵니다.
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 3; x++) {
                if (touchPadStamp[x + (y * 3)].TouchCheck(
                        global.inputManager.touch(0).x(),
                        global.inputManager.touch(0).y(),
                        InputManager.DOWN) && global.bitmapResource.BuildingMarkMax[selectMark] > x + (y * 3)) {
                    Sound_Stamp_Button.play(100.0f);

                    selectStamp = x + (y * 3) + ((scrollBarLeft.Value() / 10) * 3);

                    PreviewAnimationSetting();
                }
            }
        }
        for (int i = 0; i < 7; i++) {
            if (touchPadMark[i].TouchCheck(global.inputManager.touch(0).x(),
                    global.inputManager.touch(0).y(),
                    InputManager.DOWN)) {
                Sound_Mark_Button.play(100.0f);
                if (global.bitmapResource.BuildingMarkMax.length > i) {
                    selectStamp = 0;
                    selectMark = i;
                    PreviewAnimationSetting();
                }
            }
        }
    }

    private void touchState() {
        // 터치중
    }

    private void touchEnd() {
        // 터치가 끝날때 한번 들어옵니다.
        for (int i = 0; i < 12; i++)
            touchPadStamp[i].TouchCheck(
                    global.inputManager.touch(0).x(),
                    global.inputManager.touch(0).y(),
                    InputManager.UP);
    }

    public boolean Update() {
        if (selecting) {
            if(posSelceter.Update()) {
                selecting = false;
                return Destroy();
            }
            return false;
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
        ButtonUpdate();
        if (backSpace.isClick()) {
            if (backSpace.isStart()) {
                Sound_Back_Button.play(100.0f);
            }
            return Destroy();
        }
        else if (CheckButton.isClick()) {
            select();
        }
        if(CheckButton.isStart()){
            Sound_Check_Button.play(100.0f);
        }

        return false;
    }


    // Stamp 랜더
    private void StampRender(Canvas canvas) {
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 3; x++) {
                if ((x + (y * 3) == (selectStamp - ((scrollBarLeft.Value() / 10) * 3)))) {
                    canvas.drawBitmap(global.bitmapResource.ActionBookStamp[1],
                            global.blackSide.UnitConversion(240, BlackSide.width) +
                                    (global.bitmapResource.ActionBookStamp[1].getWidth() * x),
                            global.blackSide.UnitConversion(100, BlackSide.height) +
                                    (global.bitmapResource.ActionBookStamp[1].getHeight() * y),
                            null);
                } else {
                    canvas.drawBitmap(global.bitmapResource.ActionBookStamp[0],
                            global.blackSide.UnitConversion(240, BlackSide.width) +
                                    (global.bitmapResource.ActionBookStamp[0].getWidth() * x),
                            global.blackSide.UnitConversion(100, BlackSide.height) +
                                    (global.bitmapResource.ActionBookStamp[0].getHeight() * y),
                            null);
                }
            }
        }
    }


    // 건물 랜더
    private void BuildingRender(Canvas canvas) {
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 3; x++) {
                if (x + ((y + (scrollBarLeft.Value() / 10)) * 3) < global.bitmapResource.BuildingMarkMax[selectMark]) {
                    if (x + ((y + (scrollBarLeft.Value() / 10)) * 3) == selectStamp) {
                        canvas.drawBitmap(global.bitmapResource.ActionBookBuildingStamp[selectMark][x + ((y + (scrollBarLeft.Value() / 10)) * 3)],
                                global.blackSide.UnitConversion(271, BlackSide.width) +
                                        ((global.bitmapResource.ActionBookBuildingStamp[selectMark][0].getWidth() + global.blackSide.UnitConversion(44)) * x),
                                global.blackSide.UnitConversion(150, BlackSide.height) +
                                        ((global.bitmapResource.ActionBookBuildingStamp[selectMark][0].getHeight() + global.blackSide.UnitConversion(40)) * y),
                                null);
                    } else {
                        canvas.drawBitmap(global.bitmapResource.ActionBookBuildingStamp[selectMark][x + ((y + (scrollBarLeft.Value() / 10)) * 3)],
                                global.blackSide.UnitConversion(261, BlackSide.width) +
                                        ((global.bitmapResource.ActionBookBuildingStamp[selectMark][0].getWidth() + global.blackSide.UnitConversion(44)) * x),
                                global.blackSide.UnitConversion(140, BlackSide.height) +
                                        ((global.bitmapResource.ActionBookBuildingStamp[selectMark][0].getHeight() + global.blackSide.UnitConversion(40)) * y),
                                null);
                    }
                }
            }
        }
    }

    // 마크 랜더
    private void MarkRender(Canvas canvas) {
        for (int i = 0; i < 7; i++) {
            //touchPadMark
            if (i == selectMark) {
                canvas.drawBitmap(global.bitmapResource.ActionBookMark[1],
                        global.blackSide.UnitConversion(10, BlackSide.width),
                        global.blackSide.UnitConversion(240, BlackSide.height) +
                                ((global.bitmapResource.ActionBookMark[1].getHeight() + global.blackSide.UnitConversion(10)) * i),
                        null);
            } else {
                canvas.drawBitmap(global.bitmapResource.ActionBookMark[0],
                        global.blackSide.UnitConversion(10, BlackSide.width),
                        global.blackSide.UnitConversion(240, BlackSide.height) +
                                ((global.bitmapResource.ActionBookMark[0].getHeight() + global.blackSide.UnitConversion(10)) * i),
                        null);
            }
        }
    }

    // 미리보기 애니메이션 랜더
    private void PreviewAnimationRender(Canvas canvas) {
        previewAnimation.Render(canvas);
    }

    // 설명 랜더
    private void ExplanationRender(Canvas canvas) {
    }

    public void FirstRender(Canvas canvas) {
        if (selecting) {
            posSelceter.FirstRender(canvas);
            return;
        }
    }

    public void Render(Canvas canvas) {
        if (selecting) {
            posSelceter.Render(canvas);
            return;
        }
    }

    // 왼쪽 페이지 랜더
    private void LeftPageRender(Canvas canvas) {
        StampRender(canvas);
        BuildingRender(canvas);
        MarkRender(canvas);
        scrollBarLeft.Render(canvas);
    }

    // 오른쪽 페이지 랜더
    private void RightPageRender(Canvas canvas) {
        PreviewAnimationRender(canvas);
        ExplanationRender(canvas);
        scrollBarRight.Render(canvas);
    }

    // UI 랜더
    public void UIRender(Canvas canvas) {

        if (selecting) {
            posSelceter.UIRender(canvas);
            return;
        }

        canvas.drawBitmap(global.bitmapResource.ActionBookImage,
                global.blackSide.UnitConversion(210, BlackSide.width),
                global.blackSide.UnitConversion(20, BlackSide.height), null);

        LeftPageRender(canvas);
        RightPageRender(canvas);

        backSpace.Render(canvas);
        CheckButton.Render(canvas);
    }
}
