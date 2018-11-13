package navi_studio.rocket_fectory.Action;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.Random;

import navi_studio.rocket_fectory.Button.AnimationButton;
import navi_studio.rocket_fectory.Function.GaugeBar;
import navi_studio.rocket_fectory.Function.SoundUnit;
import navi_studio.rocket_fectory.Function.Vector2;
import navi_studio.rocket_fectory.Global.BlackSide;
import navi_studio.rocket_fectory.Global.Global;
import navi_studio.rocket_fectory.Global.ItemCode;
import navi_studio.rocket_fectory.Object.Core.ObjLow;
import navi_studio.rocket_fectory.Object.Core.ObjectCode;
import navi_studio.rocket_fectory.R;

public class CollectingAction extends ObjLow {
    private final static int gauge_max = 100;
    private Global global;
    Paint paint;
    Bitmap background;
    Bitmap pre_item;            //처리되기전 아이템
    Bitmap post_item;           //처리된후 받게될 아이템
    Bitmap gauge_background;    //게이지 백그라운드
    Bitmap gauge_percent;       //게이지 퍼센트
    Bitmap gauge_frame;         //게이지 프레임
    Random random;              //랜덤
    boolean is_touched = false;   //눌렸는가
    private boolean first = true;
    private AnimationButton backSpace;              // 뒤로가기 버튼
    private int storageCode;                        // 받아온 코드   오브젝트 코드
    GaugeBar gaugeBar;
    boolean is_full = false;
    boolean isWater = false;

    //TODO : Pick CollectingAction Sound
    SoundUnit Sound_Collecting;
    SoundUnit Sound_Back_Button;

