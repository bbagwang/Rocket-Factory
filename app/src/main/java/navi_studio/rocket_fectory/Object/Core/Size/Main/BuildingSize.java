package navi_studio.rocket_fectory.Object.Core.Size.Main;

import navi_studio.rocket_fectory.Global.BitmapResource;
import navi_studio.rocket_fectory.Global.Global;
import navi_studio.rocket_fectory.Object.Core.Size.CombineSize;
import navi_studio.rocket_fectory.Object.Core.Size.ConsumptionSize;
import navi_studio.rocket_fectory.Object.Core.Size.DistributionSize;
import navi_studio.rocket_fectory.Object.Core.Size.ProductionSize;
import navi_studio.rocket_fectory.Object.Core.Size.SmeltingSize;
import navi_studio.rocket_fectory.Object.Core.Size.SpecialSize;

public class BuildingSize {

    private Global global;

    private ProductionSize production;
    private SmeltingSize smelting;
    private CombineSize combine;
    private DistributionSize distribution;
    private ConsumptionSize consumption;
    private SpecialSize special;

    public BuildingSize() {
        production = new ProductionSize();
        smelting = new SmeltingSize();
        combine = new CombineSize();
        distribution  = new DistributionSize();
        consumption = new ConsumptionSize();
        special = new SpecialSize();
    }

    public void Create(Global _global){
        global = _global;
    }

    public int getSize(int type, int number) {
        switch (type) {
            case BitmapResource.Production:
                return global.blackSide.UnitConversion(production.getSize(number));
            case BitmapResource.Smelting:
                return global.blackSide.UnitConversion(smelting.getSize(number));
            case BitmapResource.Combine:
                return global.blackSide.UnitConversion(combine.getSize(number));
            case BitmapResource.Distribution:
                return global.blackSide.UnitConversion(distribution.getSize(number));
            case BitmapResource.Consumption:
                return global.blackSide.UnitConversion(consumption.getSize(number));
            case BitmapResource.Special:
                return global.blackSide.UnitConversion(special.getSize(number));
            default:
                return 0;
        }
    }

}
