package navi_studio.rocket_fectory.basic;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import navi_studio.rocket_fectory.Progress.MainProgress;
import navi_studio.rocket_fectory.Global.Pause;

public class MainActivity extends AppCompatActivity {

    Pause pause = new Pause();

    private IntentFilter Filter;
    private BroadcastReceiver Receiver;

    @Override
    protected void onStop() {
        super.onStop();
        pause.set(true);
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        pause.set(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if(intent.getAction().equals("android.intent.action.SCREEN_OFF")) {
                    pause.set(true);
                }
            }
        };

        Filter = new IntentFilter();
        Filter.addAction(Intent.ACTION_SCREEN_OFF);
        registerReceiver(Receiver, Filter);

        // 현재는 가로 고정입니다.
        // 어플리케이션을 세로 고정으로 제작하실경우
        // 1. BlackSide 의 가로, 세로를 수정하여 주세요.
        // 2. AndroidManifest.xml 의 "landscape" 를 "portrait" 로 수정하여 주세요.
        // this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(new MainProgress(this, pause));
    }
}
