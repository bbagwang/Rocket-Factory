package navi_studio.rocket_fectory.scene.Demo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import navi_studio.rocket_fectory.Function.Vector2;
import navi_studio.rocket_fectory.Global.BlackSide;
import navi_studio.rocket_fectory.Global.Target;
import navi_studio.rocket_fectory.Interface.loadsceneInterface;
import navi_studio.rocket_fectory.R;

public class sceneLoading extends loadsceneInterface {

    Bitmap[] EnginePart;
    Bitmap[] EngineFrame;
    Bitmap EngineCode;

    Vector2[] PartPosition;
    Vector2[] EndPosition;

    Vector2 addPos;

    int orderNumber;
    boolean End;

    Paint paint;

    public sceneLoading() {

        orderNumber = 0;
        End = false;

        paint = new Paint();
        paint.setARGB(255, 129, 137, 163);
    }

    @Override
    public int Awake(int progress) {

        addPos = new Vector2(
                global.blackSide.UnitConversion(10, BlackSide.width),
                global.blackSide.UnitConversion(100, BlackSide.height)
        );

        EnginePart = new Bitmap[16];
        EngineFrame = new Bitmap[4];

        EngineCode = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.enginecode);

        EngineCode = Bitmap.createScaledBitmap(EngineCode,
                global.blackSide.UnitConversion(350),
                global.blackSide.UnitConversion(210),
                true);

