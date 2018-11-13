package navi_studio.rocket_fectory.scene.Demo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.widget.ImageView;

import navi_studio.rocket_fectory.Function.Gesture;
import navi_studio.rocket_fectory.Function.SoundUnit;
import navi_studio.rocket_fectory.Function.TileRender;
import navi_studio.rocket_fectory.Function.Vector2;
import navi_studio.rocket_fectory.InGame.MapCreator;
import navi_studio.rocket_fectory.Interface.sceneInterface;
import navi_studio.rocket_fectory.Object.Available.SpecialBuilding.CommandCenter;
import navi_studio.rocket_fectory.Object.Available.SpecialBuilding.TetoraCenter;
import navi_studio.rocket_fectory.Object.Manager.ObjManager;
import navi_studio.rocket_fectory.R;

public class sceneInGame extends sceneInterface {

    ObjManager objManager;

    CommandCenter commandCenter;
    TetoraCenter tetoraCenter;

    MapCreator mapCreator;

    Bitmap[] touchAnimationBitmap;

    Gesture gesture;

    boolean first;                      // 최초 진입 확인 ( bitmapResource 는 최초 진입에서 생성합니다. )

    TileRender tileRender;

    @Override
    public int Awake(int progress) {

        if (global.bitmapResource.success && progress == 0)
            progress = 4000;

        if (!global.bitmapResource.success)
            return global.bitmapResource.Awake();

        progress++;

        switch (progress) {
            case 4001:
                gesture = new Gesture();
                break;
            case 4002:
                tileRender=new TileRender();
                tileRender.Create(global);

                objManager = new ObjManager();
                objManager.Create(global);
                break;
            case 4003:
                break;
            case 4004:
                commandCenter = new CommandCenter();
                break;
            case 4005:
                commandCenter.Create(
                        new Vector2(
                                global.blackSide.UnitConversion(1200),
                                global.blackSide.UnitConversion(600)),
                        global.bitmapResource.CommandCenterImage, global
                );
                break;
            case 4006:
                objManager.add(commandCenter);
                break;
            case 4007:
                tetoraCenter = new TetoraCenter();
                break;
            case 4008:
                tetoraCenter.Initialize(global);
                break;
            case 4009:
                tetoraCenter.Create(
                        new Vector2(
                                global.blackSide.UnitConversion(2300),
                                global.blackSide.UnitConversion(1200)),
                        global.bitmapResource.TetoraCenterImage[tetoraCenter.getLevel()]
                );
                break;
            case 4010:
                objManager.add(tetoraCenter);
                break;
            case 4011:
                touchAnimationBitmap = new Bitmap[23];
                break;
            case 4012:
                touchAnimationBitmap[0] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.dot1);
                break;
            case 4013:
                touchAnimationBitmap[1] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.dot2);
                break;
            case 4014:
                touchAnimationBitmap[2] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.dot3);
                break;
            case 4015:
                touchAnimationBitmap[3] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.dot4);
                break;
            case 4016:
                touchAnimationBitmap[4] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.dot5);
                break;
            case 4017:
                touchAnimationBitmap[5] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.dot6);
                break;
            case 4018:
                touchAnimationBitmap[6] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.dot7);
                break;
            case 4019:
                touchAnimationBitmap[7] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.dot8);
                break;
            case 4020:
                touchAnimationBitmap[8] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.dot9);
                break;
            case 4021:
                touchAnimationBitmap[9] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.dot10);
                break;
            case 4022:
                touchAnimationBitmap[10] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.dot11);
                break;
            case 4023:
                touchAnimationBitmap[11] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.dot12);
                break;
            case 4024:
                touchAnimationBitmap[12] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.dot13);
                break;
            case 4025:
                touchAnimationBitmap[13] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.dot14);
                break;
            case 4026:
                touchAnimationBitmap[14] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.dot15);
                break;
            case 4027:
                touchAnimationBitmap[15] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.dot16);
                break;
            case 4028:
                touchAnimationBitmap[16] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.dot17);
                break;
            case 4029:
                touchAnimationBitmap[17] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.dot18);
                break;
            case 4030:
                touchAnimationBitmap[18] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.dot19);
                break;
            case 4031:
                touchAnimationBitmap[19] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.dot20);
                break;
            case 4032:
                touchAnimationBitmap[20] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.dot21);
                break;
            case 4033:
                touchAnimationBitmap[21] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.dot22);
                break;
            case 4034:
                touchAnimationBitmap[22] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.dot23);
                break;
            case 4035:
                gesture.Create(global, objManager, touchAnimationBitmap);
                break;
            case 4036:
                mapCreator = new MapCreator(objManager, global);
                mapCreator.Create();
                break;
            default:
                return success;
        }
        return progress;
    }

    @Override
    public void Start() {
        global.camera.setPos(
                commandCenter.getPos().x,
                commandCenter.getPos().y);
    }

    @Override
    public void FixedUpdate() {
        objManager.FixedUpdate();
    }

    @Override
    public void UIUpdate() {
        gesture.Update();
    }


    @Override
    public String Update() {

        tileRender.Update();
        objManager.Update();

        if (commandCenter.IsJump()) {
            return "Lobby";
        }

        return Running;
    }

    @Override
    public void LateUpdate() {

    }

    @Override
    public void Render(Canvas canvas) {
        canvas.drawColor(Color.rgb(61, 108, 28));
        tileRender.Render(canvas);

        FirstRender(canvas);

        objManager.Render(canvas);


    }

    public void FirstRender(Canvas canvas) {
        gesture.FirstRender(canvas);
    }

    @Override
    public void LastRender(Canvas canvas) {
        gesture.Render(canvas);
    }

    @Override
    public void UIRender(Canvas canvas) {
        objManager.UIRender(canvas);
        gesture.UIRender(canvas);
    }

    @Override
    public void Exception() {
        // 저장
    }

    @Override
    public void Disable() {
        // 저장
    }

    @Override
    public void Destroy() {
    }
}
