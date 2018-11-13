package navi_studio.rocket_fectory.Function;

import navi_studio.rocket_fectory.Global.Target;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

//2D Vector Class
public class Vector2 {

    public int x;
    public int y;

    public Vector2() {                //기본생성자 매개변수 없음
        x = 0;
        y = 0;
    }

    public Vector2(int _x, int _y) {        //기본생성자 매개변수 있음
        x = _x;
        y = _y;
    }

    public void setVector(int _x, int _y) {
        x = _x;
        y = _y;
    }

    public void setVector(Vector2 vector) {
        x = vector.x;
        y = vector.y;
    }

    public float ForDistance(Vector2 target) {
        return (float) (sqrt(pow(target.x - x, 2) + pow(target.y - y, 2)));  //두점 사이의 거리 선택된 벡터 위치 기반
        // 두 점 사이의 거리 반환
    }

    public Vector2 ForCenterVec(Vector2 target){
        return new Vector2(
                (int)(Math.abs(x - target.x) *0.5f),
                (int)(Math.abs(y - target.y) *0.5f)
        );
    }

    public int ForDistanceLow(Vector2 target) {
        return (int)((Math.abs((x - target.x)) + Math.abs((y - target.y))) * 0.65f);
        // 두 점 사이의 거리 반환 값이 불확실
    }

    public float ForAngle(Vector2 target) {
        return (float) ((Math.atan2(target.x - x, target.y - y)) * 57.3f);
        // 두 점 사이의 각도 반환
    }

}
