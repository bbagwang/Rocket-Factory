package navi_studio.rocket_fectory.Object.Available.Building.Smelting;

import android.graphics.Bitmap;

import navi_studio.rocket_fectory.Action.Manager.ActionManager;
import navi_studio.rocket_fectory.Function.LocaleManager;
import navi_studio.rocket_fectory.Function.Vector2;
import navi_studio.rocket_fectory.Object.Basic.Building;
import navi_studio.rocket_fectory.Object.Basic.BuildingType.Smelting;
import navi_studio.rocket_fectory.Object.Core.ObjectCode;
import navi_studio.rocket_fectory.Part.ItemCell;

public class BrazierHigh extends Smelting {

    int[] possibleItem = new int[3];

    public BrazierHigh() {
        possibleItem[0] = ObjectCode.Iron;
        possibleItem[1] = ObjectCode.Anvil;
        possibleItem[2] = ObjectCode.Pail;
    }

    public void Create(Vector2 pos, int size, Bitmap[] AnimationImage, Bitmap[] _image) {

        Create(pos, size, AnimationImage,
                _image, 100,
                2, 1,
                ObjectCode.BuildingBrazierHigh,
                Building.SpeedLevel1,
                possibleItem);

        StartCommandSetting();

        ItemCell[] temp;
        temp = new ItemCell[4];

        temp[0] = new ItemCell(ObjectCode.Coal, 1, ItemCell.RawMaterials);
        temp[1] = new ItemCell(ObjectCode.Stone, 3, ItemCell.RawMaterials);
        temp[2] = new ItemCell(ObjectCode.Dirt, 5, ItemCell.RawMaterials);
        temp[3] = new ItemCell(ObjectCode.Tool, 1, ItemCell.Consumption);

        RequiredItemSetting(temp);

    }
}
