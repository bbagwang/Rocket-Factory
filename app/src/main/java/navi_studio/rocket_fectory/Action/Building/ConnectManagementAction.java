package navi_studio.rocket_fectory.Action.Building;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import navi_studio.rocket_fectory.Button.AnimationButton;
import navi_studio.rocket_fectory.Function.Vector2;
import navi_studio.rocket_fectory.Global.BlackSide;
import navi_studio.rocket_fectory.Global.Global;
import navi_studio.rocket_fectory.Object.Basic.Conveyor;
import navi_studio.rocket_fectory.Object.Core.ObjLow;
import navi_studio.rocket_fectory.Object.Manager.ObjManager;
import navi_studio.rocket_fectory.R;

public class ConnectManagementAction extends ObjLow {

    private final static int InMode = 0;            // In 모드
    private final static int OutMode = 1;           // Out 모드

    private final static int Non = 0;           // 선택 안함
    private final static int Make = 1;           // 컨베이어 건설 중
    private final static int Destroy = 2;           // 컨베이어 파괴 중
    private final static int Change = 3;           // 컨베이어 교체 중

    private Global global;
    private ObjManager objManager;

    private int storageCode;                        // 받아온 코드, 오브젝트 아이디
    private int mode;                               // in / out 모드 관리
    private int State;                              // 추가 작동 관리

    private InputResourceActionConveyorVersion inputResourceAction;

    private Bitmap BackGroundImage;

    private Bitmap[] ChangeButtonImage;
    private Bitmap[] MakeButtonImage;
    private Bitmap[] DestroyButtonImage;
    private Bitmap[] InOutButtonImage;

    private Bitmap[] directionSign;
    private Bitmap[] InOutSign;
    private Bitmap[] OnOffSign;

    private AnimationButton backSpace;              // 뒤로가기 버튼

    private AnimationButton InOutButton;              // In / Out 교체 버튼
    private AnimationButton[] ChangeButton;             // 컨베이어 교체
    private AnimationButton[] MakeButton;               // 컨베이어 제작
    private AnimationButton[] DestroyButton;            // 컨베이어 파괴

    // destroy = true 일 경우 파괴
    public void Create(Global _global, ObjManager _objManager) {
        global = _global;
        objManager = _objManager;
        mode = OutMode;
        State = Non;

        inputResourceAction = new InputResourceActionConveyorVersion();

        inputResourceAction.Create(global, objManager);

        BackGroundImage = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.connectmanagerbackground);
        BackGroundImage = Bitmap.createScaledBitmap(BackGroundImage,
                global.blackSide.UnitConversion(1920),
                global.blackSide.UnitConversion(1080), true);

        ChangeButtonImage = new Bitmap[5];
        DestroyButtonImage = new Bitmap[5];
        MakeButtonImage = new Bitmap[5];
        InOutButtonImage = new Bitmap[5];

        directionSign = new Bitmap[2];
        InOutSign = new Bitmap[2];
        OnOffSign = new Bitmap[2];

