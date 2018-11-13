package navi_studio.rocket_fectory.Action.Manager;

import android.graphics.Canvas;

import navi_studio.rocket_fectory.Action.Building.BuildingConnection;
import navi_studio.rocket_fectory.Action.Building.BuildingInformationAction;
import navi_studio.rocket_fectory.Action.Building.ConnectManagementAction;
import navi_studio.rocket_fectory.Action.Building.DestroyAction;
import navi_studio.rocket_fectory.Action.Building.InputResourceAction;
import navi_studio.rocket_fectory.Action.Building.InputWorkerAction;
import navi_studio.rocket_fectory.Action.Building.Production.ProductionManagementAction;
import navi_studio.rocket_fectory.Action.CollectingAction;
import navi_studio.rocket_fectory.Action.Construction.BuildingSelectAction;
import navi_studio.rocket_fectory.Action.InformationAction;
import navi_studio.rocket_fectory.Action.InventoryAction;
import navi_studio.rocket_fectory.Action.Make.MakeAction;
import navi_studio.rocket_fectory.Action.SaveAction;
import navi_studio.rocket_fectory.Function.Vector2;
import navi_studio.rocket_fectory.Global.Global;
import navi_studio.rocket_fectory.Object.Core.ObjectCode;
import navi_studio.rocket_fectory.Object.Manager.ObjManager;

public class ActionManager {

    public final static int Construction = 0;           // 건설
    public final static int Collecting = 1;             // 선택
    public final static int Information = 2;            // 정보
    public final static int Inventory = 6;              // 인벤토리
    public final static int Make = 12;                  // 제작
    public final static int Save = 18;                  // 저장
    public final static int InputResource = 150;        // 자원투입
    public final static int InputWorker = 153;          // 인력투입
    public final static int BuildingInformation = 156;  // 건물정보 (건설완료 전)
    public final static int BuildingDestroy = 159;      // 건물파괴
    public final static int BuildingConnection = 162;      // 연결
    public final static int ConnectManagement = 165;      // 연결관리

    public final static int ProductionManagement = 300;       // 관리 (생산건물)

    public final static int FAIL = 999;                 // 실패

    private BuildingSelectAction buildingSelectAction;          // 건물 선택
    private CollectingAction collectingAction;                  // 채집
    private InformationAction informationAction;                // 정보
    private InventoryAction inventoryAction;                    // 인벤토리
    private MakeAction makeAction;                              // 제작
    private SaveAction saveAction;                              // 제작
    private InputResourceAction inputResourceAction;            // 자원투입
    private InputWorkerAction inputWorkerAction;                // 인력투입
    private BuildingInformationAction buildingInformationAction;// 건물정보 (건설완료 전)
    private DestroyAction destroyAction;                        // 건물파괴
    private BuildingConnection buildingConnection;              // 건물연결
    private ConnectManagementAction connectManagementAction;    // 연결관리

    private ProductionManagementAction productionManagementAction;    // 관리 (생산건물)

    private Vector2 Position;                       // 위치 정보

    private int storageCode;                        // 받아온 코드, 코드 (건물의 경우 오브젝트)
    private int Command;                            // 커맨드

    private Global global;
    private ObjManager objManager;

    public void Create(Global _global, ObjManager _objManager) {

        global = _global;
        objManager = _objManager;

        buildingSelectAction = new BuildingSelectAction();
        collectingAction = new CollectingAction();
        informationAction = new InformationAction();
        inventoryAction = new InventoryAction();
        makeAction = new MakeAction();
        saveAction = new SaveAction();
        inputResourceAction = new InputResourceAction();
        inputWorkerAction = new InputWorkerAction();
        buildingInformationAction = new BuildingInformationAction();
        destroyAction = new DestroyAction();
        buildingConnection = new BuildingConnection();
        productionManagementAction = new ProductionManagementAction();
        connectManagementAction = new ConnectManagementAction();

        buildingSelectAction.Create(global, objManager);
        collectingAction.Create(global);
        informationAction.Create(global, objManager);
        inventoryAction.Create(global);
        makeAction.Create(global);
        saveAction.Create(global);
        inputResourceAction.Create(global, objManager);
        inputWorkerAction.Create(global, objManager);
        buildingInformationAction.Create(global, objManager);
        destroyAction.Create(global, objManager);
        buildingConnection.Create(global, objManager);
        productionManagementAction.Create(global, objManager);
        connectManagementAction.Create(global, objManager);
    }

