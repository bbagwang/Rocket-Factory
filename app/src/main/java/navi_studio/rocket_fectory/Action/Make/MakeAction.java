package navi_studio.rocket_fectory.Action.Make;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

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

public class MakeAction extends ObjLow {

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

    Bitmap[] is_makeable;
    Bitmap[] img_make_button;
    private AnimationButton Make_Button;

    private Recipe recipe;      //제작 레시피
    private RecipeBook recipeBook;  //제작 레시피 북

    public boolean make_sign = false;
    public int make_amount = 1;//만들 개수

    final static int MAKE_MATERIAL = 0;
    final static int MAKE_PARTS = 1;
    final static int MAKE_CONSUMPTION = 2;
    final static int MAKE_SPECIAL = 3;

    SoundUnit Sound_Check_Button;
    SoundUnit Sound_Calculator_Button;
    SoundUnit Sound_Stamp_Button;
    SoundUnit Sound_Mark_Button;
    SoundUnit Sound_Up_Button;
    SoundUnit Sound_Down_Button;
    SoundUnit Sound_Back_Button;
    SoundUnit Sound_Make_Button;

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

        calculator = new Calculator();
        calculator.Create(global);

        Make_Button = new AnimationButton();
        recipeBook = new RecipeBook();
        recipeBook.Create(global, this);
        recipe = new Recipe();
        recipe.Create(global, recipeBook, this,calculator);

        Sound_Check_Button=new SoundUnit();
        Sound_Calculator_Button=new SoundUnit();
        Sound_Stamp_Button=new SoundUnit();
        Sound_Mark_Button=new SoundUnit();
        Sound_Up_Button=new SoundUnit();
        Sound_Down_Button=new SoundUnit();
        Sound_Back_Button=new SoundUnit();
        Sound_Make_Button=new SoundUnit();

        Sound_Check_Button.Create(SoundUnit.music,R.raw.check_button,1,global.context);
        Sound_Calculator_Button.Create(SoundUnit.music,R.raw.calculator_button,1,global.context);
        Sound_Stamp_Button.Create(SoundUnit.music,R.raw.stamp_mark,1,global.context);
        Sound_Mark_Button.Create(SoundUnit.music,R.raw.stamp_mark,1,global.context);
        Sound_Up_Button.Create(SoundUnit.music,R.raw.up_button,1,global.context);
        Sound_Down_Button.Create(SoundUnit.music,R.raw.down_button,1,global.context);
        Sound_Back_Button.Create(SoundUnit.music,R.raw.back_button,1,global.context);
        Sound_Make_Button.Create(SoundUnit.music,R.raw.make_button,1,global.context);

