package jtp.c.dendai.ac.jp.adventure;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import jtp.c.dendai.ac.jp.adventure.scene.AbstractScene;

public class MainActivity extends AppCompatActivity {

    private Game game;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  setTheme(R.style.MyTheme);
        AbstractScene.setActivity(this);
        AbstractScene.setHandler(new Handler());
        setContentView(R.layout.title);
        game = new Game(this);
        //game.start(0, 0, 0);
        game.start(0);
    }
}
