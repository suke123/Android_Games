package jtp.c.dendai.ac.jp.a2d_action_game;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton startButton = (ImageButton) findViewById(R.id.start_btn);
        startButton.setOnClickListener(new android.view.View.OnClickListener() {
            public void onClick(android.view.View v) {
                // ゲーム画面を起動
                Intent intent = new Intent();
                intent.setClassName("jtp.c.dendai.ac.jp.a2d_action_game", "jtp.c.dendai.ac.jp.a2d_action_game.GameActivity");
                startActivity(intent);
            }
        });
    }

}
