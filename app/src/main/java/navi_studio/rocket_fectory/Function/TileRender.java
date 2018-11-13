package navi_studio.rocket_fectory.Function;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import navi_studio.rocket_fectory.Global.BlackSide;
import navi_studio.rocket_fectory.Global.Global;
import navi_studio.rocket_fectory.R;

public class TileRender {

    Global global;
    final static int TILE_X = 350;
    final static int TILE_Y = 350;

    Bitmap Tile_Grass;
    Bitmap Tile_Stone;
    Bitmap Tile_Dirt;

    public Vector2 temp;

    public Vector2 genesis_position;               // TOP LEFT 에서 최초 생성될 타일의 위치
    public Vector2 top_left;                       // TOP LEFT
    public Vector2 current_origin_position;        // GENESIS POSITION으로 부터 움직여 생성된 현재의 TILE ORIGIN POINT Position
    public Vector2 move_count;                     //  움직인 타일 카운트

    int tile_count_x = 0;       // 만들 타일 개수 X
    int tile_count_y = 0;       // 만들 타일 개수 Y

    public void Create(Global _global) {

        global = _global;

        top_left = new Vector2();
        genesis_position = new Vector2();
        current_origin_position = new Vector2();
        move_count = new Vector2(0, 0);

        Tile_Grass = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.tile1);
        Tile_Grass = Bitmap.createScaledBitmap(Tile_Grass, global.blackSide.UnitConversion(TILE_X), global.blackSide.UnitConversion(TILE_Y), true);
        Tile_Stone = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.stonetile);
        Tile_Stone = Bitmap.createScaledBitmap(Tile_Stone, global.blackSide.UnitConversion(TILE_X), global.blackSide.UnitConversion(TILE_Y), true);
        Tile_Dirt = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.dirttile);
        Tile_Dirt = Bitmap.createScaledBitmap(Tile_Dirt, global.blackSide.UnitConversion(TILE_X), global.blackSide.UnitConversion(TILE_Y), true);

        genesis_position.x = top_left.x - TILE_X;
        genesis_position.y = top_left.y - TILE_Y;

        current_origin_position = genesis_position;

        tile_count_x += (global.screenSize.width / TILE_X) + 8;
        tile_count_y += (global.screenSize.height / TILE_Y) + 8;

        temp = new Vector2(global.camera.getPos().x,global.camera.getPos().y);

    }

    public boolean Update() {
        Top_Left_Updater();
        Move_Distance_Updater();
        return false;
    }

    public void Render(Canvas canvas) {
        for (int y = 0; y < tile_count_x; y++) {
            for (int x = 0; x < tile_count_x; x++) {
                canvas.drawBitmap(Tile_Grass,
                        global.blackSide.UnitConversion(top_left.x - (global.camera.getSizeWidth() / 2)- current_origin_position.x + (TILE_X * x) - TILE_X * 3),
                        global.blackSide.UnitConversion(top_left.y - (global.camera.getSizeHeight() / 2)- current_origin_position.y + (TILE_Y * y) - TILE_Y * 3), null);
            }
        }
    }

    public boolean Destroy() {
        return true;
    }

    void Top_Left_Updater() {

        top_left.x = temp.x - global.camera.getPos().x;
        top_left.y = temp.y - global.camera.getPos().y;
    }

    void Move_Distance_Updater() {

        //오른쪽 이동
        if (top_left.x > current_origin_position.x + TILE_X) {
            current_origin_position.x += TILE_X;
        }

        //왼쪽 이동
        if (top_left.x < current_origin_position.x) {
            current_origin_position.x -= TILE_X;
        }

        //윗쪽 이동
        if (top_left.y < current_origin_position.y) {
            current_origin_position.y -= TILE_X;
        }

        //아랫쪽 이동
        if (top_left.y > current_origin_position.y + TILE_Y) {
            current_origin_position.y += TILE_X;
        }
    }

}
