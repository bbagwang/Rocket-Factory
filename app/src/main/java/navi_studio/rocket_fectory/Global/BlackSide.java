package navi_studio.rocket_fectory.Global;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by beanb on 2018-03-11.
 */

public class BlackSide {
    // 화면크기 1920 * 1080, 화면비율 9:16를 기준으로 합니다.
    // 1920 * 1080 기준 텍스트 사이즈는 50입니다.
    // 1920 * 1080 기준 화면 크기 단위의 값은 1 입니다.

    // 현재는 가로 고정입니다.
    // 어플리케이션을 세로 고정으로 제작하실경우, X와 Y의 값을 교체하여 주세요.
    private final static int RatioX = 16;
    private final static int RatioY = 9;
    private final static int SettingSizeX = 1920;
    private final static int SettingSizeY = 1080;

    private final static boolean WidthMode = false;       // 가로보정 모드
    private final static boolean HeightMode = true;       // 세로보정 설정

    public final static boolean width = false;
    public final static boolean height = true;

    private float BlackSideWidth;                   // 블랙 사이드 가로
    private float BlackSideHeight;                  // 블랙 사이드 세로
    private float FontUnit;                          // 글자 크기
    private float ScreenRatioUnit;                  // 비율 단위 변수
    private boolean SettingMode;                    // 가로, 세로 보정 모드 설정
    private int ScreenSizeX;                         // 디바이스 화면 크기 가로
    private int ScreenSizeY;                         // 디바이스 화면 크기 가로
    private Paint paint;                              // BlackSide 랜더용 변수

    public int Width() {
        return (int) BlackSideWidth;
    }

    public int Height() {
        return (int) BlackSideHeight;
    }

    // BlackSide 값을 설정합니다.
    public void InitBlackSide(int Width, int Height) {

        paint = new Paint();
        paint.setColor(Color.BLACK);

        ScreenSizeX = Width;
        ScreenSizeY = Height;

        // 세로가 더 긴 경우
        if ((Width / RatioX) <= (Height / RatioY)) {
            SettingMode = WidthMode;
            ScreenRatioUnit = (Width / RatioX);
            BlackSideWidth = Width;
            FontUnit = (float) Width / SettingSizeX;

        }
        // 가로가 더 긴 경우
        else {
            SettingMode = HeightMode;
            ScreenRatioUnit = (Height / RatioY);
            BlackSideHeight = Height;
            FontUnit = (float) Height / SettingSizeY;
        }

        // BlackSide 설정
        if (SettingMode == WidthMode) {
            BlackSideHeight = (((ScreenRatioUnit * RatioY) - Height) / 2f);
            BlackSideWidth = 0;
            if (BlackSideHeight < 0)
                BlackSideHeight = -BlackSideHeight;
        } else {
            BlackSideWidth = (((ScreenRatioUnit * RatioX) - Width) / 2f);
            BlackSideHeight = 0;
            if (BlackSideWidth < 0)
                BlackSideWidth = -BlackSideWidth;
        }
    }

    public void BlackSideRender(Canvas canvas) {
        if (SettingMode == WidthMode) {
            canvas.drawRect(0, 0, ScreenSizeX, BlackSideHeight, paint);
            canvas.drawRect(0, ScreenSizeY - BlackSideHeight, ScreenSizeX, ScreenSizeY, paint);
        } else {
            canvas.drawRect(0, 0, BlackSideWidth, ScreenSizeY, paint);
            canvas.drawRect(ScreenSizeX - BlackSideWidth, 0, ScreenSizeX, ScreenSizeY, paint);
        }
    }

    public int UnitConversion(int Pixel, boolean Mode) {
        if (SettingMode == WidthMode) {
            if (Mode == height)
                return (int) (BlackSideHeight + (Pixel / (SettingSizeX / 100f)) * (ScreenSizeX / 100f));
            else
                return (int) ((Pixel / (SettingSizeX / 100f)) * (ScreenSizeX / 100f));
        } else {
            if (Mode == width)
                return (int) (BlackSideWidth + (Pixel / (SettingSizeY / 100f)) * (ScreenSizeY / 100f));
            else
                return (int) ((Pixel / (SettingSizeY / 100f)) * (ScreenSizeY / 100f));
        }
    }

    public int UnitConversion(int Pixel) {
        if (SettingMode == WidthMode) {
            return (int) ((Pixel / (SettingSizeX / 100f)) * (ScreenSizeX / 100f));
        }
        return (int) ((Pixel / (SettingSizeY / 100f)) * (ScreenSizeY / 100f));
    }

    public int FontUnitConversion(int FontSize) {
        return (int) (FontUnit * FontSize);
    }
}
