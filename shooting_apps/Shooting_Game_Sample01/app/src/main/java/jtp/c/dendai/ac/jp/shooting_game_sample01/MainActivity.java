package jtp.c.dendai.ac.jp.shooting_game_sample01;
/*
 * プロジェクトを作成するとまずこれが作成されますねのクラス
 * ”元から作成されてるやつ”と書いてあるやつ以外を書くと
 * フルスクリーン仕様になり、画面の大きさを変数に収めています
 */

import android.app.Activity;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {
    public float disp_w,disp_h;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);//元から作成されてるやつ
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        Window window = getWindow();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        WindowManager manager = window.getWindowManager();
        Display disp = manager.getDefaultDisplay();
        disp_w = disp.getWidth();
        disp_h = disp.getHeight();

        setContentView(new MainLoop(this));
        //setContentView(R.layout.main);//元から作成されてるやつ

    }
}

