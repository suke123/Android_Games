package jtp.c.dendai.ac.jp.switch_test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.boy1).startAnimation(AnimationUtils.loadAnimation(this,R.anim.a1));
        findViewById(R.id.girl1).startAnimation(AnimationUtils.loadAnimation(this,R.anim.a1));

        Button button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), StageSelect.class);
                startActivity(intent);
            }
        });
    }
}
