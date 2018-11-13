package navi_studio.rocket_fectory.Object.Available.Conveyor.Manager;

import java.util.ArrayList;

import navi_studio.rocket_fectory.Global.Global;
import navi_studio.rocket_fectory.Object.Available.Conveyor.Wagon;
import navi_studio.rocket_fectory.Object.Basic.Conveyor;

public class ConveyorCode {

    public Conveyor getConveyor(int Level){
        switch (Level) {
            case 1:
                return new Wagon();
        }
        return null;
    }
}
