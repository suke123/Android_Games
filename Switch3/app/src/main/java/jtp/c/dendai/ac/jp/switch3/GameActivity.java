package jtp.c.dendai.ac.jp.switch3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import jtp.c.dendai.ac.jp.switch3.stage.Stage1;

public class GameActivity extends Activity {

    private Stage1 stage1;
    //MainPanel mainPanel = new MainPanel(this,this);
    private Game game;
    private String stageName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        //Stage1 stage1 = new Stage1("map01.dat",this,this);

        //setContentView(R.layout.activity_game);
        //setContentView(stage1);

        Intent intent = getIntent();
        stageName = intent.getStringExtra("StageName");
        //if(MyApplication.getInstance() == null){
        //   Log.d("Message", "MyApplication.getInstance() == null");
        //}
        /*
        if(getApplicationContext() != null){
            game = new Game(this, this.getApplicationContext(), stageName);
        }
        else{
            Log.d("Message", "Gameに渡すthis.getApplicationContext()はnullだよ！！");
        }
        */

    }

    @Override
    public void onStart(){
        super.onStart();
        game = new Game(stageName, this);
        stage1 = new Stage1("map01.dat", this, this);
        //this.setContentView(stage1);
    }

}
