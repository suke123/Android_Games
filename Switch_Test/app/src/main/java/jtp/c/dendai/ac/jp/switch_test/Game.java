package jtp.c.dendai.ac.jp.switch_test;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by taka on 2017/10/22.
 */

public class Game{// implements View.OnClickListener{

    private Activity activity;
    //private TitleActivity title;
    private ImageButton left,right,change,jump;
    private TextView stagenameText;
    private String stagename;

    private Context context;

    private GameView gameView;

    Game(Activity a, Context context, String stagename, GameView gameView){
        this.activity = a;
        this.stagename = stagename;

        this.context = context;

        this.gameView = gameView;

        setID();
        setButtonOnClickListener();
        //setButtonOnLongClickListener();
        setStagename();
    }


    private void setStagename() {
        stagenameText.setText(stagename);
    }

    public void setID() {
        left = (ImageButton)activity.findViewById(R.id.array_left_Button);
        right = (ImageButton)activity.findViewById(R.id.array_right_Button);
        change = (ImageButton)activity.findViewById(R.id.change_Button);
        jump = (ImageButton)activity.findViewById(R.id.array_up_Button);

        stagenameText = (TextView)activity.findViewById(R.id.stagename_Text);
    }

    private void setButtonOnClickListener() {
        left.setOnClickListener(buttonClick);
        right.setOnClickListener(buttonClick);
        change.setOnClickListener(buttonClick);
        jump.setOnClickListener(buttonClick);
    }
/*
    private void setButtonOnLongClickListener() {
        left.setOnLongClickListener(buttonLongClick);
        right.setOnLongClickListener(buttonLongClick);
    }
*/
    private View.OnClickListener buttonClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.array_left_Button:
                    //Toast.makeText(context, "Left", Toast.LENGTH_SHORT).show();
                    if(gameView.getIsBoy()){
                        gameView.boyMoveLeft();
                    }
                    else{
                        gameView.girlMoveLeft();
                    }
                    break;

                case R.id.array_right_Button:
                    //Toast.makeText(context, "Right", Toast.LENGTH_SHORT).show();
                    if(gameView.getIsBoy()){
                        gameView.boyMoveRight();
                    }
                    else{
                        gameView.girlMoveRight();
                    }
                    break;

                case R.id.change_Button:
                    //Toast.makeText(context, "Change", Toast.LENGTH_SHORT).show();
                    if(gameView.getIsBoy()){
                        gameView.setIsBoy(0);
                        break;
                    }
                    else{
                        gameView.setIsBoy(1);
                        break;
                    }

                case R.id.array_up_Button:
                    //Toast.makeText(context, "Jump", Toast.LENGTH_SHORT).show();
                    if(gameView.getIsBoy()){
                        gameView.boyMoveUp();
                    }
                    else{
                        gameView.girlMoveUp();
                    }
                    break;
            }
        }
    };

    /*
    private View.OnLongClickListener buttonLongClick = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            switch (v.getId()) {
                case R.id.array_left_Button:
                    //Toast.makeText(context, "Left", Toast.LENGTH_SHORT).show();
                    if (gameView.getIsBoy()) {
                        gameView.boyMoveLeft();
                    } else {
                        gameView.girlMoveLeft();
                    }
                    //return true;
                    break;

                case R.id.array_right_Button:
                    //Toast.makeText(context, "Right", Toast.LENGTH_SHORT).show();
                    if (gameView.getIsBoy()) {
                        gameView.boyMoveRight();
                    } else {
                        gameView.girlMoveRight();
                    }
                    //return true;
                    break;

            }
            return false;
        }
    };
    */

}
