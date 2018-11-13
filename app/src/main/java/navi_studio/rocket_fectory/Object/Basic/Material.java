package navi_studio.rocket_fectory.Object.Basic;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import navi_studio.rocket_fectory.Function.Vector2;
import navi_studio.rocket_fectory.Global.Camera;
import navi_studio.rocket_fectory.Global.Global;
import navi_studio.rocket_fectory.Global.Target;
import navi_studio.rocket_fectory.Object.Core.Object;

/**
 * Created by beanb on 2018-04-02.
 */

public class Material extends Object {

    public final static int TileSize = 200;        // 애니메이션 없음, 왼쪽 상단기준

    Bitmap image;
    int cellSize;

    protected void Create(Vector2 _pos, int _cellSize, int _setCode, Bitmap _image, Global global) {
        position = _pos;
        cellSize = _cellSize;

        image = _image;

        size = global.blackSide.UnitConversion(TileSize) * cellSize;
        type = MATERIAL;
        setCode(_setCode);
    }

    @Override
    public void Render(Canvas canvas, Camera camera) {
        canvas.drawBitmap(image,
                position.x - (image.getWidth() / 2) - (camera.getPos().x - (camera.getSizeWidth() / 2)),
                position.y - (image.getHeight() / 2) - (camera.getPos().y - (camera.getSizeHeight() / 2)), null);
    }
}
