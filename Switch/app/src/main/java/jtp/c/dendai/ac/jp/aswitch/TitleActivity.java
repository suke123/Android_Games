package jtp.c.dendai.ac.jp.aswitch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by taka on 2017/10/03.
 */

public class TitleActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.title_activity);

        ImageView titleView = (ImageView) findViewById(R.id.titleImage);
        titleView.setImageResource(R.drawable.title_background);

        titleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), SelectActivity.class);
                startActivity(intent);
            }
        });
    }
}