    // 위치, 코드, 타입
    public void storageSet(Vector2 pos, int command, int code, int id) {

        Position = new Vector2();
        Position.setVector(pos);
        storageCode = code;
        // 빈 땅 일경우
        if (storageCode == ObjectCode.Empty) {
            Command = command * 6;
        } else
            Command = command;
    }

    public void start() {
        switch (Command) {
            case Construction:
                buildingSelectAction.set(storageCode);
                break;
            case Collecting:
                collectingAction.set(storageCode);
                break;
            case Information:
                informationAction.set(storageCode);
                break;
            case Inventory:
                inventoryAction.set(storageCode);
                break;
            case Make:
                makeAction.set(storageCode);
                break;
            case Save:
                saveAction.set(storageCode);
                break;
            case InputResource:
                inputResourceAction.set(storageCode);
                break;
            case InputWorker:
                inputWorkerAction.set(storageCode);
                break;
            case BuildingInformation:
                buildingInformationAction.set(storageCode);
                break;
            case BuildingDestroy:
                destroyAction.set(storageCode);
                break;
            case BuildingConnection:
                buildingConnection.set(storageCode);
                break;
            case ProductionManagement:
                productionManagementAction.set(storageCode);
                break;
            case ConnectManagement:
                connectManagementAction.set(storageCode);
                break;
        }
    }

    // 주 실행
    public boolean Update() {
        switch (Command) {
            case Construction:
                return buildingSelectAction.Update();
            case Collecting:
                return collectingAction.Update();
            case Information:
                return informationAction.Update();
            case Inventory:
                return inventoryAction.Update();
            case Make:
                return makeAction.Update();
            case Save:
                return saveAction.Update();
            case InputResource:
                return inputResourceAction.Update();
            case InputWorker:
                return inputWorkerAction.Update();
            case BuildingInformation:
                return buildingInformationAction.Update();
            case BuildingDestroy:
                return destroyAction.Update();
            case BuildingConnection:
                return buildingConnection.Update();
            case ProductionManagement:
                return productionManagementAction.Update();
            case ConnectManagement:
                return connectManagementAction.Update();
        }
        return true;
    }

    public void FirstRender(Canvas canvas) {
        if (Command == Construction) {
            buildingSelectAction.FirstRender(canvas);
        }
    }

    public void Render(Canvas canvas) {
        switch (Command) {
            case Construction:
                buildingSelectAction.Render(canvas);
                break;
            case Collecting:
                collectingAction.Render(canvas);
                break;
            case Information:
                informationAction.Render(canvas);
                break;
            case Inventory:
                inventoryAction.Render(canvas);
                break;
            case Make:
                makeAction.Render(canvas);
                break;
            case Save:
                saveAction.Render(canvas);
                break;
            case InputResource:
                inputResourceAction.Render(canvas);
                break;
            case InputWorker:
                inputWorkerAction.Render(canvas);
                break;
            case BuildingInformation:
                buildingInformationAction.Render(canvas);
                break;
            case BuildingDestroy:
                destroyAction.Render(canvas);
                break;
            case BuildingConnection:
                buildingConnection.Render(canvas);
                break;
            case ProductionManagement:
                productionManagementAction.Render(canvas);
                break;
            case ConnectManagement:
                connectManagementAction.Render(canvas);
                break;
        }
    }

    // UI 랜더
    public void UIRender(Canvas canvas) {
        switch (Command) {
            case Construction:
                buildingSelectAction.UIRender(canvas);
                break;
            case Collecting:
                collectingAction.UIRender(canvas);
                break;
            case Information:
                informationAction.UIRender(canvas);
                break;
            case Inventory:
                inventoryAction.UIRender(canvas);
                break;
            case Make:
                makeAction.UIRender(canvas);
                break;
            case Save:
                saveAction.UIRender(canvas);
                break;
            case InputResource:
                inputResourceAction.UIRender(canvas);
                break;
            case InputWorker:
                inputWorkerAction.UIRender(canvas);
                break;
            case BuildingInformation:
                buildingInformationAction.UIRender(canvas);
                break;
            case BuildingDestroy:
                destroyAction.UIRender(canvas);
                break;
            case BuildingConnection:
                buildingConnection.UIRender(canvas);
                break;
            case ProductionManagement:
                productionManagementAction.UIRender(canvas);
                break;
            case ConnectManagement:
                connectManagementAction.UIRender(canvas);
                break;
        }
    }
}
