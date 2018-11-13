package navi_studio.rocket_fectory.Global;

import android.widget.Switch;

import navi_studio.rocket_fectory.Part.ItemCell;

public class Inventory {

    public ItemCode item;

    public static int MaxSize = 2000;
    private static int Size = 0;

    Global global;

    public Inventory(Global _global) {
        global = _global;
        item = new ItemCode(global);
        item.sizeClear();
    }

    public final int getSize(){
        return Size;
    }

    // 묶음 추가
    public boolean addItem(int[] Code, int[] Number) {

        if (isFull(Code, Number))
            return false;

        for (int i = 0; i < Code.length; i++) {
            item.get(select(Code[i])).number += Number[i];
            Size = item.get(select(Code[i])).number * global.itemCode.get(select(Code[i])).weight;
        }

        return true;
    }

    // 단일 추가
    public boolean addItem(int Code, int Number) {

        if (isFull(Code, Number))
            return false;


        item.get(Code).number += Number;

        Size = item.get(Code).number * global.itemCode.get(Code).weight;

        return true;
    }

    // 묶음 소모
    public boolean subItem(int[] Code, int[] Number) {

        if (!isEnough(Code, Number))
            return false;

        for (int i = 0; i < Code.length; i++) {
            item.get(Code[i]).number -= Number[i];
            Size = item.get(Code[i]).number * global.itemCode.get(Code[i]).weight;
        }
        return true;
    }

    // 단일 소모
    public boolean subItem(int Code, int Number) {

        if (!isEnough(Code, Number))
            return false;

        item.get(Code).number -= Number;

        Size = item.get(Code).number * global.itemCode.get(Code).weight;

        return true;
    }

    // 아이템을 넣을 공간이 존재하는가?
    public boolean isFull(int[] Code, int[] Number) {

        int temp = 0;

        for (int i = 0; i < Code.length; i++)
            temp += global.itemCode.get(Code[i]).number * Number[i];

        if (Size + temp > MaxSize)
            return true;

        return false;
    }

    // 충분한 양을 소지하고 있는가?
    public boolean isEnough(int[] Code, int[] Number) {

        for (int i = 0; i < Code.length; i++)
            if (!isEnough(Code[i], Number[i]))
                return false;       // 하나라도 충족되지 않을경우

        return true;
    }

    // 아이템을 넣을 공간이 존재하는가? 단일
    public boolean isFull(int Code, int Number) {

        if (Size + (global.itemCode.get(Code).weight * Number) > MaxSize)
            return true;

        return false;
    }

    // 충분한 양을 소지하고 있는가? 단일
    public boolean isEnough(int Code, int Number) {
        if (item.get(Code).number >= Number)
            return true;
        return false;
    }

    // 코드에 맞는 인벤토리 번호 반환
    private int select(int Code) {

        switch (global.itemCode.getType(Code)) {
            case ItemCell.RawMaterials:
                for (int i = 0; i < item.RawMaterialsCell.size(); i++) {
                    if (item.get(i).code == Code)
                        return i;
                }
                break;
            case ItemCell.Material:
                for (int i = 0; i < item.MaterialCell.size(); i++) {
                    if (item.get(i).code == Code)
                        return i;
                }
                break;
            case ItemCell.Parts:
                for (int i = 0; i < item.PartsCell.size(); i++) {
                    if (item.get(i).code == Code)
                        return i;
                }
                break;
            case ItemCell.Consumption:
                for (int i = 0; i < item.ConsumptionCell.size(); i++) {
                    if (item.get(i).code == Code)
                        return i;
                }
                break;
            case ItemCell.Special:
                for (int i = 0; i < item.SpecialCell.size(); i++) {
                    if (item.get(i).code == Code)
                        return i;
                }
                break;
        }
        return ItemCode.FALSE;
    }

    public int GetSize(){
        int temp_size=0;
        for(int i=0;i<item.RawMaterialsCell.size();i++){
            temp_size+=item.RawMaterialsCell.get(i).number*item.RawMaterialsCell.get(i).weight;
        }
        for(int i=0;i<item.MaterialCell.size();i++){
            temp_size+=item.MaterialCell.get(i).number*item.MaterialCell.get(i).weight;
        }
        for(int i=0;i<item.PartsCell.size();i++){
            temp_size+=item.PartsCell.get(i).number*item.PartsCell.get(i).weight;
        }
        for(int i=0;i<item.ConsumptionCell.size();i++){
            temp_size+=item.ConsumptionCell.get(i).number*item.ConsumptionCell.get(i).weight;
        }
        for(int i=0;i<item.SpecialCell.size();i++){
            temp_size+=item.SpecialCell.get(i).number*item.SpecialCell.get(i).weight;
        }
        return temp_size;
    }

}