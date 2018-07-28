package jtp.c.dendai.ac.jp.switch_test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout.LayoutParams;


public class GameActivity extends AppCompatActivity {

    private GameView gameView;
    private String stageName;
    private Game game;

    private int zahyouIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        stageName = intent.getStringExtra("Stagename");
        zahyouIndex = intent.getIntExtra("zahyou", 0);
        gameView = new GameView(this, this, stageName, zahyouIndex);

        setContentView(gameView);


        View view = getLayoutInflater().inflate(R.layout.activity_game, null);
        addContentView(view, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));

        game = new Game(this, this, stageName, gameView);
    }
}
