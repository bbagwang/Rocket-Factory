package navi_studio.rocket_fectory.Object.Core;

import navi_studio.rocket_fectory.Function.Vector2;
import navi_studio.rocket_fectory.Global.BitmapResource;
import navi_studio.rocket_fectory.Global.Global;
import navi_studio.rocket_fectory.Object.Available.Building.Combine.Carpenter;
import navi_studio.rocket_fectory.Object.Available.Building.Combine.Smithy;
import navi_studio.rocket_fectory.Object.Available.Building.Combine.WorkShop;
import navi_studio.rocket_fectory.Object.Available.Building.Consumption.FoodDepots;
import navi_studio.rocket_fectory.Object.Available.Building.Consumption.Town;
import navi_studio.rocket_fectory.Object.Available.Building.Distribution.DistributionCenter;
import navi_studio.rocket_fectory.Object.Available.Building.Production.CornField;
import navi_studio.rocket_fectory.Object.Available.Building.Production.Log;
import navi_studio.rocket_fectory.Object.Available.Building.Production.Mine;
import navi_studio.rocket_fectory.Object.Available.Building.Production.Quarry;
import navi_studio.rocket_fectory.Object.Available.Building.Production.Well;
import navi_studio.rocket_fectory.Object.Available.Building.Smelting.BrazierHigh;
import navi_studio.rocket_fectory.Object.Available.Building.Smelting.BrazierLow;
import navi_studio.rocket_fectory.Object.Available.Building.Smelting.WindMill;
import navi_studio.rocket_fectory.Object.Available.Building.Special.Statue;
import navi_studio.rocket_fectory.Object.Basic.Building;
import navi_studio.rocket_fectory.Object.Basic.BuildingType.Combine;
import navi_studio.rocket_fectory.Object.Basic.BuildingType.Consumption;
import navi_studio.rocket_fectory.Object.Basic.BuildingType.Distribution;
import navi_studio.rocket_fectory.Object.Basic.BuildingType.Production;
import navi_studio.rocket_fectory.Object.Basic.BuildingType.Smelting;
import navi_studio.rocket_fectory.Object.Core.Code.CombineCode;
import navi_studio.rocket_fectory.Object.Core.Code.ConsumptionCode;
import navi_studio.rocket_fectory.Object.Core.Code.DistributionCode;
import navi_studio.rocket_fectory.Object.Core.Code.ProductionCode;
import navi_studio.rocket_fectory.Object.Core.Code.SmeltingCode;
import navi_studio.rocket_fectory.Object.Core.Code.SpecialCode;
import navi_studio.rocket_fectory.Object.Core.Size.Main.BuildingSize;

public class BuildingList {

    private Global global;
    private BuildingSize buildingSize;

    public BuildingList(Global _global) {
        global = _global;
        buildingSize = new BuildingSize();
    }

    public Building Creator(int type, int number, Vector2 pos) {
        switch (type) {
            case BitmapResource.Production:
                return getProduction(type, number, pos);
            case BitmapResource.Smelting:
                return getSmelting(type, number, pos);
            case BitmapResource.Combine:
                return getCombine(type, number, pos);
            case BitmapResource.Distribution:
                return getDistribution(type, number, pos);
            case BitmapResource.Consumption:
                return getConsumption(type, number, pos);
            case BitmapResource.Special:
                return getSpecial(type, number, pos);
            default:
                return null;
        }
    }

    // 생산공장
    public Production getProduction(int type, int number, Vector2 pos) {
        switch (number) {
            case ProductionCode.log:
                Log log = new Log();
                log.Create(pos, buildingSize.getSize(type, number),
                        global.bitmapResource.BuildingAnimation[type][number],
                        global.bitmapResource.BuildingImage[type][number]);
                return log;
            case ProductionCode.Stone:
                Quarry stone = new Quarry();
                stone.Create(pos, buildingSize.getSize(type, number),
                        global.bitmapResource.BuildingAnimation[type][number],
                        global.bitmapResource.BuildingImage[type][number]);
                return stone;
            case ProductionCode.CornField:
                CornField cornField = new CornField();
                cornField.Create(pos, buildingSize.getSize(type, number),
                        global.bitmapResource.BuildingAnimation[type][number],
                        global.bitmapResource.BuildingImage[type][number]);
                return cornField;
            case ProductionCode.Well:
                Well well = new Well();
                well.Create(pos, buildingSize.getSize(type, number),
                        global.bitmapResource.BuildingAnimation[type][number],
                        global.bitmapResource.BuildingImage[type][number]);
                return well;
            case ProductionCode.Mine:
                Mine mine = new Mine();
                mine.Create(pos, buildingSize.getSize(type, number),
                        global.bitmapResource.BuildingAnimation[type][number],
                        global.bitmapResource.BuildingImage[type][number]);
                return mine;
            default:
                return null;
        }
    }

