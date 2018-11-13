package navi_studio.rocket_fectory.scene.Demo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import navi_studio.rocket_fectory.Button.AnimationButton;
import navi_studio.rocket_fectory.Button.Button;
import navi_studio.rocket_fectory.Button.NullButton;
import navi_studio.rocket_fectory.Function.Animation;
import navi_studio.rocket_fectory.Function.SoundUnit;
import navi_studio.rocket_fectory.Function.Vector2;
import navi_studio.rocket_fectory.Global.BlackSide;
import navi_studio.rocket_fectory.Interface.sceneInterface;
import navi_studio.rocket_fectory.Object.Core.Ephemera;
import navi_studio.rocket_fectory.R;

public class sceneStart extends sceneInterface {

    Bitmap[] TitleRocketImage;             // 타이틀 "로켓" 이미지
    Bitmap[] TitleFactoryImage;            // 타이틀 "팩토리" 이미지

    AnimationButton TitleRocket;                  // 타이틀 "로켓"
    AnimationButton TitleFactory;                 // 타이틀 "팩토리"

    Bitmap BackGroundImage;                    // "배경" 이미지
    Bitmap LaunchingPad;                    // "발사대" 이미지
    Bitmap Rocket;                           // "로켓" 이미지
    Bitmap fire;                           // "불꽃" 이미지

    NullButton startButton;                     // 시작 버튼

    Vector2 RocketPos;

    boolean start = false;
    boolean next = false;

    int oldCount;
    int count;
    int extraCount;

    float rocketSpeed = 1;

    //TODO : Pick Start Sound
    SoundUnit Sound_Rocket;
    SoundUnit Sound_Rocket_Button;
    SoundUnit Sound_Factory_Button;
    SoundUnit Sound_Launch_Button;
    SoundUnit Sound_Title_BGM;

    @Override
    public int Awake(int progress) {
        TitleRocket = new AnimationButton();
        TitleFactory = new AnimationButton();
        TitleRocketImage = new Bitmap[12];
        TitleFactoryImage = new Bitmap[11];
        startButton = new NullButton();
        RocketPos = new Vector2();

        Sound_Rocket=new SoundUnit();
        Sound_Rocket_Button=new SoundUnit();
        Sound_Factory_Button=new SoundUnit();
        Sound_Launch_Button=new SoundUnit();
        Sound_Title_BGM=new SoundUnit();

        //TODO : Import Sounds
        Sound_Rocket.Create(SoundUnit.music,R.raw.rocket_launch_sound,1,global.context);
        Sound_Rocket_Button.Create(SoundUnit.music,R.raw.rocket_title,1,global.context);
        Sound_Factory_Button.Create(SoundUnit.music,R.raw.factory_title,1,global.context);
        Sound_Launch_Button.Create(SoundUnit.music,R.raw.launch_button,1,global.context);
        Sound_Title_BGM.Create(SoundUnit.music,R.raw.title_bgm,1,global.context);

        startButton.Create(Button.BASIC,
                global.blackSide.UnitConversion(420, BlackSide.width),
                global.blackSide.UnitConversion(570, BlackSide.height),
                global.blackSide.UnitConversion(1000),
                global.blackSide.UnitConversion(510),
                Button.RECTANGLE,global
        );

        LaunchingPad = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.lunchingpad);
        LaunchingPad = Bitmap.createScaledBitmap(LaunchingPad,
                global.blackSide.UnitConversion(990),
                global.blackSide.UnitConversion(690),
                true);

