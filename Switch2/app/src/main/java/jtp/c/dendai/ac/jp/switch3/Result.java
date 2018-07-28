package jtp.c.dendai.ac.jp.switch3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Result extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clear);


        Intent intent = getIntent();

        TextView stageName = (TextView)findViewById(R.id.stagename);
        stageName.setText(intent.getStringExtra("StageName"));
        //setButtonId();

    }
}
