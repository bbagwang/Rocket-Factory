package navi_studio.rocket_fectory.scene.Demo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import navi_studio.rocket_fectory.Button.NullButton;
import navi_studio.rocket_fectory.Function.Animation;
import navi_studio.rocket_fectory.Function.SoundUnit;
import navi_studio.rocket_fectory.Global.BlackSide;
import navi_studio.rocket_fectory.Global.InputManager;
import navi_studio.rocket_fectory.Interface.sceneInterface;
import navi_studio.rocket_fectory.R;

public class sceneLobby extends sceneInterface {

    public final static int NonSelect = 99;        // 애니메이션 없음, 왼쪽 상단기준
    public final static int SelectAsurai = 100;        // 애니메이션 없음, 왼쪽 상단기준
    public final static int SelectNotepad = 101;        // 애니메이션 없음, 왼쪽 상단기준

    Bitmap LobbyImage;      // "로비" 이미지

    Animation NotepadAni;          // lobbyjournal
    Animation MonitorAni;          // lobbymonita
    Animation LedAni;              // lobbyled
    Animation AsuraiAni;           // lobbyasurai

    Bitmap[] NotepadImage;          // lobbyjournal
    Bitmap[] MonitorImage;          // lobbymonita
    Bitmap[] LedImage;              // lobbyled
    Bitmap[] AsuraiImage;           // lobbyasurai

    NullButton AsuraiButton;
    NullButton MonitorButton;
    NullButton NotepadButton;

    int state;

    //TODO : Pick Lobby Sound
    SoundUnit Sound_Ambient;
    SoundUnit Sound_Fan;
    SoundUnit Sound_Asurai_Talk;

    @Override
    public int Awake(int progress) {

        NotepadAni = new Animation();
        MonitorAni = new Animation();
        LedAni = new Animation();
        AsuraiAni = new Animation();

        AsuraiButton = new NullButton();
        MonitorButton = new NullButton();
        NotepadButton = new NullButton();

        NotepadImage = new Bitmap[6];
        MonitorImage = new Bitmap[3];
        LedImage = new Bitmap[2];
        AsuraiImage = new Bitmap[9];

        AsuraiButton.Create(
                NullButton.BASIC,
                global.blackSide.UnitConversion(80, BlackSide.width),
                global.blackSide.UnitConversion(480, BlackSide.height),
                global.blackSide.UnitConversion(430),
                global.blackSide.UnitConversion(580),
                NullButton.RECTANGLE,global
        );

        MonitorButton.Create(
                NullButton.BASIC,
                global.blackSide.UnitConversion(670, BlackSide.width),
                global.blackSide.UnitConversion(260, BlackSide.height),
                global.blackSide.UnitConversion(590),
                global.blackSide.UnitConversion(340),
                NullButton.RECTANGLE,global
        );

        NotepadButton.Create(
                NullButton.BASIC,
                global.blackSide.UnitConversion(1390, BlackSide.width),
                global.blackSide.UnitConversion(260, BlackSide.height),
                global.blackSide.UnitConversion(360),
                global.blackSide.UnitConversion(230),
                NullButton.RECTANGLE,global
        );

        LobbyImage = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.lobby);
        LobbyImage = Bitmap.createScaledBitmap(LobbyImage,
                global.blackSide.UnitConversion(1920),
                global.blackSide.UnitConversion(1080), true);

