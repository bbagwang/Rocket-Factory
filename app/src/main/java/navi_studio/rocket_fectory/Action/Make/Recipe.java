package navi_studio.rocket_fectory.Action.Make;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import navi_studio.rocket_fectory.Button.AnimationButton;
import navi_studio.rocket_fectory.Function.Calculator;
import navi_studio.rocket_fectory.Function.SoundUnit;
import navi_studio.rocket_fectory.Function.Vector2;
import navi_studio.rocket_fectory.Global.BlackSide;
import navi_studio.rocket_fectory.Global.Global;
import navi_studio.rocket_fectory.R;

public class Recipe {

    private Global global;      //글로벌 전역
    private AnimationButton backSpace;              // 뒤로가기 버튼
    private Paint paint;

    Bitmap background;
    Bitmap[] img_Check_Button;
    Bitmap[] img_Check_Button_OK;

    AnimationButton Check_Button;
    AnimationButton Check_Button_OK;
    AnimationButton Calculator_Button;      // 계산기 버튼
    Calculator calculator;
    RecipeBook recipeBook;          //제작 도감인 레시피 북 선어
    MakeAction makeAction;

    public boolean recipe_running=false;
    public boolean calculator_result_return = true;    //값 받았음?

    //TODO : Pick Recipe Button
    SoundUnit Sound_Check_Button;
    SoundUnit Sound_Back_Button;
    SoundUnit Sound_Calculator_Button;

    public void Create(Global _global,RecipeBook _recipeBook,MakeAction _makeAction,Calculator _calculator) {

        global = _global;
        makeAction=_makeAction;
        paint = new Paint();
        Check_Button=new AnimationButton();
        Check_Button_OK=new AnimationButton();
        Calculator_Button=new AnimationButton();
        calculator=_calculator;
        backSpace = new AnimationButton();
        recipeBook=_recipeBook;
        background = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.materialinformation);
        background = Bitmap.createScaledBitmap(background, global.blackSide.UnitConversion(1420), global.blackSide.UnitConversion(860), true);

        Sound_Check_Button=new SoundUnit();
        Sound_Back_Button=new SoundUnit();
        Sound_Calculator_Button=new SoundUnit();
        //TODO : ADD SOUNDS
        Sound_Check_Button.Create(SoundUnit.music,R.raw.recipe_check_button,1,global.context);
        Sound_Back_Button.Create(SoundUnit.music,R.raw.back_button,1,global.context);
        Sound_Calculator_Button.Create(SoundUnit.music,R.raw.calculator_button,1,global.context);

        img_Check_Button=new Bitmap[5];
        img_Check_Button[0]=BitmapFactory.decodeResource(global.context.getResources(),R.drawable.materialinformationbutton1);
        img_Check_Button[0] = Bitmap.createScaledBitmap(img_Check_Button[0], global.blackSide.UnitConversion(320), global.blackSide.UnitConversion(180), true);
        img_Check_Button[1]=BitmapFactory.decodeResource(global.context.getResources(),R.drawable.materialinformationbutton2);
        img_Check_Button[1] = Bitmap.createScaledBitmap(img_Check_Button[1], global.blackSide.UnitConversion(320), global.blackSide.UnitConversion(180), true);
        img_Check_Button[2]=BitmapFactory.decodeResource(global.context.getResources(),R.drawable.materialinformationbutton3);
        img_Check_Button[2] = Bitmap.createScaledBitmap(img_Check_Button[2], global.blackSide.UnitConversion(320), global.blackSide.UnitConversion(180), true);
        img_Check_Button[3]=BitmapFactory.decodeResource(global.context.getResources(),R.drawable.materialinformationbutton4);
        img_Check_Button[3] = Bitmap.createScaledBitmap(img_Check_Button[3], global.blackSide.UnitConversion(320), global.blackSide.UnitConversion(180), true);
        img_Check_Button[4]= img_Check_Button[0];

        img_Check_Button_OK=new Bitmap[5];
        img_Check_Button_OK[0]=BitmapFactory.decodeResource(global.context.getResources(),R.drawable.materialinformatioffbutton1);
        img_Check_Button_OK[0] = Bitmap.createScaledBitmap(img_Check_Button_OK[0], global.blackSide.UnitConversion(320), global.blackSide.UnitConversion(180), true);
        img_Check_Button_OK[1]=BitmapFactory.decodeResource(global.context.getResources(),R.drawable.materialinformatioffbutton2);
        img_Check_Button_OK[1] = Bitmap.createScaledBitmap(img_Check_Button_OK[1], global.blackSide.UnitConversion(320), global.blackSide.UnitConversion(180), true);
        img_Check_Button_OK[2]=BitmapFactory.decodeResource(global.context.getResources(),R.drawable.materialinformatioffbutton3);
        img_Check_Button_OK[2] = Bitmap.createScaledBitmap(img_Check_Button_OK[2], global.blackSide.UnitConversion(320), global.blackSide.UnitConversion(180), true);
        img_Check_Button_OK[3]=BitmapFactory.decodeResource(global.context.getResources(),R.drawable.materialinformatioffbutton4);
        img_Check_Button_OK[3] = Bitmap.createScaledBitmap(img_Check_Button_OK[3], global.blackSide.UnitConversion(320), global.blackSide.UnitConversion(180), true);
        img_Check_Button_OK[4]= img_Check_Button[0];

