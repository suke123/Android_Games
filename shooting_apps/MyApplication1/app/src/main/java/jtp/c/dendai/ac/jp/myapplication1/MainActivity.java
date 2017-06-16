package jtp.c.dendai.ac.jp.myapplication1;

import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;

public class MainActivity extends AppCompatActivity {
    private View view;
    private Thread mainThread;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        Display display = getWindowManager().getDefaultDisplay();
        Point p = new Point();
        display.getSize(p);
        view = new View(this, p);
        view.init();
        setContentView(view);
    }

    @Override
    public void onResume() {
        super.onResume();
        view.start();
    }

    @Override
    public void onStop() {
        super.onStop();
        while (mainThread != null && mainThread.isAlive()) {
            try {
                view.shutdown = true;
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        }
    }
}