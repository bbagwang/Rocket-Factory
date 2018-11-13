package navi_studio.rocket_fectory.Object.Core.Size;

public class CombineSize {

    public final static int Carpenter = 200;
    public final static int WorkShop = 210;
    public final static int Smithy = 220;

    public int getSize(int number){
        switch (number){
            case 0:
                return Carpenter;
            case 1:
                return WorkShop;
            case 2:
                return Smithy;
            default:
                return 0;
        }
    }
}
