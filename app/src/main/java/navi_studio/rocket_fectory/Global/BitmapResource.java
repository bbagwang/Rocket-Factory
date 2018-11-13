package navi_studio.rocket_fectory.Global;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;

import navi_studio.rocket_fectory.Function.Vector2;
import navi_studio.rocket_fectory.Part.ItemCell;
import navi_studio.rocket_fectory.R;

public class BitmapResource {

    public final static int Production = 0;             // 생산
    public final static int Smelting = 1;               // 제런
    public final static int Combine = 2;                // 조합
    public final static int Distribution = 3;           // 분배
    public final static int Consumption = 4;            // 소모
    public final static int Special = 5;                // 특수

    public final static int rawMaterialSize = 7;
    public final static int materialSize = 3;
    public final static int partsSize = 6;
    public final static int consumptionSize = 6;
    public final static int specialSize = 2;

    public boolean success = false;

    public final static int BuildingSize = 350;
    public final static int TetoraCenterMaxLevel = 10;

    public int[] BuildingMarkMax;                   // Mark 별 건물 개수를 저장합니다.
    public int TetoraCenterLevel;
    public int progress;
    private Global global;

    public Bitmap ActionBookImage;
    public Bitmap ActionBookScrollbar;
    public Bitmap[] CheckButton;
    public Bitmap[] CommandCenterImage;
    public Bitmap[][] TetoraCenterImage;
    public Bitmap[] ActionBookMark;
    public Bitmap[] ActionBookStamp;
    public Bitmap[] ActionBookHandle;
    public Bitmap[] ActionBackSpaceImage;
    public Bitmap[] ActionBookButton;
    public Bitmap[][] ActionBookBuildingStamp;             // 작은 사이즈의 건물 이미지를 저장합니다.

    public Bitmap[][][] BuildingImage;                           // MarkMax 로 끊어서 사용함.      분류, 번호, 애니메이션 관련
    public Bitmap[][][] BuildingAnimation;                       // MarkMax 로 끊어서 사용함.

    public Bitmap inventory_background;                    // 인벤토리 배경
    public Bitmap[] inventory_check_button;                // 체크 버튼
    public Bitmap[] inventory_down_button;                 // 감소 버튼
    public Bitmap[] inventory_up_button;                   // 증가 버튼
    public Bitmap[] inventory_mark;                        // 좌측 카테고리 마크
    public Bitmap inventory_scrollbar;                     // 스크롤바
    public Bitmap[] inventory_scroll_handle;               // 스크롤바 핸들
    public Bitmap[] inventory_stamp;                       // 메인 항목 프레임 (스탬프)
    public Bitmap[] inventory_calculator;                  // 계산기 입장

    public Bitmap gauge_percent;                           // 게이지 퍼센티지
    public Bitmap gauge_background;                        // 게이지 배경
    public Bitmap gauge_frame;                             // 게이지 프레임
    public Bitmap gauge_button;                            // 게이지 버튼

    int[] count;
    int type;

    public BitmapResource(Global _global) {
        progress = 0;
        global = _global;
    }

    public int Awake() {

        switch (progress) {
            case 0:
                BuildingMarkMax = new int[6];
                BuildingMarkMax[0] = 5;
                BuildingMarkMax[1] = 3;
                BuildingMarkMax[2] = 3;
                BuildingMarkMax[3] = 1;
                BuildingMarkMax[4] = 2;
                BuildingMarkMax[5] = 1;

                inventory_check_button = new Bitmap[4];
                inventory_down_button = new Bitmap[2];
                inventory_up_button = new Bitmap[2];
                inventory_mark = new Bitmap[2];
                inventory_stamp = new Bitmap[2];
                inventory_scroll_handle = new Bitmap[2];
                inventory_calculator = new Bitmap[3];

                count = new int[6];
                for(int i = 0; i < 6;i++)
                    count[i] = -1;
                break;
            case 1:
                // Mark 총합
                ActionBookBuildingStamp = new Bitmap[6][];

                inventory_background = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.inventorybackground);
                inventory_background = Bitmap.createScaledBitmap(inventory_background, global.blackSide.UnitConversion(1660), global.blackSide.UnitConversion(820), true);

                inventory_scrollbar = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.inventoryscrollbar);
                inventory_scrollbar = Bitmap.createScaledBitmap(inventory_scrollbar, global.blackSide.UnitConversion(80), global.blackSide.UnitConversion(770), true);

