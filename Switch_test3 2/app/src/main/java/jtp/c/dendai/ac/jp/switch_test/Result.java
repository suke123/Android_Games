package jtp.c.dendai.ac.jp.switch_test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Result extends Activity {

    private Button toSelect;
    private String stagename;
    private boolean isGoal;
    private TextView stageNameText, resultText;
    private ImageView bg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent getIntent = getIntent();
        isGoal = getIntent.getBooleanExtra("IsGoal", true);
        stagename = getIntent.getStringExtra("Stagename");

        setId();
        setText();
        setOnClickListener();

        if(isGoal){
            //ImageView bg = (ImageView)findViewById(R.drawable.fireworks);
            bg.setImageResource(R.drawable.fireworks);
            setResultText("STAGE CLEAR");
        }
        else {
            bg.setImageResource(R.drawable.over);
            setResultText("GAME OVER");
        }
    }

    private void setText() {
        stageNameText.setText(stagename);
    }

    private void setOnClickListener() {
        toSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), StageSelect.class);
                startActivity(intent);
            }
        });
    }

    private void setId() {
        toSelect = (Button)findViewById(R.id.toSelect);
        stageNameText = (TextView)findViewById(R.id.stagenameText);
        resultText = (TextView)findViewById(R.id.resultText);
        bg = (ImageView)findViewById(R.id.imageView);
    }

    public void setResultText(String result) {
        resultText.setText(result);
    }
}
