package navi_studio.rocket_fectory.scene.Test;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import navi_studio.rocket_fectory.Button.AnimationButton;
import navi_studio.rocket_fectory.Button.Button;
import navi_studio.rocket_fectory.Function.Animation;
import navi_studio.rocket_fectory.Function.Vector2;
import navi_studio.rocket_fectory.Global.BitmapResource;
import navi_studio.rocket_fectory.Global.BlackSide;
import navi_studio.rocket_fectory.Global.Target;
import navi_studio.rocket_fectory.Interface.sceneInterface;
import navi_studio.rocket_fectory.R;

/**
 * Created by beanb on 2018-03-11.
 */

public class scenePrologue extends sceneInterface {

    Paint paint;

    Button TestButton01;
    Button TestButton02;

    AnimationButton TestAnimationButton01;

    Animation TestAnimation01;

    @Override
    public int Awake(int progress) {
        /*
        if (Target.test) {
            global.bitmapResource.Awake();

            TestButton01 = new Button();
            TestButton02 = new Button();
            TestAnimation01 = new Animation();
            TestAnimationButton01 = new AnimationButton();

            Bitmap testAnimationButtonImage[] = new Bitmap[5];

            testAnimationButtonImage[0]
                    = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.starttitlebtton1);
            testAnimationButtonImage[1]
                    = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.starttitlebtton2);
            testAnimationButtonImage[2]
                    = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.starttitlebtton3);
            testAnimationButtonImage[3]
                    = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.starttitlebtton4);
            testAnimationButtonImage[4]
                    = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.starttitlebtton5);

            TestAnimationButton01.Create(AnimationButton.CENTER,
                    new Vector2(global.blackSide.UnitConversion(300, BlackSide.width),
                            global.blackSide.UnitConversion(800, BlackSide.height)),
                    new Vector2(global.blackSide.UnitConversion(220),
                            global.blackSide.UnitConversion(240)),
                    AnimationButton.RECTANGLE,testAnimationButtonImage,
                    3,3,5,10,global
                    );

            TestButton01.Create(Button.BASIC,
                    global.blackSide.UnitConversion(200, BlackSide.width), global.blackSide.UnitConversion(200, BlackSide.height),
                    global.blackSide.UnitConversion(320), global.blackSide.UnitConversion(320),
                    BitmapFactory.decodeResource(global.context.getResources(), R.drawable.testbutton01),
                    Button.CIRCLE);

            TestButton02.Create(Button.BASIC,
                    global.blackSide.UnitConversion((1920 - 500), BlackSide.width), global.blackSide.UnitConversion(600, BlackSide.height),
                    global.blackSide.UnitConversion(300), global.blackSide.UnitConversion(300),
                    BitmapFactory.decodeResource(global.context.getResources(), R.drawable.testbutton02),
                    Button.RECTANGLE);

            // 애니메이션 팩 셋팅용 임시 변수
            Bitmap temp[] = new Bitmap[5];

            // 애니메이션 팩 셋팅
            temp[0] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.testanimation1);
            temp[1] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.testanimation2);
            temp[2] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.testanimation3);
            temp[3] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.testanimation4);
            temp[4] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.testanimation5);

            TestAnimation01.Create(Animation.BASIC,
                    global.blackSide.UnitConversion((1920 - 500), BlackSide.width), global.blackSide.UnitConversion(300, BlackSide.height),
                    global.blackSide.UnitConversion(250), global.blackSide.UnitConversion(250),
                    temp,10);
        }
        */
        return success;
    }

    @Override
    public void Start() {
        if (Target.test) {
            paint = new Paint();
            paint.setColor(Color.WHITE);
            paint.setTextSize(global.blackSide.FontUnitConversion(50));
            paint.setTextAlign(Paint.Align.CENTER);
        }
    }

    @Override
    public String Update() {
        if (Target.test) {

            TestAnimationButton01.Update();
            if(TestAnimationButton01.isClick()){
                Next = "Test";
                return "loadTest";
            }
            if (TestButton01.TouchCheck(global.inputManager.touch(0).x(), global.inputManager.touch(0).y(), global.inputManager.touch(0).type())) {
                Next = "Test";
                return "loadTest";
            }
            if (TestButton02.TouchCheck(global.inputManager.touch(0).x(), global.inputManager.touch(0).y(), global.inputManager.touch(0).type())) {
                return "End";
            }
        }
        return Running;
    }

    @Override
    public void Render(Canvas canvas) {
        if (Target.test) {
            canvas.drawText("Prologue On",
                    global.blackSide.UnitConversion(960, global.blackSide.width),
                    global.blackSide.UnitConversion(800, global.blackSide.height),
                    paint);

            canvas.drawText(global.camera.getSizeWidth() + " / " + global.camera.getSizeHeight(),
                    global.blackSide.UnitConversion(960, global.blackSide.width),
                    global.blackSide.UnitConversion(700, global.blackSide.height),
                    paint);
        }
    }

    @Override
    public void UIRender(Canvas canvas) {
        if (Target.test) {

            TestButton01.Render(canvas);
            TestButton02.Render(canvas);

            TestAnimation01.Render(canvas);

            TestAnimationButton01.Render(canvas);

            canvas.drawText("Test / loadTest",
                    global.blackSide.UnitConversion(350, global.blackSide.width),
                    global.blackSide.UnitConversion(575, global.blackSide.height),
                    paint);
            canvas.drawText("End / Null",
                    global.blackSide.UnitConversion((1920 - 350), global.blackSide.width),
                    global.blackSide.UnitConversion(975, global.blackSide.height),
                    paint);
        }
    }

    @Override
    public void Destroy() {
        paint = null;
        TestButton01 = null;
        TestButton02 = null;
    }
}