        backSpace.Create(AnimationButton.BASIC,
                new Vector2(global.blackSide.UnitConversion(10, BlackSide.width),
                        global.blackSide.UnitConversion(10, BlackSide.height)),
                AnimationButton.RECTANGLE, global.bitmapResource.ActionBackSpaceImage,
                3, 3, 5, 10, global);

        Check_Button.Create(AnimationButton.BASIC,
                new Vector2(global.blackSide.UnitConversion(795, BlackSide.width),
                        global.blackSide.UnitConversion(700, BlackSide.height)),
                AnimationButton.RECTANGLE, img_Check_Button,
                1, 1, 5, 10, global);

        Check_Button_OK.Create(AnimationButton.BASIC,
                new Vector2(global.blackSide.UnitConversion(795, BlackSide.width),
                        global.blackSide.UnitConversion(700, BlackSide.height)),
                AnimationButton.RECTANGLE, img_Check_Button_OK,
                1, 1, 5, 10, global);

        Calculator_Button.Create(AnimationButton.BASIC,
                new Vector2(global.blackSide.UnitConversion(840, BlackSide.width),
                        global.blackSide.UnitConversion(420, BlackSide.height)),
                AnimationButton.RECTANGLE, global.bitmapResource.inventory_calculator,
                1, 1, 3, 10, global);

    }

    public boolean Destroy() {
        /*Sound_Check_Button.stop();
        Sound_Back_Button.stop();
        Sound_Calculator_Button.stop();*/
        recipe_running = false;
        return true;
    }

    // 주 실행
    public boolean Update() {

        backSpace.Update();
        if (calculator.calculator_running) {
            calculator.Update();
            return false;
        }

        if (calculator_result_return) {
            makeAction.make_amount = calculator.getResult();
            calculator.SetResult(makeAction.make_amount);
            Required_Quantity_Updater();
            calculator_result_return = false;
        }

        if(Calculator_Button.isClick()){
            Sound_Calculator_Button.play(100.0f);
            calculator.calculator_running=true;
            calculator_result_return = true;
        }

        Calculator_Button.Update();
        if(makeAction.make_sign)
            Check_Button_OK.Update();
        else
            Check_Button.Update();

       if(backSpace.isClick()){
           Sound_Back_Button.play(100.0f);
           return Destroy();
       }

        Update_Required_Amount();

       if(Check_Button.isClick()||Check_Button_OK.isClick()){
           Sound_Check_Button.play(100.0f);
           if(makeAction.make_sign){
               for(int i=0;i<recipeBook.index_size;i++)
                global.inventory.subItem(recipeBook.item_index[i],recipeBook.req_amount[i]*makeAction.make_amount);
               global.inventory.addItem(recipeBook.make_item_code,recipeBook.make_amount*makeAction.make_amount);
               return Destroy();
           }
           else{
               return Destroy();
           }
       }
        return false;
    }

    void Items_Render(Canvas canvas){

        //Item Side (Left)
        for(int y=0;y<3;y++) {
            for (int x = 0; x < 2; x++) {
                if(y*2+x<recipeBook.index_size) {
                    canvas.drawBitmap(recipeBook.item_image[y*2+x],
                            global.blackSide.UnitConversion(300 + (240 * x), BlackSide.width),
                            global.blackSide.UnitConversion(130 + (240 * y), BlackSide.height), null);
                }else
                    break;
            }
        }
    }

    void Required_Quantity_Updater(){
        Update_Required_Amount();
    }

    void Required_Quantity_Render(Canvas canvas){
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(global.blackSide.FontUnitConversion(150));
        paint.setARGB(255,255,255,255);
        //Quantity Side (Right)
        for(int y=0;y<3;y++){
            for(int x=0;x<2;x++){
                if(y*2+x<recipeBook.index_size){
                    canvas.drawText(""+recipeBook.req_amount[y*2+x],
                            global.blackSide.UnitConversion(1250+(240*x), BlackSide.width),
                            global.blackSide.UnitConversion(300+(240*y), BlackSide.height), paint);
                }
            }
        }
    }


    public void Render(Canvas canvas) {
        backSpace.Render(canvas);
        paint.setColor(Color.WHITE);
        paint.setTextSize(global.blackSide.FontUnitConversion(100));
        paint.setTextAlign(Paint.Align.CENTER);
        canvas.drawBitmap(background,
                global.blackSide.UnitConversion(250, BlackSide.width),
                global.blackSide.UnitConversion(90, BlackSide.height), null);

        canvas.drawText(""+makeAction.make_amount,
                global.blackSide.UnitConversion(950, BlackSide.width),
                global.blackSide.UnitConversion(330, BlackSide.height), paint);
        Items_Render(canvas);
        Required_Quantity_Render(canvas);

        Check_Button_Updater(makeAction.make_sign,canvas);

        Calculator_Button.Render(canvas);

        if (calculator.calculator_running) {
            canvas.drawARGB(120, 0, 0, 0);
            calculator.Render(canvas);
        }
    }

    // UI 랜더
    public void UIRender(Canvas canvas) {

    }

    public void Update_Required_Amount(){
        for (int i = 0; i < recipeBook.index_size; i++) {
            recipeBook.req_amount[i] = recipeBook.original_req_amount[i]*makeAction.make_amount;
        }
    }

    public void Check_Button_Updater(boolean _make_sign,Canvas canvas){
        if(_make_sign)
            Check_Button_OK.Render(canvas);
        else
            Check_Button.Render(canvas);
    }

}
