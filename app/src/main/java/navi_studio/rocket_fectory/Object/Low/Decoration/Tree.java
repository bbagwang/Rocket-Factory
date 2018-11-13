package navi_studio.rocket_fectory.Object.Low.Decoration;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import navi_studio.rocket_fectory.Function.Animation;
import navi_studio.rocket_fectory.Function.Vector2;
import navi_studio.rocket_fectory.Global.Global;
import navi_studio.rocket_fectory.Object.Low.Decoration.Core.Base;
import navi_studio.rocket_fectory.R;

public class Tree extends Base {

    public void Create(Vector2 _position, Global global) {

        Bitmap bitmap = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.wood);
        bitmap = Bitmap.createScaledBitmap(bitmap,
                global.blackSide.UnitConversion(200),
                global.blackSide.UnitConversion(200), true);

        Create(bitmap,_position);
    }
}
