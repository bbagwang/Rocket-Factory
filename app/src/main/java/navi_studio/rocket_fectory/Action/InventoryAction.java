package navi_studio.rocket_fectory.Action;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;

import java.util.ArrayList;

import navi_studio.rocket_fectory.Button.AnimationButton;
import navi_studio.rocket_fectory.Button.NullButton;
import navi_studio.rocket_fectory.Button.ScrollBar;
import navi_studio.rocket_fectory.Function.Calculator;
import navi_studio.rocket_fectory.Function.GaugeBar;
import navi_studio.rocket_fectory.Function.SoundUnit;
import navi_studio.rocket_fectory.Function.Vector2;
import navi_studio.rocket_fectory.Global.BlackSide;
import navi_studio.rocket_fectory.Global.Global;
import navi_studio.rocket_fectory.Object.Core.ObjLow;
import navi_studio.rocket_fectory.Part.ItemCell;
import navi_studio.rocket_fectory.R;

public class InventoryAction extends ObjLow {

    private Global global;      //글로벌 전역
    private AnimationButton backSpace;              // 뒤로가기 버튼
    private AnimationButton Check_Button;           // 체크 버튼
    private AnimationButton Calculator_Button;      // 계산기 버튼
    private NullButton Up_Button;                   // 증가 버튼
    private NullButton Down_Button;                 // 감소 버튼
    private ScrollBar scrollBar;                    // 스크롤 바
    private GaugeBar gaugeBar;                      // 게이지 바

    private NullButton[] touchPadStamp;             // 스탬프 터치 입력생성
    private NullButton[] touchPadMark;              // 마크 터치 입력생성

    Paint paint;

    int selected_mark = 0;      //선택된 카테고리
    int selected_stamp = 0;       //선택된 물건

    private Calculator calculator;
    public boolean calculator_result_return = true;    //값 받았음?
    public int throw_amount;   //버리는 양

    //TODO : Pick Inventory Sound
    SoundUnit Sound_Check_Button;
    SoundUnit Sound_Calculator_Button;
    SoundUnit Sound_Stamp_Button;
    SoundUnit Sound_Mark_Button;
    SoundUnit Sound_Up_Button;
    SoundUnit Sound_Down_Button;
    SoundUnit Sound_Back_Button;

