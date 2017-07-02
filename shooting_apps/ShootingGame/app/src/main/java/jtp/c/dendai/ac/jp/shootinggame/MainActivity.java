package jtp.c.dendai.ac.jp.shootinggame;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.*;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    SharedPreferences highscoreData;

    int highscore;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();

        if (highscore == 0) {
            //highscoreを保存する
            highscoreData = getSharedPreferences("highscoreData", MODE_PRIVATE);
            SharedPreferences.Editor editor = highscoreData.edit();
            editor.putInt("highscore", highscore);
            editor.commit();
        }

        this.highscore = highscoreData.getInt("highscore",0);

        TextView highscoreText = (TextView) findViewById(R.id.score_text);

        highscoreText.setText("TOP：" + intent.getIntExtra("HIGHSCORE",0));

        Button start_btn = (Button) findViewById(R.id.start_button);

        start_btn.setOnClickListener(new android.view.View.OnClickListener() {
            public void onClick(android.view.View v) {
                // ゲーム画面に遷移
                Intent intent = new Intent();
                intent.setClassName("jtp.c.dendai.ac.jp.shootinggame", "jtp.c.dendai.ac.jp.shootinggame.GameActivity");
                startActivity(intent);
            }
        });
    }
}