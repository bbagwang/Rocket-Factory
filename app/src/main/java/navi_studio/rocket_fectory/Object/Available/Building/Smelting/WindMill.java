package navi_studio.rocket_fectory.Object.Available.Building.Smelting;

import android.graphics.Bitmap;

import navi_studio.rocket_fectory.Action.Manager.ActionManager;
import navi_studio.rocket_fectory.Function.LocaleManager;
import navi_studio.rocket_fectory.Function.Vector2;
import navi_studio.rocket_fectory.Object.Basic.Building;
import navi_studio.rocket_fectory.Object.Basic.BuildingType.Smelting;
import navi_studio.rocket_fectory.Object.Core.ObjectCode;
import navi_studio.rocket_fectory.Part.ItemCell;

public class WindMill extends Smelting {

    int[] possibleItem = new int[1];

    public WindMill() {
        possibleItem[0] = ObjectCode.Wheat;
    }

    public void Create(Vector2 pos, int size, Bitmap[] AnimationImage, Bitmap[] _image) {

        Create(pos, size, AnimationImage,
                _image,100,
                3,1,
                ObjectCode.BuildingWindMill,
                Building.SpeedLevel1,
                possibleItem );

        StartCommandSetting();

        ItemCell[] temp;
        temp = new ItemCell[2];

        temp[0] = new ItemCell(ObjectCode.Wood, 7, ItemCell.Material);
        temp[1] = new ItemCell(ObjectCode.WoodenGear, 3, ItemCell.Parts);

        RequiredItemSetting(temp);

    }
}
