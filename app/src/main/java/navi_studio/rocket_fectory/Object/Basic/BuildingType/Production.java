package navi_studio.rocket_fectory.Object.Basic.BuildingType;

import android.graphics.Bitmap;

import navi_studio.rocket_fectory.Action.Manager.ActionManager;
import navi_studio.rocket_fectory.Function.Vector2;
import navi_studio.rocket_fectory.Object.Basic.Building;
import navi_studio.rocket_fectory.Global.ItemCode;

/**
 * Created by beanb on 2018-04-01.
 */

public class Production extends Building {
    // 생산공장

    // 위치
    // 크기
    private int speed;
    // 이미지
    // 이미지 개수
    // fpsSetting
    // 연결가능 건물 개수
    // 받아들일 수 있는 아이템 개수
    // code
    // Level
    private int[] availableMaterial;    // 채집 가능한 아이템 종류

    public void Create(Vector2 _pos, int _size, Bitmap[] AnimationImage, Bitmap[] _image,
                       int _acceptMaxSize, int _inConnectMaxCount, int _outConnectMaxCount, int _setCode, int _speed) {
        Create(_pos, _size, AnimationImage, _image, _acceptMaxSize,_inConnectMaxCount, _outConnectMaxCount, _setCode, true);
        speed = _speed;
        setCode(_setCode);
        acceptClear();

        ManagementNumber = ActionManager.ProductionManagement;
    }

    // 채굴 가능 아이템 셋팅
    public void AvailableMaterialSetting(int[] _availableMaterial) {
        availableMaterial = _availableMaterial;
    }

    // 채굴 가능 여부 검사
    public boolean Availability(int Material) {
        for (int i = 0; i < availableMaterial.length; i++)
            if (availableMaterial[i] == Material)
                return true;
        return false;
    }

    // Gesture 에서 선택된 자원을 가져와 설정
    public int MinedMaterialSearch(int _MinedMaterial) {
        for (int i = 0; i < availableMaterial.length; i++) {
            if (availableMaterial[i] == _MinedMaterial) {
                outPutID = _MinedMaterial;    // 생산될 아이템
                outPutCount = 0;             // 생산될 아이템 개수
                return _MinedMaterial;
            }
        }
        return ItemCode.FALSE;
    }

    // 자원 채집
    protected void Mined() {
    }

    @Override
    public boolean Update() {
        // 건설이 완료되어 있을 경우
        speedCount++;
        if (speedCount == speed) {
            Mined();
            speedCount = 0;
        }
        return destroy;
    }
}
