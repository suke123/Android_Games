package jtp.c.dendai.ac.jp.aswitch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

/**
 * Created by taka on 2017/10/09.
 */

public class SelectActivity extends Activity implements View.OnClickListener {

    private Button stage1,stage2,stage3;
    private ImageButton btsButton;
    private Intent stageIntent, titleIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        setStageselect();

        setID();
        setonClicklistener();
        setIntentId();

    }

    private void setStageselect() {

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.STAGE1:
                stageIntent.putExtra("StageName",getString(R.string.stagename1));
                startActivity(stageIntent);
                break;

            case R.id.STAGE2:
                stageIntent.putExtra("StageName",getString(R.string.stagename2));
                startActivity(stageIntent);
                break;

            case R.id.STAGE3:
                stageIntent.putExtra("StageName",getString(R.string.stagename3));
                startActivity(stageIntent);
                break;

            case R.id.back_to_title:
                startActivity(titleIntent);
                break;
        }
    }

    private void setID() {
        stage1 = (Button) findViewById(R.id.STAGE1);
        stage2 = (Button) findViewById(R.id.STAGE2);
        stage3 = (Button) findViewById(R.id.STAGE3);
        btsButton = (ImageButton)findViewById(R.id.back_to_title);
    }

    private void setonClicklistener() {
        stage1.setOnClickListener(this);
        stage2.setOnClickListener(this);
        stage3.setOnClickListener(this);
        btsButton.setOnClickListener(this);
    }

    private void setIntentId() {
        stageIntent = new Intent(getApplication(), GameActivity.class);
        titleIntent = new Intent(getApplication(), TitleActivity.class);
    }

}
