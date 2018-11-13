package navi_studio.rocket_fectory.Function;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class GaugeBar {

    Bitmap GaugeImage;
    Bitmap BackGroundImage;
    Bitmap FrameImage;

    Vector2 position;

    int Value;
    int Max;
    int blankSize;
    int count;

    // 생성
    public void Create(Bitmap gaugeImage, Bitmap backGroundImage, Bitmap frameImage, Vector2 _position, int _Max, boolean isMax, int _blankSize) {
        position = _position;
        GaugeImage = gaugeImage;
        BackGroundImage = backGroundImage;
        FrameImage = frameImage;

        Max = _Max;
        blankSize = _blankSize;

        if (isMax) {
            Value = Max;
            count = (BackGroundImage.getWidth() - (blankSize * 2) + GaugeImage.getWidth()) / GaugeImage.getWidth();
        } else if (!isMax) {
            Value = 0;
            count = 0;
        }
    }

    public void fill(){
        Value = Max;
        count = (BackGroundImage.getWidth() - (blankSize * 2) + GaugeImage.getWidth()) / GaugeImage.getWidth();
    }

    public void clear(){
        Value = 0;
        count = 0;
    }

    public int getValue() {
        return Value;
    }

    public void setValue(int value) {
        Value = value;
        temp = (Max / ((BackGroundImage.getWidth() - (blankSize * 2)) / GaugeImage.getWidth()));
        if(temp < 1)
            temp = 1;

        count = Value / temp;
    }

    int temp;

    // 값 추가
    public void addValue(int value) {
        Value += value;
        if (Value < 0)
            Value = 0;
        else if (Value > Max)
            Value = Max;

        temp = (Max / ((BackGroundImage.getWidth() - (blankSize * 2)) / GaugeImage.getWidth()));
        if(temp < 1)
            temp = 1;

        count = Value / temp;
    }

    // 채력 바 랜더
    private void GaugeRender(Canvas canvas) {

        for (int i = 0; i < count; i++)
            canvas.drawBitmap(GaugeImage,
                    position.x + blankSize + (i * GaugeImage.getWidth()),
                    position.y + (BackGroundImage.getHeight() / 2) - (GaugeImage.getHeight() / 2), null);

    }

    public void Render(Canvas canvas) {
        // 배경 랜더
        canvas.drawBitmap(BackGroundImage, position.x, position.y, null);

        GaugeRender(canvas);

        // 프레임 렌더
        canvas.drawBitmap(FrameImage, position.x, position.y, null);
    }

}