        NotepadImage[0] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.lobbyjournal1);
        NotepadImage[1] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.lobbyjournal2);
        NotepadImage[2] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.lobbyjournal3);
        NotepadImage[3] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.lobbyjournal4);
        NotepadImage[4] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.lobbyjournal5);
        NotepadImage[5] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.lobbyjournal6);

        for (int i = 0; i < NotepadImage.length; i++)
            NotepadImage[i] = Bitmap.createScaledBitmap(NotepadImage[i],
                    global.blackSide.UnitConversion(460),
                    global.blackSide.UnitConversion(210), true);

        NotepadAni.Create(Animation.BASIC,
                global.blackSide.UnitConversion(1280, BlackSide.width),
                global.blackSide.UnitConversion(280, BlackSide.height),
                NotepadImage, 10, 10
        );

        MonitorImage[0] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.lobbymonita1);
        MonitorImage[1] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.lobbymonita2);
        MonitorImage[2] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.lobbymonita3);

        for (int i = 0; i < MonitorImage.length; i++)
            MonitorImage[i] = Bitmap.createScaledBitmap(MonitorImage[i],
                    global.blackSide.UnitConversion(740),
                    global.blackSide.UnitConversion(440), true);

        MonitorAni.Create(Animation.BASIC,
                global.blackSide.UnitConversion(600, BlackSide.width),
                global.blackSide.UnitConversion(270, BlackSide.height),
                MonitorImage, 10, 3
        );

        LedImage[0] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.lobbyled1);
        LedImage[1] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.lobbyled2);

        for (int i = 0; i < LedImage.length; i++)
            LedImage[i] = Bitmap.createScaledBitmap(LedImage[i],
                    global.blackSide.UnitConversion(200),
                    global.blackSide.UnitConversion(20), true);

        LedAni.Create(Animation.BASIC,
                global.blackSide.UnitConversion(320, BlackSide.width),
                global.blackSide.UnitConversion(350, BlackSide.height),
                LedImage, 10, 7
        );

        AsuraiImage[0] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.lobbyasurai1);
        AsuraiImage[1] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.lobbyasurai2);
        AsuraiImage[2] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.lobbyasurai3);
        AsuraiImage[3] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.lobbyasurai4);
        AsuraiImage[4] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.lobbyasurai5);
        AsuraiImage[5] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.lobbyasurai6);
        AsuraiImage[6] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.lobbyasurai7);
        AsuraiImage[7] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.lobbyasurai8);
        AsuraiImage[8] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.lobbyasurai9);

        for (int i = 0; i < AsuraiImage.length; i++)
            AsuraiImage[i] = Bitmap.createScaledBitmap(AsuraiImage[i],
                    global.blackSide.UnitConversion(560),
                    global.blackSide.UnitConversion(690), true);

        AsuraiAni.Create(Animation.BASIC,
                global.blackSide.UnitConversion(20, BlackSide.width),
                global.blackSide.UnitConversion(390, BlackSide.height),
                AsuraiImage, 10, 10
        );

        return success;
    }

    @Override
    public void Start() {
        //TODO : Add Sound to Looping Ambient
        Sound_Ambient=new SoundUnit();
        Sound_Fan=new SoundUnit();
        Sound_Asurai_Talk=new SoundUnit();

        /*localeManager=new LocaleManager("AsuraiLobbyTalk");
        asurai_lobby_talk=new Speech();
        asurai_lobby_talk.Create(localeManager,"001001A001","001001A002",global);*/

        Sound_Ambient.Create(SoundUnit.music,R.raw.lobby_01_ambient,1,global.context);
        Sound_Fan.Create(SoundUnit.music,R.raw.lobby_00_fan,1,global.context);
        Sound_Asurai_Talk.Create(SoundUnit.music,R.raw.lobby_02_asurai_voice,1,global.context);

        //Sound_Ambient.play(1.0f);
        Sound_Fan.play(1.0f);
    }

    @Override
    public void FixedUpdate() {

    }

    @Override
    public void InputEvent() {

    }

    @Override
    public String Update() {
        ButtonUpdate();

        if (global.inputManager.touch(0).type() == InputManager.UP) {
            if (AsuraiButton.isClick()) {
                Sound_Asurai_Talk.play(5.0f);
            }
            if (MonitorButton.isClick()) {
                Next = "InGame";
                return "Loading";
            }
            if (NotepadButton.isClick()) {

            }
        }
        return Running;
    }

    private void ButtonUpdate(){
        AsuraiButton.Update();
        MonitorButton.Update();
        NotepadButton.Update();
    }

    @Override
    public void LateUpdate() {

    }

    @Override
    public void UIUpdate() {

    }

    @Override
    public void Render(Canvas canvas) {
        canvas.drawBitmap(LobbyImage,
                global.blackSide.UnitConversion(0, BlackSide.width),
                global.blackSide.UnitConversion(0, BlackSide.height),
                null);
    }

    @Override
    public void LastRender(Canvas canvas) {
        NotepadAni.Render(canvas);
        MonitorAni.Render(canvas);
        LedAni.Render(canvas);
        if (state != SelectAsurai)
            AsuraiAni.Render(canvas);
    }

    @Override
    public void UIRender(Canvas canvas) {

    }

    @Override
    public void Destroy() {
        NotepadAni.Destroy();
        NotepadAni = null;
        MonitorAni.Destroy();
        MonitorAni = null;
        LedAni.Destroy();
        LedAni = null;
        AsuraiAni.Destroy();
        AsuraiAni = null;

        LobbyImage.recycle();

        AsuraiButton = null;
        MonitorButton = null;
        NotepadButton = null;

        /*Sound_Ambient.stop();
        Sound_Fan.stop();
        Sound_Asurai_Talk.stop();*/
    }
}
