package navi_studio.rocket_fectory.Object.Available.Building.Special;

import android.graphics.Bitmap;

import navi_studio.rocket_fectory.Action.Manager.ActionManager;
import navi_studio.rocket_fectory.Function.LocaleManager;
import navi_studio.rocket_fectory.Function.Vector2;
import navi_studio.rocket_fectory.Object.Basic.Building;
import navi_studio.rocket_fectory.Object.Core.ObjectCode;
import navi_studio.rocket_fectory.Part.ItemCell;

public class Statue extends Building {

    public Statue() {

    }

    public void Create(Vector2 pos, int size, Bitmap[] AnimationImage, Bitmap[] image) {
        Create(pos, size, AnimationImage, image,
        0,0, 0, ObjectCode.BuildingStatue, true);

        StartCommandSetting();

        ItemCell[] temp;
        temp = new ItemCell[2];

        temp[0] = new ItemCell(ObjectCode.Stone, 5, ItemCell.RawMaterials);
        temp[1] = new ItemCell(ObjectCode.Tool, 1, ItemCell.Consumption);

        RequiredItemSetting(temp);

    }
}
