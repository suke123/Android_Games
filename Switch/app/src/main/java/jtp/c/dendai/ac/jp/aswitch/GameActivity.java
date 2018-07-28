package jtp.c.dendai.ac.jp.aswitch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class GameActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Intent intent = getIntent();
        String stageName = intent.getStringExtra("StageName");

        Game game = new Game(this, stageName);


        /*Button clearButton = (Button)findViewById(clearButton);
        final Chronometer chronometer = (Chronometer) findViewById(R.id.chronometer);

        Intent intent = getIntent();
        final String stageName = intent.getStringExtra("StageName");

        stagenameText.setText(stageName);

        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.start();

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chronometer.stop();
                Intent intent = new Intent(getApplication(), ClearActivity.class);
                intent.putExtra("StageName", stageName);
                intent.putExtra("GameTime", chronometer.getText());
                startActivity(intent);
            }
        });*/
    }
}
