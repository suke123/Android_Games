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

        Intent intent = getIntent();

        Button toTitle_btn = (Button) findViewById(R.id.toTitle_button);
        TextView scoreText = (TextView) findViewById(R.id.score_text);

        scoreText.setText("SCORE：" + intent.getIntExtra("SCORE", 0));

        toTitle_btn.setOnClickListener(new android.view.View.OnClickListener() {
            public void onClick(android.view.View v) {
                Intent intent = new Intent();
                intent.setClassName("jtp.c.dendai.ac.jp.shootinggame", "jtp.c.dendai.ac.jp.shootinggame.MainActivity");
                startActivity(intent);
            }
        });
    }
}
