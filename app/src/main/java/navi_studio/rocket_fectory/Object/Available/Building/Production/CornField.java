package navi_studio.rocket_fectory.Object.Available.Building.Production;

import android.graphics.Bitmap;

import navi_studio.rocket_fectory.Action.Manager.ActionManager;
import navi_studio.rocket_fectory.Function.LocaleManager;
import navi_studio.rocket_fectory.Function.Vector2;
import navi_studio.rocket_fectory.Object.Basic.Building;
import navi_studio.rocket_fectory.Object.Basic.BuildingType.Production;
import navi_studio.rocket_fectory.Object.Core.ObjectCode;
import navi_studio.rocket_fectory.Part.ItemCell;

public class CornField extends Production {

    public int[] AvailableMaterial = new int[1];

    public CornField() {
        AvailableMaterial[0] = ObjectCode.Dirt;

        AvailableMaterialSetting(AvailableMaterial);
    }

    public void Create(Vector2 pos, int size, Bitmap[] AnimationImage, Bitmap[] _image) {
        Create(pos, size, AnimationImage,
                _image,100,
                1,1,
                ObjectCode.BuildingCornField,
                Building.SpeedLevel1);

        StartCommandSetting();

        ItemCell[] temp;
        temp = new ItemCell[3];

        temp[0] = new ItemCell(ObjectCode.Hoe, 1, ItemCell.Consumption);
        temp[1] = new ItemCell(ObjectCode.Dirt, 3, ItemCell.RawMaterials);
        temp[2] = new ItemCell(ObjectCode.WaterPail, 1, ItemCell.RawMaterials);

        RequiredItemSetting(temp);
    }
}
