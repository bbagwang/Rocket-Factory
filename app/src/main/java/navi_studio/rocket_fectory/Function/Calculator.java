package navi_studio.rocket_fectory.Function;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import navi_studio.rocket_fectory.Action.InventoryAction;
import navi_studio.rocket_fectory.Button.AnimationButton;
import navi_studio.rocket_fectory.Function.Vector2;
import navi_studio.rocket_fectory.Global.BlackSide;
import navi_studio.rocket_fectory.Global.Global;
import navi_studio.rocket_fectory.R;

public class Calculator {

    private Global global;      //글로벌 전역
    private AnimationButton backSpace;              // 뒤로가기 버튼
    private Paint paint;

    Bitmap background;
    Bitmap[][] img_btn;
    Bitmap[] img_btn_back;
    Bitmap[] img_btn_clear;
    Bitmap[] img_btn_cancel;
    Bitmap[] img_btn_check;
    Bitmap[] img_btn_add;
    Bitmap[] img_btn_sub;
    Bitmap[] img_btn_multi;
    Bitmap[] img_btn_sum;
    Bitmap[] img_btn_temp;

    private AnimationButton[] button_num;         //
    private AnimationButton button_back;      // 뒤로가기
    private AnimationButton button_clear;     // 지우기
    private AnimationButton button_cancel;    // 취소
    private AnimationButton button_check;     // 체크
    private AnimationButton button_add;       // 더하기
    private AnimationButton button_sub;       // 빼기
    private AnimationButton button_multi;     // 곱하기
    private AnimationButton button_sum;       // 결과
    private AnimationButton button_temp;      // 임시

    String expression;
    public int result = 1;

    boolean calculated = false;

    char[] operators;
    int[] operands;
    String temp = "";
    int count = 0;
    int operator_amount_count = 0;
    public boolean calculator_running = false;

    SoundUnit Sound_Buttons;

    public void Create(Global _global) {

        global = _global;
        paint = new Paint();

        backSpace = new AnimationButton();

        button_num = new AnimationButton[10];

        for (int i = 0; i < 10; i++)
            button_num[i] = new AnimationButton();

        button_back = new AnimationButton();
        button_clear = new AnimationButton();
        button_cancel = new AnimationButton();
        button_check = new AnimationButton();
        button_add = new AnimationButton();
        button_sub = new AnimationButton();
        button_multi = new AnimationButton();
        button_sum = new AnimationButton();
        button_temp = new AnimationButton();


        img_btn = new Bitmap[10][];

        for (int i = 0; i < 10; i++)
            img_btn[i] = new Bitmap[4];

        img_btn_back = new Bitmap[4];
        img_btn_clear = new Bitmap[4];
        img_btn_cancel = new Bitmap[4];
        img_btn_check = new Bitmap[4];
        img_btn_add = new Bitmap[4];
        img_btn_sub = new Bitmap[4];
        img_btn_multi = new Bitmap[4];
        img_btn_sum = new Bitmap[4];
        img_btn_temp = new Bitmap[4];

        expression = new String();

        Sound_Buttons=new SoundUnit();
        Sound_Buttons.Create(SoundUnit.music,R.raw.calculator_button_push,1,global.context);

        background = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.calculatorbackground);
        background = Bitmap.createScaledBitmap(background, global.blackSide.UnitConversion(1180), global.blackSide.UnitConversion(1020), true);

