package navi_studio.rocket_fectory.InGame;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import navi_studio.rocket_fectory.Function.Vector2;
import navi_studio.rocket_fectory.Global.Global;
import navi_studio.rocket_fectory.Object.Available.Material.Coal;
import navi_studio.rocket_fectory.Object.Available.Material.Dirt;
import navi_studio.rocket_fectory.Object.Available.Material.IronStone;
import navi_studio.rocket_fectory.Object.Available.Material.Stone;
import navi_studio.rocket_fectory.Object.Available.Material.Water;
import navi_studio.rocket_fectory.Object.Available.Material.Wood;
import navi_studio.rocket_fectory.Object.Manager.ObjManager;
import navi_studio.rocket_fectory.R;

public class MapCreator {

    ObjManager objManager;
    Global global;

    public MapCreator(ObjManager _objManager, Global _global) {
        objManager = _objManager;
        global = _global;
    }

    public void Create() {
        Wood TestWood01;
        TestWood01 = new Wood();

        TestWood01.Create(
                new Vector2(
                        global.blackSide.UnitConversion(800),
                        global.blackSide.UnitConversion(1200))
                , 2,global);

        objManager.add(TestWood01);
        // ============================================================================================= //
        Stone TestStone01;
        TestStone01 = new Stone();

        TestStone01.Create(
                new Vector2(
                        global.blackSide.UnitConversion(1500),
                        global.blackSide.UnitConversion(1100))
                , 2,global);

        objManager.add(TestStone01);
        // ============================================================================================= //
        Dirt TestDirt01;
        TestDirt01 = new Dirt();

        TestDirt01.Create(
                new Vector2(
                        global.blackSide.UnitConversion(0000),
                        global.blackSide.UnitConversion(0000))
                , 2,global);

        objManager.add(TestDirt01);
        // ============================================================================================= //
        IronStone TestIronStone01;
        TestIronStone01 = new IronStone();

        TestIronStone01.Create(
                new Vector2(
                        global.blackSide.UnitConversion(1600),
                        global.blackSide.UnitConversion(0300))
                , 2,global);

        objManager.add(TestIronStone01);
        // ============================================================================================= //
        Coal TestCoal01;
        TestCoal01 = new Coal();

        TestCoal01.Create(
                new Vector2(
                        global.blackSide.UnitConversion(800),
                        global.blackSide.UnitConversion(0100))
                , 2,global);

        objManager.add(TestCoal01);
        // ============================================================================================= //
        Water TestWater01;
        TestWater01 = new Water();

        TestWater01.Create(
                new Vector2(
                        global.blackSide.UnitConversion(0200),
                        global.blackSide.UnitConversion(0700))
                , 3,global);

        objManager.add(TestWater01);
    }

}
