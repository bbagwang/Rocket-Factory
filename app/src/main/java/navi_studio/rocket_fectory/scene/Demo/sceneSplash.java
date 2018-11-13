package navi_studio.rocket_fectory.scene.Demo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import navi_studio.rocket_fectory.Function.SoundUnit;
import navi_studio.rocket_fectory.Global.BlackSide;
import navi_studio.rocket_fectory.Interface.sceneInterface;
import navi_studio.rocket_fectory.R;

public class sceneSplash extends sceneInterface {

    Bitmap Logo;                // Splash 로고

    Paint paint;

    int count;
    int AValue;                 // 알파값

    boolean Reach;             // 설정

    SoundUnit Sound_Splash;

    @Override
    public int Awake(int progress) {
        Logo = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.logo);
        Logo = Bitmap.createScaledBitmap(Logo,
                global.blackSide.UnitConversion(1920),
                global.blackSide.UnitConversion(1080), true);
        paint = new Paint();
        Reach = false;
        AValue = 255;
        paint.setARGB(AValue, 0, 0, 0);
        Sound_Splash=new SoundUnit();
        Sound_Splash.Create(SoundUnit.music,R.raw.splash_test,1,global.context);
        return success;
    }

    @Override
    public String Update() {
        Sound_Splash.play(30);
        if (!Reach)
            AValue -= 2;
        else if (Reach)
            AValue += 6;

        if (AValue <= 0) {
            Reach = true;
            AValue = 0;
        }else if (AValue >= 255) {
            return "Start";
        }

        count++;
        if(count > 7) {
            count = 0;
            paint.setARGB(AValue, 0, 0, 0);
        }
        return Running;
    }

    @Override
    public void UIRender(Canvas canvas) {
        canvas.drawBitmap(Logo,
                global.blackSide.UnitConversion(0, BlackSide.width),
                global.blackSide.UnitConversion(0, BlackSide.height),
                null);
        canvas.drawRect(0, 0,
                global.blackSide.UnitConversion(1930, BlackSide.width),
                global.blackSide.UnitConversion(1090, BlackSide.height),
                paint
        );
    }

    public void Destroy(){
        Logo.recycle();
        Logo = null;
        paint = null;
        //Sound_Splash.stop();
    }
}
