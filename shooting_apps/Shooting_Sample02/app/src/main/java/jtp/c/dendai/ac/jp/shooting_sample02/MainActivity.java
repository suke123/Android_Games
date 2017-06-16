package jtp.c.dendai.ac.jp.shooting_sample02;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button start_btn = (Button) findViewById(R.id.start_button);

        start_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Sub 画面を起動
                Intent intent = new Intent();
                intent.setClassName("jtp.c.dendai.ac.jp.shooting_sample02", "jtp.c.dendai.ac.jp.shooting_sample02.GameActivity");
                startActivity(intent);
            }
        });
    }
}
