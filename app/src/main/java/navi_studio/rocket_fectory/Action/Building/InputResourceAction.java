package navi_studio.rocket_fectory.Action.Building;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import navi_studio.rocket_fectory.Button.AnimationButton;
import navi_studio.rocket_fectory.Function.Vector2;
import navi_studio.rocket_fectory.Global.BlackSide;
import navi_studio.rocket_fectory.Global.Global;
import navi_studio.rocket_fectory.Object.Core.ObjLow;
import navi_studio.rocket_fectory.Object.Manager.ObjManager;
import navi_studio.rocket_fectory.Part.ItemCell;
import navi_studio.rocket_fectory.R;

public class InputResourceAction extends ObjLow {

    private AnimationButton backSpace;              // 뒤로가기 버튼
    private AnimationButton CheckButton;            // 완료 버튼
    private AnimationButton[] InputButton;          // 투입버튼

    private Global global;
    private ObjManager objManager;

    private Bitmap BackGround;                          // 배경
    private Bitmap Box;                                 // 박싱
    private Bitmap[] InputButtonImage;                     // 투입 버튼 이미지
    private Bitmap[] CheckButtonImage;                     // 완료 버튼 이미지

    private int storageCode;                        // 받아온 코드, 오브젝트 아이디

    private Paint paint;

    // destroy = true 일 경우 파괴
    public void Create(Global _global, ObjManager _objManager) {

        LoadBitmap();

        global = _global;
        objManager = _objManager;

        paint = new Paint();

        paint.setTextAlign(Paint.Align.CENTER);
        paint.setColor(Color.WHITE);
        paint.setTextSize(global.blackSide.FontUnitConversion(50));

        backSpace = new AnimationButton();
        backSpace.Create(AnimationButton.BASIC,
                new Vector2(global.blackSide.UnitConversion(10, BlackSide.width),
                        global.blackSide.UnitConversion(10, BlackSide.height)),
                AnimationButton.RECTANGLE, global.bitmapResource.ActionBackSpaceImage,
                3, 3, 5, 10, global);

        CheckButton = new AnimationButton();

        CheckButton.Create(AnimationButton.BASIC,
                new Vector2(global.blackSide.UnitConversion(830, BlackSide.width),
                        global.blackSide.UnitConversion(860, BlackSide.height)),
                AnimationButton.RECTANGLE, CheckButtonImage,
                3, 3, 5, 10, global);

        InputButton = new AnimationButton[6];
        for (int x = 0; x < 3; x++)
            for (int y = 0; y < 2; y++) {
                InputButton[x + (y * 3)] = new AnimationButton();
                InputButton[x + (y * 3)].Create(AnimationButton.BASIC,
                        new Vector2(global.blackSide.UnitConversion(330 + (500 * x), BlackSide.width),
                                global.blackSide.UnitConversion(460 + (320 * y), BlackSide.height)),
                        AnimationButton.RECTANGLE, InputButtonImage,
                        2, 2, 4, 10, global);
            }
    }

    // 비트맵 로드
    private void LoadBitmap() {
        // 배경
        BackGround = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.recopebackground);
        BackGround = Bitmap.createScaledBitmap(BackGround,
                global.blackSide.UnitConversion(1650),
                global.blackSide.UnitConversion(1040), true);

