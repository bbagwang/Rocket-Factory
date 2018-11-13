package navi_studio.rocket_fectory.Object.Available.Building.Consumption;

import android.graphics.Bitmap;

import navi_studio.rocket_fectory.Action.Manager.ActionManager;
import navi_studio.rocket_fectory.Function.LocaleManager;
import navi_studio.rocket_fectory.Function.Vector2;
import navi_studio.rocket_fectory.Object.Basic.Building;
import navi_studio.rocket_fectory.Object.Basic.BuildingType.Consumption;
import navi_studio.rocket_fectory.Object.Core.ObjectCode;
import navi_studio.rocket_fectory.Part.ItemCell;

public class Town extends Consumption {

    int[] possibleItem;

    public Town() {
        possibleItem = new int[1];

        possibleItem[0] = ObjectCode.BronzeMirror;
    }

    public void Create(Vector2 pos, int size, Bitmap[] AnimationImage, Bitmap[] _image) {

        Create(pos, size, AnimationImage,
                _image,
                1,
                ObjectCode.BuildingTown,
                Building.SpeedLevel1,
                possibleItem);

        StartCommandSetting();

        ItemCell[] temp;
        temp = new ItemCell[4];

        temp[0] = new ItemCell(ObjectCode.Log, 5, ItemCell.RawMaterials);
        temp[1] = new ItemCell(ObjectCode.Wood, 3, ItemCell.Material);
        temp[2] = new ItemCell(ObjectCode.Stone, 5, ItemCell.RawMaterials);
        temp[3] = new ItemCell(ObjectCode.Tool, 2, ItemCell.Consumption);

        RequiredItemSetting(temp);
    }

}
