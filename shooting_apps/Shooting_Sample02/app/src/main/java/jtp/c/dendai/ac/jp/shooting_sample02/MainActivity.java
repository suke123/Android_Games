package jtp.c.dendai.ac.jp.shooting_sample02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements GameView.EventCallback {
    private GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gameView = new GameView(this);

        //GameViewのインスタンス生成後、MainActivity自身をEventCallbackに設定
        gameView.setEventCallback(this);

        setContentView(gameView);
    }

    @Override
    public void onGameOver(long score) {
        gameView.stopDrawThread();

        Toast.makeText(this, "GAME OVER SCORE：" + score, Toast.LENGTH_LONG).show();
    }

}