        is_makeable = new Bitmap[2];
        is_makeable[0] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.makecheckdisplay0);
        is_makeable[0] = Bitmap.createScaledBitmap(is_makeable[0], global.blackSide.UnitConversion(450), global.blackSide.UnitConversion(120), true);
        is_makeable[1] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.makecheckdisplay1);
        is_makeable[1] = Bitmap.createScaledBitmap(is_makeable[1], global.blackSide.UnitConversion(450), global.blackSide.UnitConversion(120), true);

        img_make_button = new Bitmap[4];
        img_make_button[0] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.makematarialbutton1);
        img_make_button[0] = Bitmap.createScaledBitmap(img_make_button[0], global.blackSide.UnitConversion(450), global.blackSide.UnitConversion(120), true);
        img_make_button[1] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.makematarialbutton2);
        img_make_button[1] = Bitmap.createScaledBitmap(img_make_button[1], global.blackSide.UnitConversion(450), global.blackSide.UnitConversion(120), true);
        img_make_button[2] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.makematarialbutton3);
        img_make_button[2] = Bitmap.createScaledBitmap(img_make_button[2], global.blackSide.UnitConversion(450), global.blackSide.UnitConversion(120), true);
        img_make_button[3] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.makematarialbutton4);
        img_make_button[3] = Bitmap.createScaledBitmap(img_make_button[3], global.blackSide.UnitConversion(450), global.blackSide.UnitConversion(120), true);

        Make_Button.Create(AnimationButton.BASIC,
                new Vector2(global.blackSide.UnitConversion(1390, BlackSide.width),
                        global.blackSide.UnitConversion(245, BlackSide.height)),
                AnimationButton.RECTANGLE, img_make_button,
                1, 1, 4, 10, global);


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
        touchPadMark = new NullButton[4];
        for (int i = 0; i < 4; i++)
            touchPadMark[i] = new NullButton();
        for (int i = 0; i < 4; i++)
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

    }

    public boolean Destroy() {

        return true;
    }

    // Button Update
    private void ButtonUpdate() {

        backSpace.Update();
        Check_Button.Update();
        scrollBar.Update();
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
        Make_Button.Update();
        MakeSignUpdater(is_Makeable());
    }

    // 주 실행
    public boolean Update() {

        MakeSignUpdater(is_Makeable());
        switch (selected_mark) {
            case MAKE_MATERIAL:
                recipeBook.SelectRecipe(recipeBook.Material_Make.get(selected_stamp).code);
                MakeSignUpdater(is_Makeable());
                break;
            case MAKE_PARTS:
                recipeBook.SelectRecipe(recipeBook.Parts_Make.get(selected_stamp).code);
                MakeSignUpdater(is_Makeable());
                break;
            case MAKE_CONSUMPTION:
                recipeBook.SelectRecipe(recipeBook.Consumption_Make.get(selected_stamp).code);
                MakeSignUpdater(is_Makeable());
                break;
            case MAKE_SPECIAL:
                recipeBook.SelectRecipe(recipeBook.Special_Make.get(selected_stamp).code);
                MakeSignUpdater(is_Makeable());
                break;
        }


        if (calculator.calculator_running) {
            calculator.Update();
            return false;
        }

        if (recipe.recipe_running) {
            Recipe_Calculator_Updater();
            MakeSignUpdater(is_Makeable());
            recipe.Update();

            make_amount=calculator.getResult();
            return false;
        }

        LeftUpdater();
        RightUpdater();

        gaugeBar.clear();
        gaugeBar.setValue(global.inventory.GetSize());
        if (calculator_result_return) {

            make_amount = calculator.getResult();
            calculator_result_return = false;
        }

        ButtonUpdate();
        if (backSpace.isClick()) {
            Sound_Back_Button.play(100.0f);
            selected_mark = 0;      //선택된 카테고리
            selected_stamp = 0;       //선택된 물건
            return Destroy();
        } else if (Check_Button.isClick()) {
            Sound_Check_Button.play(100.0f);
            if(make_sign){
                for(int i=0;i<recipeBook.index_size;i++)
                    global.inventory.subItem(recipeBook.item_index[i],recipeBook.req_amount[i]*make_amount);
                global.inventory.addItem(recipeBook.make_item_code,recipeBook.make_amount*make_amount);
                make_amount = 1;
            }
            else{
                return Destroy();
            }

        } else if (Calculator_Button.isClick()) {
            Sound_Calculator_Button.play(100.0f);
            calculator.calculator_running = true;
            calculator_result_return = true;
        } else if (Make_Button.isClick()) {
            Sound_Make_Button.play(100.0f);
            recipe.recipe_running = true;
        }
        return false;
    }

    private void MarkUpdater() {

        for (int i = 0; i < 4; i++) {
            touchPadMark[i].Update();
            if (touchPadMark[i].isClick()) {
                Sound_Mark_Button.play(100.0f);
                selected_mark = i;
                selected_stamp = 0;
                make_amount = 1;
            }
        }
    }

    private void MarkRender(Canvas canvas) {

        for (int i = 0; i < 4; i++) {
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
                    make_amount = 1;
                    switch (selected_mark) {
                        case MAKE_MATERIAL:
                            if (recipeBook.Material_Make.size() > y * 3 + x)
                                selected_stamp = y * 3 + x;
                            else
                                selected_stamp = 0;
                            break;
                        case MAKE_PARTS:
                            if (recipeBook.Parts_Make.size() > y * 3 + x)
                                selected_stamp = y * 3 + x;
                            else
                                selected_stamp = 0;
                            break;
                        case MAKE_CONSUMPTION:
                            if (recipeBook.Consumption_Make.size() > y * 3 + x)
                                selected_stamp = y * 3 + x;
                            else
                                selected_stamp = 0;
                            break;
                        case MAKE_SPECIAL:
                            if (recipeBook.Special_Make.size() > y * 3 + x)
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
                    case MAKE_MATERIAL: {
                        if (y * 3 + x < recipeBook.Material_Make.size()) {
                            canvas.drawBitmap(recipeBook.Material_Make.get(y * 3 + x).image,
                                    global.blackSide.UnitConversion((235 + (x * 245) + (x * 10)), BlackSide.width),
                                    global.blackSide.UnitConversion((235 + (y * 245) + (y * 10)), BlackSide.height), null);
                            break;
                        }
                        break;
                    }
                    case MAKE_PARTS: {
                        if (y * 3 + x < recipeBook.Parts_Make.size()) {
                            canvas.drawBitmap(recipeBook.Parts_Make.get(y * 3 + x).image,
                                    global.blackSide.UnitConversion((235 + (x * 245) + (x * 10)), BlackSide.width),
                                    global.blackSide.UnitConversion((235 + (y * 245) + (y * 10)), BlackSide.height), null);
                            break;
                        }
                        break;
                    }
                    case MAKE_CONSUMPTION: {
                        if (y * 3 + x < recipeBook.Consumption_Make.size()) {
                            canvas.drawBitmap(recipeBook.Consumption_Make.get(y * 3 + x).image,
                                    global.blackSide.UnitConversion((250 + (x * 245) + (x * 10)), BlackSide.width),
                                    global.blackSide.UnitConversion((250 + (y * 245) + (y * 10)), BlackSide.height), null);
                            break;
                        }
                        break;
                    }
                    case MAKE_SPECIAL: {
                        if (y * 3 + x < recipeBook.Special_Make.size()) {
                            canvas.drawBitmap(recipeBook.Special_Make.get(y * 3 + x).image,
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
        Make_Button.Render(canvas);
        Check_Button.Render(canvas);
        canvas.drawBitmap(global.bitmapResource.inventory_stamp[0],
                global.blackSide.UnitConversion(1135, BlackSide.width),
                global.blackSide.UnitConversion(250, BlackSide.height), null);

        //TODO : 레시피 사인
        if (make_sign) {
            canvas.drawBitmap(is_makeable[1],
                    global.blackSide.UnitConversion(1390, BlackSide.width),
                    global.blackSide.UnitConversion(375, BlackSide.height), null);
        } else {
            canvas.drawBitmap(is_makeable[0],
                    global.blackSide.UnitConversion(1390, BlackSide.width),
                    global.blackSide.UnitConversion(375, BlackSide.height), null);
        }

        if (Up_Button.isClick()) {
            Sound_Up_Button.play(100.0f);
            canvas.drawBitmap(global.bitmapResource.inventory_up_button[1],
                    global.blackSide.UnitConversion(1715, BlackSide.width),
                    global.blackSide.UnitConversion(510, BlackSide.height), null);

            make_amount++;
            calculator.SetResult(make_amount);
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

            if (make_amount > 1) {
                make_amount--;
                calculator.SetResult(make_amount);
            }

        } else {
            canvas.drawBitmap(global.bitmapResource.inventory_down_button[0],
                    global.blackSide.UnitConversion(1135, BlackSide.width),
                    global.blackSide.UnitConversion(510, BlackSide.height), null);
        }

        switch (selected_mark) {
            case MAKE_MATERIAL:
                if (recipeBook.Material_Make.size() >= selected_stamp) {
                    canvas.drawBitmap(recipeBook.Material_Make.get(selected_stamp).image,
                            global.blackSide.UnitConversion(1135, BlackSide.width),
                            global.blackSide.UnitConversion(255, BlackSide.height), null);
                } else {
                    break;
                }

                break;
            case MAKE_PARTS:
                if (recipeBook.Parts_Make.size() >= selected_stamp) {
                    canvas.drawBitmap(recipeBook.Parts_Make.get(selected_stamp).image,
                            global.blackSide.UnitConversion(1135, BlackSide.width),
                            global.blackSide.UnitConversion(255, BlackSide.height), null);
                } else {
                    break;
                }

                break;
            case MAKE_CONSUMPTION:
                if (recipeBook.Consumption_Make.size() >= selected_stamp) {
                    canvas.drawBitmap(recipeBook.Consumption_Make.get(selected_stamp).image,
                            global.blackSide.UnitConversion(1135, BlackSide.width),
                            global.blackSide.UnitConversion(255, BlackSide.height), null);
                } else {
                    break;
                }
                break;
            case MAKE_SPECIAL:
                if (recipeBook.Special_Make.size() >= selected_stamp) {
                    canvas.drawBitmap(recipeBook.Special_Make.get(selected_stamp).image,
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
        paint.setTextAlign(Paint.Align.CENTER);

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
                global.blackSide.UnitConversion(1650, BlackSide.width),
                global.blackSide.UnitConversion(130, BlackSide.height), paint);


        paint.setTextSize(global.blackSide.FontUnitConversion(70));
        switch (selected_mark) {
            case MAKE_MATERIAL:
                canvas.drawText("" + make_amount,
                        global.blackSide.UnitConversion(1470, BlackSide.width),
                        global.blackSide.UnitConversion(590, BlackSide.height),
                        paint);
                canvas.drawText("보유량 : "+global.inventory.item.get(recipeBook.Material_Make.get(selected_stamp).code).number,
                        global.blackSide.UnitConversion(1470, BlackSide.width),
                        global.blackSide.UnitConversion(720, BlackSide.height),
                        paint);
                break;
            case MAKE_PARTS:
                canvas.drawText("" + make_amount,
                        global.blackSide.UnitConversion(1470, BlackSide.width),
                        global.blackSide.UnitConversion(590, BlackSide.height),
                        paint);
                canvas.drawText("보유량 : "+global.inventory.item.get(recipeBook.Parts_Make.get(selected_stamp).code).number,
                        global.blackSide.UnitConversion(1470, BlackSide.width),
                        global.blackSide.UnitConversion(720, BlackSide.height),
                        paint);
                break;
            case MAKE_CONSUMPTION:
                canvas.drawText("" + make_amount,
                        global.blackSide.UnitConversion(1470, BlackSide.width),
                        global.blackSide.UnitConversion(590, BlackSide.height),
                        paint);
                canvas.drawText("보유량 : "+global.inventory.item.get(recipeBook.Consumption_Make.get(selected_stamp).code).number,
                        global.blackSide.UnitConversion(1470, BlackSide.width),
                        global.blackSide.UnitConversion(720, BlackSide.height),
                        paint);
                break;
            case MAKE_SPECIAL:
                canvas.drawText("" + make_amount,
                        global.blackSide.UnitConversion(1470, BlackSide.width),
                        global.blackSide.UnitConversion(590, BlackSide.height),
                        paint);
                canvas.drawText("보유량 : "+global.inventory.item.get(recipeBook.Special_Make.get(selected_stamp).code).number,
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

        if (recipe.recipe_running) {
            canvas.drawARGB(220, 0, 0, 0);
            recipe.Render(canvas);
        }

    }

    // UI 랜더
    public void UIRender(Canvas canvas) {

        if (!calculator.calculator_running && !recipe.recipe_running)
            backSpace.Render(canvas);
    }

    public void MakeSignUpdater(boolean _is_makeable) {
        if (_is_makeable)
            make_sign = true;
        else
            make_sign = false;
    }

    public boolean is_Makeable() {
        for (int i = 0; i < recipeBook.index_size; i++) {
            if (recipeBook.req_amount[i] * make_amount <= global.inventory.item.get(recipeBook.item_index[i]).number) {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }

    public void Recipe_Calculator_Updater(){
        make_amount=recipe.calculator.getResult();
    }
}