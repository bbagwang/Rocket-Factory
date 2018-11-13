package navi_studio.rocket_fectory.Global;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.ArrayList;

import navi_studio.rocket_fectory.Object.Core.ObjectCode;
import navi_studio.rocket_fectory.Part.ItemCell;
import navi_studio.rocket_fectory.R;

public class ItemCode {

    public final static int FALSE = 999;

    public ArrayList<ItemCell> RawMaterialsCell;
    public ArrayList<ItemCell> MaterialCell;
    public ArrayList<ItemCell> PartsCell;
    public ArrayList<ItemCell> ConsumptionCell;
    public ArrayList<ItemCell> SpecialCell;

    public ItemCode(Global global) {

        RawMaterialsCell = new ArrayList<ItemCell>();
        MaterialCell = new ArrayList<ItemCell>();
        PartsCell = new ArrayList<ItemCell>();
        ConsumptionCell = new ArrayList<ItemCell>();
        SpecialCell = new ArrayList<ItemCell>();

        RawMaterialsCell.add(new ItemCell(ObjectCode.Dirt, 10,ItemCell.RawMaterials,
                10,
                BitmapFactory.decodeResource(global.context.getResources(), R.drawable.dust),
                global
        ));     // 흙        : 미가공 아이템   0
        RawMaterialsCell.add(new ItemCell(ObjectCode.Stone, 11,ItemCell.RawMaterials,
                11,
                BitmapFactory.decodeResource(global.context.getResources(), R.drawable.stone),
                global
        ));     // 돌        : 미가공 아이템   1
        RawMaterialsCell.add(new ItemCell(ObjectCode.IronStone, 12,ItemCell.RawMaterials,
                12,
                BitmapFactory.decodeResource(global.context.getResources(), R.drawable.ironstone),
                global
        ));     // 철광석    : 미가공 아이템   2
        RawMaterialsCell.add(new ItemCell(ObjectCode.Log, 14,ItemCell.RawMaterials,
                14,
                BitmapFactory.decodeResource(global.context.getResources(), R.drawable.log),
                global
        ));     // 통나무    : 미가공 아이템   3
        RawMaterialsCell.add(new ItemCell(ObjectCode.Coal, 20,ItemCell.RawMaterials,
                20,
                BitmapFactory.decodeResource(global.context.getResources(), R.drawable.coal),
                global
        ));     // 석탄      : 미가공 아이템   4
        RawMaterialsCell.add(new ItemCell(ObjectCode.Wheat, 24,ItemCell.RawMaterials,
                25,
                BitmapFactory.decodeResource(global.context.getResources(), R.drawable.wheat),
                global
        ));     // 밀        : 미가공 아이템   5
        RawMaterialsCell.add(new ItemCell(ObjectCode.WaterPail, 25,ItemCell.RawMaterials,
                26,
                BitmapFactory.decodeResource(global.context.getResources(), R.drawable.waterpail),
                global
        ));     // 물 양동이 : 미가공 아이템   6



        MaterialCell.add(new ItemCell(ObjectCode.Wood, 13,ItemCell.Material,
                13,
                BitmapFactory.decodeResource(global.context.getResources(), R.drawable.wood),
                global
        ));     // 합판      : 가공 아이템   0
        MaterialCell.add(new ItemCell(ObjectCode.Iron, 16,ItemCell.Material,
        16,
                BitmapFactory.decodeResource(global.context.getResources(), R.drawable.iron),
                global
        ));     // 철        : 가공 아이템   1
        MaterialCell.add(new ItemCell(ObjectCode.IronPlate, 23,ItemCell.Material,
                24,
                BitmapFactory.decodeResource(global.context.getResources(), R.drawable.ironplate),
                global
        ));     // 철판       : 가공 아이템



        PartsCell.add(new ItemCell(ObjectCode.WoodenGear, 22,ItemCell.Parts,
                22,
                BitmapFactory.decodeResource(global.context.getResources(), R.drawable.woodengear),
                global
        ));     // 나무기어   : 조립 아이템



        ConsumptionCell.add(new ItemCell(ObjectCode.Tool, 15,ItemCell.Consumption,
                15,
                BitmapFactory.decodeResource(global.context.getResources(), R.drawable.tool),
                global
        ));     // 도구      : 소모성 아이템   0
        ConsumptionCell.add(new ItemCell(ObjectCode.Wagon, 18,ItemCell.Consumption,
                18,
                BitmapFactory.decodeResource(global.context.getResources(), R.drawable.wagon),
                global
        ));     // 수레      : 소모성 아이템   1
        ConsumptionCell.add(new ItemCell(ObjectCode.IronTool, 19,ItemCell.Consumption,
                19,
                BitmapFactory.decodeResource(global.context.getResources(), R.drawable.irontool),
                global
        ));     // 철툴      : 소모성 아이템   2
        ConsumptionCell.add(new ItemCell(ObjectCode.Hoe, 21,ItemCell.Consumption,
                21,
                BitmapFactory.decodeResource(global.context.getResources(), R.drawable.hoe),
                global
        ));     // 괭이      : 소모성 아이템   3
        ConsumptionCell.add(new ItemCell(ObjectCode.Anvil, 23,ItemCell.Consumption,
                23,
                BitmapFactory.decodeResource(global.context.getResources(), R.drawable.anvil),
                global
        ));     // 모루      : 소모성 아이템   4
        ConsumptionCell.add(new ItemCell(ObjectCode.Pail, 26,ItemCell.Consumption,
                27,
                BitmapFactory.decodeResource(global.context.getResources(), R.drawable.pail),
                global
        ));     // 양동이    : 소모성   5

        SpecialCell.add(new ItemCell(ObjectCode.BronzeMirror, 17,ItemCell.Special,
                17,
                BitmapFactory.decodeResource(global.context.getResources(), R.drawable.bronzemirror),
                global
        ));     // 청동거울  : 특수   0
        SpecialCell.add(new ItemCell(ObjectCode.Bread, 27,ItemCell.Special,
                28,
                BitmapFactory.decodeResource(global.context.getResources(), R.drawable.bread),
                global
        ));     // 빵        : 특수   1
    }