        ChangeButtonImage[0] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.connectmanagerchangebutton1);
        ChangeButtonImage[1] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.connectmanagerchangebutton2);
        ChangeButtonImage[2] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.connectmanagerchangebutton3);
        ChangeButtonImage[3] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.connectmanagerchangebutton4);
        ChangeButtonImage[4] = ChangeButtonImage[0];
        for (int i = 0; i < ChangeButtonImage.length - 1; i++)
            ChangeButtonImage[i] = Bitmap.createScaledBitmap(ChangeButtonImage[i],
                    global.blackSide.UnitConversion(310),
                    global.blackSide.UnitConversion(210), true);

        DestroyButtonImage[0] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.connectmanagerdestroybutton1);
        DestroyButtonImage[1] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.connectmanagerdestroybutton2);
        DestroyButtonImage[2] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.connectmanagerdestroybutton3);
        DestroyButtonImage[3] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.connectmanagerdestroybutton4);
        DestroyButtonImage[4] = DestroyButtonImage[0];
        for (int i = 0; i < DestroyButtonImage.length - 1; i++)
            DestroyButtonImage[i] = Bitmap.createScaledBitmap(DestroyButtonImage[i],
                    global.blackSide.UnitConversion(310),
                    global.blackSide.UnitConversion(210), true);

        MakeButtonImage[0] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.connectmanagermakebutton1);
        MakeButtonImage[1] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.connectmanagermakebutton2);
        MakeButtonImage[2] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.connectmanagermakebutton3);
        MakeButtonImage[3] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.connectmanagermakebutton4);
        MakeButtonImage[4] = MakeButtonImage[0];
        for (int i = 0; i < MakeButtonImage.length - 1; i++)
            MakeButtonImage[i] = Bitmap.createScaledBitmap(MakeButtonImage[i],
                    global.blackSide.UnitConversion(310),
                    global.blackSide.UnitConversion(210), true);

        InOutButtonImage[0] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.connectmanagerinoutbutton1);
        InOutButtonImage[1] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.connectmanagerinoutbutton2);
        InOutButtonImage[2] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.connectmanagerinoutbutton3);
        InOutButtonImage[3] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.connectmanagerinoutbutton4);
        InOutButtonImage[4] = InOutButtonImage[0];
        for (int i = 0; i < InOutButtonImage.length - 1; i++)
            InOutButtonImage[i] = Bitmap.createScaledBitmap(InOutButtonImage[i],
                    global.blackSide.UnitConversion(150),
                    global.blackSide.UnitConversion(230), true);

        directionSign[0] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.connectmanagerdirection1);
        directionSign[1] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.connectmanagerdirection2);
        for (int i = 0; i < directionSign.length; i++)
            directionSign[i] = Bitmap.createScaledBitmap(directionSign[i],
                    global.blackSide.UnitConversion(70),
                    global.blackSide.UnitConversion(120), true);

        InOutSign[0] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.connectmanagerinoutsign1);
        InOutSign[1] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.connectmanagerinoutsign2);
        for (int i = 0; i < InOutSign.length; i++)
            InOutSign[i] = Bitmap.createScaledBitmap(InOutSign[i],
                    global.blackSide.UnitConversion(310),
                    global.blackSide.UnitConversion(130), true);

        OnOffSign[0] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.connectmanageronoff1);
        OnOffSign[1] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.connectmanageronoff2);
        for (int i = 0; i < OnOffSign.length; i++)
            OnOffSign[i] = Bitmap.createScaledBitmap(OnOffSign[i],
                    global.blackSide.UnitConversion(170),
                    global.blackSide.UnitConversion(140), true);

        backSpace = new AnimationButton();
        backSpace.Create(AnimationButton.BASIC,
                new Vector2(global.blackSide.UnitConversion(10, BlackSide.width),
                        global.blackSide.UnitConversion(10, BlackSide.height)),
                AnimationButton.RECTANGLE, global.bitmapResource.ActionBackSpaceImage,
                3, 3, 5, 10, global
        );

        InOutButton = new AnimationButton();
        InOutButton.Create(AnimationButton.BASIC,
                new Vector2(global.blackSide.UnitConversion(1290, BlackSide.width),
                        global.blackSide.UnitConversion(0, BlackSide.height)),
                AnimationButton.RECTANGLE, InOutButtonImage,
                3, 3, 5, 10, global
        );

        ChangeButton = new AnimationButton[3];

        for (int i = 0; i < ChangeButton.length; i++) {
            ChangeButton[i] = new AnimationButton();
            ChangeButton[i].Create(AnimationButton.BASIC,
                    new Vector2(global.blackSide.UnitConversion(1570, BlackSide.width),
                            global.blackSide.UnitConversion(280 + (i * 50), BlackSide.height)),
                    AnimationButton.RECTANGLE, ChangeButtonImage,
                    3, 3, 5, 10, global
            );
        }

        MakeButton = new AnimationButton[3];

        for (int i = 0; i < MakeButton.length; i++) {
            MakeButton[i] = new AnimationButton();
            MakeButton[i].Create(AnimationButton.BASIC,
                    new Vector2(global.blackSide.UnitConversion(1250, BlackSide.width),
                            global.blackSide.UnitConversion(280 + (i * 50), BlackSide.height)),
                    AnimationButton.RECTANGLE, MakeButtonImage,
                    3, 3, 5, 10, global
            );
        }

        DestroyButton = new AnimationButton[3];

        for (int i = 0; i < MakeButton.length; i++) {
            DestroyButton[i] = new AnimationButton();
            DestroyButton[i].Create(AnimationButton.BASIC,
                    new Vector2(global.blackSide.UnitConversion(1250, BlackSide.width),
                            global.blackSide.UnitConversion(280 + (i * 50), BlackSide.height)),
                    AnimationButton.RECTANGLE, DestroyButtonImage,
                    3, 3, 5, 10, global
            );
        }
    }

    public void set(int _storageCode) {
        storageCode = _storageCode;
    }

    public boolean Destroy() {

        return true;
    }

    // 주 실행
    public boolean Update() {
        // 건물 검색
        // objManager.BuildingSearch(storageCode);

        if(State == Make) {
            if(inputResourceAction.Update())
                State = Non;
            return false;
        }

        backSpace.Update();
        InOutButton.Update();

        if (InOutButton.isClick()) {
            if (mode == InMode)
                mode = OutMode;
            else
                mode = InMode;
        }

        if (backSpace.isClick())
            return Destroy();

        if (mode == InMode) {
            for (int i = 0; i < objManager.BuildingSearch(storageCode).getInConnectCount(); i++) {
                ChangeButton[i].Update();

                if (!objManager.BuildingSearch(storageCode).getInConnect().get(i).isCompleted()) {
                    MakeButton[i].Update();
                } else {
                    DestroyButton[i].Update();
                }

                if (MakeButton[i].isClick()) {
                    State = Make;
                    inputResourceAction.set(objManager.BuildingSearch(storageCode).getOutConnect().get(i).getID());
                } else if (DestroyButton[i].isClick()) {

                } else if (ChangeButton[i].isClick()) {

                }

            }
        } else {
            for (int i = 0; i < objManager.BuildingSearch(storageCode).getOutConnectCount(); i++) {
                ChangeButton[i].Update();

                if (!objManager.BuildingSearch(storageCode).getOutConnect().get(i).isCompleted()) {
                    MakeButton[i].Update();
                } else {
                    DestroyButton[i].Update();
                }

                if (MakeButton[i].isClick()) {
                    State = Make;
                    inputResourceAction.set(objManager.BuildingSearch(storageCode).getOutConnect().get(i).getID());
                    inputResourceAction.Update();
                } else if (DestroyButton[i].isClick()) {

                } else if (ChangeButton[i].isClick()) {

                }

            }
        }

        return false;
    }

    public void Render(Canvas canvas) {
    }

    // UI 랜더
    public void UIRender(Canvas canvas) {

        canvas.drawBitmap(BackGroundImage,
                global.blackSide.UnitConversion(0, BlackSide.width),
                global.blackSide.UnitConversion(0, BlackSide.height),
                null);

        backSpace.Render(canvas);

        InOutButton.Render(canvas);

        if (mode == InMode) {

            canvas.drawBitmap(InOutSign[0],
                    global.blackSide.UnitConversion(840, BlackSide.width),
                    global.blackSide.UnitConversion(40, BlackSide.height),
                    null);

            for (int i = 0; i < objManager.BuildingSearch(storageCode).getInConnectCount(); i++) {
                ChangeButton[i].Render(canvas);

                canvas.drawBitmap(directionSign[0],
                        global.blackSide.UnitConversion(290, BlackSide.width),
                        global.blackSide.UnitConversion(300 + (i * 140), BlackSide.height),
                        null);

                if (!objManager.BuildingSearch(storageCode).getInConnect().get(i).isCompleted()) {
                    MakeButton[i].Render(canvas);
                } else {
                    DestroyButton[i].Render(canvas);
                }
            }
        } else {

            canvas.drawBitmap(InOutSign[1],
                    global.blackSide.UnitConversion(840, BlackSide.width),
                    global.blackSide.UnitConversion(40, BlackSide.height),
                    null);

            for (int i = 0; i < objManager.BuildingSearch(storageCode).getOutConnectCount(); i++) {
                ChangeButton[i].Render(canvas);

                canvas.drawBitmap(directionSign[1],
                        global.blackSide.UnitConversion(290, BlackSide.width),
                        global.blackSide.UnitConversion(300 + (i * 140), BlackSide.height),
                        null);

                if (!objManager.BuildingSearch(storageCode).getOutConnect().get(i).isCompleted()) {
                    MakeButton[i].Render(canvas);
                } else {
                    DestroyButton[i].Render(canvas);
                }
            }
        }

        if(State == Make) {
            canvas.drawARGB(120,0,0,0);
            inputResourceAction.Render(canvas);
            inputResourceAction.UIRender(canvas);
        }


    }
}