    // 제련공장
    public Smelting getSmelting(int type, int number, Vector2 pos) {
        switch (number) {
            case SmeltingCode.BrazierLow:
                BrazierLow brazierLow = new BrazierLow();
                brazierLow.Create(pos, buildingSize.getSize(type, number),
                        global.bitmapResource.BuildingAnimation[type][number],
                        global.bitmapResource.BuildingImage[type][number]);
                return brazierLow;
            case SmeltingCode.BrazierHigh:
                BrazierHigh brazierHigh = new BrazierHigh();
                brazierHigh.Create(pos, buildingSize.getSize(type, number),
                        global.bitmapResource.BuildingAnimation[type][number],
                        global.bitmapResource.BuildingImage[type][number]);
                return brazierHigh;
            case SmeltingCode.WindMill:
                WindMill windMill = new WindMill();
                windMill.Create(pos, buildingSize.getSize(type, number),
                        global.bitmapResource.BuildingAnimation[type][number],
                        global.bitmapResource.BuildingImage[type][number]);
                return windMill;
            default:
                return null;
        }
    }

    // 조합공장
    public Combine getCombine(int type, int number, Vector2 pos) {
        switch (number) {
            case CombineCode.Carpenter:
                Carpenter carpenter = new Carpenter();
                carpenter.Create(pos, buildingSize.getSize(type, number),
                        global.bitmapResource.BuildingAnimation[type][number],
                        global.bitmapResource.BuildingImage[type][number]);
                return carpenter;
            case CombineCode.WorkShop:
                WorkShop workShop = new WorkShop();
                workShop.Create(pos, buildingSize.getSize(type, number),
                        global.bitmapResource.BuildingAnimation[type][number],
                        global.bitmapResource.BuildingImage[type][number]);
                return workShop;
            case CombineCode.Smithy:
                Smithy smithy = new Smithy();
                smithy.Create(pos, buildingSize.getSize(type, number),
                        global.bitmapResource.BuildingAnimation[type][number],
                        global.bitmapResource.BuildingImage[type][number]);
                return smithy;
            default:
                return null;
        }
    }

    // 분배공장
    public Distribution getDistribution(int type, int number, Vector2 pos) {
        switch (number) {
            case DistributionCode.DistributionCenter:
                DistributionCenter distributionCenter = new DistributionCenter();
                distributionCenter.Create(pos, buildingSize.getSize(type, number),
                        global.bitmapResource.BuildingAnimation[type][number],
                        global.bitmapResource.BuildingImage[type][number]);
                return distributionCenter;
            default:
                return null;
        }
    }

    // 소모공장
    public Consumption getConsumption(int type, int number, Vector2 pos) {
        switch (number) {
            case ConsumptionCode.Town:
                Town town = new Town();
                town.Create(pos, buildingSize.getSize(type, number),
                        global.bitmapResource.BuildingAnimation[type][number],
                        global.bitmapResource.BuildingImage[type][number]);
                return town;
            case ConsumptionCode.FoodDepots:
                FoodDepots foodDepots = new FoodDepots();
                foodDepots.Create(pos, buildingSize.getSize(type, number),
                        global.bitmapResource.BuildingAnimation[type][number],
                        global.bitmapResource.BuildingImage[type][number]);
                return foodDepots;
            default:
                return null;
        }
    }

    // 특수공장
    public Building getSpecial(int type, int number, Vector2 pos) {
        switch (number) {
            case SpecialCode.Statue:
                Statue temp = new Statue();
                temp.Create(pos, buildingSize.getSize(type, number),
                        global.bitmapResource.BuildingAnimation[type][number],
                        global.bitmapResource.BuildingImage[type][number]);
                return temp;
            default:
                return null;
        }
    }

    // 생산공장
    public Production getProduction(int number) {
        switch (number) {
            case ProductionCode.log:
                return new Log();
            case ProductionCode.Stone:
                return new Quarry();
            case ProductionCode.CornField:
                return new CornField();
            case ProductionCode.Well:
                return new Well();
            case ProductionCode.Mine:
                return new Mine();
            default:
                return null;
        }
    }

    // 제련공장
    public Smelting getSmelting(int number) {
        switch (number) {
            case SmeltingCode.BrazierLow:
                return new BrazierLow();
            case SmeltingCode.BrazierHigh:
                return new BrazierHigh();
            case SmeltingCode.WindMill:
                return new WindMill();
            default:
                return null;
        }
    }

    // 조합공장
    public Combine getCombine(int number) {
        switch (number) {
            case CombineCode.Carpenter:
                return new Carpenter();
            case CombineCode.WorkShop:
                return new WorkShop();
            case CombineCode.Smithy:
                return new Smithy();
            default:
                return null;
        }
    }

    // 분배공장
    public Distribution getDistribution(int number) {
        switch (number) {
            case DistributionCode.DistributionCenter:
                return new DistributionCenter();
            default:
                return null;
        }
    }

    // 소모공장
    public Consumption getConsumption(int number) {
        switch (number) {
            case ConsumptionCode.Town:
                return new Town();
            default:
                return null;
        }
    }

    // 특수공장
    public Building getSpecial(int number) {
        switch (number) {
            case SpecialCode.Statue:
                return new Statue();
            default:
                return null;
        }
    }

}
