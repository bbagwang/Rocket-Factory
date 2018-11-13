package navi_studio.rocket_fectory.Function;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;

/**
 * Created by beanb on 2018-03-26.
 */

public class SoundUnit {

    public final static int effect = 100;   // 효과음
    public final static int music = 101;   // 음악

    protected SoundPool Effect;                // 효과음을 저장합니다.
    protected MediaPlayer Music;            // 음악을 저장합니다.
    protected int srcID;                   // 아이디를 저장합니다.
    protected int priority;                 // 우선순위를 정합니다.
    protected int type;                     // 사운드 종류를 지정합니다.

    // 설정
    public void Create(int _type, int soundID, int _priority, Context _context) {
        switch (_type) {
            case effect:
                Effect = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
                srcID = Effect.load(_context, soundID, 1);
                break;
            case music:
                Music = new MediaPlayer();
                Music = MediaPlayer.create(_context, soundID);
                Music.setLooping(false);
                break;
        }
        priority = _priority;
        type = _type;
    }

    // 양쪽 사운드가 같은 크기로 출력됩니다.
    public void play(float size) {
        switch (type) {
            case effect:
                Effect.play(srcID, size, size, priority, 0, 1f);
                break;
            case music:
                Music.setVolume(size,size);
                Music.start();
                break;
        }
    }

    // 양쪽 사운드가 각각 입력값으로 출력됩니다.
    public void play(float sizeL, float sizeR) {
        switch (type) {
            case effect:
                Effect.play(srcID, sizeL, sizeR, priority, 0, 1f);
                break;
            case music:
                Music.setVolume(sizeL, sizeR);
                Music.start();
                break;
        }
    }

    // 음악재생을 일시 중단합니다.
    public void pause() {
        Music.pause();
    }

    // 음악재생을 중단합니다.
    public void stop() {
        Music.stop();
    }
}