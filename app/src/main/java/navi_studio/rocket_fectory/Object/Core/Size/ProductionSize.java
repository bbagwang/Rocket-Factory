package navi_studio.rocket_fectory.Object.Core.Size;

import navi_studio.rocket_fectory.Object.Core.Code.ProductionCode;

public class ProductionSize {
    private final static int log = 300;
    private final static int Stone = 250;
    private final static int CornField = 500;
    private final static int Well = 150;
    private final static int Mine = 350;

    public int getSize(int number){
        switch (number){
            case 0:
                return log;
            case 1:
                return Stone;
            case 2:
                return CornField;
            case 3:
                return Well;
            case 4:
                return Mine;
            default:
                return 0;
        }
    }

}
