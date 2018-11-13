package navi_studio.rocket_fectory.Global;

import java.util.Set;

import navi_studio.rocket_fectory.Function.Vector2;

public class Camera {

    protected Vector2 position;
    protected ScreenSize size;
    public int range = 200;        //카메라 렌더 범위 설정 (화면 밖의 렌더 포인트 범위)

    public Camera() {
        setting();
    }

    public Camera(Vector2 _position, Vector2 _size) {                          //매개변수 벡터 : 벡터
        setting();
        position.x = _position.x;
        position.y = _position.y;
        size.width = _size.x;
        size.height = _size.y;
    }

    // 생성자에서 호출하는 함수입니다.
    public void setting() {
        position = new Vector2();
        size = new ScreenSize();
    }

    public void Create(Vector2 _position, Vector2 _size,int _range) {                     //매개변수 벡터 : 벡터
        range = _range;
        position.x = _position.x;
        position.y = _position.y;
        size.width = _size.x;
        size.height = _size.y;
    }

    public void Create(Vector2 _size, int _range) {                     //매개변수 벡터 : 벡터
        range = _range;
        position = new Vector2(0, 0);
        size.width = _size.x;
        size.height = _size.y;
    }

    public void setPos(Vector2 _position) {    //위치 좌표 설정
        position = _position;
    }

    public void setPos(int _x,int _y) { position.x=_x; position.y=_y; }

    public void setPos_X(int _x) { position.x = _x; }

    public void setPos_Y(int _y) { position.y = _y; }

    public Vector2 getPos() { return position; }//좌표 반환

    public int getSizeWidth() {
        return size.width;
    }

    public int getSizeHeight() {
        return size.height;
    }
}
