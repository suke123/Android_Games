package jp.ac.dendai.c.jtp.slideshowapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import java.io.File;
import java.io.StringReader;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    private ViewFlipper viewFlipper;
    private float firstTouch;
    private boolean isFlip = false;
    private ImageView firstImageView;
    private ImageView secondImageView;
    TextView textView;

    File dir = new File("¥¥Users¥¥taka¥¥Android_Games¥¥SlideshowApp¥¥app¥¥src¥¥main¥¥res¥¥drawable");
    String[] files = dir.list();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewFlipper = (ViewFlipper) findViewById(R.id.flipper);
        firstImageView = (ImageView) findViewById(R.id.imageview_first);
        secondImageView = (ImageView) findViewById(R.id.imageview_second);
        textView = (TextView) findViewById(R.id.textView);
        findViewById(R.id.layout_first).setOnTouchListener(this);
        findViewById(R.id.layout_second).setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int x = (int) event.getRawX();
        switch (v.getId()) {
            case R.id.layout_first:
            case R.id.layout_second:
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        firstTouch = event.getRawX();
                        return true;
                    case MotionEvent.ACTION_MOVE:
                        if (!isFlip) {
                            if (x - firstTouch > 50) {
                                isFlip = true;
                                viewFlipper.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.move_in_left));
                                viewFlipper.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.move_out_right));
                                textView.setText(files.length);
                                viewFlipper.showNext();
                            } else if (firstTouch - x > 50) {
                                isFlip = true;
                                viewFlipper.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.move_in_right));
                                viewFlipper.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.move_out_left));
                                viewFlipper.showPrevious();
                            }
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        isFlip = false;
                        break;
                }
        }

        return false;
    }
}
