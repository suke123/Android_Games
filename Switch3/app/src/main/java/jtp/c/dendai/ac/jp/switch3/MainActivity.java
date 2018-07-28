package jtp.c.dendai.ac.jp.switch3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView titleView = (ImageView)findViewById(R.id.titleImage);
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
