package navi_studio.rocket_fectory.Object.Manager;

import navi_studio.rocket_fectory.Function.Vector2;
import navi_studio.rocket_fectory.Global.Camera;
import navi_studio.rocket_fectory.Global.Global;
import navi_studio.rocket_fectory.Object.Basic.Conveyor;
import navi_studio.rocket_fectory.Object.Core.Object;

public class Intersection {

    private Global global;

    public Intersection(Global _global){
        global = _global;
    }

    // 샐렉트 렌더링 부분 (교집합 단어 사용)
    // 오브젝트
    public boolean select(Object obj, Camera camera) {

        // 화면 왼쪽 아래부터 오른쪽 위의 셀렉트 렌더링 부분 감별 코드
        if (    obj.getPosX() > ((camera.getPos().x - (camera.getSizeWidth() / 2)) - camera.range) &&
                obj.getPosX() < ((camera.getPos().x + (camera.getSizeWidth() / 2)) + camera.range) &&
                obj.getPosY() > ((camera.getPos().y - (camera.getSizeHeight() / 2)) - camera.range) &&
                obj.getPosY() < ((camera.getPos().y + (camera.getSizeHeight() / 2)) + camera.range)) {
            // 오브젝트 렌더링
            obj.setSelect(true);
            return true;
        } else {
            // 렌더링 부분 밖 렌더링 안함
            obj.setSelect(false);
            return false;
        }
    }

    // 샐렉트 렌더링 부분 (교집합 단어 사용)
    // 컨베이어
    public boolean select(Conveyor conveyor, Camera camera) {

        if (conveyor.getStart().getPos().x > ((camera.getPos().x - (camera.getSizeWidth() / 2)) - camera.range) &&
                conveyor.getStart().getPos().x < ((camera.getPos().x + (camera.getSizeWidth() / 2)) + camera.range) &&
                conveyor.getStart().getPos().y > ((camera.getPos().y - (camera.getSizeHeight() / 2)) - camera.range) &&
                conveyor.getStart().getPos().y < ((camera.getPos().y + (camera.getSizeHeight() / 2)) + camera.range)) {
            // 컨베이어 시작부분이 출력범위 안에 있을경우
            conveyor.setSelect(true);
            return true;
        } else if (conveyor.getTarget().getPos().x > ((camera.getPos().x - (camera.getSizeWidth() / 2)) - camera.range) &&
                conveyor.getTarget().getPos().x < ((camera.getPos().x + (camera.getSizeWidth() / 2)) + camera.range) &&
                conveyor.getTarget().getPos().y > ((camera.getPos().y - (camera.getSizeHeight() / 2)) - camera.range) &&
                conveyor.getTarget().getPos().y < ((camera.getPos().y + (camera.getSizeHeight() / 2)) + camera.range)) {
            // 컨베이어 끝부분이 출력범위 안에 있을경우
            conveyor.setSelect(true);
            return true;
        } else
            return false;
    }
}