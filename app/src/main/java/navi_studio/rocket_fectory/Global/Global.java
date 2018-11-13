package navi_studio.rocket_fectory.Global;

import android.content.Context;

/**
 * Created by beanb on 2018-03-29.
 */

public class Global {
    public static dTime dtime; // 시간 관련
    public static FPS fps; // fps 관련
    public static ScreenSize screenSize; // 화면 크기
    public static BlackSide blackSide; // 화면 크기 수정, 화면 단위
    public static InputManager inputManager; // 입력 신호 관리자
    public static Context context;          // 전역 context사용
    public static Option option;          // 옵션 데이터나 이후 추가될 데이터 저장용으로 사용됩니다.
    public static Camera camera;          // 인 게임용 카메라 입니다.
    public BitmapResource bitmapResource;        // Bitmap 을 저장합니다.
    public Character character;                     // 캐릭터 Resource 를 저장합니다.
    public static Pause pause;                      // 정지 신호
    public static LevelLimit levelLimit;           // 레벨 별 제한
    public static CharacterScript characterScript;
    public static Inventory inventory;
    public TextUnit textUnit;
    public static ItemCode itemCode;
}
