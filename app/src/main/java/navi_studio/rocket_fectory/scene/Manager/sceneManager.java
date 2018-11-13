package navi_studio.rocket_fectory.scene.Manager;

import android.graphics.Canvas;

import navi_studio.rocket_fectory.Interface.sceneInterface;
import navi_studio.rocket_fectory.scene.Demo.sceneInGame;
import navi_studio.rocket_fectory.scene.Demo.sceneLoading;
import navi_studio.rocket_fectory.scene.Demo.sceneLobby;
import navi_studio.rocket_fectory.scene.Demo.sceneSplash;
import navi_studio.rocket_fectory.scene.Demo.sceneStart;
import navi_studio.rocket_fectory.scene.Test.sceneTest;
import navi_studio.rocket_fectory.scene.Test.sceneloadTest;

/**
 * Created by beanb on 2018-03-11.
 */

public class sceneManager extends sceneInterface {

    private sceneInterface scenethis;
    private sceneInterface sceneNext;

    private String buff;

    private boolean Change;

    private final boolean On = true;
    private final boolean Off = false;

    // 존재하는 모든 씬을 이곳에서 준비해 둡니다.
    public boolean loadNextScene(String sceneName) {
        switch (sceneName) {
            case "Start":
                sceneNext = new sceneStart();
                sceneNext.Initialize(global);
                return true;
            case "Lobby":
                sceneNext = new sceneLobby();
                sceneNext.Initialize(global);
                return true;
            case "InGame":
                sceneNext = new sceneInGame();
                sceneNext.Initialize(global);
                return true;
            case "Loading":
                sceneNext = new sceneLoading();
                sceneNext.Initialize(global);
                return true;
            case "TestLoading":
                sceneNext = new sceneloadTest();
                sceneNext.Initialize(global);
                return true;
            case "Test":
                sceneNext = new sceneTest();
                sceneNext.Initialize(global);
                return true;
            default:
                return false;
        }
    }

    // Awake 에서 불러옵니다.
    @Override
    public void Start() {
        Change = Off;
        scenethis = new sceneSplash();

        scenethis.Initialize(global);

        scenethis.Awake(loading);
        scenethis.Start();
    }

    @Override
    public void Exception() {
        scenethis.Exception();
    }

    // Update 에서 end 가 나올경우 불러옵니다.
    public String nextScnen() {

        if (buff == "End") {
            scenethis.Disable();
            scenethis.Destroy();
            return "End";
        }

        if (loadNextScene(buff) == false) {    // 다음 씬을 준비시킵니다.
            scenethis.Disable();
            scenethis.Destroy();
            return "End";
        }
        Next = scenethis.Next;
        sceneNext.Next = Next;
        return Running;
    }

    @Override
    public void FixedUpdate() {
        if (Change == On)
            return;
        scenethis.FixedUpdate();
    }

    @Override
    public void InputEvent() {
        if (Change == On)
            return;
        scenethis.InputEvent();
    }

    @Override
    public void UIUpdate() {
        if (Change == On)
            return;
        scenethis.UIUpdate();
    }

    @Override
    public String Update() {

        // Update 에서 end 를 반환하고 다음 씬이 존재할경우 싱행됩니다.
        // 다음 씬을 조금 로딩하고 다음 씬이 존재할경우 현재 씬의 로드 업데이트 를 실행합니다.
        if (Change == On) {
            loading = scenethis.LoadScene(sceneNext);
            scenethis.LoadUpdate();
        }

        // 로드 업데이트 중에는 업데이트를 실행하지 않습니다.
        if (Change == Off) {
            buff = scenethis.Update();
            if (buff != Running) {
                if (nextScnen() == End) {
                    return End;
                }
                Change = On;
            }
        }
        return Running;
    }

    @Override
    public void LateUpdate() {
        if (Change == On)
            return;
        scenethis.LateUpdate();
    }

    @Override
    public void Render(Canvas canvas) {

        scenethis.Render(canvas);

        if (Change == On)
            scenethis.LoadRender(canvas);

        if (loading >= success) {
            scenethis.Disable();
            scenethis.Destroy();

            scenethis = null;

            scenethis = sceneNext;
            scenethis.Start();

            Change = Off;
            loading = 0;
        }
    }

    @Override
    public void LastRender(Canvas canvas) {
        if (Change == On)
            return;
        scenethis.LastRender(canvas);
    }

    @Override
    public void UIRender(Canvas canvas) {
        if (Change == On)
            return;
        scenethis.UIRender(canvas);
    }

    @Override
    public void Disable() {

    }

    @Override
    public void Destroy() {

    }
}
