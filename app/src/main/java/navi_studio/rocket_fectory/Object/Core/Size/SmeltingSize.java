package navi_studio.rocket_fectory.Object.Core.Size;

public class SmeltingSize {

    private final static int BrazierLow = 150;
    private final static int BrazierHigh = 150;
    private final static int WindMill = 150;

    public int getSize(int number){
        switch (number){
            case 0:
                return BrazierLow;
            case 1:
                return BrazierHigh;
            case 2:
                return WindMill;
            default:
                return 0;
        }
    }
}
