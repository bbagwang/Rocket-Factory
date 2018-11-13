package navi_studio.rocket_fectory.Part;

import android.graphics.Bitmap;

import navi_studio.rocket_fectory.Global.Global;

public class ItemCell {

    public final static int RawMaterials = 0;          // 미가공 아이템
    public final static int Material = 1;              // 제련 아이템
    public final static int Parts = 2;                 // 조합 아이템
    public final static int Consumption = 3;           // 소모성 아이템
    public final static int Special = 4;               // 특수 아이템


    public int code;                // 코드
    public int number;              // 개수
    public int weight;              // 무게
    public int type;                // 분류
    public int time;                // 시간

    public Bitmap image;            // 이미지

    public ItemCell() {

    }


    public ItemCell(int _code, int _size, int _type) {
        code = _code;
        number = _size;
        weight = _size;
        type = _type;
        time = 0;
        image = null;
    }

    public ItemCell(int _code, int _size, int _type, int _time, Bitmap _image, Global global) {
        code = _code;
        number = _size;
        weight = _size;
        type = _type;
        time = _time;
        image = _image;

        image = Bitmap.createScaledBitmap(image, global.blackSide.UnitConversion(220), global.blackSide.UnitConversion(220), true);

    }

}
