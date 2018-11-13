package navi_studio.rocket_fectory.Function;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import navi_studio.rocket_fectory.Global.Camera;
import navi_studio.rocket_fectory.Global.FPS;

/**
 * Created by beanb on 2018-03-25.
 */

public class Animation {

    public final static int BASIC = 100;        // 애니메이션 없음, 왼쪽 상단기준
    public final static int CENTER = 101;        // 중앙기준

    protected Bitmap image[];                                // 기본 이미지

    protected int count;                                   // 애니메이션 프레임 위치
    protected float Max_Frame_count;                       // fpsSetting / fps (디바이스 30프레임에 애니메이션 10프레임이면 3)
    protected float Frame_count;                           // 프레임 스택
    protected int FrameSpeed;                              // 초당 넘어갈 프레임 속도

    protected Vector2 Position;
    protected Vector2 Size;

    private boolean Run = true;
    private boolean hide = false;

    private int MaxCount;

    public void Create(int _PosMode, int _PosX, int _PosY, int _SizeX, int _SizeY, Bitmap[] _image, int _FrameSpeed) {

        Position = new Vector2();
        Size = new Vector2();
        switch (_PosMode) {
            // 그림의 위치는 왼쪽 상단을 기준으로 저장됩니다.
            case BASIC:
                Size.x = _SizeX;
                Size.y = _SizeY;
                Position.x = _PosX + (Size.x / 2);
                Position.y = _PosY + (Size.y / 2);
                break;
            // 그림의 위치는 중앙을 기준으로 저장됩니다.
            case CENTER:
                Size.x = _SizeX;
                Size.y = _SizeY;
                Position.x = _PosX;
                Position.y = _PosY;
                break;
        }
        FrameSpeed = _FrameSpeed;
        Max_Frame_count = FPS.fpsSetting / FrameSpeed;
        Frame_count = 0;
        count = 0;

        image = _image;

        MaxCount = image.length;

        for (int i = 0; i < image.length; i++) {
            image[i] = Bitmap.createScaledBitmap(_image[i], Size.x, Size.y, true);
        }
    }

    public void Create(int _PosMode, int _PosX, int _PosY, Bitmap[] _image, int _FrameSpeed) {

        Position = new Vector2();
        Size = new Vector2();
        switch (_PosMode) {
            // 그림의 위치는 왼쪽 상단을 기준으로 저장됩니다.
            case BASIC:
                Size.x = _image[0].getWidth();
                Size.y = _image[0].getHeight();
                Position.x = _PosX + (Size.x / 2);
                Position.y = _PosY + (Size.y / 2);
                break;
            // 그림의 위치는 중앙을 기준으로 저장됩니다.
            case CENTER:
                Size.x = _image[0].getWidth();
                Size.y = _image[0].getHeight();
                Position.x = _PosX;
                Position.y = _PosY;
                break;
        }
        FrameSpeed = _FrameSpeed;
        Max_Frame_count = FPS.fpsSetting / FrameSpeed;
        Frame_count = 0;
        count = 0;

        image = _image;

        MaxCount = image.length;
    }

    public void Create(int _PosMode, int _PosX, int _PosY, int _SizeX, int _SizeY, Bitmap[] _image, int _FrameSpeed, int _extra) {
        Create(_PosMode, _PosX, _PosY, _SizeX, _SizeY, _image, _FrameSpeed);
        MaxCount = _extra;
    }

    public void Create(int _PosMode, int _PosX, int _PosY, Bitmap[] _image, int _FrameSpeed, int _extra) {
        Create(_PosMode, _PosX, _PosY, _image, _FrameSpeed);
        MaxCount = _extra;
    }

    public void setPosition(int x, int y) {
        Position.x = x;
        Position.y = y;
    }

    public void stop() {
        Run = false;
    }

    public void start() {
        Run = true;
    }

    public boolean isRun() {
        return Run;
    }

    public void hide() {
        hide = true;
    }

    public void seek() {
        hide = false;
    }

    public boolean isHide() {
        return hide;
    }

    public Vector2 getPosition() {
        return Position;
    }

    public void Render(Canvas canvas) {
        if (hide)
            return;
        counter();

        if (count >= image.length)
            canvas.drawBitmap(image[image.length - 1],
                    Position.x - (Size.x / 2),
                    Position.y - (Size.y / 2), null);
        else
            canvas.drawBitmap(image[count],
                    Position.x - (Size.x / 2),
                    Position.y - (Size.y / 2), null);
    }

    public void Render(Canvas canvas, Camera camera) {
        if (hide)
            return;
        counter();

        if (count >= image.length)
            canvas.drawBitmap(image[image.length - 1],
                    Position.x - (Size.x / 2) - (camera.getPos().x - (camera.getSizeWidth() / 2)),
                    Position.y - (Size.y / 2) - (camera.getPos().y - (camera.getSizeHeight() / 2)), null);
        else
            canvas.drawBitmap(image[count],
                    Position.x - (Size.x / 2) - (camera.getPos().x - (camera.getSizeWidth() / 2)),
                    Position.y - (Size.y / 2) - (camera.getPos().y - (camera.getSizeHeight() / 2)), null);
    }

    // 카운터
    public void counter() {
        if (Run) {
            Frame_count++;                                  // 프레임당 1씩 증가합니다.
            if (Frame_count > Max_Frame_count) {           // 프레임 카운터가 Max_Frame_count보다 크면 통과
                Frame_count -= Max_Frame_count;           // 프레임 카운터를 Max_Frame_count만큼 뺀다.

                //  카운터
                count++;
                if (count >= MaxCount) {
                    count = 0;
                }
            }
        }
    }

    // 이미지 크기 재설정
    public void resize(float size) {
        for (int i = 0; i < image.length; i++)
            image[i] = Bitmap.createScaledBitmap(image[i], (int) (Size.x * size), (int) (Size.y * size), true);
    }

    public void Destroy() {
        for (int i = 0; i < image.length; i++)
            image[i].recycle();
        image = null;
    }

}
