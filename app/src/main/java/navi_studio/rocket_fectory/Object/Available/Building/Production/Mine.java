package navi_studio.rocket_fectory.Object.Available.Building.Production;

import android.graphics.Bitmap;

import navi_studio.rocket_fectory.Action.Manager.ActionManager;
import navi_studio.rocket_fectory.Function.LocaleManager;
import navi_studio.rocket_fectory.Function.Vector2;
import navi_studio.rocket_fectory.Object.Basic.Building;
import navi_studio.rocket_fectory.Object.Basic.BuildingType.Production;
import navi_studio.rocket_fectory.Object.Core.ObjectCode;
import navi_studio.rocket_fectory.Part.ItemCell;

public class Mine extends Production {

    public int[] AvailableMaterial = new int[4];

    public Mine() {
        AvailableMaterial[0] = ObjectCode.Stone;
        AvailableMaterial[1] = ObjectCode.Dirt;
        AvailableMaterial[2] = ObjectCode.IronStone;
        AvailableMaterial[3] = ObjectCode.Coal;

        AvailableMaterialSetting(AvailableMaterial);
    }

    public void Create(Vector2 pos, int size, Bitmap[] AnimationImage, Bitmap[] _image) {
        Create(pos, size, AnimationImage,
                _image,100,
                0,1,
                ObjectCode.BuildingMine,
                Building.SpeedLevel1);

        StartCommandSetting();

        ItemCell[] temp;
        temp = new ItemCell[3];

        temp[0] = new ItemCell(ObjectCode.Coal, 2, ItemCell.RawMaterials);
        temp[1] = new ItemCell(ObjectCode.Wood, 3, ItemCell.Material);
        temp[2] = new ItemCell(ObjectCode.IronTool, 1, ItemCell.Consumption);

        RequiredItemSetting(temp);
    }
}
