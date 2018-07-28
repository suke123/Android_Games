package jtp.c.dendai.ac.jp.switch_test;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by taka on 2017/10/22.
 */

public class Game implements View.OnClickListener{

    private Activity activity;
    private ImageButton left,right,change,jump;
    private TextView stagenameText, changeNumText;
    private String stagename;

    private Button clear;

    private Context context;

    private GameView gameView;

    Game(Activity a, Context context, String stagename, GameView gameView){
        this.activity = a;
        this.stagename = stagename;

        this.context = context;

        this.gameView = gameView;

        setID();
        setButtonOnClickListener();
        setStagename();
        setChangeNum();
    }

    private void setChangeNum() {
        changeNumText.setTextColor(Color.WHITE);
        changeNumText.setText("CHANGE: " + String.valueOf(gameView.getScore()));
    }


    private void setStagename() {
        stagenameText.setTextColor(Color.WHITE);
        stagenameText.setText(stagename);
    }

    public void setID() {
        left = (ImageButton)activity.findViewById(R.id.array_left_Button);
        right = (ImageButton)activity.findViewById(R.id.array_right_Button);
        change = (ImageButton)activity.findViewById(R.id.change_Button);
        jump = (ImageButton)activity.findViewById(R.id.array_up_Button);

        stagenameText = (TextView)activity.findViewById(R.id.stagename_Text);
        changeNumText = (TextView)activity.findViewById(R.id.change_num);

        clear = (Button)activity.findViewById(R.id.clearButton);
    }

    private void setButtonOnClickListener() {
        left.setOnClickListener(this);
        right.setOnClickListener(this);
        change.setOnClickListener(this);
        jump.setOnClickListener(this);

        clear.setOnClickListener(this);

        LongClickRepeatAdapter.bless(left);
        LongClickRepeatAdapter.bless(right);
    }

    @Override
    public void onClick(View v) {
        if (v != null) {
            switch (v.getId()) {
                case R.id.array_right_Button:
                    if(gameView.getIsBoy()){
                        gameView.boyMoveRight();
                    }
                    else{
                        gameView.girlMoveRight();
                    }
                    break;

                case R.id.array_left_Button:
                    if(gameView.getIsBoy()){
                        gameView.boyMoveLeft();
                    }
                    else{
                        gameView.girlMoveLeft();
                    }
                    break;

                case R.id.array_up_Button:
                    if(gameView.getIsBoy()){
                        gameView.boyJump();
                    }
                    else{
                        gameView.girlJump();
                    }
                    break;

                case R.id.change_Button:
                    if(gameView.getIsBoy()){
                        gameView.setIsBoy(0);
                        gameView.setScore();
                        setChangeNum();
                        break;
                    }
                    else{
                        gameView.setIsBoy(1);
                        gameView.setScore();
                        setChangeNum();
                        break;
                    }

                case R.id.clearButton:
                    Intent intent = new Intent(activity.getApplication(), StageSelect.class);
                    activity.startActivity(intent);

            }
        }
    }
}
