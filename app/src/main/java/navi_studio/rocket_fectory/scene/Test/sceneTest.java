package navi_studio.rocket_fectory.scene.Test;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import navi_studio.rocket_fectory.Button.Button;
import navi_studio.rocket_fectory.Function.Gesture;
import navi_studio.rocket_fectory.Function.LocaleManager;
import navi_studio.rocket_fectory.Function.Speech;
import navi_studio.rocket_fectory.Interface.sceneInterface;
import navi_studio.rocket_fectory.Object.Manager.ObjManager;
import navi_studio.rocket_fectory.R;

/**
 * Created by beanb on 2018-03-11.
 */

public class sceneTest extends sceneInterface {

    Paint paint;

    ObjManager objManager;

    Bitmap touchAnimationBitmap[];

    Gesture gesture;

    Speech speech;
    LocaleManager localeManager;
    @Override
    public int Awake(int progress) {
        return success;
    }

    @Override
    public void Start() {

        global.bitmapResource.Awake();

        gesture = new Gesture();
        objManager = new ObjManager();

        objManager.Create(global);
        localeManager=new LocaleManager("testscript");
        speech.Create(localeManager,"001001A001","001001A004",global);

        touchAnimationBitmap = new Bitmap[23];

        touchAnimationBitmap[0] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.dot1);
        touchAnimationBitmap[1] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.dot2);
        touchAnimationBitmap[2] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.dot3);
        touchAnimationBitmap[3] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.dot4);
        touchAnimationBitmap[4] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.dot5);
        touchAnimationBitmap[5] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.dot6);
        touchAnimationBitmap[6] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.dot7);
        touchAnimationBitmap[7] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.dot8);
        touchAnimationBitmap[8] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.dot9);
        touchAnimationBitmap[9] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.dot10);
        touchAnimationBitmap[10] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.dot11);
        touchAnimationBitmap[11] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.dot12);
        touchAnimationBitmap[12] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.dot13);
        touchAnimationBitmap[13] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.dot14);
        touchAnimationBitmap[14] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.dot15);
        touchAnimationBitmap[15] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.dot16);
        touchAnimationBitmap[16] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.dot17);
        touchAnimationBitmap[17] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.dot18);
        touchAnimationBitmap[18] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.dot19);
        touchAnimationBitmap[19] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.dot20);
        touchAnimationBitmap[20] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.dot21);
        touchAnimationBitmap[21] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.dot22);
        touchAnimationBitmap[22] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.dot23);

        gesture.Create(global, objManager, touchAnimationBitmap);
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
        objManager.Update();
        speech.Update();
        return Running;
    }

    @Override
    public void Render(Canvas canvas) {
        canvas.drawARGB(100,255,255,255);
        objManager.Render(canvas);
        gesture.Render(canvas);
        speech.Render(canvas);
    }

    @Override
    public void UIRender(Canvas canvas) {
        objManager.UIRender(canvas);
        gesture.UIRender(canvas);
    }

    @Override
    public void Exception() {

    }

    @Override
    public void Destroy() {
        paint = null;
    }
}
