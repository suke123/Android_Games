package jtp.c.dendai.ac.jp.myapplication1;

import android.app.Activity;
import android.os.Bundle;
import android.view.*;
import android.view.View;
import android.widget.Button;

/**
 * Created by taka on 2017/06/16.
 */

public class TitleActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);

        Button returnButton = (Button) findViewById(R.id.return_button);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