    // destroy = true 일 경우 파괴
    public void Create(Global _global) {

        global = _global;
        paint = new Paint();
        backSpace = new AnimationButton();
        Check_Button = new AnimationButton();
        Calculator_Button = new AnimationButton();
        Up_Button = new NullButton();
        Down_Button = new NullButton();
        scrollBar = new ScrollBar();
        gaugeBar = new GaugeBar();

        throw_amount = 0;         //버릴 용량
        calculator = new Calculator();
        calculator.Create(global);

        Sound_Check_Button=new SoundUnit();
        Sound_Calculator_Button=new SoundUnit();
        Sound_Stamp_Button=new SoundUnit();
        Sound_Mark_Button=new SoundUnit();
        Sound_Up_Button=new SoundUnit();
        Sound_Down_Button=new SoundUnit();
        Sound_Back_Button=new SoundUnit();

        Sound_Check_Button.Create(SoundUnit.music,R.raw.check_button,1,global.context);
        Sound_Calculator_Button.Create(SoundUnit.music,R.raw.calculator_button,1,global.context);
        Sound_Stamp_Button.Create(SoundUnit.music,R.raw.stamp_mark,1,global.context);
        Sound_Mark_Button.Create(SoundUnit.music,R.raw.stamp_mark,1,global.context);
        Sound_Up_Button.Create(SoundUnit.music,R.raw.up_button,1,global.context);
        Sound_Down_Button.Create(SoundUnit.music,R.raw.down_button,1,global.context);
        Sound_Back_Button.Create(SoundUnit.music,R.raw.back_button,1,global.context);

        //TouchPad [STAMP] Init
        touchPadStamp = new NullButton[9];
        for (int i = 0; i < 9; i++)
            touchPadStamp[i] = new NullButton();
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                touchPadStamp[(y * 3) + x].Create(NullButton.BASIC,
                        global.blackSide.UnitConversion((230 + (x * 240) + 10), BlackSide.width),
                        global.blackSide.UnitConversion((230 + (y * 240) + 10), BlackSide.height),
                        global.blackSide.UnitConversion(240), global.blackSide.UnitConversion(240), NullButton.RECTANGLE, global);
            }
        }

        //TouchPad [MARK] Init
        touchPadMark = new NullButton[5];
        for (int i = 0; i < 5; i++)
            touchPadMark[i] = new NullButton();
        for (int i = 0; i < 5; i++)
            touchPadMark[i].Create(NullButton.BASIC,
                    global.blackSide.UnitConversion(10, BlackSide.width),
                    global.blackSide.UnitConversion(230 + (i * 120) + 10, BlackSide.height),
                    global.blackSide.UnitConversion(220), global.blackSide.UnitConversion(120),
                    NullButton.RECTANGLE, global);

        backSpace.Create(AnimationButton.BASIC,
                new Vector2(global.blackSide.UnitConversion(10, BlackSide.width),
                        global.blackSide.UnitConversion(10, BlackSide.height)),
                AnimationButton.RECTANGLE, global.bitmapResource.ActionBackSpaceImage,
                3, 3, 5, 10, global);
        Check_Button.Create(AnimationButton.BASIC,
                new Vector2(global.blackSide.UnitConversion(1390, BlackSide.width),
                        global.blackSide.UnitConversion(765, BlackSide.height)),
                AnimationButton.RECTANGLE, global.bitmapResource.inventory_check_button,
                1, 1, 4, 10, global);
        Calculator_Button.Create(AnimationButton.BASIC,
                new Vector2(global.blackSide.UnitConversion(1150, BlackSide.width),
                        global.blackSide.UnitConversion(765, BlackSide.height)),
                AnimationButton.RECTANGLE, global.bitmapResource.inventory_calculator,
                1, 1, 3, 10, global);
        Up_Button.Create(NullButton.BASIC,
                global.blackSide.UnitConversion(1715, BlackSide.width),
                global.blackSide.UnitConversion(510, BlackSide.height),
                global.blackSide.UnitConversion(130), global.blackSide.UnitConversion(240),
                NullButton.RECTANGLE, global);
        Down_Button.Create(NullButton.BASIC,
                global.blackSide.UnitConversion(1135, BlackSide.width),
                global.blackSide.UnitConversion(510, BlackSide.height),
                global.blackSide.UnitConversion(130), global.blackSide.UnitConversion(240),
                NullButton.RECTANGLE, global);
        scrollBar.Create(global,
                new Vector2(global.blackSide.UnitConversion(1000, BlackSide.width),
                        global.blackSide.UnitConversion(240, BlackSide.height)),
                ScrollBar.Vertical, global.bitmapResource.inventory_scrollbar, global.bitmapResource.inventory_scroll_handle, 0);

        gaugeBar.Create(global.bitmapResource.gauge_percent, global.bitmapResource.gauge_background, global.bitmapResource.gauge_frame, new Vector2(
                        global.blackSide.UnitConversion(220, BlackSide.width),
                        global.blackSide.UnitConversion(20, BlackSide.height)),
                global.inventory.MaxSize, false, global.blackSide.UnitConversion(60));
    }

    public void set(int Empty) {
        gaugeBar.setValue(global.inventory.GetSize());
    }

    public boolean Destroy() {
        /*Sound_Calculator_Button.stop();
        Sound_Stamp_Button.stop();
        Sound_Mark_Button.stop();
        Sound_Up_Button.stop();
        Sound_Down_Button.stop();
        Sound_Back_Button.stop();*/
        return true;
    }

    // Button Update
    private void ButtonUpdate() {
        backSpace.Update();
        Check_Button.Update();
        scrollBar.Update();
    }

    // 주 실행
    private void touchStart() {
        // 처음 터치될때 한번 들어옵니다.
    }

    private void touchState() {
        // 터치중
    }

    private void touchEnd() {
        // 터치가 끝날때 한번 들어옵니다.
    }

    private void LeftUpdater() {
        MarkUpdater();
        StampUpdater();
        scrollBar.Update();

    }

    private void RightUpdater() {
        Up_Button.Update();
        Down_Button.Update();
        Calculator_Button.Update();
        Check_Button.Update();
    }

    // 주 실행
    public boolean Update() {

        if (calculator.calculator_running) {
            calculator.Update();

            return false;
        }
        LeftUpdater();
        RightUpdater();

        if (calculator_result_return) {
            throw_amount = calculator.getResult();
            calculator_result_return = false;
        }

        switch (selected_mark) {
            case ItemCell.RawMaterials:
                if (throw_amount > global.inventory.item.getRawMaterialsCell(global.itemCode.RawMaterialsCell.get(selected_stamp).code).number)
                    throw_amount = global.inventory.item.getRawMaterialsCell(global.itemCode.RawMaterialsCell.get(selected_stamp).code).number;
                break;
            case ItemCell.Material:
                if (throw_amount > global.inventory.item.getMaterialCell(global.itemCode.MaterialCell.get(selected_stamp).code).number)
                    throw_amount = global.inventory.item.getMaterialCell(global.itemCode.MaterialCell.get(selected_stamp).code).number;
                break;
            case ItemCell.Parts:
                if (throw_amount > global.inventory.item.getPartsCell(global.itemCode.PartsCell.get(selected_stamp).code).number)
                    throw_amount = global.inventory.item.getPartsCell(global.itemCode.PartsCell.get(selected_stamp).code).number;
                break;
            case ItemCell.Consumption:
                if (throw_amount > global.inventory.item.getConsumptionCell(global.itemCode.ConsumptionCell.get(selected_stamp).code).number)
                    throw_amount = global.inventory.item.getConsumptionCell(global.itemCode.ConsumptionCell.get(selected_stamp).code).number;
                break;
            case ItemCell.Special:
                if (throw_amount > global.inventory.item.getSpecialCell(global.itemCode.SpecialCell.get(selected_stamp).code).number)
                    throw_amount = global.inventory.item.getSpecialCell(global.itemCode.SpecialCell.get(selected_stamp).code).number;
                break;
            default:
        }

        ButtonUpdate();
        if (backSpace.isClick()) {
            Sound_Back_Button.play(100.0f);
            selected_mark = 0;      //선택된 카테고리
            selected_stamp = 0;       //선택된 물건
            return Destroy();
        } else if (Check_Button.isClick()) {
            Sound_Check_Button.play(100.0f);
            switch (selected_mark) {
                case ItemCell.RawMaterials:
                    global.inventory.subItem(global.itemCode.RawMaterialsCell.get(selected_stamp).code, throw_amount);
                    break;
                case ItemCell.Material:
                    global.inventory.subItem(global.itemCode.MaterialCell.get(selected_stamp).code, throw_amount);
                    break;
                case ItemCell.Parts:
                    global.inventory.subItem(global.itemCode.PartsCell.get(selected_stamp).code, throw_amount);
                    break;
                case ItemCell.Consumption:
                    global.inventory.subItem(global.itemCode.ConsumptionCell.get(selected_stamp).code, throw_amount);
                    break;
                case ItemCell.Special:
                    global.inventory.subItem(global.itemCode.SpecialCell.get(selected_stamp).code, throw_amount);
                    break;
                default:
            }
            throw_amount = 0;
            gaugeBar.setValue(global.inventory.GetSize());
        } else if (Calculator_Button.isClick()) {
            Sound_Calculator_Button.play(100.0f);
            calculator.calculator_running = true;
            calculator_result_return = true;
        }
        return false;
    }

    private void MarkUpdater() {
        for (int i = 0; i < 5; i++) {
            touchPadMark[i].Update();
            if (touchPadMark[i].isClick()) {
                Sound_Mark_Button.play(100.0f);
                selected_mark = i;
                selected_stamp = 0;
                throw_amount = 0;
            }
        }
    }

    private void MarkRender(Canvas canvas) {
        for (int i = 0; i < 5; i++) {
            if (touchPadMark[i].isClick()) {
                canvas.drawBitmap(global.bitmapResource.inventory_mark[1],
                        global.blackSide.UnitConversion(10, BlackSide.width),
                        global.blackSide.UnitConversion(230 + (i * 120) + 10, BlackSide.height), null);
                selected_stamp = 0;
            } else {
                canvas.drawBitmap(global.bitmapResource.inventory_mark[0],
                        global.blackSide.UnitConversion(10, BlackSide.width),
                        global.blackSide.UnitConversion(230 + (i * 120) + 10, BlackSide.height), null);
            }
            touchPadMark[i].Render(canvas);
        }
    }

    private void StampUpdater() {
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                touchPadStamp[y * 3 + x].Update();
                if (touchPadStamp[y * 3 + x].isClick()) {
                    Sound_Stamp_Button.play(100.0f);
                    throw_amount = 0;
                    switch (selected_mark) {
                        case ItemCell.RawMaterials:
                            if (global.itemCode.RawMaterialsCell.size() > y * 3 + x)
                                selected_stamp = y * 3 + x;
                            else
                                selected_stamp = 0;
                            break;
                        case ItemCell.Material:
                            if (global.itemCode.MaterialCell.size() > y * 3 + x)
                                selected_stamp = y * 3 + x;
                            else
                                selected_stamp = 0;
                            break;
                        case ItemCell.Parts:
                            if (global.itemCode.PartsCell.size() > y * 3 + x)
                                selected_stamp = y * 3 + x;
                            else
                                selected_stamp = 0;
                            break;
                        case ItemCell.Consumption:
                            if (global.itemCode.ConsumptionCell.size() > y * 3 + x)
                                selected_stamp = y * 3 + x;
                            else
                                selected_stamp = 0;
                            break;
                        case ItemCell.Special:
                            if (global.itemCode.SpecialCell.size() > y * 3 + x)
                                selected_stamp = y * 3 + x;
                            else
                                selected_stamp = 0;
                            break;
                        default:
                            break;
                    }
                }
            }
        }
    }

    private void StampRender(Canvas canvas) {

        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {

                if (touchPadStamp[y * 3 + x].isClick()) {
                    canvas.drawBitmap(global.bitmapResource.inventory_stamp[1],
                            global.blackSide.UnitConversion((235 + (x * 240) + 10), BlackSide.width),
                            global.blackSide.UnitConversion((235 + (y * 240) + 20), BlackSide.height), null);
                } else {
                    canvas.drawBitmap(global.bitmapResource.inventory_stamp[0],
                            global.blackSide.UnitConversion((235 + (x * 240) + 10), BlackSide.width),
                            global.blackSide.UnitConversion((235 + (y * 240) + 20), BlackSide.height), null);
                }
                switch (selected_mark) {
                    case ItemCell.RawMaterials: {
                        if (y * 3 + x < global.itemCode.RawMaterialsCell.size()) {
                            canvas.drawBitmap(global.itemCode.getRawMaterialsCellNumber(y * 3 + x).image,
                                    global.blackSide.UnitConversion((235 + (x * 245) + (x * 10)), BlackSide.width),
                                    global.blackSide.UnitConversion((235 + (y * 245) + (y * 10)), BlackSide.height), null);
                            break;
                        }
                        break;
                    }
                    case ItemCell.Material: {
                        if (y * 3 + x < global.itemCode.MaterialCell.size()) {
                            canvas.drawBitmap(global.itemCode.getMaterialCellNumber(y * 3 + x).image,
                                    global.blackSide.UnitConversion((235 + (x * 245) + (x * 10)), BlackSide.width),
                                    global.blackSide.UnitConversion((235 + (y * 245) + (y * 10)), BlackSide.height), null);
                            break;
                        }
                        break;
                    }
                    case ItemCell.Parts: {
                        if (y * 3 + x < global.itemCode.PartsCell.size()) {
                            canvas.drawBitmap(global.itemCode.getPartsCellNumber(y * 3 + x).image,
                                    global.blackSide.UnitConversion((250 + (x * 245) + (x * 10)), BlackSide.width),
                                    global.blackSide.UnitConversion((250 + (y * 245) + (y * 10)), BlackSide.height), null);
                            break;
                        }
                        break;
                    }
                    case ItemCell.Consumption: {
                        if (y * 3 + x < global.itemCode.ConsumptionCell.size()) {
                            canvas.drawBitmap(global.itemCode.getConsumptionCellNumber(y * 3 + x).image,
                                    global.blackSide.UnitConversion((235 + (x * 245) + (x * 10)), BlackSide.width),
                                    global.blackSide.UnitConversion((235 + (y * 245) + (y * 10)), BlackSide.height), null);
                            break;
                        }
                        break;
                    }
                    case ItemCell.Special: {
                        if (y * 3 + x < global.itemCode.SpecialCell.size()) {
                            canvas.drawBitmap(global.itemCode.getSpecialCellNumber(y * 3 + x).image,
                                    global.blackSide.UnitConversion((235 + (x * 245) + (x * 10)), BlackSide.width),
                                    global.blackSide.UnitConversion((235 + (y * 245) + (y * 10)), BlackSide.height), null);
                            break;
                        }
                        break;
                    }
                    default:
                        break;
                }

                touchPadStamp[y * 3 + x].Render(canvas);
            }
        }
    }

    private void LeftRender(Canvas canvas) {
        scrollBar.Render(canvas);
        StampRender(canvas);
        MarkRender(canvas);
    }

    private void RightRender(Canvas canvas) {
        Calculator_Button.Render(canvas);

        Check_Button.Render(canvas);
        canvas.drawBitmap(global.bitmapResource.inventory_stamp[0],
                global.blackSide.UnitConversion(1135, BlackSide.width),
                global.blackSide.UnitConversion(250, BlackSide.height), null);

        if (Up_Button.isClick()) {
            Sound_Up_Button.play(100.0f);

            canvas.drawBitmap(global.bitmapResource.inventory_up_button[1],
                    global.blackSide.UnitConversion(1715, BlackSide.width),
                    global.blackSide.UnitConversion(510, BlackSide.height), null);

            switch (selected_mark) {
                case ItemCell.RawMaterials:
                    if (throw_amount < global.inventory.item.getRawMaterialsCell(global.itemCode.RawMaterialsCell.get(selected_stamp).code).number)
                        throw_amount++;
                    break;
                case ItemCell.Material:
                    if (throw_amount < global.inventory.item.getMaterialCell(global.itemCode.MaterialCell.get(selected_stamp).code).number)
                        throw_amount++;
                    break;
                case ItemCell.Parts:
                    if (throw_amount < global.inventory.item.getPartsCell(global.itemCode.PartsCell.get(selected_stamp).code).number)
                        throw_amount++;
                    break;
                case ItemCell.Consumption:
                    if (throw_amount < global.inventory.item.getConsumptionCell(global.itemCode.ConsumptionCell.get(selected_stamp).code).number)
                        throw_amount++;
                    break;
                case ItemCell.Special:
                    if (throw_amount < global.inventory.item.getSpecialCell(global.itemCode.SpecialCell.get(selected_stamp).code).number)
                        throw_amount++;
                    break;
                default:
            }
        } else {
            canvas.drawBitmap(global.bitmapResource.inventory_up_button[0],
                    global.blackSide.UnitConversion(1715, BlackSide.width),
                    global.blackSide.UnitConversion(510, BlackSide.height), null);
        }

        if (Down_Button.isClick()) {
            Sound_Down_Button.play(100.0f);
            canvas.drawBitmap(global.bitmapResource.inventory_down_button[1],
                    global.blackSide.UnitConversion(1135, BlackSide.width),
                    global.blackSide.UnitConversion(510, BlackSide.height), null);
            if (throw_amount > 0)
                throw_amount--;
            else
                throw_amount = 0;
        } else {
            canvas.drawBitmap(global.bitmapResource.inventory_down_button[0],
                    global.blackSide.UnitConversion(1135, BlackSide.width),
                    global.blackSide.UnitConversion(510, BlackSide.height), null);
        }

        switch (selected_mark) {
            case ItemCell.RawMaterials:
                if (global.itemCode.RawMaterialsCell.size() >= selected_stamp) {
                    canvas.drawBitmap(global.itemCode.getRawMaterialsCellNumber(selected_stamp).image,
                            global.blackSide.UnitConversion(1135, BlackSide.width),
                            global.blackSide.UnitConversion(255, BlackSide.height), null);
                } else {
                    break;
                }

                break;
            case ItemCell.Material:
                if (global.itemCode.MaterialCell.size() >= selected_stamp) {
                    canvas.drawBitmap(global.itemCode.getMaterialCellNumber(selected_stamp).image,
                            global.blackSide.UnitConversion(1135, BlackSide.width),
                            global.blackSide.UnitConversion(255, BlackSide.height), null);
                } else {
                    break;
                }

                break;
            case ItemCell.Parts:
                if (global.itemCode.PartsCell.size() >= selected_stamp) {
                    canvas.drawBitmap(global.itemCode.getPartsCellNumber(selected_stamp).image,
                            global.blackSide.UnitConversion(1135, BlackSide.width),
                            global.blackSide.UnitConversion(255, BlackSide.height), null);
                } else {
                    break;
                }
                break;
            case ItemCell.Consumption:
                if (global.itemCode.ConsumptionCell.size() >= selected_stamp) {
                    canvas.drawBitmap(global.itemCode.getConsumptionCellNumber(selected_stamp).image,
                            global.blackSide.UnitConversion(1135, BlackSide.width),
                            global.blackSide.UnitConversion(255, BlackSide.height), null);
                } else {
                    break;
                }
                break;
            case ItemCell.Special:
                if (global.itemCode.SpecialCell.size() >= selected_stamp) {
                    canvas.drawBitmap(global.itemCode.getSpecialCellNumber(selected_stamp).image,
                            global.blackSide.UnitConversion(1135, BlackSide.width),
                            global.blackSide.UnitConversion(255, BlackSide.height), null);
                } else {
                    break;
                }
                break;
            default:
                break;
        }

        Up_Button.Render(canvas);
        Down_Button.Render(canvas);
    }

    public void Render(Canvas canvas) {
        paint.setColor(Color.WHITE);
        paint.setTextSize(global.blackSide.FontUnitConversion(75));

        canvas.drawBitmap(global.bitmapResource.inventory_background,
                global.blackSide.UnitConversion(210, BlackSide.width),
                global.blackSide.UnitConversion(210, BlackSide.height), null);
        canvas.drawBitmap(global.bitmapResource.gauge_button,
                global.blackSide.UnitConversion(1500, BlackSide.width),
                global.blackSide.UnitConversion(20, BlackSide.height), null);

        LeftRender(canvas);
        RightRender(canvas);
        gaugeBar.Render(canvas);

        paint.setTextSize(global.blackSide.FontUnitConversion(50));
        canvas.drawText("" + global.inventory.GetSize() + " / " + global.inventory.MaxSize,
                global.blackSide.UnitConversion(1525, BlackSide.width),
                global.blackSide.UnitConversion(130, BlackSide.height), paint);

        paint.setTextSize(global.blackSide.FontUnitConversion(75));
        switch (selected_mark) {
            case ItemCell.RawMaterials:
                canvas.drawText("" + global.inventory.item.getRawMaterialsCellNumber(selected_stamp).number,
                        global.blackSide.UnitConversion(1600, BlackSide.width),
                        global.blackSide.UnitConversion(330, BlackSide.height),
                        paint);
                canvas.drawText("" + (global.inventory.item.getRawMaterialsCellNumber(selected_stamp).number * global.itemCode.RawMaterialsCell.get(selected_stamp).number),
                        global.blackSide.UnitConversion(1600, BlackSide.width),
                        global.blackSide.UnitConversion(460, BlackSide.height),
                        paint);
                canvas.drawText("" + throw_amount,
                        global.blackSide.UnitConversion(1470, BlackSide.width),
                        global.blackSide.UnitConversion(590, BlackSide.height),
                        paint);
                canvas.drawText("" + throw_amount * global.itemCode.RawMaterialsCell.get(selected_stamp).number,
                        global.blackSide.UnitConversion(1470, BlackSide.width),
                        global.blackSide.UnitConversion(720, BlackSide.height),
                        paint);
                break;
            case ItemCell.Material:
                canvas.drawText("" + global.inventory.item.getMaterialCellNumber(selected_stamp).number,
                        global.blackSide.UnitConversion(1600, BlackSide.width),
                        global.blackSide.UnitConversion(330, BlackSide.height),
                        paint);
                canvas.drawText("" + (global.inventory.item.getMaterialCellNumber(selected_stamp).number * global.itemCode.MaterialCell.get(selected_stamp).number),
                        global.blackSide.UnitConversion(1600, BlackSide.width),
                        global.blackSide.UnitConversion(460, BlackSide.height),
                        paint);
                canvas.drawText("" + throw_amount,
                        global.blackSide.UnitConversion(1470, BlackSide.width),
                        global.blackSide.UnitConversion(590, BlackSide.height),
                        paint);
                canvas.drawText("" + throw_amount * global.itemCode.MaterialCell.get(selected_stamp).number,
                        global.blackSide.UnitConversion(1470, BlackSide.width),
                        global.blackSide.UnitConversion(720, BlackSide.height),
                        paint);
                break;
            case ItemCell.Parts:
                canvas.drawText("" + global.inventory.item.getPartsCellNumber(selected_stamp).number,
                        global.blackSide.UnitConversion(1600, BlackSide.width),
                        global.blackSide.UnitConversion(330, BlackSide.height),
                        paint);
                canvas.drawText("" + (global.inventory.item.getPartsCellNumber(selected_stamp).number * global.itemCode.PartsCell.get(selected_stamp).number),
                        global.blackSide.UnitConversion(1600, BlackSide.width),
                        global.blackSide.UnitConversion(460, BlackSide.height),
                        paint);
                canvas.drawText("" + throw_amount,
                        global.blackSide.UnitConversion(1470, BlackSide.width),
                        global.blackSide.UnitConversion(590, BlackSide.height),
                        paint);
                canvas.drawText("" + throw_amount * global.itemCode.PartsCell.get(selected_stamp).number,
                        global.blackSide.UnitConversion(1470, BlackSide.width),
                        global.blackSide.UnitConversion(720, BlackSide.height),
                        paint);
                break;
            case ItemCell.Consumption:
                canvas.drawText("" + global.inventory.item.getConsumptionCellNumber(selected_stamp).number,
                        global.blackSide.UnitConversion(1600, BlackSide.width),
                        global.blackSide.UnitConversion(330, BlackSide.height),
                        paint);
                canvas.drawText("" + (global.inventory.item.getConsumptionCellNumber(selected_stamp).number * global.itemCode.ConsumptionCell.get(selected_stamp).number),
                        global.blackSide.UnitConversion(1600, BlackSide.width),
                        global.blackSide.UnitConversion(460, BlackSide.height),
                        paint);
                canvas.drawText("" + throw_amount,
                        global.blackSide.UnitConversion(1470, BlackSide.width),
                        global.blackSide.UnitConversion(590, BlackSide.height),
                        paint);
                canvas.drawText("" + throw_amount * global.itemCode.ConsumptionCell.get(selected_stamp).number,
                        global.blackSide.UnitConversion(1470, BlackSide.width),
                        global.blackSide.UnitConversion(720, BlackSide.height),
                        paint);
                break;
            case ItemCell.Special:
                canvas.drawText("" + global.inventory.item.getSpecialCellNumber(selected_stamp).number,
                        global.blackSide.UnitConversion(1600, BlackSide.width),
                        global.blackSide.UnitConversion(330, BlackSide.height),
                        paint);
                canvas.drawText("" + (global.inventory.item.getSpecialCellNumber(selected_stamp).number * global.itemCode.SpecialCell.get(selected_stamp).number),
                        global.blackSide.UnitConversion(1600, BlackSide.width),
                        global.blackSide.UnitConversion(460, BlackSide.height),
                        paint);
                canvas.drawText("" + throw_amount,
                        global.blackSide.UnitConversion(1470, BlackSide.width),
                        global.blackSide.UnitConversion(590, BlackSide.height),
                        paint);
                canvas.drawText("" + throw_amount * global.itemCode.SpecialCell.get(selected_stamp).number,
                        global.blackSide.UnitConversion(1470, BlackSide.width),
                        global.blackSide.UnitConversion(720, BlackSide.height),
                        paint);
                break;
            default:
                break;
        }

        if (calculator.calculator_running) {
            canvas.drawARGB(120, 0, 0, 0);
            calculator.Render(canvas);
        }

    }

    // UI 랜더
    public void UIRender(Canvas canvas) {

        if (!calculator.calculator_running) {
            backSpace.Render(canvas);
        }
    }
}