                gauge_background = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.inventorygaugebarbackground);
                gauge_background = Bitmap.createScaledBitmap(gauge_background, global.blackSide.UnitConversion(1280), global.blackSide.UnitConversion(180), true);

                gauge_frame = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.inventorygaugebarframe);
                gauge_frame = Bitmap.createScaledBitmap(gauge_frame, global.blackSide.UnitConversion(1280), global.blackSide.UnitConversion(180), true);

                gauge_button = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.inventorygaugebarbutton1);
                gauge_button = Bitmap.createScaledBitmap(gauge_button, global.blackSide.UnitConversion(370), global.blackSide.UnitConversion(180), true);
                gauge_percent = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.inventorygauge);
                gauge_percent = Bitmap.createScaledBitmap(gauge_percent, global.blackSide.UnitConversion(30), global.blackSide.UnitConversion(180), true);
                break;
            case 2:
                // 스템프 건물 미리보기 사이즈 지정   Mark 총합

                for (int i = 0; i < 6; i++) {
                    ActionBookBuildingStamp[i] = new Bitmap[BuildingMarkMax[i]];
                }

                ActionBackSpaceImage = new Bitmap[5];
                CheckButton = new Bitmap[5];

                inventory_check_button[0] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.inventorycheckbutton1);
                inventory_check_button[0] = Bitmap.createScaledBitmap(inventory_check_button[0], global.blackSide.UnitConversion(450), global.blackSide.UnitConversion(220), true);
                inventory_check_button[1] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.inventorycheckbutton2);
                inventory_check_button[1] = Bitmap.createScaledBitmap(inventory_check_button[1], global.blackSide.UnitConversion(450), global.blackSide.UnitConversion(220), true);
                inventory_check_button[2] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.inventorycheckbutton3);
                inventory_check_button[2] = Bitmap.createScaledBitmap(inventory_check_button[2], global.blackSide.UnitConversion(450), global.blackSide.UnitConversion(220), true);
                inventory_check_button[3] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.inventorycheckbutton4);
                inventory_check_button[3] = Bitmap.createScaledBitmap(inventory_check_button[3], global.blackSide.UnitConversion(450), global.blackSide.UnitConversion(220), true);
                break;
            case 3:
                ActionBackSpaceImage[0]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.actionscreenbackbutton1);
                CheckButton[0]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.btton1);
                ActionBackSpaceImage[1]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.actionscreenbackbutton2);
                CheckButton[1]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.btton2);
                ActionBackSpaceImage[2]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.actionscreenbackbutton3);
                CheckButton[2]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.btton3);
                ActionBackSpaceImage[3]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.actionscreenbackbutton4);
                CheckButton[3]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.btton4);
                ActionBackSpaceImage[4] = ActionBackSpaceImage[0];
                CheckButton[4]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.btton5);
                break;
            case 4:
                inventory_down_button[0] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.inventorydownbutton1);
                inventory_down_button[0] = Bitmap.createScaledBitmap(inventory_down_button[0], global.blackSide.UnitConversion(130), global.blackSide.UnitConversion(240), true);
                inventory_down_button[1] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.inventorydownbutton2);
                inventory_down_button[1] = Bitmap.createScaledBitmap(inventory_down_button[1], global.blackSide.UnitConversion(130), global.blackSide.UnitConversion(240), true);
                inventory_up_button[0] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.inventoryupbutton1);
                inventory_up_button[0] = Bitmap.createScaledBitmap(inventory_up_button[0], global.blackSide.UnitConversion(130), global.blackSide.UnitConversion(240), true);
                inventory_up_button[1] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.inventoryupbutton2);
                inventory_up_button[1] = Bitmap.createScaledBitmap(inventory_up_button[1], global.blackSide.UnitConversion(130), global.blackSide.UnitConversion(240), true);
                break;
            case 5:
                inventory_mark[0] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.inventorymark1);
                inventory_mark[0] = Bitmap.createScaledBitmap(inventory_mark[0], global.blackSide.UnitConversion(220), global.blackSide.UnitConversion(120), true);
                inventory_mark[1] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.inventorymark2);
                inventory_mark[1] = Bitmap.createScaledBitmap(inventory_mark[1], global.blackSide.UnitConversion(220), global.blackSide.UnitConversion(120), true);
                inventory_stamp[0] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.inventorystamp1);
                inventory_stamp[0] = Bitmap.createScaledBitmap(inventory_stamp[0], global.blackSide.UnitConversion(240), global.blackSide.UnitConversion(240), true);
                inventory_stamp[1] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.inventorystamp2);
                inventory_stamp[1] = Bitmap.createScaledBitmap(inventory_stamp[1], global.blackSide.UnitConversion(240), global.blackSide.UnitConversion(240), true);
                break;
            case 6:
                inventory_scroll_handle[0] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.inventoryscrollbarhandle1);
                inventory_scroll_handle[0] = Bitmap.createScaledBitmap(inventory_scroll_handle[0], global.blackSide.UnitConversion(80), global.blackSide.UnitConversion(120), true);
                inventory_scroll_handle[1] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.inventoryscrollbarhandle2);
                inventory_scroll_handle[1] = Bitmap.createScaledBitmap(inventory_scroll_handle[1], global.blackSide.UnitConversion(80), global.blackSide.UnitConversion(120), true);
                inventory_calculator[0] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.calculatorenter1);
                inventory_calculator[0] = Bitmap.createScaledBitmap(inventory_calculator[0], global.blackSide.UnitConversion(230), global.blackSide.UnitConversion(230), true);
                inventory_calculator[1] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.calculatorenter2);
                inventory_calculator[1] = Bitmap.createScaledBitmap(inventory_calculator[1], global.blackSide.UnitConversion(230), global.blackSide.UnitConversion(230), true);
                inventory_calculator[2] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.calculatorenter3);
                inventory_calculator[2] = Bitmap.createScaledBitmap(inventory_calculator[2], global.blackSide.UnitConversion(230), global.blackSide.UnitConversion(230), true);

                break;
            case 7:

                break;
            case 8:
                for (int i = 0; i < CheckButton.length; i++)
                    CheckButton[i] = Bitmap.createScaledBitmap(CheckButton[i],
                            global.blackSide.UnitConversion(220),
                            global.blackSide.UnitConversion(240), true);
                for (int i = 0; i < ActionBackSpaceImage.length; i++)
                    ActionBackSpaceImage[i] = Bitmap.createScaledBitmap(ActionBackSpaceImage[i],
                            global.blackSide.UnitConversion(200),
                            global.blackSide.UnitConversion(200), true);
                break;
            case 9:

                ActionBookImage
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.book);
                ActionBookImage = Bitmap.createScaledBitmap(ActionBookImage,
                        global.blackSide.UnitConversion(1690),
                        global.blackSide.UnitConversion(1040), true);

                break;
            case 10:
                ActionBookScrollbar
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.bookscrollbar);
                ActionBookScrollbar = Bitmap.createScaledBitmap(ActionBookScrollbar,
                        global.blackSide.UnitConversion(80),
                        global.blackSide.UnitConversion(910), true);
                break;
            case 11:
                ActionBookMark = new Bitmap[2];
                break;
            case 12:
                ActionBookMark[0]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.bookmark1);
                break;
            case 13:
                ActionBookMark[1]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.bookmark2);
                break;
            case 14:
                for (int i = 0; i < ActionBookMark.length; i++)
                    ActionBookMark[i] = Bitmap.createScaledBitmap(ActionBookMark[i],
                            global.blackSide.UnitConversion(222),
                            global.blackSide.UnitConversion(100), true);
                break;
            case 15:
                ActionBookButton = new Bitmap[5];
                break;
            case 16:
                ActionBookButton[0]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.bookselectbutton1);
                break;
            case 17:
                ActionBookButton[1]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.bookselectbutton2);
                break;
            case 18:
                ActionBookButton[2]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.bookselectbutton3);
                break;
            case 19:
                ActionBookButton[3]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.bookselectbutton4);
                break;
            case 20:
                ActionBookButton[4] = ActionBookButton[0];
                break;
            case 21:
                for (int i = 0; i < ActionBookButton.length; i++)
                    ActionBookButton[i] = Bitmap.createScaledBitmap(ActionBookButton[i],
                            global.blackSide.UnitConversion(680),
                            global.blackSide.UnitConversion(150), true);
                break;
            case 22:
                ActionBookStamp = new Bitmap[2];
                break;
            case 23:
                ActionBookStamp[0]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.bookstamp1);
                break;
            case 24:
                ActionBookStamp[1]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.bookstamp2);
                break;
            case 25:
                for (int i = 0; i < ActionBookStamp.length; i++)
                    ActionBookStamp[i] = Bitmap.createScaledBitmap(ActionBookStamp[i],
                            global.blackSide.UnitConversion(230),
                            global.blackSide.UnitConversion(230), true);
                break;
            case 26:
                ActionBookHandle = new Bitmap[2];
                break;
            case 27:
                ActionBookHandle[0]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.bookscrollhandle1);
                break;
            case 28:
                ActionBookHandle[1]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.bookscrollhandle2);
                break;
            case 29:
                for (int i = 0; i < ActionBookHandle.length; i++)
                    ActionBookHandle[i] = Bitmap.createScaledBitmap(ActionBookHandle[i],
                            global.blackSide.UnitConversion(80),
                            global.blackSide.UnitConversion(200), true);
                break;
            case 30:
                BuildingImage = new Bitmap[6][][];
                for (int i = 0; i < 6; i++)
                    BuildingImage[i] = new Bitmap[BuildingMarkMax[i]][];
                break;
            case 31:
                BuildingAnimation = new Bitmap[6][][];
                for (int i = 0; i < 6; i++)
                    BuildingAnimation[i] = new Bitmap[BuildingMarkMax[i]][];
                break;
            case 32:
                CommandCenterImage = new Bitmap[6];
                break;
            case 33:
                CommandCenterImage[0] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.commandcenter1);
                break;
            case 34:
                CommandCenterImage[1] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.commandcenter2);
                break;
            case 35:
                CommandCenterImage[2] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.commandcenter3);
                break;
            case 36:
                CommandCenterImage[3] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.commandcenter4);
                break;
            case 37:
                CommandCenterImage[4] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.commandcenter5);
                break;
            case 38:
                CommandCenterImage[5] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.commandcenter6);
                break;
            case 39:
                for (int i = 0; i < CommandCenterImage.length; i++)
                    CommandCenterImage[i] = Bitmap.createScaledBitmap(CommandCenterImage[i],
                            global.blackSide.UnitConversion(BuildingSize),
                            global.blackSide.UnitConversion(BuildingSize), true);
                break;
            case 40:
                TetoraCenterImage = new Bitmap[TetoraCenterMaxLevel][];
                break;
            case 41:
                TetoraCenterLevel = 0;

                TetoraCenterImage[TetoraCenterLevel] = new Bitmap[6];
                break;
            case 42:
                TetoraCenterImage[TetoraCenterLevel][0] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.tetoracenter1level1);
                break;
            case 43:
                TetoraCenterImage[TetoraCenterLevel][1] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.tetoracenter1level2);
                break;
            case 44:
                TetoraCenterImage[TetoraCenterLevel][2] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.tetoracenter1level3);
                break;
            case 45:
                TetoraCenterImage[TetoraCenterLevel][3] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.tetoracenter1level4);
                break;
            case 46:
                TetoraCenterImage[TetoraCenterLevel][4] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.tetoracenter1level5);
                break;
            case 47:
                TetoraCenterImage[TetoraCenterLevel][5] = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.tetoracenter1level6);
                break;
            case 48:
                for (int i = 0; i < TetoraCenterImage[TetoraCenterLevel].length; i++)
                    TetoraCenterImage[TetoraCenterLevel][i] = Bitmap.createScaledBitmap(TetoraCenterImage[TetoraCenterLevel][i],
                            global.blackSide.UnitConversion(BuildingSize),
                            global.blackSide.UnitConversion(BuildingSize), true);
                break;
            case 49:
                type = Production;
                count[type]++;
                BuildingImage[type][count[type]] = new Bitmap[2];
                break;
            case 50:
                BuildingImage[type][count[type]][0]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.productionlogstop);
                break;
            case 51:
                BuildingImage[type][count[type]][1]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.productionlog1level);
                break;
            case 52:
                for (int x = 0; x < BuildingImage[type][count[type]].length; x++)
                    BuildingImage[type][count[type]][x] = Bitmap.createScaledBitmap(BuildingImage[type][count[type]][x],
                            global.blackSide.UnitConversion(BuildingSize),
                            global.blackSide.UnitConversion(BuildingSize), true);
                break;
            case 53:
                BuildingAnimation[type][count[type]] = new Bitmap[6];
                break;
            case 54:
                BuildingAnimation[type][count[type]][0]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.productionlog1);
                break;
            case 55:
                BuildingAnimation[type][count[type]][1]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.productionlog2);
                break;
            case 56:
                BuildingAnimation[type][count[type]][2]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.productionlog3);
                break;
            case 57:
                BuildingAnimation[type][count[type]][3]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.productionlog4);
                break;
            case 58:
                BuildingAnimation[type][count[type]][4]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.productionlog5);
                break;
            case 59:
                BuildingAnimation[type][count[type]][5]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.productionlog6);
                break;
            case 60:
                for (int x = 0; x < BuildingAnimation[type][count[type]].length; x++)
                    BuildingAnimation[type][count[type]][x] = Bitmap.createScaledBitmap(BuildingAnimation[type][count[type]][x],
                            global.blackSide.UnitConversion(BuildingSize),
                            global.blackSide.UnitConversion(BuildingSize), true);
                break;
            case 61:
                type = Production;
                count[type]++;
                BuildingImage[type][count[type]] = new Bitmap[2];
                break;
            case 62:
                BuildingImage[type][count[type]][0]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.productionquarrystop);
                break;
            case 63:
                BuildingImage[type][count[type]][1]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.productionquarry1level);
                break;
            case 64:
                for (int x = 0; x < BuildingImage[type][count[type]].length; x++)
                    BuildingImage[type][count[type]][x] = Bitmap.createScaledBitmap(BuildingImage[type][count[type]][x],
                            global.blackSide.UnitConversion(BuildingSize),
                            global.blackSide.UnitConversion(BuildingSize), true);
                break;
            case 65:
                BuildingAnimation[type][count[type]] = new Bitmap[6];
                break;
            case 66:
                BuildingAnimation[type][count[type]][0]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.productionquarry1);
                break;
            case 67:
                BuildingAnimation[type][count[type]][1]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.productionquarry2);
                break;
            case 68:
                BuildingAnimation[type][count[type]][2]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.productionquarry3);
                break;
            case 69:
                BuildingAnimation[type][count[type]][3]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.productionquarry4);
                break;
            case 70:
                BuildingAnimation[type][count[type]][4]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.productionquarry5);
                break;
            case 71:
                BuildingAnimation[type][count[type]][5]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.productionquarry6);
                break;
            case 72:
                for (int x = 0; x < BuildingAnimation[type][count[type]].length; x++)
                    BuildingAnimation[type][count[type]][x] = Bitmap.createScaledBitmap(BuildingAnimation[type][count[type]][x],
                            global.blackSide.UnitConversion(BuildingSize),
                            global.blackSide.UnitConversion(BuildingSize), true);
                break;
            case 73:
                type = Smelting;
                count[type]++;
                BuildingImage[type][count[type]] = new Bitmap[2];
                break;
            case 74:
                BuildingImage[type][count[type]][0]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.smeltingbrazierlowstop);
                break;
            case 75:
                BuildingImage[type][count[type]][1]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.smeltingbrazierlow1level);
                break;
            case 76:
                for (int x = 0; x < BuildingImage[type][count[type]].length; x++)
                    BuildingImage[type][count[type]][x] = Bitmap.createScaledBitmap(BuildingImage[type][count[type]][x],
                            global.blackSide.UnitConversion(BuildingSize),
                            global.blackSide.UnitConversion(BuildingSize), true);
                break;
            case 77:
                BuildingAnimation[type][count[type]] = new Bitmap[6];
                break;
            case 78:
                BuildingAnimation[type][count[type]][0]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.smeltingbrazierlow1);
                break;
            case 79:
                BuildingAnimation[type][count[type]][1]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.smeltingbrazierlow2);
                break;
            case 80:
                BuildingAnimation[type][count[type]][2]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.smeltingbrazierlow3);
                break;
            case 81:
                BuildingAnimation[type][count[type]][3]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.smeltingbrazierlow4);
                break;
            case 82:
                BuildingAnimation[type][count[type]][4]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.smeltingbrazierlow5);
                break;
            case 83:
                BuildingAnimation[type][count[type]][5]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.smeltingbrazierlow6);
                break;
            case 84:
                for (int x = 0; x < BuildingAnimation[type][count[type]].length; x++)
                    BuildingAnimation[type][count[type]][x] = Bitmap.createScaledBitmap(BuildingAnimation[type][count[type]][x],
                            global.blackSide.UnitConversion(BuildingSize),
                            global.blackSide.UnitConversion(BuildingSize), true);
                break;
            case 85:
                type = Smelting;
                count[type]++;
                BuildingImage[type][count[type]] = new Bitmap[2];
                break;
            case 86:
                BuildingImage[type][count[type]][0]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.smeltingbrazierhighstop);
                break;
            case 87:
                BuildingImage[type][count[type]][1]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.smeltingbrazierlow1level);
                break;
            case 88:
                for (int x = 0; x < BuildingImage[type][count[type]].length; x++)
                    BuildingImage[type][count[type]][x] = Bitmap.createScaledBitmap(BuildingImage[type][count[type]][x],
                            global.blackSide.UnitConversion(BuildingSize),
                            global.blackSide.UnitConversion(BuildingSize), true);
                break;
            case 89:
                BuildingAnimation[type][count[type]] = new Bitmap[6];
                break;
            case 90:
                BuildingAnimation[type][count[type]][0]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.smeltingbrazierhigh1);
                break;
            case 91:
                BuildingAnimation[type][count[type]][1]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.smeltingbrazierhigh2);
                break;
            case 92:
                BuildingAnimation[type][count[type]][2]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.smeltingbrazierhigh3);
                break;
            case 93:
                BuildingAnimation[type][count[type]][3]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.smeltingbrazierhigh4);
                break;
            case 94:
                BuildingAnimation[type][count[type]][4]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.smeltingbrazierhigh5);
                break;
            case 95:
                BuildingAnimation[type][count[type]][5]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.smeltingbrazierhigh6);
                break;
            case 96:
                for (int x = 0; x < BuildingAnimation[type][count[type]].length; x++)
                    BuildingAnimation[type][count[type]][x] = Bitmap.createScaledBitmap(BuildingAnimation[type][count[type]][x],
                            global.blackSide.UnitConversion(BuildingSize),
                            global.blackSide.UnitConversion(BuildingSize), true);
                break;
            case 97:
                type = Smelting;
                count[type]++;
                BuildingImage[type][count[type]] = new Bitmap[2];
                break;
            case 98:
                BuildingImage[type][count[type]][0]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.smeltingtierwindmillstop);
                break;
            case 99:
                BuildingImage[type][count[type]][1]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.smeltingtierwindmill1level);
                break;
            case 100:
                for (int x = 0; x < BuildingImage[type][count[type]].length; x++)
                    BuildingImage[type][count[type]][x] = Bitmap.createScaledBitmap(BuildingImage[type][count[type]][x],
                            global.blackSide.UnitConversion(BuildingSize),
                            global.blackSide.UnitConversion(BuildingSize), true);
                break;
            case 101:
                BuildingAnimation[type][count[type]] = new Bitmap[4];
                break;
            case 102:
                BuildingAnimation[type][count[type]][0]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.smeltingtierwindmill1);
                break;
            case 103:
                BuildingAnimation[type][count[type]][1]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.smeltingtierwindmill2);
                break;
            case 104:
                BuildingAnimation[type][count[type]][2]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.smeltingtierwindmill3);
                break;
            case 105:
                BuildingAnimation[type][count[type]][3]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.smeltingtierwindmill4);
                break;
            case 106:
                for (int x = 0; x < BuildingAnimation[type][count[type]].length; x++)
                    BuildingAnimation[type][count[type]][x] = Bitmap.createScaledBitmap(BuildingAnimation[type][count[type]][x],
                            global.blackSide.UnitConversion(BuildingSize),
                            global.blackSide.UnitConversion(BuildingSize), true);
                break;
            case 107:
                type = Production;
                count[type]++;
                BuildingImage[type][count[type]] = new Bitmap[2];
                break;
            case 108:
                BuildingImage[type][count[type]][0]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.productioncornfield1);
                break;
            case 109:
                BuildingImage[type][count[type]][1]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.productioncornfield1level);
                break;
            case 110:
                for (int x = 0; x < BuildingImage[type][count[type]].length; x++)
                    BuildingImage[type][count[type]][x] = Bitmap.createScaledBitmap(BuildingImage[type][count[type]][x],
                            global.blackSide.UnitConversion(BuildingSize),
                            global.blackSide.UnitConversion(BuildingSize), true);
                BuildingAnimation[type][count[type]] = new Bitmap[6];
                break;
            case 111:
                BuildingAnimation[type][count[type]][0]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.productioncornfield1);
                break;
            case 112:
                BuildingAnimation[type][count[type]][1]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.productioncornfield2);
                break;
            case 113:
                BuildingAnimation[type][count[type]][2]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.productioncornfield3);
                break;
            case 114:
                BuildingAnimation[type][count[type]][3]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.productioncornfield4);
                break;
            case 115:
                BuildingAnimation[type][count[type]][4]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.productioncornfield5);
                break;
            case 116:
                BuildingAnimation[type][count[type]][5]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.productioncornfield6);
                break;
            case 117:
                for (int x = 0; x < BuildingAnimation[type][count[type]].length; x++)
                    BuildingAnimation[type][count[type]][x] = Bitmap.createScaledBitmap(BuildingAnimation[type][count[type]][x],
                            global.blackSide.UnitConversion(BuildingSize),
                            global.blackSide.UnitConversion(BuildingSize), true);
                break;
            case 118:
                type = Production;
                count[type]++;
                BuildingImage[type][count[type]] = new Bitmap[2];
                break;
            case 119:
                BuildingImage[type][count[type]][0]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.productionwellstop);
                break;
            case 120:
                BuildingImage[type][count[type]][1]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.productionwell1level);
                break;
            case 121:
                for (int x = 0; x < BuildingImage[type][count[type]].length; x++)
                    BuildingImage[type][count[type]][x] = Bitmap.createScaledBitmap(BuildingImage[type][count[type]][x],
                            global.blackSide.UnitConversion(BuildingSize),
                            global.blackSide.UnitConversion(BuildingSize), true);
                BuildingAnimation[type][count[type]] = new Bitmap[6];
                break;
            case 122:
                BuildingAnimation[type][count[type]][0]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.productionwell1);
                break;
            case 123:
                BuildingAnimation[type][count[type]][1]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.productionwell2);
                break;
            case 124:
                BuildingAnimation[type][count[type]][2]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.productionwell3);
                break;
            case 125:
                BuildingAnimation[type][count[type]][3]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.productionwell4);
                break;
            case 126:
                BuildingAnimation[type][count[type]][4]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.productionwell5);
                break;
            case 127:
                BuildingAnimation[type][count[type]][5]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.productionwell6);
                break;
            case 128:
                for (int x = 0; x < BuildingAnimation[type][count[type]].length; x++)
                    BuildingAnimation[type][count[type]][x] = Bitmap.createScaledBitmap(BuildingAnimation[type][count[type]][x],
                            global.blackSide.UnitConversion(BuildingSize),
                            global.blackSide.UnitConversion(BuildingSize), true);
                break;
            case 129:
                type = Production;
                count[type]++;
                BuildingImage[type][count[type]] = new Bitmap[2];
                break;
            case 130:
                BuildingImage[type][count[type]][0]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.productionminestop);
                break;
            case 131:
                BuildingImage[type][count[type]][1]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.productionmine1level);
                break;
            case 132:
                for (int x = 0; x < BuildingImage[type][count[type]].length; x++)
                    BuildingImage[type][count[type]][x] = Bitmap.createScaledBitmap(BuildingImage[type][count[type]][x],
                            global.blackSide.UnitConversion(BuildingSize),
                            global.blackSide.UnitConversion(BuildingSize), true);
                BuildingAnimation[type][count[type]] = new Bitmap[6];
                break;
            case 133:
                BuildingAnimation[type][count[type]][0]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.productionmine1);
                break;
            case 134:
                BuildingAnimation[type][count[type]][1]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.productionmine2);
                break;
            case 135:
                BuildingAnimation[type][count[type]][2]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.productionmine3);
                break;
            case 136:
                BuildingAnimation[type][count[type]][3]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.productionmine4);
                break;
            case 137:
                BuildingAnimation[type][count[type]][4]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.productionmine5);
                break;
            case 138:
                BuildingAnimation[type][count[type]][5]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.productionmine6);
                break;
            case 139:
                for (int x = 0; x < BuildingAnimation[type][count[type]].length; x++)
                    BuildingAnimation[type][count[type]][x] = Bitmap.createScaledBitmap(BuildingAnimation[type][count[type]][x],
                            global.blackSide.UnitConversion(BuildingSize),
                            global.blackSide.UnitConversion(BuildingSize), true);
                break;
            case 140:
                type = Combine;
                count[type]++;
                BuildingImage[type][count[type]] = new Bitmap[2];
                break;
            case 141:
                BuildingImage[type][count[type]][0]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.combinecarpenterstop);
                break;
            case 142:
                BuildingImage[type][count[type]][1]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.combinecarpenter1level);
                break;
            case 143:
                for (int x = 0; x < BuildingImage[type][count[type]].length; x++)
                    BuildingImage[type][count[type]][x] = Bitmap.createScaledBitmap(BuildingImage[type][count[type]][x],
                            global.blackSide.UnitConversion(BuildingSize),
                            global.blackSide.UnitConversion(BuildingSize), true);
                BuildingAnimation[type][count[type]] = new Bitmap[6];
                break;
            case 144:
                BuildingAnimation[type][count[type]][0]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.combinecarpenter1);
                break;
            case 145:
                BuildingAnimation[type][count[type]][1]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.combinecarpenter2);
                break;
            case 146:
                BuildingAnimation[type][count[type]][2]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.combinecarpenter3);
                break;
            case 147:
                BuildingAnimation[type][count[type]][3]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.combinecarpenter4);
                break;
            case 148:
                BuildingAnimation[type][count[type]][4]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.combinecarpenter5);
                break;
            case 149:
                BuildingAnimation[type][count[type]][5]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.combinecarpenter6);
                break;
            case 150:
                for (int x = 0; x < BuildingAnimation[type][count[type]].length; x++)
                    BuildingAnimation[type][count[type]][x] = Bitmap.createScaledBitmap(BuildingAnimation[type][count[type]][x],
                            global.blackSide.UnitConversion(BuildingSize),
                            global.blackSide.UnitConversion(BuildingSize), true);
                break;
            case 151:
                type = Combine;
                count[type]++;
                BuildingImage[type][count[type]] = new Bitmap[2];
                break;
            case 152:
                BuildingImage[type][count[type]][0]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.combineworkshopstop);
                break;
            case 153:
                BuildingImage[type][count[type]][1]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.combineworkshop1level);
                break;
            case 154:
                for (int x = 0; x < BuildingImage[type][count[type]].length; x++)
                    BuildingImage[type][count[type]][x] = Bitmap.createScaledBitmap(BuildingImage[type][count[type]][x],
                            global.blackSide.UnitConversion(BuildingSize),
                            global.blackSide.UnitConversion(BuildingSize), true);
                BuildingAnimation[type][count[type]] = new Bitmap[6];
                break;
            case 155:
                BuildingAnimation[type][count[type]][0]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.combineworkshop1);
                break;
            case 156:
                BuildingAnimation[type][count[type]][1]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.combineworkshop2);
                break;
            case 157:
                BuildingAnimation[type][count[type]][2]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.combineworkshop3);
                break;
            case 158:
                BuildingAnimation[type][count[type]][3]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.combineworkshop4);
                break;
            case 159:
                BuildingAnimation[type][count[type]][4]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.combineworkshop5);
                break;
            case 160:
                BuildingAnimation[type][count[type]][5]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.combineworkshop6);
                break;
            case 161:
                for (int x = 0; x < BuildingAnimation[type][count[type]].length; x++)
                    BuildingAnimation[type][count[type]][x] = Bitmap.createScaledBitmap(BuildingAnimation[type][count[type]][x],
                            global.blackSide.UnitConversion(BuildingSize),
                            global.blackSide.UnitConversion(BuildingSize), true);
                break;
            case 162:
                type = Combine;
                count[type]++;
                BuildingImage[type][count[type]] = new Bitmap[2];
                break;
            case 163:
                BuildingImage[type][count[type]][0]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.combinesmithystop);
                break;
            case 164:
                BuildingImage[type][count[type]][1]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.combinesmithy1level);
                break;
            case 165:
                for (int x = 0; x < BuildingImage[type][count[type]].length; x++)
                    BuildingImage[type][count[type]][x] = Bitmap.createScaledBitmap(BuildingImage[type][count[type]][x],
                            global.blackSide.UnitConversion(BuildingSize),
                            global.blackSide.UnitConversion(BuildingSize), true);
                BuildingAnimation[type][count[type]] = new Bitmap[6];
                break;
            case 166:
                BuildingAnimation[type][count[type]][0]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.combinesmithy1);
                break;
            case 167:
                BuildingAnimation[type][count[type]][1]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.combinesmithy2);
                break;
            case 168:
                BuildingAnimation[type][count[type]][2]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.combinesmithy3);
                break;
            case 169:
                BuildingAnimation[type][count[type]][3]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.combinesmithy4);
                break;
            case 170:
                BuildingAnimation[type][count[type]][4]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.combinesmithy5);
                break;
            case 171:
                BuildingAnimation[type][count[type]][5]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.combinesmithy6);
                break;
            case 172:
                for (int x = 0; x < BuildingAnimation[type][count[type]].length; x++)
                    BuildingAnimation[type][count[type]][x] = Bitmap.createScaledBitmap(BuildingAnimation[type][count[type]][x],
                            global.blackSide.UnitConversion(BuildingSize),
                            global.blackSide.UnitConversion(BuildingSize), true);
                break;
            case 173:
                type = Distribution;
                count[type]++;
                BuildingImage[type][count[type]] = new Bitmap[2];
                break;
            case 174:
                BuildingImage[type][count[type]][0]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.distributiondistributioncenterstop);
                break;
            case 175:
                BuildingImage[type][count[type]][1]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.distributiondistributioncenter1level);
                break;
            case 176:
                for (int x = 0; x < BuildingImage[type][count[type]].length; x++)
                    BuildingImage[type][count[type]][x] = Bitmap.createScaledBitmap(BuildingImage[type][count[type]][x],
                            global.blackSide.UnitConversion(BuildingSize),
                            global.blackSide.UnitConversion(BuildingSize), true);
                BuildingAnimation[type][count[type]] = new Bitmap[6];
                break;
            case 177:
                BuildingAnimation[type][count[type]][0]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.distributiondistributioncenter1);
                break;
            case 178:
                BuildingAnimation[type][count[type]][1]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.distributiondistributioncenter2);
                break;
            case 179:
                BuildingAnimation[type][count[type]][2]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.distributiondistributioncenter3);
                break;
            case 180:
                BuildingAnimation[type][count[type]][3]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.distributiondistributioncenter4);
                break;
            case 181:
                BuildingAnimation[type][count[type]][4]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.distributiondistributioncenter5);
                break;
            case 182:
                BuildingAnimation[type][count[type]][5]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.distributiondistributioncenter6);
                for (int x = 0; x < BuildingAnimation[type][count[type]].length; x++)
                    BuildingAnimation[type][count[type]][x] = Bitmap.createScaledBitmap(BuildingAnimation[type][count[type]][x],
                            global.blackSide.UnitConversion(BuildingSize),
                            global.blackSide.UnitConversion(BuildingSize), true);
                break;
            case 183:
                type = Consumption;
                count[type]++;
                BuildingImage[type][count[type]] = new Bitmap[2];
                break;
            case 184:
                BuildingImage[type][count[type]][0]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.consumptionfoodwarehousestop);
                break;
            case 185:
                BuildingImage[type][count[type]][1]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.consumptionfoodwarehouse1level);
                break;
            case 186:
                for (int x = 0; x < BuildingImage[type][count[type]].length; x++)
                    BuildingImage[type][count[type]][x] = Bitmap.createScaledBitmap(BuildingImage[type][count[type]][x],
                            global.blackSide.UnitConversion(BuildingSize),
                            global.blackSide.UnitConversion(BuildingSize), true);
                BuildingAnimation[type][count[type]] = new Bitmap[6];
                break;
            case 187:
                BuildingAnimation[type][count[type]][0]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.consumptionfoodwarehouse1);
                break;
            case 188:
                BuildingAnimation[type][count[type]][1]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.consumptionfoodwarehouse2);
                break;
            case 189:
                BuildingAnimation[type][count[type]][2]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.consumptionfoodwarehouse3);
                break;
            case 190:
                BuildingAnimation[type][count[type]][3]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.consumptionfoodwarehouse4);
                break;
            case 191:
                BuildingAnimation[type][count[type]][4]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.consumptionfoodwarehouse5);
                break;
            case 192:
                BuildingAnimation[type][count[type]][5]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.consumptionfoodwarehouse6);
                break;
            case 193:
                for (int x = 0; x < BuildingAnimation[type][count[type]].length; x++)
                    BuildingAnimation[type][count[type]][x] = Bitmap.createScaledBitmap(BuildingAnimation[type][count[type]][x],
                            global.blackSide.UnitConversion(BuildingSize),
                            global.blackSide.UnitConversion(BuildingSize), true);
                break;
            case 194:
                type = Special;
                count[type]++;
                BuildingImage[type][count[type]] = new Bitmap[2];
                break;
            case 195:
                BuildingImage[type][count[type]][0]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.aboriginalplastercaststop);
                break;
            case 196:
                BuildingImage[type][count[type]][1]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.aboriginalplastercast1level);
                break;
            case 197:
                for (int x = 0; x < BuildingImage[type][count[type]].length; x++)
                    BuildingImage[type][count[type]][x] = Bitmap.createScaledBitmap(BuildingImage[type][count[type]][x],
                            global.blackSide.UnitConversion(BuildingSize),
                            global.blackSide.UnitConversion(BuildingSize), true);
                BuildingAnimation[type][count[type]] = new Bitmap[6];
                break;
            case 198:
                BuildingAnimation[type][count[type]][0]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.aboriginalplastercast1);
                break;
            case 199:
                BuildingAnimation[type][count[type]][1]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.aboriginalplastercast2);
                break;
            case 200:
                BuildingAnimation[type][count[type]][2]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.aboriginalplastercast3);
                break;
            case 201:
                BuildingAnimation[type][count[type]][3]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.aboriginalplastercast4);
                break;
            case 202:
                BuildingAnimation[type][count[type]][4]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.aboriginalplastercast5);
                break;
            case 203:
                BuildingAnimation[type][count[type]][5]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.aboriginalplastercast6);
                break;
            case 204:
                for (int x = 0; x < BuildingAnimation[type][count[type]].length; x++)
                    BuildingAnimation[type][count[type]][x] = Bitmap.createScaledBitmap(BuildingAnimation[type][count[type]][x],
                            global.blackSide.UnitConversion(BuildingSize),
                            global.blackSide.UnitConversion(BuildingSize), true);
                break;
            case 205:
                type = Consumption;
                count[type]++;
                BuildingImage[type][count[type]] = new Bitmap[2];
                break;
            case 206:
                BuildingImage[type][count[type]][0]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.consumptiontownstop);
                break;
            case 207:
                BuildingImage[type][count[type]][1]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.consumptiontown1level);
                break;
            case 208:
                for (int x = 0; x < BuildingImage[type][count[type]].length; x++)
                    BuildingImage[type][count[type]][x] = Bitmap.createScaledBitmap(BuildingImage[type][count[type]][x],
                            global.blackSide.UnitConversion(BuildingSize),
                            global.blackSide.UnitConversion(BuildingSize), true);
                BuildingAnimation[type][count[type]] = new Bitmap[6];
                break;
            case 209:
                BuildingAnimation[type][count[type]][0]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.consumptiontown1);
                break;
            case 210:
                BuildingAnimation[type][count[type]][1]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.consumptiontown2);
                break;
            case 211:
                BuildingAnimation[type][count[type]][2]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.consumptiontown3);
                break;
            case 212:
                BuildingAnimation[type][count[type]][3]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.consumptiontown4);
                break;
            case 213:
                BuildingAnimation[type][count[type]][4]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.consumptiontown5);
                break;
            case 214:
                BuildingAnimation[type][count[type]][5]
                        = BitmapFactory.decodeResource(global.context.getResources(), R.drawable.consumptiontown6);
                break;
            case 215:
                for (int x = 0; x < BuildingAnimation[type][count[type]].length; x++)
                    BuildingAnimation[type][count[type]][x] = Bitmap.createScaledBitmap(BuildingAnimation[type][count[type]][x],
                            global.blackSide.UnitConversion(BuildingSize),
                            global.blackSide.UnitConversion(BuildingSize), true);
                break;
            default:
                for (int i = 0; i < 6; i++)
                    for (int j = 0; j < ActionBookBuildingStamp[i].length; j++)
                        ActionBookBuildingStamp[i][j] = Bitmap.createScaledBitmap(BuildingImage[i][j][0],
                                global.blackSide.UnitConversion(186),
                                global.blackSide.UnitConversion(186), true);
                success = true;
                progress = 4000;
                return progress;
        }

        progress++;

        return progress;
    }
}
