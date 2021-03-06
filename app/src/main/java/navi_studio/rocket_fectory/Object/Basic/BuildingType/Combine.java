package navi_studio.rocket_fectory.Object.Basic.BuildingType;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import navi_studio.rocket_fectory.Function.Vector2;
import navi_studio.rocket_fectory.Global.Camera;
import navi_studio.rocket_fectory.Object.Basic.Building;
import navi_studio.rocket_fectory.Object.Core.ObjectCode;

/**
 * Created by beanb on 2018-04-01.
 */

public class Combine extends Building {
    // 조합공장

    // 위치
    // 크기
    // 이미지
    // 이미지 개수
    // fpsSetting
    // 연결가능 건물 개수
    // 받아들일 수 있는 아이템 개수
    // code
    // Level
    private int speed;
    // 조합에 사용될 아이탬 종류의 개수

    public void Create(Vector2 _pos, int _size, Bitmap[] AnimationImage, Bitmap[] _image,
                       int _acceptMaxSize, int _inConnectMaxCount, int _outConnectMaxCount, int _setCode,int _speed, int[] possibleItem) {
        Create(_pos, _size, AnimationImage, _image, _acceptMaxSize, _inConnectMaxCount,_outConnectMaxCount, _setCode,true);
        setCode(_setCode);
        speed = _speed;
        acceptID = possibleItem;
    }

    @Override
    public boolean Update() {
        return destroy;
    }
}
