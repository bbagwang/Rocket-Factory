package navi_studio.rocket_fectory.Object.Low.Decoration.Core;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import navi_studio.rocket_fectory.Function.Animation;
import navi_studio.rocket_fectory.Function.Vector2;
import navi_studio.rocket_fectory.Global.Camera;
import navi_studio.rocket_fectory.Object.Core.ObjLow;

public class Base extends ObjLow {

    private Bitmap bitmap;
    private Vector2 position;

    public void Create(Bitmap _bitmap,Vector2 _position) {
        bitmap = _bitmap;
        position = _position;
    }

    @Override
    public void Render(Canvas canvas, Camera camera) {
        canvas.drawBitmap(bitmap,
                position.x - (bitmap.getWidth() / 2) - (camera.getPos().x - (camera.getSizeWidth() / 2)),
                position.y - (bitmap.getHeight() / 2) - (camera.getPos().y - (camera.getSizeHeight() / 2)), null);
    }

}
