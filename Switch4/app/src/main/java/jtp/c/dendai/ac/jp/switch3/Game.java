package jtp.c.dendai.ac.jp.switch3;

import android.app.Activity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by taka on 2017/10/22.
 */

public class Game implements View.OnClickListener{

    Activity activity;
    //private TitleActivity title;
    private ImageButton left,right,change,jump;
    private TextView stagenameText;
    private String stagename;

    Game(Activity a, String stagename){
        this.activity = a;
        this.stagename = stagename;

        setID();
        setButtonOnClickListener();
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
        left.setOnClickListener(this);
        right.setOnClickListener(this);
        change.setOnClickListener(this);
        jump.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.array_left_Button:

                break;
            case R.id.array_right_Button:

                break;
            case R.id.change_Button:

                break;

            case R.id.array_up_Button:

                break;
        }
    }
}