    // destroy = true 일 경우 파괴
    public void Create(Global _global) {
        backSpace = new AnimationButton();
        global = _global;
        paint = new Paint();
        random = new Random();

        Sound_Collecting=new SoundUnit();
        Sound_Back_Button=new SoundUnit();

        Sound_Collecting.Create(SoundUnit.music,R.raw.collecting_action_button,1,global.context);
        Sound_Back_Button.Create(SoundUnit.music,R.raw.back_button,1,global.context);

        gauge_background = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.mininggaugebarbackground);
        gauge_background = Bitmap.createScaledBitmap(gauge_background,
                global.blackSide.UnitConversion(980),
                global.blackSide.UnitConversion(140), true);
        gauge_frame = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.mininggaugebarframe);
        gauge_frame = Bitmap.createScaledBitmap(gauge_frame,
                global.blackSide.UnitConversion(980),
                global.blackSide.UnitConversion(140), true);
        gauge_percent = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.mininggauge);
        gauge_percent = Bitmap.createScaledBitmap(gauge_percent,
                global.blackSide.UnitConversion(30),
                global.blackSide.UnitConversion(100), true);

        gaugeBar = new GaugeBar();

        gaugeBar.Create(gauge_percent, gauge_background, gauge_frame,
                new Vector2(global.blackSide.UnitConversion(475, BlackSide.width), global.blackSide.UnitConversion(100, BlackSide.height)),
                gauge_max, true, global.blackSide.UnitConversion(40));
        background = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.gatheringframe);
        background = Bitmap.createScaledBitmap(background,
                global.blackSide.UnitConversion(1900),
                global.blackSide.UnitConversion(1060), true);

        backSpace.Create(AnimationButton.BASIC,
                new Vector2(global.blackSide.UnitConversion(10, BlackSide.width),
                        global.blackSide.UnitConversion(10, BlackSide.height)),
                AnimationButton.RECTANGLE, global.bitmapResource.ActionBackSpaceImage,
                3, 3, 5, 10, global);


    }

    private void Start() {

        switch (storageCode)        //자원 코드로 그릴 자원들 분기
        {
            case ObjectCode.Log:
                pre_item = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.gatheringlog);
                pre_item = Bitmap.createScaledBitmap(pre_item,
                        global.blackSide.UnitConversion(850),
                        global.blackSide.UnitConversion(560), true);
                post_item = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.log);
                break;
            case ObjectCode.Stone:
                pre_item = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.gatheringstone);
                pre_item = Bitmap.createScaledBitmap(pre_item,
                        global.blackSide.UnitConversion(850),
                        global.blackSide.UnitConversion(560), true);
                post_item = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.stone);
                break;
            case ObjectCode.IronStone:
                pre_item = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.gatheringironstone);
                pre_item = Bitmap.createScaledBitmap(pre_item,
                        global.blackSide.UnitConversion(850),
                        global.blackSide.UnitConversion(560), true);
                post_item = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.ironstone);
                break;
            case ObjectCode.Coal:
                pre_item = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.gatheringcoal);
                pre_item = Bitmap.createScaledBitmap(pre_item,
                        global.blackSide.UnitConversion(850),
                        global.blackSide.UnitConversion(560), true);
                post_item = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.coal);
                break;
            case ObjectCode.Dirt:
                pre_item = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.gatheringdust);
                pre_item = Bitmap.createScaledBitmap(pre_item,
                        global.blackSide.UnitConversion(850),
                        global.blackSide.UnitConversion(560), true);
                post_item = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.dust);
                break;
            case ObjectCode.Water:
                isWater = true;
                return;
            default:
                break;
        }

        // 크기 따로 설정
        pre_item = Bitmap.createScaledBitmap(pre_item,
                global.blackSide.UnitConversion(770),
                global.blackSide.UnitConversion(560), true);

        post_item = Bitmap.createScaledBitmap(post_item,
                global.blackSide.UnitConversion(250),
                global.blackSide.UnitConversion(250), true);
    }

    public void set(int _storageCode) {
        storageCode = _storageCode;
        Start();
    }

    public boolean Destroy() {
        /*Sound_Collecting.stop();
        Sound_Back_Button.stop();*/
        gaugeBar.fill();
        return true;
    }

    private void touchStart() {
        if (global.inputManager.touch(0).x() >= global.blackSide.UnitConversion(500, BlackSide.width) &&
                global.inputManager.touch(0).x() <= global.blackSide.UnitConversion(1300, BlackSide.width) &&
                global.inputManager.touch(0).y() >= global.blackSide.UnitConversion(300, BlackSide.height) &&
                global.inputManager.touch(0).y() <= global.blackSide.UnitConversion(860, BlackSide.height)) {
            is_touched = true;
            Sound_Collecting.play(100.0f);
            if (gaugeBar.getValue() <= 0) {     //0보다 작을 때
                gaugeBar.fill();
                if (!global.inventory.isFull(storageCode, 1)) {       // 아이템이 꽉 차지 않았을 때
                    global.inventory.addItem(storageCode, 1);
                    is_full = false;
                } else {
                    is_full = true;
                }
            } else {
                gaugeBar.addValue(-10);
            }
        }
    }

    private void touchState() {
        // 터치중
    }

    private void touchEnd() {
        is_touched = false;
    }

    // 주 실행
    public boolean Update() {

        if (isWater) {
            isWater = false;
            return true;
        }

        backSpace.Update();
        if (backSpace.isClick()) {
            Sound_Back_Button.play(100.0f);
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
            }
            touchState();
        }
        return false;
    }

    public void Render(Canvas canvas) {
        if (isWater)
            return;

        paint.setTextAlign(Paint.Align.CENTER);
        paint.setColor(Color.WHITE);
        paint.setTextSize(global.blackSide.FontUnitConversion(75));
        backSpace.Render(canvas);

        canvas.drawBitmap(background,
                global.blackSide.UnitConversion(10, BlackSide.width),
                global.blackSide.UnitConversion(10, BlackSide.height),
                null);
        if (is_touched) {
            canvas.drawBitmap(pre_item,
                    global.blackSide.UnitConversion(550 + random.nextInt(20) + 10, BlackSide.width),
                    global.blackSide.UnitConversion(300 + random.nextInt(20) + 10, BlackSide.height),
                    null);
        } else {
            canvas.drawBitmap(pre_item,
                    global.blackSide.UnitConversion(550, BlackSide.width),
                    global.blackSide.UnitConversion(300, BlackSide.height),
                    null);
        }

        canvas.drawBitmap(post_item,
                global.blackSide.UnitConversion(1575, BlackSide.width),
                global.blackSide.UnitConversion(730, BlackSide.height),
                null);

        if (!is_full)
            canvas.drawText("" + global.inventory.item.getRawMaterialsCell(storageCode).number,
                    global.blackSide.UnitConversion(1420, BlackSide.width),
                    global.blackSide.UnitConversion(950, BlackSide.height),
                    paint);
        else {
            canvas.drawText("" + global.inventory.item.getRawMaterialsCell(storageCode).number,
                    global.blackSide.UnitConversion(1420, BlackSide.width),
                    global.blackSide.UnitConversion(950, BlackSide.height),
                    paint);
            canvas.drawText("저장 한도 초과로 더이상 습득할 수 없습니다.",
                    global.blackSide.UnitConversion(950, BlackSide.width),
                    global.blackSide.UnitConversion(560, BlackSide.height),
                    paint);
        }

    }

    // UI 랜더
    public void UIRender(Canvas canvas) {
        if (isWater)
            return;
        gaugeBar.Render(canvas);
    }
}
