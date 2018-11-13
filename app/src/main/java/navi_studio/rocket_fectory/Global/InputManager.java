package navi_studio.rocket_fectory.Global;

/**
 * Created by beanb on 2018-03-11.
 */

public class InputManager {

    public final static int UP          = 0;
    public final static int DOWN        = 1;

    private InputData originalData[];        // 입력 데이터 오리지널
    private InputData copyData[];             // 입력 데이터 복사
    private int Maxtouch;                   // 최대 터치 가능 수
    private int OverTouch;                  // 최대 터치 가능 수 초과 범위 저장

    public void InitInputManager(int _MaxTouch){

        OverTouch = 0;

        Maxtouch = _MaxTouch;
        originalData = new InputData[Maxtouch];
        copyData = new InputData[Maxtouch];
        for(int i = 0; i < Maxtouch ; i++){
            originalData[i] = new InputData();
            copyData[i] = new InputData();
        }
    }

    public void pushOverTouch(int _OverTouch){
        OverTouch = _OverTouch;
    }

    public int getOverTouch(){
        return  OverTouch;
    }

    // 터치 사이즈를 가져옵니다.
    public int getMaxtouch(){
        return Maxtouch;
    }

    // 실시간으로 변경되는 정보를 복사하여 저장합니다.
    public void setCopyData(){
        copyData = originalData;
    }

    // InputData에 신호를 저장합니다.
    public void Insert(int x, int y, int type){
        originalData[0].setInputData(x,y,type);
    }

    public void Insert(int x1, int y1, int type1,int x2, int y2, int type2){
        originalData[0].setInputData(x1,y1,type1);
        originalData[1].setInputData(x2,y2,type2);
    }
    public void Insert(int x1, int y1, int type1,int x2, int y2, int type2,int x3, int y3, int type3){
        originalData[0].setInputData(x1,y1,type1);
        originalData[1].setInputData(x2,y2,type2);
        originalData[2].setInputData(x3,y3,type3);
    }

    public InputData touch(int number){
        if(number > Maxtouch)
            number = Maxtouch;
        return copyData[number];
    }

    public InputData originalTouch(int number){
        if(number > Maxtouch)
            number = Maxtouch;
        return originalData[number];
    }
}
