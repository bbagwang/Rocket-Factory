package navi_studio.rocket_fectory.Object.Low;

import android.graphics.Bitmap;

import navi_studio.rocket_fectory.Object.Core.Ephemera;

public class TouchDestroy extends Ephemera {
    
    public void Create(int _PosMode, int _PosX, int _PosY, int _SizeX, int _SizeY, Bitmap _image[], int _FrameSpeed){
        Create(_PosMode,_PosX,_PosY,_SizeX,_SizeY,_image,_FrameSpeed,CHECK);
    }
}
