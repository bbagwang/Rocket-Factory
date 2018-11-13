package navi_studio.rocket_fectory.Function;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import navi_studio.rocket_fectory.Global.Global;
import navi_studio.rocket_fectory.R;

public class LocaleManager {
    Global global;
    ArrayList<Locale> script;

    enum ResID {TESTSCRIPT, DEFAULT}          //스크립트 추가될 때 마다 대문자로 추가

    int getResID(String filename) {        //ResID 리턴
        switch (filename) {                //스크립트 파일 이름
            case "Testscript":
                return R.raw.testscript;
            case "Game":
                return R.raw.gamek;
            case "BuildingCommandCenter":
                return R.raw.buildingcommandcenterk;
            case "BuildingTetoraCenter":
                return R.raw.buildingtetoracenterk;
            case "BuildingCommand":
                return R.raw.buildingcommandk;
            default:                        //에러일 경우
                return -1;
        }
    }

    public LocaleManager(String resName) {                //생성자에서 초기화
        init(resName);
    }

    public LocaleManager() {}

    public void setinit(String resName) {
        if (script.size() == 0)
            init(resName);
    }

    public void init(String resID) {
        String script_number;                                       //스크립트 넘버
        String script_message;                                      //스크립트 대사
        script = new ArrayList<Locale>();

        BufferedReader buf_reader = new BufferedReader(new InputStreamReader(global.context.getResources().openRawResource(getResID(resID))));
        try {
            script_number = buf_reader.readLine();                      //맨 처음 초기화를 위해 사용 //스크립트 번호
            while (!script_number.equals("*END*")) {                        //스크립트 넘버가 NULL이 아닐경우 읽음
                script_message = buf_reader.readLine();                 //스크립트 대사
                Locale temp = new Locale(script_number, script_message); //스크립트에 스크립트정보 저장후 할당
                script_number = buf_reader.readLine();                    //스크립트 넘버를 다음것으로 돌림
                script.add(temp);                                      //스크립트 ArrayList에 저장
            }
        } catch (IOException e) {               //IOException 핸들링
            e.printStackTrace();                //으응~? 뭐지~?
        }
    }
    public char TEST_getCharacterID(int index){
        String temp=TEST_getScriptNumByIndex(index);
        return temp.charAt(6);
    }
    public int TEST_getIndexByNum(String script_num){
        int i;
        for (i = 0; i < script.size(); i++) {
            if (script.get(i).script_num.equals(script_num))
                break;
            else
                continue;
        }
        return i;
    }

    public String TEST_getScriptByIndex(int index){
        return script.get(index).message;
    }

    public String TEST_getScriptNumByIndex(int index){
        return script.get(index).script_num;
    }

    public String getScriptByNum(String scriptCode) {
        int i;
        for (i = 0; i < script.size(); i++) {
            if (script.get(i).script_num.equals(scriptCode))
                return script.get(i).message;
        }
        return "Error";
    }
}