        // 박스
        Box = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.recipeitembox);
        Box = Bitmap.createScaledBitmap(Box,
                global.blackSide.UnitConversion(480),
                global.blackSide.UnitConversion(310), true);

        // 투입 버튼
        InputButtonImage = new Bitmap[4];

        InputButtonImage[0] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.recipeinsertbutton1);
        InputButtonImage[1] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.recipeinsertbutton2);
        InputButtonImage[2] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.recipeinsertbutton3);
        InputButtonImage[3] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.recipeinsertbutton1);

        for (int i = 0; i < InputButtonImage.length; i++)
            InputButtonImage[i] = Bitmap.createScaledBitmap(InputButtonImage[i],
                    global.blackSide.UnitConversion(460),
                    global.blackSide.UnitConversion(80), true);

        CheckButtonImage = new Bitmap[5];

        CheckButtonImage[0] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.recipecompletebutton1);
        CheckButtonImage[1] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.recipecompletebutton2);
        CheckButtonImage[2] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.recipecompletebutton3);
        CheckButtonImage[3] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.recipecompletebutton4);
        CheckButtonImage[4] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.recipecompletebutton1);

        for (int i = 0; i < CheckButtonImage.length; i++)
            CheckButtonImage[i] = Bitmap.createScaledBitmap(CheckButtonImage[i],
                    global.blackSide.UnitConversion(460),
                    global.blackSide.UnitConversion(150), true);

    }

    public void set(int _storageCode) {
        storageCode = _storageCode;
    }

    public boolean Destroy() {

        return true;
    }

    private void Construct() {
        objManager.BuildingSearch(storageCode).Completed();
    }

    private boolean Check() {
        // 하나라도 충족되지 않을경우
        for (int i = 0; i < objManager.BuildingSearch(storageCode).RequiredItem().length; i++) {
            if(!objManager.BuildingSearch(storageCode).CheckItem()[i])
                return false;
        }
        // 하나도 충족되지 않는경우가 없을경우
        return true;
    }

    // 주 실행
    public boolean Update() {
        backSpace.Update();
        CheckButton.Update();
        if (backSpace.isClick())
            return Destroy();

        // 확인버튼
        if (CheckButton.isClick()) {
            if(Check()){
                // 충분한 준비가 되어있는 경우
                Construct();
                return Destroy();
            }
            else{
                // 충분한 준비가 되어있지 않을경우
            }
        }
        for (int i = 0; i < InputButton.length; i++)
            InputButton[i].Update();

        for (int i = 0; i < objManager.BuildingSearch(storageCode).RequiredItem().length; i++) {
            if (InputButton[i].isClick()) {
                // 건물 검색
                // 비교
                if (global.inventory.isEnough(
                        objManager.BuildingSearch(storageCode).RequiredItem()[i].code,
                        objManager.BuildingSearch(storageCode).RequiredItem()[i].number)) {
                    // 충분한 아이템을 소지하고 있을 경우
                    global.inventory.subItem(
                            objManager.BuildingSearch(storageCode).RequiredItem()[i].code,
                            objManager.BuildingSearch(storageCode).RequiredItem()[i].number);
                    objManager.BuildingSearch(storageCode).insertItem(i);                           // 투입
                }else{
                    // 충분한 아이템을 소지하고 있지 않을경우
                }
            }
        }

        return false;
    }

    public void Render(Canvas canvas) {
        canvas.drawBitmap(BackGround,
                global.blackSide.UnitConversion(240, BlackSide.width),
                global.blackSide.UnitConversion(10, BlackSide.height),
                null);
        for (int i = 0; i < InputButton.length; i++)
            InputButton[i].Render(canvas);

        for (int i = 0; i < objManager.BuildingSearch(storageCode).RequiredItem().length; i++) {
            canvas.drawText("" + objManager.BuildingSearch(storageCode).RequiredItem()[i].number,
                    global.blackSide.UnitConversion(380 + ((i - ((i / 3) * 3)) * 500), BlackSide.width),
                    global.blackSide.UnitConversion(460 + ((i / 3) * 320), BlackSide.height),
                    paint
            );

            switch (objManager.BuildingSearch(storageCode).RequiredItem()[i].type) {
                case ItemCell.RawMaterials:
                    canvas.drawBitmap(
                            global.itemCode.getRawMaterialsCell(objManager.BuildingSearch(storageCode).RequiredItem()[i].code).image,

                            global.blackSide.UnitConversion(450 + ((i - ((i / 3) * 3)) * 500), BlackSide.width),
                            global.blackSide.UnitConversion(250 + ((i / 3) * 320), BlackSide.height),
                            null);

                    canvas.drawText("" + global.inventory.item.getRawMaterialsCell(objManager.BuildingSearch(storageCode).RequiredItem()[i].code).number,
                            global.blackSide.UnitConversion(740 + ((i - ((i / 3) * 3)) * 500), BlackSide.width),
                            global.blackSide.UnitConversion(460 + ((i / 3) * 320), BlackSide.height),
                            paint
                    );
                    break;
                case ItemCell.Material:
                    canvas.drawBitmap(
                            global.itemCode.getMaterialCell(objManager.BuildingSearch(storageCode).RequiredItem()[i].code).image,

                            global.blackSide.UnitConversion(450 + ((i - ((i / 3) * 3)) * 500), BlackSide.width),
                            global.blackSide.UnitConversion(250 + ((i / 3) * 320), BlackSide.height),
                            null);

                    canvas.drawText("" + global.inventory.item.getMaterialCell(objManager.BuildingSearch(storageCode).RequiredItem()[i].code).number,
                            global.blackSide.UnitConversion(740 + ((i - ((i / 3) * 3)) * 500), BlackSide.width),
                            global.blackSide.UnitConversion(460 + ((i / 3) * 320), BlackSide.height),
                            paint
                    );
                    break;
                case ItemCell.Parts:
                    canvas.drawBitmap(
                            global.itemCode.getPartsCell(objManager.BuildingSearch(storageCode).RequiredItem()[i].code).image,

                            global.blackSide.UnitConversion(450 + ((i - ((i / 3) * 3)) * 500), BlackSide.width),
                            global.blackSide.UnitConversion(250 + ((i / 3) * 320), BlackSide.height),
                            null);

                    canvas.drawText("" + global.inventory.item.getPartsCell(objManager.BuildingSearch(storageCode).RequiredItem()[i].code).number,
                            global.blackSide.UnitConversion(740 + ((i - ((i / 3) * 3)) * 500), BlackSide.width),
                            global.blackSide.UnitConversion(460 + ((i / 3) * 320), BlackSide.height),
                            paint
                    );
                    break;
                case ItemCell.Consumption:
                    canvas.drawBitmap(
                            global.itemCode.getConsumptionCell(objManager.BuildingSearch(storageCode).RequiredItem()[i].code).image,

                            global.blackSide.UnitConversion(450 + ((i - ((i / 3) * 3)) * 500), BlackSide.width),
                            global.blackSide.UnitConversion(250 + ((i / 3) * 320), BlackSide.height),
                            null);

                    canvas.drawText("" + global.inventory.item.getConsumptionCell(objManager.BuildingSearch(storageCode).RequiredItem()[i].code).number,
                            global.blackSide.UnitConversion(740 + ((i - ((i / 3) * 3)) * 500), BlackSide.width),
                            global.blackSide.UnitConversion(460 + ((i / 3) * 320), BlackSide.height),
                            paint
                    );
                    break;
                case ItemCell.Special:
                    canvas.drawBitmap(
                            global.itemCode.getSpecialCell(objManager.BuildingSearch(storageCode).RequiredItem()[i].code).image,

                            global.blackSide.UnitConversion(450 + ((i - ((i / 3) * 3)) * 500), BlackSide.width),
                            global.blackSide.UnitConversion(250 + ((i / 3) * 320), BlackSide.height),
                            null);

                    canvas.drawText("" + global.inventory.item.getSpecialCell(objManager.BuildingSearch(storageCode).RequiredItem()[i].code).number,
                            global.blackSide.UnitConversion(740 + ((i - ((i / 3) * 3)) * 500), BlackSide.width),
                            global.blackSide.UnitConversion(460 + ((i / 3) * 320), BlackSide.height),
                            paint
                    );
                    break;
            }

            if (objManager.BuildingSearch(storageCode).CheckItem()[i]) {
                canvas.drawBitmap(
                        Box,
                        global.blackSide.UnitConversion(320 + ((i - ((i / 3) * 3)) * 500), BlackSide.width),
                        global.blackSide.UnitConversion(230 + ((i / 3) * 320), BlackSide.height),
                        null);
            }
        }
        for (int i = objManager.BuildingSearch(storageCode).RequiredItem().length;
             i < InputButton.length;
             i++) {
            canvas.drawBitmap(
                    Box,
                    global.blackSide.UnitConversion(320 + ((i - ((i / 3) * 3)) * 500), BlackSide.width),
                    global.blackSide.UnitConversion(230 + ((i / 3) * 320), BlackSide.height),
                    null);
        }
    }

    // UI 랜더
    public void UIRender(Canvas canvas) {
        CheckButton.Render(canvas);
        backSpace.Render(canvas);
    }
}
