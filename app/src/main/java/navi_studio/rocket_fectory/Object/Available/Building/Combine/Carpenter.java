package navi_studio.rocket_fectory.Object.Available.Building.Combine;

import android.graphics.Bitmap;

import navi_studio.rocket_fectory.Action.Manager.ActionManager;
import navi_studio.rocket_fectory.Function.LocaleManager;
import navi_studio.rocket_fectory.Function.Vector2;
import navi_studio.rocket_fectory.Object.Basic.Building;
import navi_studio.rocket_fectory.Object.Basic.BuildingType.Combine;
import navi_studio.rocket_fectory.Object.Core.ObjectCode;
import navi_studio.rocket_fectory.Part.ItemCell;

public class Carpenter extends Combine {

    int[] possibleItem;

    public Carpenter() {
        possibleItem = new int[1];

        possibleItem[0] = ObjectCode.Wood;
    }

    public void Create(Vector2 pos, int size, Bitmap[] AnimationImage, Bitmap[] _image) {

        Create(pos, size, AnimationImage,
                _image, 100,
                1, 1,
                ObjectCode.BuildingCarpenter,
                Building.SpeedLevel1,
                possibleItem);

        StartCommandSetting();

        ItemCell[] temp;
        temp = new ItemCell[2];

        temp[0] = new ItemCell(ObjectCode.Wood, 3, ItemCell.Material);
        temp[1] = new ItemCell(ObjectCode.Tool, 1, ItemCell.Consumption);

        RequiredItemSetting(temp);
    }
}
