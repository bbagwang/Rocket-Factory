package navi_studio.rocket_fectory.Object.Basic;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.widget.ImageView;

import java.util.ArrayList;

import navi_studio.rocket_fectory.Action.Manager.ActionManager;
import navi_studio.rocket_fectory.Function.Animation;
import navi_studio.rocket_fectory.Function.LocaleManager;
import navi_studio.rocket_fectory.Function.Vector2;
import navi_studio.rocket_fectory.Global.Camera;
import navi_studio.rocket_fectory.Global.FPS;
import navi_studio.rocket_fectory.Global.Global;
import navi_studio.rocket_fectory.Global.Target;
import navi_studio.rocket_fectory.Object.Core.Object;
import navi_studio.rocket_fectory.Part.ItemCell;

public class Building extends Object {
    // 건물 오브젝트 입니다.

    protected int ManagementNumber;         // 메니저 넘버

    protected boolean isCompleted;          // 건설 완료

    protected boolean Switch;           // 작동 상황 저장

    public int storageMaxSize = 100;  // 생산품 임시 저장 공간

    public final static int FrameSpeed = 8;             // 오브젝트 애니메이션 속도

    protected Animation animation;                        // 애니메이션 팩

    protected Bitmap[] image;                             // 기본 이미지 팩

    private int acceptItemMaxSize;   // 받아들일 수 있는 아이템 무게
    protected int[] acceptID;         // 받아들일 아이템 품목

    protected ItemCell[] requiredItem;      // 건설에 필요한 아이템
    protected boolean[] checkItem;            // 투입된 아이템

    protected int outPutID;          // 제작된 아이템 ID
    protected int outPutCount;       // 제작된 아이템 개수

    private int inConnectMaxCount;                 // 연결 가능한 오브젝트 개수 입니다.       ( IN )
    protected ArrayList<Conveyor> inConnect;       // 연결되어 있는 오브젝트                ( IN )
    protected int inConnectCount;                  // 현제 연결되어 있는 오브젝트 개수        ( IN )

    private int outConnectMaxCount;                 // 연결 가능한 오브젝트 개수 입니다.       ( OUT )
    protected ArrayList<Conveyor> outConnect;       // 연결되어 있는 오브젝트                ( OUT )
    protected int outConnectCount;                  // 현제 연결되어 있는 오브젝트 개수        ( OUT )

    protected int Action;                     // 행동가능 개수
    protected String[] Command;               // 행동 명령

    public void Create(Vector2 _pos, int _size, Bitmap[] AnimationImage, Bitmap[] _image,
                       int _acceptItemMaxSize, int _inConnectMaxCount, int _outConnectMaxCount, int _setCode, boolean check) {
        Create(_pos, _size, Object.BUILDING);
        image = _image;

        inConnect = new ArrayList<Conveyor>();
        outConnect = new ArrayList<Conveyor>();

        animation = new Animation();
        animation.Create(Animation.CENTER,
                position.x, position.y,
                AnimationImage, FrameSpeed);
        inConnectMaxCount = _inConnectMaxCount;
        outConnectMaxCount = _outConnectMaxCount;
        acceptItemMaxSize = _acceptItemMaxSize;
        inConnectCount = 0;
        outConnectCount = 0;
        isCompleted = false;
        setCode(_setCode);
        Action = 0;
        Command = new String[0];
    }

    public void RequiredItemSetting(ItemCell[] itemCells) {
        requiredItem = itemCells;

        checkItem = new boolean[requiredItem.length];

    }

    protected void StartCommandSetting() {
        LocaleManager localeManager = new LocaleManager();
        localeManager.init("BuildingCommand");

        Command = new String[4];

        Command[0] = localeManager.getScriptByNum("ALL001C001");
        Command[1] = localeManager.getScriptByNum("ALL001C002");
        Command[2] = localeManager.getScriptByNum("ALL001C003");
        Command[3] = localeManager.getScriptByNum("ALL001C004");
    }

    public void Completed() {
        isCompleted = true;

        Switch = true;

        LocaleManager localeManager = new LocaleManager();
        localeManager.init("BuildingCommand");

        Command = new String[4];

        Command[0] = localeManager.getScriptByNum("ALL002C001");
        Command[1] = localeManager.getScriptByNum("ALL002C002");
        Command[2] = localeManager.getScriptByNum("ALL002C003");
        Command[3] = localeManager.getScriptByNum("ALL002C004");

        requiredItem = null;
    }