    public ItemCell get(int code){

        if(FALSE != RawMaterialsSelect(code)){
            return getRawMaterialsCell(code);
        } else if(FALSE != MaterialSelect(code)){
            return getMaterialCell(code);
        } else if(FALSE != PartsSelect(code)){
            return getPartsCell(code);
        } else if(FALSE != ConsumptionSelect(code)){
            return getConsumptionCell(code);
        } else if(FALSE != SpecialSelect(code)){
            return getSpecialCell(code);
        }

        return null;
    }

    public int getType(int code){

        if(FALSE != RawMaterialsSelect(code)){
            return ItemCell.RawMaterials;
        } else if(FALSE != MaterialSelect(code)){
            return ItemCell.Material;
        } else if(FALSE != PartsSelect(code)){
            return ItemCell.Parts;
        } else if(FALSE != ConsumptionSelect(code)){
            return ItemCell.Consumption;
        } else if(FALSE != SpecialSelect(code)){
            return ItemCell.Special;
        }

        return FALSE;
    }

    public void sizeClear(){

        for(int i = 0; i < RawMaterialsCell.size();i++){
            RawMaterialsCell.get(i).number = 0;
        }
        for(int i = 0; i < MaterialCell.size();i++){
            MaterialCell.get(i).number = 0;
        }
        for(int i = 0; i < PartsCell.size();i++){
            PartsCell.get(i).number = 0;
        }
        for(int i = 0; i < ConsumptionCell.size();i++){
            ConsumptionCell.get(i).number = 0;
        }
        for(int i = 0; i < SpecialCell.size();i++){
            SpecialCell.get(i).number = 0;
        }
    }

    public ItemCell getSpecialCell(int code) {
        return SpecialCell.get(SpecialSelect(code));
    }

    // 코드에 맞는 인벤토리 번호 반환
    public int SpecialSelect(int code) {
        for (int i = 0; i < SpecialCell.size(); i++)
            if (SpecialCell.get(i).code == code)
                return i;
        return FALSE;
    }

    public ItemCell getConsumptionCell(int code) {
        return ConsumptionCell.get(ConsumptionSelect(code));
    }

    // 코드에 맞는 인벤토리 번호 반환
    public int ConsumptionSelect(int code) {
        for (int i = 0; i < ConsumptionCell.size(); i++)
            if (ConsumptionCell.get(i).code == code)
                return i;
        return FALSE;
    }

    public ItemCell getPartsCell(int code) {
        return PartsCell.get(PartsSelect(code));
    }

    // 코드에 맞는 인벤토리 번호 반환
    public int PartsSelect(int code) {
        for (int i = 0; i < PartsCell.size(); i++)
            if (PartsCell.get(i).code == code)
                return i;
        return FALSE;
    }

    public ItemCell getMaterialCell(int code) {
        return MaterialCell.get(MaterialSelect(code));
    }

    // 코드에 맞는 인벤토리 번호 반환
    public int MaterialSelect(int code) {
        for (int i = 0; i < MaterialCell.size(); i++)
            if (MaterialCell.get(i).code == code)
                return i;
        return FALSE;
    }

    public ItemCell getRawMaterialsCell(int code) {
        return RawMaterialsCell.get(RawMaterialsSelect(code));
    }

    // 코드에 맞는 인벤토리 번호 반환
    public int RawMaterialsSelect(int code) {
        for (int i = 0; i < RawMaterialsCell.size(); i++)
            if (RawMaterialsCell.get(i).code == code)
                return i;
        return FALSE;
    }

    public ItemCell getRawMaterialsCellNumber(int number) {
        return RawMaterialsCell.get(number);
    }
    public ItemCell getMaterialCellNumber(int number) {
        return MaterialCell.get(number);
    }
    public ItemCell getPartsCellNumber(int number) {
        return PartsCell.get(number);
    }
    public ItemCell getConsumptionCellNumber(int number) {
        return ConsumptionCell.get(number);
    }
    public ItemCell getSpecialCellNumber(int number) {
        return SpecialCell.get(number);
    }
}
