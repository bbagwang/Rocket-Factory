package navi_studio.rocket_fectory.Object.Available.Material;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import navi_studio.rocket_fectory.Function.Vector2;
import navi_studio.rocket_fectory.Global.Global;
import navi_studio.rocket_fectory.Object.Basic.Material;
import navi_studio.rocket_fectory.Object.Core.ObjectCode;
import navi_studio.rocket_fectory.R;

public class Coal extends Material {

    public void Create(Vector2 _pos, int _size, Global global) {

        Bitmap bitmap = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.tilecoal);
        bitmap = Bitmap.createScaledBitmap(bitmap,
                global.blackSide.UnitConversion(528),
                global.blackSide.UnitConversion(272), true);

        Create(_pos, _size, ObjectCode.Coal, bitmap, global);
    }
}
