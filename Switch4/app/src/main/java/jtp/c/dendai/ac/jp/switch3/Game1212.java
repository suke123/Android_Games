package jtp.c.dendai.ac.jp.switch3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

/**
 * Created by DE on 2017/10/24.
 */

public class Game1212  extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);


        Button clearButton = (Button)findViewById(R.id.clearButton);
        clearButton.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                Intent intent = new Intent(getApplication(), ClearActivity.class);
                startActivity(intent);
            }
        });
    }

}
