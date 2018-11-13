package navi_studio.rocket_fectory.Object.Core.Size;

public class ConsumptionSize {

    public final static int Town = 350;
    public final static int FoodDepots = 150;

    public int getSize(int number){
        switch (number){
            case 0:
                return Town;
            case 1:
                return FoodDepots;
            default:
                return 0;
        }
    }
}
