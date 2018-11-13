package navi_studio.rocket_fectory.Object.Core.Size;

public class SpecialSize {

    public final static int Statue = 100;

    public int getSize(int number){
        switch (number){
            case 0:
                return Statue;
            default:
                return 0;
        }
    }
}
