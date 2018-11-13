package navi_studio.rocket_fectory.Object.Available.Building.Distribution;

import android.graphics.Bitmap;

import navi_studio.rocket_fectory.Action.Manager.ActionManager;
import navi_studio.rocket_fectory.Function.LocaleManager;
import navi_studio.rocket_fectory.Function.Vector2;
import navi_studio.rocket_fectory.Object.Basic.Building;
import navi_studio.rocket_fectory.Object.Basic.BuildingType.Distribution;
import navi_studio.rocket_fectory.Object.Core.ObjectCode;
import navi_studio.rocket_fectory.Part.ItemCell;

public class DistributionCenter extends Distribution {

    public DistributionCenter() {

    }

    public void Create(Vector2 pos, int size, Bitmap[] AnimationImage, Bitmap[] _image) {

        Create(pos, size, AnimationImage,
                _image, 1000,
                2, 2,
                ObjectCode.BuildingDistributionCenter,
                Building.SpeedLevel1);

        StartCommandSetting();

        ItemCell[] temp;
        temp = new ItemCell[2];

        temp[0] = new ItemCell(ObjectCode.Log, 3, ItemCell.RawMaterials);
        temp[1] = new ItemCell(ObjectCode.Stone, 1, ItemCell.RawMaterials);

        RequiredItemSetting(temp);
    }
}
