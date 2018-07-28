package jtp.c.dendai.ac.jp.switch_test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class StageSelect extends AppCompatActivity {

    private Button stage1,stage2,stage3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stage_select);

        setButtonId();
        setOnClickListener();
    }

    private void setOnClickListener() {
        stage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), GameActivity.class);
                intent.putExtra("Stagename", "STAGE1");
                intent.putExtra("zahyou", 0);
                startActivity(intent);
            }
        });

        stage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), GameActivity.class);
                intent.putExtra("Stagename", "STAGE2");
                intent.putExtra("zahyou", 1);
                startActivity(intent);
            }
        });

        stage3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), GameActivity.class);
                intent.putExtra("Stagename", "STAGE3");
                intent.putExtra("zahyou", 2);
                startActivity(intent);
            }
        });
    }

    private void setButtonId() {
        stage1 = (Button)findViewById(R.id.button1);
        stage2 = (Button)findViewById(R.id.button2);
        stage3 = (Button)findViewById(R.id.button3);
    }
}
