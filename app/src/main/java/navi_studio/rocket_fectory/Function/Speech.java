package navi_studio.rocket_fectory.Function;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import navi_studio.rocket_fectory.Global.BlackSide;
import navi_studio.rocket_fectory.Global.FPS;
import navi_studio.rocket_fectory.Global.Global;
import navi_studio.rocket_fectory.Global.InputManager;
import navi_studio.rocket_fectory.R;

public class Speech {

    public final static int NORMAL = 0;
    public final static int HAPPY = 1;
    public final static int ANGRY = 2;

    public final static int Asurai = 0;

    Global global;                  //글로벌 변수
    LocaleManager localeManager;    //로켈 메니저
    String master;                  //뿌릴 스트링
    String temp;                    //임시 저장할 스트링

    Paint StringBackGroundPaint;

    int current_index;              //현재 읽고있는 인덱스
    int current_script_num;         //현재 스크립트 번호
    int end_script_num;             //종료 스크립트 번호
    int frame_manip_count;          //프레임 변경 스택 카운터
    int length;                     //문자열의 크기

    int count;                      // 애니메이션 프레임 위치
    float Max_Frame_count;          // fpsSetting / fps (디바이스 30프레임에 애니메이션 10프레임이면 3)
    float Frame_count;              // 프레임 스택
    int FrameSpeed;                 // 초당 넘어갈 프레임 속도
    boolean is_speak = true;        //출력 했는가
    boolean counter_running = true; //카운터를 실행 시키고 있는가
    boolean is_touched = false;       //터치했는가
    boolean is_speak_compelete = false;  //대화가 전부 끝났는가
    int size_count = 0;               //글자 갯수 카운트

    String[] Line;

    Bitmap[][] charactersImage;

    char char_id;
    char char_location;
    int emotion_index;

    int characterNumber;

    Vector2 position;

    SoundUnit Sound_String_Beeps_Loop;

    public void Create(LocaleManager _localeManager, String _start, String _end, Global _global) {
        master = new String();
        Line = new String[4];

        StringBackGroundPaint = new Paint();

        StringBackGroundPaint.setColor(Color.argb(150,0,0,0));

        for (int i = 0; i > Line.length; i++)
            Line[i] = new String();

        temp = new String();
        position = new Vector2();
        global = _global;

        FrameSpeed = 10;
        Max_Frame_count = FPS.fpsSetting / FrameSpeed;
        Frame_count = 0;
        count = 0;

        frame_manip_count = 0;
        localeManager = _localeManager;
        current_index = 0;    //읽는 인덱스 번호 초기화
        temp = localeManager.getScriptByNum(_start);                          //스크립트 복사해서 가져옴
        length = temp.length();
        current_script_num = localeManager.TEST_getIndexByNum(_start);    //현재 스크립트 인덱스 번호 가져옴
        end_script_num = localeManager.TEST_getIndexByNum(_end);          //종료 스크립트 인덱스 번호 가져옴
        char_id = localeManager.TEST_getScriptNumByIndex(current_script_num).charAt(6);    //캐릭터 변수 저장
        char_location = 'L';

        charactersImage = global.character.getImage();

        Sound_String_Beeps_Loop=new SoundUnit();
        Sound_String_Beeps_Loop.Create(SoundUnit.music,R.raw.lobby_02_asurai_voice,1,global.context);

    }

    public void ConverseTouchCheck() {
        Vector2 left_top = new Vector2(0, 600);
        Vector2 right_bottom = new Vector2(1920, 1080);
        Vector2 touch = new Vector2(global.inputManager.touch(0).x(),
                global.inputManager.touch(0).y());
        if (touch.x >= left_top.x && touch.x <= right_bottom.x &&
                touch.y >= left_top.y && touch.y <= left_top.y) {
            is_touched = true;
        }
    }

    public void masterBuilder() {
        if (is_speak) {
            if (current_script_num <= end_script_num) {
                if (!(count == length)) {
                    if (temp.charAt(count) != '>' && temp.charAt(count) != '<' && temp.charAt(count) != '|') {
                        if (length > 40) {
                            if (size_count >= 0 && size_count <= 40) {
                                Line[0] += temp.charAt(count);

                            } else if (size_count >= 41 && size_count <= 80) {
                                Line[1] += temp.charAt(count);

                            } else if (size_count >= 81 && size_count <= 120) {
                                Line[2] += temp.charAt(count);

                            } else if (size_count >= 121 && size_count <= 160) {
                                Line[3] += temp.charAt(count);

                            }
                            size_count++;

                            is_speak = false;
                        } else {
                            master += temp.charAt(count);
                            size_count++;

                            is_speak = false;
                        }

                    } else {
                        if (temp.charAt(count) == '<' || temp.charAt(count) == '>') {
                            frameManipulate(temp.charAt(count));
                            return;
                        } else if (temp.charAt(count) == '|') {
                            CharacterLocationAndEmotionManipulator(temp.charAt(++count));
                            return;
                        }

                    }
                } else {
                    counter_running = false;
                    initNextScript();
                }
            } else {
                count = 0;
                is_speak_compelete = true;
                return;
            }
        }
    }

