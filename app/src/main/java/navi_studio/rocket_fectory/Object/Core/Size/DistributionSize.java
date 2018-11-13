package navi_studio.rocket_fectory.Object.Core.Size;

public class DistributionSize {

    public final static int DistributionCenter = 150;


    public int getSize(int number){
        switch (number){
            case 0:
                return DistributionCenter;
            default:
                return 0;
        }
    }
}
