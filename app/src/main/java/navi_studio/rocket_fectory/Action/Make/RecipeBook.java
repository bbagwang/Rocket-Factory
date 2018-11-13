package navi_studio.rocket_fectory.Action.Make;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import java.util.ArrayList;

import navi_studio.rocket_fectory.Global.Global;
import navi_studio.rocket_fectory.Object.Core.ObjectCode;
import navi_studio.rocket_fectory.Part.ItemCell;

public class RecipeBook {
    Global global;
    public int[] item_index;    //필요한 아이템 인덱스
    public int[] req_amount;    //필요한 양
    public int[] original_req_amount;   //개당 필요한 양
    public int make_amount;    //만들어지는 개수
    public int index_size;      //인덱스 크기
    public Bitmap[] item_image; //아이템 이미지
    public int make_item_code;  //만들 아이템의 코드

    public ArrayList<ItemCell> Material_Make;
    public ArrayList<ItemCell> Parts_Make;
    public ArrayList<ItemCell> Consumption_Make;
    public ArrayList<ItemCell> Special_Make;

    public MakeAction makeAction;

    void Create(Global _global,MakeAction _makeAction) {
        global = _global;
        makeAction=_makeAction;
        Material_Make = new ArrayList<ItemCell>();
        Parts_Make = new ArrayList<ItemCell>();
        Consumption_Make = new ArrayList<ItemCell>();
        Special_Make = new ArrayList<ItemCell>();

        Initialize_Material_List();
        Initialize_Parts_List();
        Initialize_Consumption_List();
        Initialize_Special_List();

        SelectRecipe(Material_Make.get(0).code);
    }

    void Initialize_Material_List() {
        Material_Make.add(global.itemCode.MaterialCell.get(0)); //합판
        Material_Make.add(global.itemCode.MaterialCell.get(1)); //철
        Material_Make.add(global.itemCode.MaterialCell.get(2)); //철판
    }

    void Initialize_Parts_List() {
        Parts_Make.add(global.itemCode.PartsCell.get(0)); //나무기어
    }

    void Initialize_Consumption_List() {
        Consumption_Make.add(global.itemCode.ConsumptionCell.get(0)); // 도구
        Consumption_Make.add(global.itemCode.ConsumptionCell.get(1)); // 수레
        Consumption_Make.add(global.itemCode.ConsumptionCell.get(2)); // 철도구
        Consumption_Make.add(global.itemCode.ConsumptionCell.get(3)); // 괭이
        Consumption_Make.add(global.itemCode.ConsumptionCell.get(4)); // 모루
    }

    void Initialize_Special_List() {
        Special_Make.add(global.itemCode.SpecialCell.get(0)); //청동거울
        Special_Make.add(global.itemCode.SpecialCell.get(1)); //빵
    }