    // 닫으며 Command 입력
    // command 에 맞는 행동 실행
    public int close(int command) {
        if (!isCompleted) {
            // 건물이 건설 완료가 안되어있을 경우
            switch (command) {
                case 0:
                    // 자원투입
                    return ActionManager.InputResource;
                case 1:
                    // 인력투입
                    return ActionManager.InputWorker;
                case 2:
                    // 정보
                    return ActionManager.BuildingInformation;
                case 3:
                    // 건설취소
                    destroy = true;
                    return ActionManager.FAIL;
            }
        } else if (isCompleted) {
            if (!Switch) {
                // 건물이 건설 완료가 되어있지만 작동을 하고있지 않을경우
            } else if (Switch) {
                // 건물이 건설 완료가 되어있고 작동중일 경우
                switch (command) {
                    case 0:
                        // 연결
                        if (outConnectCount == outConnectMaxCount)
                            return ActionManager.FAIL;

                        return ActionManager.BuildingConnection;
                    case 1:
                        // 관리
                        return ManagementNumber;
                    case 2:
                        // 조사
                        return ActionManager.ConnectManagement;
                    case 3:
                        // 파괴
                        return ActionManager.BuildingDestroy;
                }
            }
        }
        return 0;
    }

    public void insertItem(int Number) {
        checkItem[Number] = true;
    }

    public final ItemCell[] RequiredItem() {
        return requiredItem;
    }

    public final boolean[] CheckItem() {
        return checkItem;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public boolean isSwitch() {
        return Switch;
    }

    public boolean Switching() {
        Switch = !Switch;
        return Switch;
    }

    public void setSwitch(boolean _Switch) {
        Switch = _Switch;
    }

    public final int getSize() {
        return size;
    }

    // 열기
    public String[] open() {
        return Command;
    }

    // 아이템을 받지 않습니다.
    public void acceptClear() {
        acceptID = null;
    }

    // 연결 컨베이어 반환
    public final ArrayList<Conveyor> getInConnect() {
        return inConnect;
    }

    // 오브젝트 연결
    public final ArrayList<Conveyor> getOutConnect() {
        return outConnect;
    }

    // 오브젝트 연결
    public boolean inConnect(Conveyor conveyor) {
        if (inConnectCount == inConnectMaxCount)
            return false;

        inConnect.add(conveyor);
        inConnectCount++;
        return true;
    }

    // 연결한 오브젝트 연결 해제
    public boolean inConnectClear(int number) {
        if (inConnectCount == 0)
            return false;

        inConnect.remove(number - 1);
        inConnectCount--;
        return true;
    }

    // 모든 오브젝트 연결 해제
    public void inConnectClear() {
        inConnectCount = 0;
    }

    public final int getInConnectMaxCount() {
        return inConnectMaxCount;
    }

    public final int getInConnectCount() {
        return inConnectCount;
    }

    // ====================================================================

    // 오브젝트 연결
    public boolean outConnect(Conveyor conveyor) {
        if (outConnectCount == outConnectMaxCount)
            return false;

        outConnect.add(conveyor);
        outConnectCount++;
        return true;
    }

    // 연결한 오브젝트 연결 해제
    public boolean outConnectClear(int number) {
        if (outConnectCount == 0)
            return false;

        outConnect.remove(number - 1);
        outConnectCount--;
        return true;
    }

    // 모든 오브젝트 연결 해제
    public void outConnectClear() {
        outConnectCount = 0;
    }

    public final int getOutConnectMaxCount() {
        return outConnectMaxCount;
    }

    public final int getOutConnectCount() {
        return outConnectCount;
    }

    public final Vector2 getPos() {
        return position;
    }

    @Override
    public boolean Update() {
        return destroy;
    }

    @Override
    public void Render(Canvas canvas, Camera camera) {

        if (!isCompleted) {
            canvas.drawBitmap(image[1],
                    position.x - (image[0].getWidth() / 2) - (camera.getPos().x - (camera.getSizeWidth() / 2)),
                    position.y - (image[0].getHeight() / 2) - (camera.getPos().y - (camera.getSizeHeight() / 2)), null);

        } else if (!Switch)
            canvas.drawBitmap(image[0],
                    position.x - (image[0].getWidth() / 2) - (camera.getPos().x - (camera.getSizeWidth() / 2)),
                    position.y - (image[0].getHeight() / 2) - (camera.getPos().y - (camera.getSizeHeight() / 2)), null);
        else
            animation.Render(canvas, camera);
    }
}