        EngineFrame[0] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.makeengineframe1);
        EngineFrame[1] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.makeengineframe2);
        EngineFrame[2] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.makeengineframe3);
        EngineFrame[3] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.makeengineframe4);

        EngineFrame[0] = Bitmap.createScaledBitmap(EngineFrame[0],
                global.blackSide.UnitConversion(20),
                global.blackSide.UnitConversion(1081),
                true);
        EngineFrame[1] = Bitmap.createScaledBitmap(EngineFrame[1],
                global.blackSide.UnitConversion(20),
                global.blackSide.UnitConversion(1081),
                true);
        EngineFrame[2] = Bitmap.createScaledBitmap(EngineFrame[2],
                global.blackSide.UnitConversion(1881),
                global.blackSide.UnitConversion(30),
                true);
        EngineFrame[3] = Bitmap.createScaledBitmap(EngineFrame[3],
                global.blackSide.UnitConversion(1881),
                global.blackSide.UnitConversion(60),
                true);

        EnginePart[0] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.makeengine1);
        EnginePart[0] = Bitmap.createScaledBitmap(EnginePart[0],
                global.blackSide.UnitConversion(320),
                global.blackSide.UnitConversion(330),
                true);

        EnginePart[1] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.makeengine2);
        EnginePart[1] = Bitmap.createScaledBitmap(EnginePart[1],
                global.blackSide.UnitConversion(250),
                global.blackSide.UnitConversion(280),
                true);

        EnginePart[2] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.makeengine3);
        EnginePart[2] = Bitmap.createScaledBitmap(EnginePart[2],
                global.blackSide.UnitConversion(310),
                global.blackSide.UnitConversion(290),
                true);

        EnginePart[3] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.makeengine4);
        EnginePart[3] = Bitmap.createScaledBitmap(EnginePart[3],
                global.blackSide.UnitConversion(240),
                global.blackSide.UnitConversion(240),
                true);

        EnginePart[4] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.makeengine5);
        EnginePart[4] = Bitmap.createScaledBitmap(EnginePart[4],
                global.blackSide.UnitConversion(470),
                global.blackSide.UnitConversion(190),
                true);

        EnginePart[5] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.makeengine6);
        EnginePart[5] = Bitmap.createScaledBitmap(EnginePart[5],
                global.blackSide.UnitConversion(320),
                global.blackSide.UnitConversion(280)
                , true);

        EnginePart[6] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.makeengine7);
        EnginePart[6] = Bitmap.createScaledBitmap(EnginePart[6],
                global.blackSide.UnitConversion(310),
                global.blackSide.UnitConversion(280),
                true);

        EnginePart[7] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.makeengine8);
        EnginePart[7] = Bitmap.createScaledBitmap(EnginePart[7],
                global.blackSide.UnitConversion(210),
                global.blackSide.UnitConversion(250),
                true);

        EnginePart[8] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.makeengine9);
        EnginePart[8] = Bitmap.createScaledBitmap(EnginePart[8],
                global.blackSide.UnitConversion(220),
                global.blackSide.UnitConversion(100),
                true);

        EnginePart[9] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.makeengine10);
        EnginePart[9] = Bitmap.createScaledBitmap(EnginePart[9],
                global.blackSide.UnitConversion(310),
                global.blackSide.UnitConversion(340),
                true);

        EnginePart[10] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.makeengine11);
        EnginePart[10] = Bitmap.createScaledBitmap(EnginePart[10],
                global.blackSide.UnitConversion(380),
                global.blackSide.UnitConversion(350),
                true);

        EnginePart[11] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.makeengine12);
        EnginePart[11] = Bitmap.createScaledBitmap(EnginePart[11],
                global.blackSide.UnitConversion(390),
                global.blackSide.UnitConversion(370),
                true);

        EnginePart[12] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.makeengine13);
        EnginePart[12] = Bitmap.createScaledBitmap(EnginePart[12],
                global.blackSide.UnitConversion(190),
                global.blackSide.UnitConversion(350),
                true);

        EnginePart[13] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.makeengine14);
        EnginePart[13] = Bitmap.createScaledBitmap(EnginePart[13],
                global.blackSide.UnitConversion(180),
                global.blackSide.UnitConversion(350),
                true);

        EnginePart[14] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.makeengine15);
        EnginePart[14] = Bitmap.createScaledBitmap(EnginePart[14],
                global.blackSide.UnitConversion(710),
                global.blackSide.UnitConversion(430),
                true);

        EnginePart[15] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.makeengine16);
        EnginePart[15] = Bitmap.createScaledBitmap(EnginePart[15],
                global.blackSide.UnitConversion(800),
                global.blackSide.UnitConversion(440),
                true);

        PartPosition = new Vector2[17];

        for (int i = 0; i < PartPosition.length; i++)
            PartPosition[i] = new Vector2(-500, -500);

        EndPosition = new Vector2[17];

        EndPosition[0] = new Vector2(50, 28);
        EndPosition[1] = new Vector2(107, 28);
        EndPosition[2] = new Vector2(61, 33);
        EndPosition[3] = new Vector2(115, 28);
        EndPosition[4] = new Vector2(102, 32);
        EndPosition[5] = new Vector2(99, 35);
        EndPosition[6] = new Vector2(89, 33);
        EndPosition[7] = new Vector2(65, 38);
        EndPosition[8] = new Vector2(63, 37);
        EndPosition[9] = new Vector2(57, 38);
        EndPosition[10] = new Vector2(84, 33);
        EndPosition[11] = new Vector2(51, 48);
        EndPosition[12] = new Vector2(53, 41);
        EndPosition[13] = new Vector2(71, 31);
        EndPosition[14] = new Vector2(47, 35);
        EndPosition[15] = new Vector2(112, 28);
        EndPosition[16] = new Vector2(48, 28);

        for (int i = 0; i < EndPosition.length; i++) {
            EndPosition[i].x = global.blackSide.UnitConversion(EndPosition[i].x * 10) + addPos.x;
            EndPosition[i].y = global.blackSide.UnitConversion(EndPosition[i].y * 10) + addPos.y;
        }

        return success;
    }

    @Override
    public void Start() {

    }

    @Override
    public void LoadUpdate() {

        if (End)
            return;

        PartPosition[orderNumber].x += (Math.abs(PartPosition[orderNumber].x - EndPosition[orderNumber].x) / 4);
        PartPosition[orderNumber].y += (Math.abs(PartPosition[orderNumber].y - EndPosition[orderNumber].y) / 4);

        if (PartPosition[orderNumber].ForDistance(EndPosition[orderNumber]) < global.blackSide.UnitConversion(40)) {
            PartPosition[orderNumber].setVector(EndPosition[orderNumber]);
            orderNumber++;
            if (orderNumber > PartPosition.length - 1) {
                orderNumber = PartPosition.length - 1;
                End = true;
            }
        }

    }

    @Override
    public void LoadRender(Canvas canvas) {
        canvas.drawColor(Color.rgb(165, 171, 173));

        GridRender(canvas);

        PartRender(canvas);

        FrameRender(canvas);

        canvas.drawBitmap(EngineCode,
                global.blackSide.UnitConversion(50,BlackSide.width),
                global.blackSide.UnitConversion(60,BlackSide.height),
                null);
    }

    private void GridRender(Canvas canvas){
        // 세로선
        for(int i = global.blackSide.UnitConversion(40,BlackSide.width);
                i < global.blackSide.UnitConversion(1920,BlackSide.width);
                i += global.blackSide.UnitConversion(40)){
            canvas.drawRect(
                    i,
                    global.blackSide.UnitConversion(0, BlackSide.height),
                    i+global.blackSide.UnitConversion(10),
                    global.blackSide.UnitConversion(1081, BlackSide.height),
                    paint);
        }
        // 가로선
        for(int i = global.blackSide.UnitConversion(40,BlackSide.height);
            i < global.blackSide.UnitConversion(1081,BlackSide.height);
            i += global.blackSide.UnitConversion(40)){
            canvas.drawRect(
                    global.blackSide.UnitConversion(0, BlackSide.width),
                    i,
                    global.blackSide.UnitConversion(1921, BlackSide.width),
                    i+global.blackSide.UnitConversion(10),
                    paint);
        }
    }

    private void FrameRender(Canvas canvas){
        canvas.drawBitmap(EngineFrame[0],
                global.blackSide.UnitConversion(0,BlackSide.width),
                global.blackSide.UnitConversion(0,BlackSide.height),
                null);

        canvas.drawBitmap(EngineFrame[1],
                global.blackSide.UnitConversion(1920,BlackSide.width) - EngineFrame[1].getWidth(),
                global.blackSide.UnitConversion(0,BlackSide.height),
                null);

        canvas.drawBitmap(EngineFrame[2],
                global.blackSide.UnitConversion(0,BlackSide.width) + EngineFrame[0].getWidth(),
                global.blackSide.UnitConversion(0,BlackSide.height),
                null);

        canvas.drawBitmap(EngineFrame[3],
                global.blackSide.UnitConversion(0,BlackSide.width) + EngineFrame[0].getWidth(),
                global.blackSide.UnitConversion(1080,BlackSide.height) - EngineFrame[3].getHeight(),
                null);
    }

    private void PartRender(Canvas canvas){
        canvas.drawBitmap(EnginePart[14],
                PartPosition[0].x,
                PartPosition[0].y,
                null);
        canvas.drawBitmap(EnginePart[12],
                PartPosition[1].x,
                PartPosition[1].y,
                null);
        canvas.drawBitmap(EnginePart[10],
                PartPosition[2].x,
                PartPosition[2].y,
                null);
        canvas.drawBitmap(EnginePart[0],
                PartPosition[3].x,
                PartPosition[3].y,
                null);
        canvas.drawBitmap(EnginePart[1],
                PartPosition[4].x,
                PartPosition[4].y,
                null);
        canvas.drawBitmap(EnginePart[3],
                PartPosition[5].x,
                PartPosition[5].y,
                null);
        canvas.drawBitmap(EnginePart[2],
                PartPosition[6].x,
                PartPosition[6].y,
                null);
        canvas.drawBitmap(EnginePart[4],
                PartPosition[7].x,
                PartPosition[7].y,
                null);
        canvas.drawBitmap(EnginePart[5],
                PartPosition[8].x,
                PartPosition[8].y,
                null);
        canvas.drawBitmap(EnginePart[2],
                PartPosition[9].x,
                PartPosition[9].y,
                null);
        canvas.drawBitmap(EnginePart[6],
                PartPosition[10].x,
                PartPosition[10].y,
                null);
        canvas.drawBitmap(EnginePart[8],
                PartPosition[11].x,
                PartPosition[11].y,
                null);
        canvas.drawBitmap(EnginePart[7],
                PartPosition[12].x,
                PartPosition[12].y,
                null);
        canvas.drawBitmap(EnginePart[11],
                PartPosition[13].x,
                PartPosition[13].y,
                null);
        canvas.drawBitmap(EnginePart[9],
                PartPosition[14].x,
                PartPosition[14].y,
                null);
        canvas.drawBitmap(EnginePart[13],
                PartPosition[15].x,
                PartPosition[15].y,
                null);
        canvas.drawBitmap(EnginePart[15],
                PartPosition[16].x,
                PartPosition[16].y,
                null);
    }

    @Override
    public void Destroy() {

        for (int i = 0; i < EnginePart.length; i++)
            EnginePart[i].recycle();
        EnginePart = null;

        for (int i = 0; i < EngineFrame.length; i++)
            EngineFrame[i].recycle();
        EngineFrame = null;

        EngineCode = null;

        for (int i = 0; i < PartPosition.length; i++)
            PartPosition[i] = null;
        PartPosition = null;

        for (int i = 0; i < EndPosition.length; i++)
            EndPosition[i] = null;
        EndPosition = null;
    }
}