    void SelectRecipe(int _select) {
        make_item_code=_select;
        switch (_select) {

            //MATERIAL CASES

            case ObjectCode.Wood:
                item_index = new int[1];
                req_amount = new int[1];
                original_req_amount=new int[1];
                item_image = new Bitmap[1];
                item_index[0] = ObjectCode.Log;
                req_amount[0] = 1;
                original_req_amount[0]=1;
                item_image[0] = global.itemCode.get(item_index[0]).image;
                make_amount = 1;
                index_size = 1;
                break;
            case ObjectCode.Iron:
                item_index = new int[1];
                req_amount = new int[1];
                original_req_amount=new int[1];
                item_image = new Bitmap[1];
                item_index[0] = ObjectCode.IronStone;
                req_amount[0] = 1;
                original_req_amount[0]=1;
                item_image[0] = global.itemCode.get(item_index[0]).image;
                make_amount = 1;
                index_size = 1;
                break;
            case ObjectCode.IronPlate:
                item_index = new int[1];
                req_amount = new int[1];
                original_req_amount=new int[1];
                item_image = new Bitmap[1];
                item_index[0] = ObjectCode.Iron;
                req_amount[0] = 1;
                original_req_amount[0]=1;
                item_image[0] = global.itemCode.get(item_index[0]).image;
                make_amount = 1;
                index_size = 1;
                break;

            //PARTS CASES

            case ObjectCode.WoodenGear:
                item_index = new int[2];
                req_amount = new int[2];
                original_req_amount=new int[2];
                item_image = new Bitmap[2];
                item_index[0] = ObjectCode.Tool;
                req_amount[0] = 1;
                original_req_amount[0]=1;
                item_image[0] = global.itemCode.get(item_index[0]).image;

                item_index[1] = ObjectCode.Wood;
                req_amount[1] = 1;
                original_req_amount[1]=1;
                item_image[1] = global.itemCode.get(item_index[1]).image;
                make_amount = 1;
                index_size = 2;
                break;

            //CONSUMPTION CASES

            case ObjectCode.Tool:
                item_index = new int[2];
                req_amount = new int[2];
                original_req_amount=new int[2];
                item_image = new Bitmap[2];
                item_index[0] = ObjectCode.Stone;
                req_amount[0] = 1;
                original_req_amount[0]=1;
                item_image[0] = global.itemCode.get(item_index[0]).image;

                item_index[1] = ObjectCode.Log;
                req_amount[1] = 1;
                original_req_amount[1]=1;
                item_image[1] = global.itemCode.get(item_index[1]).image;
                make_amount = 1;
                index_size = 2;
                break;
            case ObjectCode.Wagon:
                item_index = new int[1];
                req_amount = new int[1];
                original_req_amount=new int[1];
                item_image = new Bitmap[1];
                item_index[0] = ObjectCode.Wood;
                req_amount[0] = 2;
                original_req_amount[0]=2;
                item_image[0] = global.itemCode.get(item_index[0]).image;
                make_amount = 1;
                index_size = 1;
                break;
            case ObjectCode.IronTool:
                item_index = new int[2];
                req_amount = new int[2];
                original_req_amount=new int[2];
                item_image = new Bitmap[2];
                item_index[0] = ObjectCode.Wood;
                req_amount[0] = 1;
                original_req_amount[0]=1;
                item_image[0] = global.itemCode.get(item_index[0]).image;

                item_index[1] = ObjectCode.IronPlate;
                req_amount[1] = 1;
                original_req_amount[1]=1;
                item_image[1] = global.itemCode.get(item_index[1]).image;
                make_amount = 1;
                index_size = 2;
                break;
            case ObjectCode.Hoe:
                item_index = new int[2];
                req_amount = new int[2];
                original_req_amount=new int[2];
                item_image = new Bitmap[2];
                item_index[0] = ObjectCode.Wood;
                req_amount[0] = 1;
                original_req_amount[0]=1;
                item_image[0] = global.itemCode.get(item_index[0]).image;

                item_index[1] = ObjectCode.IronPlate;
                req_amount[1] = 1;
                original_req_amount[1]=1;
                item_image[1] = global.itemCode.get(item_index[1]).image;
                make_amount = 1;
                index_size = 2;
                break;
            case ObjectCode.Anvil:
                item_index = new int[1];
                req_amount = new int[1];
                original_req_amount=new int[1];
                item_image = new Bitmap[1];
                item_index[0] = ObjectCode.Iron;
                req_amount[0] = 3;
                original_req_amount[0]=3;
                item_image[0] = global.itemCode.get(item_index[0]).image;
                make_amount = 1;
                index_size = 1;
                break;
            case ObjectCode.Pail:
                item_index = new int[1];
                req_amount = new int[1];
                original_req_amount=new int[1];
                item_image = new Bitmap[1];
                item_index[0] = ObjectCode.IronPlate;
                req_amount[0] = 1;
                original_req_amount[0]=1;
                item_image[0] = global.itemCode.get(item_index[0]).image;
                make_amount = 1;
                index_size = 1;
                break;

            //Special ITEM CASES

            case ObjectCode.BronzeMirror:
                item_index = new int[1];
                req_amount = new int[1];
                original_req_amount=new int[1];
                item_image = new Bitmap[1];
                item_index[0] = ObjectCode.Iron;
                req_amount[0] = 1;
                original_req_amount[0]=1;
                item_image[0] = global.itemCode.get(item_index[0]).image;
                make_amount = 1;
                index_size = 1;
                break;
            case ObjectCode.Bread:
                item_index = new int[2];
                req_amount = new int[2];
                original_req_amount=new int[2];
                item_image = new Bitmap[2];
                item_index[0] = ObjectCode.Wheat;
                req_amount[0] = 1;
                original_req_amount[0]=1;
                item_image[0] = global.itemCode.get(item_index[0]).image;

                item_index[1] = ObjectCode.WaterPail;
                req_amount[1] = 1;
                original_req_amount[1]=1;
                item_image[1] = global.itemCode.get(item_index[1]).image;
                make_amount = 1;
                index_size = 2;
                break;

            default:
                break;
        }
    }

    void Update() {
    }

    void Render(Canvas canvas) {
    }

    public boolean Destroy() {
        return true;
    }
}