        img_btn[0][0] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.calculator0button1);
        img_btn[0][0] = Bitmap.createScaledBitmap(img_btn[0][0], global.blackSide.UnitConversion(190), global.blackSide.UnitConversion(190), true);
        img_btn[0][1] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.calculator0button2);
        img_btn[0][1] = Bitmap.createScaledBitmap(img_btn[0][1], global.blackSide.UnitConversion(190), global.blackSide.UnitConversion(190), true);
        img_btn[0][2] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.calculator0button3);
        img_btn[0][2] = Bitmap.createScaledBitmap(img_btn[0][2], global.blackSide.UnitConversion(190), global.blackSide.UnitConversion(190), true);

        img_btn[1][0] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.calculator1button1);
        img_btn[1][0] = Bitmap.createScaledBitmap(img_btn[1][0], global.blackSide.UnitConversion(190), global.blackSide.UnitConversion(190), true);
        img_btn[1][1] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.calculator1button2);
        img_btn[1][1] = Bitmap.createScaledBitmap(img_btn[1][1], global.blackSide.UnitConversion(190), global.blackSide.UnitConversion(190), true);
        img_btn[1][2] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.calculator1button3);
        img_btn[1][2] = Bitmap.createScaledBitmap(img_btn[1][2], global.blackSide.UnitConversion(190), global.blackSide.UnitConversion(190), true);

        img_btn[2][0] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.calculator2button1);
        img_btn[2][0] = Bitmap.createScaledBitmap(img_btn[2][0], global.blackSide.UnitConversion(190), global.blackSide.UnitConversion(190), true);
        img_btn[2][1] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.calculator2button2);
        img_btn[2][1] = Bitmap.createScaledBitmap(img_btn[2][1], global.blackSide.UnitConversion(190), global.blackSide.UnitConversion(190), true);
        img_btn[2][2] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.calculator2button3);
        img_btn[2][2] = Bitmap.createScaledBitmap(img_btn[2][2], global.blackSide.UnitConversion(190), global.blackSide.UnitConversion(190), true);

        img_btn[3][0] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.calculator3button1);
        img_btn[3][0] = Bitmap.createScaledBitmap(img_btn[3][0], global.blackSide.UnitConversion(190), global.blackSide.UnitConversion(190), true);
        img_btn[3][1] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.calculator3button2);
        img_btn[3][1] = Bitmap.createScaledBitmap(img_btn[3][1], global.blackSide.UnitConversion(190), global.blackSide.UnitConversion(190), true);
        img_btn[3][2] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.calculator3button3);
        img_btn[3][2] = Bitmap.createScaledBitmap(img_btn[3][2], global.blackSide.UnitConversion(190), global.blackSide.UnitConversion(190), true);

        img_btn[4][0] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.calculator4button1);
        img_btn[4][0] = Bitmap.createScaledBitmap(img_btn[4][0], global.blackSide.UnitConversion(190), global.blackSide.UnitConversion(190), true);
        img_btn[4][1] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.calculator4button2);
        img_btn[4][1] = Bitmap.createScaledBitmap(img_btn[4][1], global.blackSide.UnitConversion(190), global.blackSide.UnitConversion(190), true);
        img_btn[4][2] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.calculator4button3);
        img_btn[4][2] = Bitmap.createScaledBitmap(img_btn[4][2], global.blackSide.UnitConversion(190), global.blackSide.UnitConversion(190), true);

        img_btn[5][0] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.calculator5button1);
        img_btn[5][0] = Bitmap.createScaledBitmap(img_btn[5][0], global.blackSide.UnitConversion(190), global.blackSide.UnitConversion(190), true);
        img_btn[5][1] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.calculator5button2);
        img_btn[5][1] = Bitmap.createScaledBitmap(img_btn[5][1], global.blackSide.UnitConversion(190), global.blackSide.UnitConversion(190), true);
        img_btn[5][2] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.calculator5button3);
        img_btn[5][2] = Bitmap.createScaledBitmap(img_btn[5][2], global.blackSide.UnitConversion(190), global.blackSide.UnitConversion(190), true);

        img_btn[6][0] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.calculator6button1);
        img_btn[6][0] = Bitmap.createScaledBitmap(img_btn[6][0], global.blackSide.UnitConversion(190), global.blackSide.UnitConversion(190), true);
        img_btn[6][1] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.calculator6button2);
        img_btn[6][1] = Bitmap.createScaledBitmap(img_btn[6][1], global.blackSide.UnitConversion(190), global.blackSide.UnitConversion(190), true);
        img_btn[6][2] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.calculator6button3);
        img_btn[6][2] = Bitmap.createScaledBitmap(img_btn[6][2], global.blackSide.UnitConversion(190), global.blackSide.UnitConversion(190), true);

        img_btn[7][0] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.calculator7button1);
        img_btn[7][0] = Bitmap.createScaledBitmap(img_btn[7][0], global.blackSide.UnitConversion(190), global.blackSide.UnitConversion(190), true);
        img_btn[7][1] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.calculator7button2);
        img_btn[7][1] = Bitmap.createScaledBitmap(img_btn[7][1], global.blackSide.UnitConversion(190), global.blackSide.UnitConversion(190), true);
        img_btn[7][2] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.calculator7button3);
        img_btn[7][2] = Bitmap.createScaledBitmap(img_btn[7][2], global.blackSide.UnitConversion(190), global.blackSide.UnitConversion(190), true);

        img_btn[8][0] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.calculator8button1);
        img_btn[8][0] = Bitmap.createScaledBitmap(img_btn[8][0], global.blackSide.UnitConversion(190), global.blackSide.UnitConversion(190), true);
        img_btn[8][1] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.calculator8button2);
        img_btn[8][1] = Bitmap.createScaledBitmap(img_btn[8][1], global.blackSide.UnitConversion(190), global.blackSide.UnitConversion(190), true);
        img_btn[8][2] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.calculator8button3);
        img_btn[8][2] = Bitmap.createScaledBitmap(img_btn[8][2], global.blackSide.UnitConversion(190), global.blackSide.UnitConversion(190), true);

        img_btn[9][0] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.calculator9button1);
        img_btn[9][0] = Bitmap.createScaledBitmap(img_btn[9][0], global.blackSide.UnitConversion(190), global.blackSide.UnitConversion(190), true);
        img_btn[9][1] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.calculator9button2);
        img_btn[9][1] = Bitmap.createScaledBitmap(img_btn[9][1], global.blackSide.UnitConversion(190), global.blackSide.UnitConversion(190), true);
        img_btn[9][2] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.calculator9button3);
        img_btn[9][2] = Bitmap.createScaledBitmap(img_btn[9][2], global.blackSide.UnitConversion(190), global.blackSide.UnitConversion(190), true);

        for (int i = 0; i < 10; i++)
            img_btn[i][3] = img_btn[i][0];

        img_btn_back[0] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.calculatorbackbutton1);
        img_btn_back[0] = Bitmap.createScaledBitmap(img_btn_back[0], global.blackSide.UnitConversion(190), global.blackSide.UnitConversion(190), true);
        img_btn_back[1] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.calculatorbackbutton2);
        img_btn_back[1] = Bitmap.createScaledBitmap(img_btn_back[1], global.blackSide.UnitConversion(190), global.blackSide.UnitConversion(190), true);
        img_btn_back[2] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.calculatorbackbutton3);
        img_btn_back[2] = Bitmap.createScaledBitmap(img_btn_back[2], global.blackSide.UnitConversion(190), global.blackSide.UnitConversion(190), true);

        img_btn_back[3] = img_btn_back[0];

        img_btn_clear[0] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.calculatorclearbutton1);
        img_btn_clear[0] = Bitmap.createScaledBitmap(img_btn_clear[0], global.blackSide.UnitConversion(190), global.blackSide.UnitConversion(190), true);
        img_btn_clear[1] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.calculatorclearbutton2);
        img_btn_clear[1] = Bitmap.createScaledBitmap(img_btn_clear[1], global.blackSide.UnitConversion(190), global.blackSide.UnitConversion(190), true);
        img_btn_clear[2] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.calculatorclearbutton3);
        img_btn_clear[2] = Bitmap.createScaledBitmap(img_btn_clear[2], global.blackSide.UnitConversion(190), global.blackSide.UnitConversion(190), true);

        img_btn_clear[3] = img_btn_clear[0];

        img_btn_cancel[0] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.calclatorcanclebutton1);
        img_btn_cancel[0] = Bitmap.createScaledBitmap(img_btn_cancel[0], global.blackSide.UnitConversion(240), global.blackSide.UnitConversion(190), true);
        img_btn_cancel[1] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.calclatorcanclebutton2);
        img_btn_cancel[1] = Bitmap.createScaledBitmap(img_btn_cancel[1], global.blackSide.UnitConversion(240), global.blackSide.UnitConversion(190), true);
        img_btn_cancel[2] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.calclatorcanclebutton3);
        img_btn_cancel[2] = Bitmap.createScaledBitmap(img_btn_cancel[2], global.blackSide.UnitConversion(240), global.blackSide.UnitConversion(190), true);

        img_btn_cancel[3] = img_btn_cancel[0];

        img_btn_check[0] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.calclatorcheckbutton1);
        img_btn_check[0] = Bitmap.createScaledBitmap(img_btn_check[0], global.blackSide.UnitConversion(240), global.blackSide.UnitConversion(190), true);
        img_btn_check[1] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.calclatorcheckbutton2);
        img_btn_check[1] = Bitmap.createScaledBitmap(img_btn_check[1], global.blackSide.UnitConversion(240), global.blackSide.UnitConversion(190), true);
        img_btn_check[2] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.calclatorcheckbutton3);
        img_btn_check[2] = Bitmap.createScaledBitmap(img_btn_check[2], global.blackSide.UnitConversion(240), global.blackSide.UnitConversion(190), true);

        img_btn_check[3] = img_btn_check[0];

        img_btn_add[0] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.calculatoraddbutton1);
        img_btn_add[0] = Bitmap.createScaledBitmap(img_btn_add[0], global.blackSide.UnitConversion(190), global.blackSide.UnitConversion(190), true);
        img_btn_add[1] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.calculatoraddbutton2);
        img_btn_add[1] = Bitmap.createScaledBitmap(img_btn_add[1], global.blackSide.UnitConversion(190), global.blackSide.UnitConversion(190), true);
        img_btn_add[2] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.calculatoraddbutton3);
        img_btn_add[2] = Bitmap.createScaledBitmap(img_btn_add[2], global.blackSide.UnitConversion(190), global.blackSide.UnitConversion(190), true);

        img_btn_add[3] = img_btn_add[0];

        img_btn_sub[0] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.calculatorsubbutton1);
        img_btn_sub[0] = Bitmap.createScaledBitmap(img_btn_sub[0], global.blackSide.UnitConversion(190), global.blackSide.UnitConversion(190), true);
        img_btn_sub[1] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.calculatorsubbutton2);
        img_btn_sub[1] = Bitmap.createScaledBitmap(img_btn_sub[1], global.blackSide.UnitConversion(190), global.blackSide.UnitConversion(190), true);
        img_btn_sub[2] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.calculatorsubbutton3);
        img_btn_sub[2] = Bitmap.createScaledBitmap(img_btn_sub[2], global.blackSide.UnitConversion(190), global.blackSide.UnitConversion(190), true);

        img_btn_sub[3] = img_btn_sub[0];

        img_btn_multi[0] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.calculatormulbutton1);
        img_btn_multi[0] = Bitmap.createScaledBitmap(img_btn_multi[0], global.blackSide.UnitConversion(190), global.blackSide.UnitConversion(190), true);
        img_btn_multi[1] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.calculatormulbutton2);
        img_btn_multi[1] = Bitmap.createScaledBitmap(img_btn_multi[1], global.blackSide.UnitConversion(190), global.blackSide.UnitConversion(190), true);
        img_btn_multi[2] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.calculatormulbutton3);
        img_btn_multi[2] = Bitmap.createScaledBitmap(img_btn_multi[2], global.blackSide.UnitConversion(190), global.blackSide.UnitConversion(190), true);

        img_btn_multi[3] = img_btn_multi[0];

        img_btn_sum[0] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.calculatosumbutton1);
        img_btn_sum[0] = Bitmap.createScaledBitmap(img_btn_sum[0], global.blackSide.UnitConversion(190), global.blackSide.UnitConversion(190), true);
        img_btn_sum[1] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.calculatosumbutton2);
        img_btn_sum[1] = Bitmap.createScaledBitmap(img_btn_sum[1], global.blackSide.UnitConversion(190), global.blackSide.UnitConversion(190), true);
        img_btn_sum[2] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.calculatosumbutton3);
        img_btn_sum[2] = Bitmap.createScaledBitmap(img_btn_sum[2], global.blackSide.UnitConversion(190), global.blackSide.UnitConversion(190), true);

        img_btn_sum[3] = img_btn_sum[0];

        img_btn_temp[0] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.calculatortempbutton1);
        img_btn_temp[0] = Bitmap.createScaledBitmap(img_btn_temp[0], global.blackSide.UnitConversion(240), global.blackSide.UnitConversion(390), true);
        img_btn_temp[1] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.calculatortempbutton2);
        img_btn_temp[1] = Bitmap.createScaledBitmap(img_btn_temp[1], global.blackSide.UnitConversion(240), global.blackSide.UnitConversion(390), true);
        img_btn_temp[2] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.calculatortempbutton3);
        img_btn_temp[2] = Bitmap.createScaledBitmap(img_btn_temp[2], global.blackSide.UnitConversion(240), global.blackSide.UnitConversion(390), true);

        img_btn_temp[3] = img_btn_temp[0];

        backSpace.Create(AnimationButton.BASIC,
                new Vector2(global.blackSide.UnitConversion(10, BlackSide.width),
                        global.blackSide.UnitConversion(10, BlackSide.height)),
                AnimationButton.RECTANGLE, global.bitmapResource.ActionBackSpaceImage,
                3, 3, 5, 10, global);

        button_num[0].Create(AnimationButton.BASIC,
                new Vector2(global.blackSide.UnitConversion(630, BlackSide.width),
                        global.blackSide.UnitConversion(840, BlackSide.height)),
                AnimationButton.RECTANGLE, img_btn[0],
                3, 3, 4, 10, global);
        button_num[1].Create(AnimationButton.BASIC,
                new Vector2(global.blackSide.UnitConversion(420, BlackSide.width),
                        global.blackSide.UnitConversion(640, BlackSide.height)),
                AnimationButton.RECTANGLE, img_btn[1],
                3, 3, 4, 10, global);
        button_num[2].Create(AnimationButton.BASIC,
                new Vector2(global.blackSide.UnitConversion(630, BlackSide.width),
                        global.blackSide.UnitConversion(640, BlackSide.height)),
                AnimationButton.RECTANGLE, img_btn[2],
                3, 3, 4, 10, global);
        button_num[3].Create(AnimationButton.BASIC,
                new Vector2(global.blackSide.UnitConversion(840, BlackSide.width),
                        global.blackSide.UnitConversion(640, BlackSide.height)),
                AnimationButton.RECTANGLE, img_btn[3],
                3, 3, 4, 10, global);
        button_num[4].Create(AnimationButton.BASIC,
                new Vector2(global.blackSide.UnitConversion(420, BlackSide.width),
                        global.blackSide.UnitConversion(440, BlackSide.height)),
                AnimationButton.RECTANGLE, img_btn[4],
                3, 3, 4, 10, global);
        button_num[5].Create(AnimationButton.BASIC,
                new Vector2(global.blackSide.UnitConversion(630, BlackSide.width),
                        global.blackSide.UnitConversion(440, BlackSide.height)),
                AnimationButton.RECTANGLE, img_btn[5],
                3, 3, 4, 10, global);
        button_num[6].Create(AnimationButton.BASIC,
                new Vector2(global.blackSide.UnitConversion(840, BlackSide.width),
                        global.blackSide.UnitConversion(440, BlackSide.height)),
                AnimationButton.RECTANGLE, img_btn[6],
                3, 3, 4, 10, global);
        button_num[7].Create(AnimationButton.BASIC,
                new Vector2(global.blackSide.UnitConversion(420, BlackSide.width),
                        global.blackSide.UnitConversion(240, BlackSide.height)),
                AnimationButton.RECTANGLE, img_btn[7],
                3, 3, 4, 10, global);
        button_num[8].Create(AnimationButton.BASIC,
                new Vector2(global.blackSide.UnitConversion(630, BlackSide.width),
                        global.blackSide.UnitConversion(240, BlackSide.height)),
                AnimationButton.RECTANGLE, img_btn[8],
                3, 3, 4, 10, global);
        button_num[9].Create(AnimationButton.BASIC,
                new Vector2(global.blackSide.UnitConversion(840, BlackSide.width),
                        global.blackSide.UnitConversion(240, BlackSide.height)),
                AnimationButton.RECTANGLE, img_btn[9],
                3, 3, 4, 10, global);

        button_back.Create(AnimationButton.BASIC,
                new Vector2(global.blackSide.UnitConversion(840, BlackSide.width),
                        global.blackSide.UnitConversion(840, BlackSide.height)),
                AnimationButton.RECTANGLE, img_btn_back,
                3, 3, 4, 10, global);
        button_clear.Create(AnimationButton.BASIC,
                new Vector2(global.blackSide.UnitConversion(420, BlackSide.width),
                        global.blackSide.UnitConversion(840, BlackSide.height)),
                AnimationButton.RECTANGLE, img_btn_clear,
                3, 3, 4, 10, global);
        button_cancel.Create(AnimationButton.BASIC,
                new Vector2(global.blackSide.UnitConversion(1280, BlackSide.width),
                        global.blackSide.UnitConversion(240, BlackSide.height)),
                AnimationButton.RECTANGLE, img_btn_cancel,
                3, 3, 4, 10, global);
        button_check.Create(AnimationButton.BASIC,
                new Vector2(global.blackSide.UnitConversion(1280, BlackSide.width),
                        global.blackSide.UnitConversion(440, BlackSide.height)),
                AnimationButton.RECTANGLE, img_btn_check,
                3, 3, 4, 10, global);
        button_add.Create(AnimationButton.BASIC,
                new Vector2(global.blackSide.UnitConversion(1050, BlackSide.width),
                        global.blackSide.UnitConversion(640, BlackSide.height)),
                AnimationButton.RECTANGLE, img_btn_add,
                3, 3, 4, 10, global);
        button_sub.Create(AnimationButton.BASIC,
                new Vector2(global.blackSide.UnitConversion(1050, BlackSide.width),
                        global.blackSide.UnitConversion(440, BlackSide.height)),
                AnimationButton.RECTANGLE, img_btn_sub,
                3, 3, 4, 10, global);
        button_multi.Create(AnimationButton.BASIC,
                new Vector2(global.blackSide.UnitConversion(1050, BlackSide.width),
                        global.blackSide.UnitConversion(240, BlackSide.height)),
                AnimationButton.RECTANGLE, img_btn_multi,
                3, 3, 4, 10, global);
        button_sum.Create(AnimationButton.BASIC,
                new Vector2(global.blackSide.UnitConversion(1050, BlackSide.width),
                        global.blackSide.UnitConversion(840, BlackSide.height)),
                AnimationButton.RECTANGLE, img_btn_sum,
                3, 3, 4, 10, global);
        button_temp.Create(AnimationButton.BASIC,
                new Vector2(global.blackSide.UnitConversion(1280, BlackSide.width),
                        global.blackSide.UnitConversion(640, BlackSide.height)),
                AnimationButton.RECTANGLE, img_btn_temp,
                3, 3, 4, 10, global);
    }

    public void set(int Empty) {
    }

    public boolean Destroy() {
        Sound_Buttons.stop();
        expression = "";
        return true;
    }

    public int getResult() {
        return result;
    }

    // Button Update
    private void ButtonUpdate() {

        backSpace.Update();

        for (int i = 0; i < 10; i++)
            button_num[i].Update();

        button_back.Update();
        button_clear.Update();
        button_cancel.Update();
        button_check.Update();
        button_add.Update();
        button_sub.Update();
        button_multi.Update();
        button_sum.Update();
        button_temp.Update();
    }

    private void ButtonRender(Canvas canvas) {
        for (int i = 0; i < 10; i++)
            button_num[i].Render(canvas);

        button_back.Render(canvas);
        button_clear.Render(canvas);
        button_cancel.Render(canvas);
        button_check.Render(canvas);
        button_add.Render(canvas);
        button_sub.Render(canvas);
        button_multi.Render(canvas);
        button_sum.Render(canvas);
        button_temp.Render(canvas);
    }

    // 주 실행
    public boolean Update() {

        ButtonUpdate();

        if (button_sum.isClick()) {
            Sound_Buttons.play(100.0f);
            Calculation();
        } else if (button_clear.isClick()) {
            Sound_Buttons.play(100.0f);
            expression = "";
        } else if (button_cancel.isClick()) {
            Sound_Buttons.play(100.0f);
            calculator_running = false;
            expression = "";
            return Destroy();
        } else if (button_check.isClick()) {
            Sound_Buttons.play(100.0f);
            calculator_running = false;
            if (!calculated && expression.length() > 0 && operator_amount_count == 0) {
                SetResult(Integer.parseInt(expression));
            }
            return Destroy();
        }

        Input_Expression();
        return false;
    }

    public void Render(Canvas canvas) {
        paint.setColor(Color.WHITE);
        paint.setTextSize(global.blackSide.FontUnitConversion(100));
        paint.setTextAlign(Paint.Align.RIGHT);

        canvas.drawBitmap(background,
                global.blackSide.UnitConversion(370, BlackSide.width),
                global.blackSide.UnitConversion(30, BlackSide.height), null);

        canvas.drawText(expression.toString(),
                global.blackSide.UnitConversion(1435, BlackSide.width),
                global.blackSide.UnitConversion(170, BlackSide.height), paint);
        ButtonRender(canvas);

    }

    void Input_Expression() {

        if (expression.equals("0") || expression.equals("*") || expression.equals("-") || expression.equals("+"))
            expression = "";

        if (expression.length() < 17) {

            for (int i = 0; i < 10; i++)
                if (button_num[i].isStart()) {
                    Sound_Buttons.play(100.0f);
                }


            for (int i = 0; i < 10; i++)
                if (button_num[i].isClick()) {
                    expression += i;
                }

            if (button_add.isClick()) {
                Sound_Buttons.play(100.0f);
                expression += "+";
            } else if (button_sub.isClick()) {
                Sound_Buttons.play(100.0f);
                expression += "-";
            } else if (button_multi.isClick()) {
                Sound_Buttons.play(100.0f);
                expression += "*";
            } else if (button_back.isClick()) {
                Sound_Buttons.play(100.0f);
                if (expression.length() != 0)
                    expression = expression.substring(0, expression.length() - 1);
            }
        }
    }

    void Calculation() {

        //FUCK YOU ERRORS
        if (expression.length() == 0 ||
                expression.charAt(expression.length() - 1) == '+' ||
                expression.charAt(expression.length() - 1) == '-' ||
                expression.charAt(expression.length() - 1) == '*' ||
                expression.toString() == "ERROR") {
            expression = "ERROR";
            return;
        }

        operator_amount_count = 0;

        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) == '+' || expression.charAt(i) == '-' || expression.charAt(i) == '*')
                operator_amount_count++;
        }

        if (operator_amount_count == 0) {
            SetResult(Integer.parseInt(expression));
            return;
        }

        if (operator_amount_count >= 1) {

            operators = new char[operator_amount_count];
            operands = new int[operator_amount_count + 1];
            temp = "";
            count = 0;

            //Splitting
            for (int i = 0; i < expression.length(); i++) {
                if (expression.charAt(i) != '+' && expression.charAt(i) != '-' && expression.charAt(i) != '*') {
                    temp += expression.charAt(i);
                    if (i == expression.length() - 1)
                        operands[count] = Integer.parseInt(temp);
                } else {
                    operands[count] = Integer.parseInt(temp);
                    operators[count++] = expression.charAt(i);
                    temp = "";
                }
            }

            //Calculate Multi First
            for (int i = 0; i < operator_amount_count; i++) {
                if (operators[i] == '*') {
                    operands[i + 1] = operands[i] * operands[i + 1];
                    operands[i] = -1;
                    operators[i] = '@';
                }
            }

            //arange operators
            for (int i = 0; i < operator_amount_count; i++) {
                if (operators[i] == '@') {
                    for (int j = i; j < operator_amount_count; j++) {
                        if (operators[j] == '+' || operators[j] == '-') {
                            operators[i] = operators[j];
                            operators[j] = '@';
                            break;
                        }
                    }
                }
            }

            //arange operands
            for (int i = 0; i <= operator_amount_count; i++) {
                if (operands[i] == -1) {
                    for (int j = i; j <= operator_amount_count; j++) {
                        if (operands[j] != -1) {
                            operands[i] = operands[j];
                            operands[j] = -1;
                            break;
                        }
                    }
                }
            }

            //Calculate Plus & Minus
            for (int i = 0; i < operator_amount_count; i++) {
                if (operands[i + 1] != -1) {
                    if (operators[i] != '@' && operators[i] == '+') {
                        operands[i + 1] = operands[i] + operands[i + 1];
                        operands[i] = -1;
                    } else if (operators[i] != '@' && operators[i] == '-') {
                        operands[i + 1] = operands[i] - operands[i + 1];
                        operands[i] = -1;
                    }
                } else {
                    break;
                }
            }

            //Find Result Index
            for (int i = 0; i <= operator_amount_count; i++) {
                if (operands[i] != -1) {
                    SetResult(operands[i]);
                    expression = String.valueOf(result);
                    break;
                }
            }
        }
    }

    int Result() {
        return result;
    }

    public void SetResult(int _result) {
        result = _result;
    }

    // UI 랜더
    public void UIRender(Canvas canvas) {

        backSpace.Render(canvas);
    }
}
