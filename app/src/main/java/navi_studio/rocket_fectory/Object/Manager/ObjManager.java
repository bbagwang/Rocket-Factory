package navi_studio.rocket_fectory.Object.Manager;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;

import navi_studio.rocket_fectory.Function.Vector2;
import navi_studio.rocket_fectory.Global.Global;
import navi_studio.rocket_fectory.Global.Target;
import navi_studio.rocket_fectory.Object.Basic.Building;
import navi_studio.rocket_fectory.Object.Basic.Conveyor;
import navi_studio.rocket_fectory.Object.Basic.Material;
import navi_studio.rocket_fectory.Object.Core.ObjLow;
import navi_studio.rocket_fectory.Object.Core.Object;
import navi_studio.rocket_fectory.Object.Core.ObjectCode;

public class ObjManager {

    public final static int BuildingType = 100;         // 건물 타입
    public final static int ConveyorType = 101;         // 컨베이어 타입
    public final static int MaterialType = 102;         // 자원 타입
    public final static int ObjLowType = 103;           // 다용도 오브젝트 타입

    public static Intersection intersection;

    private int ID = 10000;         // object 에 시작 ID 입니다. 1씩 증가합니다.

    private ArrayList<Building> building;   // 건물 타입
    private ArrayList<Conveyor> conveyor;   // 컨베이어 타입
    private ArrayList<Material> material;   // 자원 타입
    private ArrayList<ObjLow> objLow;     // 기본 타입

    public Global global;

    public ObjManager() {
        building = new ArrayList<Building>();
        conveyor = new ArrayList<Conveyor>();
        material = new ArrayList<Material>();
        objLow = new ArrayList<ObjLow>();
        intersection = new Intersection(global);
    }

    public void Create(Global _global) {
        global = _global;
    }

    // Intersection 을 수행합니다.
    public void FixedUpdate() {
        // 모든 true 오브젝트 false로 전환 작업
        for (int i = 0; i < material.size(); i++)
            material.get(i).setSelect(false);
        for (int i = 0; i < conveyor.size(); i++)
            conveyor.get(i).setSelect(false);
        for (int i = 0; i < building.size(); i++)
            building.get(i).setSelect(false);

        // intersection 실행
        for (int i = 0; i < material.size(); i++)
            intersection.select(material.get(i), global.camera);

        // intersection 실행
        for (int i = 0; i < conveyor.size(); i++)
            intersection.select(conveyor.get(i), global.camera);

        // intersection 실행
        for (int i = 0; i < building.size(); i++)
            intersection.select(building.get(i), global.camera);
    }

    // object 의 기능을 수행합니다.
    public void Update() {
        for (int i = 0; i < objLow.size(); i++)
            if (objLow.get(i).Update())
                objLow.remove(i);

        for (int i = 0; i < building.size(); i++)
            if (building.get(i).Update())
                building.remove(i);

        for (int i = 0; i < conveyor.size(); i++)
            if (conveyor.get(i).Update())
                conveyor.remove(i);

        for (int i = 0; i < material.size(); i++)
            if (material.get(i).Update())
                material.remove(i);
    }

    // object 의 랜더를 실행합니다.
    public void Render(Canvas canvas) {

        // 랜더 순서가 바뀌어서는 안됩니다.
        for (int i = 0; i < material.size(); i++) {
            if (material.get(i).getSelect())
                material.get(i).Render(canvas, global.camera);
        }
        for (int i = 0; i < conveyor.size(); i++) {
            if (conveyor.get(i).getSelect())
                conveyor.get(i).Render(canvas, global.camera);
        }
        for (int i = 0; i < building.size(); i++) {
            if (building.get(i).getSelect())
                building.get(i).Render(canvas, global.camera);
        }
        for (int i = 0; i < objLow.size(); i++)
            objLow.get(i).Render(canvas, global.camera);
    }

    // object 의 UI 랜더를 실행합니다.
    public void UIRender(Canvas canvas) {
    }

    // 오브젝트 개수 반환
    public int getSize(int type) {
        switch (type) {
            case BuildingType:
                return building.size();
            case ConveyorType:
                return conveyor.size();
            case MaterialType:
                return material.size();
            case ObjLowType:
                return objLow.size();
            default:
                return 0;
        }
    }

    public final ArrayList<Building> getBuilding(){
        return building;
    }

    public final ArrayList<Material> getMaterial(){
        return material;
    }

    // building 추가
    public void add(Building _building) {
        _building.setID(ID++);
        building.add(_building);
    }

    // conveyor 추가
    public void add(Conveyor _conveyor) {
        _conveyor.setID(ID++);
        conveyor.add(_conveyor);
    }

    // material 추가
    public void add(Material _material) {
        _material.setID(ID++);
        material.add(_material);
    }

    public void add(ObjLow _objLow) {
        objLow.add(_objLow);
    }

    public Building BuildingSearch(int ID) {
        for (int i = 0; i < building.size(); i++) {
            if (building.get(i).getID() == ID)
                return building.get(i);
        }
        return null;
    }

    public Conveyor ConveyorSearch(int ID) {
        for (int i = 0; i < conveyor.size(); i++) {
            if (conveyor.get(i).getID() == ID)
                return conveyor.get(i);
        }
        return null;
    }

    // 터치된 오브젝트의 ID 반환
    public int touchObject(int x, int y) {

        // 순서를 변경해서는 안됩니다.
        // 건물 터치했을때
        for (int i = 0; i < building.size(); i++) {
            if (building.get(i).touchCheck(x, y)) {
                return building.get(i).getID();
            }
        }
        // 자원 터치했을때
        for (int i = 0; i < material.size(); i++) {
            if (material.get(i).touchCheck(x, y)) {
                return material.get(i).getCode();
            }
        }
        return ObjectCode.Empty;
    }

    // 벡터 입력 touchObject
    public int touchObject(Vector2 vector2) {
        return touchObject(vector2.x, vector2.y);
    }

    // 터치된 오브젝트의 ID 반환
    // 카메라 위치 적용
    public int touchObject(int x, int y, boolean camera) {

        // 순서를 변경해서는 안됩니다.
        // 건물 터치했을때
        if (camera) {
            for (int i = 0; i < building.size(); i++) {
                if (building.get(i).touchCheck(
                        x + global.camera.getPos().x - (global.camera.getSizeWidth() / 2),
                        y + global.camera.getPos().y - (global.camera.getSizeHeight() / 2))) {
                    return building.get(i).getID();
                }
            }
            // 자원 터치했을때
            for (int i = 0; i < material.size(); i++) {
                if (material.get(i).touchCheck(
                        x + global.camera.getPos().x - (global.camera.getSizeWidth() / 2),
                        y + global.camera.getPos().y - (global.camera.getSizeHeight() / 2))) {
                    return material.get(i).getCode();
                }
            }
        } else {
            touchObject(x, y);
        }
        return ObjectCode.Empty;
    }

    // ID를 이용해 오브젝트 삭제
    public boolean remove(int ID) {

        for (int i = 0; i < building.size(); i++) {
            if (building.get(i).getID() == ID) {
                building.remove(i);
                return true;
            }
        }
        for (int i = 0; i < conveyor.size(); i++) {
            if (conveyor.get(i).getID() == ID) {
                conveyor.remove(i);
                return true;
            }
        }
        for (int i = 0; i < material.size(); i++) {
            if (material.get(i).getID() == ID) {
                material.remove(i);
                return true;
            }
        }
        return false;
    }
}
