package jtp.c.dendai.ac.jp.shootinggame;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;

public class GameActivity extends Activity {
    private View view;
    private Thread mainThread;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
    }
    @Override
    public void onStart(){
        super.onStart();
        Display display = getWindowManager().getDefaultDisplay();
        Point p = new Point();
        display.getSize(p);
        view = new View(this, p, this);
        view.init();
        setContentView(view);
    }
    @Override
    public void onResume(){
        super.onResume();
        view.start();
    }
    @Override
    public void onStop(){
        super.onStop();
        while (mainThread != null && mainThread.isAlive()) {
            try {
                view.shutdown = true;
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        }
    }

    public void toResult() {
        Intent intent = new Intent();
        intent.setClassName("jtp.c.dendai.ac.jp.shootinggame", "jtp.c.dendai.ac.jp.shootinggame.ResultActivity");
        intent.putExtra("SCORE", view.getScore());
        startActivity(intent);
    }
}