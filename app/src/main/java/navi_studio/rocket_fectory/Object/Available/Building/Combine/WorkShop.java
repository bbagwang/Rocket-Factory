package navi_studio.rocket_fectory.Object.Available.Building.Combine;

import android.graphics.Bitmap;

import navi_studio.rocket_fectory.Action.Manager.ActionManager;
import navi_studio.rocket_fectory.Function.LocaleManager;
import navi_studio.rocket_fectory.Function.Vector2;
import navi_studio.rocket_fectory.Object.Basic.Building;
import navi_studio.rocket_fectory.Object.Basic.BuildingType.Combine;
import navi_studio.rocket_fectory.Object.Core.ObjectCode;
import navi_studio.rocket_fectory.Part.ItemCell;

public class WorkShop extends Combine {

    int[] possibleItem;

    public WorkShop() {
        possibleItem = new int[4];

        possibleItem[0] = ObjectCode.Wood;
        possibleItem[1] = ObjectCode.Tool;
        possibleItem[2] = ObjectCode.Wagon;
        possibleItem[3] = ObjectCode.BronzeMirror;
    }

    public void Create(Vector2 pos, int size, Bitmap[] AnimationImage, Bitmap[] _image) {

        Create(pos, size, AnimationImage,
                _image,100,
                2,1,
                ObjectCode.BuildingWorkShop,
                Building.SpeedLevel1,
                possibleItem);

        StartCommandSetting();

        ItemCell[] temp;
        temp = new ItemCell[5];

        temp[0] = new ItemCell(ObjectCode.Log, 5, ItemCell.RawMaterials);
        temp[1] = new ItemCell(ObjectCode.Wood, 3, ItemCell.Material);
        temp[2] = new ItemCell(ObjectCode.Stone, 2, ItemCell.RawMaterials);
        temp[3] = new ItemCell(ObjectCode.Tool, 2, ItemCell.Consumption);
        temp[4] = new ItemCell(ObjectCode.Iron, 2, ItemCell.Material);

        RequiredItemSetting(temp);
    }
}
