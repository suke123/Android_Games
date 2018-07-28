package jtp.c.dendai.ac.jp.switch3;

import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import jtp.c.dendai.ac.jp.switch3.stage.Stage1;

public class GameActivity extends AppCompatActivity implements MainPanel.Callback{

    Stage1 stage1;// = new Stage1("map01.dat",this,this);
    MainPanel mainPanel;// = new MainPanel(this,this);
    private Map2 map2;
    Canvas canvas;
    String fileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Intent intent = getIntent();
        fileName = intent.getStringExtra("StageName");

        map2 = new Map2(fileName);

        map2.draw(canvas, 0, 0);

        if(mainPanel == null){
            mainPanel = new MainPanel(this,this);
        }
        //mainPanel = new MainPanel(this,this);
        //stage1 = new Stage1("map01.dat",this,this);

        mainPanel.setCallback(this);
        setContentView(mainPanel);
/*
        Button clearButton = (Button)findViewById(R.id.clearButton);
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), ClearActivity.class);
                startActivity(intent);
            }
        });
*/
    }

    @Override
    public void onGameOver2() {

        Toast.makeText(this, "ケチ成柔多", Toast.LENGTH_LONG).show();
    }


}
