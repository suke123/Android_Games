package jtp.c.dendai.ac.jp.shootinggame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by taka on 2017/06/16.
 */

public class ResultActivity extends Activity {

    //private Score score = new Score();
    //int resultScore = 0;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        //resultScore = score.getScore();
        Intent intent = getIntent();
        //int resultScore = intent.getIntExtra("SCORE", 0);

        Button toTitle_btn = (Button) findViewById(R.id.toTitle_button);
        TextView scoreText = (TextView) findViewById(R.id.score_text);
        //TextView topScoreText = (TextView) findViewById(R.id.topscore_text);

        //topScoreText.setText("TOP：" + intent.getIntExtra("SCORE", 0));
        scoreText.setText("SCORE：" + intent.getIntExtra("SCORE", 0));

        toTitle_btn.setOnClickListener(new android.view.View.OnClickListener() {
            public void onClick(android.view.View v) {
                // Sub 画面を起動
                Intent intent = new Intent();
                intent.setClassName("jtp.c.dendai.ac.jp.shootinggame", "jtp.c.dendai.ac.jp.shootinggame.MainActivity");
                startActivity(intent);
            }
        });
    }
}