    public void initNextScript() {
        temp = new String();
        for (int i = 0; i < Line.length; i++)
            Line[i] = "";

        master = new String();
        if (current_script_num == end_script_num)
            return;
        else
            current_script_num++;

        temp = localeManager.TEST_getScriptByIndex(current_script_num);
        length = temp.length();

        Frame_count = 0;
        count = 0;
        FrameSpeed = 10;
        frame_manip_count = 0;
        current_index = 0;    //읽는 인덱스 번호 초기화
        char_id = localeManager.TEST_getScriptNumByIndex(current_script_num).charAt(6);    //캐릭터 번호 새롭게 할당
        size_count = 0;   //글자 카운트 초기화
        is_touched = false;
        counter_running = true;
        return;
    }

    public void frameManipulate(char _state) {
        FrameSpeed = 10;
        if (_state == '>') {
            frame_manip_count++;
            for (int i = 1; i < 3; i++) {
                if (temp.charAt(count + i) == '>') {
                    frame_manip_count++;
                }
            }
            counter_running = false;
            count += frame_manip_count;
            FrameSpeed += frame_manip_count;
            counter_running = true;
        } else if (_state == '<') {
            frame_manip_count++;
            for (int i = 1; i < 3; i++) {
                if (temp.charAt(count + i) == '<') {
                    frame_manip_count++;
                }
            }
            counter_running = false;
            count += frame_manip_count;
            FrameSpeed -= frame_manip_count;
            counter_running = true;
        }
        frame_manip_count = 0;
    }

    public void CharacterLocationAndEmotionManipulator(char _input) {
        switch (_input) {
            case 'L':
                char_location = 'L';
                break;  //LEFT
            case 'R':
                char_location = 'R';
                break;  //RIGHT
            case 'C':
                char_location = 'C';
                break;  //CENTER

            case 'N':
                emotion_index = NORMAL;
                break;    //NORMAL
            case 'H':
                emotion_index = HAPPY;
                break;    //HAPPY
            case 'A':
                emotion_index = ANGRY;
                break;    //ANGRY
        }
        count += 2; //심볼과 그 다음 나오는 |을 제외 하기 위해
        return;
    }

    public void CharacterSelect(Canvas canvas, char _character_ID, char _location) {

        switch (_location) {
            case 'L':
                position.x = global.blackSide.UnitConversion(10, BlackSide.width);
                break;
            case 'R':
                position.x = global.blackSide.UnitConversion(1560, BlackSide.width);
                break;
            case 'C':
                position.x = global.blackSide.UnitConversion(610, BlackSide.width);
                break;
        }

        switch (_character_ID) {
            case 'A':
                characterNumber = Asurai;
                break;
        }

        canvas.drawBitmap(charactersImage[characterNumber][emotion_index],
                position.x,
                global.blackSide.UnitConversion(800, global.blackSide.height) -
                        charactersImage[characterNumber][emotion_index].getHeight(),
                null);

    }

    public void TouchCheck() {
        if (global.inputManager.touch(0).x() >= 0 && global.inputManager.touch(0).y() >= 800) {

        }

    }

    public boolean Update() {
        Max_Frame_count = FPS.fpsSetting / FrameSpeed;
        if (counter_running) {
            counter();
            ConverseTouchCheck();
            TouchCheck();
            masterBuilder();
            return false;
        }
        if (!is_speak_compelete) {
            Sound_String_Beeps_Loop.play(100.0f);
            return false;
        } else {
            Sound_String_Beeps_Loop.stop();
            return true;
        }

    }

    public void Render(Canvas canvas) {

        CharacterSelect(canvas, char_id, char_location);

        canvas.drawRect(
                global.blackSide.UnitConversion(-1, global.blackSide.width),
                global.blackSide.UnitConversion(800, global.blackSide.height),
                global.blackSide.UnitConversion(1921, global.blackSide.width),
                global.blackSide.UnitConversion(1081, global.blackSide.height),
                StringBackGroundPaint);

        if (counter_running) {
            //solo
            canvas.drawText(master,
                    global.blackSide.UnitConversion(960, global.blackSide.width),
                    global.blackSide.UnitConversion(800, global.blackSide.height),
                    null);

            //Multi Lines
            for (int i = 0; i < Line.length; i++)
                canvas.drawText(Line[i],
                        global.blackSide.UnitConversion(960, global.blackSide.width),
                        global.blackSide.UnitConversion(800, global.blackSide.height),
                        null);
        }

        //still image making
        canvas.drawText(master,
                global.blackSide.UnitConversion(960, global.blackSide.width),
                global.blackSide.UnitConversion(800, global.blackSide.height),
                null);

        for (int i = 0; i < Line.length; i++)
        canvas.drawText(Line[i],
                global.blackSide.UnitConversion(960, global.blackSide.width),
                global.blackSide.UnitConversion(800, global.blackSide.height),
                null);
    }

    private void counter() {
        if (counter_running) {
            Frame_count++;                                  // 프레임당 1씩 증가합니다.
            if (Frame_count > Max_Frame_count) {           // 프레임 카운터가 Max_Frame_count보다 크면 통과
                Frame_count -= Max_Frame_count;           // 프레임 카운터를 Max_Frame_count만큼 뺀다.
                is_speak = true;
                //  카운터
                if (count <= length) {
                    count++;
                }
            }
        }
    }
}