        BackGroundImage = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.backgraundslice);
        BackGroundImage = Bitmap.createScaledBitmap(BackGroundImage,
                global.blackSide.UnitConversion(80),
                global.blackSide.UnitConversion(1080),
                true);

        Rocket = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.roket);
        Rocket = Bitmap.createScaledBitmap(Rocket,
                global.blackSide.UnitConversion(70),
                global.blackSide.UnitConversion(520),
                true);

        fire = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.roketfire);
        fire = Bitmap.createScaledBitmap(fire,
                global.blackSide.UnitConversion(90),
                global.blackSide.UnitConversion(470),
                true);

        RocketPos.setVector(
                global.blackSide.UnitConversion(1030, BlackSide.width),
                global.blackSide.UnitConversion(490, BlackSide.height));

        TitleRocketImage[0] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.starttitlerocket1);
        TitleRocketImage[1] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.starttitlerocket2);
        TitleRocketImage[2] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.starttitlerocket3);
        TitleRocketImage[3] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.starttitlerocket4);
        TitleRocketImage[4] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.starttitlerocket5);
        TitleRocketImage[5] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.starttitlerocket6);
        TitleRocketImage[6] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.starttitlerocket7);
        TitleRocketImage[7] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.starttitlerocket8);
        TitleRocketImage[8] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.starttitlerocket9);
        TitleRocketImage[9] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.starttitlerocket10);
        TitleRocketImage[10] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.starttitlerocket11);
        TitleRocketImage[11] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.starttitlerocket12);

        TitleRocket.Create(AnimationButton.BASIC,
                new Vector2(
                        global.blackSide.UnitConversion(60, BlackSide.width),
                        global.blackSide.UnitConversion(20, BlackSide.height)
                ),
                new Vector2(
                        global.blackSide.UnitConversion(830),
                        global.blackSide.UnitConversion(370)
                ),
                AnimationButton.RECTANGLE, TitleRocketImage,
                1, 11, 12, 10, global
        );

        TitleFactoryImage[0] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.starttitlefactory1);
        TitleFactoryImage[1] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.starttitlefactory2);
        TitleFactoryImage[2] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.starttitlefactory3);
        TitleFactoryImage[3] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.starttitlefactory4);
        TitleFactoryImage[4] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.starttitlefactory5);
        TitleFactoryImage[5] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.starttitlefactory6);
        TitleFactoryImage[6] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.starttitlefactory7);
        TitleFactoryImage[7] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.starttitlefactory8);
        TitleFactoryImage[8] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.starttitlefactory9);
        TitleFactoryImage[9] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.starttitlefactory10);
        TitleFactoryImage[10] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.starttitlefactory11);

        TitleFactory.Create(AnimationButton.BASIC,
                new Vector2(
                        global.blackSide.UnitConversion(1050, BlackSide.width),
                        global.blackSide.UnitConversion(70, BlackSide.height)
                ),
                new Vector2(
                        global.blackSide.UnitConversion(780),
                        global.blackSide.UnitConversion(320)
                ),
                AnimationButton.RECTANGLE, TitleFactoryImage,
                1, 10, 11, 10, global
        );
        return success;
    }


    @Override
    public void FixedUpdate() {
        if (!start && !next) {
            startButton.Update();
        }

        if (startButton.isClick() && !start)
            start = true;

        if (next)
            start = false;
    }

    @Override
    public String Update() {
        //TODO : Setting Up the Sound Timing
        Sound_Title_BGM.play(100.0f);
        TitleRocket.Update();
        TitleFactory.Update();

        TitleRocket.isClick();
        TitleFactory.isClick();

        if(TitleRocket.isStart()){
            Sound_Rocket_Button.play(100.0f);
            TitleRocket.touchEnd();
        }

        if(TitleFactory.isStart()) {
            Sound_Factory_Button.play(100.0f);
            TitleFactory.touchEnd();
        }
        if(startButton.isClick()) {
            Sound_Launch_Button.play(100.0f);
        }

        if (start) {
            Sound_Title_BGM.stop();
            Sound_Rocket.play(200.0f);

            count++;
            if (oldCount + 2 < count) {
                oldCount = count;
                extraCount++;
                RocketPos.y -= rocketSpeed;
                rocketSpeed *= 1.2f;
                TitleRocket.setPosition(
                        TitleRocket.getPosition().x - 30,
                        TitleRocket.getPosition().y
                );
                TitleFactory.setPosition(
                        TitleFactory.getPosition().x + 30,
                        TitleFactory.getPosition().y
                );
                if (extraCount > 31) {
                    // 로딩 거쳐서 도달하도록
                    Sound_Rocket.stop();
                    Sound_Launch_Button.stop();
                    return "Lobby";
                }
            }
        }
        return Running;
    }

    @Override
    public void Render(Canvas canvas) {
        for (int i = 0;
             i < global.blackSide.UnitConversion(0, BlackSide.width) + global.screenSize.width;
             i += (BackGroundImage.getWidth() - 1))
            canvas.drawBitmap(BackGroundImage,
                    global.blackSide.UnitConversion(0, BlackSide.width) + i,
                    global.blackSide.UnitConversion(0, BlackSide.height),
                    null);

        TitleRocket.Render(canvas);
        TitleFactory.Render(canvas);

        canvas.drawBitmap(Rocket,
                RocketPos.x, RocketPos.y,
                null);

        canvas.drawBitmap(fire,
                RocketPos.x - global.blackSide.UnitConversion(10),
                RocketPos.y + Rocket.getHeight(),
                null);

        canvas.drawBitmap(LaunchingPad,
                global.blackSide.UnitConversion(440, BlackSide.width),
                global.blackSide.UnitConversion(391, BlackSide.height),
                null);
    }

    @Override
    public void UIRender(Canvas canvas) {
    }

    public void Destroy() {
        TitleRocket.Destroy();
        TitleFactory.Destroy();

        TitleRocket = null;
        TitleFactory = null;

        BackGroundImage.recycle();
        BackGroundImage = null;
        LaunchingPad.recycle();
        LaunchingPad = null;
        Rocket.recycle();
        Rocket = null;
        fire.recycle();
        fire = null;

        startButton = null;
        RocketPos = null;


        /*Sound_Rocket.stop();
        Sound_Rocket_Button.stop();
        Sound_Factory_Button.stop();
        Sound_Launch_Button.stop();
        Sound_Title_BGM.stop();*/
    }

}
