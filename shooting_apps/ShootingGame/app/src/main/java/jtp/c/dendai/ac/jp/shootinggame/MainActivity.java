package jtp.c.dendai.ac.jp.shootinggame;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.*;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView highscoreText = (TextView)findViewById(R.id.score_text);

        highscoreText.setText("TOP：");

        Button start_btn = (Button) findViewById(R.id.start_button);

        start_btn.setOnClickListener(new android.view.View.OnClickListener() {
            public void onClick(android.view.View v) {
                // Sub 画面を起動
                Intent intent = new Intent();
                intent.setClassName("jtp.c.dendai.ac.jp.shootinggame", "jtp.c.dendai.ac.jp.shootinggame.GameActivity");
                startActivity(intent);
            }
        });
    }
}