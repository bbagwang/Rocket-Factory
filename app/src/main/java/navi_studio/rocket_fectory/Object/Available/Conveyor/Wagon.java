package navi_studio.rocket_fectory.Object.Available.Conveyor;

import navi_studio.rocket_fectory.Function.Vector2;
import navi_studio.rocket_fectory.Global.FPS;
import navi_studio.rocket_fectory.Global.Global;
import navi_studio.rocket_fectory.Object.Basic.Building;
import navi_studio.rocket_fectory.Object.Basic.Conveyor;
import navi_studio.rocket_fectory.Object.Core.ObjectCode;
import navi_studio.rocket_fectory.Part.ItemCell;

public class Wagon extends Conveyor {

    @Override
    public void Create(Building _start, Building _target, Global global) {
        Create(_start, _target,
                global.blackSide.UnitConversion(30),
                1,
                160, 160, 0,
                ObjectCode.ConveyorWagon,
                WagonSize, global
        );

        ItemCell[] temp;
        temp = new ItemCell[1];

        temp[0] = new ItemCell(ObjectCode.Wagon, (int) _start.getPos().ForDistance(_target.getPos()) / 200, ItemCell.Consumption);

        RequiredItemSetting(temp);
    }
}
